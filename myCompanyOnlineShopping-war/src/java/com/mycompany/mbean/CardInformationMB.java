/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mbean;

import com.mycompany.interfaces.CardInformationServiceLocal;
import com.mycompany.models.CreditCard;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Manages the credit card information action of a particular user
 * Saves the credit card information 
 * @return card information 
 * @version 1.0.0
 * @author Md Mojahidul Islam
 * 
 */
@Named(value = "cardInformationMB")
@SessionScoped
public class CardInformationMB implements Serializable {

    @EJB
    private CardInformationServiceLocal cardInfoService;

    private CreditCard cardInfo;

    public CardInformationMB() {
        cardInfo = new CreditCard();
    }
    /**
     * Saves the card information
     * @return to the order detail home page 
     */
    public String save() {
        
        cardInfo=cardInfoService.save(cardInfo);
       
         return "orderDetail?faces-redirect=true";
    }

    public CreditCard getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CreditCard cardInfo) {
        this.cardInfo = cardInfo;
    }

}
