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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.log4j.Log4j2;

/**
 * @author Ksp
 *
 */
@RestController
@RequestMapping("/api/v1.0/market/stock")
@CrossOrigin(origins = "http://localhost:4200")
@Log4j2
public class StockPriceController {

	@Autowired
	private IStockPriceService stockPriceService;

	/**
	 * This method is used to add stock price to that specific company
	 * 
	 * @param price
	 * @param companyCode
	 * @throws ParseException
	 */
	@PostMapping("/add/{companyCode}")
	@SecurityRequirement(name = "bearerAuth")
	public void addStockPrice(@RequestBody PriceDto price, @PathVariable("companyCode") String companyCode)
			throws ParseException {
		log.info("StockPriceController.addStockPrice, request: PriceDto - {}, companyCode - {} ", price, companyCode);
		stockPriceService.addStockPrice(price, companyCode);
	}

	/**
	 * This method is used to stockPrice details between two time period
	 * 
	 * @param companyCode
	 * @param startdate
	 * @param endDate
	 * @return ViewStockPriceDetailsDto
	 * @throws ParseException
	 */
	@GetMapping("/get/{companyCode}/{startDate}/{endDate}")
	@SecurityRequirement(name = "bearerAuth")
	public ViewStockPriceDetailsDto viewStockDetails(@PathVariable("companyCode") String companyCode,
			@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date startdate,
			@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") @PathVariable("endDate") Date endDate) throws ParseException {
		log.info("StockPriceController.viewStockDetails, request:  companyCode - {} , startDate - {} , endDate - {} ",
				companyCode, startdate, endDate);
		return stockPriceService.viewStockDetails(companyCode, startdate, endDate);
	}
}
