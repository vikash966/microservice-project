package com.major.OrderService.Controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.major.OrderService.Model.OrderRequest;
//import com.major.OrderService.Model.OrderResponse;
import com.major.OrderService.Model.OrderResponse2;
import com.major.OrderService.Service.OrderService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	@PostMapping("/placeorder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {
        long orderId = orderService.placeOrder(orderRequest);
        log.info("Order Id: {}", orderId);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponse2> getOrderDetails(@PathVariable long orderId)
	{
		OrderResponse2 orderResponse =orderService.getOrderDetails(orderId);
		
		return new ResponseEntity<>(orderResponse,HttpStatus.OK);
		
	}
}


