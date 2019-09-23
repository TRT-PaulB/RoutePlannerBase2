package com.routeplanner.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.OldUser;
import com.routeplanner.shopping.Order;
import com.routeplanner.shopping.Purchase;
import com.routeplanner.shopping.RouteQuery;
import com.routeplanner.shopping.TestGroup;

/*
MONDAY
- DONE get spring security running in all aspects and permission levels
- DONE merge with route planner front end
- DONE POC on getting the route
- DONE load complex db with hibernate

- plug route and start, destination into code
- implement getRouteQuery() to get backend info, and handle errors and exceptions in react (maybe use toastify)
- decide on policy to mix security approaches: a) spring security b) mosh c) jugtours
- SPRINGSEC: defend members only pages
- SPRINGSEC: defend admin only pages   

- POC on saving basket with route info [move away from shopping object - need boolean to show if open, only allow 1] 
      ie. on query page...., with mosh handling   

TUESDAY  
==> complete the basic journey through purchase, and to register, admin  etc
--> persisting order will involve closing basket, creating order as a transaction
--> a purchase will involve closing a currentOrder flag, and purchasing
--> persist as and when: order, purchase and user

 */


@RestController
public class BasicController {
	
	private final static Logger logger = LoggerFactory.getLogger(BasicController.class);
	
	@GetMapping("/")
	public String home() {
		return ("<h1>Welcome All!</h1>");
	}
	
	// TODO POC ONLY
	@GetMapping("/home")
	public TestGroup home2() {
		TestGroup tg = new TestGroup();
		return tg;
	}
	
	// TODO POC ONLY 2
	@GetMapping("/route/{start}/{destination}")
	public RouteQuery getRouteQuery(@PathVariable String start, @PathVariable String destination) {
		logger.info("making a route query: start = " + start + " -0-> dest = " + destination);
		RouteQuery rq = new RouteQuery(start, destination, "dummy route info goes here", false);
		return rq;
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
