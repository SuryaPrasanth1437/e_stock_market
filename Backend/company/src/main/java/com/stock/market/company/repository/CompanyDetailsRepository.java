package com.stock.market.company.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stock.market.company.entity.CompanyDetails;

@Repository
public interface CompanyDetailsRepository extends MongoRepository<CompanyDetails, String>{

}
