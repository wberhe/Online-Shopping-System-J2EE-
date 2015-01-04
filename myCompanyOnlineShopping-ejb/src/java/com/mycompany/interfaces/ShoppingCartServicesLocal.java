/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.interfaces;

import com.mycompany.models.ShoppingCart;
import com.mycompany.models.ShoppingCartItem;
import javax.ejb.Local;

/**
 *
 * @author shahin
 */
@Local
public interface ShoppingCartServicesLocal {
    public ShoppingCart addToCart(ShoppingCart cart);
     public ShoppingCart removeFromCart(ShoppingCart cart,ShoppingCartItem item);
}
