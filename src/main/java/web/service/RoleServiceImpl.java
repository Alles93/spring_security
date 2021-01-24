package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    private static List<String> allRoles = Arrays.asList("ROLE_ADMIN", "ROLE_USER");

    @Transactional(readOnly = true)
    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }


    @Transactional(readOnly = true)
    @Override
    public void createRoles() {
        for (String role : allRoles
        ) {
            roleDao.createRoles(role);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllRoles() {
        return allRoles;
    }
}
