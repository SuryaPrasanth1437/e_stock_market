package com.stock.market.stockprice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("stockPrices")
public class Price {

	@Id
	private String id;

	@Field("cmpyCode")
	private String companyCode;
	
	@Field("stckPrice")
	private double price;

}
