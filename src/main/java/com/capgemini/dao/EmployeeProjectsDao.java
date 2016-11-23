package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeProjectsEntity;

public interface EmployeeProjectsDao extends Dao<EmployeeProjectsEntity, Long> {


	List<EmployeeEntity> findEmployeesInProjectsByProjectAndPeriod (String pName, int noOfMonths);

	List<EmployeeEntity> findCurrentEmployeesInTheProject(String pName);

	List<EmployeeProjectsEntity> findProjectAssignmentsByRole(String role);

	List<EmployeeProjectsEntity> findAllProjectsWithAssignedEmployees();



}
