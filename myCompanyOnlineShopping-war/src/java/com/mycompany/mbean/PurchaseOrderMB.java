/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mbean;

import com.mycompany.interfaces.CardInformationServiceLocal;
import com.mycompany.interfaces.PurchaseOrderServiceLocal;
import com.mycompany.interfaces.ShoppingCartServicesLocal;
import com.mycompany.models.BillingAddress;
import com.mycompany.models.CreditCard;
import com.mycompany.models.Product;
import com.mycompany.models.PurchaseOrder;
import com.mycompany.models.ShippingAddress;
import com.mycompany.models.ShoppingCart;
import com.mycompany.models.ShoppingCartItem;
import com.mycompany.models.Users;
import com.mycompany.services.AddressService;
import com.mycompany.services.ProductService;
import com.mycompany.services.UserService;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 * 
 * Placing an order
 * @version 1.0.0
 * @author Md Mojahidul Islam
 */
@Named(value = "purchaseOrderMB")
@SessionScoped
public class PurchaseOrderMB implements Serializable {

    @EJB
    private PurchaseOrderServiceLocal purchaseOrderService;

    @EJB
    private ShoppingCartServicesLocal shoppingCartService;

    @EJB
    private ProductService productService;

    @EJB
    private CardInformationServiceLocal cardInfoService;

    @EJB
    private UserService userService;

    @EJB
    private AddressService addressService;

    private CreditCard cardInfo;

    private BillingAddress billingAddress;

    private ShippingAddress shippingAddress;

    private PurchaseOrder purchaseOrder;

    private ShoppingCart shoppingCart = new ShoppingCart();

    private Product product;

    private int productQty;

    private double price;

    private int noOfItemsInTheCart;

    private Users usr=null;
    
    private String redirect;
    
    /**
     * constructor
     */
    public PurchaseOrderMB() {
        purchaseOrder = new PurchaseOrder();
        billingAddress = new BillingAddress();
        shippingAddress = new ShippingAddress();
        cardInfo = new CreditCard();
        productQty = 1;
    }
    
    /**
     * Refreshes the session
     */
    public void refresh() {
        HttpSession activeSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        usr = (Users) activeSession.getAttribute("loggedUser");

        if (usr != null) {
            if(usr.getBillingAddress()==null) {
                usr.setBillingAddress(new BillingAddress());
            }
            billingAddress = usr.getBillingAddress();
            
            if(usr.getShippingAddress()==null) {
                usr.setShoppingCart(new ShoppingCart());
            }
            shippingAddress = usr.getShippingAddress();
        }
    }

    private void updateShoppingCartTotalCost(){
        double total = 0;
        for(ShoppingCartItem shoppingCartItem: shoppingCart.getShoppingCartItems()) {
            total += shoppingCartItem.getPrice() * shoppingCartItem.getQuantity();
        }
        shoppingCart.setTotalPrice(total);
    }

    public String addToCart(int productId) {

        //TODO: I am adding some demo value about a product
        product = productService.get(productId);
        //productQty = 5;
        //price = 100;

        if (shoppingCart.getId() == 0) {
            shoppingCart.setUser(usr);
            shoppingCart.setShopDate(Calendar.getInstance());
            //shoppingCart.setTotalPrice(100000.00);
        }

        List<ShoppingCartItem> cartItems = shoppingCart.getShoppingCartItems();
        ShoppingCartItem item = new ShoppingCartItem();
        item.setProduct(product);
        item.setQuantity(productQty);
        item.setPrice(productQty * product.getPrice());
        item.setShoppingCart(shoppingCart);
        cartItems.add(item);
        
        productQty = 1;

        //TODO: Change user with proper user info and Total Price
        shoppingCart = shoppingCartService.addToCart(shoppingCart);
        if (shoppingCart != null) {
            System.out.println("Product in Shopping cart Add Successful");

            noOfItemsInTheCart = shoppingCart.getShoppingCartItems().size();

        } else {
            System.out.println("Product add failed");
        }
        updateShoppingCartTotalCost();
        return "cart";
    }
    /**
     * 
     * removes the product from the cart
     * @param item 
     */
    public void removeProduct(ShoppingCartItem item) {
        shoppingCart = shoppingCartService.removeFromCart(shoppingCart, item);
        updateShoppingCartTotalCost();
    }
    /**
     * Updating the cart
     * @param item 
     */
    public void updateProduct(ShoppingCartItem item) {

        for (ShoppingCartItem cartItem : shoppingCart.getShoppingCartItems()) {
            if (cartItem.getId() == item.getId()) {
                cartItem.setPrice(item.getQuantity() * item.getProduct().getPrice());
            }
        }
        shoppingCart = shoppingCartService.addToCart(shoppingCart);
        updateShoppingCartTotalCost();
    }
    /**
     * Save both shipping and billing addresses 
     * @return user shopping cart home page
     */
    public String saveAddress() {

        System.out.println("Cart " + shoppingCart);

        HttpSession activeSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        usr = (Users) activeSession.getAttribute("loggedUser");

        if (addressService.save(billingAddress) && addressService.save(shippingAddress)) {

            if (usr != null) {

                usr.setBillingAddress(billingAddress);
                usr.setShippingAddress(shippingAddress);

                usr = userService.updateUser(usr);

                shoppingCart.setUser(usr);
                shoppingCart = shoppingCartService.addToCart(shoppingCart);
            }

            return "card?faces-redirect=true";
        }
        return "fail?faces-redirect=true";
    }
    /**
     * 
     * Saves the credit card information
     * @return to order home page
     */
    public String saveCardInformation() {
        
        cardInfo=cardInfoService.save(cardInfo);
        
        
        
        purchaseOrder=purchaseOrderService.saveOrder(shoppingCart, billingAddress, shippingAddress, cardInfo);
        
        purchaseOrder.getCardInformation().setCardNumber(purchaseOrder.getCardInformation().getCardNumber().substring(12));

        return "orderDetail?faces-redirect=true";
    }
    
    public String checkout(){
        if(usr==null){
            UserBean.setRedirect("address");
            return "customerLogin";
        } else {
            billingAddress = usr.getBillingAddress();
            shippingAddress = usr.getShippingAddress();

            return "address";
        }
    }
    /**
     * 
     * @return to user registration home page
     */
    public String registerForPurchase() {
        UserBean.setRedirect("customerLogin");
        return "user_registration";
    }

    public CreditCard getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CreditCard cardInfo) {
        this.cardInfo = cardInfo;
    }

    public BillingAddress getBillingAddress() {
        refresh();
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public ShippingAddress getShippingAddress() {
        refresh();
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void findOrderDetails() {
        purchaseOrder = purchaseOrderService.findById(1);
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNoOfItemsInTheCart() {
        return noOfItemsInTheCart;
    }

    public void setNoOfItemsInTheCart(int noOfItemsInTheCart) {
        this.noOfItemsInTheCart = noOfItemsInTheCart;
    }

    public Users getUsr() {
        return usr;
    }

    public void setUsr(Users usr) {
        this.usr = usr;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

}
