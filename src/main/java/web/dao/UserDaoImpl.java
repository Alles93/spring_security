package web.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
        System.out.println("Пользователь обновлен!");
    }

    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
        System.out.println("Пользователь создан!");
    }

    @Override
    public void deleteUserById(Long id) {
        User user = getUserById(id);
        entityManager.remove(user);
        System.out.println("Пользователь удален " + id);
    }

    @Override
    public User getUserByName(String username) {
        try {
        User user = (User) entityManager.createQuery("select u from User u where u.username=:username")
                .setParameter("username", username)
                .getSingleResult();
            return user;
        } catch (NoResultException ex) {
            return null;
        }

    }
}