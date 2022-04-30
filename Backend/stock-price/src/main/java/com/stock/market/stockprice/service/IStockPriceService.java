package com.stock.market.stockprice.service;

import java.text.ParseException;
import java.util.Date;

import com.stock.market.stockprice.dto.PriceDto;
import com.stock.market.stockprice.dto.ViewStockPriceDetailsDto;
/**
 * @author Ksp
 *
 */
public interface IStockPriceService {

	void addStockPrice(PriceDto price,String companyCode) throws ParseException;
	ViewStockPriceDetailsDto viewStockDetails(String companyCode, Date startDate, Date endDate) throws ParseException;
}
