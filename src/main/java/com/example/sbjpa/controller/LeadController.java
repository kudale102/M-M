package com.example.sbjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sbjpa.entity.Reponse;
import com.example.sbjpa.entity.ErrorResponse;
import com.example.sbjpa.entity.Lead;
import com.example.sbjpa.service.LeadService;

@RestController
@RequestMapping("/api")
public class LeadController {
	
	@Autowired
	private LeadService leadService;
	
	@PostMapping("/leads")
	public ResponseEntity<Object> saveLead(@Validated @RequestBody Lead lead, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			ErrorResponse errorResponse = new ErrorResponse("E10001",bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toArray(String[]::new));
            Reponse apiResponse = new Reponse("error", errorResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}
		leadService.saveLead(lead);
		Reponse response = new Reponse("success", "Created Leads Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	
	@GetMapping("/getLeadsbyMobile/{mobile}")
	public ResponseEntity<Object> findLeadByMobile(@PathVariable("mobile") String mobileNo)
	{
		List<Lead> leads = leadService.findLeadbyMobile(mobileNo);
		return new ResponseEntity<Object>(leads, HttpStatus.OK);
	}
	
}
