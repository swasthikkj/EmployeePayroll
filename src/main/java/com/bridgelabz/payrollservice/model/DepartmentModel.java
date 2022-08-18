package com.bridgelabz.payrollservice.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.payrollservice.dto.DepartmentDTO;

import lombok.Data;

@Entity
@Table(name = "department")
@Data
public class DepartmentModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long departmentId;
	private String departmentName;
	private String departmentDescription;
	private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    
    public DepartmentModel() {
    	
    }
	
	public DepartmentModel(DepartmentDTO departmentdto) {
		this.departmentId = departmentdto.getDepartmentId();
		this.departmentName = departmentdto.getDepartmentName();
		this.departmentDescription = departmentdto.getDepartmentDescription();
		this.createdTime = departmentdto.getCreatedTime().now();
		this.updatedTime = departmentdto.getUpdatedTime().now();
	}	
}
