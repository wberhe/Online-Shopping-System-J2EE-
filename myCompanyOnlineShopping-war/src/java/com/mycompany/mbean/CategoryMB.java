
package com.mycompany.mbean;

import com.mycompany.models.Category;
import com.mycompany.services.CategoryService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Saves category
 * @version 1.0.0
 * @return a single or list of categories
 * @author TalakB
 */
@Named
@SessionScoped
public class CategoryMB implements Serializable{
    @EJB
    private CategoryService categoryService;
    private Category category = new Category();
    /**
     * constructor
     */
    public CategoryMB() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    /**
     * 
     * @return list of all categories
     * @throws Exception 
     */
    public List<Category> getAllCategories() throws Exception {
        return categoryService.findAllCategories();
        
    }
    /**
     * 
     * @param catName
     * @return category
     */
    public Category findCategoryByName(String catName){
        return categoryService.findCategoryByName(catName);
    
    }
    
}
