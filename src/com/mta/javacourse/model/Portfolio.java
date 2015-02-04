package com.mta.javacourse.model;

import java.util.*;
import com.mta.javacourse.exception.*;

/** 
 * a class that representing stock protfolio 
 * @author STAV
 */
public class Portfolio {

	public static enum ALGO_RECOMMENDATION {
		DO_NOTHING,
		BUY,
		SELL
	}
	
	public final static int MAX_PORTFOLIO_SIZE = 5;
	
	private String title;
	private StockStatus[] stockStatus;
	private int portfolioSize;
	private float balance;
	
	public Portfolio() {
		stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		portfolioSize = 0;
		balance = 0;
	}
	
	/**
	 * constructor that creates a new protfolio with a title
	 * @param title - name of portfolio
	 */
	public Portfolio(String title) {
		this();
		
		setTitle(title);
	}
	
	/**
	 * copy constructor duplicates portfolio 
	 * @param portfolio - an existing portfolio
	 */
	public Portfolio(Portfolio portfolio) {
		
		this(portfolio.getTitle());
		
		Stock[] stocks = portfolio.stockStatus;
		
		try{
			for (int i = 0; i < portfolioSize; i++)
				addStock(new Stock(stocks[i]));
		}catch (Exception e){
			e.printStackTrace();
		}	
	}
	
