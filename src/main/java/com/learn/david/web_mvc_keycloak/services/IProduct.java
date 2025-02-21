package com.learn.david.web_mvc_keycloak.services;

import com.learn.david.web_mvc_keycloak.entities.Product;

import java.util.List;

public interface IProduct {
    void addProduct(Product product);
    List<Product> getProducts();
    public Product getProduct(String id);
    void updateProduct(Product newProduct);
    void deleteProduct(String id);
}