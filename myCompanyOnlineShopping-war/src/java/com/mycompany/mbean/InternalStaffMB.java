package com.mycompany.mbean;

import com.mycompany.models.InternalUser;
import com.mycompany.models.Role;
import com.mycompany.models.Users;
import com.mycompany.services.RoleService;
import com.mycompany.services.UserService;
import com.mycompany.util.PasswordService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 
 * Creates internal user with userName and passWord
 * @version 1.0.0
 * @return newly created internal user
 * @author TalakB
 */
@Named
@SessionScoped
public class InternalStaffMB implements Serializable {

    @EJB
    private UserService userservice;
    private InternalUser internaluser = new InternalUser();

    private PasswordService encpass = new PasswordService();

    @EJB
    private RoleService roleservice;

    @Inject
    private UserBean usermb;

    public InternalStaffMB() {
        internaluser = new InternalUser();
        //   userrole = new Role();
        internaluser = new InternalUser();
    }

    public InternalUser getInternaluser() {
        return internaluser;
    }

    public void setInternaluser(InternalUser internaluser) {
        this.internaluser = internaluser;
    }
    
    /**
     * Adds internal users(staff)
     * @return to the add internal user confirmation home page
     * @throws Exception 
     */
    public String addInternalStaff() throws Exception {
        String internalUserPass = internaluser.getPassword();
        String encPass = encpass.encrypt(internalUserPass);
        internaluser.setPassword(encPass);
        // usermb.getLoggedInUser();
        //If logged in user is admin he can only create InternalStaff
        Role userrole = roleservice.getUserRoleBYUserCode(2);
        if (usermb.getLoggedInUser().getRole().getUserCode() == 1) {
            //Users InternalStaff = new InternalUser();
            //InternalStaff
            //  userrole.setId(2);
            internaluser.setRole(userrole);
            // userrole = internaluser.getRole();

        }
        //internaluser.setRole(userrole);

        if (userservice.saveUser(internaluser)) {
            return "adminAddInternalUserCOnfirmation";
        }
        return null;
    }

}
