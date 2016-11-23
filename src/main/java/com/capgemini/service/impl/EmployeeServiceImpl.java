package com.capgemini.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.DepartmentDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.EmployeeProjectsDao;
import com.capgemini.domain.DepartmentEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.exceptions.WrongParameterException;
import com.capgemini.exceptions.WrongDepartmentIdException;
import com.capgemini.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{


		@Autowired
		private EmployeeDao employeeDao;

		@Autowired
		private DepartmentDao departmentDao;
		
		@Autowired
		private EmployeeProjectsDao employeeProjectsDao;

		@Override
		@Transactional
		public EmployeeEntity addAnEmploye(EmployeeEntity employee) {
			employee = employeeDao.save(employee);
			return employee;
		}

		@Override
		@Transactional
		public void deleteAnEmployee(EmployeeEntity employee) {
			employeeDao.delete(employee);
		}

		@Override
		@Transactional
		public EmployeeEntity updateAnEmployee(EmployeeEntity employee) {
			return employeeDao.update(employee);
		}

		@Override
		@Transactional
		public EmployeeEntity assignDepartmentToAnEmployee (Long employeeId, Long departmentId) throws WrongParameterException, WrongDepartmentIdException {
			if (departmentId == null || departmentId < 1) {
				throw new WrongDepartmentIdException();
			}
			EmployeeEntity employee = findEmployee(employeeId);
			DepartmentEntity department = departmentDao.findOne(departmentId);
			employee.setDepartment(department);
			return updateAnEmployee(employee);
		}

		@Override
		public List<EmployeeEntity> findEmployeeByNameAndSurname(String name, String surname) {

			return employeeDao.findEmployeeByNameAndSurname(name, surname);
		}

		@Override
		public List<EmployeeEntity> findEmployeeByDepartment(Long id) throws WrongDepartmentIdException {
			if (id == null || id < 1) {
				throw new WrongDepartmentIdException();
			}
			return employeeDao.findEmployeeByDepartmentId(id);
		}

		@Override
		public List<EmployeeEntity> findAllEmployees() {
			return employeeDao.findAll();
		}

		@Override
		public EmployeeEntity findEmployee(Long employeeId) throws WrongParameterException {
			if (employeeId == null || employeeId < 1) {
				throw new WrongParameterException();
			}
			EmployeeEntity employee = employeeDao.findOne(employeeId);
			return employee;
		}

		@Override
		public List<EmployeeEntity> findEmployeeByProjectAndNumberOfMonths(String pName, int noOfMonths) {
			List<EmployeeEntity> epList = employeeProjectsDao.findEmployeesInProjectsByProjectAndPeriod(pName,
					noOfMonths);

			List<EmployeeEntity> employeeList = new ArrayList<EmployeeEntity>();

			for (EmployeeEntity e : epList) {
				employeeList.add(e);
				
			}

			return employeeList;
		}
}
