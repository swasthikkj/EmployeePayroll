package com.bridgelabz.payrollservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.payrollservice.dto.DepartmentDTO;
import com.bridgelabz.payrollservice.model.DepartmentModel;

@Service
public interface IDepartmentService {
	DepartmentModel addDepartment(DepartmentDTO departmentdto);

	DepartmentModel updateDepartment(DepartmentDTO departmentDTO, Long id);

	List<DepartmentModel> getAllDepartments();

	DepartmentModel deleteDepartment(Long id);
}
