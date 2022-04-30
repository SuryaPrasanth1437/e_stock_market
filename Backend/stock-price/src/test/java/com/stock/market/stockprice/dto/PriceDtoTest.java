package com.stock.market.stockprice.dto;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PriceDtoTest {

	@Test
	public void testPriceDto() {
		PriceDto priceDto= new PriceDto();
		priceDto.setStckPrice(0.0);
		assertNotNull(priceDto.getStckPrice());
	}
}
