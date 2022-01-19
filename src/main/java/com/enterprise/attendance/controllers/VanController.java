package com.enterprise.attendance.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.attendance.dto.input.VanInputDTO;
import com.enterprise.attendance.dto.output.VanOutputDTO;
import com.enterprise.attendance.service.VanService;

@RestController
@RequestMapping(value = "/admin/vans")
public class VanController {

	@Autowired
	private VanService vanService;

	@PostMapping
	public VanOutputDTO create(@RequestBody VanInputDTO vanInputDTO) {
		return vanService.create(vanInputDTO);
	}

	@GetMapping
	public List<VanOutputDTO> retrieveAll() {
		return vanService.retrieveAll();
	}

	@GetMapping(value = "/{id}")
	public VanOutputDTO retrieveById(@PathVariable Integer id) {
		return vanService.retrieveById(id);
	}

	@PutMapping
	public VanOutputDTO update(@RequestBody VanInputDTO vanInputDTO, @RequestParam Integer id) {
		return vanService.update(vanInputDTO, id);
	}

	@DeleteMapping
	public void delete(@RequestParam(value = "id", required = true) Integer id) {
		vanService.delete(id);
	}
}
