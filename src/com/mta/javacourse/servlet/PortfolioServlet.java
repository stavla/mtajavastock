package com.mta.javacourse.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;
import com.mta.javacourse.service.PortfolioService;

@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
	
		PortfolioService portfolioService = new PortfolioService();
		
		Portfolio portfolio1 = portfolioService.getPortfolio();
		Portfolio portfolio2 = new Portfolio(portfolio1);
				
		portfolio2.setTitle("Portfolio #2");
				
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
				
		portfolio1.removeStock("PIH");
				
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
				
		portfolio2.getStocks()[portfolio2.getSize() - 1].setBid(55.55f);
				
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
	}
	
}
