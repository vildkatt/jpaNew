package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeProjectsEntity;

public interface EmployeeProjectsService {

	void addEmployeeProjectAssignment(EmployeeProjectsEntity epEntity);

	List<EmployeeProjectsEntity> findProjectAssignmentsByRole(String role);

	List<EmployeeProjectsEntity> findAllProjectsWithAssignedEmployees();

	List<EmployeeEntity> findEmployeesInProjectsByProjectAndPeriod(String pName, int noOfMonths);

}
