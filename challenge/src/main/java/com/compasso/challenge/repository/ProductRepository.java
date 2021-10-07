package com.compasso.challenge.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.compasso.challenge.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findById(Long id);
	
	@Query("select p from Product p where " +
		       "(?1 is null or upper(p.name) like concat('%', upper(?1), '%') or upper(p.description) like concat('%', upper(?1), '%')) " +
		       "and (?2 is null or p.price >= ?2) " +
		       "and (?3 is null or p.price <= ?3) ")
	List<Product> search(String q, Double min_price, Double max_price);
	
}
