package com.mta.javacourse.service;

import java.util.GregorianCalendar;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;

public class PortfolioService {
	
	public Portfolio getPortfolio() {
		
		Stock st1 = new Stock("PIH", 10f, 8.5f, new GregorianCalendar(2014, 12, 15).getTime());
		Stock st2 = new Stock("AAL", 30f, 25.5f, new GregorianCalendar(2014, 12, 15).getTime());
		Stock st3 = new Stock("CAAS", 20f, 15.15, new GregorianCalendar(2014, 12, 15).getTime());
		
		Portfolio myPortfolio = new Portfolio("Portfolio #1");
		
		myPortfolio.addStock(st1);
		myPortfolio.addStock(st2);
		myPortfolio.addStock(st3);
		
		return myPortfolio;
	}

}
