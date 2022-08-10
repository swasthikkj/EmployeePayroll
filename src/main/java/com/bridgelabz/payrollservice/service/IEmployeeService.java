package com.bridgelabz.payrollservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.payrollservice.dto.EmployeeDTO;
import com.bridgelabz.payrollservice.model.EmployeeModel;
@Service
public interface IEmployeeService {

	EmployeeModel addEmployee(EmployeeDTO employeeDTO);

	EmployeeModel updateEmployeeById(EmployeeDTO employeeDTO, long id);
	
	List<EmployeeModel> getEmpData();

    EmployeeModel deleteEmployee(Long id);
}
