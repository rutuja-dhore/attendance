package com.enterprise.attendance.dto.output;

import java.util.Date;

import com.enterprise.attendance.security.dto.UserDto;

public class AttendanceOutputDTO {

	private Integer id;

	private VanOutputDTO vanOutputDTO;

	private String tableNo;

	private double startKm;

	private double endKm;

	private double totalKm;
	private double disel;
	private Date logDate;

	private UserDto userOutupDTO;

	private VendorOutputDTO vendorOutputDTO;

	private String comment;

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

	public double getDisel() {
		return disel;
	}

	public void setDisel(double disel) {
		this.disel = disel;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public UserDto getUserOutupDTO() {
		return userOutupDTO;
	}

	public void setUserOutupDTO(UserDto userOutupDTO) {
		this.userOutupDTO = userOutupDTO;
	}

	public VendorOutputDTO getVendorOutputDTO() {
		return vendorOutputDTO;
	}

	public void setVendorOutputDTO(VendorOutputDTO vendorOutputDTO) {
		this.vendorOutputDTO = vendorOutputDTO;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
