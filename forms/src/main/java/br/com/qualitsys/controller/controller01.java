package br.com.qualitsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.qualitsys.model.Aluno;


@WebServlet(urlPatterns= {"/controller01"})
public class controller01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static List<Aluno> listAlunos  = new ArrayList<Aluno>(); 
	
	public controller01() {
		super();
	}
	
	
    public void init() throws ServletException {
		
		Aluno a1 = new Aluno("111111", "Flávio Alberto Dutra","11 - 42246189", "11 - 983282529", 
				"flavio.adore@gmail.com", "flavioalberto.dutra@uscsonline.com.br",
				"109 - CIÊNCIA DA COMPUTAÇÃO"); 
		listAlunos.add(a1); 
		
		Aluno a2 = new Aluno("222222", "Lucas Silva Adamussi","11 - 22692576" , "11 - 960741595", 
				"lucasadamussi@gmail.com" , "lucas.adamussi4627@uscsonline.com.br",
				"109 - CIÊNCIA DA COMPUTAÇÃO"); 
		listAlunos.add(a2); 

		Aluno a3 = new Aluno("333333", "Marcus Vinicius Silva ","11 - 34568877" , "11 - 987651209", 
				"marcusvinicius@gmail.com" , "marcus.vinicius4627@uscsonline.com.br",
				"110 - SISTEMAS DE INFORMAÇÃO"); 
		listAlunos.add(a3); 

		Aluno a4 = new Aluno("444444", "Paulo Rodrigo Souza ","11 - 76540987" , "11 - 965210099", 
				"paulorodrigo@gmail.com" , "paulo.rodrigo1@uscsonline.com.br",
				"110 - SISTEMAS DE INFORMAÇÃO"); 
		listAlunos.add(a4);
		
		Aluno a5 = new Aluno("555555", "Ana Paula Tomé ","11 - 76540051" , "11 - 956770991", 
				"anapaulatome@gmail.com" , "ana.tome2@uscsonline.com.br",
				"141 - SISTEMAS DE INFORMAÇÃO"); 
		listAlunos.add(a5);
		
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
		response.setContentType("text/html;charset=utf-8");
		System.out.println(codaluno);
		
		Integer size = listAlunos.size();
		int i =0; 
		for (; i < size; i++) {
			System.out.println(codaluno);
			System.out.println(listAlunos.get(i).getCodAluno());
			
			if (listAlunos.get(i).getCodAluno().equals(codaluno) ) {
				
				
				out.println(
						"<html>" +
								"<head><title>" + "Response - Código Aluno" + "</title></head>" +
								"<body bgcolor=\"#6699FF\">" +
									"Execução em: " + dtf.format(now) +"</br>"+ 

									"<ol>" + "<hr size=4 width=50%>" + "</ol>" +

									"<h2 align=center> Código do Aluno: " + codaluno + "</h2>" +
									"<h2 align=center> Nome: " + listAlunos.get(i).getNomeAluno() + "</h2>" +
									"<h2 align=center> Telefone 1: " + listAlunos.get(i).getFone1Aluno() + "</h2>" +
									"<h2 align=center> Telefone 2: " + listAlunos.get(i).getFone2Aluno() + "</h2>" +
									"<h2 align=center> Email Pessoal: " + listAlunos.get(i).getEmailPessoalAluno() + "</h2>" +
									"<h2 align=center> Email Google: " + listAlunos.get(i).getEmailGoogleAluno() + "</h2>" +
									"<h2 align=center> Curso: " + listAlunos.get(i).getCursoAluno() + "</h2>" +
									"<ol>" + "<hr size=4 width=50%>" + "</ol>" +
								"</body>" + 
						"</html>");
				break;
			}
		}
		
		if (i == size) {
			
			String url = "/resp02.html";
			getServletContext().getRequestDispatcher(url).forward(request,response);
		}
	}
}    

