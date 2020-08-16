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
 * Servlet implementation class Servlet_05
 */
@WebServlet(
		urlPatterns = { "/s5" }, 
		initParams = { 
				@WebInitParam(name = "PARAM1", value = "QUALITSYS"), 
				@WebInitParam(name = "PARAM2", value = "5")
		})
public class Servlet_05 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String param1; 
	private static String param2; 


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet_05() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

		System.out.println("INIT EXECUTADO....");

		//super.init(config);
		param1  = config.getInitParameter("PARAM1");
		param2 = config.getInitParameter("PARAM2");

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now)); 

		PrintWriter out = response.getWriter();

		String Titulo = "Qualitsys Consultoria de Informática - Servlet 05";

		out.println(
				"<HTML>" +
						"<HEAD><TITLE>" + Titulo + "</TITLE></HEAD>" +
						"<BODY BGCOLOR=\"#6699FF\">" +
							"Context Path: " + request.getContextPath()  + "</br>"+
							"Request URI: " + request.getRequestURI() + "</br>"+
							"Request URL: " + request.getRequestURL() + "</br>"+ 
							"Execução em: " + dtf.format(now) +"</br>"+ 
							"<H1 ALIGN=CENTER>" + Titulo + "</H1>" +
							"<OL>" + "<HR SIZE=4 WIDTH=50%>" + "</OL>" +
							"<h2 ALIGN=CENTER> PARAM1 = " + param1 + "</h2>" +
							"<h2 ALIGN=CENTER> PARAM2 = " + param2 + "</h2>" +
							"<OL>" + "<HR SIZE=4 WIDTH=50%>" + "</OL>" +
						"</BODY>" + 
				"</HTML>");

	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
