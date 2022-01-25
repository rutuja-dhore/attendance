package com.enterprise.attendance.dto.input;

import java.util.Date;

public class AttendanceInputDTO {


	private String vanNumber;

	private String tableNo;

	private double startKm;

	private double endKm;

	private Date logDate;

	private double disel;

	private String mobileNumber;

	private String vendorName;

	private String comment;

	public String getVanNumber() {
		return vanNumber;
	}

	public void setVanNumber(String vanNumber) {
		this.vanNumber = vanNumber;
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

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
