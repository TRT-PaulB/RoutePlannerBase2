package com.routeplanner.shopping.repository;
import java.util.Optional;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.ContactDetails;

public interface ContactDetailsRespository extends JpaRepository<ContactDetails, Integer> {
	
	@Modifying
	@Query("update ContactDetails cd set cd.fullname = ?1, " +
			"cd.title = ?4, " + 
			"cd.addressLine1 = ?5, " +
			"cd.addressLine2 = ?6, " +
			"cd.addressLine3 = ?7, " +
			"cd.city = ?8, " +
			"cd.region = ?9, " +
			"cd.country = ?10, " +
			"cd.email = ?11, " +
			"cd.mobileTel = ?12, " +
			"cd.homeTel = ?13, " +
			"cd.officeTel = ?14 " +
			"where cd.user.id = ?2 and cd.id = ?3 ")
	void setUserInfoById(String userName, Integer userId, Integer contactDetailsId, String title, String address1, String address2, String address3,
			String city, String region, String country, String email, String mobileTel, String homeTel, String officeTel);
	
	
	@Query("SELECT cd from ContactDetails cd join cd.user u WHERE u.id = :id")
	Optional<ContactDetails> findContactDetailsForUser(@Param("id") Integer id);
	
}

