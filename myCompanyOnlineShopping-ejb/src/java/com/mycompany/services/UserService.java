package com.mycompany.services;

import com.mandrill.clients.exception.RequestFailedException;
import com.mandrill.clients.model.MandrillTemplatedMessageRequest;
import com.mycompany.models.Role;
import com.mycompany.models.Users;
import com.mycompany.models.VendorUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This service class is responsible for All the CRUD operations on user entity.
 *
 * @author Talak
 * @version 1.0.0
 */
@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager em;
    
    @EJB
    MandrillService mandrillService;

    /**
     * This method saves user info into the database and returns status.
     *
     * @param user
     * @return saved
     */
    public boolean saveUser(Users user) {
        boolean saved = false;
        try {
            em.persist(user);
            saved = true;
            try {
                MandrillTemplatedMessageRequest mandrillMessage = mandrillService.getMandrillMessageObject(null, user, "Welcome to MyCompany.com!");
                if(mandrillMessage!=null){
                    mandrillService.sendTemplatedMessage(mandrillMessage);
                }
            } catch (RequestFailedException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
        }

        return saved;
    }

    /**
     * Update User Information by Md Mojahidul Islam
     *
     * @param user
     * @return
     */
       public Users updateUser(Users user) {     
 
        try {
            user=em.merge(user);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method authenticates user access and returns status.
     * 
     * @param user
     * @return userAuthenticated or null
     */
    public Users authenticateUser(Users user) {

        String userName = user.getEmail();
        String passw = user.getPassword();

        Query query = em.createNamedQuery("checkUser");
        query.setParameter("uname", userName);
        query.setParameter("upass", passw);

        if (!query.getResultList().isEmpty()) {
            Users userAuthenticated = (Users) query.getSingleResult();
            return userAuthenticated;
        }
        return null;
    }
/**
 * This method gives you user info by id
 * 
 * @param id
 * @return user
 */
    public Users findById(int id) {
        return em.find(Users.class, id);
    }
/**
 * This method gives you a role of particular user
 * 
 * @param user
 * @return userRole or null
 */
    public Role getUserRole(Users user) {
        Role userRole = em.find(Role.class, user.getRole().getId());
        if (userRole != null) {
            return userRole;
        } else {
            return null;
        }

    }
/**
 * This method gives you user info by email id.
 * 
 * @param email
 * @return registeredUser or null
 */
    public Users findBYEmailId(String email) {
        Query query = em.createNamedQuery("findUserByEmailId");
        query.setParameter("uemail", email);
        if (!query.getResultList().isEmpty()) {
            Users registeredUser = (Users) query.getSingleResult();
            return registeredUser;
        }
        return null;
    }
/**
 * This method gives vendor user info by email id.
 * 
 * @param email
 * @return registeredUser or null
 */
 
 public VendorUser findVuserBYEmailId(String email) {
        Query query = em.createNamedQuery("findUserByEmailId");
        query.setParameter("uemail", email);
        if (!query.getResultList().isEmpty()) {
            VendorUser registeredUser = (VendorUser) query.getSingleResult();
            return registeredUser;
        }
        return null;
    }

}
