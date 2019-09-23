package com.routeplanner.shopping.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.routeplanner.shopping.OldUser;

public interface OldUserRepository<T> extends JpaRepository<OldUser, Integer> {

	Optional<OldUser> findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.username = :username and u.password = :password")
	Optional<OldUser> fetchUserFromLoginCredentials(String username, String password);
	
}
