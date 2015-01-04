/*
 * Copyright (c)2014
 */

package com.mycompany.mbean;

import com.mycompany.services.paymentgateway.PaymentGatewayService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * version 1.0.0
 * @author Va Y.
 */
@Named(value = "paymentGatewayMB")
@RequestScoped
public class PaymentGatewayMB {

    @EJB
    private PaymentGatewayService client;
    
    public PaymentGatewayMB() {
    }
    
    public String validateAction() {
        
        
        return null;
    }
    
    
}
