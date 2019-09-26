package com.routeplanner.shopping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="user")  
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(unique=true)
	private String userName;
	
	private String password;
	private boolean active;
	private String roles;
	
//	private String title;
//	
//	@NotNull
//    @Size(min=2, max=30)
//	private String fullname;
//	
//	@Size(min=2, max=30)
//	@Column(name="address_line_1", length = 80)
//	private String addressLine1;
//	
//	@Column(name="address_line_2", length = 80)
//	private String addressLine2;
//	
//	@Column(name="address_line_3", length = 80)
//	private String addressLine3;
//	
//	@Column(length = 40)
//	private String city;
//	
//	@Column(name="region", length = 50)
//	private String region;
//	
//	@Column(length = 40)
//	private String country;
//	
//	@Email
//	@Column(length = 60)
//	private String email;
//	
//	@Column(name="mobile_phone", length = 30)
//	@Pattern(regexp = "^[0-9]*$")
//	private String mobileTel;
//	
//	@Column(name="home_phone", length = 30)
//	@Pattern(regexp = "^[0-9]*$")
//	private String homeTel;
//	
//	@Column(name="office_phone", length = 30)
//	@Pattern(regexp = "^[0-9]*$")
//	private String officeTel;

	
	public User() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getFullname() {
//		return fullname;
//	}
//	public void setFullname(String fullname) {
//		this.fullname = fullname;
//	}
//	public String getAddressLine1() {
//		return addressLine1;
//	}
//	public void setAddressLine1(String addressLine1) {
//		this.addressLine1 = addressLine1;
//	}
//	public String getAddressLine2() {
//		return addressLine2;
//	}
//	public void setAddressLine2(String addressLine2) {
//		this.addressLine2 = addressLine2;
//	}
//	public String getAddressLine3() {
//		return addressLine3;
//	}
//	public void setAddressLine3(String addressLine3) {
//		this.addressLine3 = addressLine3;
//	}
//	public String getCity() {
//		return city;
//	}
//	public void setCity(String city) {
//		this.city = city;
//	}
//	public String getRegion() {
//		return region;
//	}
//	public void setRegion(String region) {
//		this.region = region;
//	}
//	public String getCountry() {
//		return country;
//	}
//	public void setCountry(String country) {
//		this.country = country;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getMobileTel() {
//		return mobileTel;
//	}
//	public void setMobileTel(String mobileTel) {
//		this.mobileTel = mobileTel;
//	}
//	public String getHomeTel() {
//		return homeTel;
//	}
//	public void setHomeTel(String homeTel) {
//		this.homeTel = homeTel;
//	}
//	public String getOfficeTel() {
//		return officeTel;
//	}
//	public void setOfficeTel(String officeTel) {
//		this.officeTel = officeTel;
//	}
	
	
	
	
}
