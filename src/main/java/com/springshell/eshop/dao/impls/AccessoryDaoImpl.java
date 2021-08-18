package com.springshell.eshop.dao.impls;

import com.springshell.eshop.exceptions.EntityNotFoundException;
import com.springshell.eshop.util.hibernate.HibernateUtil;
import com.springshell.eshop.dao.AccessoryDao;
import com.springshell.eshop.domain.entity.Accessory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccessoryDaoImpl implements AccessoryDao {

    private static final Logger logger = LogManager.getLogger(AccessoryDao.class);
    
    @Override
    public Accessory findById(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Accessory result = null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Accessory.class);
            result = (Accessory) criteria.add(Restrictions.eq("id", id)).uniqueResult();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to find Accessory by id = " + id, e);
        }finally {
            session.close();
        }
        if (result == null){
            throw new EntityNotFoundException("Accessory is not found");
        }
        return result;
    }

    @Override
    public void create(Accessory entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();

            logger.info("Accessory saved successfully");
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to create accessory", e);
        }finally {
            session.close();
        }
    }

    @Override
    public void update(Accessory entity, Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            entity.setId(id);
            session.update(entity);
            session.getTransaction().commit();

            logger.info("Accessory updated successfully");
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to update Accessory", e);
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
            Accessory accessory = (Accessory) session.createCriteria(Accessory.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
            session.delete(accessory);
            session.getTransaction().commit();

            logger.info("Accessory deleted successfully");
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to delete Accessory", e);
        }finally {
            session.close();
        }
    }

    @Override
    public List<Accessory> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Accessory> accessories = null;
        try {
            session.beginTransaction();
            accessories = session.createCriteria(Accessory.class).list();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to findAll Accessories", e);
        }finally {
            session.close();
        }

        if (accessories == null){
            throw new EntityNotFoundException("Accessories not found");
        }
        return accessories;
    }

    @Override
    public Accessory findByName(String name) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Accessory result = null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Accessory.class);
            result = (Accessory) criteria.add(Restrictions.eq("name", name)).uniqueResult();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to find Accessory by name = " + name, e);
        }finally {
            session.close();
        }
        if (result == null){
            throw new EntityNotFoundException("Accessory is not found");
        }
        return result;
    }
}
