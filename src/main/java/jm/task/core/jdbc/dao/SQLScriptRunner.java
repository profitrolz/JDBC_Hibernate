package jm.task.core.jdbc.dao;


import org.apache.ibatis.jdbc.ScriptRunner;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLScriptRunner {

    private final DataSource dataSource;

    public SQLScriptRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void runSqlScript(Path script){

        try (Connection connection = dataSource.getConnection()) {
            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(script.toFile()))){
                ScriptRunner sr = new ScriptRunner(connection);
                sr.runScript(bufferedReader);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
