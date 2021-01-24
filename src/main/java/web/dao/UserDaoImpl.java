package web.dao;


import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        return entityManager.unwrap(Session.class).createQuery("from User", User.class).getResultList();

    }

    @Override
    public User getUserById(Long id) {
        return entityManager.unwrap(Session.class).createQuery("from User where id = '" + id + "'", User.class).getSingleResult();
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        entityManager.unwrap(Session.class).saveOrUpdate(user);
        System.out.println("Пользователь обновлен!");
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        entityManager.unwrap(Session.class).saveOrUpdate(user);
        System.out.println("Пользователь создан!");
    }

    @Override
    public void deleteUserById(Long id) {
        User user = getUserById(id);
        entityManager.unwrap(Session.class).delete(user);
        System.out.println("Пользователь удален " + id);
    }

    @Override
    public User getUserByName(String username) {
        return entityManager.unwrap(Session.class).createQuery("from User where username = '" + username + "'", User.class).getSingleResult();
    }

}