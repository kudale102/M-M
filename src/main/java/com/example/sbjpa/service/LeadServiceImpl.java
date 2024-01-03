package com.example.sbjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sbjpa.dao.LeadRepository;
import com.example.sbjpa.entity.Lead;
import com.example.sbjpa.exceptionHandler.LeadAlreadyExistsException;
import com.example.sbjpa.exceptionHandler.NoLeadFoundException;

@Service
public class LeadServiceImpl implements LeadService{

	@Autowired
	private LeadRepository leadRepository;
	
	@Override
	public Lead saveLead(Lead lead) {

		if(leadRepository.existsById(lead.getLeadId()))
		{
			throw new LeadAlreadyExistsException("Lead already exists with leadId: " + lead.getLeadId());
		}
		
		return leadRepository.save(lead);
		
	}

	@Override
	public List<Lead> findLeadbyMobile(String mobileNo) {

		List<Lead> leads = leadRepository.findByMobileNumber(mobileNo);
		if(leads.size() == 0 )
		{
			throw new NoLeadFoundException("No Lead found with the Mobile Number: "+mobileNo);
		}
		
		return leads;
	}

}
