/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.models.Address;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This service class is responsible for persisting address info.
 * 
 * @author Md Mojahidul Islam
 * @version 1.0.0
 */
@Stateless
public class AddressService {

    @PersistenceContext
    private EntityManager em;
    /**
     * This method saves address into database.
     * @param address
     * @return 
     */

    public boolean save(Address address) {
        boolean saved = false;
        try {
            em.persist(address);
            saved = true;
        } catch (Exception e) {

        }
        return saved;
    }

}
