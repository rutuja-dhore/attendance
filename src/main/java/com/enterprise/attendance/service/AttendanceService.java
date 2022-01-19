package com.enterprise.attendance.service;

import java.util.Date;
import java.util.List;

import com.enterprise.attendance.dto.input.AttendanceInputDTO;
import com.enterprise.attendance.dto.output.AttendanceOutputDTO;

public interface AttendanceService {

	public AttendanceOutputDTO create(AttendanceInputDTO AttendanceInputDTO);

	public List<AttendanceOutputDTO> retrieveAll(Date fromDate, Date toDate, String mobileNumber);

	public AttendanceOutputDTO retrieveById(Integer id);

	public List<AttendanceOutputDTO> retrieveByMobileNumber(String mobileNumber);

	public AttendanceOutputDTO update(AttendanceInputDTO attendanceInputDTO, Integer id);

}

