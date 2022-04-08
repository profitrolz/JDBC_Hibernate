package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.apache.ibatis.jdbc.ScriptRunner;

import javax.sql.DataSource;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static DataSource dataSource = Util.getDataSource();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        dropUsersTable();
        try {
            SQLScriptRunner scriptRunner = new SQLScriptRunner(dataSource);
            scriptRunner.runSqlScript(Path.of(""));
            Path path = Paths.get(UserDaoJDBCImpl.class.getClassLoader().getResource("createTables.sql").toURI());
            scriptRunner.runSqlScript(path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            SQLScriptRunner scriptRunner = new SQLScriptRunner(dataSource);
            scriptRunner.runSqlScript(Path.of(""));
            Path path = Paths.get(UserDaoJDBCImpl.class.getClassLoader().getResource("dropTables.sql").toURI());
            scriptRunner.runSqlScript(path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
