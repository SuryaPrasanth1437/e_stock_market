package com.stock.market.company.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Ksp
 *
 */
@Builder
@Getter
public class CompanyDetailsDto {
	private String id;

	private String companyCode;

	private String companyName;

	private String companyCEO;

	private String companyTurnOver;

	private String companyWebsite;

	private String stockExchange;

	private double stockPrice;
}
