package com.cg.onlinepizza.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.onlinepizza.model.Admin;

public interface adminRepository extends JpaRepository<Admin,String> {

	
	
}
