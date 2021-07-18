package br.com.qualitsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import br.com.qualitsys.model.Categoria;
import br.com.qualitsys.model.Montadora;
import br.com.qualitsys.model.ResultJoin;

//*------------------------------------------------------------------
//*-- Salva na Session =>  atributo: "listagemItens" 
//*-- Salva na Session =>  atributo: "nomeCategoriaEscolhida" 
//*-- Salva na Session =>  atributo: "nomeMontadoraEscolhida" 
//*-- Salva na Session =>  atributo: "codCategoriaEscolhida" 
//*-- Salva na Session =>  atributo: "codMontadoraEscolhida" 
//*------------------------------------------------------------------


@WebServlet("/controller02")
public class controller02 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public controller02() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		//Recupera do Request a Categoria escolhido pelo usuário
		String codCategoriaEscolhida = request.getParameter("codcategoria");
		out.println("Categoria escolhida = " + codCategoriaEscolhida);

		//Recupera do Request a Categoria escolhido pelo usuário
		String codMontadoraEscolhida = request.getParameter("codmontadora");
		out.println("Montadora escolhida = " + codMontadoraEscolhida);

		HttpSession session = request.getSession(); 

		@SuppressWarnings("unchecked")
		ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>)session.getAttribute("listaCategorias");
		@SuppressWarnings("unchecked")
		ArrayList<Montadora> listaMontadoras = (ArrayList<Montadora>)session.getAttribute("listaMontadoras");

		//* ------------------------------------------------
		//* --  Recupera nome da Categoria escolhida  ------
		//* ------------------------------------------------

		String nomeCategoriaEscolhida = null;
		int size1 = listaCategorias.size();

		for (int i=0; i<size1;i++)  
			if(listaCategorias.get(i).getCodCategoria().equals(codCategoriaEscolhida)) {
				nomeCategoriaEscolhida = listaCategorias.get(i).getDescCategoria();
				break;
			}

		//* ------------------------------------------------
		//* --  Recupera nome da Montadora escolhida  ------
		//* ------------------------------------------------

		String nomeMontadoraEscolhida = null;
		int size2 = listaMontadoras.size();

		for (int i=0; i<size2;i++)  
			if(listaMontadoras.get(i).getCodMontadora().equals(codMontadoraEscolhida)) {
				nomeMontadoraEscolhida = listaMontadoras.get(i).getDescMontadora();
				break;
			}

		Connection conn;
		
		try {
			
			conn = DBHandlerLocal.getConn();

			String preparedSQL = 

					"SELECT DISTINCT  M.descmontadora AS Montadora , " + 
							"T.mercadoparalelo AS 'Mercado Paralelo ', " +
							"T.coditem AS 'Código Interno ', " + 
							"T.descitem AS 'Descrição do Ítem ', " +
							"T.imagemitem AS 'Imagem do Ítem ' " + 

							"FROM montadora_item MI " + 

							"INNER JOIN tabitem T " + 
							"ON  T.coditem = MI.coditem " +  

							"INNER JOIN aplicacao A  " +  
							"ON A.coditem = MI.coditem " +

							"INNER JOIN montadora M " + 
							"ON  M.codmontadora =? " +

							"where T.codcategoria =?  " +    
							"ORDER BY T.coditem , M.descmontadora " ; 

			PreparedStatement ps = conn.prepareStatement(preparedSQL);

			ps.setString(1, codMontadoraEscolhida);
			ps.setString(2, codCategoriaEscolhida);
			

			ResultSet rs = ps.executeQuery();

			String descmontadora; 
			String mercadoparalelo;
			String coditem;
			String descitem;
			Object imagemitem;

			ArrayList<ResultJoin> listagemItens = new ArrayList<ResultJoin>();

			while (rs.next() ) {

				descmontadora = rs.getString(1);
				mercadoparalelo = rs.getString(2);
				coditem = rs.getString(3);
				descitem = rs.getString(4);
				imagemitem = rs.getObject(5);

				ResultJoin rj = new 
						ResultJoin(descmontadora, mercadoparalelo, coditem, descitem, imagemitem); 

				listagemItens.add(rj);
			}


			ps.close();
			rs.close();
			conn.close();

			//*-------------------------------------------------------------------
			//* ------------    Salva dados na Session ---------------------------
			//* ------------------------------------------------------------------

			session.setAttribute("listagemItens", listagemItens);
			session.setAttribute("nomeCategoriaEscolhida", nomeCategoriaEscolhida);
			session.setAttribute("nomeMontadoraEscolhida", nomeMontadoraEscolhida);
			session.setAttribute("codCategoriaEscolhida", codCategoriaEscolhida);
			session.setAttribute("codMontadoraEscolhida", codMontadoraEscolhida);

			getServletContext().getRequestDispatcher("/jsp02.jsp").forward(request, response);  
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
