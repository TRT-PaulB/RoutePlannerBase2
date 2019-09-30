package com.routeplanner.shopping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.routeplanner.shopping.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUserName(String userName);	
	
	Optional<User> findById(Integer id);

}
