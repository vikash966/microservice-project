package com.major.OrderService.Model;

import java.time.Instant;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse2 {
	 private long orderId;
	    private Instant orderDate;
	    private String orderStatus;
	    private long amount;
	    private ProductDetails productDetails;
	    private PaymentResponse paymentResponse;
	    
	    @Data
	    @Builder
	    @NoArgsConstructor
	    @AllArgsConstructor
	    public static class ProductDetails {
	    	
	    	
	    	private String productName;	
	    	private long productId;
	    	private long quantity;
	    	private long price;

	    }
	    
	    @Data
	    @Builder
	    @AllArgsConstructor
	    @NoArgsConstructor
	    
	    public static class PaymentResponse {
	    	
	    	
	    	private long paymentid;
	    	
	    	private String status;
	    	
	    	private PaymentMode paymentMode;
	    	
	    	private long amount;
	    	
	    	private Instant paymentDate;
	    	
	    	private Long orderId;

	    }


}


