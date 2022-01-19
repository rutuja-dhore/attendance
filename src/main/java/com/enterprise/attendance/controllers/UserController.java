package com.enterprise.attendance.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.attendance.security.dto.UserDto;
import com.enterprise.attendance.security.dto.UserInputDTO;
import com.enterprise.attendance.security.service.impl.UserService;

@RestController
@RequestMapping(value = "/admin/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public UserDto createUser(@RequestBody UserInputDTO userDto) {
		return userService.createUser(userDto);
	}

	@PostMapping("/addAdmins")
	@ResponseBody
	public UserDto createAdmin(@RequestBody UserInputDTO userDto) {
		return userService.createAdmin(userDto);
	}

	@GetMapping
	public List<UserDto> retrieveAll() {
		return userService.retrieveAll();
	}

	@GetMapping(value = "/getByMobileNumber")
	public UserDto retrieveByMobileNumber(@RequestParam String mobileNumber) {
		return userService.retrieveByMobileNumber(mobileNumber);
	}

	@PutMapping
	public UserDto update(@RequestBody UserInputDTO userInputDTO, @RequestParam Long id) {
		return userService.update(userInputDTO, id);
	}

	@DeleteMapping
	public void delete(@RequestParam(value = "id", required = true) Long id) {
		userService.delete(id);
	}

}
