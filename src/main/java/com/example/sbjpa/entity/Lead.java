package com.example.sbjpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "leads")
public class Lead {
	
	@Id
	private int leadId;

	@NotBlank(message = "First name is mandatory")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "First name should contain only alphabets")
	private String firstName;
	

	@Pattern(regexp = "^[a-zA-Z]*$", message = "Middle name should contain only alphabets")
	private String middleName;

	@NotBlank(message = "Last name is mandatory")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should contain only alphabets")
	private String lastName;

	@NotBlank(message = "Mobile number is mandatory")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
	private String mobileNumber;

	@NotBlank(message = "Gender is mandatory")
	@Pattern(regexp = "^(Male|Female|Others)$", message = "Invalid gender")
	@JsonProperty(value = "Gender")
	private String gender;

	@NotBlank(message = "Date of birth is mandatory")
	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Invalid date of birth")
	@JsonProperty(value = "DOB")
	private String dob;

	@Email(message = "Invalid email address")
	private String email;
    
	public int getLeadId() {
		return leadId;
	}
	public void setLeadId(int leadId) {
		this.leadId = leadId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	} 
    
    
}
