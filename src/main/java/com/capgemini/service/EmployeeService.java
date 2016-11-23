package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.exceptions.WrongDepartmentIdException;
import com.capgemini.exceptions.WrongParameterException;

public interface EmployeeService {

	EmployeeEntity addAnEmploye(EmployeeEntity employee);

	void deleteAnEmployee(EmployeeEntity employee);

	EmployeeEntity updateAnEmployee(EmployeeEntity employee);

	EmployeeEntity assignDepartmentToAnEmployee(Long employeeId, Long departmentId)
			throws WrongParameterException, WrongDepartmentIdException;

	List<EmployeeEntity> findEmployeeByNameAndSurname(String name, String surname);

	List<EmployeeEntity> findEmployeeByDepartment(Long id) throws WrongDepartmentIdException;

	List<EmployeeEntity> findAllEmployees();

	EmployeeEntity findEmployee(Long employeeId) throws WrongParameterException;

	List<EmployeeEntity> findEmployeeByProjectAndNumberOfMonths(String pName, int noOfMonths);
}
