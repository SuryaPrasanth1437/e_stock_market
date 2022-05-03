package com.stock.market.company.entity;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.stock.market.company.dto.JwtResponse;

public class JwtResponseTest {

	@Test
	public void testJwtResponse() {
		JwtResponse jwtResponse= new JwtResponse( "1");
		jwtResponse.setToken("1");
		assertNotNull(jwtResponse.getToken());
	}
}
