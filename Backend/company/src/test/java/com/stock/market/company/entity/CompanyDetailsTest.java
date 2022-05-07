package com.stock.market.company.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CompanyDetailsTest {

	@Test
	public void testCompanyDetails() {
		CompanyDetails companyDetails = new CompanyDetails();
		companyDetails.setCompanyCEO("abc");
		companyDetails.setCompanyCode("abc");
		companyDetails.setCompanyName("abc");
		companyDetails.setCompanyTurnOver("abc");
		companyDetails.setCompanyWebsite("abc");
		companyDetails.setId("1");
		companyDetails.setStockExchange("abc");
		assertEquals("1", companyDetails.getId());
		assertEquals("abc", companyDetails.getCompanyCEO());
		assertEquals("abc", companyDetails.getCompanyCode());
		assertEquals("abc", companyDetails.getCompanyName());
		assertEquals("abc", companyDetails.getCompanyTurnOver());
		assertEquals("abc", companyDetails.getCompanyWebsite());
		assertEquals("abc", companyDetails.getStockExchange());
	}
}
