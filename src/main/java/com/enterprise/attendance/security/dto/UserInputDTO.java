package com.enterprise.attendance.security.dto;

//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

import java.util.List;

public class UserInputDTO {

//	@Size(min = 1, message = "{Size.userDto.firstName}")
	private String firstName;

//	@NotNull
//	@Size(min = 1, message = "{Size.userDto.lastName}")
	private String lastName;

	private String password;

	private String mobileNumber;

	private List<String> vendors;

	private String vanNumber;

	public String getVanNumber() {
		return vanNumber;
	}

	public void setVanNumber(String vanNumber) {
		this.vanNumber = vanNumber;
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

	public List<String> getVendors() {
		return vendors;
	}

	public void setVendors(List<String> vendors) {
		this.vendors = vendors;
	}
}
