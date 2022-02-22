package com.enterprise.attendance.dto.output;

import com.enterprise.attendance.model.Van;

import javax.persistence.*;
import java.util.Date;

public class FuelOutputDTO {

	private Integer id;

	private VanOutputDTO vanOutputDTO;

	private String logDate;

	private double amount;

	private String type;

	private Integer dayOfLogDate;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public VanOutputDTO getVanOutputDTO() {
		return vanOutputDTO;
	}

	public void setVanOutputDTO(VanOutputDTO vanOutputDTO) {
		this.vanOutputDTO = vanOutputDTO;
	}

	public String getLogDate() {
		return logDate;
	}

	public void setLogDate(String logDate) {
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

	public Integer getDayOfLogDate() {
		return dayOfLogDate;
	}

	public void setDayOfLogDate(Integer dayOfLogDate) {
		this.dayOfLogDate = dayOfLogDate;
	}
}
