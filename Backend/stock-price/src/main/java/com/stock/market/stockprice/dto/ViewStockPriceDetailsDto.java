package com.stock.market.stockprice.dto;

import java.util.List;
import java.util.OptionalDouble;

import lombok.Builder;
import lombok.Getter;
/**
 * @author Ksp
 *
 */
@Getter
@Builder
public class ViewStockPriceDetailsDto {

	private double min;
	private double max;
	private OptionalDouble average;
	private List<Double> stockPriceList;
}
