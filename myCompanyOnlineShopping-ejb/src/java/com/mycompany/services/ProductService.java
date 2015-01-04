/*
 * Copyright (c)2014
 */

package com.mycompany.services;

import com.mycompany.interfaces.ProductServiceLocal;
import com.mycompany.models.Category;
import com.mycompany.models.Product;
import com.mycompany.models.Vendor;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * This service class is responsible for CRUD operations on product entity .
 * 
 * @author Va yim
 * @version 1.0.0
 */
@Stateless
public class ProductService {

    @PersistenceContext
    private EntityManager em;
    
    public ProductService(){
        
    }
    /**
     * This method gives you all the products in the database
     * @return 
     */

    public List<Product> getAll() {
        
       TypedQuery<Product> query = em.createNamedQuery("Product.list", Product.class);
        return query.getResultList();
    
    }
    /**
     * This method gives you product info. by id.
     * 
     * @param id
     * @return 
     */

    public Product get(int id) {
        
        return em.find(Product.class, id);
    }
    
    /**
     * This method saves product info. into database.
     * @param product
     * @return saved
     */
    public boolean saveProduct(Product product) {
        boolean saved = false;
        try {
            em.persist(product);
            saved = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saved;
    }
   /**
    * This method gives you all the products in the database.
    * 
    * @return 
    */
    public List<Product> findAll() {
        TypedQuery<Product> q = em.createNamedQuery("Product.list", Product.class);
        return q.getResultList();
    }
    /**
     * This method deletes product from database
     * 
     * @param id 
     */

    public void delete(int id) {
        Product p = em.find(Product.class, id);
        em.remove(p);
    }
    
    /**
     * This method finds product into database
     * 
     * @param id
     * @return product 
     */
    public Product find(int id) {
        return em.find(Product.class, id);
    }
    
    /**
     * This method search product by name
     * 
     * @param pname
     * @return searchResult 
     */
    public List<Product> searchByProductName(String pname){
        TypedQuery<Product> q = em.createNamedQuery("findProductByName", Product.class);
        String likeParam = "%" + pname + "%";
        q.setParameter("pname", likeParam);
        return q.getResultList();
    
    }
    
    
}
