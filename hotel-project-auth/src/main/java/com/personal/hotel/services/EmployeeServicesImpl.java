package com.personal.hotel.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.hotel.model.Employee;
import com.personal.hotel.repository.EmployeeRepository;

@Service
public class EmployeeServicesImpl implements EmployeeServices {

	private final EmployeeRepository repository;
	
	public EmployeeServicesImpl(EmployeeRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<Employee> findById(Long id) {
		return repository.findById(id).isPresent() ? repository.findById(id) : Optional.empty();
	}

	@Override
	public Employee save(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public void delete(Employee employee) {
		repository.delete(employee);
		
	}

}
