package br.com.qualitsys.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns= {"/controller01"})
public class controller01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public controller01() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String dataHoraExecucao = "Execução processada em: " + dtf.format(now); 
	
		String codaluno = request.getParameter("codaluno");
		System.out.println("Código do aluno: " +  codaluno);
		
		request.setAttribute("codaluno", codaluno);
		request.setAttribute("dataHoraExecucao", dataHoraExecucao);
		
		String url = "/jsp01.jsp";
		getServletContext().getRequestDispatcher(url).forward(request, response);
	
	}
	
}    

