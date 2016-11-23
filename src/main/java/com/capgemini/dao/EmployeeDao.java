package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.DepartmentEntity;
import com.capgemini.domain.EmployeeEntity;

public interface EmployeeDao extends Dao<EmployeeEntity, Long> {

	List<EmployeeEntity> findEmployeeByPesel(String email);

	List<EmployeeEntity> findEmployeeByName(String name);
	
	List<EmployeeEntity> findEmployeeBySurname(String surname);
	
	List<EmployeeEntity> findEmployeeByNameAndSurname(String name, String surname);

	boolean isPeselInDatabase(String pesel);

	List<EmployeeEntity> findEmployeeByDepartment(String dName);

	void assignDepartmentToAnEmployee(DepartmentEntity department, EmployeeEntity employee);

	void deleteEmployee(EmployeeEntity employee);

	List<EmployeeEntity> findEmployeeByDepartmentId(Long id);
			
}
