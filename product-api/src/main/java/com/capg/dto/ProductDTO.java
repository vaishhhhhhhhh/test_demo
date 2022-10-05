package com.capg.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.capg.entity.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
	private Integer pId;
	
	@NotBlank(message="Product name can't be blank or Null")
	@Size(min=5,max=30)
	private String name;
	
	@Min(100)
	@NotNull
	private Integer price;
	//private String type;
	
	@Size(min=10, max=200)
	@NotBlank(message=" Product description can't be blank or Null")
	private String description;
	
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	
	public ProductDTO(Product product) {
		this.pId=product.getPId();
		this.name=product.getName();
		this.price=product.getPrice();
		//this.type=product.getType();
		this.description=product.getDescription();
		this.createdOn=product.getCreatedOn();
		this.updatedOn=product.getUpdatedOn();
		
	}
	

}
