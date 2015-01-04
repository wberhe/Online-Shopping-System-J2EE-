package com.mycompany.services;

import com.mycompany.models.Category;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * This service class is responsible for saving category
 * 
 * @author shahin
 * @version 1.0.0
 */
@Stateless
@LocalBean
public class AdminService {

    @PersistenceContext
    private EntityManager em;
    /**
     *This method saves customer information into database. 
     * @param category
     * @return saved 
     */
      public boolean persistCustomer(Category category) {

        boolean saved = false;
        try {
            em.persist(category);
            saved = true;
        } catch (Exception e) {

        }

        return saved;
    }
}
