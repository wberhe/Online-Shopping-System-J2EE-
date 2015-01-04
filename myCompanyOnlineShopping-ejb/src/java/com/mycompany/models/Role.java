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
 * This class is for Role of Users and contains Credit Card features.
 * 
 * 
 * @author james
 * version 1.0.0
 */

@Entity
@NamedQueries({
     @NamedQuery(name = "findRoleByUserCode", query = "select r from Role r where r.userCode = :rcode")
})
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final int ROLE_ADMIN = 1;
    public static final int ROLE_INTERNAL_USER = 2;
    public static final int ROLE_VENDOR_USER = 3;
    public static final int ROLE_BUYER = 4;
    public static final int ROLE_GUEST = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int userCode;

    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
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

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }
    
    
}
