package com.enterprise.attendance.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.enterprise.attendance.security.model.User;

@Entity
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "van_id", nullable = false)
	private Van van;

	@ManyToOne
	@JoinColumn(name = "shift_id", nullable = false)
	private Shift shift;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	private String tableNo;

	private double startKm;

	private double endKm;

	private double totalKm;

	private Date logDate;

	private Double disel;

	public Integer getId() {
		return id;
	}

	public Van getVan() {
		return van;
	}

	public void setVan(Van van) {
		this.van = van;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

	public String getTableNo() {
		return tableNo;
	}

	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}

	public double getStartKm() {
		return startKm;
	}

	public void setStartKm(double startKm) {
		this.startKm = startKm;
	}

	public double getEndKm() {
		return endKm;
	}

	public void setEndKm(double endKm) {
		this.endKm = endKm;
	}

	public double getTotalKm() {
		return totalKm;
	}

	public void setTotalKm(double totalKm) {
		this.totalKm = totalKm;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public Double getDisel() {
		return disel;
	}

	public void setDisel(Double disel) {
		this.disel = disel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
