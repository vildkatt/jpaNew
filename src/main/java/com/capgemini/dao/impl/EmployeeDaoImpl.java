package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.DepartmentEntity;
import com.capgemini.domain.EmployeeEntity;

@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public List<EmployeeEntity> findEmployeeByName(String name) {
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
				"select employee from EmployeeEntity employee where employee.name like :emplName ",
				EmployeeEntity.class);
		query.setParameter("emplName", name);
		return query.getResultList();
	}

	@Override
	public List<EmployeeEntity> findEmployeeByNameAndSurname(String firstName, String surname) {
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
				"select employee from EmployeeEntity employee where employee.firstName like :firstName and employee.surname like :surname",
				EmployeeEntity.class);
		query.setParameter("firstName", firstName);
		query.setParameter("surname", surname);

		return query.getResultList();
	}

	@Override
	public List<EmployeeEntity> findEmployeeBySurname(String surname) {
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
				"select employee from EmployeeEntity employee where employee.surname like :eSurname ",
				EmployeeEntity.class);
		query.setParameter("eSurname", surname);
		return query.getResultList();
	}

	@Override
	public boolean isPeselInDatabase(String pesel) {
		TypedQuery<Boolean> query = entityManager.createQuery(
				"SELECT case when (count(employee) > 0)  then true else false end FROM EmployeeEntity employee WHERE employee.pesel like :pesel",
				Boolean.class);
		query.setParameter("pesel", pesel);

		return query.getSingleResult();
	}

	@Override
	public List<EmployeeEntity> findEmployeeByPesel(String email) {
		return null;
	}

	@Override
	public List<EmployeeEntity> findEmployeeByDepartment(String dName) {
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
				"select employee from EmployeeEntity employee left join employee.department dept where dept.name = ?1",
				EmployeeEntity.class);
		query.setParameter(1, dName);
		return query.getResultList();
	}

	@Override
	public List<EmployeeEntity> findEmployeeByDepartmentId(Long dId) {
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
				"select employee from EmployeeEntity employee left join employee.department dept where dept.id = ?1",
				EmployeeEntity.class);
		query.setParameter(1, dId);
		return query.getResultList();
	}

	@Override
	public void assignDepartmentToAnEmployee(DepartmentEntity department, EmployeeEntity employee) {
		employee.setDepartment(department);
		entityManager.merge(employee);
	}

	@Override
	public void deleteEmployee(EmployeeEntity employee) {
		employee = entityManager.merge(employee);
		entityManager.remove(employee);
	}

}
