package com.stock.market.company.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ksp Entity for companyDetails
 *
 */
@Getter
@Setter
@Document("companyDetails")
public class CompanyDetails {

	@Id
	private String id;

	@Field(name = "cmpyCode")
	@NotNull(message = "company code cannot be empty")
	private String companyCode;

	@Field("cmpyName")
	@NotNull(message = "company name cannot be empty")
	private String companyName;

	@Field("cmpyCeo")
	@NotNull(message = "company CEO cannot be empty")
	private String companyCEO;

	@Field("cmpyTurnOver")
	@Size(min = 9)
	@NotNull(message = "turn over cannot be empty")
	private String companyTurnOver;

	@Field("cmpyWebsite")
	@NotNull(message = "company website cannot be empty")
	private String companyWebsite;

	@Field("stckExchange")
	@NotNull(message = "stock exchange cannot be empty")
	private String stockExchange;

	@Field("password")
	@NotNull(message = "password cannot be empty")
	private String password;

	@Field("confirmPassword")
	@NotNull(message = "confirm password cannot be empty")
	private String confirmPassword;

}
