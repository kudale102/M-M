package com.example.sbjpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sbjpa.entity.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer>{
	
	List<Lead> findByMobileNumber(String mobileNo);
	
	
}
