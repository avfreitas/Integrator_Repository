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


@WebServlet(urlPatterns= {"/forms01"})
public class forms01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public forms01() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now)); 

		String codaluno = request.getParameter("codaluno");
		PrintWriter out = response.getWriter();
		out.println(
				"<html>" +
						"<head><title>" + "Response - Código Aluno" + "</title></head>" +
						"<body bgcolor=\"#6699FF\">" +
							"Execução em: " + dtf.format(now) +"</br>"+ 

							"<ol>" + "<hr size=4 width=50%>" + "</ol>" +

							"<h2 align=center> Código do Aluno = " + codaluno + "</h2>" +
							"<ol>" + "<hr size=4 width=50%>" + "</ol>" +
						"</body>" + 
				"</ttml>");

	}
}    

