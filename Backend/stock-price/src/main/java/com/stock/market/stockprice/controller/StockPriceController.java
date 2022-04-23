package com.stock.market.stockprice.controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.market.stockprice.dto.PriceDto;
import com.stock.market.stockprice.dto.ViewStockPriceDetailsDto;
import com.stock.market.stockprice.service.IStockPriceService;

@RestController
@RequestMapping("/api/v1.0/market/stock")
@CrossOrigin(origins = "http://localhost:4200")
public class StockPriceController {

	@Autowired
	private IStockPriceService stockPriceService;

	@PostMapping("/add/{companyCode}")
	public void addStockPrice(@RequestBody PriceDto price, @PathVariable("companyCode") String companyCode)
			throws ParseException {
		stockPriceService.addStockPrice(price, companyCode);
	}

	@GetMapping("/get/{companyCode}/{startDate}/{endDate}")
	public ViewStockPriceDetailsDto viewStockDetails(@PathVariable("companyCode") String companyCode,
			@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startdate,  @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate") Date endDate)
			throws ParseException {
	return stockPriceService.viewStockDetails(companyCode, startdate, endDate);
	}
}