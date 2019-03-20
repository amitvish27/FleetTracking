package org.tutorial.spring_boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tutorial.spring_boot.entity.Employee;
import org.tutorial.spring_boot.exception.BadRequestException;
import org.tutorial.spring_boot.exception.EmployeeNotFoundException;
import org.tutorial.spring_boot.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	@Transactional(readOnly = true)
	public List<Employee> findAll() {
		return (List<Employee>) repository.findAll();
	}

	@Transactional(readOnly = true)
	public Employee findById(String empId) {
		Optional<Employee> existing = repository.findById(empId);
		if(!existing.isPresent()) {
			throw new EmployeeNotFoundException("Employee with id: [" + empId + "] NOT FOUND");
		}
		return existing.get();
	}

	@Transactional
	public Employee create(Employee emp) {
		Optional<Employee> existing = repository.findByEmail(emp.getEmail());
		if(existing.isPresent()) {
			throw new BadRequestException("Employee with email [" + emp.getEmail() + "] already exist!");
		}
		return repository.save(emp);
	}

	@Transactional
	public Employee update(String empId, Employee emp) {
		Optional<Employee> existing = repository.findById(emp.getId());
		if(!existing.isPresent()) {
			throw new EmployeeNotFoundException("Employee with ID: [" + empId + "] does not exist");	
		}
		return repository.save(emp);
	}

	@Transactional
	public void delete(String empId) {
		Optional<Employee> existing = repository.findById(empId);
		if(!existing.isPresent()) {
			throw new EmployeeNotFoundException("Employee with ID: [" + empId + "] does not exist");	
		}
		repository.delete(existing.get());
	}

}
