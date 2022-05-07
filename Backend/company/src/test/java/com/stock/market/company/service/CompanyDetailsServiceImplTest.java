package com.stock.market.company.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.stock.market.company.entity.CompanyDetails;
import com.stock.market.company.entity.Price;
import com.stock.market.company.repository.CompanyDetailsRepository;
import com.stock.market.company.repository.StockPriceRepository;
import com.stock.market.company.service.impl.CompanyDetailsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CompanyDetailsServiceImplTest {

	@InjectMocks
	CompanyDetailsServiceImpl companyDetailsServiceImpl;

	@Mock
	StockPriceRepository stockPriceRepository;

	@Mock
	CompanyDetailsRepository companyDetailsRepository;

	@Test
	public void testGetAllCompanyDetailList() {
		CompanyDetails companyDetails = companyDetailsTestData("abc");
		List<CompanyDetails> companyDetailsList = new ArrayList<CompanyDetails>();
		companyDetailsList.add(companyDetails);
		Mockito.when(companyDetailsRepository.findAll()).thenReturn(companyDetailsList);
		Price price = priceTestData();
		List<Price> priceList = new ArrayList<Price>();
		priceList.add(price);
		Mockito.when(stockPriceRepository.findAllByOrderByCreationDateDesc()).thenReturn(priceList);
		assertNotNull(companyDetailsServiceImpl.getAllCompanyDetailList());
	}

	@Test
	public void testGetAllCompanyDetailListCompanyCodeNotMatch() {
		CompanyDetails companyDetails = companyDetailsTestData("abcd");
		List<CompanyDetails> companyDetailsList = new ArrayList<CompanyDetails>();
		companyDetailsList.add(companyDetails);
		Mockito.when(companyDetailsRepository.findAll()).thenReturn(companyDetailsList);
		Price price = priceTestData();
		List<Price> priceList = new ArrayList<Price>();
		priceList.add(price);
		Mockito.when(stockPriceRepository.findAllByOrderByCreationDateDesc()).thenReturn(priceList);
		assertNotNull(companyDetailsServiceImpl.getAllCompanyDetailList());
	}

	@Test
	public void testGetAllCompanyDetailListEmpty() {
		CompanyDetails companyDetails = companyDetailsTestData("abc");
		List<CompanyDetails> companyDetailsList = new ArrayList<CompanyDetails>();
		companyDetailsList.add(companyDetails);
		Mockito.when(companyDetailsRepository.findAll()).thenReturn(new ArrayList<CompanyDetails>());
		Price price = priceTestData();
		List<Price> priceList = new ArrayList<Price>();
		priceList.add(price);
		Mockito.when(stockPriceRepository.findAllByOrderByCreationDateDesc()).thenReturn(priceList);
		assertNotNull(companyDetailsServiceImpl.getAllCompanyDetailList());
	}

	@Test
	public void getCompanyDetailsByCompanyCode() {
		CompanyDetails companyDetails = companyDetailsTestData("abc");
		Mockito.when(companyDetailsRepository.findByCompanyCode("abc")).thenReturn(companyDetails);
		Price price = priceTestData();
		List<Price> priceList = new ArrayList<Price>();
		priceList.add(price);
		Mockito.when(stockPriceRepository.findByCompanyCodeOrderByCreationDateDesc("abc")).thenReturn(priceList);
		assertNotNull(companyDetailsServiceImpl.getCompanyDetailsByCompanyCode("abc"));
	}
	
	@Test
	public void getCompanyDetailsByCompanyCodeDifferent() {
		CompanyDetails companyDetails = companyDetailsTestData("abcd");
		Mockito.when(companyDetailsRepository.findByCompanyCode("abc")).thenReturn(companyDetails);
		Price price = priceTestData();
		List<Price> priceList = new ArrayList<Price>();
		priceList.add(price);
		Mockito.when(stockPriceRepository.findByCompanyCodeOrderByCreationDateDesc("abc")).thenReturn(priceList);
		assertNotNull(companyDetailsServiceImpl.getCompanyDetailsByCompanyCode("abc"));
	}
	
	@Test
	public void getCompanyDetailsByCompanyCodeCompanyDetailsNull() {
		Mockito.when(companyDetailsRepository.findByCompanyCode("abc")).thenReturn(null);
		Price price = priceTestData();
		List<Price> priceList = new ArrayList<Price>();
		priceList.add(price);
		Mockito.when(stockPriceRepository.findByCompanyCodeOrderByCreationDateDesc("abc")).thenReturn(priceList);
		assertNull(companyDetailsServiceImpl.getCompanyDetailsByCompanyCode("abc"));
	}
	
	@Test
	public void testRegisterCompanyDetail() {
		CompanyDetails companyDetails = companyDetailsTestData("abcd");
		Mockito.when(companyDetailsRepository.save(companyDetails)).thenAnswer(i -> i.getArguments()[0]);
		companyDetailsServiceImpl.registerCompanyDetail(companyDetails);
	}
	
	@Test
	public void testDeleteCompanyDetailsByCompanyCode() {
		doNothing().when(companyDetailsRepository).deleteByCompanyCode("abc");
		doNothing().when(stockPriceRepository).deleteAllByCompanyCode("abc");
		companyDetailsServiceImpl.deleteCompanyDetailsByCompanyCode("abc");
	}

	private Price priceTestData() {
		return Price.builder().companyCode("abc").creationDate(new Date()).id("1").StckPrice(0.0).build();
	}

	private CompanyDetails companyDetailsTestData(String companyCode) {
		CompanyDetails companyDetails = new CompanyDetails();
		companyDetails.setCompanyCEO("abc");
		companyDetails.setCompanyCode(companyCode);
		companyDetails.setCompanyName("abc");
		companyDetails.setCompanyTurnOver("abc");
		companyDetails.setCompanyWebsite("abc");
		companyDetails.setId("1");
		companyDetails.setStockExchange("abc");
		return companyDetails;
	}

}
