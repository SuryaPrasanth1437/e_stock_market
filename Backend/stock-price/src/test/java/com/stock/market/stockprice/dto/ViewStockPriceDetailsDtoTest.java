package com.stock.market.stockprice.dto;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.OptionalDouble;

import org.junit.Test;

public class ViewStockPriceDetailsDtoTest {

	@Test
	public void testViewStockPriceDetailsDtoTest() {
		ViewStockPriceDetailsDto viewStockPriceDetailsDto = ViewStockPriceDetailsDto.builder().min(0.0).max(0.0)
				.average(OptionalDouble.of(452146)).stockPriceList(new ArrayList()).build();
		assertNotNull(viewStockPriceDetailsDto.getMin());
		assertNotNull(viewStockPriceDetailsDto.getMax());
		assertNotNull(viewStockPriceDetailsDto.getStockPriceList());
		assertNotNull(viewStockPriceDetailsDto.getAverage());
		assertNotNull(viewStockPriceDetailsDto.builder().toString());
	}
}
