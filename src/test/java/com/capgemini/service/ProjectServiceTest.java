package com.capgemini.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeProjectsEntity;
import com.capgemini.domain.ProjectEntity;
import com.capgemini.domain.ProjectLocation;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProjectServiceTest {

	@Autowired
	private ProjectService ps;

	@Autowired
	private EmployeeService es;

	@Autowired
	private EmployeeProjectsService eps;

	@PersistenceContext
	private EntityManager em;

	@Test
	public void shouldAddNewProject() {
		ProjectEntity project = new ProjectEntity();
		project.setpName("tiger");
		project.setLocation(ProjectLocation.EXTERNAL);
		project.setEmployeeProject(new HashSet<>());
		//when
		ps.addANewProject(project);
		//then
		Assert.assertNotNull(ps.findProjectByName("tiger"));
		Assert.assertEquals(1, ps.findProjectByName("tiger").size());
	}

	@Test
	public void shouldRemoveProject() {
		ProjectEntity project = new ProjectEntity();
		project.setpName("brand");
		project.setLocation(ProjectLocation.INTERNAL);
		project.setEmployeeProject(new HashSet<>());
		Assert.assertEquals(0, ps.findProjectByName("brand").size());
		ps.addANewProject(project);
		Assert.assertEquals(1, ps.findProjectByName("brand").size());
		
		//when
		ps.deleteAProject(project);
		
		//then
		Assert.assertEquals(0, ps.findProjectByName("brand").size());
	}

	@Test
	public void shouldUpdateProjectData() {
		ProjectEntity project = new ProjectEntity();
		project.setpName("hero");
		project.setLocation(ProjectLocation.INTERNAL);
		project.setEmployeeProject(new HashSet<>());

		ps.addANewProject(project);

		ProjectEntity projectToUpdate = ps.findProjectByName("hero").get(0);
		Assert.assertEquals(ProjectLocation.INTERNAL, projectToUpdate.getLocation());

		//when
		projectToUpdate.setLocation(ProjectLocation.EXTERNAL);
		ps.updateProjectData(projectToUpdate);

		//then
		Assert.assertEquals(1, ps.findProjectByName("hero").size());
		Assert.assertEquals(ProjectLocation.EXTERNAL, ps.findProjectByName("hero").get(0).getLocation());
	}

	@Test
	public void shouldEndProjectAssignmentAndSetFinishDate() {

		Set<EmployeeProjectsEntity> employeeProjectsSet = new HashSet<>();
		EmployeeProjectsEntity epe = new EmployeeProjectsEntity();
		employeeProjectsSet.add(epe);

		ProjectEntity pe = new ProjectEntity();
		pe.setpName("leo");
		pe.setEmployeeProject(employeeProjectsSet);
		pe.setLocation(ProjectLocation.INTERNAL);

		EmployeeEntity ee = new EmployeeEntity();
		ee.setFirstName("Janusz");
		ee.setSurname("Nowak");
		ee.setBirthDate(new Date(1321));
		ee.setPesel("98766532321");
		ee.setEmployeeProject(employeeProjectsSet);

		ps.addANewProject(pe);
		epe.setEmployee(ee);

		epe.setProject(pe);
		epe.setRole("dev");
		es.addAnEmploye(ee);
		Assert.assertEquals(1, eps.findAllProjectsWithAssignedEmployees().size());
		//when
		epe.setFinishDate(new Date());
		//then
		Assert.assertEquals(0, eps.findAllProjectsWithAssignedEmployees().size());

	}

	@Test
	public void shouldFindAllProjectsWithAssignedEmployees() {

		Set<EmployeeProjectsEntity> employeeProjectsSet = new HashSet<>();
		EmployeeProjectsEntity epe = new EmployeeProjectsEntity();
		EmployeeProjectsEntity epe2 = new EmployeeProjectsEntity();
		
		employeeProjectsSet.add(epe);
		employeeProjectsSet.add(epe2);

		ProjectEntity pe = new ProjectEntity();
		pe.setpName("olex");
		pe.setEmployeeProject(employeeProjectsSet);
		pe.setLocation(ProjectLocation.INTERNAL);

		EmployeeEntity ee1 = new EmployeeEntity();
		ee1.setFirstName("Joe");
		ee1.setSurname("Martin");
		ee1.setBirthDate(new Date(143));
		ee1.setPesel("14314");
		ee1.setEmployeeProject(employeeProjectsSet);

		EmployeeEntity ee2 = new EmployeeEntity();
		ee2.setFirstName("Hans");
		ee2.setSurname("Solo");
		ee2.setBirthDate(new Date(1324));
		ee2.setPesel("1241");
		ee2.setEmployeeProject(employeeProjectsSet);

		ps.addANewProject(pe);
		epe.setEmployee(ee1);
		epe2.setEmployee(ee2);
		epe.setProject(pe);
		epe2.setProject(pe);
		epe.setRole("DEV");
		epe2.setRole("DEV");
		//when
		es.addAnEmploye(ee1);
		es.addAnEmploye(ee2);
		//then
		Assert.assertEquals(2, eps.findAllProjectsWithAssignedEmployees().size());
	}
	
	@Test
	public void shouldFindAllEmployeesInAProjectByPeriod() {

		Set<EmployeeProjectsEntity> employeeProjectsSet = new HashSet<>();
		EmployeeProjectsEntity epe = new EmployeeProjectsEntity();
		EmployeeProjectsEntity epe2 = new EmployeeProjectsEntity();
		
		employeeProjectsSet.add(epe);
		employeeProjectsSet.add(epe2);

		ProjectEntity pe = new ProjectEntity();
		pe.setpName("olex");
		pe.setEmployeeProject(employeeProjectsSet);
		pe.setLocation(ProjectLocation.INTERNAL);

		EmployeeEntity ee1 = new EmployeeEntity();
		ee1.setFirstName("Joe");
		ee1.setSurname("Martin");
		ee1.setBirthDate(new Date(143));
		ee1.setPesel("14314");
		ee1.setEmployeeProject(employeeProjectsSet);

		EmployeeEntity ee2 = new EmployeeEntity();
		ee2.setFirstName("Hans");
		ee2.setSurname("Solo");
		ee2.setBirthDate(new Date(1324));
		ee2.setPesel("1241");
		ee2.setEmployeeProject(employeeProjectsSet);

		ps.addANewProject(pe);
		epe.setEmployee(ee1);
		epe2.setEmployee(ee2);
		epe.setProject(pe);
		epe2.setProject(pe);
		epe.setRole("DEV");
		epe2.setRole("DEV");
		//when
		es.addAnEmploye(ee1);
		es.addAnEmploye(ee2);
		//then
		Assert.assertEquals(2, eps.findAllProjectsWithAssignedEmployees().size());
	}
	

}
