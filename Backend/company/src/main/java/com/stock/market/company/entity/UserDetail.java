package com.stock.market.company.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
