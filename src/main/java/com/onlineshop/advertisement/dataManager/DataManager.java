package com.onlineshop.advertisement.dataManager;

import com.onlineshop.advertisement.loader.Loader;
import com.onlineshop.advertisement.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DataManager {

    private boolean isChanged = false;

    private static DataManager dataManager;

    private  Loader loader = Loader.getInstance();

    public List<Product> products = loader.getProducts();

    private static  Logger LOGGER = LoggerFactory.getLogger(DataManager.class);

    private  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

    private String changesMessage = "";

    public static DataManager getInstance() {
        if (dataManager == null)
            dataManager = new DataManager();
        return dataManager;
    }

    private void updateTop() {
        products = loader.getProducts();
        LOGGER.info("Products top has been updated!");
    }


    public void changeState(String message) throws IOException {
        if (message.contains("Update")) {
            updateTop();
            changesMessage = "Products top has been last updated: " + formatter.format(new Date(System.currentTimeMillis()));
            setChanged();
        }
    }

    public void setChanged() {
        isChanged = true;
    }

    public boolean getChangeStatus() {
        return isChanged;
    }

    public void resetStatus() {
        isChanged = false;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getChangesMessage() {
        return changesMessage;
    }
}
