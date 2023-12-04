package com.major.ProductService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.major.ProductService.model.ProductRequest;
import com.major.ProductService.model.ProductResponse;
import com.major.ProductService.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
		
		Long productid=productService.addproduct(productRequest);
		
		return new ResponseEntity<>(productid,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse>getProductId(@PathVariable("id") long productId)  
	{
		ProductResponse productResponse =productService.getProductById(productId);
		
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
		
	}
	
	@PutMapping("/reduceQuantity/{id}")
	public ResponseEntity<Void>reduceQuantity( @PathVariable("id") long productId,@RequestParam long quantity)
	{
		
		productService.reduceQuantity(productId,quantity);
		
		
		return new ResponseEntity<>(HttpStatus.OK);
		
		
	}

	
	
	
}
