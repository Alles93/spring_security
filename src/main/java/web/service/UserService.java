package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByName(String username);

    void updateUser(User user);

    void saveUser(User user);

    void deleteUserById(Long id);
}
