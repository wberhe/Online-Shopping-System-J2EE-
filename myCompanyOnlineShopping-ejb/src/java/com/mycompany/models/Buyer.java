/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.models;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * This class is for Buyer user and shares all the attributes of User.
 * 
 * @author james
 * version 1.0.0
 */
@Entity
public class Buyer extends Users implements Serializable {
    private static final long serialVersionUID = 1L;
}
