package com.routeplanner.ctrl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.routeplanner.client.service.ITravelInfoService;
import com.routeplanner.dm.JourneySummary;
import com.routeplanner.shopping.RouteQuery;


@RestController
@RequestMapping("route")
public class RoutePlannerController {
	
	private final static Logger logger = LoggerFactory.getLogger(RoutePlannerController.class);

	@Autowired
	private ITravelInfoService travelInfoService;
	
	@GetMapping("/")
	public String home() {
		return ("<h1>Welcome All!</h1>");
	}
	
	@GetMapping("/{start}/{destination}")
	public RouteQuery getRouteQuery(@PathVariable String start, @PathVariable String destination) {
		logger.info("making a route query: start = " + start + " --> dest = " + destination);
		JourneySummary journey = travelInfoService.getJourneyDetails(start, destination);
		return new RouteQuery(start, destination, journey.getRouteInfo());
	}
	
	/*
	 * https://developer.okta.com/blog/2018/07/19/simple-crud-react-and-spring-boot
	 * Note: this is also the OCTA account!
	 * 
	@GetMapping("/group/{id}")
    ResponseEntity<?> getGroup(@PathVariable Long id) {
        Optional<Group> group = groupRepository.findById(id);
        return group.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
	*/
	
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

}
