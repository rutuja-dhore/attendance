package com.enterprise.attendance.security.dto;

import java.util.List;

//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

public class UserDto {

	private Long id;

	//@Size(min = 1, message = "{Size.userDto.firstName}")
	private String firstName;

	//@NotNull
	//@Size(min = 1, message = "{Size.userDto.lastName}")
	private String lastName;

	private String password;

	private String mobileNumber;

	private boolean enabled;

	private List<String> role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}

}
