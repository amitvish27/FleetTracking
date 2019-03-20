package org.tutorial.spring_boot.controller;

import java.util.Collections;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.tutorial.spring_boot.entity.Employee;
import org.tutorial.spring_boot.repository.EmployeeRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("integrationTest")
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private EmployeeRepository repository;

	@Autowired
	private ObjectMapper mapper;

	private List<Employee> employees;

	@Before
	public void setup() {
		Employee emp = new Employee();
		emp.setId("amit-id");
		emp.setEmail("amit@mail.com");
		emp.setFirstName("Ameet");
		emp.setLastName("Vishwakarma");
		employees = Collections.singletonList(emp);
		repository.save(employees.get(0));
	}

	@After
	public void cleanUp() {
		repository.deleteAll();
	}

	@Test
	public void findAll() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/employee")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("amit@mail.com")));
	}

	@Test
	public void findById() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/employee/amit-id")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("amit@mail.com")));
	}

	@Test
	public void findById404() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/employee/random-id"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void create() throws Exception {
		Employee emp = new Employee();
		emp.setEmail("arti@mail.com");
		emp.setFirstName("Arti");
		emp.setLastName("Vishwakarma");

		mvc.perform(MockMvcRequestBuilders.post("/employee").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(mapper.writeValueAsBytes(emp))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.notNullValue()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("arti@mail.com")));

		mvc.perform(MockMvcRequestBuilders.get("/employee")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
	}
	
	@Test
	public void create400() throws Exception {
		Employee emp = new Employee();
		emp.setEmail("amit@mail.com");
		emp.setFirstName("random");
		emp.setLastName("person");

		mvc.perform(MockMvcRequestBuilders.post("/employee").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(mapper.writeValueAsBytes(emp))).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

}
