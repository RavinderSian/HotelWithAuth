package com.personal.hotel.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.personal.hotel.model.Employee;
import com.personal.hotel.repository.EmployeeRepository;

@SpringBootTest
public class EmployeeServicesImplTest {

	EmployeeServices services;
	
	@Mock
	EmployeeRepository repository;
	
	@Mock
	Employee employee;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.services = new EmployeeServicesImpl(repository);
		employee = new Employee();
		employee.setFirstName("rav");
	}

	@Test
	public void test_Service_IsNotNull() {
		assertThat(services, is(not(nullValue())));
	}
	
	@Test
	public void test_Save_CallsRepositorySave_WhenCalled() {
		services.save(employee);
		verify(repository, times(1)).save(employee);
	}
	
	@Test
	public void test_Save_ReturnsCorrectEmployee_WhenGivenEmployee() {
		when(repository.save(employee)).thenReturn(employee);
		Employee employeeResult = services.save(employee);
		assertThat(employeeResult, equalTo(employee));
	}
	
	@Test
	public void test_Delete_CallsRepositoryDelete_WhenCalled() {
		services.delete(employee);
		verify(repository, times(1)).delete(employee);
	}
	
	@Test
	public void test_FindById_CallsRepositoryFindById_WhenCalled() {
		services.findById(1L);
		verify(repository, times(1)).findById(1L);
	}
	
	@Test
	public void test_FindById_ReturnsCorrectEmployee_WhenGivenId1() {
		when(repository.findById(1L)).thenReturn(Optional.of(employee));
		Optional<Employee> employeeOptional = services.findById(1L);
		assertThat(employeeOptional, equalTo(Optional.of(employee)));
	}
	
	@Test
	public void test_FindById_ReturnsEmptyOptional_WhenGivenId10() {
		Optional<Employee> employeeOptional = services.findById(10L);
		assertThat(employeeOptional, equalTo(Optional.empty()));
	}

}
