package com.payrollapplication.payroll;

import java.util.Objects;

import net.bytebuddy.asm.Advice.This;

public class Employee {
	
	private Long id;
	private String name;
	private String role;
	
	public Employee() {
		super();
	}

	public Employee(Long id, String name, String role) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Employee)) {
			return false;
		}
		Employee employee = (Employee) obj;
		return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name) 
				&& Objects.equals(this.role, employee.role);
	}
	
	
}
