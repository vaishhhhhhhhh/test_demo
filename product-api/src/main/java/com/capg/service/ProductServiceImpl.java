package com.capg.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.dto.ProductDTO;
import com.capg.entity.Product;
import com.capg.exception.ProductNotFoundException;
import com.capg.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductDTO> getProducts() {
		List<Product>products= productRepository.findAll();
		return products.stream().map(ProductDTO::new).collect(Collectors.toList());
		}

	@Override
	public ProductDTO getProduct(Integer productId) {
		Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product does not exist with given Id: "));
		return new ProductDTO(product);
	}

	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		Product product = new Product(productDTO);
		return new ProductDTO (productRepository.save(product));
	}

	@Override
	//@Transactional
	public ProductDTO updateProduct(Integer productId, ProductDTO productDTO) {
		Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product does not exist with given Id: "));
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setUpdatedOn(productDTO.getUpdatedOn());
		
		return new ProductDTO(product);
	}

	@Override
	public void deleteProduct(Integer productId) {
		Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product does not exist with given Id: "));
		productRepository.delete(product);
	}

	@Override
	public void deleteAll() {
		productRepository.deleteAll();
		
		
	}

	@Override
	public List<ProductDTO> searchProducts(String query) {
		List<Product>products= productRepository.searchProducts(query);
		return products.stream().map(ProductDTO::new).collect(Collectors.toList());
	}

	@Override
	public ProductDTO partialupdateProduct(Integer productId, String name) {
		Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product does not exist with given Id: "));
		product.setName(name);
		//product.setPrice(productDTO.getPrice());
		//product.setUpdatedOn(productDTO.getUpdatedOn());
		
		return new ProductDTO(product);
		
	}

	

}