/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mbean;

import com.mycompany.models.Address;
import com.mycompany.models.BillingAddress;
import com.mycompany.models.ShippingAddress;
import com.mycompany.services.AddressService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
/**
 * Manages the user action related to address
 * Version 1.0.0
 * @return  the shipping and billing addresses of the user
 * 
 */
@Named(value = "addressMB")
@SessionScoped
public class AddressMB implements Serializable {

    @EJB
    private AddressService addressService;

    private BillingAddress billingAddress;
    
    private ShippingAddress shippingAddress;

    public AddressMB() {
        billingAddress=new BillingAddress();
        shippingAddress=new ShippingAddress();
    }

   

    public String save() {        
        if (addressService.save(billingAddress) && addressService.save(shippingAddress)) {
            return "card?faces-redirect=true";
        }
        return "fail?faces-redirect=true";
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
    
    
}
