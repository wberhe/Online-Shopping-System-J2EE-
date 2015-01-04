/*
 * Copyright (c)2014
 */

package com.mycompany.mbean;

import com.mycompany.services.myfiance.MyFinanceService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @optional
 * Version 1.0.0
 * @author Va Y.
 */
@Named(value = "myFinancedotcomMB")
@RequestScoped
public class MyFinancedotcomMB {

    @EJB
    private MyFinanceService client;
    
    public MyFinancedotcomMB() {
    }
    
    
    
    
}
