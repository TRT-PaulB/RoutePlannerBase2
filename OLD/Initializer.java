package com.routeplanner;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.routeplanner.shopping.Role;
import com.routeplanner.shopping.OldUser;
import com.routeplanner.shopping.repository.RoleRepository;
import com.routeplanner.shopping.repository.OldUserRepository;

@Component
class Initializer implements CommandLineRunner {

    private final OldUserRepository usrRepository;
    
    private final RoleRepository roleRepository;

    public Initializer(OldUserRepository usrRepository, RoleRepository roleRepository) {
        this.usrRepository = usrRepository;
        this.roleRepository = roleRepository;
    }

    
    
    @Override
    public void run(String... strings) {
		
    	// TEST IN~SErting Roles.....
//    	Set<Role> roles = roleRepository.findAll().stream().collect(Collectors.toSet());;
//	    roles.forEach(System.out::println);
//	    
//	    User user = new User();
//    	user.setEmail("email@email.com");
//		user.setLastName("Smith");
//		user.setUsername("Johnny");
//		user.setPassword("password");
//		user.setActive(1);
//		user.setId(1);
//		usrRepository.save(user);
//		usrRepository.findAll().forEach(System.out::println);
//		user.setRoles(roles);
   
		
		// EXAMPLE:
//        // work around as anotation processing is not working
//        Group djug = repository.findByName("Denver JUG");
//        Event e = Event.builder().title("Full Stack Reactive")
//                .description("Reactive with Spring Boot + React")
//                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
//                .build();
//        djug.setEvents(Collections.singleton(e));
//        repository.save(djug);
//
//        repository.findAll().forEach(System.out::println);
    }
}

