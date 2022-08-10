package com.bridgelabz.payrollservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.payrollservice.dto.EmployeeDTO;
import com.bridgelabz.payrollservice.exception.EmployeeNotFoundException;
import com.bridgelabz.payrollservice.model.EmployeeModel;
import com.bridgelabz.payrollservice.repository.EmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public EmployeeModel addEmployee(EmployeeDTO employeeDTO) {
		EmployeeModel model = new EmployeeModel(employeeDTO);
		model.setRegisteredDate(LocalDateTime.now());
		employeeRepository.save(model);
		return model;
	}

	@Override
	public EmployeeModel updateEmployeeById(EmployeeDTO employeeDTO, long id) {
		Optional<EmployeeModel> isEmployeePresent = employeeRepository.findById(id);
		if(isEmployeePresent.isPresent()) {
			isEmployeePresent.get().setFirstName(employeeDTO.getFirstName());
			isEmployeePresent.get().setLastName(employeeDTO.getLastName());
			isEmployeePresent.get().setCompanyName(employeeDTO.getCompanyName());
			isEmployeePresent.get().setDepartment(employeeDTO.getDepartment());
			isEmployeePresent.get().setPhoneNumber(employeeDTO.getPhoneNumber());
			isEmployeePresent.get().setSalary(employeeDTO.getSalary());
			isEmployeePresent.get().setUpdatedDate(LocalDateTime.now());
			employeeRepository.save(isEmployeePresent.get());
			return isEmployeePresent.get();
		}
		throw new EmployeeNotFoundException(400,"Employee Not Present!!");
	}
	
	@Override
    public List<EmployeeModel> getEmpData() {
        List<EmployeeModel> getallemployee=employeeRepository.findAll();
        if(getallemployee.size()>0)
            return getallemployee;
        else
            throw new EmployeeNotFoundException(400,"No DATA Present");
    }

    @Override
    public EmployeeModel deleteEmployee(Long id) {
        Optional<EmployeeModel> isEmployeePresent=employeeRepository.findById(id);
        if(isEmployeePresent.isPresent()){
            employeeRepository.delete(isEmployeePresent.get());
            return isEmployeePresent.get();
        }
        throw new EmployeeNotFoundException(400,"Employee Not Present");
    }
}
