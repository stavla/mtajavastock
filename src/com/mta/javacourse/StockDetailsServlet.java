package com.mta.javacourse;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.*;

import com.mta.javacourse.model.Stock;

@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Stock st1 = new Stock("PIH", 12.4f, 13.1f, new GregorianCalendar(2014, 10, 15).getTime());
		Stock st2 = new Stock("AAL", 5.5f, 5.78f, new GregorianCalendar(2014, 10, 15).getTime());
		Stock st3 = new Stock("CAAS", 31.5f, 31.2f, new GregorianCalendar(2014, 10, 15).getTime());
		
		resp.getWriter().println(st1.getHtmlDescription() + "<br>");
		resp.getWriter().println(st2.getHtmlDescription() + "<br>");
		resp.getWriter().println(st3.getHtmlDescription() + "<br>");
		
	}

}