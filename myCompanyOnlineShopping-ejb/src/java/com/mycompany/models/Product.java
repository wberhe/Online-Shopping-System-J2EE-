/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.models;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * This class is for Product and contains Product features.
 * 
 * @author james
 * version 1.0.0
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Product.list", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.count", query = "SELECT COUNT(p) FROM Product p"),
    @NamedQuery(name = "findProductByName", query = "SELECT p FROM Product p WHERE p.name LIKE :pname"),})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    
    private double price;
    
    private String imageURL;
    
    private String description;
    
    private int quantity;
    
    //@OneToMany(mappedBy = "products")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Vendor vendor;
    
    //@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ManyToOne(cascade = CascadeType.MERGE)
    private Category category;
    
    public Product() {
        
    }
    
    public Product(String name, double price) {
        
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
