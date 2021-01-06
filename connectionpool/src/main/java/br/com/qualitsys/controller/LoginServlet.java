package br.com.qualitsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServletJava
 */

@WebServlet(urlPatterns= {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(); 
	
		String usuario = request.getParameter("usuario");  
		String password = request.getParameter("password");  

		if (usuario.equals("aparecidovfreitas@gmail.com") && (password.equals("mhmarcam"))) {

			session.setAttribute("usuario", usuario);
			getServletContext().getRequestDispatcher("/jsp01.jsp").forward(request, response);

		}  
		else{
			out.println("<b> Desculpe, usuário ou password inválidos ! </b>");
			getServletContext().getRequestDispatcher("/index.jsp").include(request, response);
		} 
		out.close(); 
	}
}



