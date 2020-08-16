package br.com.qualitsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet_01
 */
@WebServlet("/s3")
public class Servlet_04 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet_04() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now)); 
		String resp;

		if (request.isSecure()) 
			resp = "Acesso feito de forma segura - HTTPS"; 
		else resp = "Acesso NÃO foi feito de forma segura, SEM HTTS";

		String titulo = "Servlet 03 - Olá QualitSys ...";
		PrintWriter out = response.getWriter();
		out.println(
				"<HTML>\n" +
						"<HEAD><TITLE>" + titulo + "</TITLE></HEAD>\n" +
						"<BODY BGCOLOR=\"#6699FF\">\n" +
						"Context Path: " + request.getContextPath()  + "</br>"+
						"Request URI: " + request.getRequestURI() + "</br>"+
						"Request URL: " + request.getRequestURL() + "</br>"+ 
						"Execução em: " + dtf.format(now) +"</br>"+ 
						resp + "</br>"+ 
						"<OL>" + "<HR SIZE=4 WIDTH=50%>" + 
						"<H1 ALIGN=center>" + titulo + "</H1>\n" +
						"<OL>" + "<HR SIZE=4 WIDTH=50%>" + 
						"<H1 ALIGN=center>" + "Qualitsys Consultoria de Informática Ltda" + "</H1>\n"); 







	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
