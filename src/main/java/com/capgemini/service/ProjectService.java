package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.EmployeeProjectsEntity;
import com.capgemini.domain.ProjectEntity;

public interface ProjectService {

	ProjectEntity addANewProject(ProjectEntity project);

	ProjectEntity findProjectById(Long projectId);
	
	ProjectEntity updateProjectData(ProjectEntity project);
	
	void deleteAProject(ProjectEntity project);

	EmployeeProjectsEntity assignEmployeeToAProject(EmployeeProjectsEntity epEntity);

	List <ProjectEntity> findProjectByName(String pName);

	

	

	
}
