package com.mta.javacourse.service;

import java.util.GregorianCalendar;

import com.mta.javacourse.Stock;
import com.mta.javacourse.model.Portfolio;

public class PortfolioService {
	
	public Portfolio getPortfolio() {
		
		Portfolio myPortfolio = new Portfolio();
		
		Stock st1 = new Stock();
		Stock st2 = new Stock();
		Stock st3 = new Stock();
		
		st1.setSymbol("PIH");
		st1.setAsk(12.4f);
		st1.setBid(13.1f);
		st1.setDate(new GregorianCalendar(2014, 10, 15).getTime());
		
		st2.setSymbol("AAL");
		st2.setAsk(5.5f);
		st2.setBid(5.78f);
		st2.setDate(new GregorianCalendar(2014, 10, 15).getTime());
		
		st3.setSymbol("CAAS");
		st3.setAsk(31.5f);
		st3.setBid(31.2f);
		st3.setDate(new GregorianCalendar(2014, 10, 15).getTime());
		
		myPortfolio.addStock(st1);
		myPortfolio.addStock(st2);
		myPortfolio.addStock(st3);
		
		return myPortfolio;
	}

}
