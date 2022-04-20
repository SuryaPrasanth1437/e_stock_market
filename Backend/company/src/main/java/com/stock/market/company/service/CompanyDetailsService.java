package com.stock.market.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stock.market.company.entity.CompanyDetails;
import com.stock.market.company.repository.CompanyDetailsRepository;

@Service
public class CompanyDetailsService implements ICompanyDetailsService{

	@Autowired
	private CompanyDetailsRepository companyDetailsRepository;
	
	@Transactional
	public List<CompanyDetails> getCompanyDetailList() {
		return companyDetailsRepository.findAll();
	}
}
