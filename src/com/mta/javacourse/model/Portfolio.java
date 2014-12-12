package com.mta.javacourse.model;

import java.util.*;

public class Portfolio {

	private final static int MAX_PORTFOLIO_SIZE = 5;
	
	private String title;
	private Stock[] stocks;
	private StockStatus[] stockStatus;
	private int portfolioSize;
	
	public Portfolio(String title) {
		setTitle(title);
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		portfolioSize = 0;
	}
	
	public Portfolio(Portfolio portfolio) {
		this("Copy of " + portfolio.getTitle());
		
		Stock[] stocks = portfolio.getStocks();
		
		int size = portfolio.getPortfolioSize();
		
		for (int i=0; i < size; i++) {
			addStock(stocks[i]);
			stockStatus[i] = new StockStatus(portfolio.stockStatus[i]);
		}
		
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public int getPortfolioSize() {
		return portfolioSize;
	}
	
	public Stock[] getStocks() {
		return stocks;
	}
	
	public void addStock(Stock stock) {
		stocks[portfolioSize] = stock;
		portfolioSize++;
	}

	public String getHtmlString() {
		
		String htmlString = "<h1>" + title + "</h1>";
		
		for (int i=0; i < portfolioSize; i++) {
			htmlString = htmlString + stocks[i].getHtmlDescription() + "<br>";
		}
		
		return htmlString;
	}
	
	private class StockStatus {
		
		private final static int DO_NOTHING = 0;
		private final static int BUY = 1;
		private final static int SELL = 2;
		
		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuantity;
		
		public StockStatus(StockStatus stockStatus) {
			symbol = stockStatus.symbol;
			currentBid = stockStatus.currentBid;
			currentAsk = stockStatus.currentAsk;
			date = stockStatus.date;
			recommendation = stockStatus.recommendation;
			stockQuantity = stockStatus.stockQuantity;
		}
		
	}
}
