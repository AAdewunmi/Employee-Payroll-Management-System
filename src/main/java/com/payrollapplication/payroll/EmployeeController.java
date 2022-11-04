package com.payrollapplication.payroll;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	private final EmployeeRepository repository;

	public EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}
	


}
