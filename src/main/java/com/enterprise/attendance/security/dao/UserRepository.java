package com.enterprise.attendance.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enterprise.attendance.security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Override
	void delete(User user);
	

	User findByMobileNumber(String mobileNumber);

}