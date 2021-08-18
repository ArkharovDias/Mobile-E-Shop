package com.springshell.eshop.dao.impls;

import com.springshell.eshop.dao.AccessoryDao;
import com.springshell.eshop.exceptions.EntityNotFoundException;
import com.springshell.eshop.util.hibernate.HibernateUtil;
import com.springshell.eshop.dao.OrderDao;
import com.springshell.eshop.domain.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = LogManager.getLogger(OrderDao.class);

    @Override
    public Order findById(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Order result = null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Order.class);
            result = (Order) criteria.add(Restrictions.eq("id", id)).uniqueResult();
            Hibernate.initialize(result.getProducts());
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to find Order by id = " + id, e);
        }finally {
            session.close();
        }
        if (result == null){
            throw new EntityNotFoundException("Order is not found");
        }
        return result;
    }

    @Override
    public void create(Order entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();

            logger.info("Order saved successfully");
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to create Order", e);
        }finally {
            session.close();
        }
    }

    @Override
    public void update(Order entity, Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            entity.setId(id);
            session.update(entity);
            session.getTransaction().commit();

            logger.info("Order updated successfully");
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to update Order", e);
        }finally {
            session.close();
        }
    }

    @Override
    public void delete(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Order order = (Order) session.createCriteria(Order.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
            session.delete(order);
            session.getTransaction().commit();

            logger.info("Order deleted successfully");
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to delete Order", e);
        }finally {
            session.close();
        }
    }

    @Override
    public List<Order> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Order> orders = null;
        try {
            session.beginTransaction();
            orders = session.createCriteria(Order.class).list();
            orders.forEach(n -> Hibernate.initialize(n.getProducts()));
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to findAll Orders", e);
        }finally {
            session.close();
        }

        if (orders == null){
            throw new EntityNotFoundException("Orders not found");
        }
        return orders;
    }
}
