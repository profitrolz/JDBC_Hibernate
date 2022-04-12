package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Extractors {

    private Extractors(){}
    public static User extractUser(ResultSet rs, String idColumn, String nameColumn, String lastnameColumn, String ageColumn) throws SQLException {
        Extractor<User> extractor = Extractor.<User>newExtractorBuilder()
                .setLong(User::setId, idColumn)
                .setString(User::setName, nameColumn)
                .setString(User::setLastName, lastnameColumn)
                .setByte(User::setAge, ageColumn)
                .build();
        return extractor.extract(rs, new User());
    }
}
