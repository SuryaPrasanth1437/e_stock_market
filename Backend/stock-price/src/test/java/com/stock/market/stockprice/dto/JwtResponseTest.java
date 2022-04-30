package com.stock.market.stockprice.dto;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class JwtResponseTest {
	@Test
	public void testJwtResponse() {
		JwtResponse jwtResponse = new JwtResponse("1", "1");
		jwtResponse.setToken("1");
		jwtResponse.setUsername("1");
		assertNotNull(jwtResponse.getToken());
		assertNotNull(jwtResponse.getUsername());
	}
}
