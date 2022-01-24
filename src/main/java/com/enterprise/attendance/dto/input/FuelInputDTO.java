package com.enterprise.attendance.dto.input;

import com.enterprise.attendance.dto.output.VanOutputDTO;

import java.util.Date;

public class FuelInputDTO {

	private String vanNumber;

	private Date logDate;

	private double amount;

	private String type;

	public String getVanNumber() {
		return vanNumber;
	}

	public void setVanNumber(String vanNumber) {
		this.vanNumber = vanNumber;
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
