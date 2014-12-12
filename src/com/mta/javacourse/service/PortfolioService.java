package com.mta.javacourse.service;

import java.util.GregorianCalendar;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;

public class PortfolioService {
	
	public Portfolio getPortfolio() {
		
		Portfolio myPortfolio = new Portfolio("New Portfolio");
		
		Stock st1 = new Stock("PIH", 12.4f, 13.1f, new GregorianCalendar(2014, 10, 15).getTime());
		Stock st2 = new Stock("AAL", 5.5f, 5.78f, new GregorianCalendar(2014, 10, 15).getTime());
		Stock st3 = new Stock("CAAS", 31.5f, 31.2f, new GregorianCalendar(2014, 10, 15).getTime());
		
		myPortfolio.addStock(st1);
		myPortfolio.addStock(st2);
		myPortfolio.addStock(st3);
		
		return myPortfolio;
	}

}
