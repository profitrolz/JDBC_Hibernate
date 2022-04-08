package jm.task.core.jdbc.util;

import jm.task.core.jdbc.service.DBPropertiesLoader;
import jm.task.core.jdbc.service.exceptions.DomainException;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class Util {
    public static DataSource getDataSource() {
        DBPropertiesLoader propertiesLoader = new DBPropertiesLoader();
        Properties properties = null;
        try {
            properties = propertiesLoader.load("dbproperties.properties");
        } catch (IOException e) {
            throw new DomainException("Failed to load DB properties", e);
        }

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(properties.getProperty("host"));
        dataSource.setUsername(properties.getProperty("user"));
        dataSource.setPassword(properties.getProperty("password"));

        return dataSource;
    }

}
