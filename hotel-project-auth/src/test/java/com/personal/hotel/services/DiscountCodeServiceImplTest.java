package com.personal.hotel.services;

import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.personal.hotel.model.DiscountCode;
import com.personal.hotel.repository.DiscountCodeRepository;

@SpringBootTest
class DiscountCodeServiceImplTest {
	
	@Mock
	private DiscountCodeRepository repository;
	

	private DiscountCodeService service;
	
	@BeforeEach
	void setUp() throws Exception {
		service = new DiscountCodeServiceImpl(repository);
	}

	@Test
	void test_GenerateDiscountCode_GeneratesDiscountCode_WhenGivenValidPercentage() {
		
		DiscountCode discountCode = service.generateDiscountCode(40);
		
		assertThat(discountCode.getCode().length(), equalTo(8));
		assertThat(discountCode.getDiscountPercentage(), equalTo(Double.valueOf(40)));
		
	}

	@Test
	void test_VerifyDiscountCode_ReturnsEmptyOptional_WhenDiscountCodeIsExpired() {
		
		DiscountCode discountCode = new DiscountCode();
		discountCode.setCode("AAAAAAAA");
		discountCode.setExpired(true);
		
		when(repository.findByCode("AAAAAAAA")).thenReturn(Optional.of(discountCode));
		
		Optional<DiscountCode> result = service.verifyDiscountCode("AAAAAAAA");
		assertThat(result.isEmpty(), equalTo(true));

	}

}
