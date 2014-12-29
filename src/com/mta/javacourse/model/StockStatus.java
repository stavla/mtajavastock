package com.mta.javacourse.model;

import java.util.Date;

/**
 * a class that representing stock's Status 
 * @author STAV 
 */
public class StockStatus extends Stock {
	
	private static enum ALGO_RECOMMENDATION {
		DO_NOTHING,
		BUY,
		SELL
	}
	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	
	/**
	 * constructs new stock status  
	 * @param symbol  a stock's symbol
	 * @param ask a stock's ask price
	 * @param bid a stock's bid price
	 * @param date a stock's date
	 */
	public StockStatus(String symbol, float ask, float bid, Date date) {
		super(symbol, ask, bid, date);
		
		recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
		stockQuantity = 0;
	}

	/**
	 * constructs a new stockStatus from an existing stockStatus
	 * @param stockStatus - an existing stockStatus
	 */
	public StockStatus(StockStatus stockStatus) {
		this(stockStatus.getSymbol(), stockStatus.getAsk(), stockStatus.getBid(), new Date(stockStatus.getDate().getTime()));
	}

	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	

}
