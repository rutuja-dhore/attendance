package com.enterprise.attendance.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.attendance.dao.ShiftDAO;
import com.enterprise.attendance.dto.input.ShiftInputDTO;
import com.enterprise.attendance.dto.output.ShiftOutputDTO;
import com.enterprise.attendance.exception.ErrorMessages;
import com.enterprise.attendance.exception.NullException;
import com.enterprise.attendance.model.Shift;
import com.enterprise.attendance.service.ShiftService;

@Service
public class ShiftServiceImpl implements ShiftService {

	@Autowired
	private ShiftDAO shiftDAO;

	@Override
	public ShiftOutputDTO create(ShiftInputDTO shiftInputDTO) {
		Shift shift = new Shift();
		shift.setName(shiftInputDTO.getName());
		shift.setStartTime(shiftInputDTO.getStartTime());
		shift.setEndTime(shiftInputDTO.getEndTime());
		shift = shiftDAO.save(shift);
		return createResponseDTO(shift);
	}

	@Override
	public List<ShiftOutputDTO> retrieveAll() {
		List<Shift> Shifts = shiftDAO.findAll();

		List<ShiftOutputDTO> responseDTOs = new ArrayList<>();
		for (Shift Shift : Shifts) {
			responseDTOs.add(createResponseDTO(Shift));
		}
		return responseDTOs;
	}

	@Override
	public ShiftOutputDTO retrieveById(Integer id) {
		Optional<Shift> shift = shiftDAO.findById(id);
		if (shift.isEmpty()) {
			throw new NullException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		return createResponseDTO(shift.get());
	}

	@Override
	public ShiftOutputDTO update(ShiftInputDTO shiftInputDTO, Integer id) {
		Optional<Shift> shift = shiftDAO.findById(id);
		if (shift.isEmpty()) {
			throw new NullException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		Shift shiftPresent = shift.get();
		shiftPresent.setName(shiftInputDTO.getName());
		shiftPresent.setStartTime(shiftInputDTO.getStartTime());
		shiftPresent.setEndTime(shiftInputDTO.getEndTime());
		shiftPresent = shiftDAO.save(shiftPresent);

		return createResponseDTO(shiftPresent);
	}

	@Override
	public void delete(Integer id) {
		Optional<Shift> shift = shiftDAO.findById(id);
		if (shift.isEmpty()) {
			throw new NullException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		shiftDAO.delete(shift.get());
	}

	private ShiftOutputDTO createResponseDTO(Shift shift) {
		ShiftOutputDTO outputDTO = new ShiftOutputDTO();
		outputDTO.setId(shift.getId());
		outputDTO.setName(shift.getName());
		outputDTO.setStartTime(shift.getStartTime());
		outputDTO.setEndTime(shift.getEndTime());
		return outputDTO;
	}

}
