
package com.mycompany.services;

import com.mycompany.models.CreditCard;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * This service class is responsible for persisting credit card info.
 * 
 * @author TalakB
 * @version 1.0.0
 */
@Stateless
@LocalBean
public class CreditCardService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    private EntityManager em;

    /**
     * This method saves credit card info. into database.
     * 
     * @param userCreditCard
     * @return 
     */
    public boolean saveCeditCard(CreditCard userCreditCard) {
        //encrypt users password before persist 
        boolean saved = false;
        try {
            em.persist(userCreditCard);
            saved = true;
        } catch (Exception e) {

        }

        return saved;
    }
}
