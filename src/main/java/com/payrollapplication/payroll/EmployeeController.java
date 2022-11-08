package com.payrollapplication.payroll;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
public class EmployeeController {
	
	private final EmployeeRepository repository;

	public EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}
	
	/*
	 * @GetMapping("/employees") List<Employee> all(){ return repository.findAll();
	 * }
	 */
	
	@GetMapping("/employees")
	// Upgrade RPC service to RESTful service by using Spring HATEOAS
	CollectionModel<EntityModel<Employee>> all(){
		List<EntityModel<Employee>> employees = repository.findAll().stream()
				.map(employee -> EntityModel.of(employee, 
						linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
						linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
						.collect(Collectors.toList());
		
				return CollectionModel.of(employees,
						linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
	}
	
	@PostMapping("/employees")
	Employee newEmployee(@RequestBody Employee newEmployee) {
		return repository.save(newEmployee);
	}
	
	/*
	 * @GetMapping("/employees/{id}") Employee one(@PathVariable Long id){ return
	 * repository.findById(id) .orElseThrow(() -> new EmployeeNotFoundException(id)
	 * ); }
	 */
	
	@GetMapping("/employees/{id}")
	// Upgrade RPC service to RESTful service by using Spring HATEOAS
	EntityModel<Employee> one(@PathVariable Long id){
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException(id));
		return EntityModel.of(employee, //
				linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
			      linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
	}
	
	@PutMapping("/employees/{id}")
	Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		return repository.findById(id)
				.map(employee -> {
					employee.setName(newEmployee.getName());
					employee.setRole(newEmployee.getRole());
					return repository.save(employee);
				})
				.orElseGet(() -> {
					newEmployee.setId(id);
					return repository.save(newEmployee);
				});	
	}
	
	@DeleteMapping("/employees/{id}")
	void deleteEmployee(@PathVariable Long id){
		repository.deleteById(id);
	}
}















