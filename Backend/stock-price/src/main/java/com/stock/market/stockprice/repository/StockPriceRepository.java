package com.stock.market.stockprice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stock.market.stockprice.entity.Price;
/**
 * @author Ksp
 *
 */
@Repository
public interface StockPriceRepository extends MongoRepository<Price, String> {

	List<Price> findByCompanyCodeAndCreationDateBetween(String companyCode,Date startDate, Date endDate);
}
