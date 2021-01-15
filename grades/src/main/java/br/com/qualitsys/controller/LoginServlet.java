package br.com.qualitsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.qualitsys.model.User;

@WebServlet(urlPatterns= {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static List<User> listaUser = new ArrayList<User>();
	
	static {
			listaUser.add(new User("aparecidovfreitas@gmail.com", "mhmarcam"));
			listaUser.add(new User("mario.longato@prof.uscs.edu.br", "maggiecindy"));
			listaUser.add(new User("renato.duarte@prof.uscs.edu.br", "carioca"));
	} 

	public LoginServlet() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(); 
			
		String usuario = request.getParameter("usuario");  
		String password = request.getParameter("password");  
		 
		if (validaUser(usuario, password)) {
			session.setAttribute("usuario", usuario);
			getServletContext().getRequestDispatcher("/controller01").forward(request, response);

		}  
		else{
			out.println("<font color=red>" + "<b> Desculpe, usuário ou password inválidos ! </b>" + "<font color=black>");
			getServletContext().getRequestDispatcher("/index.jsp").include(request, response);
		} 
		out.close(); 
	}

	public static boolean validaUser(String usuario, String password) {
		
		int size = LoginServlet.listaUser.size();
		
		for (int i=0; i<size; i++) {
			
			if ( listaUser.get(i).getUserName().equals(usuario)
						&&
				 listaUser.get(i).getPassword().equals(password)
			   )
					return true; 
		}	
		return false;
	}
}
	

