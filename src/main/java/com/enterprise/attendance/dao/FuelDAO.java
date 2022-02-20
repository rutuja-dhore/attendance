package com.enterprise.attendance.dao;

import com.enterprise.attendance.model.Attendance;
import com.enterprise.attendance.model.Fuel;
import com.enterprise.attendance.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FuelDAO extends JpaRepository<Fuel, Integer> {

	List<Fuel> findByVanId(Integer id);

	List<Fuel> findByLogDate(Date fromDate);

	List<Fuel> findByLogDateBetween(Date fromDate, Date toDate);

	List<Fuel> findByVanNumberAndLogDateBetween(String vanNumber, Date fromDate, Date toDate);

	List<Fuel> findByVanNumber(String vanNumber);

	List<Fuel> findByVanNumberAndLogDate(String vanNumber, Date fromDate);


}

