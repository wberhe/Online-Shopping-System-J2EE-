package com.mycompany.mbean;

import com.mycompany.models.Category;
import com.mycompany.services.AdminService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Manages the admin action 
 * approves the vendors, creates the internal users(staff users) and adds Product category
 * @returns category, internal users, vendor acceptance
 * @version 1.0.0
 * @author TalakB
 * 
 */
@Named
@SessionScoped
public class AdminMB implements Serializable {

    @EJB
    private AdminService adminservice;

    private Category category = new Category();

    public AdminMB() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Staff users can be created only by admin. 
     * @return
     */
    public String addStaffUser() {
        return "adminAddInternalUserRegistration";
    }

    /**
     * Displays vendor request
     *@return to the admin vendor approval home page
     */
    public String showVendorRequest() {
        return "adminVendorRequestApproval";
    }
    /**
     * Add product category
     * @return to the admin add category home page 
     */
    public String addCategory() {
        return "adminAddcategory";
    }
    /**
     * Saves the newly created category
     * @return to the admin home page
     * @throws Exception 
     */
    public String saveCategory() throws Exception {

        if (adminservice.persistCustomer(category)) {
            return "admin_home";
        }
        return null;
    }

}
