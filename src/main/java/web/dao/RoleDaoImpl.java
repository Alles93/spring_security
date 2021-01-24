package web.dao;

import org.hibernate.Session;
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



    public Role getRoleByName(String name) {
        return entityManager.unwrap(Session.class).createQuery("from Role where name = '" + name + "'", Role.class).getSingleResult();
    }


    public Role getRoleById(Long id) {
        return entityManager.unwrap(Session.class).createQuery("from Role where id = '" + id + "'", Role.class).getSingleResult();
    }

    public void createRoles(String roleName) {
        entityManager.persist(new Role(roleName));
    }
}
