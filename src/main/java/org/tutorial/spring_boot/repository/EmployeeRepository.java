package org.tutorial.spring_boot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.tutorial.spring_boot.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String>{
	
	Optional<Employee> findByEmail(String email);
	
	List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

}
