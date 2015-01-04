/*
 * Copyright (c)2014
 */

package com.mycompany.services.myfiance;

import javax.ejb.Stateless;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:OrderTransactionFacadeREST
 * [entities.ordertransaction]<br>
 * USAGE:
 * <pre>
 *        @EJB
 *        MyFinanceService client;
 *        Object response = client.XXX(...);
 *        // do whatever with response
 * </pre>
 *
 * @author weldu
 * version 1.0.0
 */
@Stateless
public class MyFinanceService {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/MyFinance.com/webresources";

    public MyFinanceService() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("entities.ordertransaction");
    }
/**
 * This method creates order transaction 
 * 
 * @param requestEntity
 * @throws ClientErrorException 
 */
    public void create_JSON(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

   /**
    * This method closes the client connection
    * 
    */ 

    public void close() {
        client.close();
    }
    
}
