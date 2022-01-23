package com.enterprise.attendance.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.enterprise.attendance.dto.output.VanOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.attendance.dao.VanDAO;
import com.enterprise.attendance.dto.input.VanInputDTO;
import com.enterprise.attendance.exception.ErrorMessages;
import com.enterprise.attendance.exception.NullException;
import com.enterprise.attendance.model.Van;
import com.enterprise.attendance.service.VanService;

@Service
public class VanServiceImpl implements VanService {

	@Autowired
	private VanDAO vanDAO;

	@Override
	public VanOutputDTO create(VanInputDTO vanInputDTO) {
		Van van = new Van();
		van.setNumber(vanInputDTO.getNumber());
		van = vanDAO.save(van);
		return createResponseDTO(van);
	}

	@Override
	public List<VanOutputDTO> retrieveAll() {
		List<Van> vans = vanDAO.findAll();

		List<VanOutputDTO> responseDTOs = new ArrayList<>();
		for (Van van : vans) {
			responseDTOs.add(createResponseDTO(van));
		}
		return responseDTOs;
	}

	@Override
	public VanOutputDTO retrieveById(Integer id) {
		Optional<Van> van = vanDAO.findById(id);
		if (van.isEmpty()) {
			throw new NullException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		return createResponseDTO(van.get());
	}

	@Override
	public VanOutputDTO update(VanInputDTO vanInputDTO, Integer id) {
		Optional<Van> van = vanDAO.findById(id);
		if (van.isEmpty()) {
			throw new NullException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		Van vanPresent = van.get();
		vanPresent.setNumber(vanInputDTO.getNumber());
		vanPresent = vanDAO.save(vanPresent);

		return createResponseDTO(vanPresent);
	}

	@Override
	public void delete(Integer id) {
		Optional<Van> van = vanDAO.findById(id);
		if (van.isEmpty()) {
			throw new NullException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		vanDAO.delete(van.get());
	}

	private VanOutputDTO createResponseDTO(Van van) {
		VanOutputDTO outputDTO = new VanOutputDTO();
		outputDTO.setId(van.getId());
		outputDTO.setNumber(van.getNumber());
		return outputDTO;
	}

}
