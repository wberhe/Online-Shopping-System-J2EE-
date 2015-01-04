/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * This class is for product category and contains category features.
 * 
 * @author james
 * version 1.0.0
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "listAllCategories", query = "SELECT c FROM Category c"),
    //findCategorByName
    @NamedQuery(name = "findCategorByName", query = "SELECT c FROM Category c WHERE c.categoryName = :cname")
})
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String categoryName;
    
    private String description;

    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
