package com.stock.market.company.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CompanyDetailsDtoTest {

	@Test
	public void testCompanyDetailsDto() {
		CompanyDetailsDto companyDetailsDto = CompanyDetailsDto.builder().id("1").companyCEO("abc").companyCode("abc")
				.companyName("abc").companyTurnOver("abc").companyWebsite("abc").stockExchange("abc").stockPrice(0.0)
				.build();
		assertEquals("1", companyDetailsDto.getId());
		assertEquals("abc", companyDetailsDto.getCompanyCEO());
		assertEquals("abc", companyDetailsDto.getCompanyCode());
		assertEquals("abc", companyDetailsDto.getCompanyName());
		assertEquals("abc", companyDetailsDto.getCompanyTurnOver());
		assertEquals("abc", companyDetailsDto.getCompanyWebsite());
		assertEquals("abc", companyDetailsDto.getStockExchange());
		assertEquals(0.0, companyDetailsDto.getStockPrice(), 0.0);
		assertNotNull(companyDetailsDto.builder().toString());
	}
}
