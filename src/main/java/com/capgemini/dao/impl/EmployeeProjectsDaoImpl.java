package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.EmployeeProjectsDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeProjectsEntity;

@Repository
public class EmployeeProjectsDaoImpl extends AbstractDao<EmployeeProjectsEntity, Long> implements EmployeeProjectsDao {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public List<EmployeeEntity> findEmployeesInProjectsByProjectAndPeriod(String pName, int noOfMonths) {
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
				"select ep.employee from EmployeeProjectsEntity ep where ep.finishDate is not null and TIMESTAMPDIFF(SQL_TSI_MONTH, ep.startDate, ep.finishDate) > :month and ep.project.name like :project ",
				EmployeeEntity.class);
		query.setParameter("project", pName);
		query.setParameter("month", noOfMonths);

		return query.getResultList();
	}

	@Override
	public List<EmployeeEntity> findCurrentEmployeesInTheProject(String pName) {
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
				"select ep.employee from EmployeeProjectsEntity ep where ep.project.name like :project and ep.startDate < CURDATE() and (ep.finishDate > CURDATE() or ep.finishDate is null))",
				EmployeeEntity.class);
		query.setParameter("project", pName);
		return query.getResultList();
	}

	@Override
	public List<EmployeeProjectsEntity> findProjectAssignmentsByRole(String role) {
		TypedQuery<EmployeeProjectsEntity> query = entityManager.createQuery(
				"select ep from EmployeeProjectsEntity ep where ep.role like :roleName ", EmployeeProjectsEntity.class);
		query.setParameter("roleName", role);
		return query.getResultList();

	}

	@Override
	public List<EmployeeProjectsEntity> findAllProjectsWithAssignedEmployees() {
		TypedQuery<EmployeeProjectsEntity> query = entityManager.createQuery(
				"select ep from EmployeeProjectsEntity ep where finishDate IS NULL ", EmployeeProjectsEntity.class);
		return query.getResultList();
	}

}
