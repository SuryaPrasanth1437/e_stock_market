package com.stock.market.stockprice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * @author Ksp Entity for userDetail
 *
 */
@Getter
@Setter
@Builder
@ToString
@Document("userDetail")
public class UserDetail {

	@Id
	private String id;
	@Field("username")
	private String username;
	@Field("password")
	private String password;
}