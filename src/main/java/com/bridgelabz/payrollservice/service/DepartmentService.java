package com.bridgelabz.payrollservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.payrollservice.dto.DepartmentDTO;
import com.bridgelabz.payrollservice.exception.EmployeeNotFoundException;
import com.bridgelabz.payrollservice.model.DepartmentModel;
import com.bridgelabz.payrollservice.repository.DepartmentRepository;

@Service
public class DepartmentService implements IDepartmentService {
	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public DepartmentModel addDepartment(DepartmentDTO departmentdto) {
		DepartmentModel model = new DepartmentModel(departmentdto);
		model.setCreatedTime(LocalDateTime.now());
		departmentRepository.save(model);
		return model;
	}

	@Override
	public DepartmentModel updateDepartment(DepartmentDTO departmentDTO, Long id) {
		Optional<DepartmentModel> isIdPresent = departmentRepository.findById(id);
		if (isIdPresent.isPresent()) {
			isIdPresent.get().setDepartmentName(departmentDTO.getDepartmentName());
			isIdPresent.get().setDepartmentDescription(departmentDTO.getDepartmentDescription());
			isIdPresent.get().setUpdatedTime(LocalDateTime.now());
			departmentRepository.save(isIdPresent.get());
			return isIdPresent.get();
		} else {
			throw new EmployeeNotFoundException(400, "Department is not found with this ID");
		}
	}

	public List<DepartmentModel> getAllDepartments() {
		List<DepartmentModel> isDepartment = departmentRepository.findAll();
		if (isDepartment.size() > 0) {
			return isDepartment;
		} else {
			throw new EmployeeNotFoundException(400, "No Departments Found");
		}
	}

	@Override
	public DepartmentModel deleteDepartment(Long id) {
		Optional<DepartmentModel> isIdPresent = departmentRepository.findById(id);
		if (isIdPresent.isPresent()) {
			departmentRepository.delete(isIdPresent.get());
			return isIdPresent.get();
		} else {
			throw new EmployeeNotFoundException(400, "Department is not found");
		}
	}
}
