package com.routeplanner.shopping.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.routeplanner.shopping.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Query("SELECT ord FROM Order ord join ord.basket b join b.contactDetails cd join cd.user u WHERE u.id = :userId and ord.purchased is true")
	List<Order> findPurchasesForUser(@Param("userName") Integer userId);
	
	
}
