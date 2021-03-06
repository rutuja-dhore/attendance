package com.enterprise.attendance.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import com.enterprise.attendance.model.Attendance;
import com.enterprise.attendance.model.Van;
import com.enterprise.attendance.model.Vendor;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;

	private String lastName;
	
	private String password;
	
	@Column(unique = true)
	private String mobileNumber;

	private boolean enabled;
	
	
	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private List<Attendance> attendance;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "vendor_users", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "vendor_id", referencedColumnName = "id"))
	private Collection<Vendor> vendorUsers = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "van_id", referencedColumnName = "id")
	private Van van;
	public User() {
	}

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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public List<Attendance> getAttendance() {
		return attendance;
	}

	public void setAttendance(List<Attendance> attendance) {
		this.attendance = attendance;
	}

	public Collection<Vendor> getVendorUsers() {
		return vendorUsers;
	}

	public void setVendorUsers(Collection<Vendor> vendorUsers) {
		this.vendorUsers = vendorUsers;
	}

	public Van getVan() {
		return van;
	}

	public void setVan(Van van) {
		this.van = van;
	}
}