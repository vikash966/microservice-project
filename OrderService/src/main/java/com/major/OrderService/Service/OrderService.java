package com.major.OrderService.Service;

import com.major.OrderService.Model.OrderRequest;
import com.major.OrderService.Model.OrderResponse;
import com.major.OrderService.Model.OrderResponse2;

public interface OrderService {


	long placeOrder(OrderRequest orderRequest);

	OrderResponse2 getOrderDetails(long orderId);

	

}
