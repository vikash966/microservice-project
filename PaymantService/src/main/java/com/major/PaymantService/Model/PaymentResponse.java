package com.major.PaymantService.Model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
	
	
	private long paymentid;
	
	private String status;
	
	private PaymentMode paymentMode;
	
	private long amount;
	
	private Instant paymentDate;
	
	private Long orderId;

}
