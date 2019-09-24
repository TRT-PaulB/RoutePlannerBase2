package com.routeplanner.ctrl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.routeplanner.client.service.ITravelInfoService;
import com.routeplanner.dm.JourneySummary;
import com.routeplanner.shopping.RouteQuery;




@RestController
public class BasicController {
	
	private final static Logger logger = LoggerFactory.getLogger(BasicController.class);

	@Autowired
	private ITravelInfoService travelInfoService;
	
	@GetMapping("/")
	public String home() {
		return ("<h1>Welcome All!</h1>");
	}
	
	@GetMapping("/route/{start}/{destination}")
	public RouteQuery getRouteQuery(@PathVariable String start, @PathVariable String destination) {
		logger.info("making a route query: start = " + start + " --> dest = " + destination);
		
		// tmp solution to fit in with old services + add error handling?
		if (travelInfoService == null) {
			logger.info("AUTOWIR~ING FAILED");
		}
		
		
		
		JourneySummary journey = travelInfoService.getJourneyDetails(start, destination);
		return new RouteQuery(start, destination, journey.getRouteInfo());
	}
	
	
	// tmp solution
	@GetMapping("/stations")
	public List<String> getStationNames() {
		return travelInfoService.getStationList();
	}
	
	
	// TODO error is not working, manually or as a result of a direct error
	@GetMapping("/errors")
	public String error() {
		return ("<h1>Error goes here!</h1>");
	}
	
	@GetMapping("/user")
	public String user() {
		return ("<h1>Welcome Users!</h1>");
	}
	
	@GetMapping("/admin")
	public String admin() {
		return ("<h1>Welcome Admin users!</h1>");
	}
	
	@GetMapping("/admin/etwas")
	public String adminEtwas() {
		return ("<h1>Welcome Admin users ETWAS!</h1>");
	}
	
	

}
