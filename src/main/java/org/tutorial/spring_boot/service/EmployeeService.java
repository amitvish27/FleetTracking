package org.tutorial.spring_boot.service;

import java.util.List;

import org.tutorial.spring_boot.entity.Employee;

public interface EmployeeService {

	List<Employee> findAll();

	Employee findById(String empId);

	Employee create(Employee emp);

	Employee update(String empId, Employee emp);

	void delete(String empId);

}
