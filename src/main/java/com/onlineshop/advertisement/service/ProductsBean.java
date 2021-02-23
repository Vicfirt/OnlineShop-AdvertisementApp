package com.onlineshop.advertisement.service;


import com.onlineshop.advertisement.controller.ProductsController;
import com.onlineshop.advertisement.model.Product;


import javax.ejb.EJB;
import java.io.Serializable;
import java.util.List;

public class ProductsBean implements Serializable {

    @EJB
    private ProductsController productsController;

    public List<Product> getProducts() {
        return productsController.getProductList();
    }

    public void update() {
        productsController.updateProducts();
    }

    public String getLastMessage() {
        return productsController.getChangesMessage();
    }
}
