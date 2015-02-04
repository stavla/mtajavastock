package com.mta.javacourse.model;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * a class that representing stock 
 * @author STAV 
 */
public class Stock {
	
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	private transient SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Stock() {
	}
	
	/**
	 * constructor set symbol, ask, bid, date in class 
	 * @param symbol  a stock symbol
	 * @param ask a stock ask price
	 * @param bid a stock bid price
	 * @param date a stock date
	 */
	public Stock(String symbol, float ask, float bid, Date date) {
		setSymbol(symbol);
		setAsk(ask);
		setBid(bid);
		setDate(date);
	}
	
	/**
	 * copy constructor duplicates stock
	 * @param stock - an existing stock
	 */
	public Stock(Stock stock){
		setSymbol(stock.getSymbol());
		setAsk(stock.getAsk());
		setBid(stock.getBid());
		setDate(new Date(stock.getDate().getTime()));
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getAsk() {
		return ask;
	}

	public void setAsk(float ask) {
		this.ask = ask;
	}

	public float getBid() {
		return bid;
	}

	public void setBid(float bid) {
		this.bid = bid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * method gets html string that represent the stock
	 * @return an html string that represent the stock
	 */
	public String getHtmlDescription() {
		return "<b>Stock symbol:</b> " + getSymbol() + ", <b>ask:</b> " + getAsk() + ", <b>bid:</b> " + getBid() + ", <b>date:</b> " + sdf.format(getDate());		
	}
	
}