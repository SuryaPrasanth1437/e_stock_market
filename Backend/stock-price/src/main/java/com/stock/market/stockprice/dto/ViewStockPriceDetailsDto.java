package com.stock.market.stockprice.dto;

import java.util.List;
import java.util.OptionalDouble;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
/**
 * @author Ksp
 *
 */
@Getter
@Setter
@Builder
public class ViewStockPriceDetailsDto {

	private double min;
	private double max;
	private OptionalDouble average;
	private List<Double> stockPriceList;
}
