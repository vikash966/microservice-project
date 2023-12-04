package com.major.OrderService.Service;

import java.time.Instant;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.major.OrderService.Entity.Order;
import com.major.OrderService.Model.OrderRequest;
//import com.major.OrderService.Model.OrderResponse;
import com.major.OrderService.Model.OrderResponse2;
import com.major.OrderService.Model.PaymentRequest;
import com.major.OrderService.Model.PaymentResponse;
import com.major.OrderService.Model.ProductResponse;
import com.major.OrderService.Repository.OrderRepository;
import com.major.OrderService.external.client.PaymentService;
import com.major.OrderService.external.client.ProductService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService; 
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//api
	public long placeOrder(OrderRequest orderRequest) {
		// TODO Auto-generated method stub
		log.info("Placing order request :{}",orderRequest);
		
		
		productService.reduceQuantity(orderRequest.getProductid(),orderRequest.getQuantity());
		
		log.info("CREATING ORDER WITH STATUS CREATED ");
		
		 Order order = Order.builder()
	                .Amount(orderRequest.getTotalAmount())
	                .orderStatus("CREATED")
	                .productid(orderRequest.getProductid())
	                .orderDate(Instant.now())
	                .quantity(orderRequest.getQuantity())
	                .build();
		
		
		order=orderRepository.save(order);
		
		log.info("CALLING PAYMENT SERVICE TO COMPLETE THE PAYMENT ......................................");
		
		PaymentRequest paymentRequest=PaymentRequest.builder()				
				.orderid(order.getId())
				.paymentMode(orderRequest.getPayementMode())
				.amount(orderRequest.getTotalAmount())

				.build();
		String orderStatus=null;
		try {
			paymentService.doPayment(paymentRequest);
			log.info("PAYMENT DONE SUCCESSFULLY");
			orderStatus="PLACED";
			
		}catch(Exception e)
		{
			log.info("PAYMENT FAILED .....................................");
			orderStatus="PAYMENT FAILED";
		}
		
		order.setOrderStatus(orderStatus);
		orderRepository.save(order);
	

		
		log.info("ORDER PLACED SUCCESSFULLY WITH ORDER ID : {}",order.getId());
		
		
		return order.getId();
	}

	@Override
	public OrderResponse2 getOrderDetails(long orderId) {
		// TODO Auto-generated method stub
		log.info("GET ORDER DETAILS FOR ORDER ID :{}",orderId);
		Order order=orderRepository.findById(orderId)
				
			.orElseThrow(()-> new RuntimeException("Order Not found"));
		
		
		log.info("INVOKING PRODUCT SERVICE TO FETCH PRODUCT WITH PRODUCT ID :{}" ,order.getProductid());
		ProductResponse productResponse =restTemplate.getForObject("http://PRODUCT-SERVICE/product/"+order.getProductid(),
				ProductResponse.class );
		
		
		log.info("GETTING PAYMENT INFORMATION FROM PAYMENT SERVICE {}");
		
		PaymentResponse paymentResponse =restTemplate.getForObject("http://PAYMENT-SERVICE/payment/order/" + order.getId(), PaymentResponse.class);
		
		OrderResponse2.ProductDetails productDetails=OrderResponse2.ProductDetails
				.builder()
				.productName(productResponse.getProductName())
				.productId(productResponse.getProductId())
			
				.build();
		
		
		OrderResponse2.PaymentResponse paymentResponse2 =OrderResponse2.PaymentResponse
				.builder()
				.paymentid(paymentResponse.getPaymentid())
				.status(paymentResponse.getStatus())
				.amount(paymentResponse.getAmount())
				.paymentDate(paymentResponse.getPaymentDate())
				.paymentMode(paymentResponse.getPaymentMode())
				.orderId(paymentResponse.getOrderId())
				
				
				.build();
		
		OrderResponse2 orderResponse=OrderResponse2.builder()
			
				.orderId(order.getId())
				.orderStatus(order.getOrderStatus())
				.amount(order.getAmount())
				.orderDate(order.getOrderDate())
				.productDetails(productDetails)
				.paymentResponse(paymentResponse2)
				
			
				.build();
		
		return orderResponse;
	}
	
	

	       

	      

	 

}
