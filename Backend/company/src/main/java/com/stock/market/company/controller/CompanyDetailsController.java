package com.stock.market.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.market.company.entity.CompanyDetails;
import com.stock.market.company.service.ICompanyDetailsService;

@RestController
//@RequestMapping("/api/v1.0/market/company")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyDetailsController {

	@Autowired
	private ICompanyDetailsService companyDetailsService;

	@GetMapping("/all")
	public List<CompanyDetails> getCompanyList() {
		return companyDetailsService.getCompanyDetailList();
	}
}
