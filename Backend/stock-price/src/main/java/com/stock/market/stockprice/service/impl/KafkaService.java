package com.stock.market.stockprice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.stock.market.stockprice.entity.Price;

/**
 * @author Surya Prasanth
 *
 */
@Service
public class KafkaService {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;


	String kafkaTopic = "stock-market";

	/**
	 * this method is used to send the kafka message
	 * 
	 * @param post
	 */
	public void send(Price price) {
		kafkaTemplate.send(kafkaTopic, new Gson().toJson(price));
	}
}
