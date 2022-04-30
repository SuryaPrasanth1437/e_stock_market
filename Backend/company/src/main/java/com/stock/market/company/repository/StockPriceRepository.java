package com.stock.market.company.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stock.market.company.entity.Price;


/**
 * @author Ksp
 *
 */
@Repository
public interface StockPriceRepository extends MongoRepository<Price, String> {

	List<Price> findAllByOrderByCreationDateDesc();
	List<Price> findByCompanyCodeOrderByCreationDateDesc(String companyCode);
	void deleteAllByCompanyCode(String companyCode);
}
