package com.compasso.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compasso.challenge.model.Product;
import com.compasso.challenge.repository.ProductRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductService {
	
	Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;
    
    public List<Product> getAll() {
        return productRepository.findAll();
    }
    
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }
    
    public List<Product> search(String q,Double min_price,Double max_price) {
        return productRepository.search(q,min_price,max_price);
    }
    
    public Product create(Product product) {
        return productRepository.save(product);
    }
    
    public Product update(Product product) {
        return productRepository.save(product);
    }
    
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
    

}
