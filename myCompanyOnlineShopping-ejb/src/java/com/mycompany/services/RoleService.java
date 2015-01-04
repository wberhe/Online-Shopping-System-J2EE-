package com.mycompany.services;

import com.mycompany.models.Role;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This service class is responsible for finding user role by id and getting user role by user code.
 * 
 * @author Md Mojahidul Islam
 * @version 1.0.0
 */
@Stateless
@LocalBean
public class RoleService {

    @PersistenceContext
    EntityManager em;

    /**
     * This method finds role by ID
     *
     * @param id
     * @return userRole
     */
    public Role findById(int id) {
        return em.find(Role.class, id);
    }

    /**
     * This methods get which user role is the user assigned.
     *
     * @param ucode
     * @param user
     * @return userRole or role
     */
    public Role getUserRoleBYUserCode(int ucode) {
        Query query = em.createNamedQuery("findRoleByUserCode");
        query.setParameter("rcode", ucode);
        if (!query.getResultList().isEmpty()) {
            Role userRole = (Role) query.getSingleResult();
            return userRole;
        }
        return null;

    }
}
