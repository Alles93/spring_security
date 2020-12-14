package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;


import java.util.List;
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return sessionFactory.getCurrentSession().createQuery("from User where id = '" + id + "'", User.class).getSingleResult();
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public User getUserByName(String username) {
        return sessionFactory.getCurrentSession().createQuery("from User where username = '" + username + "'", User.class).getSingleResult();
    }

    @Override
    public Role getRoleByName(String name) {

        return sessionFactory.getCurrentSession().createQuery("from Role where name = '" + name + "'", Role.class).getSingleResult();
    }

    @Override
    public void addRole(Role role) {
        sessionFactory.getCurrentSession().save(role);
    }

}