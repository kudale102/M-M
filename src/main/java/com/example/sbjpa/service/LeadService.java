package com.example.sbjpa.service;

import java.util.List;

import com.example.sbjpa.entity.Lead;

public interface LeadService {
	
	public Lead saveLead(Lead lead);

	public List<Lead> findLeadbyMobile(String mobileNo);

}
