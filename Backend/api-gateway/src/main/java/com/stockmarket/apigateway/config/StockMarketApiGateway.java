package com.stockmarket.apigateway.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
/**
 * @author Ksp This method is used to configure the gateway for all microservice
 *
 */
@Configuration
public class StockMarketApiGateway {

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/api/v1.0/market/company/**").uri("http://localhost:8081"))
				.route(r -> r.path("/api/v1.0/market/authentication/**").uri("http://localhost:8081"))
				.route(r -> r.path("/api/v1.0/market/stock/**").uri("http://localhost:8082/"))
				.build();
	} 
	  @Bean
	    public CorsWebFilter corsWebFilter() {

	        final CorsConfiguration corsConfig = new CorsConfiguration();
	        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
	        corsConfig.setMaxAge(3600L);
	        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST"));
	        corsConfig.addAllowedHeader("*");

	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", corsConfig);

	        return new CorsWebFilter(source);
	    }  
}
