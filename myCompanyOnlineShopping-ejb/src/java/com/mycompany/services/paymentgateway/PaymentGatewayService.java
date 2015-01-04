/*
 * Copyright (c)2014
 */

package com.mycompany.services.paymentgateway;

import javax.ejb.Stateless;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:CreditcardFacadeREST
 * [entities.creditcard]<br>
 * USAGE:
 * <pre>
 *        @EJB
 *        PaymentGatewayService client = new PaymentGatewayService();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 * </pre>
 *
 * @author weldu
 * version 1.0.0
 */
@Stateless
public class PaymentGatewayService {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/PaymentGateway/webresources";

    public PaymentGatewayService() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("entities.creditcard");
    }
/**
 * This method creates dummy credit card and credit card account
 * @param requestEntity
 * @throws ClientErrorException 
 */
    public void create_JSON(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }
/** 
 * This method validates credit card info.
 * @param <T>
 * @param responseType
 * @param cardnumber
 * @param holdername
 * @param expireddate
 * @param securecode
 * @param amount
 * @return
 * @throws ClientErrorException 
 */

    public <T> T validate(Class<T> responseType, String cardnumber, String holdername, String expireddate, String securecode, String amount) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}/{2}/{3}/{4}", new Object[]{cardnumber, holdername, expireddate, securecode, amount}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
    }
    /**
     * This method withdraw money from credit card account
     * @param <T>
     * @param responseType
     * @param cardnumber
     * @param holdername
     * @param expireddate
     * @param securecode
     * @param amount
     * @param withdraw
     * @return
     * @throws ClientErrorException 
     */
    public <T> T withdraw(Class<T> responseType, String cardnumber, String holdername, String expireddate, String securecode, String amount, String withdraw) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{cardnumber, holdername, expireddate, securecode, amount, withdraw}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
    }
/**
 * This method closes client connection
 */
    public void close() {
        client.close();
    }
    
}
