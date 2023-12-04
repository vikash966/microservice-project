package com.major.PaymantService.Entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="TRANSACTIONAL_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Transactional {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@Column(name="ORDER_ID")
	private long orderid;
	@Column(name="PAYMENT_MODE")
	private String paymentMode;
	@Column(name="REFERENCE_NUMBER")
	private String referenceNumber;
	@Column(name="PAYMENTDATE")
	private Instant paymentDate;
	@Column(name="PAYMENTSTATUS")
	private String paymentStatus;
	@Column(name="AMOUNT")
	private long amount;
	

}
