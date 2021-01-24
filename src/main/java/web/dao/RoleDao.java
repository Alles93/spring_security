package web.dao;

import web.model.Role;

public interface RoleDao {
    Role getRoleByName (String name);
    Role getRoleById(Long id);
    void createRoles (String roleName);
}
