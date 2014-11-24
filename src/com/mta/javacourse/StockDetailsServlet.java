package com.mta.javacourse;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
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
		
		resp.getWriter().println(st1.getHtmlDescription() + "<br>");
		resp.getWriter().println(st2.getHtmlDescription() + "<br>");
		resp.getWriter().println(st3.getHtmlDescription() + "<br>");
		
	}

}