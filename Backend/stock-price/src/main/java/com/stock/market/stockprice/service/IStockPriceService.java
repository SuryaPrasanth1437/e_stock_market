package com.stock.market.stockprice.service;

import com.stock.market.stockprice.dto.PriceDto;

public interface IStockPriceService {

	void addStockPrice(PriceDto price,String companyCode);
}
