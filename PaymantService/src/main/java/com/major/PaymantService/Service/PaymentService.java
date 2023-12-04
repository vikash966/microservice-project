package com.major.PaymantService.Service;

import com.major.PaymantService.Model.PaymentRequest;
import com.major.PaymantService.Model.PaymentResponse;

public interface PaymentService {

	long doPayment (PaymentRequest paymentRequest);

	PaymentResponse getPaymentdetailsbyorderid(String orderId);
	
	
}
