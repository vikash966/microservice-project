package com.major.ProductService.service;

import com.major.ProductService.model.ProductRequest;
import com.major.ProductService.model.ProductResponse;

public interface ProductService {

	Long addproduct(ProductRequest productRequest);

	ProductResponse getProductById(long productId);

	void reduceQuantity(long productId, long quantity);




}
