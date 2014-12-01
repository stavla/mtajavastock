package com.mta.javacourse.model;

import com.mta.javacourse.Stock;
import java.util.*;

public class Portfolio {

	private final static int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private Stock[] stocks; 
	private StockStatus[] stockStatus;
	private int portfolioSize;
	
	public Portfolio() {
		title = "New Portfolio";
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
	}
	
	public void addStock(Stock stock) {
		stocks[portfolioSize] = stock;
		portfolioSize++;
	}
	
	public Stock[] getStocks() {
		return stocks;
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
		private float currentBid , currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuantity;
		
	}
}
