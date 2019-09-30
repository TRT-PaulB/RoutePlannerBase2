package com.routeplanner.shopping.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.routeplanner.shopping.Basket;

@Repository
public interface BasketRepository<T> extends JpaRepository<Basket, Integer> {
	
	@Query("SELECT b FROM Basket b join b.contactDetails cd join cd.user u WHERE u.userName = :userName and b.open is true")
	Optional<Basket> findOpenBasketForUser(@Param("userName") String userName);
	
	@Query("SELECT b FROM Basket b join b.contactDetails cd join cd.user u WHERE u.id = :userId and b.open is true")
	Optional<Basket> findOpenBasketByUserId(@Param("userId") Integer userId);
	
}
