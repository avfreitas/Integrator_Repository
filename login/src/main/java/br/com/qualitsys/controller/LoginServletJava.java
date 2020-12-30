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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServletJava
 */

@WebServlet(urlPatterns= {"/LoginServletJava"})
public class LoginServletJava extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServletJava() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String dataHoraExecucao = "Execução processada em: " + dtf.format(now); 

		HttpSession session = request.getSession();  

		String sessionId = session.getId();
		String sessionAtributoUsuario = (String) session.getAttribute("usuario");

		String usuario = request.getParameter("usuario");  
		String password = request.getParameter("password");  

		String msg1 = ("Usuário logado: " + usuario); 
		String msg2 = ("Session Id: " + sessionId); 
		
		if (usuario.equals(sessionAtributoUsuario)) {
			request.setAttribute("dataHoraExecucao", dataHoraExecucao);
			request.setAttribute("usuario", usuario);
			request.setAttribute("msg1", msg1);
			request.setAttribute("msg2", msg2);
					
	        String url = "/jsp01.jsp";
	        getServletContext().getRequestDispatcher(url).forward(request, response);
		}
		else {
			if(password.equals("admin123") && (usuario.equals("admin"))){
				session.setAttribute("usuario", usuario);
				request.setAttribute("dataHoraExecucao", dataHoraExecucao);
				request.setAttribute("usuario", usuario);
				request.setAttribute("msg1", msg1);
				request.setAttribute("msg2", msg2);
			
				String url = "/jsp01.jsp";
			    getServletContext().getRequestDispatcher(url).forward(request, response);
				
			}  
			else{
				out.print(dataHoraExecucao);
				out.print("<br>Desculpe, usuário ou password inválidos !!!");  
				
				getServletContext().getRequestDispatcher("/login.html").include(request, response);
			} 
		}
 		out.close(); 

	}

}



