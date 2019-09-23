package com.routeplanner.ctrl;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.routeplanner.shopping.OldUser;
import com.routeplanner.shopping.repository.OldUserRepository;


@RestController
@RequestMapping("/routeplanner")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	private static final ResourceBundle rb = ResourceBundle.getBundle("config.sysprops");
	
	//@Autowired
	private OldUserRepository userRepository;
		
    private static final String API_BASE = "/routeplanner";
	
	
	// NOTE: ~TEMP, ONLY ARTIFICIAL UNTIL SECURITY IMPLEMENTED this is a react - java app pre security, so log on in primitive way....
	// http://localhost:8080/routeplanner/login
	// {"username": "Johnny", "password": "password"}
		@PostMapping("/login")
	    ResponseEntity<String> createGroup(@Valid @RequestBody OldUser loginUser) throws URISyntaxException {
			boolean validAdmin = (loginUser.getUsername().contentEquals("Johnny") 
					&& loginUser.getPassword().contentEquals("password"));
			String result = validAdmin ? "success" : "";
//			logger.info("Logged In = " + result);
			
			// access properties file
			String value = rb.getString("database.name");
			logger.info("OUT OF INTEREST  - working with database:  " + value);
			
	        return ResponseEntity.created(new URI(API_BASE + "/query")).body(result);
	    }
	

		@PostMapping("/register")
	    ResponseEntity<OldUser> register(@Valid @RequestBody OldUser user) throws URISyntaxException {
			logger.info("Request to create group: {}", user);
	        OldUser result = null; // userRepository.save(user);	        
	        return ResponseEntity.created(new URI(API_BASE + "/query" + result.getId()))
	                .body(result);
	    }	
		
		
		@GetMapping("/titles") 
		public String gotToRegistration() {
//			if (sess.getAttribute("titleList") == null) {
//				sess.setAttribute("titleList", getTitleLiterals());
//			}
			return "registration";
		}
	

	
	@PutMapping("/user/{id}/change-pass")
    ResponseEntity<OldUser> updateUserPassword(@Valid @RequestBody OldUser user) {
        logger.info("Request to update user: {}", user);
        OldUser result = null; // userRepository.save(user);
        return ResponseEntity.ok().body(result);
    }
    

}