package com.enterprise.attendance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.attendance.model.Van;

public interface VanDAO extends JpaRepository<Van, Integer> {

	Van findByNumber(String vanNumber);

}
