package com.example.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.model.Product;
import com.example.service.ProductService;

@RestController
@RequestMapping("/products")
public class Controller {

    @Autowired
    private ProductService service;

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return service.getProductsByCategory(category);
    }

    @GetMapping("/filter")
    public List<Product> getByPriceRange(@RequestParam double min, @RequestParam double max) {
        return service.getProductsByPriceRange(min, max);
    }

    @GetMapping("/sorted")
    public List<Product> getSortedProducts() {
        return service.getSortedProducts();
    }

    @GetMapping("/expensive/{price}")
    public List<Product> getExpensiveProducts(@PathVariable double price) {
        return service.getExpensiveProducts(price);
    }
}