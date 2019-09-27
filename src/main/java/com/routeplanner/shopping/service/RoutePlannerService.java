package com.routeplanner.shopping.service;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.routeplanner.shopping.RouteQuery;
import com.routeplanner.shopping.repository.RouteQueryRepository;

@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED) 
@Service
public class RoutePlannerService {

	private static final Logger logger = LoggerFactory.getLogger(RoutePlannerService.class);
	
	@Autowired
	private RouteQueryRepository routeQueryRespository;
	
	public RoutePlannerService() {
		
	}
	
	public RouteQuery saveRoute(RouteQuery route) {
		return routeQueryRespository.save(route);
	}
	
	
	public List<RouteQuery> getRoutesForUser(Integer userId) throws Throwable {
		return routeQueryRespository.findRouteQueriesForUser(userId);
	}
	
}
