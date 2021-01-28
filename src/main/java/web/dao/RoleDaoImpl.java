package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void deleteRoleById(Long id) {
        Role role = getRoleById(id);
        entityManager.remove(role);
        System.out.println("Пользователь удален " + role);
    }

    public Role getRoleByName(String name) {
        return (Role) entityManager.createQuery("select u from Role u where u.name=:name")
                .setParameter("name", name)
                .getSingleResult();
        //return entityManager.unwrap(Session.class).createQuery("from Role where name = '" + name + "'", Role.class).getSingleResult();
    }


    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    public void createRoles(String roleName) {
        entityManager.persist(new Role(roleName));
    }
}
