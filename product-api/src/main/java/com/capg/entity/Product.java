package com.capg.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.capg.dto.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_table")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_Id")
	private Integer pId;
	@Column(name = "product_name")
	private String name;
	@Column(name = "product_price")
	private Integer price;
	/*@Column(name = "type")
	private String type;*/
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "created_date")
	@CreatedDate
	private LocalDateTime createdOn;
	
	@Column(name = "updated_on")
	@UpdateTimestamp
	private LocalDateTime updatedOn;
	
	
	public Product(ProductDTO productDTO) {
		this.pId = productDTO.getPId();
		this.name = productDTO.getName();
		this.price = productDTO.getPrice();
		//this.type = productDTO.getType();
		this.description =productDTO.getDescription();
		this.createdOn = productDTO.getCreatedOn();
		this.updatedOn = productDTO.getUpdatedOn();		
	}
	
	@PrePersist
	public void created_on() {
		createdOn=LocalDateTime.now();
	}
	
	@PreUpdate
	public void updated_on() {
		updatedOn = LocalDateTime.now();
	}

}
