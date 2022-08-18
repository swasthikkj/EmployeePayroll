package com.bridgelabz.payrollservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bridgelabz.payrollservice.dto.EmployeeDTO;

import lombok.Data;

@Entity
@Table(name = "Employee")
@Data
public class EmployeeModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long employeeId;
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private long salary;
	private String companyName;
	@OneToOne
	private DepartmentModel department;
    private String emailId;
    private String password;
    private String gender;
    private String note;
    private String profilePic;

	public EmployeeModel(EmployeeDTO dto) {
		this.firstName = dto.getFirstName();
		this.lastName = dto.getLastName();
		this.phoneNumber = dto.getPhoneNumber();
		this.salary= dto.getSalary();
		this.companyName = dto.getCompanyName();
		this.emailId = dto.getEmailId();
		this.password = dto.getPassword();
		this.gender = dto.getGender();
		this.note = dto.getNote();
		this.profilePic = dto.getProfilePic();
	}

	public EmployeeModel() {

	}
}
