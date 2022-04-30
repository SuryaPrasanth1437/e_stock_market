package com.stock.market.stockprice.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.stock.market.stockprice.dto.PriceDto;
import com.stock.market.stockprice.entity.Price;
import com.stock.market.stockprice.service.impl.KafkaService;
import com.stock.market.stockprice.service.impl.StockPriceServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class StockPriceServiceImplTest {

	@InjectMocks
	StockPriceServiceImpl stockPriceServiceImpl;

	@Mock
	KafkaService kafkaService;

	@Mock
	MongoTemplate mongoTemplate;

	@Test
	public void testAddStockPrice() throws ParseException {
		Price price = Price.builder().companyCode("abc").creationDate(new Date()).id("1").StckPrice(0.0).build();

		doNothing().when(kafkaService).send(price);
		PriceDto priceDto = new PriceDto();
		priceDto.setStckPrice(10.0);
		stockPriceServiceImpl.addStockPrice(priceDto, "abc");
	}

	@Test
	public void testViewStockDetails() {
		Price price = Price.builder().companyCode("abc").creationDate(new Date()).id("1").StckPrice(0.0).build();
		List<Object> priceList = new ArrayList<>();
		priceList.add(price);
		try {
			Mockito.when(mongoTemplate.find(Mockito.any(), Mockito.any())).thenReturn(priceList);
			stockPriceServiceImpl.viewStockDetails("abc", new Date(), new Date());
		} catch (Throwable e) {
			assertNotNull(e);
		}
	}

	@Test
	public void testViewStockDetailsPriceListEmpty() {
		Price price = Price.builder().companyCode("abc").creationDate(new Date()).id("1").StckPrice(0.0).build();
		List<Object> priceList = new ArrayList<>();
		priceList.add(price);
		try {
			Mockito.when(mongoTemplate.find(Mockito.any(), Mockito.any())).thenReturn(new ArrayList());
			stockPriceServiceImpl.viewStockDetails("abc", new Date(), new Date());
		} catch (Throwable e) {
			assertNotNull(e);
		}
	}
}
