package com.major.OrderService.Entity;

import java.time.Instant;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="PRODUCT_ID")
	private long productid;
	@Column(name="Quantity")
	private long quantity;
	@Column(name="Order_date")
	private Instant orderDate;
	@Column(name="status")
	private String orderStatus;
	@Column(name="Total_amount")
	private long Amount;
	
	

}
