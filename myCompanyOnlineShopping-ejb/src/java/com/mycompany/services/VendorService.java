package com.mycompany.services;

import com.mycompany.models.Role;
import com.mycompany.models.Vendor;
import com.mycompany.models.VendorUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * This service class is responsible for all CRUD operations on vendor entity.
 * 
 * @author Talak
 * @version 1.0.0
 */
@Stateless
@LocalBean
public class VendorService {

   
    @PersistenceContext
    private EntityManager em;
    
    /**
     * This method saves vendor and user in database and returns status.
     * 
     * @param vendor
     * @param user
     * @return saved
     */

    public boolean sendVendorReq(Vendor vendor, VendorUser user) {

        boolean saved = false;
        try {
            em.persist(vendor);
            em.persist(user);
            saved = true;
        } catch (Exception e) {

        }

        return saved;
    }
    /**
     * This method gets all the vendors from the database.
     * @return 
     */
    public List<Vendor> getAllVendors() {

        TypedQuery<Vendor> query = em.createNamedQuery("listAllVendors", Vendor.class);
        return query.getResultList();

    }
/**
 * This method gets all the non vendors from the database.
 * @return 
 */
    public List<Vendor> getNonApprovedVendors() {

        TypedQuery<Vendor> query = em.createNamedQuery("listNonApprovedVendors", Vendor.class);
        return query.getResultList();

    }

    /**
     * This method update vendor info in the database and returns status .
     *
     * @param vendor
     * @return updated
     */
    public boolean updateVendor(Vendor vendor) {
        boolean updated = false;
        try {
            em.merge(vendor);
            updated = true;
        } catch (Exception e) {

        }

        //if updated then update users role 
        if (updated) {
            if (!updateVendorUserRole(vendor)) {
                updated = false;
            }

        }

        return updated;

    }

    /**
     * This method sets the vendor user role 3 (vendor user) to approve and returns updated status
     *
     * @param vendor
     * @return 
     */
    public boolean updateVendorUserRole(Vendor vendor) {
        boolean vendorUserRoleupdated = false;
        TypedQuery<VendorUser> query = em.createNamedQuery("listVendorUsers", VendorUser.class);
        query.setParameter("vid", vendor.getId());
        List<VendorUser> vusers = query.getResultList();
        for (VendorUser v : vusers) {
            Role role = new Role();
            role.setId(3);
            v.setRole(role);

        }
        return vendorUserRoleupdated;

    }

    /**
     * This method checks whether vendor is approved or not and returns status accordingly.
     * @param vendor
     * @return 
     */
    public boolean isVendorApproved(Vendor vendor) {
        boolean approved = false;
        if (vendor.isApproved()) {
            approved = true;
        }
        return approved;
    }

    /**
     * This method gives vendor by ID
     *
     * @param id
     * @return vendor or null
     */
    public Vendor findVendor(int id) {
        Vendor vendor = em.find(Vendor.class, id);
        if (vendor != null) {
            return vendor;
        } else {
            return null;
        }
    }
}
