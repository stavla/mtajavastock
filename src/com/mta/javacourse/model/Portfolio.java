package com.mta.javacourse.model;

import java.util.*;

/** 
 * a class that representing stock protfolio 
 * @author STAV
 */
public class Portfolio {

	private final static int MAX_PORTFOLIO_SIZE = 5;
	
	private String title;
	private Stock[] stocks;
	private StockStatus[] stockStatus;
	private int portfolioSize;
	
	/**
	 * constructor that creates a new protfolio with a title
	 * @param title - name of portfolio
	 */
	public Portfolio(String title) {
		setTitle(title);
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		portfolioSize = 0;
	}
	
	/**
	 * copy constructor duplicates portfolio 
	 * @param portfolio - an existing portfolio
	 */
	public Portfolio(Portfolio portfolio) {
		this("Copy of " + portfolio.getTitle());
		
		Stock[] stocks = portfolio.getStocks();
		
		int size = portfolio.getPortfolioSize();
		
		for (int i=0; i < size; i++) {
			addStock(stocks[i]);
			stockStatus[i] = new StockStatus(portfolio.stocks[i]);
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
	
	public int getSize(){
		return portfolioSize;
	}
	
	public Stock[] getStocks() {
		return stocks;
	}
	
	/**
	 * method that added the stock to protfolio
	 * @param stock - a stock to added to protfolio
	 */
	public void addStock(Stock stock) {
		if (portfolioSize < MAX_PORTFOLIO_SIZE) {
			stocks[portfolioSize] = stock;
			portfolioSize++;
		}
	}
	
 	/**
	 * Removes a stock from the portfolio.
	 * @param symbol The symbol of the stock that should be removed from the portfolio.
	 */
	public boolean removeStock(String symbol) {
		int size = getSize();
		boolean found = false;
		
		for (int i = 0; i < size - 1; i++) {
			if (found || stocks[i].getSymbol() == symbol) {
				Stock temp = stocks[i];
				
				stocks[i] = stocks[i+1];
				stocks[i+1] = temp;
				
				found = true;
			}
		}
		
		if (found || stocks[size-1].getSymbol() == symbol) {
			stocks[size-1] = null;
			portfolioSize--;
			
			return true;
		}
		return false;
	}
	
	/** 
	 * method gets html string that represent the protfolio
	 * @return an html string that represent the protfolio
	 */
	public String getHtmlString() {
		
		String htmlString = "<h1>" + title + "</h1>";
		
		for (int i=0; i < portfolioSize; i++) {
			htmlString = htmlString + stocks[i].getHtmlDescription() + "<br>";
		}
		
		return htmlString;
	}
	
	/**
	 * a class representing a stock's status 
	 * @author STAV
	 */
	private class StockStatus {
		
		private final static int DO_NOTHING = 0;
		private final static int BUY = 1;
		private final static int SELL = 2;
		
		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuantity;
		
		/**
		* Constructs a new StockStatus from an existing stock.
		* @param stock An existing stock.
		*/
		public StockStatus(Stock stock) {
			this.symbol = stock.getSymbol();
			this.currentAsk = stock.getAsk();
			this.currentBid = stock.getBid();
			this.date = stock.getDate();
			this.recommendation = 0;
			this.stockQuantity = 0;
		}
		
	}
}
