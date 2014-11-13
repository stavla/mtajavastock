package com.mta.javacourse;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Java_MISServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		int num1 = 4, num2 = 3, num3 = 7;
		
		int result = (num1 + num2) * num3;
		
		String resultStr = new String("<h1>Result of (" + num1 + "+" + num2 + ")*" + num3 + "=" + result + "</h1>");
		
		resp.getWriter().println(resultStr);

		int radius = 50;
		
		String line1 = new String("<p><b>Calculation 1:</b> Area of circle with radius " + radius + " is : " + calculateCircleArea(radius) + " square-cm.</p>");
		
		resp.getWriter().println(line1);
		
		int angleB = 30,  hypotenuseLength = 50;
		double angleBInRadians = Math.toRadians(angleB);
		
		String line2 = new String("<p><b>Calculation 2:</b> Length of opposite where angle B is " + angleB +" degrees and Hypotenuse length is " + hypotenuseLength + " cm is: " + calculateLengthOfOpposite(angleBInRadians, hypotenuseLength) + " cm </p>");
		
		resp.getWriter().println(line2);
		
		int base = 20, exp = 13;
		
		String line3 = new String("<p><b>Calculation 3:</b> Power of " +base+ " with exp of "+exp + " is: " + (long)Math.pow(base, exp) + "</p>");
		
		resp.getWriter().println(line3);
		
	}
	
	double calculateCircleArea(int radius){
		return Math.PI*(Math.pow(radius, 2));
	}
	
	double calculateLengthOfOpposite(double angle, int hypotenuseLength){
		return Math.sin(angle)*hypotenuseLength;
	}
	
}
