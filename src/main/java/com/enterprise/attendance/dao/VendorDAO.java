package com.enterprise.attendance.dao;

import com.enterprise.attendance.model.Van;
import com.enterprise.attendance.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorDAO extends JpaRepository<Vendor, Integer> {

	Vendor findByName(String name);

}
