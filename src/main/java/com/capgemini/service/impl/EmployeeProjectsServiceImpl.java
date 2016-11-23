package com.capgemini.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.EmployeeProjectsDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeProjectsEntity;
import com.capgemini.service.EmployeeProjectsService;

@Service
@Transactional
public class EmployeeProjectsServiceImpl implements EmployeeProjectsService {

	@Autowired
	private EmployeeProjectsDao employeeProjectsDao;

	@Override
	public List<EmployeeProjectsEntity> findProjectAssignmentsByRole(String role) {
		List<EmployeeProjectsEntity> employeeProjects = employeeProjectsDao.findProjectAssignmentsByRole(role);
		return employeeProjects;
	}

	@Override
	public List<EmployeeProjectsEntity> findAllProjectsWithAssignedEmployees() {
		List<EmployeeProjectsEntity> employeeProjects = employeeProjectsDao.findAllProjectsWithAssignedEmployees();
		return employeeProjects;
	}

	@Override
	public void addEmployeeProjectAssignment(EmployeeProjectsEntity epEntity) {
		employeeProjectsDao.save(epEntity);
	}

	@Override
	public List<EmployeeEntity> findEmployeesInProjectsByProjectAndPeriod (String pName, int noOfMonths) {
		List<EmployeeEntity> employees = employeeProjectsDao.findEmployeesInProjectsByProjectAndPeriod(pName, noOfMonths);
		return employees;
}

}