package com.routeplanner.shopping.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.routeplanner.shopping.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
	
	@Query("SELECT pm FROM PaymentMethod pm join pm.user u WHERE u.id = :userId")
	Optional<List<PaymentMethod>> findPaymentMethodForUser(@Param("userId") Integer userId);
	
}