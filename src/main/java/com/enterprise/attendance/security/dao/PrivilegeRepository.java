package com.enterprise.attendance.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enterprise.attendance.security.model.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

	Privilege findByName(String name);

}
