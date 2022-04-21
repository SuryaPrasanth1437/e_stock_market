package com.stock.market.stockprice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.market.stockprice.dto.PriceDto;
import com.stock.market.stockprice.service.IStockPriceService;

@RestController
@RequestMapping("/api/v1.0/market/stock")
@CrossOrigin(origins = "http://localhost:4200")
public class StockPriceController {

	@Autowired
	private IStockPriceService stockPriceService;
	
	@PostMapping("/add/{companyCode}")
	public void addStockPrice(@RequestBody PriceDto price, @PathVariable("companyCode") String companyCode) {
		stockPriceService.addStockPrice(price,companyCode);
	}
}
