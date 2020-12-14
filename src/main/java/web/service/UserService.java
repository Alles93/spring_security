package web.service;


import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    void updateUser(User user);

    void deleteUser(Long id);

    User getUserByName(String username);

    Role getRoleByName(String name);

    void addRole(Role role);
}
