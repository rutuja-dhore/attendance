package com.enterprise.attendance.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.attendance.model.Attendance;

public interface AttendanceDAO extends JpaRepository<Attendance, Integer> {

	List<Attendance> findByUserId(Long id);

	List<Attendance> findByLogDate(Date fromDate);

	List<Attendance> findByLogDateBetween(Date fromDate, Date toDate);

	List<Attendance> findByUserMobileNumberAndLogDateBetween(String mobileNumber, Date fromDate, Date toDate);

	List<Attendance> findByUserMobileNumber(String mobileNumber);

	List<Attendance> findByUserMobileNumberAndLogDate(String mobileNumber, Date fromDate);

}



