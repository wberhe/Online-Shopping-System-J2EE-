
package com.mycompany.mbean;

import com.mycompany.models.Role;
import com.mycompany.models.Vendor;
import com.mycompany.models.VendorUser;
import com.mycompany.services.RoleService;
import com.mycompany.services.UserService;
import com.mycompany.services.VendorService;
import com.mycompany.util.PasswordService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Vendor related tasks
 * @version 1.0.0
 * @author TalakB
 */
@Named
@SessionScoped
public class VendorMBean implements Serializable {

    @EJB
    private VendorService vendorService;
    private Vendor vendor = new Vendor();
    private VendorUser vuser = new VendorUser();
    private PasswordService passwordservice = new PasswordService();

    @EJB
    private UserService userService;
    
    @EJB
    RoleService roleService;

    @Inject
    private UserBean userbean;

    public VendorMBean() {
        Vendor vendor = new Vendor();
        VendorUser vuser = new VendorUser();
    }

    public VendorService getVendorService() {
        return vendorService;
    }

    public void setVendorService(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public VendorUser getVuser() {
        return vuser;
    }

    public void setVuser(VendorUser vuser) {
        this.vuser = vuser;
    }

    /**
     * Send request that will be approved by the admin.
     *
     * @return
     * @throws Exception
     */
    public String sendVendorRequest() throws Exception {

        vuser.setVendor(vendor);

        //encrypt vendor user passworcd 
        vuser.setPassword(passwordservice.encrypt(vuser.getPassword()));
        
        //by default the vendor user is assigned a customer role 
        Role vuserrole = roleService.getUserRoleBYUserCode(4);
        vuser.setRole(vuserrole);
        if (vendorService.sendVendorReq(vendor, vuser)) {
            return "vendorRequestSentConfirmation";
        }
        return null;
    }

    /**
     * List all the vendors
     *
     * @return
     */
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }
    
    /**
     * 
     * @return 
     */
     public List<Vendor> getNonApprovedVendors() {
        return vendorService.getNonApprovedVendors();
    }

    /**
     * Approve vendors
     *
     * @param vendor
     * @return to admin home page
     */
    public String approveVendor(Vendor vendor) {
        boolean approved = false;
        vendor.setApproved(true);
        if (vendorService.updateVendor(vendor)){
            approved = true;
        }

        //return the same page that shows the admin home
        return "admin_home";

    }


    
    
    
    
    /**
     * Checks the whether the email is registered or not
     * @param fc
     * @param c
     * @param value 
     */
    public void checkEmail(FacesContext fc, UIComponent c, Object value) {

        String email = (String) value;
        vuser = userService.findVuserBYEmailId(email);
        if (vuser != null) {
            throw new ValidatorException(
                    new FacesMessage("Email address registered. Please enter new email id"));
        }
    }

}
