package com.bridgelabz.payrollservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;

import com.bridgelabz.payrollservice.model.EmployeeModel;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {
	Optional<EmployeeModel> findByEmailId(String email);
}
