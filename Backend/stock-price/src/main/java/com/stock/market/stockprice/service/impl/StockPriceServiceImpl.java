package com.stock.market.stockprice.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stock.market.stockprice.dto.PriceDto;
import com.stock.market.stockprice.entity.Price;
import com.stock.market.stockprice.repository.StockPriceRepository;
import com.stock.market.stockprice.service.IStockPriceService;

@Service
public class StockPriceServiceImpl implements IStockPriceService {

	@Autowired
	private StockPriceRepository stockPriceRepository;

	@Transactional
	public void addStockPrice(PriceDto priceDto, String companyCode) {
		System.out.println(new Date());
		Price price = Price.builder().StckPrice(priceDto.getStckPrice()).companyCode(companyCode).creationDate(new Date())
				.build();
		stockPriceRepository.save(price);

	}
}
