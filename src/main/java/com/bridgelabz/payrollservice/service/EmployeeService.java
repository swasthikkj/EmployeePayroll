package com.bridgelabz.payrollservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.payrollservice.dto.EmployeeDTO;
import com.bridgelabz.payrollservice.exception.EmployeeNotFoundException;
import com.bridgelabz.payrollservice.model.DepartmentModel;
import com.bridgelabz.payrollservice.model.EmployeeModel;
import com.bridgelabz.payrollservice.repository.DepartmentRepository;
import com.bridgelabz.payrollservice.repository.EmployeeRepository;
import com.bridgelabz.payrollservice.util.Response;
import com.bridgelabz.payrollservice.util.TokenUtil;

@Service
public class EmployeeService implements IEmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	MailService mailService;

	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public EmployeeModel addEmployee(EmployeeDTO employeeDTO, Long departmentId) {
		Optional<DepartmentModel> isDepartment = departmentRepository.findById(departmentId);
		if (isDepartment.isPresent()){
			EmployeeModel employeeModel = new EmployeeModel(employeeDTO);
			employeeModel.setDepartment(isDepartment.get());
			employeeRepository.save(employeeModel);
			String body="Employee is added succesfully with employeeId "+employeeModel.getEmployeeId();
			String subject="Employee Registration Succesfull";
			mailService.send(employeeModel.getEmailId(),subject,body);
			return employeeModel;
		}
		throw  new EmployeeNotFoundException(400,"Employee Not Present");
	}

	@Override
	public EmployeeModel updateEmployeeById(EmployeeDTO employeeDTO, String token, long departmentId) {
		Long empId = tokenUtil.decodeToken(token);
		Optional<DepartmentModel> isDepartment = departmentRepository.findById(departmentId);
		if (isDepartment.isPresent()) {
			Optional<EmployeeModel> isEmployeePresent = employeeRepository.findById(empId);
			if (isEmployeePresent.isPresent()) {
				isEmployeePresent.get().setFirstName(employeeDTO.getFirstName());
				isEmployeePresent.get().setLastName(employeeDTO.getLastName());
				isEmployeePresent.get().setPhoneNumber(employeeDTO.getPhoneNumber());
				isEmployeePresent.get().setSalary(employeeDTO.getSalary());
				isEmployeePresent.get().setDepartment(isDepartment.get());
				isEmployeePresent.get().setCompanyName(employeeDTO.getCompanyName());
				isEmployeePresent.get().setGender(employeeDTO.getGender());
				isEmployeePresent.get().setNote(employeeDTO.getNote());
				isEmployeePresent.get().setProfilePic(employeeDTO.getProfilePic());
				employeeRepository.save(isEmployeePresent.get());
				String body="Employee is added succesfully with employeeId "+isEmployeePresent.get().getEmployeeId();
				String subject="Employee Registration Succesfull";
				mailService.send(employeeDTO.getEmailId(),subject,body);
				return isEmployeePresent.get();
			}
			throw new EmployeeNotFoundException(400, "Employee Not Present");
		}
		throw new EmployeeNotFoundException(400, "Employee Not Present");
	}

	@Override
	public List<EmployeeModel> getEmpData(String token) {
		Long empId = tokenUtil.decodeToken(token);
		Optional<EmployeeModel> isEmployeePresent = employeeRepository.findById(empId);
		if(isEmployeePresent.isPresent()) {
			List<EmployeeModel> getAllEmployee = employeeRepository.findAll();
			if (getAllEmployee.size() > 0)
				return getAllEmployee;
			else
				throw new EmployeeNotFoundException(400, "No DATA Present");
		}
		throw new EmployeeNotFoundException(400,"Employee Not found");
	}

	@Override
	public EmployeeModel deleteEmployee(Long id, String token) { 
		Long empId = tokenUtil.decodeToken(token);
		Optional<EmployeeModel> isEmployeePresent = employeeRepository.findById(id);
		if(isEmployeePresent.isPresent()){
			employeeRepository.delete(isEmployeePresent.get());
			String body = "Employee Deleted Successfully with Employee id is :" + isEmployeePresent.get().getEmployeeId();
			String subject = "Employee Deleted..";
			mailService.send(isEmployeePresent.get().getEmailId(), body, subject);
			return isEmployeePresent.get();
		}
		throw  new EmployeeNotFoundException(400,"Employee Not Present");
	}


	@Override
	public Response login(String email, String password) {
		Optional<EmployeeModel> isEmailPresent = employeeRepository.findByEmailId(email);
		if(isEmailPresent.isPresent()){
			if(isEmailPresent.get().getPassword().equals(password)){
				String token = tokenUtil.createToken(isEmailPresent.get().getEmployeeId());
				return new Response("login successfull", 200, token);
			}
			throw new EmployeeNotFoundException(400,"Invalid credentials");
		}
		throw new EmployeeNotFoundException(400,"Employee not found");
	}
}
