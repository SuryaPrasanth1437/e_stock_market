package com.stock.market.stockprice.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stock.market.stockprice.entity.UserDetail;
import com.stock.market.stockprice.repository.UserDetailRepository;

import lombok.extern.log4j.Log4j2;
/**
 * @author Ksp
 *
 */
@Service
@Log4j2
public class JwtUserDetailService implements UserDetailsService {

	@Autowired
	private UserDetailRepository userDetailRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetail userdetail = userDetailRepository.findByusername(username);
		if (userdetail != null && userdetail.getUsername().equals(username)) {
			log.info("user found");
			return new User(userdetail.getUsername(), userdetail.getPassword(), new ArrayList<>());
		} else {
			log.error("user not found ");
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}
