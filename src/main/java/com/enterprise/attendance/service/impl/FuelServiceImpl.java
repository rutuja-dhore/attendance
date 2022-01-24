package com.enterprise.attendance.service.impl;

import com.enterprise.attendance.dao.FuelDAO;
import com.enterprise.attendance.dao.ShiftDAO;
import com.enterprise.attendance.dao.VanDAO;
import com.enterprise.attendance.dto.input.FuelInputDTO;
import com.enterprise.attendance.dto.input.ShiftInputDTO;
import com.enterprise.attendance.dto.output.AttendanceOutputDTO;
import com.enterprise.attendance.dto.output.FuelOutputDTO;
import com.enterprise.attendance.dto.output.ShiftOutputDTO;
import com.enterprise.attendance.exception.ErrorMessages;
import com.enterprise.attendance.exception.NullException;
import com.enterprise.attendance.model.Attendance;
import com.enterprise.attendance.model.Fuel;
import com.enterprise.attendance.model.Shift;
import com.enterprise.attendance.model.Van;
import com.enterprise.attendance.security.dao.UserRepository;
import com.enterprise.attendance.security.model.User;
import com.enterprise.attendance.service.FuelService;
import com.enterprise.attendance.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FuelServiceImpl implements FuelService {

	@Autowired
	private FuelDAO fuelDAO;

	@Autowired
	private VanDAO vanDAO;

	private FuelOutputDTO createResponseDTO(Fuel fuel) {
		FuelOutputDTO outputDTO= new FuelOutputDTO();
		outputDTO.setId(fuel.getId());
		outputDTO.setAmount(fuel.getAmount());
		outputDTO.setType(fuel.getType());
		outputDTO.setLogDate(fuel.getLogDate());
		return outputDTO;
	}

	@Override
	public FuelOutputDTO create(FuelInputDTO fuelInputDTO) {
		Fuel fuel = new Fuel();
		fuel.setType(fuelInputDTO.getType());
		fuel.setAmount(fuelInputDTO.getAmount());
		fuel.setLogDate(fuelInputDTO.getLogDate());

		Van van = vanDAO.findByNumber(fuelInputDTO.getVanNumber());
		fuel.setVan(van);
		fuel = fuelDAO.save(fuel);
		return createResponseDTO(fuel);
	}

	@Override
	public List<FuelOutputDTO> retrieveByVanNumber(String vanNumber) {
		Van van = vanDAO.findByNumber(vanNumber);

		List<Fuel> fuels = fuelDAO.findByVanId(van.getId());

		List<FuelOutputDTO> responseDTOs = new ArrayList<>();
		for (Fuel fuel : fuels) {
			responseDTOs.add(createResponseDTO(fuel));
		}
		return responseDTOs;
	}
}
