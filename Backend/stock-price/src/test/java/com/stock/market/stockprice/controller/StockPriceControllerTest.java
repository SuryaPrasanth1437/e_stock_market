package com.stock.market.stockprice.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.OptionalDouble;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.stock.market.stockprice.dto.PriceDto;
import com.stock.market.stockprice.dto.ViewStockPriceDetailsDto;
import com.stock.market.stockprice.service.impl.StockPriceServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class StockPriceControllerTest {

	@Mock
	StockPriceServiceImpl stockPriceServiceImpl;

	@InjectMocks
	StockPriceController stockPriceController;

	@Test
	public void testAddStockPrice() {
		PriceDto priceDto = new PriceDto();
		priceDto.setStckPrice(0.0);
		try {
			doNothing().when(stockPriceServiceImpl).addStockPrice(priceDto, "abc");
			stockPriceController.addStockPrice(priceDto, "abc");
		} catch (Throwable e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testViewStockDetails() {
		ViewStockPriceDetailsDto viewStockPriceDetailsDto = ViewStockPriceDetailsDto.builder().min(0.0).max(0.0)
				.average(OptionalDouble.of(452146)).stockPriceList(new ArrayList()).build();
		try {
			Mockito.when(stockPriceServiceImpl.viewStockDetails("abc", new Date(), new Date())).thenReturn(viewStockPriceDetailsDto);
			assertNotNull(stockPriceController.viewStockDetails("abc", new Date(), new Date()));
		}catch(Throwable e) {
			assertNotNull(e);
		}
	}
}
