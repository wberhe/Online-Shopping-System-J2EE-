package com.mycompany.services;

import com.mycompany.interfaces.ShoppingCartServicesLocal;
import com.mycompany.models.ShoppingCart;
import com.mycompany.models.ShoppingCartItem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Shopping Cart service class will do all database operation for a shopping
 * cart. For example, insert the cart and cart items, update cart, remove
 * products from the cart etc.
 *
 * @author Md Mojahidul Islam
 * @version 0.0.1
 */
@Stateless
public class ShoppingCartService implements ShoppingCartServicesLocal {

    @PersistenceContext
    private EntityManager em;
    /**
     * This method adds product to shopping cart
     * 
     * @param cart
     * @return cart or null
     */

    public ShoppingCart addToCart(ShoppingCart cart) {
        
        try {
            ShoppingCart sc=em.merge(cart);
            return sc;
        } catch (Exception ex) {
            
        }
        return null;
    }
    
        public ShoppingCart removeFromCart(ShoppingCart cart,ShoppingCartItem item) {
        
        try {
             cart.getShoppingCartItems().remove(item);
             ShoppingCartItem it=em.find(ShoppingCartItem.class, item.getId());
             em.remove(it);
            // em.flush();
            return cart;
        } catch (Exception ex) {
            
        }
        return null;
    }
}
