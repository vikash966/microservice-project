package com.major.ProductService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductRequest {
	
	private String name;
	
	private long price;
	
	private long quantity;

}
