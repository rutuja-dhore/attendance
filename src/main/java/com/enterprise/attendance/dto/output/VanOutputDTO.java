package com.enterprise.attendance.dto.output;

import java.io.Serializable;

public class VanOutputDTO implements Serializable {

	private Integer id;

	private String number;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
