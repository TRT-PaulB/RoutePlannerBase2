package com.routeplanner.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.routeplanner.shopping.RouteQuery;

public interface RouteQueryRepository extends JpaRepository<RouteQuery, Integer> {

	@Query("SELECT rq FROM RouteQuery rq join rq.user u WHERE u.id = :userId")
	List<RouteQuery> findRouteQueriesForUser(@Param("userId") Integer userId);
	
	
	
}
