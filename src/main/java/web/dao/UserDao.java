package web.dao;



import web.model.Role;
import web.model.User;
import java.util.List;

public interface UserDao{

    List<User> getAllUsers();

    User getUserById(Long id);

    void updateUser(User user);

    void deleteUser(Long id);

    User getUserByName(String username);

    Role getRoleByName(String name);

    void addRole(Role role);
}
