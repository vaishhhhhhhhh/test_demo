package com.capg.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capg.dto.ProductDTO;
import com.capg.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/getall")
	public List<ProductDTO> products(){
		return productService.getProducts();
	}
	
	@GetMapping("/search")
	public List<ProductDTO> searchProduct(@RequestParam("query") String query){
		return productService.searchProducts(query);
	}
	
	@GetMapping("/get/{productId}")
	public ProductDTO proudctById(@PathVariable Integer productId){
		return productService.getProduct(productId);
	}
	
	@PostMapping("/save")
	public ResponseEntity<ProductDTO> save(@Valid @RequestBody ProductDTO prod) {
		return new ResponseEntity<ProductDTO>(productService.createProduct(prod),HttpStatus.CREATED);
	}
	
	
	
	@Transactional
	@PutMapping("/update/{productId}")
	public ResponseEntity<ProductDTO> update(@PathVariable Integer productId, @RequestBody ProductDTO prod) {
		return new ResponseEntity<ProductDTO>(productService.updateProduct(productId, prod),HttpStatus.ACCEPTED);
	}
	
	@Transactional
	@PatchMapping("/partialUpdate/{productId}/{name}")
	public ResponseEntity<ProductDTO> partialUpdate(@PathVariable Integer productId, @PathVariable String name) {
		return new ResponseEntity<ProductDTO>(productService.partialupdateProduct(productId, name),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{productId}")
	public String delete(@PathVariable Integer productId) {
		productService.deleteProduct(productId);
		return "Product with ID " + productId + " was deleted successfully";
	}
	
	@DeleteMapping("/deleteAll")
	public String deleteAll() {
		productService.deleteAll();
		return "All products deleted successfully";
	}
}