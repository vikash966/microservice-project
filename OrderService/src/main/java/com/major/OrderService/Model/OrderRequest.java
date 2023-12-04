package com.major.OrderService.Model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
	
	private long productid;
	
	private long totalAmount;
	
	private long quantity;
	
	private PaymentMode payementMode;

}
