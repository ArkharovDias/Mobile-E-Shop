package com.springshell.eshop.dao.impls;

import com.springshell.eshop.dao.AccessoryDao;
import com.springshell.eshop.exceptions.EntityNotFoundException;
import com.springshell.eshop.util.hibernate.HibernateUtil;
import com.springshell.eshop.dao.AccountDao;
import com.springshell.eshop.domain.entity.Account;
import com.springshell.eshop.domain.entity.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountDaoImpl implements AccountDao {

    private static final Logger logger = LogManager.getLogger(AccessoryDao.class);

    @Override
    public Account findById(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Account result = null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Account.class);
            result = (Account) criteria.add(Restrictions.eq("id", id)).uniqueResult();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to find Account by id = " + id, e);
        }finally {
            session.close();
        }
        if (result == null){
            throw new EntityNotFoundException("Account is not found");
        }
        return result;
    }

    @Override
    public void create(Account entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();

            logger.info("Account saved successfully");
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to create Account", e);
        }finally {
            session.close();
        }
    }

    @Override
    public void update(Account entity, Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            entity.setId(id);
            session.update(entity);
            session.getTransaction().commit();

            logger.info("Account updated successfully");
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to update Account", e);
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
            Account account = (Account) session.createCriteria(Account.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
            session.delete(account);
            session.getTransaction().commit();

            logger.info("Account deleted successfully");
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to delete Account", e);
        }finally {
            session.close();
        }
    }

    @Override
    public List<Account> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Account> accounts = null;
        try {
            session.beginTransaction();
            accounts= session.createCriteria(Account.class).list();
            for (Account account: accounts){
                if (account.getCustomer() == null){
                    account.setCustomer(Customer.builder().build());
                }
            }
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to findAll Accounts", e);
        }finally {
            session.close();
        }

        if (accounts == null){
            throw new EntityNotFoundException("Accounts not found");
        }
        return accounts;
    }

    @Override
    public Account findByLogin(String login) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Account account = null;
        try {
            session.beginTransaction();
            account = (Account) session.createCriteria(Account.class)
                    .add(Restrictions.eq("login", login))
                    .uniqueResult();
            /*Customer customer = account.getCustomer();
            Hibernate.initialize(customer);
            List<Order> orders = customer.getOrders();
            Hibernate.initialize(orders);
            orders.forEach(n ->{
                List<Product> products = n.getProducts();
                Hibernate.initialize(products);
            });*/
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            logger.error("Failed to find Account by name = " + login, e);
        }finally {
            session.close();
        }

        if (account == null){
            throw new EntityNotFoundException("Accounts not found");
        }
        return account;
    }
}
