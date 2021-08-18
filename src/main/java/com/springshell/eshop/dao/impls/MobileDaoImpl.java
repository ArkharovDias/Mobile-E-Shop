package com.springshell.eshop.dao.impls;

import com.springshell.eshop.dao.AccessoryDao;
import com.springshell.eshop.exceptions.EntityNotFoundException;
import com.springshell.eshop.util.hibernate.HibernateUtil;
import com.springshell.eshop.dao.MobileDao;
import com.springshell.eshop.domain.entity.Mobile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MobileDaoImpl implements MobileDao {

    private static final Logger logger = LogManager.getLogger(MobileDao.class);

    @Override
    public Mobile findById(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Mobile result = null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Mobile.class);
            result = (Mobile) criteria.add(Restrictions.eq("id", id)).uniqueResult();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to find Mobile by id = " + id, e);
        }finally {
            session.close();
        }
        if (result == null){
            throw new EntityNotFoundException("Mobile is not found");
        }
        return result;
    }

    @Override
    public void create(Mobile entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();

            logger.info("Mobile saved successfully");
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to create Mobile", e);
        }finally {
            session.close();
        }
    }

    @Override
    public void update(Mobile entity, Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            entity.setId(id);
            session.update(entity);
            session.getTransaction().commit();

            logger.info("Mobile updated successfully");
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to update Mobile", e);
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
            Mobile mobile = (Mobile) session.createCriteria(Mobile.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
            session.delete(mobile);
            session.getTransaction().commit();

            logger.info("Mobile deleted successfully");
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to delete Mobile", e);
        }finally {
            session.close();
        }
    }

    @Override
    public List<Mobile> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Mobile> mobiles = null;
        try {
            session.beginTransaction();
            mobiles = session.createCriteria(Mobile.class).list();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to findAll Mobiles", e);
        }finally {
            session.close();
        }

        if (mobiles == null){
            throw new EntityNotFoundException("Mobiles not found");
        }
        return mobiles;
    }

    @Override
    public Mobile findByName(String name) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Mobile result = null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Mobile.class);
            result = (Mobile) criteria.add(Restrictions.eq("name", name)).uniqueResult();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to find Mobile by name = " + name, e);
        }finally {
            session.close();
        }
        if (result == null){
            throw new EntityNotFoundException("Mobile is not found");
        }
        return result;
    }
}
