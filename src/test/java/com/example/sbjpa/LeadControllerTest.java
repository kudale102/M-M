package com.example.sbjpa;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.sbjpa.controller.LeadController;
import com.example.sbjpa.entity.Lead;
import com.example.sbjpa.service.LeadService;
import com.example.sbjpa.entity.Reponse;

@RunWith(MockitoJUnitRunner.class)
public class LeadControllerTest {

    @Mock
    private LeadService leadService;

    @InjectMocks
    private LeadController leadController;

    @Test
    public void testSaveLead_Success() {
        Lead mockLead = new Lead();
        mockLead.setLeadId(123);
        mockLead.setFirstName("Aditya");
        mockLead.setLastName("Kudale");
        mockLead.setGender("Male");
        mockLead.setDob("21/08/1995");
        mockLead.setEmail("a@gmail.com");
        mockLead.setMobileNumber("9815165151");
        
        BindingResult result = mock(BindingResult.class);

        ResponseEntity<Object> responseEntity = leadController.saveLead(mockLead, result);

        verify(leadService, times(1)).saveLead(any(Lead.class));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("success", ((Reponse) responseEntity.getBody()).getStatus());
    }

    @Test
    public void testSaveLead_ValidationError() {
        Lead mockLead = new Lead();
        mockLead.setLeadId(123);
        mockLead.setFirstName("");
        mockLead.setLastName("Kudale");
        mockLead.setGender("Male");
        mockLead.setDob("21/08/1995");
        mockLead.setEmail("a@gmail.com");
        mockLead.setMobileNumber("9815165151");
        
        BindingResult result = new BeanPropertyBindingResult(mockLead, "lead");
        result.addError(new FieldError("lead", "firstName", "First name is required"));

        ResponseEntity<Object> responseEntity = leadController.saveLead(mockLead, result);

        verify(leadService, never()).saveLead(any(Lead.class));

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("error", ((Reponse) responseEntity.getBody()).getStatus());
    }

    @Test
    public void testFindLeadByMobile_Success() {
        List<Lead> mockLeads = Arrays.asList(new Lead());

        when(leadService.findLeadbyMobile(anyString())).thenReturn(mockLeads);

        ResponseEntity<Object> responseEntity = leadController.findLeadByMobile("1234567890");

        verify(leadService, times(1)).findLeadbyMobile(anyString());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockLeads, responseEntity.getBody());
    }

}
