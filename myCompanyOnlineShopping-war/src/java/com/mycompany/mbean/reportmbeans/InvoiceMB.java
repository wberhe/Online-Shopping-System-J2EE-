/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mbean.reportmbeans;

import com.mycompany.interfaces.PurchaseOrderServiceLocal;
import com.mycompany.models.PurchaseOrder;
import com.mycompany.models.ShoppingCartItem;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author shahin
 */
@Named(value = "invoiceMB")
@SessionScoped
public class InvoiceMB implements Serializable {

    @EJB
    private PurchaseOrderServiceLocal purchaseOrderService;

    private PurchaseOrder order;

    public InvoiceMB() {
    }

//    public void init(){
//        JRBeanCollectionDataSource beanCollectionSource=new JRBeanCollectionDataSource(order.getShoppingCart().getShoppingCartItems());
//        JasperFillManager.fillReport(null, null)
//    }
//    
//    
    public void generateInvoicePDF(int orderId) throws JRException, IOException {

        order = purchaseOrderService.findById(orderId);

        // EntityManager em=PerisitenceManager.getEntityManager();
        // Query query= em.createQuery("select s from ShoppingCart s");
        //List<shoppingcart> listOfShoppingCart=(List<shoppingcart>)query.getResultList();
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("invoiceNumber", String.valueOf(order.getId()));
        param.put("customerName", order.getUser().getFirstName() + order.getUser().getLastName());

        List<ShoppingCartItem> items = order.getShoppingCart().getShoppingCartItems();
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(items);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/jsfpages/reports/invoice.jasper");
        JasperPrint jasperPrint;
        jasperPrint = JasperFillManager.fillReport(reportPath, param, beanCollectionDataSource);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

}
