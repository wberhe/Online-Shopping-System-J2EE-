package com.mycompany.mbean;

import com.mycompany.models.Category;
import com.mycompany.models.Product;
import com.mycompany.models.Role;
import com.mycompany.models.Users;
import com.mycompany.models.Vendor;
import com.mycompany.models.VendorUser;
import com.mycompany.services.ProductService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 * A bean (CDI) for product related tasks.
 * @author Va Yim
 * @version 1.0.0
 * @return 
 */
@Named
@SessionScoped
public class ProductBean implements Serializable {

    @EJB
    private ProductService productService;
    private Product product = null;
    private List<Product> products = null;

    private Vendor vendor = new Vendor();
    private VendorUser user = new VendorUser();
    private Category category = new Category();
    private String categoryName;
    private String searchKey;

    @Inject
    UserBean usersmbean;

    @Inject
    CategoryMB categorymb;

    public ProductBean() {
        product = new Product();
        products = new ArrayList<>();
        vendor = new Vendor();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    @PostConstruct
    private void init(){
        products = productService.getAll();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * 
     * Add the product
     * @return user home page
     * @throws Exception 
     */
    public String addProduct() throws Exception {
        VendorUser user = (VendorUser) usersmbean.getLoggedInUser();
        vendor = user.getVendor();
        category = categorymb.findCategoryByName(categoryName);
        product.setCategory(category);
        product.setVendor(vendor);
        productService.saveProduct(product);
        return "user_home";
    }
    /**
     * Finds all products
     * @return to the product list home page
     * @throws Exception 
     */
    public String listProduct() throws Exception {
        products = new ArrayList<>();
        List<Product> result = productService.findAll();
        
        Users loggedInUser = this.getLoggedInUser();
        if(loggedInUser!=null && loggedInUser.getRole().getId()==Role.ROLE_VENDOR_USER) {
            for(Product prod: result) {
                products.add(prod);
            }
        } else {
            products = result;
        }
        return "index";
    }

    public String addProductPage() {
        return "product_add";
    }
    /**
     * Updates the value 
     * @param v 
     */
    public void valueChanged(ValueChangeEvent v) {
        categoryName = (String) v.getNewValue();
    }
    /**
     * Displays the detailed product information
     * @param productIn
     * @return  to product detail home page
     */
    public String viewProductDetail(Product productIn) {
        product = productIn;
        if (product != null) {
            return "product_detail";
        } else {
            return null;
        }
    }

    /**
     * 
     * add product to shipping cart
     * @param product
     * @return to shopping cart information home page
     */
    public String addToCart(Product product) {
        //set all shipping cart related info here
       
        return "shoppingCartInfo";
    }

    /**
     * Search product by name
     * @return to user home page
     */
    public String searchProduct() {
        products = new ArrayList<>();
        List<Product> result = productService.searchByProductName(searchKey);
        
        Users loggedInUser = this.getLoggedInUser();
        if(loggedInUser!=null && loggedInUser.getRole().getId()==Role.ROLE_VENDOR_USER) {
            for(Product prod: result) {
                products.add(prod);
            }
        } else {
            products = result;
        }
        return "index";
    }
    /**
     * 
     * Searches the product by category 
     * @param categoryId
     * @return similar products to the user home page
     */
    public String searchCategory(int categoryId) {
        products = new ArrayList<>();
        List<Product> result = productService.findAll();
        
        List<Product> tempProducts = new ArrayList<>();
        
        Users loggedInUser = this.getLoggedInUser();
        if(loggedInUser!=null && loggedInUser.getRole().getId()==Role.ROLE_VENDOR_USER) {
            for(Product prod: result) {
                tempProducts.add(prod);
            }
        } else {
            tempProducts = result;
        }
        
        if(categoryId==0) {
            products = tempProducts;
        } else {
            for(Product prod: tempProducts) {
                if(prod !=null && prod.getCategory()!=null){
                    if(prod.getCategory().getId()==categoryId){
                        products.add(prod);
                    }
                }
            }
        }
        return "index";
    }
    
    public Users getLoggedInUser() {
        HttpSession activeSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        return (Users) activeSession.getAttribute("loggedUser");
    }

}
