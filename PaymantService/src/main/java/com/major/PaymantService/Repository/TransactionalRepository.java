package com.major.PaymantService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.major.PaymantService.Entity.Transactional;


@Repository
public interface  TransactionalRepository extends JpaRepository<Transactional, Long>
{
	Transactional findByOrderid(long OrderId);
	

}
