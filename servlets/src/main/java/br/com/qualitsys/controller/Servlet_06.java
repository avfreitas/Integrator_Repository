package br.com.qualitsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet_06
 */
@WebServlet("/s6")
		 
public class Servlet_06 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet_06() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now)); 
		
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    String Titulo = "SERVLET_06 - Coletando dados de um FORM HTML - Método HTTP - GET";
	  	out.println(	
	                "<HTML>" +
	                "<HEAD><TITLE>" + Titulo  + "</TITLE></HEAD>" +
	                "<BODY BGCOLOR=\"#6699FF\">" +
	                "<H1 ALIGN=\"CENTER\">" + Titulo + "</H1>" +
	                "<UL>" +
	                "<LI><B>param1</B>: "
	                + request.getParameter("param1")  +
	                "<LI><B>param2</B>: "
	                + request.getParameter("param2")  +
	                "</UL>" +
	                "</BODY></HTML>"
	     );
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
