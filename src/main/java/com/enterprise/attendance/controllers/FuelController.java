package com.enterprise.attendance.controllers;

import com.enterprise.attendance.dto.input.FuelInputDTO;
import com.enterprise.attendance.dto.output.AttendanceOutputDTO;
import com.enterprise.attendance.dto.output.FuelOutputDTO;
import com.enterprise.attendance.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/fuel")
public class FuelController {

	@Autowired
	private FuelService fuelService;

	@PostMapping
	public FuelOutputDTO create(@RequestBody FuelInputDTO fuelInputDTO) {
		return fuelService.create(fuelInputDTO);
	}

	@GetMapping(value = "/{vanNumber}")
	public List<FuelOutputDTO> retrieveByVanNumber(@PathVariable String vanNumber) {
		return fuelService.retrieveByVanNumber(vanNumber);
	}

	@GetMapping
	public List<FuelOutputDTO> retrieveAll(
			@RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate,
			@RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date toDate,
			@RequestParam(value = "vanNumber", required = false) String vanNumber) {
		return fuelService.retrieveAll(fromDate, toDate, vanNumber);
	}
}
