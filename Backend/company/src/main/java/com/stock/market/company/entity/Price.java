package com.stock.market.company.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Document("stockPrices")
public class Price {

	@Id
	private String id;

	@Field("cmpyCode")
	private String companyCode;
	
	@Field("stckPrice")
	private double StckPrice;
	
	@Field("creationDate")
	private Date creationDate;

}
