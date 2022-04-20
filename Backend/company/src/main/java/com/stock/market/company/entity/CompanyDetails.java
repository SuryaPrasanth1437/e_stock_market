package com.stock.market.company.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("companyDetails")
public class CompanyDetails {

	@Id
	private String id;

	@Indexed(name = "cmpyCode")
	private String companyCode;

	@Field("cmpyName")
	private String companyName;

	@Field("cmpyCeo")
	private String companyCEO;

	@Field("cmpyTurnOver")
	private String companyTurnOver;

	@Field("cmpyWebsite")
	private String companyWebsite;

	@Field("stckExchange")
	private String stockExchange;

	@Field("password")
	private String password;

	@Field("confirmPassword")
	private String confirmPassword;

}
