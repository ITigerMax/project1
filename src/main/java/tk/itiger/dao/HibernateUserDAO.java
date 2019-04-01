package tk.itiger.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.itiger.model.User;

import java.util.List;

@Component
public class HibernateUserDAO implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<User> getAll() {
        return currentSession().createQuery("from User", User.class).list();
    }

    @Override
    public User getOne(String email) {
        Query<User> query = currentSession()
                .createQuery("from User where email = :email", User.class);
        query.setParameter("email", email);
        return query.list().stream().findAny().orElse(null);
    }

    @Override
    public void addUser(User user) {
        currentSession().save(user);
    }
}
