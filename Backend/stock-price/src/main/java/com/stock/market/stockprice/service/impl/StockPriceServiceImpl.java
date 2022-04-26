package com.stock.market.stockprice.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.stock.market.stockprice.dto.PriceDto;
import com.stock.market.stockprice.dto.ViewStockPriceDetailsDto;
import com.stock.market.stockprice.entity.Price;
import com.stock.market.stockprice.repository.StockPriceRepository;
import com.stock.market.stockprice.service.IStockPriceService;

@Service
public class StockPriceServiceImpl implements IStockPriceService {

	@Autowired
	private StockPriceRepository stockPriceRepository;
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private KafkaService kafkaService;

	@Transactional
	public void addStockPrice(PriceDto priceDto, String companyCode) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String creationDateString = df.format(new Date());
		Date requiredDate = df.parse(creationDateString);
		Price price = Price.builder().StckPrice(priceDto.getStckPrice()).companyCode(companyCode)
				.creationDate(requiredDate).build();
		kafkaService.send(price);
		stockPriceRepository.save(price);

	}

	@Override
	@Transactional
	public ViewStockPriceDetailsDto viewStockDetails(String companyCode, Date startDate, Date endDate)
			throws ParseException {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		String startDateString = df.format(startDate);
		String endDateString = df.format(endDate);
		Date startDateRequired = df.parse(startDateString);
		Date endDateRequired = df.parse(endDateString);
		Query query = new Query(Criteria.where("cmpyCode").is(companyCode).andOperator(
				Criteria.where("creationDate").gte(startDateRequired),
				Criteria.where("creationDate").lte(endDateRequired)));
		List<Price> priceList = mongoTemplate.find(query, Price.class);
		List<Double> stockPriceList = new ArrayList<Double>();
		if (!priceList.isEmpty()) {
			for (Price price : priceList) {
				if (!ObjectUtils.isEmpty(price.getStckPrice())) {
					stockPriceList.add(price.getStckPrice());

				}
			}
		}
		Collections.sort(stockPriceList);
		ViewStockPriceDetailsDto viewStockPriceDetailsDto = null;
		if (!ObjectUtils.isEmpty(stockPriceList)) {
			double min = stockPriceList.get(0);
			double max = stockPriceList.get(stockPriceList.size() - 1);
			OptionalDouble average = stockPriceList.stream().mapToDouble(n -> n).average();
			viewStockPriceDetailsDto = ViewStockPriceDetailsDto.builder().average(average).min(min).max(max)
					.stockPriceList(stockPriceList).build();
		}
		return viewStockPriceDetailsDto;
	}
}
