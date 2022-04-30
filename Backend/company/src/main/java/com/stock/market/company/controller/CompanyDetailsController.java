package com.stock.market.company.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.market.company.dto.CompanyDetailsDto;
import com.stock.market.company.entity.CompanyDetails;
import com.stock.market.company.service.ICompanyDetailsService;

/**
 * @author Ksp
 *
 */
@RestController
@RequestMapping("/api/v1.0/market/company")
@CrossOrigin(origins = "/**")
public class CompanyDetailsController {

	@Autowired
	private ICompanyDetailsService companyDetailsService;

	/**
	 * This method is used to return all the companyDetailsList
	 * 
	 * @return List<CompanyDetailsDto>
	 */
	@GetMapping("/getall")
	public List<CompanyDetailsDto> getAllCompanyList() {
		return companyDetailsService.getAllCompanyDetailList();
	}

	/**
	 * This method is used for registering the company details
	 * 
	 * @param companyDetails
	 * 
	 */
	@PostMapping("/register")
	public void registerCompanyDetails(@Valid @RequestBody CompanyDetails companyDetails) {
		companyDetailsService.registerCompanyDetail(companyDetails);
	}

	/**
	 * This method is used the company details based on companyCode
	 * 
	 * @param companyCode
	 * @return CompanyDetailsDto
	 */
	@GetMapping("/info/{companyCode}")
	public CompanyDetailsDto getCompanyDetailsByCompanyCode(@PathVariable("companyCode") String companyCode) {
		return companyDetailsService.getCompanyDetailsByCompanyCode(companyCode);
	}

	/**
	 * This method is used to delete the company details
	 * 
	 * @param companyCode
	 */
	@DeleteMapping("/delete/{companyCode}")
	public void deleteCompanyDetailsByCompanyCode(@PathVariable String companyCode) {
		companyDetailsService.deleteCompanyDetailsByCompanyCode(companyCode);
	}

}
