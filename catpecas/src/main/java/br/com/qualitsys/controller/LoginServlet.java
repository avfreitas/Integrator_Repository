//*-------------------------------------------------------------------------------------------------
//*------------       Módulo LoginServlet   --------------------------------------------------------
//*-------------------------------------------------------------------------------------------------

package br.com.qualitsys.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.qualitsys.model.User;

//*-------------------------------------------------------------------------------------------------
//*------------       Salva na Session =>  atributo: "usuario"  ------------------------------------
//*------------       Salva na Session =>  atributo: "msgerro"  c/código "00001" -------------------

//*-------------------------------------------------------------------------------------------------
//*------------       Leitura no Request =>  "usuario" ---------------------------------------------
//*------------       Leitura no Request =>  "password" --------------------------------------------

//*-------------------------------------------------------------------------------------------------
//*-----------        Se login ok =>  chama view jspmain -------------------------------------------
//*-----------        Se erro de login =>  chama view: index.jsp com msgerro = "00001" -------------
//*-------------------------------------------------------------------------------------------------

@WebServlet(urlPatterns= {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static List<User> listaUser = new ArrayList<User>();
	
	
	//* --------------------------------------------------------------------------------------------
	//* ------  Lista de usuários válidos  ---------------------------------------------------------
	//* --------------------------------------------------------------------------------------------

	static {
			listaUser.add(new User("aparecidovfreitas@gmail.com", "mhmarcam"));
			listaUser.add(new User("leandro@gmail.com", "leandro"));
			listaUser.add(new User("x@gmail.com", "x"));
			listaUser.add(new User("pedro@gmail.com", "pedro"));
			listaUser.add(new User("mario.longato@prof.uscs.edu.br", "maggiecindy"));
			listaUser.add(new User("ricardo.mendonca@prof.uscs.edu.br","#MestreRicardo#2021"));
			listaUser.add(new User("luxfacta@gmail.com","luxfacta"));
			listaUser.add(new User("renato.duarte@prof.uscs.edu.br", "carioca"));
	} 

	public LoginServlet() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(); 
		
		//*---------------------------------------------------------------------------------
		//*-------------  Leitura de "usuario" e "password" da Session ---------------------
		//*---------------------------------------------------------------------------------
		
		String usuario = request.getParameter("usuario");  
		String password = request.getParameter("password");  
		
		//*---------------------------------------------------------------------------------
		//*-------------  Chamada do método validaUser para validar Login  -----------------
		//*---------------------------------------------------------------------------------
	
		if (validaUser(usuario, password)) {
				session.setAttribute("usuario", usuario);
				session.setAttribute("msgerro", " ");
				getServletContext().getRequestDispatcher("/jspmain.jsp").forward(request, response);
		}
 		else {
 				session.setAttribute("msgerro","00001");
 				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		} 

	}

	//* -------------------------------------------------------------------------------------------------
	//* --------- Método validaUser para validação de usuário -------------------------------------------
	//* -------------------------------------------------------------------------------------------------
	
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
	

