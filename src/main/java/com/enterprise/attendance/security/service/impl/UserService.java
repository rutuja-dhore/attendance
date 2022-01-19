package com.enterprise.attendance.security.service.impl;

import java.util.List;

import com.enterprise.attendance.security.dto.UserDto;
import com.enterprise.attendance.security.dto.UserInputDTO;

public interface UserService {

	public UserDto createAdmin(UserInputDTO userDTO);

	public UserDto createUser(UserInputDTO userDto);

	public List<UserDto> retrieveAll();

	public UserDto retrieveByMobileNumber(String mobileNumber);

	public UserDto update(UserInputDTO userInputDTO, Long id);

	public void delete(Long id);

}
