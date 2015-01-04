/*
 * Copyright (c)2014
 */
package com.mycompany.services;

import com.mycompany.models.CreditCard;
import com.mycompany.services.paymentgateway.PaymentGatewayService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *This class is for payment gateway listener
 * 
 * @author Va Y.
 * version 1.0.0
 */
@Stateless
@LocalBean
public class PaymentGatewayListener {
    
    @EJB
    private PaymentGatewayService client;
/**
 * This method modifies greeting message
 * @param ctx
 * @return
 * @throws Exception 
 */
    @AroundInvoke
    public Object modifyGreeting(InvocationContext ctx) throws Exception {
        Object[] parameters = ctx.getParameters();
        CreditCard param = (CreditCard) parameters[0];
        parameters[0] = param;
        ctx.setParameters(parameters);
        client.create_JSON(param);
        
        try {
            return ctx.proceed();
        } catch (Exception e) {
            return null;
        }
    }
}
