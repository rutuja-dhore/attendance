package com.enterprise.attendance.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.attendance.dto.input.AttendanceInputDTO;
import com.enterprise.attendance.dto.output.AttendanceOutputDTO;
import com.enterprise.attendance.service.AttendanceService;

@RestController
@RequestMapping(value = "/trips")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@PostMapping
	public AttendanceOutputDTO create(@RequestBody AttendanceInputDTO vanInputDTO) {
		return attendanceService.create(vanInputDTO);
	}

	@GetMapping
	public List<AttendanceOutputDTO> retrieveAll(
			@RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate,
			@RequestParam(value = "mobileNumber", required = false) String mobileNumber) {
		return attendanceService.retrieveAll(fromDate, toDate, mobileNumber);
	}

	@GetMapping(value = "/{mobileNumber}")
	public List<AttendanceOutputDTO> retrieveByMobileNumber(@PathVariable String mobileNumber) {
		return attendanceService.retrieveByMobileNumber(mobileNumber);
	}

	// @GetMapping(value = "/{id}")
	// public VanOutputDTO retrieveById(@PathVariable Integer id) {
	// return attendanceService.retrieveById(id);
	// }

	@PutMapping
	public AttendanceOutputDTO update(@RequestBody AttendanceInputDTO attendanceInputDTO, @RequestParam Integer id) {
		return attendanceService.update(attendanceInputDTO, id);
	}
}
