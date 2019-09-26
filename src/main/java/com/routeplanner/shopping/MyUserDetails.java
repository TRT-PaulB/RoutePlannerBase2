//package com.routeplanner.shopping;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class MyUserDetails implements UserDetails {
//
//	public static final long serialVersionUID = 65465465465l;
//	
//	private String userName;
//	private String password;
//	private boolean active;
//	private List<GrantedAuthority> authorities;
//	
//	public MyUserDetails() {
//		
//	}
//	
//	public MyUserDetails(User user) {
//		this.userName = user.getUserName();
//		this.password = user.getPassword();
//		this.active = user.isActive();
//		
//		// roles are stored in the db as a comma separated list, prefixwed by 'ROLE_'
//		this.authorities = Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return authorities;
//	}
//
//	@Override
//	public String getPassword() {
//		return password;
//	}
//
//	@Override
//	public String getUsername() {
//		return userName;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return active;
//	}
//	
//	
////  WHEN NOT USING JPA, USE THE FOLLOWING BELOW WORKING WITH USERNAME, THIS IS HARD-CODED	
////	public MyUserDetails(String userName) {
////		this.userName = userName;
////	}
////	
////	@Override
////	public Collection<? extends GrantedAuthority> getAuthorities() {
////		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
////	}
////
////	@Override
////	public String getPassword() {
////		return "pass";
////	}
////
////	@Override
////	public String getUsername() {
////		return userName;
////	}
////
////	@Override
////	public boolean isAccountNonExpired() {
////		return true;
////	}
////
////	@Override
////	public boolean isAccountNonLocked() {
////		return true;
////	}
////
////	@Override
////	public boolean isCredentialsNonExpired() {
////		return true;
////	}
////
////	@Override
////	public boolean isEnabled() {
////		return true;
////	}
//	
//	
//}
