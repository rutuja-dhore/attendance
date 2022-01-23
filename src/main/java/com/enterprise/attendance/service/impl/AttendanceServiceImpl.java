package com.enterprise.attendance.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.enterprise.attendance.dao.VendorDAO;
import com.enterprise.attendance.dto.output.VanOutputDTO;
import com.enterprise.attendance.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.attendance.dao.AttendanceDAO;
import com.enterprise.attendance.dao.ShiftDAO;
import com.enterprise.attendance.dao.VanDAO;
import com.enterprise.attendance.dto.input.AttendanceInputDTO;
import com.enterprise.attendance.dto.output.AttendanceOutputDTO;
import com.enterprise.attendance.dto.output.ShiftOutputDTO;
import com.enterprise.attendance.dto.output.VendorOutputDTO;
import com.enterprise.attendance.exception.ErrorMessages;
import com.enterprise.attendance.exception.NullException;
import com.enterprise.attendance.model.Attendance;
import com.enterprise.attendance.model.Shift;
import com.enterprise.attendance.model.Van;
import com.enterprise.attendance.security.dao.UserRepository;
import com.enterprise.attendance.security.dto.UserDto;
import com.enterprise.attendance.security.model.Role;
import com.enterprise.attendance.security.model.User;
import com.enterprise.attendance.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceDAO attendanceDAO;

	@Autowired
	private VanDAO vanDAO;

	@Autowired
	private ShiftDAO shiftDAO;

	@Autowired
	private UserRepository userDAO;

	@Autowired
	private VendorDAO vendorDAO;
	@Override
	public AttendanceOutputDTO create(AttendanceInputDTO attendanceInputDTO) {
		Attendance attendance = new Attendance();
		attendance.setLogDate(attendanceInputDTO.getLogDate());
		attendance.setEndKm(attendanceInputDTO.getEndKm());
		attendance.setStartKm(attendanceInputDTO.getStartKm());
		attendance.setTotalKm(attendanceInputDTO.getEndKm() - attendanceInputDTO.getStartKm());
		attendance.setTableNo(attendanceInputDTO.getTableNo());
		Van van = vanDAO.findByNumber(attendanceInputDTO.getVanNumber());
		attendance.setVan(van);
		Shift shift = shiftDAO.findByName(attendanceInputDTO.getShiftName());
		attendance.setShift(shift);
		attendance.setDisel(attendanceInputDTO.getDisel());
		User user = userDAO.findByMobileNumber(attendanceInputDTO.getMobileNumber());
		attendance.setUser(user);
		attendance.setComment(attendance.getComment());
		Vendor vendor = vendorDAO.findByName(attendanceInputDTO.getVendorName());
		attendance.setVendor(vendor);
		attendance = attendanceDAO.save(attendance);
		return createResponseDTO(attendance);
	}

	@Override
	public List<AttendanceOutputDTO> retrieveAll(Date fromDate, Date toDate, String mobileNumber) {

		List<Attendance> attendances = null;

		if (fromDate != null && toDate == null) {
			attendances = attendanceDAO.findByLogDate(fromDate);
		} else if (fromDate != null && toDate != null) {
			attendances = attendanceDAO.findByLogDateBetween(fromDate, toDate);
		}

		if (fromDate != null && toDate != null && mobileNumber != null) {
			attendances = attendanceDAO.findByUserMobileNumberAndLogDateBetween(mobileNumber, fromDate, toDate);
		} else if (fromDate != null && mobileNumber != null) {
			attendances = attendanceDAO.findByUserMobileNumberAndLogDate(mobileNumber, fromDate);
		} else if (mobileNumber != null) {
			attendances = attendanceDAO.findByUserMobileNumber(mobileNumber);
		} else if (fromDate == null && toDate == null && mobileNumber == null) {
			attendances = attendanceDAO.findAll();
		}

		List<AttendanceOutputDTO> responseDTOs = new ArrayList<>();
		for (Attendance attendance : attendances) {
			responseDTOs.add(createResponseDTO(attendance));
		}
		return responseDTOs;
	}

	@Override
	public AttendanceOutputDTO retrieveById(Integer id) {
		Optional<Attendance> attendance = attendanceDAO.findById(id);
		if (attendance.isEmpty()) {
			throw new NullException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		return createResponseDTO(attendance.get());
	}

	@Override
	public List<AttendanceOutputDTO> retrieveByMobileNumber(String mobileNumber) {

		User user = userDAO.findByMobileNumber(mobileNumber);

		List<Attendance> attendances = attendanceDAO.findByUserId(user.getId());

		List<AttendanceOutputDTO> responseDTOs = new ArrayList<>();
		for (Attendance attendance : attendances) {
			responseDTOs.add(createResponseDTO(attendance));
		}
		return responseDTOs;
	}

	@Override
	public AttendanceOutputDTO update(AttendanceInputDTO attendanceInputDTO, Integer id) {
		Optional<Attendance> attendance = attendanceDAO.findById(id);
		if (attendance.isEmpty()) {
			throw new NullException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		Attendance attendancePresent = attendance.get();
		attendancePresent.setLogDate(attendanceInputDTO.getLogDate());
		attendancePresent.setEndKm(attendanceInputDTO.getEndKm());
		attendancePresent.setStartKm(attendanceInputDTO.getStartKm());
		attendancePresent.setTableNo(attendanceInputDTO.getTableNo());
		attendancePresent.setTotalKm(attendanceInputDTO.getEndKm() - attendanceInputDTO.getStartKm());
		Van van = vanDAO.findByNumber(attendanceInputDTO.getVanNumber());
		attendancePresent.setVan(van);
		Shift shift = shiftDAO.findByName(attendanceInputDTO.getShiftName());
		attendancePresent.setShift(shift);
		attendancePresent.setDisel(attendanceInputDTO.getDisel());
		User user = userDAO.findByMobileNumber(attendanceInputDTO.getMobileNumber());
		attendancePresent.setUser(user);
		attendancePresent = attendanceDAO.save(attendancePresent);
		return createResponseDTO(attendancePresent);
	}

	private AttendanceOutputDTO createResponseDTO(Attendance attendance) {
		AttendanceOutputDTO outputDTO = new AttendanceOutputDTO();
		outputDTO.setId(attendance.getId());
		outputDTO.setEndKm(attendance.getEndKm());
		outputDTO.setStartKm(attendance.getStartKm());
		outputDTO.setTableNo(attendance.getTableNo());
		outputDTO.setShiftOutputDTO(createShiftResponseDTO(attendance.getShift()));
		outputDTO.setVanOutputDTO(createVanResponseDTO(attendance.getVan()));
		outputDTO.setDisel(attendance.getDisel());
		outputDTO.setLogDate(attendance.getLogDate());
		outputDTO.setUserOutupDTO(createUserResponseDTO(attendance.getUser()));
		outputDTO.setTotalKm(attendance.getTotalKm());
		outputDTO.setComment(attendance.getComment());
		outputDTO.setVendorOutputDTO(createVendorResponseDTO(attendance.getVendor()));
		return outputDTO;
	}

	private VendorOutputDTO createVendorResponseDTO(Vendor vendor) {
		VendorOutputDTO outputDTO = new VendorOutputDTO();
		outputDTO.setId(vendor.getId());
		outputDTO.setName(vendor.getName());
		return outputDTO;
	}
	private VanOutputDTO createVanResponseDTO(Van van) {
		VanOutputDTO outputDTO = new VanOutputDTO();
		outputDTO.setId(van.getId());
		outputDTO.setNumber(van.getNumber());
		return outputDTO;
	}

	private ShiftOutputDTO createShiftResponseDTO(Shift shift) {
		ShiftOutputDTO outputDTO = new ShiftOutputDTO();
		outputDTO.setId(shift.getId());
		outputDTO.setName(shift.getName());
		outputDTO.setStartTime(shift.getStartTime());
		outputDTO.setEndTime(shift.getEndTime());
		return outputDTO;
	}

	private UserDto createUserResponseDTO(User user) {
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
		return outputDTO;
	}

}
