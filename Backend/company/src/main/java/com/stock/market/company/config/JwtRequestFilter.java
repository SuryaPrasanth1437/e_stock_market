package com.stock.market.company.config;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

@Component
public class JwtRequestFilter extends BasicAuthenticationFilter {
	@Value("${jwt.secret}")
	private String secret;

	public JwtRequestFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader("Authorization");
//		if (header == null || !header.startsWith("Bearer ")) {
		if (header != null && header.startsWith("Basic ")) {
			chain.doFilter(req, res);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {

		String token = req.getHeader("Authorization");
		if (token != null) {
			Jws<Claims> jws;
			try {

				jws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token.replace("Bearer ", ""));
				String user = jws.getBody().getSubject();
				if (user != null) {
					return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
				}
			} catch (JwtException ex) {
				return null;
			}
		}
		return null;
	}

}
