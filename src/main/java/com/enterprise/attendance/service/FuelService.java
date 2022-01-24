package com.enterprise.attendance.service;

import com.enterprise.attendance.dto.input.FuelInputDTO;
import com.enterprise.attendance.dto.output.FuelOutputDTO;

import java.util.Date;
import java.util.List;

public interface FuelService {

	public FuelOutputDTO create(FuelInputDTO AttendanceInputDTO);

	public List<FuelOutputDTO> retrieveByVanNumber(String vanNumber);

}

