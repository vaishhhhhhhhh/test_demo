package com.capg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capg.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query("SELECT p FROM Product p WHERE "+
	       "p.name LIKE CONCAT ('%', :query, '%')"+
			"OR p.description LIKE CONCAT('%', :query, '%')")
	List<Product> searchProducts(String query);

}
