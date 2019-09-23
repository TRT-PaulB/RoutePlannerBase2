package com.routeplanner.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
	
	@GetMapping("/")
	public String home() {
		return ("<h1>Welcome All!</h1>");
	}
	
	@GetMapping("/home")
	public String home2() {
		return ("<h1>Welcome Home All!</h1>");
	}
	
	// TODO error is not working, manually or as a result of a direct error
	@GetMapping("/error")
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
