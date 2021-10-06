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
    

}
