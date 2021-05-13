package com.personal.hotel.repository;

import org.springframework.data.repository.CrudRepository;

import com.personal.hotel.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	
}
