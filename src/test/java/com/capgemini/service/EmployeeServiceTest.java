package com.capgemini.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.DepartmentEntity;
import com.capgemini.domain.EmployeeEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService es;

	@Autowired
	private DepartmentService ds;

	@Before
	public void setup() {

		Set<EmployeeEntity> employeeSet = new HashSet<>();

		DepartmentEntity de = new DepartmentEntity();
		de.setDepartmentName("IT");
		de.setEmployees(employeeSet);
		de.setEmail("department@dep.co");
		de.setCompanyNumber("12431");
		de.setLandLine("00453234234");

		ds.addDepartment(de);

		EmployeeEntity ee = new EmployeeEntity();
		ee.setBirthDate(new Date(1321));
		ee.setCompanyNumber("jwye832ye");
		ee.setPesel("98766532321");
		ee.setFirstName("Janusz");
		ee.setSurname("Nowak");
		ee.setEmail("janusz.nowak@company.com");
		ee.setDepartment(de);
		ee.setHomeNumber("143123414");
		es.addAnEmploye(ee);
	}

	@Test
	public void shouldReturnEmployeeListWithOneRecord() {
		List<EmployeeEntity> employeeList = es.findAllEmployees();

		Assert.assertNotNull(employeeList);
		Assert.assertEquals(1, employeeList.size());
	}

	@Test
	public void shouldReturnCorrectEmployeeCredentials() {
		// when
		List<EmployeeEntity> employeeList = es.findAllEmployees();
		// then
		Assert.assertEquals("Janusz", employeeList.get(0).getFirstName());
		Assert.assertEquals("Nowak", employeeList.get(0).getSurname());
		Assert.assertEquals("98766532321", employeeList.get(0).getPesel());
		Assert.assertEquals("jwye832ye", employeeList.get(0).getCompanyNumber());
		Assert.assertEquals("janusz.nowak@company.com", employeeList.get(0).getEmail());
	}

	@Test
	public void shouldFindCorrectEmployeeByNameAndSurname() {
		// when
		List<EmployeeEntity> employeeList = es.findEmployeeByNameAndSurname("Janusz", "Nowak");
		// then
		Assert.assertNotNull(employeeList);
		Assert.assertEquals(1, employeeList.size());
		Assert.assertEquals("Janusz", employeeList.get(0).getFirstName());
		Assert.assertEquals("Nowak", employeeList.get(0).getSurname());
	}

	@Test
	public void shouldNotFindEmployeeWhoIsNotInDB() {
		// when
		List<EmployeeEntity> employeeList = es.findEmployeeByNameAndSurname("XXXX", "YYYY");
		// then
		Assert.assertEquals(0, employeeList.size());
	}

	@Test
	public void shouldAddANewEmployee() {

		Set<EmployeeEntity> employeeSet = new HashSet<>();

		DepartmentEntity de = new DepartmentEntity();
		de.setDepartmentName("IT");
		de.setEmployees(employeeSet);
		ds.addDepartment(de);

		EmployeeEntity ee = new EmployeeEntity();
		ee.setBirthDate(new Date(1321));
		ee.setPesel("777777777");
		ee.setFirstName("Anna");
		ee.setSurname("Kowal");
		ee.setDepartment(de);

		// when
		es.addAnEmploye(ee);

		// then
		Assert.assertTrue(ee != null);
		Assert.assertFalse(es.findEmployeeByNameAndSurname("Anna", "Kowal").isEmpty());
		Assert.assertEquals(2, es.findAllEmployees().size());

	}

	@Test
	public void shouldModifyEmployeeSurname() {
		List<EmployeeEntity> employeeList = es.findAllEmployees();
		EmployeeEntity employee = employeeList.get(0);
		String newSurname = "testName";
		employee.setSurname(newSurname);
		// when
		employee = es.updateAnEmployee(employee);
		// then
		Assert.assertEquals("testName", es.findAllEmployees().get(0).getSurname());

	}

}
