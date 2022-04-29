package com.stock.market.company.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class UserDetailTest {

	@Test
	public void testUserDetail() {
		UserDetail userDetail= UserDetail.builder().id("1").password("abc").username("abc").build();
		assertEquals("1", userDetail.getId());
		assertEquals("abc", userDetail.getPassword());
		assertEquals("abc", userDetail.getUsername());
		assertNotNull(userDetail.builder().toString());
	}
}
