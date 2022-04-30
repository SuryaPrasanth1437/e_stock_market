package com.stock.market.company.service;

import java.util.List;

import com.stock.market.company.dto.CompanyDetailsDto;
import com.stock.market.company.entity.CompanyDetails;

/**
 * @author Ksp
 *
 */
public interface ICompanyDetailsService {
	List<CompanyDetailsDto> getAllCompanyDetailList();
	void registerCompanyDetail(CompanyDetails companyDetails);
	CompanyDetailsDto getCompanyDetailsByCompanyCode(String companyCode);
	void deleteCompanyDetailsByCompanyCode(String companyCode);
}
