package com.routeplanner.ctrl;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.routeplanner.client.service.ITravelInfoService;
import com.routeplanner.dm.JourneySummary;
import com.routeplanner.shopping.RouteQuery;
import com.routeplanner.shopping.service.RoutePlannerService;


@RestController
@RequestMapping("route")
public class RoutePlannerController {
	
	private final static Logger logger = LoggerFactory.getLogger(RoutePlannerController.class);

	@Autowired
	private ITravelInfoService travelInfoService;
	
	@Autowired
	private RoutePlannerService routePlannerService;
	
	@GetMapping("/")
	public String home() {
		return ("<h1>Welcome All!</h1>");
	}
	
	@GetMapping("/{start}/{destination}")
	public ResponseEntity<RouteQuery> getRouteQuery(@PathVariable String start, @PathVariable String destination) throws URISyntaxException {
		logger.info("making a route query: start = " + start + " --> dest = " + destination);
		JourneySummary journey = travelInfoService.getJourneyDetails(start, destination);
		try {
			RouteQuery route = new RouteQuery(start, destination, journey.getRouteInfo());
			return ResponseEntity.ok().body(route);
		} catch(Throwable t) {
			// in case of an issue not handled in TravelInfoService
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<RouteQuery> saveRoute(@RequestBody RouteQuery routeQuery) throws URISyntaxException {
		logger.info("Request to persist route: {}", routeQuery);
		RouteQuery result = routePlannerService.saveRoute(routeQuery);
		return ResponseEntity.created(new URI("/route/add" + result.getId()))
                .body(result);
	}	
	
	@GetMapping("/stations")
	public List<String> getStationNames() {
		return travelInfoService.getStationList();
	}
	
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
	
	@GetMapping("/query/{userId}")
	public List<RouteQuery> getRouteQueriesForUser(@PathVariable Integer userId) {
		try {
			return routePlannerService.getRoutesForUser(userId);
		} catch(Throwable t) {
			return new ArrayList<RouteQuery>();
		}
	}

}
