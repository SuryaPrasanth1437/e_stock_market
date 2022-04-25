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

@RestController
@RequestMapping("/api/v1.0/market/company")
@CrossOrigin(origins = "/**")
public class CompanyDetailsController {

	@Autowired
	private ICompanyDetailsService companyDetailsService;

	@GetMapping("/getall")
	public List<CompanyDetailsDto> getAllCompanyList() {
		return companyDetailsService.getAllCompanyDetailList();
	}
	
	@PostMapping("/register")
	public void registerCompanyDetails(@Valid @RequestBody CompanyDetails companyDetails) {
		companyDetailsService.registerCompanyDetail(companyDetails);
	}
	
	@GetMapping("/info/{companyCode}")
	public CompanyDetailsDto getCompanyDetailsByCompanyCode(@PathVariable("companyCode") String companyCode) {
		return companyDetailsService.getCompanyDetailsByCompanyCode(companyCode);
	}
	
	@DeleteMapping("/delete/{companyCode}")
	public void deleteCompanyDetailsByCompanyCode(@PathVariable String companyCode) {
		companyDetailsService.deleteCompanyDetailsByCompanyCode(companyCode);
	}

	
}
