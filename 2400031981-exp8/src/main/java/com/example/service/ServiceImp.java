package com.example.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Product;
import com.example.repo.Repo;

@Service
public class ServiceImp implements ProductService {

    @Autowired
    private Repo repository;

    @Override
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return repository.findByCategory(category);
    }

    @Override
    public List<Product> getProductsByPriceRange(double min, double max) {
        return repository.findByPriceBetween(min, max);
    }

    @Override
    public List<Product> getSortedProducts() {
        return repository.getProductsSortedByPrice();
    }

    @Override
    public List<Product> getExpensiveProducts(double price) {
        return repository.getExpensiveProducts(price);
    }
}