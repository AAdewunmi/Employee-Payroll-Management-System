package com.payrollapplication.payroll;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmployeeController {
	
	private final EmployeeRepository repository;

	public EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/employees")
	List<Employee> all(){
		return repository.findAll();
	}

}
