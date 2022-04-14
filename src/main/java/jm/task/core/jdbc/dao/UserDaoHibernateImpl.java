package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final String TABLE_CREATE_QUERY = "create table users\n" +
            "(\n" +
            "    id        serial,\n" +
            "    name varchar(255) not null,\n" +
            "    lastName  varchar(255) not null,\n" +
            "    age    integer,\n" +
            "    primary key (id))";

    private static final String TABLE_DROP_QUERY = "drop table if exists users";

    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery(TABLE_CREATE_QUERY).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery(TABLE_DROP_QUERY).addEntity(User.class).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = User.builder()
                .name(name)
                .lastName(lastName)
                .age(age)
                .build();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(User.builder().id(id).build());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from User").list();
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("DELETE from User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
