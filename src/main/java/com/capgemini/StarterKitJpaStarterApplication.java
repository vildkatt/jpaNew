package com.capgemini;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.capgemini.domain.DepartmentEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeProjectsEntity;
import com.capgemini.domain.ProjectEntity;
import com.capgemini.domain.ProjectLocation;
import com.capgemini.service.DepartmentService;
import com.capgemini.service.EmployeeProjectsService;
import com.capgemini.service.EmployeeService;
import com.capgemini.service.ProjectService;

@SpringBootApplication
public class StarterKitJpaStarterApplication {

	public static void main(String[] args) {
		
		ApplicationContext ctx = SpringApplication.run(StarterKitJpaStarterApplication.class, args);
		
		DepartmentService dService = ctx.getBean(DepartmentService.class);
		EmployeeProjectsService epService = ctx.getBean(EmployeeProjectsService.class);
		EmployeeService eService = ctx.getBean(EmployeeService.class);
		ProjectService pService = ctx.getBean(ProjectService.class);
		
		Set<EmployeeProjectsEntity>employeeProjectsSet = new HashSet<>();
		Set<EmployeeEntity>employeeSet = new HashSet<>();
		
		DepartmentEntity de = new DepartmentEntity();
		de.setDepartmentName("IT");
		de.setEmployees(employeeSet);
		de.setEmail("department@dep.co");
		de.setCompanyNumber("12431");
		de.setLandLine("00453234234");
		
		dService.addDepartment(de);
		
		EmployeeEntity ee = new EmployeeEntity();
		ee.setBirthDate(new Date(1321));
		ee.setCompanyNumber("jwye832ye");
		ee.setPesel("98766532321");
		ee.setFirstName("Juliusz");
		ee.setSurname("Verne");
		ee.setEmail("email@gmail.com");
		ee.setDepartment(de);
		ee.setHomeNumber("143123414");
		eService.addAnEmploye(ee);
		
		ProjectEntity project = new ProjectEntity();
		project.setCreationTime(new Date());
		project.setpName("Project1");
		project.setLocation(ProjectLocation.EXTERNAL);
		project.setEmployeeProject(employeeProjectsSet);
		pService.addANewProject(project);
		
		EmployeeProjectsEntity epe = new EmployeeProjectsEntity(ee, project, "DEV", new Date(), 234L);
		employeeProjectsSet.add(epe);
		employeeSet.add(ee);
		ee.setEmployeeProject(employeeProjectsSet);
		

		epService.addEmployeeProjectAssignment(epe);
		
	}
}
