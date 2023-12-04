package com.major.OrderService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.major.OrderService.Entity.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{

}
