package com.mta.javacourse.service;

import java.util.GregorianCalendar;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;

public class PortfolioService {
	
	public Portfolio getPortfolio() {
		
		Stock st1 = new Stock("PIH", 10f, 8.5f, new GregorianCalendar(2014, 11, 15).getTime());
		Stock st2 = new Stock("AAL", 30f, 25.5f, new GregorianCalendar(2014, 11, 15).getTime());
		Stock st3 = new Stock("CAAS", 20f, 15.5f, new GregorianCalendar(2014, 11, 15).getTime());
		
		Portfolio myPortfolio = new Portfolio("Exercise 7 portfolio");
		
		myPortfolio.addStock(st1);
		myPortfolio.addStock(st2);
		myPortfolio.addStock(st3);
		
		myPortfolio.updateBalance(10000);
		
		myPortfolio.buyStock("PIH", 20);
		myPortfolio.buyStock("AAL", 30);
		myPortfolio.buyStock("CAAS", 40);

		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		
		return myPortfolio;
	}

}
