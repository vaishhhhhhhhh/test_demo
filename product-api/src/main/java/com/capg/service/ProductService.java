package com.capg.service;

import java.util.List;

import com.capg.dto.ProductDTO;

public interface ProductService {
	List<ProductDTO> getProducts();
	ProductDTO getProduct(Integer productId);
	ProductDTO createProduct(ProductDTO product);
	ProductDTO updateProduct(Integer productId, ProductDTO productDTO );
	ProductDTO partialupdateProduct(Integer productId, String name );
	void deleteProduct(Integer productId);
	void deleteAll();
	List<ProductDTO> searchProducts(String query);
	

}