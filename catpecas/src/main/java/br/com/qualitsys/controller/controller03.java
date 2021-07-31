package br.com.qualitsys.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.qualitsys.model.ResultJoinItem;

//*-------------------------------------------------------------------------------------------------
//*------------       Módulo controller03        ---------------------------------------------------
//*-------------------------------------------------------------------------------------------------
//*---
//*------------       Salva na Session =>  atributo: codItemEscolhido ------------------------------
//*------------       Salva na Session =>  atributo: listagemItem ----------------------------------
//*------------       Salva na Session =>  atributo: listagemMontadoras ----------------------------
//*------------       Salva na Session =>  atributo: "msgerro"  c/código "00002" -------------------
//*--- 
//*--- 
//*-----------        Se item existe =====>  chama view jsp03 --------------------------------------
//*-----------        Se item não existe =>  chama view: jspitem com msgerro = "00002" -------------
//*-------------------------------------------------------------------------------------------------


@WebServlet("/controller03")
public class controller03 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public controller03() {

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
			//Recupera do Request o Codigo do Item (coditem) escolhido pelo usuário
			//* -----------------------------------------------------------------------

			String codItemEscolhido = request.getParameter("coditem");
		
			//* ------------------------------------------------------------------------------
			//* ------    Recupera Descrição do item escolhido pelo usuário  
			//* ------------------------------------------------------------------------------

			String preparedSQL =  "SELECT * FROM tabitem T where T.coditem =?";  
			PreparedStatement ps = conn.prepareStatement(preparedSQL);

			ps.setString(1,codItemEscolhido);
			ResultSet rs = ps.executeQuery();

			String descitem; 
			String codigosoriginais;
			String mercadoparalelo;

			ArrayList<ResultJoinItem> listagemItem = new ArrayList<ResultJoinItem>();

			while (rs.next() ) {

				descitem = rs.getString("descitem");
				codigosoriginais = rs.getString("codigosoriginais");
				mercadoparalelo = rs.getString("mercadoparalelo");

				ResultJoinItem rji = new ResultJoinItem(descitem,codigosoriginais, mercadoparalelo); 
				listagemItem.add(rji);
			}

			//* ------------------------------------------------------------------------------
			//* ------    Recupera Montadoras do Item escolhido 
			//* ------------------------------------------------------------------------------

			preparedSQL = 

					"SELECT DISTINCT  M.descmontadora AS 'Montadora'  " + 
							"FROM  montadora_item MI " + 

					" INNER JOIN montadora M" +   
					" ON  M.codmontadora = MI.codmontadora"  +

					"  INNER JOIN tabitem T " +
					" ON  T.coditem = MI.coditem "  +

					" where T.coditem =?  "  +
					" order by M.descmontadora"; 

			ps = conn.prepareStatement(preparedSQL);
			ps.setString(1, codItemEscolhido);
			rs = ps.executeQuery();

			String descMontadora; 
			ArrayList<String> listagemMontadoras = new ArrayList<String>();

			while (rs.next() ) {

				descMontadora = (String) rs.getString(1);
				listagemMontadoras.add(descMontadora);
			}	

			//* ---------------------------------------------------------
			//*----------  Fechamento de conexões com o SGBD  -----------
			//* ---------------------------------------------------------

			rs.close();
			ps.close();
			conn.close();	

			//*----------------------------------------------------------
			//* ------------    Salva dados na Session ------------------
			//* ---------------------------------------------------------

			if (listagemItem.size() == 0) {
				session.setAttribute("msgerro", "00002");
				getServletContext().getRequestDispatcher("/jspitem.jsp").forward(request, response);  
				
			}
			else {
				session.setAttribute("msgerro", " ");
				session.setAttribute("codItemEscolhido", codItemEscolhido);
				session.setAttribute("listagemMontadoras", listagemMontadoras);
				session.setAttribute("listagemItem", listagemItem);
				getServletContext().getRequestDispatcher("/jsp03.jsp").forward(request, response);  
			}
	 
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
}
}