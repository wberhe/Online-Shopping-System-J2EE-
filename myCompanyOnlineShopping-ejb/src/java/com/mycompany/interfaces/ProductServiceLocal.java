/*
 * Copyright (c)2014
 */

package com.mycompany.interfaces;

import com.mycompany.models.Product;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Va Y.
 */
@Local
public interface ProductServiceLocal {
    
    public List<Product> getAll();
    
}