	public Portfolio(List<StockStatus> stockStatuses) {
		this();
		
		try{
			for (StockStatus stock : stockStatuses)
				addStock(stock);
		}catch (Exception e){
			e.printStackTrace();
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
	
	public float getBalance() {
		return balance;
	}
	
	public StockStatus[] getStocks() {
		return this.stockStatus;
	}
	
	/**
	 * method gets the value of the stocks in the portfolio
	 * @return the value of the stocks in the protfolio 
	 * */
	public float getStocksValue() {
		
		float sum = 0;
		
		for( int i = 0; i < portfolioSize; i++) {
			sum += stockStatus[i].getStockQuantity() * stockStatus[i].getBid();	
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
		htmlString += "balance: $" + getBalance() +"<br>";

		for (int i=0; i < portfolioSize; i++) {
			htmlString += stockStatus[i].getHtmlDescription() + "<br>";
		}
		
		return htmlString;
	}

	public StockStatus findBySymbol(String symbol) throws StockNotExistException {
		for (int i = 0; i < portfolioSize; i++) {
			if (stockStatus[i].getSymbol().equalsIgnoreCase(symbol)) {
				return stockStatus[i];
			}
		}
		
		throw new StockNotExistException(symbol);
	}
	
	/**
	 * method that added the stock to protfolio
	 * @param stock - a stock to added to protfolio
	 * @throws PortfolioFullException
	 * @throws StockAlreadyExistsException
	 */
	public void addStock(Stock stock) throws PortfolioFullException, StockAlreadyExistsException {
		
		if (portfolioSize >= MAX_PORTFOLIO_SIZE) 	
			throw new PortfolioFullException();
	
		String symbol = stock.getSymbol();
		
		for(int i = 0; i < portfolioSize; i++) {
			if (stockStatus[i].getSymbol().equalsIgnoreCase(symbol))
				throw new StockAlreadyExistsException(symbol);
		}
		
		stockStatus[portfolioSize] = new StockStatus(stock);
		portfolioSize++;
	}
	
	/**
	 * method that added the stock to protfolio
	 * @param stock - a stock to added to protfolio
	 * @throws PortfolioFullException
	 * @throws StockAlreadyExistsException
	 */
	public void addStock(StockStatus stock) throws PortfolioFullException, StockAlreadyExistsException {
		
		if (portfolioSize >= MAX_PORTFOLIO_SIZE) 	
			throw new PortfolioFullException();
	
		String symbol = stock.getSymbol();
		
		for(int i = 0; i < portfolioSize; i++) {
			if (stockStatus[i].getSymbol().equalsIgnoreCase(symbol))
				throw new StockAlreadyExistsException(symbol);
		}
		
		stockStatus[portfolioSize] = new StockStatus(stock);
		portfolioSize++;
	}
	
 	/**
	 * Removes a stock from the portfolio.
	 * @param symbol The symbol of the stock that should be removed from the portfolio.
	 * @return true if stock removed else false;
	 * @throws StockNotExistException
	 */
	public void removeStock(String symbol) throws StockNotExistException {
		int stockIndex = findIndexInStock(symbol);
		
		if (stockIndex == -1)
			throw new StockNotExistException(symbol);
		try{
			sellStock(symbol,-1);
			for (int i = stockIndex; i < portfolioSize - 1; i++) {	
				StockStatus statusTemp = stockStatus[i];
				
				stockStatus[i] = stockStatus[i+1];
				stockStatus[i+1] = statusTemp;
		}
		stockStatus[portfolioSize-1]= null;
		portfolioSize--;
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * method gets amount and check it. if negative - throw error, else set in balance
	 * @return current balance
	 * @throws BalanceException
	 */
	public void updateBalance(float amount) throws NegativeBalanceException {
		
		if(balance + amount < 0) {
			throw new NegativeBalanceException();
		}
		balance += amount;
	}

	
	/**
	 * method gets stock name and quantity and sell this stock
	 * @param symbol the symbol of stock
	 * @param quantity the amount of stocks to sell. -1 to sell all
	 * @return true if the sale successful else false
	 * @throws StockNotExistException
	 * @throws BalanceException
	 * @throws NotEnoughStocksException
	 * */
	public void sellStock(String symbol, int quantity) throws StockNotExistException, IllegalQuantityException, NotEnoughStocksException{
		
		int stockIndex = findIndexInStock(symbol);
		
		if (stockIndex < 0) 
			throw new StockNotExistException(symbol);

		StockStatus stock = stockStatus[stockIndex];
		
		if (stock.getStockQuantity() == 0)
			return;
		
		if (quantity == -1)
			quantity = stock.getStockQuantity();
		
		if (quantity < 1) 
			throw new IllegalQuantityException();			
		
		if (stock.getStockQuantity() < quantity) {
			System.out.println("canno't sell " + quantity + " , only " + stock.getStockQuantity() + " " + stock.getSymbol() + " stocks owend.");
			
			throw new NotEnoughStocksException(stock, quantity);
		}
		
		stock.setStockQuantity(stock.getStockQuantity()-quantity);
		balance += stock.getBid() * quantity;		
	}
	
	/**
	 * method gets stock name and quantity and buy this stock
	 * @param symbol the symbol of stock
	 * @param quantity the amount of stocks to sell. -1 to buy all
	 * @return true if the purchase was successful else false
	 * @throws StockNotExistException
	 * @throws BalanceException
	 * @throws NotEnoughBalanceException
	 * */
	public void buyStock(String symbol, int quantity) throws StockNotExistException, IllegalQuantityException, NotEnoughBalanceException {
		
		int stockIndex = findIndexInStock(symbol);
		
		if (stockIndex < 0) {
			throw new StockNotExistException(symbol);
		}
		
		StockStatus stock = stockStatus[stockIndex];
		
		if (quantity < 1) {
			throw new IllegalQuantityException();
		}
		
		if (stock.getAsk() * quantity > balance) {
			System.out.println("canno't buy " + quantity + " " + stock.getSymbol() + " stocks (value of $" + stock.getAsk() * quantity + "), only $" +balance + "in the balance.");
			
			throw new NotEnoughBalanceException(stock, quantity);
		}
		
		stock.setStockQuantity(stock.getStockQuantity() +quantity);
		balance -= stock.getAsk() * quantity;	
	}
	
	/**
	 * a method that find the stock in portfolio
	 * @param symbol to find in protfolio
	 * @return the index of the stock
	 * */
	private int findIndexInStock(String symbol) {
		
		for(int i = 0; i < portfolioSize; i++) {
			if(stockStatus[i].getSymbol().equalsIgnoreCase(symbol)) {
				return i;
			}
		}
		return -1;
	}

}
