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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * This class is for Vendor User and contains Vendor user features.
 * 
 * @author james
 * version 1.0.0
 */
@Entity
public class VendorUser extends Users implements Serializable {
    private static final long serialVersionUID = 1L;
    
//    @OneToMany(mappedBy = "vendorUsers")
    @ManyToOne
    private Vendor vendor;

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
    
}
