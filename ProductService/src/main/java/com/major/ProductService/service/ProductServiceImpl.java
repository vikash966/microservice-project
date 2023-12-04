package com.major.ProductService.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.major.ProductService.Repository.ProductRepository;
import com.major.ProductService.entity.ProductEntity;
import com.major.ProductService.model.ProductRequest;
import com.major.ProductService.model.ProductResponse;

import lombok.extern.log4j.Log4j2;



@Service
@Log4j2
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productrepository;

	public Long addproduct(ProductRequest productRequest) {
		// TODO Auto-generated method stub
		log.info("ADDING PRODUCT");
		ProductEntity product=ProductEntity.builder().productName(productRequest.getName()).quantity(productRequest.getQuantity())
				.price(productRequest.getPrice()).build();
		
		productrepository.save(product);
		log.info("PRODUCT CREATED ");
		return product.getProductid();
	}

	@Override
	public ProductResponse getProductById(long productId) {
		// TODO Auto-generated method stub
		log.info("Get the product for product id :{}",productId);
		
		ProductEntity product=productrepository.findById(productId).orElseThrow(()-> new RuntimeException("Product not found "));
		
		ProductResponse productResponse=new ProductResponse();
		
		BeanUtils.copyProperties(product, productResponse);
		
		return productResponse;
		
	}

	@Override
	public void reduceQuantity(long productId, long quantity) {
		// TODO Auto-generated method stub
		
		log.info("Reduce Quantity ..... . ");
		ProductEntity product=productrepository.findById(productId).orElseThrow(()-> new RuntimeException("Product with given Id  not found "));
		
		
		if(product.getQuantity()<quantity)
		{
			throw new RuntimeException("INSUFFICIENT QUANTITY");
		}
		
		product.setQuantity(product.getQuantity() - quantity);
		productrepository.save(product);
		
		
		log.info("PRODUCT QUANTITY UPDATE SUCCESSFULLT ....... ");
		
	}
	
	
	

}
