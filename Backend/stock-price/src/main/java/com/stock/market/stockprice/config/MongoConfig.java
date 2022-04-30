package com.stock.market.stockprice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
/**
 * @author Ksp This method is used to configure mongoDb with spring boot
 *
 */
@Configuration
public class MongoConfig {
	/*
	 * Use the standard Mongo driver API to create a com.mongodb.client.MongoClient
	 * instance.
	 */
	@Value("${mongo.path}")
	private String mongoUrl;

	@Value("${mongo.databaseName}")
	private String databaseName;

	public String getMongoUrl() {
		return mongoUrl;
	}

	public void setMongoUrl(String mongoUrl) {
		this.mongoUrl = mongoUrl;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public @Bean MongoClient mongoClient() {
		return MongoClients.create(getMongoUrl());
	}

	public @Bean MongoTemplate mongoTemplate() {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(), getDatabaseName());
		((MappingMongoConverter) mongoTemplate.getConverter()).setTypeMapper(new DefaultMongoTypeMapper(null));// removes
																												// _class
		return mongoTemplate;
	}
}
