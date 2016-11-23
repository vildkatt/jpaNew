package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.EmployeeProjectsDao;
import com.capgemini.dao.ProjectDao;
import com.capgemini.domain.EmployeeProjectsEntity;
import com.capgemini.domain.ProjectEntity;
import com.capgemini.service.ProjectService;

@Service
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectDao projectDao;

	@Autowired
	EmployeeProjectsDao epDao;

	@Override
	@Transactional(readOnly = false)
	public ProjectEntity addANewProject(ProjectEntity project) {
		project = projectDao.save(project);
		return project;
	}

	@Override
	public ProjectEntity findProjectById(Long projectId) {
		if (projectId == null || projectId < 1) {
			throw new RuntimeException();
		}
		ProjectEntity project = projectDao.findOne(projectId);
		return project;

	}

	@Override
	public List<ProjectEntity> findProjectByName(String pName) {
		if (pName == null || pName.isEmpty()) {
			throw new RuntimeException();
		}

		List<ProjectEntity> project = projectDao.findProjectByName(pName);
		return project;

	}

	@Override
	public ProjectEntity updateProjectData(ProjectEntity project) {
		project = projectDao.update(project);
		return project;
	}

	@Override
	public void deleteAProject(ProjectEntity project) {

		projectDao.delete(project);
	}

	@Override
	public EmployeeProjectsEntity assignEmployeeToAProject(EmployeeProjectsEntity epEntity) {
		epEntity = epDao.save(epEntity);
		return epEntity;
	}


}
