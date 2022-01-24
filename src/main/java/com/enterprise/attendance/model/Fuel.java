package com.enterprise.attendance.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Fuel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "van_id", nullable = false)
	private Van van;

	private Date logDate;

	private double amount;

	private String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Van getVan() {
		return van;
	}

	public void setVan(Van van) {
		this.van = van;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
