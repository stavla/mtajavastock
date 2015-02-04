package com.mta.javacourse.model;

import java.util.Date;
import com.mta.javacourse.model.Portfolio.ALGO_RECOMMENDATION;

/**
 * a class that representing stock's Status 
 * @author STAV 
 */
public class StockStatus extends Stock {
	
	private ALGO_RECOMMENDATION recommendation = ALGO_RECOMMENDATION.DO_NOTHING;;
	private int stockQuantity = 0;
	
	public StockStatus() {
		super();
	}
	
	/**
	 * constructs new stock status  
	 * @param symbol  a stock's symbol
	 * @param ask a stock's ask price
	 * @param bid a stock's bid price
	 * @param date a stock's date
	 */
	public StockStatus(String symbol, float ask, float bid, Date date) {
		super(symbol, ask, bid, date);
	}
	
	/**
	 * constructs a new stockStatus from an existing Stock
	 * @param stock - an existing stock
	 */
	public StockStatus(Stock stock) {
		this(stock.getSymbol(), stock.getAsk(), stock.getBid(), new Date(stock.getDate().getTime()));
	}

	/**
	 * constructs a new stockStatus from an existing stockStatus
	 * @param stockStatus - an existing stockStatus
	 */
	public StockStatus(StockStatus stockStatus) {
		this(stockStatus.getSymbol(), stockStatus.getAsk(), stockStatus.getBid(), new Date(stockStatus.getDate().getTime()));
		
		this.recommendation = stockStatus.getRecommendation();
		this.stockQuantity = stockStatus.getStockQuantity();
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