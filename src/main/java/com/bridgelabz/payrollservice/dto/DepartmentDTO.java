package com.bridgelabz.payrollservice.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DepartmentDTO {
	private Long departmentId;
	private String departmentName;
	private String departmentDescription;
	private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
