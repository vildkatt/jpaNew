package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.ProjectEntity;

public interface ProjectDao extends Dao<ProjectEntity, Long> {

	List<ProjectEntity> findProjectByName(String pName);

}
