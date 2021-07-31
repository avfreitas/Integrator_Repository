package br.com.qualitsys.controller;

import java.io.IOException;
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
//*------------       Módulo controller06        ---------------------------------------------------
//*-------------------------------------------------------------------------------------------------
//*---
//*------------       Salva na Session =>  atributo: listagemDesc  ---------------------------------
//*------------       Salva na Session =>  atributo: "msgerro"  c/código "00008" -------------------
//*--- 
//*--- 
//*-----------        Se itens existem =====>  chama view jsp07 --------------------------------------
//*-----------        Se itens não existem =>  chama view: jspdec com msgerro = "00008" ------
//*---------------------------------------------------------------------------------------------------


@WebServlet("/controller06")
public class controller06 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public controller06() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		//* -----------------------------------------------------------------------------
		//* ---------  Bloqueia chamada direta desse servlet pelo usuário ---------------
		//* -----------------------------------------------------------------------------
			
		HttpSession session = request.getSession(); 
		if (session.getAttribute("usuario") == null)
			getServletContext().getRequestDispatcher("/jsperrologin.jsp").forward(request, response);
		
		Connection conn;
		
		try {
			
			//conn = DBHandlerLocal.getConn();
			conn = DBHandlerIntegrator.getConn();
		
			//* -----------------------------------------------------------------------
			//Recupera do Request o Argumento de Pesquisa:  searchcdesc
			//* -----------------------------------------------------------------------

			String searchdesc = request.getParameter("searchdesc");
		
		
			String query =  "SELECT DISTINCT M.descmontadora AS Montadora, T.mercadoparalelo AS 'Mercado Paralelo',   " +
			
					"T.coditem AS 'Código Interno', T.descitem AS 'Descrição do Item', T.codigosoriginais AS 'Códigos Originais'  " +
					
					"FROM montadora_item MI  " + 
					
					"INNER JOIN  tabitem T ON T.coditem = MI.coditem  " +
					
					"INNER JOIN  montadora M ON M.codmontadora = MI.codmontadora " +
					
					"WHERE T.descitem like " + "'%" + searchdesc + "%'"  + "  "  + 
					
					"ORDER BY M.descmontadora , T.coditem " ;
					
					
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
 
			ArrayList<ResultJoin> listagemDesc = new ArrayList<ResultJoin>();

			 String descmontadora; 
			 String mercadoparalelo;
			 String coditem;
			 String descitem;
			 String codigosoriginais;

			while (rs.next() ) {

				descmontadora = rs.getString(1);
				coditem = rs.getString(3);
				descitem = rs.getString(4);
				codigosoriginais = rs.getString(5);
				mercadoparalelo = rs.getString(2);

				ResultJoin rjdesc = new ResultJoin(descmontadora, mercadoparalelo, coditem, descitem," ", codigosoriginais); 
				listagemDesc.add(rjdesc);
			}

			//* ---------------------------------------------------------
			//*----------  Fechamento de conexões com o SGBD  -----------
			//* ---------------------------------------------------------

			rs.close();
			conn.close();	

			//*----------------------------------------------------------
			//* ------------    Salva dados na Session ------------------
			//* ---------------------------------------------------------

			if (listagemDesc.size() == 0) {
				session.setAttribute("msgerro", "00008");
				getServletContext().getRequestDispatcher("/jspdesc.jsp").forward(request, response);  
				
			}
			else {
				session.setAttribute("msgerro", " ");
				session.setAttribute("listagemDesc", listagemDesc);
				getServletContext().getRequestDispatcher("/jsp07.jsp").forward(request, response);  
			}
	 
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
}
}