package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService service = new UserServiceImpl();
        service.createUsersTable();

        service.saveUser("Johnny", "Sins", (byte)42);
        service.saveUser("Patrik", "Betta", (byte)36);
        service.saveUser("Li", "Keiran", (byte)35);
        service.saveUser("James", "Din", (byte)27);

        service.getAllUsers().forEach(System.out::println);

        service.cleanUsersTable();
        service.dropUsersTable();

    }
}
