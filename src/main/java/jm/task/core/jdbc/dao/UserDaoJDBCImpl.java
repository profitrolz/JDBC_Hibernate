package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.apache.ibatis.jdbc.ScriptRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static DataSource dataSource = Util.getDataSource();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        dropUsersTable();
        try {
            SQLScriptRunner scriptRunner = new SQLScriptRunner(dataSource);
            Path path = Paths.get(UserDaoJDBCImpl.class.getClassLoader()
                    .getResource("createTables.sql")
                    .toURI());
            scriptRunner.runSqlScript(path);
        } catch (URISyntaxException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            SQLScriptRunner scriptRunner = new SQLScriptRunner(dataSource);
            Path path = Paths.get(UserDaoJDBCImpl.class.getClassLoader()
                    .getResource("dropTables.sql")
                    .toURI());
            scriptRunner.runSqlScript(path);
        } catch (URISyntaxException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "Insert into users (name, lastName, age) values(?,?,?)";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String query = "Delete from users where id = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "Select * from users";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                users.add(Extractors.extractUser(rs, "id", "name", "lastName", "age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        String query = "Delete from users";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
