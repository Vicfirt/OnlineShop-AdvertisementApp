package com.onlineshop.advertisement.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineshop.advertisement.model.Product;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

public class Loader {

    private static Loader loader;

    private  ObjectMapper objectMapper = new ObjectMapper();

    private static  Logger LOGGER = LoggerFactory.getLogger(Loader.class);

    private  Client client = new Client();

    public synchronized static Loader getInstance() {
        if (loader == null)
            loader = new Loader();
        return loader;
    }

    public List<Product> getProducts() {
        String response = getResultResponse("http://localhost:8080/product/top");
        List<Product> products = null;
        try {
            products = objectMapper.readValue(response, new TypeReference<List<Product>>() {
            });
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return products;
    }

    private String getResultResponse(String url) {
        WebResource webResource = client.resource(url);
        return webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class).getEntity(String.class);
    }
}
