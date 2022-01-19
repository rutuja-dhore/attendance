package com.enterprise.attendance.service;

import java.util.List;

import com.enterprise.attendance.dto.input.VanInputDTO;
import com.enterprise.attendance.dto.output.VanOutputDTO;

public interface VanService {

	public VanOutputDTO create(VanInputDTO vanInputDTO);

	public List<VanOutputDTO> retrieveAll();

	public VanOutputDTO retrieveById(Integer id);

	public VanOutputDTO update(VanInputDTO vanInputDTO, Integer id);

	public void delete(Integer id);
}
