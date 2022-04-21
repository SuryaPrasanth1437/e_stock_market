package com.stock.market.stockprice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stock.market.stockprice.entity.Price;

@Repository
public interface StockPriceRepository extends MongoRepository<Price, String> {

}
