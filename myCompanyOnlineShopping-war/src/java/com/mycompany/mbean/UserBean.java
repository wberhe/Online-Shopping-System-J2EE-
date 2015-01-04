package com.mycompany.mbean;

import com.mycompany.services.CreditCardService;
import com.mycompany.services.UserService;
import com.mycompany.models.CreditCard;
import com.mycompany.models.Product;
import com.mycompany.models.Role;
import com.mycompany.models.Users;
import com.mycompany.models.Vendor;
import com.mycompany.models.VendorUser;
import com.mycompany.services.ProductService;
import com.mycompany.services.RoleService;
import com.mycompany.services.VendorService;
import com.mycompany.util.PasswordService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 * User related task
 * @version 1.0.0
 * @author TalakB User bean
 */
@Named
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    private UserService userservice;
    private Users user = new Users();
    private Role userrole = new Role();

    @EJB
    private CreditCardService creaditCardService;
    private CreditCard userCreditCard = new CreditCard();

    @EJB
    private VendorService vendorService;
    private VendorUser vendoruser = new VendorUser();
    
    @EJB
    private ProductService productService;

    @Inject
    CreditCardBean creditcardMBean;

    @EJB
    private RoleService roleservice;

    private HttpSession activeSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

    private PasswordService encpass = new PasswordService();
    private Vendor vendor = new Vendor();
    
    private boolean userLogged;
    private static String redirect = "";

    public static String getRedirect() {
        return redirect;
    }

    public static void setRedirect(String redirect) {
        UserBean.redirect = redirect;
    }

    public UserBean() {
        user = new Users();
        userCreditCard = new CreditCard();
        userrole = new Role();
        vendor = new Vendor();

    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public boolean isIsUserLogged() {
        return userLogged;
    }

    public void setIsUserLogged(boolean userLogged) {
        this.userLogged = userLogged;
    }

    /**
     * add customer and
     *
     * @return
     * @throws Exception
     */
    public String addUsers() throws Exception {
        String encPass = encryptUserPassword(user.getPassword());
        user.setPassword(encPass);

        //Registered users are customers and user role 4 is mapped to customer 
        // find the role for the use by default 
        userrole = roleservice.getUserRoleBYUserCode(4);
        //  userrole.setUserCode(1);
        user.setRole(userrole);

        // generate credit card detail
//        userCreditCard.setCardholderName(user.getFirstName() + " " + user.getLastName());
//        creditcardMBean.saveCreditCardDetail(userCreditCard);
        if (userservice.saveUser(user)) {
            
             if (redirect != null && redirect.length() > 0) {
                 String redirectPage=redirect;
                 redirect="address";
                return redirectPage;
            } else {
                return "registration_confirmation";
            }
        }
        return null;
    }
    /**
     * Login registered users
     * @return to user login home page
     * @throws Exception 
     */
    public String loginUser() throws Exception {
        System.out.println("redi = "+ redirect);
        
        String encPass = encryptUserPassword(user.getPassword());
        user.setPassword(encPass);
        String retURL = "";

        if (userservice.authenticateUser(user) != null) {
            //get the authenticated user 
            user = userservice.authenticateUser(user);
            userrole = userservice.getUserRole(user);
            //admin
            if (userrole.getUserCode() == 1) {
                activeSession.setAttribute("loggedUser", user);
                userLogged = true;
                retURL = "admin_home";
            } //vedor user 

            else if (userrole.getUserCode() == 2) {
                activeSession.setAttribute("loggedUser", user);
                userLogged = true;
                retURL = "internalUserHomePage";
            } //vedor user
            else if (userrole.getUserCode() == 3) {
                //check if the vendor company is approved
                vendoruser = (VendorUser) user;
                vendor = vendorService.findVendor(vendoruser.getVendor().getId());
                if (vendorService.isVendorApproved(vendor)) {
                     activeSession.setAttribute("loggedUser", user);
  					userLogged = true;
                    
                    retURL = "vendoruser_home";
                }

            } //cusotmer 
            else if (userrole.getUserCode() == 4) {
                userLogged = true;
                activeSession.setAttribute("loggedUser", user);
                retURL = "index";
            } else {
                retURL = "index";
            }

            if (redirect != null && redirect.length() > 0) {
                return redirect;
            } else {
                return retURL;
            }

        }
        return null;
    }

    public String registerUser() {
        user = new Users();
        return "user_registration";
    }
    /**
     * User encryption
     * @param password
     * @return
     * @throws Exception 
     */
    public String encryptUserPassword(String password) throws Exception {
        String encrptedPass = encpass.encrypt(user.getPassword());
        return encrptedPass;
    }

    /**
     * Logout user -Invalidate the session and redirect to home page
     * @return to home page
     */
    public String logoutUser() {
        activeSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        activeSession.invalidate();
        userLogged = false;
        return "index";
    }

    /**
     * Get currently logged user. Keep user object in session
     *
     * @return
     */
    public Users getLoggedInUser() {
        return (Users) activeSession.getAttribute("loggedUser");
    }

    /**
     * Check if the email is registered before.
     *
     * @param fc
     * @param c
     * @param value
     */
    public void checkEmail(FacesContext fc, UIComponent c, Object value) {
        String email = (String) value;
        Users userCheck = userservice.findBYEmailId(email);
        if (userCheck != null) {
            throw new ValidatorException(
                    new FacesMessage("Email address registered. Please enter new email id."));
        }
    }

    /**
     * Check if the user is logged in and what role is he assigned to restrict
     * pages.
     * @return 
     * @param event
     */
    public void isAdminUser(ComponentSystemEvent event) {

        FacesContext context = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();

        //if the user is not logged in then redirect to log in page 
        if (!userLogged) {
            handler.performNavigation("index");
        }//check type of user
        else {
            //non admin users redirect to the index page
            if (!(user.getRole().getUserCode() == 1)) {
                handler.performNavigation("accessDenied");
            }
        }
    }

    /**
     * pages that are allowed only for vendor users
     * @return to index home page
     * @param event
     */
    public void isVendorUser(ComponentSystemEvent event) {

        FacesContext context = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();

        //if the user is not logged in then redirect to log in page 
        if (!userLogged) {
            handler.performNavigation("index");
        }//check type of user
        else {
            //non admin users redirect to the index page
            if (!(user.getRole().getUserCode() == 3)) {
                handler.performNavigation("accessDenied");
            }
        }
    }

    /**
     * Only for staff users
     *
     * @param event
     */
    public void isInternalStaff(ComponentSystemEvent event) {

        FacesContext context = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();

        //if the user is not logged in then redirect to log in page 
        if (!userLogged) {
            handler.performNavigation("index");
        }//check type of user
        else {
            //non admin users redirect to the index page
            if (!(user.getRole().getUserCode() == 2)) {
                handler.performNavigation("accessDenied");
            }

        }
    }

    /**
     * Only for staff users
     *
     * @param event
     */
    public void isRegisteredUser(ComponentSystemEvent event) {

        FacesContext context = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();

        //if the user is not logged in then redirect to log in page 
        if (!userLogged) {
            handler.performNavigation("index");
        }//check type of user
        else {
            //non admin users redirect to the index page
            if (!(user.getRole().getUserCode() == 4)) {
                handler.performNavigation("accessDenied");
            }

        }
    }

}
