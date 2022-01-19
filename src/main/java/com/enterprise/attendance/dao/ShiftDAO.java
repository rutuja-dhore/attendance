package com.enterprise.attendance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.attendance.model.Shift;

public interface ShiftDAO extends JpaRepository<Shift, Integer> {

	Shift findByName(String shiftName);

}

