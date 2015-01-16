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
		
		try{
			Portfolio portfolioA = portfolioService.getPortfolio();
			
			resp.getWriter().println(portfolioA.getHtmlString());
		}catch (Exception e){
			e.printStackTrace();
			resp.getWriter().println("<b>Error: " + e.getMessage() + "</b>");
		
	}
}
}
