package org.tutorial.spring_boot.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.tutorial.spring_boot.entity.Employee;
import org.tutorial.spring_boot.exception.EmployeeNotFoundException;
import org.tutorial.spring_boot.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {

	private List<Employee> employees;
	
	@TestConfiguration
	static class EmployeeServiceImplTestConfiguration {
		@Bean
		public EmployeeService getService() {
			return new EmployeeServiceImpl();
		}
	}

	@Autowired
	private EmployeeService service;

	@MockBean
	private EmployeeRepository repository;

	@Before
	public void setup() {
		Employee emp = new Employee();
		emp.setEmail("amit@mail.com");
		emp.setFirstName("Ameet");
		emp.setLastName("Vishwakarma");
		employees = Collections.singletonList(emp);
		
		Mockito.when(repository.findAll())
				.thenReturn(employees);
		
		Mockito.when(repository.findById(emp.getId()))
				.thenReturn(Optional.of(emp));
		
	}	
	
	@After
	public void cleanup() {
		
	}
	
	@Test
	public void findAll() throws Exception {
		List<Employee> result = service.findAll();	
		Assert.assertEquals("employees should match", employees, result);
	}

	@Test
	public void findById() throws Exception {
		Employee result = service.findById(employees.get(0).getId());
		Assert.assertEquals("employee should match", employees.get(0), result);
	}

	@Test(expected=EmployeeNotFoundException.class)
	public void findByIdNotFound() throws Exception {
		service.findById("123242");
	}

	@Test
	public void create() throws Exception {
	}

	@Test
	public void update() throws Exception {
	}

	@Test
	public void delete() throws Exception {
	}

}
