/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.models;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 * This class is for Product Order and contains Product Order features.
 * 
 * @author james
 * version 1.0.0
 */
@Entity
public class PurchaseOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar buyingDate;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BillingAddress billingAddress;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ShippingAddress shippingAddress;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ShoppingCart shoppingCart;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CreditCard cardInformation;
    
    //@OneToMany(mappedBy = "orders")
    @ManyToOne
    private Users user;

    public PurchaseOrder() {
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   

    public Calendar getBuyingDate() {
        return buyingDate;
    }

    public void setBuyingDate(Calendar buyingDate) {
        this.buyingDate = buyingDate;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public CreditCard getCardInformation() {
        return cardInformation;
    }

    public void setCardInformation(CreditCard cardInformation) {
        this.cardInformation = cardInformation;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
