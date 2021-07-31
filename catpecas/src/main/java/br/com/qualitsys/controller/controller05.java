package br.com.qualitsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.qualitsys.model.ResultJoin;


//*-------------------------------------------------------------------------------------------------
//*------------            Módulo controller05      ------------------------------------------------
//*-------------------------------------------------------------------------------------------------
//*------------        Salva na Session =>  atributo: searchitem -----------------------------------
//*-------------------------------------------------------------------------------------------------

@WebServlet("/controller05")
public class controller05 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public controller05() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(); 
		if (session.getAttribute("usuario") == null)
			getServletContext().getRequestDispatcher("/jsperrologin.jsp").forward(request, response);
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		Connection conn;
		
		try {
			
			//conn = DBHandlerLocal.getConn();
			conn = DBHandlerIntegrator.getConn();
		
			//* -----------------------------------------------------------------------
			//Recupera do Request o texto dos Itens definido pelo usuário
			//* -----------------------------------------------------------------------

			String searchitem = request.getParameter("searchitem");
	
			//* -----------------------------------------------------------------------
			//Recupera do Request o código da Montadora escolhido pelo usuário 
			//* -----------------------------------------------------------------------
			
			String codmontadora = request.getParameter("codmontadora");
			out.println("Código da Montadora escolhido pelo usuário = " + codmontadora);
			
			String query =  "SELECT DISTINCT M.descmontadora AS Montadora, T.mercadoparalelo AS 'Mercado Paralelo',   " +
			
					"T.coditem AS 'Código Interno', T.descitem AS 'Descrição do Item', T.codigosoriginais AS 'Códigos Originais'  " +
					
					"FROM montadora_item MI  " + 
					
					"INNER JOIN  tabitem T ON T.coditem = MI.coditem  " +
					
					"INNER JOIN  montadora M ON M.codmontadora = MI.codmontadora " +
					
					"WHERE T.descitem like " + "'%" + searchitem + "%'"  + "  "  + " and M.codmontadora = " + codmontadora + "  "  +
					
					"ORDER BY M.descmontadora , T.coditem " ;
			
			Statement stmt = conn.createStatement();
					
			ResultSet rs = stmt.executeQuery(query);
 
			ArrayList<ResultJoin> listagemItens = new ArrayList<ResultJoin>();

			 String descmontadora; 
			 String mercadoparalelo;
			 String coditem;
			 String descitem;
			 Object imagemitem = null;
			 String codigosoriginais;
			
		
			while (rs.next() ) {

				descmontadora = rs.getString(1);
				mercadoparalelo = rs.getString(2);
				coditem = rs.getString(3); 
				descitem = rs.getString(4);
				codigosoriginais = rs.getString(5);
			
				ResultJoin rj = new ResultJoin(descmontadora, mercadoparalelo, coditem, descitem,imagemitem, codigosoriginais ); 
				listagemItens.add(rj);
			}
			
			//* ---------------------------------------------------------
			//*----------  Fechamento de conexões com o SGBD  -----------
			//* ---------------------------------------------------------

			rs.close();
			conn.close();	

			//*----------------------------------------------------------
			//* ------------    Salva dados na Session ------------------
			//* ---------------------------------------------------------

			if (listagemItens.size() == 0) {
				session.setAttribute("msgerro", "00003");
				getServletContext().getRequestDispatcher("/jsp04.jsp").forward(request, response);  
				
			}
			else {
				session.setAttribute("msgerro", " ");
				session.setAttribute("codmontadora", codmontadora);
				session.setAttribute("searchitem", searchitem);
				session.setAttribute("listagemItens", listagemItens);
				getServletContext().getRequestDispatcher("/jsp05.jsp").forward(request, response);  
			}
 
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
}
}