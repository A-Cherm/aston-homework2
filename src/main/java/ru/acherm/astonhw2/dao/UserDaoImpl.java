package ru.acherm.astonhw2.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.acherm.astonhw2.model.User;

public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoImpl() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User create(User user) {
        try {
            sessionFactory.inTransaction(session -> session.persist(user));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public User get(long id) {
        return sessionFactory.fromTransaction(session -> session.find(User.class, id));
    }

    @Override
    public User update(User user) {
        return sessionFactory.fromTransaction(session -> session.merge(user));
    }

    @Override
    public void delete(long id) {
        sessionFactory.inTransaction(session -> session.createMutationQuery("delete from User where id = :id")
                .setParameter("id", id)
                .executeUpdate());
    }
}
