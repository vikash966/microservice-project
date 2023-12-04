package com.major.PaymantService.Service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.major.PaymantService.Entity.Transactional;
import com.major.PaymantService.Model.PaymentMode;
import com.major.PaymantService.Model.PaymentRequest;
import com.major.PaymantService.Model.PaymentResponse;
import com.major.PaymantService.Repository.TransactionalRepository;

import lombok.extern.log4j.Log4j2;



@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private TransactionalRepository transactionalRepository;

	@Override
	public long doPayment(PaymentRequest paymentRequest) {
		// TODO Auto-generated method stub
		
		log.info("RECORDING PAYMENT DETAILS: {}",paymentRequest);
		
		Transactional transactional=Transactional.builder()
				.paymentDate(Instant.now())
				.paymentMode(paymentRequest.getPaymentMode().name())
				.paymentStatus("SUCCESS")
				.orderid(paymentRequest.getOrderid())
				.referenceNumber(paymentRequest.getReferenceNumber())
				.amount(paymentRequest.getAmount())
				.build();
		
		transactionalRepository.save(transactional);
		
		
		log.info("Transactions completed with Id : {}",transactional.getId());
		return transactional.getId();
	}

	@Override
	public PaymentResponse getPaymentdetailsbyorderid(String orderId) {
		// TODO Auto-generated method stub
		
		log.info("GETTING PAYMENT DETAILS FOR ORDER ID :{}",orderId);
		
		Transactional transactionaldetails= transactionalRepository.findByOrderid(Long.valueOf(orderId));
		
		PaymentResponse paymentResponse=PaymentResponse.builder()
				
				.paymentid(transactionaldetails.getId())
				.paymentMode(PaymentMode.valueOf(transactionaldetails.getPaymentMode()))
				.paymentDate(transactionaldetails.getPaymentDate())
				.orderId(transactionaldetails.getOrderid())
				.status(transactionaldetails.getPaymentStatus())
				.amount(transactionaldetails.getAmount())
				
				.build();
		
		return paymentResponse;
	}

}
