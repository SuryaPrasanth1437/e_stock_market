package com.stock.market.stockprice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stock.market.stockprice.entity.UserDetail;
/**
 * @author Ksp
 *
 */
@Repository
public interface UserDetailRepository extends MongoRepository<UserDetail, String>{

	UserDetail findByusername(String username);
}
