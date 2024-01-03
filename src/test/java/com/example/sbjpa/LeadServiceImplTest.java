package com.example.sbjpa;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.sbjpa.dao.LeadRepository;
import com.example.sbjpa.entity.Lead;
import com.example.sbjpa.exceptionHandler.LeadAlreadyExistsException;
import com.example.sbjpa.exceptionHandler.NoLeadFoundException;
import com.example.sbjpa.service.LeadServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LeadServiceImplTest {

    @Mock
    private LeadRepository leadRepository;

    @InjectMocks
    private LeadServiceImpl leadService;

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
        
        when(leadRepository.existsById(mockLead.getLeadId())).thenReturn(false);
        when(leadRepository.save(mockLead)).thenReturn(mockLead);

        Lead savedLead = leadService.saveLead(mockLead);

        verify(leadRepository, times(1)).existsById(mockLead.getLeadId());
        verify(leadRepository, times(1)).save(mockLead);

        assertEquals(mockLead, savedLead);
    }

    @Test(expected = LeadAlreadyExistsException.class)
    public void testSaveLead_DuplicateLead() {
    	Lead mockLead = new Lead();
        mockLead.setLeadId(123);
        mockLead.setFirstName("Aditya");
        mockLead.setLastName("Kudale");
        mockLead.setGender("Male");
        mockLead.setDob("21/08/1995");
        mockLead.setEmail("a@gmail.com");
        mockLead.setMobileNumber("9815165151");

        when(leadRepository.existsById(mockLead.getLeadId())).thenReturn(true);

        leadService.saveLead(mockLead);

        verify(leadRepository, times(1)).existsById(mockLead.getLeadId());
        verify(leadRepository, never()).save(any(Lead.class));
    }

    @Test
    public void testFindLeadByMobile_Success() {
        String mobileNumber = "1234567890";
        Lead mockLead = new Lead();
        mockLead.setLeadId(123);
        mockLead.setFirstName("Aditya");
        mockLead.setLastName("Kudale");
        mockLead.setGender("Male");
        mockLead.setDob("21/08/1995");
        mockLead.setEmail("a@gmail.com");
        mockLead.setMobileNumber(mobileNumber);
        List<Lead> leads = Arrays.asList(mockLead);

        when(leadRepository.findByMobileNumber(mobileNumber)).thenReturn(leads);

        List<Lead> resultLeads = leadService.findLeadbyMobile(mobileNumber);

        verify(leadRepository, times(1)).findByMobileNumber(mobileNumber);

        assertEquals(leads, resultLeads);
    }

    @Test(expected = NoLeadFoundException.class)
    public void testFindLeadByMobile_NoLeadsFound() {
        String mobileNumber = "1234567890";

        when(leadRepository.findByMobileNumber(mobileNumber)).thenReturn(Collections.emptyList());

        leadService.findLeadbyMobile(mobileNumber);

        verify(leadRepository, times(1)).findByMobileNumber(mobileNumber);
    }
}
