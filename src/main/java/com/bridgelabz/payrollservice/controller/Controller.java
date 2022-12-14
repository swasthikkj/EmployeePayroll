package com.bridgelabz.payrollservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.payrollservice.dto.EmployeeDTO;
import com.bridgelabz.payrollservice.model.EmployeeModel;
import com.bridgelabz.payrollservice.service.IEmployeeService;
import com.bridgelabz.payrollservice.util.Response;

@RestController
@RequestMapping("/employeepayroll")
public class Controller {
	@Autowired
	IEmployeeService employeeService;
	
	@PostMapping("/addemployee")
	public EmployeeModel addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, @RequestParam Long departmentId) {
		return employeeService.addEmployee(employeeDTO, departmentId);
	}
	
	@PutMapping("/updateemployee/{id}")
	public EmployeeModel updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable long id, @RequestHeader String token, @RequestParam Long departmentId) {
		return employeeService.updateEmployeeById(employeeDTO, token, departmentId);
	}
	
	@GetMapping("getEmployeedata")
	public List<EmployeeModel> getallemployee(@RequestHeader String token) {
        return employeeService.getEmpData(token);
    }

    @DeleteMapping("deleteemployee/{id}")
    public EmployeeModel deleteemployee(@PathVariable Long id, String token) {
        return employeeService.deleteEmployee(id, token);
    }
    
    @PostMapping("login")
    public Response login(@RequestParam String email,@RequestParam String password) {
        return employeeService.login(email, password);
    }
}

