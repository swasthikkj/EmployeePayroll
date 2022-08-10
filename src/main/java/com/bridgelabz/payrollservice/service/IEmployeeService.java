package com.bridgelabz.payrollservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.payrollservice.dto.EmployeeDTO;
import com.bridgelabz.payrollservice.model.EmployeeModel;
import com.bridgelabz.payrollservice.util.Response;
@Service
public interface IEmployeeService {

	EmployeeModel addEmployee(EmployeeDTO employeeDTO);

	EmployeeModel updateEmployeeById(EmployeeDTO employeeDTO, long id);
	
	List<EmployeeModel> getEmpData(String token);

    EmployeeModel deleteEmployee(Long id);

	Response login(String email, String password);
}
