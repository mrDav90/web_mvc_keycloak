package com.learn.david.web_mvc_keycloak.services;

import com.learn.david.web_mvc_keycloak.entities.Product;
import com.learn.david.web_mvc_keycloak.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductImpl implements IProduct {
    private final ProductRepository productRepository;

    public void addProduct(Product product) {
        Product newProduct = new Product();
        newProduct.setRef(UUID.randomUUID().toString());
        newProduct.setName(product.getName());
        productRepository.save(newProduct);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public void updateProduct(Product newProduct) {
        var product = productRepository.findById(newProduct.getRef());
        product.map(p->{
            p.setName(newProduct.getName());
            return productRepository.save(p);
        })
        ;
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

}