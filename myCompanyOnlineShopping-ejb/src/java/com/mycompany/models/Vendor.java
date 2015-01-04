/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * This class is for Vendor and contains Vendor features.
 * 
 * @author james
 * version 1.0.0
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "listAllVendors", query = "SELECT v FROM Vendor v"),
    @NamedQuery(name = "listNonApprovedVendors", query = "SELECT v FROM Vendor v WHERE v.approved='FALSE'"),
    @NamedQuery(name = "listVendorUsers", query = "SELECT u FROM VendorUser u where u.vendor.id = :vid")      
})
public class Vendor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String vendorName;
    
    private String phone;
    
    private String website;

    @Column(unique = true)
    private String email;
    
    private boolean approved;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VendorUser> vendorUsers;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

    public Vendor() {
    }

    public Vendor(String vendorName) {
        this.vendorName = vendorName;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<VendorUser> getVendorUsers() {
        return vendorUsers;
    }

    public void addVendorUser(VendorUser vendorUser) {
        this.vendorUsers.add(vendorUser);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
}
