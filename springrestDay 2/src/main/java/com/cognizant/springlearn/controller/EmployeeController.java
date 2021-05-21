package com.cognizant.springlearn.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.EmployeeService;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;



@RequestMapping("/employees")
@RestController
public class EmployeeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);
	
	
	private EmployeeService employeeService;
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping
	public List<Employee> getAllEmployees() {
		LOGGER.info("START");
		List<Employee> employees = employeeService.getAllEmployees();
		
		LOGGER.debug("Employees : {}", employees);
		LOGGER.info("END");
		
		return employees;
	}
	
	@PutMapping
	public void updateEmployee(@RequestBody @Valid Employee employee) throws EmployeeNotFoundException {
		LOGGER.info("START");
		employeeService.updateEmployee(employee);
		LOGGER.info("END");
	}

	@DeleteMapping("/{id}")
	public List<Employee> deleteEmployee(@PathVariable int id) throws EmployeeNotFoundException {
		LOGGER.info("START");
		
		List<Employee> employees = employeeService.deleteEmployee(id);
		LOGGER.debug("Employee deleted with id : " + id);
		LOGGER.debug("Employees : {}", employees);
		LOGGER.info("END");
		return null;
	}
}
