package com.compasso.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compasso.challenge.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
