package com.enterprise.attendance.security.service.impl;

import java.util.*;

import com.enterprise.attendance.dao.VanDAO;
import com.enterprise.attendance.dao.VendorDAO;
import com.enterprise.attendance.dto.output.VanOutputDTO;
import com.enterprise.attendance.dto.output.VendorOutputDTO;
import com.enterprise.attendance.model.Van;
import com.enterprise.attendance.model.Vendor;
import com.enterprise.attendance.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprise.attendance.exception.ErrorMessages;
import com.enterprise.attendance.exception.NullException;
import com.enterprise.attendance.security.dao.PrivilegeRepository;
import com.enterprise.attendance.security.dao.RoleRepository;
import com.enterprise.attendance.security.dao.UserRepository;
import com.enterprise.attendance.security.dto.UserDto;
import com.enterprise.attendance.security.dto.UserInputDTO;
import com.enterprise.attendance.security.model.Privilege;
import com.enterprise.attendance.security.model.Role;
import com.enterprise.attendance.security.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private VendorDAO vendorDAO;

	@Autowired
	private VanDAO vanDAO;
	@Override
	public UserDto createAdmin(UserInputDTO userInputDTO) {
		Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
		Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
		List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
		createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
		Role adminRole = roleRepository.findByName("ROLE_ADMIN");
		User user = new User();
		user.setFirstName(userInputDTO.getFirstName());
		user.setLastName(userInputDTO.getLastName());
		user.setPassword(userInputDTO.getPassword());
		user.setMobileNumber(userInputDTO.getMobileNumber());
		user.setRoles(Collections.singletonList(adminRole));
		user.setEnabled(true);
		user = userRepository.save(user);
		return createResponse(user);
	}

	private UserDto createResponse(User user) {
		UserDto outputDTO = new UserDto();
		outputDTO.setId(user.getId());
		outputDTO.setFirstName(user.getFirstName());
		outputDTO.setLastName(user.getLastName());
		outputDTO.setPassword(user.getPassword());
		outputDTO.setMobileNumber(user.getMobileNumber());
		outputDTO.setEnabled(user.isEnabled());
		List<String> roleNames = new ArrayList<>();
		for (Role role : user.getRoles()) {
			roleNames.add(role.getName());
		}
		
		outputDTO.setRole(roleNames);

		Van van = user.getVan();
		if(van != null) {
			VanOutputDTO vanOutputDTO = new VanOutputDTO();
			vanOutputDTO.setNumber(van.getNumber());
			vanOutputDTO.setId(van.getId());
			outputDTO.setVan(vanOutputDTO);
		}
		return outputDTO;
	}

	@Override
	public UserDto createUser(UserInputDTO userInputDTO) {
		Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
		createRoleIfNotFound("ROLE_USER", Collections.singletonList(readPrivilege));
		Role userRole = roleRepository.findByName("ROLE_USER");
		User user = new User();
		user.setFirstName(userInputDTO.getFirstName());
		user.setLastName(userInputDTO.getLastName());
		user.setPassword(userInputDTO.getPassword());
		user.setRoles(Arrays.asList(userRole));
		user.setEnabled(true);
		user.setMobileNumber(userInputDTO.getMobileNumber());

		Van van = vanDAO.findByNumber(userInputDTO.getVanNumber());
		user.setVan(van);
		List<String> vendorNames = userInputDTO.getVendors();
		if(!vendorNames.isEmpty()) {
			List<Vendor> vendors = new ArrayList<>();

			for (String vendorName : vendorNames) {
				vendors.add(vendorDAO.findByName(vendorName));
		}
		user.setVendorUsers(vendors);
		}
		user = userRepository.save(user);

		return createResponse(user);
	}

	@Override
	public List<UserDto> retrieveAll() {

		List<User> users = userRepository.findAll();
		List<UserDto> userDetails = new ArrayList<>();

		for (User user : users) {
			UserDto userDTO = createResponse(user);
			userDetails.add(userDTO);
		}
		return userDetails;
	}

	@Override
	public UserDto retrieveByMobileNumber(String mobileNumber) {
		User user = userRepository.findByMobileNumber(mobileNumber);
		if (user == null) {
			throw new NullException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		return createResponse(user);
	}

//	@Transactional
	private Privilege createPrivilegeIfNotFound(String name) {

		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

//	@Transactional
	private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
			role.setPrivileges(privileges);
			roleRepository.save(role);
		}
		return role;
	}

	@Override
	public UserDto update(UserInputDTO userInputDTO, Long id) {

		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new NullException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		User userPresent = user.get();
		userPresent.setFirstName(userInputDTO.getFirstName());
		userPresent.setLastName(userInputDTO.getLastName());
		userPresent.setPassword(userInputDTO.getPassword());
		userPresent.setMobileNumber(userInputDTO.getMobileNumber());
		userPresent.setEnabled(true);
		userPresent = userRepository.save(userPresent);
		return createResponse(userPresent);
	}

	@Override
	public void delete(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new NullException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		userRepository.delete(user.get());
	}

}
