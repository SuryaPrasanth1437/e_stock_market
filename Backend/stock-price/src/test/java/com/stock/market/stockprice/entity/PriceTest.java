package com.stock.market.stockprice.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

public class PriceTest {
	@Test
	public void testPrice() {
		Price price = Price.builder().companyCode("abc").creationDate(new Date()).id("1").StckPrice(0.0).build();
		assertEquals("1", price.getId());
		assertEquals("abc", price.getCompanyCode());
		assertNotNull(price.getCreationDate());
		assertEquals(0.0, price.getStckPrice(), 0.0);
		assertNotNull(price.builder().toString());
		
	}
}
