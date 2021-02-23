package com.onlineshop.advertisement.controller;


import com.onlineshop.advertisement.dataManager.DataManager;
import com.onlineshop.advertisement.listener.Listener;
import com.onlineshop.advertisement.loader.Loader;
import com.onlineshop.advertisement.model.Product;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Singleton
public class ProductsController {

    private  DataManager dataManager = DataManager.getInstance();

    private  Listener listener = new Listener();

    private  Loader loader = Loader.getInstance();

    private String changesMessage = "Product are up to date";

    private List<Product> productList = loader.getProducts();

    @PostConstruct
    private void init() throws IOException, TimeoutException {
        listener.start();
    }

    @PreDestroy
    private void destroy() throws IOException, TimeoutException {
        listener.stop();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void updateProducts() {
        if (dataManager.getChangeStatus()) {
            changesMessage = dataManager.getChangesMessage();
            dataManager.resetStatus();
            productList = dataManager.getProducts();
        }
    }

    public String getChangesMessage() {
        return changesMessage;
    }
}
