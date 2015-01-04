package com.mycompany.mbean;

import com.mycompany.models.Product;
import com.mycompany.models.ShoppingCart;
import com.mycompany.models.ShoppingCartItem;
import com.mycompany.interfaces.ShoppingCartServicesLocal;
import com.mycompany.models.Users;
import com.mycompany.services.ProductService;
import com.mycompany.services.UserService;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Shopping Cart managed bean is used to perform all shopping related
 * information. For example add to cart, remove from cart etc will be handled by
 * this class.
 *
 * @author Md Mojahidul Islam
 * @version 0.0.1
 */
@Named(value = "shoppingCartMB")
@SessionScoped
public class ShoppingCartMB implements Serializable {

    @EJB
    private ShoppingCartServicesLocal shoppingCartService;

    @EJB
    private UserService userService;

    @EJB
    private ProductService productService;

    private ShoppingCart shoppingCart = new ShoppingCart();

    private Product product;

    private int productQty;

    private double price;

    private int noOfItemsInTheCart;
    
    private Users usr;

    public ShoppingCartMB() {
    }
    
    @PostConstruct
    public void init(){
        HttpSession activeSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
         usr= (Users) activeSession.getAttribute("loggedUser");
    }
    /**
     * Add products to cart
     * @param productId 
     */
    public void addToCart(int productId) {

        //TODO: I am adding some demo value about a product
        product = productService.get(productId);
        //productQty = 5;
        //price = 100;

        if (shoppingCart.getId()==0) {
            shoppingCart.setUser(usr);
            shoppingCart.setShopDate(Calendar.getInstance());
            //shoppingCart.setTotalPrice(100000.00);
        }

        List<ShoppingCartItem> cartItems = shoppingCart.getShoppingCartItems();
        ShoppingCartItem item = new ShoppingCartItem();
        item.setProduct(product);
        item.setQuantity(productQty);
        item.setPrice(productQty*product.getPrice());
        item.setShoppingCart(shoppingCart);
        cartItems.add(item);

        //TODO: Change user with proper user info and Total Price
        shoppingCart=shoppingCartService.addToCart(shoppingCart);
        if (shoppingCart!=null) {
            System.out.println("Product in Shopping cart Add Successful");

            noOfItemsInTheCart = shoppingCart.getShoppingCartItems().size();

        } else {
            System.out.println("Product add failed");
        }
    }
    /**
     * remove product
     * @param item 
     */
    public void removeProduct(ShoppingCartItem item){
        shoppingCart=shoppingCartService.removeFromCart(shoppingCart, item);
    }
    /**
     * Update the shopping cart
     * @param item 
     */
    public void updateProduct(ShoppingCartItem item){
        
        for(ShoppingCartItem cartItem:shoppingCart.getShoppingCartItems()){
            if(cartItem.getId()==item.getId()){
                 cartItem.setPrice(item.getQuantity()*item.getProduct().getPrice());
            }
        }
        shoppingCart=shoppingCartService.addToCart(shoppingCart);
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

}
