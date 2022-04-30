package com.stock.market.company.controller;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.market.company.dto.JwtResponse;
import com.stock.market.company.service.impl.JwtUserDetailService;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;

/**
 * @author Ksp
 *
 */
@RestController
@RequestMapping("/api/v1.0/market/company")
@CrossOrigin(origins = "/**")
@Log4j2
public class AuthenticationController {

	@Value("${jwt.secret}")
	private String secret;

	@Autowired
	private JwtUserDetailService jwtInMemoryUserDetailsService;


	@GetMapping("/authenticate")
	public JwtResponse generateAuthenticationToken(@RequestHeader("Authorization") String authHeader)
			throws Exception {

		String username = getUser(authHeader);
		String token = generateToken(username);
		
		return new JwtResponse(token, username);

	}

	private String generateToken(String username) {
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(username);
		builder.setIssuedAt(new Date());
		builder.setExpiration(new Date(new Date().getTime() + 1200000));
		builder.signWith(SignatureAlgorithm.HS256, secret);

		return builder.compact();
	}

	private String getUser(String authHeader) {
		log.info("start get user method");
		String encodeCredentials = authHeader.split(" ")[1];
		byte[] encodedCredentials = Base64.getDecoder().decode(encodeCredentials);
		String user = new String(encodedCredentials).split(":")[0];
		return user;
	}

}
