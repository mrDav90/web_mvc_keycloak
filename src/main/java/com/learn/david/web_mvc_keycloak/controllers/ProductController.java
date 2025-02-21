package com.learn.david.web_mvc_keycloak.controllers;


import com.learn.david.web_mvc_keycloak.entities.Product;
import com.learn.david.web_mvc_keycloak.services.IProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor

public class ProductController {
    private final IProduct productService;

    @GetMapping
    public String getProducts(ModelMap model) {
        var list = productService.getProducts();
        model.addAttribute("products", list);
        model.addAttribute("product", new Product());
        model.addAttribute("operation", "add");
        return "products";
    }

    @PostMapping
    public String addProduct(ModelMap model, @ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String getProductToEdit(@PathVariable String id, ModelMap model) {
        model.addAttribute("product", productService.getProduct(id));
        model.addAttribute("operation", "update");
        return "redirect:/products";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable String id, ModelMap model) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id, ModelMap model) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}