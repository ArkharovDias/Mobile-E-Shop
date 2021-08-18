package com.springshell.eshop.dao.impls;

import com.springshell.eshop.dao.AccessoryDao;
import com.springshell.eshop.exceptions.EntityNotFoundException;
import com.springshell.eshop.util.hibernate.HibernateUtil;
import com.springshell.eshop.dao.ProductDao;
import com.springshell.eshop.domain.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = LogManager.getLogger(ProductDao.class);

    @Override
    public Product findById(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Product result = null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Product.class);
            result = (Product) criteria.add(Restrictions.eq("id", id)).uniqueResult();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        if (result == null){
            throw new EntityNotFoundException("Product is not found");
        }
        return result;
    }

    @Override
    public void create(Product entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public void update(Product entity, Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            entity.setId(id);
            session.update(entity);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
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
            Product product = (Product) session.createCriteria(Product.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
            session.delete(product);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public List<Product> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Product> products = null;
        try {
            session.beginTransaction();
            products = session.createCriteria(Product.class).list();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        if (products == null){
            throw new EntityNotFoundException("Products not found");
        }
        return products;
    }
}
