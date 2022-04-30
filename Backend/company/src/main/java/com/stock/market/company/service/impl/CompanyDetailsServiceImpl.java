package com.stock.market.company.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stock.market.company.dto.CompanyDetailsDto;
import com.stock.market.company.entity.CompanyDetails;
import com.stock.market.company.entity.Price;
import com.stock.market.company.repository.CompanyDetailsRepository;
import com.stock.market.company.repository.StockPriceRepository;
import com.stock.market.company.service.ICompanyDetailsService;

import lombok.extern.log4j.Log4j2;

/**
 * @author Ksp
 *
 */
@Service
@Log4j2
public class CompanyDetailsServiceImpl implements ICompanyDetailsService {

	@Autowired
	private CompanyDetailsRepository companyDetailsRepository;

	@Autowired
	StockPriceRepository stockPriceRepository;

	@Transactional
	public List<CompanyDetailsDto> getAllCompanyDetailList() {
		List<CompanyDetailsDto> companyDetailsDtoList = new ArrayList<CompanyDetailsDto>();
		List<CompanyDetails> companyDetailsList = companyDetailsRepository.findAll();
		List<Price> priceList = stockPriceRepository.findAllByOrderByCreationDateDesc();
		log.info("CompanyDetailsController.getAllCompanyDetailList, companyDetailsList - {}", companyDetailsList);
		log.info("CompanyDetailsController.getAllCompanyDetailList, priceList - {}", priceList);
		if (!companyDetailsList.isEmpty()) {

			for (CompanyDetails company : companyDetailsList) {
				double stckPrice = 0.0;
				for (Price price : priceList) {
					if (price.getCompanyCode().equalsIgnoreCase(company.getCompanyCode())) {
						stckPrice = price.getStckPrice();
					}
				}
				CompanyDetailsDto companyDetailsDto = CompanyDetailsDto.builder().companyCode(company.getCompanyCode())
						.companyName(company.getCompanyName()).companyCEO(company.getCompanyCEO())
						.companyTurnOver(company.getCompanyTurnOver()).companyWebsite(company.getCompanyWebsite())
						.stockExchange(company.getStockExchange()).stockPrice(stckPrice).id(company.getCompanyCEO())
						.build();
				companyDetailsDtoList.add(companyDetailsDto);
			}
		}
		log.info("CompanyDetailsController.getAllCompanyDetailList, final response - {}", companyDetailsDtoList);
		return companyDetailsDtoList;
	}

	@Transactional
	public CompanyDetailsDto getCompanyDetailsByCompanyCode(String companyCode) {
		CompanyDetails companyDetails = companyDetailsRepository.findByCompanyCode(companyCode);
		List<Price> priceList = stockPriceRepository.findByCompanyCodeOrderByCreationDateDesc(companyCode);
		CompanyDetailsDto companyDetailsDto = null;
		log.info("CompanyDetailsController.getAllCompanyDetailList, companyDetails - {}", companyDetails);
		log.info("CompanyDetailsController.getAllCompanyDetailList, priceList - {}", priceList);
		if (companyDetails != null) {

			double stckPrice = 0.0;
			for (Price price : priceList) {
				if (price.getCompanyCode().equalsIgnoreCase(companyDetails.getCompanyCode())) {
					stckPrice = price.getStckPrice();
				}
			}
			companyDetailsDto = CompanyDetailsDto.builder().companyCode(companyDetails.getCompanyCode())
					.companyName(companyDetails.getCompanyName()).companyCEO(companyDetails.getCompanyCEO())
					.companyTurnOver(companyDetails.getCompanyTurnOver())
					.companyWebsite(companyDetails.getCompanyWebsite()).stockExchange(companyDetails.getStockExchange())
					.stockPrice(stckPrice).id(companyDetails.getCompanyCEO()).build();
		}
		log.info("CompanyDetailsController.getCompanyDetailsByCompanyCode(), final response - {}", companyDetailsDto);
		return companyDetailsDto;
	}

	@Override
	@Transactional
	public void registerCompanyDetail(CompanyDetails companyDetails) {
		log.info("CompanyDetailsController.registerCompanyDetail(),  {}", companyDetails);
		companyDetailsRepository.save(companyDetails);

	}

	@Override
	@Transactional
	public void deleteCompanyDetailsByCompanyCode(String companyCode) {
		log.info("CompanyDetailsController.registerCompanyDetail(),  {}", companyCode);
		companyDetailsRepository.deleteByCompanyCode(companyCode);
		stockPriceRepository.deleteAllByCompanyCode(companyCode);

	}
}
