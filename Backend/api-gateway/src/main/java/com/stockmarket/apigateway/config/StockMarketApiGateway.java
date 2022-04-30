package com.stockmarket.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
				.route(r -> r.path("/api/v1.0/market/stock/**").uri("http://localhost:8082/"))
				.build();
	}
}
