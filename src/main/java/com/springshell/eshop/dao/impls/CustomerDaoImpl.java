package com.springshell.eshop.dao.impls;

import com.springshell.eshop.dao.AccessoryDao;
import com.springshell.eshop.exceptions.EntityNotFoundException;
import com.springshell.eshop.util.hibernate.HibernateUtil;
import com.springshell.eshop.dao.CustomerDao;
import com.springshell.eshop.domain.entity.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDaoImpl implements CustomerDao {

    private static final Logger logger = LogManager.getLogger(CustomerDao.class);

    @Override
    public Customer findById(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Customer customer = null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Customer.class);
            customer = (Customer) criteria.add(Restrictions.eq("id", id)).uniqueResult();
            Hibernate.initialize(customer.getOrders());
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to find Customer by id = " + id, e);
        }finally {
            session.close();
        }
        if (customer == null){
            throw new EntityNotFoundException("Customer is not found");
        }
        return customer;
    }

    @Override
    public void create(Customer entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();

            logger.info("Customer saved successfully");
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to create Customer", e);
        }finally {
            session.close();
        }
    }

    @Override
    public void update(Customer entity, Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            entity.setId(id);
            session.update(entity);
            session.getTransaction().commit();

            logger.info("Customer updated successfully");
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to update Customer", e);
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
            Customer customer = (Customer) session.createCriteria(Customer.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
            session.delete(customer);
            session.getTransaction().commit();

            logger.info("Customer deleted successfully");
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to delete Customer", e);
        }finally {
            session.close();
        }
    }

    @Override
    public List<Customer> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Customer> customers = null;
        try {
            session.beginTransaction();
            customers = session.createCriteria(Customer.class).list();
            for (Customer customer: customers) {
                Hibernate.initialize(customer.getOrders());
            }
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to findAll Customers", e);
        }finally {
            session.close();
        }

        if (customers == null){
            throw new EntityNotFoundException("Customers not found");
        }
        return customers;
    }

    @Override
    public Customer findByName(String name) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Customer customer = null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Customer.class);
            customer = (Customer) criteria.add(Restrictions.eq("name", name)).uniqueResult();
            /*List<Order> orders = customer.getOrders();
            Hibernate.initialize(orders);*/
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to find Customer by name = " + name, e);
        }finally {
            session.close();
        }
        if (customer == null){
            throw new EntityNotFoundException("Customer is not found");
        }
        return customer;
    }
}
