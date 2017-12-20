package com.xdhuxc.integration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	
	private static final long serialVersionUID = -5721269588722054531L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		response.setContentType("text/html;charset=GBK");
		
		System.out.println("It is your name :" + username);
		System.out.println("It is your password :" + password);
	
		response.getWriter().println("<html>");
		response.getWriter().print("<head>");
		response.getWriter().println("<title>登录成功</title>");
		response.getWriter().println("</head>");
		response.getWriter().println("<body>");
		response.getWriter().println("Welcome " + username + " using our website");
		response.getWriter().println("\n");
		response.getWriter().println("Your password is " + password);
		response.getWriter().println("\n");
		response.getWriter().println("It is a Continue Integration Test");
		response.getWriter().println("</body>");
		response.getWriter().println("</html>");
	}

}
