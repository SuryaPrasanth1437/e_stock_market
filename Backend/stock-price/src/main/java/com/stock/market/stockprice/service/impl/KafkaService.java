package com.stock.market.stockprice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.stock.market.stockprice.entity.Price;
import com.stock.market.stockprice.repository.StockPriceRepository;

import lombok.extern.log4j.Log4j2;

/**
 * @author Ksp This class is used for send and receive kafka message
 *
 */
@Service
@Log4j2
public class KafkaService {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private StockPriceRepository stockPriceRepository;
	String kafkaTopic = "stock-market";

	/**
	 * this method is used to send the kafka message
	 * 
	 * @param post
	 */
	public void send(Price price) {
		log.info("sending message to kafka " ,price);
		kafkaTemplate.send(kafkaTopic, new Gson().toJson(price));
	}

	/**
	 * This method is used to receive message from Kafka and store the message in DB
	 * 
	 * @param message
	 */
	@KafkaListener(topics = "stock-market", groupId = "id")
	public void publish(String message) {
		log.info("Message received from Kafka -{} ", message);
		if (message != null) {
			Price price = new Gson().fromJson(message, Price.class);
			log.debug("after object conversion -{} ", price);
			stockPriceRepository.save(price);
		}
	}
}
