package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource = Util.getDataSource();
        dataSource.getConnection();
        System.out.println("Ok!");

        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.dropUsersTable();
    }
}
