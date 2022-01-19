package com.enterprise.attendance.service;

import java.util.List;

import com.enterprise.attendance.dto.input.ShiftInputDTO;
import com.enterprise.attendance.dto.output.ShiftOutputDTO;

public interface ShiftService {

	public ShiftOutputDTO create(ShiftInputDTO vanInputDTO);

	public List<ShiftOutputDTO> retrieveAll();

	public ShiftOutputDTO retrieveById(Integer id);

	public ShiftOutputDTO update(ShiftInputDTO vanInputDTO, Integer id);

	public void delete(Integer id);
}
