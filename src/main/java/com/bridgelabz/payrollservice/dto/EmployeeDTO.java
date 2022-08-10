package com.bridgelabz.payrollservice.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private long salary;
	private String companyName;
	private String department;
	private String emailId;
    private String password;
}
