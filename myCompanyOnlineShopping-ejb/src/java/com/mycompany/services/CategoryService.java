package com.mycompany.services;

import com.mycompany.models.Category;
import com.mycompany.models.Vendor;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * This service class is responsible for finding all the categories and categories by name 
 * 
 * @author TalakB
 * @version 1.0.0
 */
@Stateless
@LocalBean
public class CategoryService {

    @PersistenceContext
    private EntityManager em;

    /**
     * This method gives you all the product categories in the database
     * @return 
     */
    public List<Category> findAllCategories() {
        TypedQuery<Category> query = em.createNamedQuery("listAllCategories", Category.class);
        return query.getResultList();
    }
    /**
     * This method finds category name by name
     * @param name
     * @return category
     */
    
    public Category findCategoryByName(String name){
        TypedQuery<Category> query = em.createNamedQuery("findCategorByName", Category.class);
        query.setParameter("cname", name);
        return query.getSingleResult();
    
    }
}
