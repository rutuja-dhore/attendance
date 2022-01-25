package com.enterprise.attendance.model;

import com.enterprise.attendance.security.model.User;

import java.util.List;

import javax.persistence.*;

@Entity
public class Van {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(unique = true)
	private String number;

	@OneToMany(mappedBy = "van", cascade=CascadeType.ALL)
	private List<Attendance> attendance;

	@OneToOne(mappedBy = "van")
	private User user;

	public Integer getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public List<Attendance> getAttendance() {
		return attendance;
	}

	public void setAttendance(List<Attendance> attendance) {
		this.attendance = attendance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
