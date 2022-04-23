package com.stock.market.company.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stock.market.company.entity.UserDetail;

@Repository
public interface UserDetailRepository extends MongoRepository<UserDetail, String>{

	UserDetail findByusername(String username);
}
