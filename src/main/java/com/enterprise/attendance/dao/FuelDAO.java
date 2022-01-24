package com.enterprise.attendance.dao;

import com.enterprise.attendance.model.Fuel;
import com.enterprise.attendance.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuelDAO extends JpaRepository<Fuel, Integer> {

	List<Fuel> findByVanId(Integer id);
}

