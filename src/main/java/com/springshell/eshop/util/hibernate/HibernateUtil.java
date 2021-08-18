package com.springshell.eshop.util.hibernate;

import com.springshell.eshop.domain.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Map;
import java.util.Properties;

public class HibernateUtil {

    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            try {

                //Map<String, String> env = System.getenv();
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/mobile_e_shop?createDatabaseIfNotExist=true&useUnicode=true&serverTimezone=UTC");
                //settings.put(Environment.URL, env.get("SPRING_DATASOURCE_URL"));*settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "Dia$514080");
                settings.put(Environment.USER, "root");
                //settings.put(Environment.USER, env.get("SPRING_DATASOURCE_USERNAME"));
                //settings.put(Environment.PASS, env.get("SPRING_DATASOURCE_PASSWORD"));
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.addProperties(settings);

                configuration.addAnnotatedClass(Accessory.class);
                configuration.addAnnotatedClass(Account.class);
                configuration.addAnnotatedClass(Customer.class);
                configuration.addAnnotatedClass(Mobile.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(Product.class);

                sessionFactory = configuration.buildSessionFactory();

                logger.info("SessionFactory created.");
            }catch (Exception e){
                logger.error("SessionFactory creation failed", e);
                if (sessionFactory != null){
                    sessionFactory.close();
                }
            }
        }
        return sessionFactory;
    }

}
