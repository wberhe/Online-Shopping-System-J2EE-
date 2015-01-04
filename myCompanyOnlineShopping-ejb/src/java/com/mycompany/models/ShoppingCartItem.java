/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.models;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * This class is for Shopping Cart item and contains Shopping Cart item features.
 * 
 * @author james
 * version 1.0.0
 */
@Entity
public class ShoppingCartItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Product product;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private ShoppingCart shoppingCart;
    
    private int quantity;
    
    private double price;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(Product product, ShoppingCart shoppingCart, int quantity, double price) {
        this.product = product;
        this.shoppingCart = shoppingCart;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    
}
