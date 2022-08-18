package com.bridgelabz.payrollservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.payrollservice.model.DepartmentModel;
@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentModel, Long>{

}
