package com.bridgelabz.payrollservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.payrollservice.dto.DepartmentDTO;
import com.bridgelabz.payrollservice.model.DepartmentModel;
import com.bridgelabz.payrollservice.service.IDepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	IDepartmentService departmentService;

	@PostMapping("/adddepartment")
	public DepartmentModel addDepartment(DepartmentDTO departmentdto) {
		return departmentService.addDepartment(departmentdto);		
	}

	@PutMapping("/updateDepartment/{id}")
	public DepartmentModel updateDepartment(@RequestBody DepartmentDTO departmentDTO,@PathVariable Long id){
		return departmentService.updateDepartment(departmentDTO,id);
	}

	@GetMapping("/getAllDepartments")
	public List<DepartmentModel> getAllDepartments(){
		return departmentService.getAllDepartments();
	}
	
	@DeleteMapping("/deleteDepartment/{id}")
	public DepartmentModel deleteDepartment(@PathVariable Long id){
		return departmentService.deleteDepartment(id);
	}
}
