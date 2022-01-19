package com.enterprise.attendance.dto.input;

import java.util.Date;

public class AttendanceInputDTO {

	private String vanNumber;

	private String shiftName;

	private String tableNo;

	private double startKm;

	private double endKm;

	private Date logDate;

	private double disel;

	private String mobileNumber;

	public String getVanNumber() {
		return vanNumber;
	}

	public void setVanNumber(String vanNumber) {
		this.vanNumber = vanNumber;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
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

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public double getDisel() {
		return disel;
	}

	public void setDisel(double disel) {
		this.disel = disel;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
