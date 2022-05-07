
package com.stock.market.company.Controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.stock.market.company.controller.CompanyDetailsController;
import com.stock.market.company.dto.CompanyDetailsDto;
import com.stock.market.company.entity.CompanyDetails;
import com.stock.market.company.service.impl.CompanyDetailsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CompanyDetailsControllerTest {

	@Mock
	CompanyDetailsServiceImpl companyDetailsServiceImpl;
	
	@InjectMocks
	CompanyDetailsController companyDetailsController;
	
	@Test
	public void testGetAllCompanyList() {
		CompanyDetailsDto companyDetailsDto = companyDetailsDto();
		ArrayList<CompanyDetailsDto> companyDetailsDtos= new ArrayList<CompanyDetailsDto>();
		companyDetailsDtos.add(companyDetailsDto);
		Mockito.when(companyDetailsServiceImpl.getAllCompanyDetailList()).thenReturn(companyDetailsDtos);
		assertNotNull(companyDetailsController.getAllCompanyList());
	}

	private CompanyDetailsDto companyDetailsDto() {
		CompanyDetailsDto companyDetailsDto = CompanyDetailsDto.builder().id("1").companyCEO("abc").companyCode("abc")
				.companyName("abc").companyTurnOver("abc").companyWebsite("abc").stockExchange("abc").stockPrice(0.0)
				.build();
		return companyDetailsDto;
	}
	
	@Test
	public void testRegisterCompanyDetails() {
		CompanyDetails companyDetails = new CompanyDetails();
		companyDetails.setCompanyCEO("abc");
		companyDetails.setCompanyCode("abc");
		companyDetails.setCompanyName("abc");
		companyDetails.setCompanyTurnOver("abc");
		companyDetails.setCompanyWebsite("abc");
		companyDetails.setId("1");
		companyDetails.setStockExchange("abc");
		doNothing().when(companyDetailsServiceImpl).registerCompanyDetail(companyDetails);
		companyDetailsController.registerCompanyDetails(companyDetails);
	}
	
	@Test
	public void testGetCompanyDetailsByCompanyCode() {
		CompanyDetailsDto companyDetailsDto = companyDetailsDto();
		Mockito.when(companyDetailsServiceImpl.getCompanyDetailsByCompanyCode("1")).thenReturn(companyDetailsDto);
		assertNotNull(companyDetailsController.getCompanyDetailsByCompanyCode("1"));
	}
	
	@Test
	public void testDeleteCompanyDetailsByCompanyCode() {
		doNothing().when(companyDetailsServiceImpl).deleteCompanyDetailsByCompanyCode("1");
		companyDetailsController.deleteCompanyDetailsByCompanyCode("1");
	}
}
