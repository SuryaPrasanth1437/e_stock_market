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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.log4j.Log4j2;

/**
 * @author Ksp
 *
 */
@RestController
@RequestMapping("/api/v1.0/market/company")
@CrossOrigin(origins = "/**")
@Log4j2
public class CompanyDetailsController {

	@Autowired
	private ICompanyDetailsService companyDetailsService;

	/**
	 * This method is used to return all the companyDetailsList
	 * 
	 * @return List<CompanyDetailsDto>
	 */
	
	@SecurityRequirement(name = "bearerAuth")
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
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping("/register")
	public void registerCompanyDetails(@Valid @RequestBody CompanyDetails companyDetails) {
		log.info("CompanyDetailsController.registerCompanyDetails(), request - {}",companyDetails);
		companyDetailsService.registerCompanyDetail(companyDetails);
	}

	/**
	 * This method is used the company details based on companyCode
	 * 
	 * @param companyCode
	 * @return CompanyDetailsDto
	 */
	@GetMapping("/info/{companyCode}")
	@SecurityRequirement(name = "bearerAuth")
	public CompanyDetailsDto getCompanyDetailsByCompanyCode(@PathVariable("companyCode") String companyCode) {
		log.info("CompanyDetailsController.getCompanyDetailsByCompanyCode(), request - {}",companyCode);
		return companyDetailsService.getCompanyDetailsByCompanyCode(companyCode);
	}


	/**
	 * This method is used to delete the company details
	 * 
	 * @param companyCode
	 */
	@DeleteMapping("/delete/{companyCode}")
	@SecurityRequirement(name = "bearerAuth")
	public void deleteCompanyDetailsByCompanyCode(@PathVariable String companyCode) {
		log.info("CompanyDetailsController.deleteCompanyDetailsByCompanyCode(), request - {}",companyCode);
		companyDetailsService.deleteCompanyDetailsByCompanyCode(companyCode);
	}

}
