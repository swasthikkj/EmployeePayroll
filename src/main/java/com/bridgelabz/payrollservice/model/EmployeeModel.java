package com.bridgelabz.payrollservice.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.payrollservice.dto.EmployeeDTO;

import lombok.Data;

@Entity
@Table(name = "Employee")
@Data
public class EmployeeModel {
	@Id
	private long employeeId;
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private long salary;
	private String companyName;
	private String department;
	private LocalDateTime registeredDate;
    private LocalDateTime updatedDate;
    private String emailId;
    private String password;

	public EmployeeModel(EmployeeDTO dto) {
		this.firstName = dto.getFirstName();
		this.lastName = dto.getLastName();
		this.phoneNumber = dto.getPhoneNumber();
		this.salary= dto.getSalary();
		this.companyName = dto.getCompanyName();
		this.department = dto.getDepartment();
		this.emailId = dto.getEmailId();
		this.password = dto.getPassword();
	}

	public EmployeeModel() {

	}
}
