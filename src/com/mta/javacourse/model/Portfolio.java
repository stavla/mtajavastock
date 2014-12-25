package com.mta.javacourse.model;

import java.util.*;

/** 
 * a class that representing stock protfolio 
 * @author STAV
 */
public class Portfolio {

	private final static int MAX_PORTFOLIO_SIZE = 5;
	public enum ALGO_RECOMMENDATION {
		DO_NOTHING,
		BUY,
		SELL
	}
	
	private String title;
	private Stock[] stocks;
	private StockStatus[] stockStatus;
	private int portfolioSize;
	private float balance;
	
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
	
	public float getBalance() {
		return balance;
	}
	
	/**
	 * method gets the value of the stocks in the portfolio
	 * @return the value of the stocks in the protfolio 
	 * */
	public float getStocksValue() {
		
		float sum = 0;
		
		for( int i = 0; i < portfolioSize; i++) {
			sum += stockStatus[i].stockQuantity * stockStatus[i].currentBid;	
		}
		
		return sum;
	}
	
	/**
	 * method gets the total value of the portfolio
	 * @return the total value of the protfolio 
	 * */
	public float getTotalValue() {
		
		return balance + getStocksValue();
	}
	
	/** 
	 * method gets html string that represent the protfolio
	 * @return an html string that represent the protfolio
	 */
	public String getHtmlString() {
		
		String htmlString = "<h1>" + title + "</h1>";
		
		htmlString += "Total portfolio value: $" + getTotalValue() + "<br>";
		htmlString += "Total stocks value: $" + getStocksValue() + "<br>";
		htmlString += "balance: $" + getBalance();

		for (int i=0; i < portfolioSize; i++) {
			htmlString += stocks[i].getHtmlDescription() + "<br>";
		}
		
		return htmlString;
	}
	
	/**
	 * method that added the stock to protfolio
	 * @param stock - a stock to added to protfolio
	 */
	public boolean addStock(Stock stock) {
		if (portfolioSize >= MAX_PORTFOLIO_SIZE) {
			System.out.println("Can't add new stock, portfolio can only have " + portfolioSize + " stocks.");
			
			return false;
		}
		
		for(int i = 0; i < portfolioSize; i++) {
			if(stock.getSymbol() == stockStatus[i].symbol) {
				System.out.println(stock.getSymbol() + " already exists in the portfolio.");
				
				return false;
			}
		}
		
		stocks[portfolioSize] = stock;
		stockStatus[portfolioSize] = new StockStatus(stock);
		portfolioSize++;
		
		return true;
	}
	
 	/**
	 * Removes a stock from the portfolio.
	 * @param symbol The symbol of the stock that should be removed from the portfolio.
	 * @return true if stock removed else false;
	 */
	public boolean removeStock(String symbol) {
		int stockIndex = findIndexInStock(symbol);
		
		if (stockIndex == -1)
			return false;
		
		if (!sellStock(symbol,-1))
			return false;
		
		for (int i = stockIndex; i < portfolioSize - 1; i++) {
			
				Stock temp = stocks[i];
				
				stocks[i] = stocks[i+1];
				stocks[i+1] = temp;
				
				StockStatus statusTemp = stockStatus[i];
				
				stockStatus[i] = stockStatus[i+1];
				stockStatus[i+1] = statusTemp;
		}
		
		stocks[portfolioSize-1] = null;
		stockStatus[portfolioSize-1]= null;
		portfolioSize--;
		
		return true;
	}
	
	/**
	 * method gets amount and check it. if negative - print error, else set in balance
	 * @return current balance
	 * */
	public float updateBalanc(float amount){
		if(amount < 0){
			System.out.println("<b>Error: negative amount! </b>");
			return balance;
		}
		else
			return balance = amount;			
	}
	
	/**
	 * method gets stock name and quantity and sell this stock
	 * @param symbol the symbol of stock
	 * @param quantity the amount of stocks to sell. -1 to sell all
	 * @return true if the sale successful else false
	 * */
	public boolean sellStock(String symbol, int quantity) {
		
		int stockIndex = findIndexInStock(symbol);
		
		if (stockIndex < 0) {
			System.out.println("stock wasn't found");
			
			return false;
		}
		
		StockStatus status = stockStatus[stockIndex];
		
		if (quantity == -1)
			quantity = status.stockQuantity;
		
		if (quantity < 1) {
			System.out.println("cannot sell less than one stock");
			
			return false;
		}
		
		if (status.stockQuantity < quantity) {
			System.out.println("only " + status.stockQuantity + " " + status.symbol + " stocks owend. cannot sell " + quantity + ".");
			
			return false;
		}
		
		status.stockQuantity -= quantity;
		balance += status.currentBid * quantity;
		
		return true;
	}
	
	/**
	 * method gets stock name and quantity and buy this stock
	 * @param symbol the symbol of stock
	 * @param quantity the amount of stocks to sell. -1 to buy all
	 * @return true if the purchase was successful else false
	 * */
	public boolean buyStock(String symbol, int quantity) {
		
		int stockIndex = findIndexInStock(symbol);
		
		if (stockIndex < 0) {
			System.out.println("stock wasn't found");
			
			return false;
		}
		
		StockStatus status = stockStatus[stockIndex];
		
		if (quantity == -1)
			quantity = (int)(balance/ status.currentAsk);
		
		if (quantity < 1) {
			System.out.println("cannot buy less than one stock");
			
			return false;
		}
		
		if (status.currentAsk * quantity < balance) {
			System.out.println("only $" + balance + " in the balance. cannot buy " + quantity + " " + status.symbol + "stocks. (value of $" + status.currentAsk * quantity + ")");
			
			return false;
		}
		
		status.stockQuantity += quantity;
		balance -= status.currentAsk * quantity;
		
		return true;
		
	}
	
	/**
	 * a method that find the stock in portfolio
	 * @param symbol to find in protfolio
	 * @return the index of the stock
	 * */
	private int findIndexInStock(String symbol) {
		for(int i = 0; i<portfolioSize; i++) {
			if(stocks[i].getSymbol() == symbol) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * a class representing a stock's status 
	 * @author STAV
	 */
	private class StockStatus {
		
		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private ALGO_RECOMMENDATION recommendation;
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
			this.recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
			this.stockQuantity = 0;
		}
		
	}
}
