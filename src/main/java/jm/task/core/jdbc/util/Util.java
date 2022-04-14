package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.DBPropertiesLoader;
import jm.task.core.jdbc.service.exceptions.DomainException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class Util {

    private static BasicDataSource dataSource;
    private static SessionFactory sessionFactory;

    private Util() {
    }

    public static DataSource getDataSource() {

        if (dataSource != null) {
            return dataSource;
        }

        DBPropertiesLoader propertiesLoader = new DBPropertiesLoader();
        Properties properties;
        try {
            properties = propertiesLoader.load("dbproperties.properties");
        } catch (IOException e) {
            throw new DomainException("Failed to load DB properties", e);
        }

        dataSource = new BasicDataSource();
        dataSource.setUrl(properties.getProperty("host"));
        dataSource.setUsername(properties.getProperty("user"));
        dataSource.setPassword(properties.getProperty("password"));

        return dataSource;
    }

    public static SessionFactory getSessionFactory() {

        if (sessionFactory != null) {
            return sessionFactory;
        }
        Properties prop = new Properties();

        prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/kata_pre-project_sql");

        prop.setProperty("dialect", "org.hibernate.dialect.MySQL");

        prop.setProperty("hibernate.connection.username", "root");
        prop.setProperty("hibernate.connection.password", "root");
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        prop.setProperty("environment.show_sql", "true");

        try {
            Configuration configuration = new Configuration().addProperties(prop);
            configuration.addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(prop);
            sessionFactory = configuration.buildSessionFactory(builder.build());

        } catch (Exception e) {
            throw new DomainException("Failed to load DB properties", e);
        }

        return sessionFactory;
    }
}
