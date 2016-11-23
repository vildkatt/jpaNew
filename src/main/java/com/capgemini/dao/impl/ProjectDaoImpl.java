package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.ProjectDao;
import com.capgemini.domain.ProjectEntity;

@Repository
public class ProjectDaoImpl extends AbstractDao<ProjectEntity, Long> implements ProjectDao {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public List<ProjectEntity> findProjectByName(String pName) {
		TypedQuery<ProjectEntity> query = entityManager.createQuery(
				"select project from ProjectEntity project where project.pName like :pName ", ProjectEntity.class);
		query.setParameter("pName", pName);
		return query.getResultList();

	}
}
