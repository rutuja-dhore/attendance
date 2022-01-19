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

import com.enterprise.attendance.dto.input.ShiftInputDTO;
import com.enterprise.attendance.dto.output.ShiftOutputDTO;
import com.enterprise.attendance.service.ShiftService;

@RestController
@RequestMapping(value = "/admin/shifts")
public class ShiftController {

	@Autowired
	private ShiftService shiftService;

	@PostMapping
	public ShiftOutputDTO create(@RequestBody ShiftInputDTO shiftInputDTO) {
		return shiftService.create(shiftInputDTO);
	}

	@GetMapping
	public List<ShiftOutputDTO> retrieveAll() {
		return shiftService.retrieveAll();
	}

	@GetMapping(value = "/{id}")
	public ShiftOutputDTO retrieveById(@PathVariable Integer id) {
		return shiftService.retrieveById(id);
	}

	@PutMapping
	public ShiftOutputDTO update(@RequestBody ShiftInputDTO shiftInputDTO, @RequestParam Integer id) {
		return shiftService.update(shiftInputDTO, id);
	}

	@DeleteMapping
	public void delete(@RequestParam(value = "id", required = true) Integer id) {
		shiftService.delete(id);
	}
}
