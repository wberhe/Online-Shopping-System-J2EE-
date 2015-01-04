/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * This class is for Users and contains user features.
 * 
 * @author james
 * version 1.0.0
 */
@Entity
@NamedQueries({
     @NamedQuery(name = "checkUser", query = "select u from Users u where u.email = :uname and u.password= :upass"),
     @NamedQuery(name = "findUserByEmailId", query = "select u from Users u where u.email = :uemail")

})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String password;
    
    private String phone;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    private Role role;
    
    @OneToOne
    private BillingAddress billingAddress;
    
    @OneToOne
    private ShippingAddress shippingAddress;
    
    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PurchaseOrder> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public List<PurchaseOrder> getOrders() {
        return orders;
    }

    public void addOrder(PurchaseOrder order) {
        this.orders.add(order);
    }
    
}
