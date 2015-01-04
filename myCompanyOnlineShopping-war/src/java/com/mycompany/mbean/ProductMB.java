/*
 * Copyright (c)2014
 */

package com.mycompany.mbean;

import com.mycompany.models.Product;
import com.mycompany.services.ProductService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * 
 * Searching the product
 * @return product
 * @version 1.0.0
 * @author Va Y.
 */
@Named(value = "productMB")
@RequestScoped
public class ProductMB {
    
    @EJB
    private ProductService productService;
    
    private List<Product> productList;
    private Product product;

    /**
     * Creates a new instance of ProductMB
     */
    public ProductMB() {
    }
    
    @PostConstruct
    private void init(){
        productList = productService.getAll();
    }
    /**
     * 
     * @return null
     */
    public String listProduct() {
        productList = productService.getAll();
        
        
        return null;
    }
    /**
     * Searches product by ID
     * @param id
     * @return  product to product home page
     */
    public String findProduct(int id) {

        product = productService.get(id);
        
        
        return "product";
    }

    public List<Product> getProductList() {
        return productList;
    }
    /**
     * 
     * @return product
     */
    public Product getProduct() {
        return product;
    }

    
    
}
