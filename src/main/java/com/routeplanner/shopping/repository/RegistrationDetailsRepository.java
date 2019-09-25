package com.routeplanner.shopping.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.ContactDetails;

public interface RegistrationDetailsRepository<RegistrationDetails> extends JpaRepository<ContactDetails, Integer> {
	
	@Query("SELECT cd FROM ContactDetails cd join cd.user u WHERE u.userName = :userName")
	Optional<ContactDetails> findByUsername(@Param("userName") String userName);
	
}

