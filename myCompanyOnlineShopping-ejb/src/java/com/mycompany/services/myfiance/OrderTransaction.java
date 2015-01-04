/*
 * Copyright (c)2014
 */

package com.mycompany.services.myfiance;

import java.util.Date;

/**
 * This class is for Order Transaction and contains Order Transcation features.
 * 
 * @author weldu
 * version 1.0.0
 */

public class OrderTransaction {
    
    private Integer id;
    private Integer orderID;
    private Date transactionDate;

    public OrderTransaction() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    
}
