package com.stock.market.company.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.stock.market.company.entity.UserDetail;
import com.stock.market.company.repository.UserDetailRepository;
import com.stock.market.company.service.impl.JwtUserDetailService;

@RunWith(MockitoJUnitRunner.class)
public class JwtUserDetailServiceTest {

	@InjectMocks
	JwtUserDetailService jwtUserDetailService;

	@Mock
	UserDetailRepository userDetailRepository;

	@Test
	public void testLoadUserByUsername() {
		UserDetail userDetail = UserDetail.builder().id("1").password("abc").username("abc").build();
		Mockito.when(userDetailRepository.findByusername("abc")).thenReturn(userDetail);
		assertNotNull(jwtUserDetailService.loadUserByUsername("abc"));
	}

	@Test
	public void testLoadUserByUsernameVaries() {
		UserDetail userDetail = UserDetail.builder().id("1").password("abc").username("abc").build();
		Mockito.when(userDetailRepository.findByusername("abc")).thenReturn(userDetail);
		try {
			jwtUserDetailService.loadUserByUsername("abcd");
		} catch (Throwable e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testLoadUserByUsernameNull() {
		Mockito.when(userDetailRepository.findByusername("abc")).thenReturn(null);
		try {
			jwtUserDetailService.loadUserByUsername("abcd");
		} catch (Throwable e) {
			assertNotNull(e);
		}
	}
	
	
}
