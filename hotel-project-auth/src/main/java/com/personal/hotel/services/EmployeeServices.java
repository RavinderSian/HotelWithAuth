package com.personal.hotel.services;

import java.util.Optional;

import com.personal.hotel.model.Employee;

public interface EmployeeServices {
	
	Optional<Employee> findById(Long id);
	Employee save(Employee employee);
	void delete(Employee employee);

}
