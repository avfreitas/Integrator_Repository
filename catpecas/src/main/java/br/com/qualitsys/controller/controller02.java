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

import br.com.qualitsys.model.Categoria;
import br.com.qualitsys.model.Montadora;
import br.com.qualitsys.model.ResultJoin;

//*-------------------------------------------------------------------------------------------------
//*--------------------------------       Módulo controller02   ------------------------------------
//*-------------------------------------------------------------------------------------------------
//*------------------ Salva na Session =>  atributo: "listagemItens1" -------------------------------
//*------------------ Salva na Session =>  atributo: "nomeCategoriaEscolhida1" ----------------------
//*------------------ Salva na Session =>  atributo: "nomeMontadoraEscolhida1" ----------------------
//*------------------ Salva na Session =>  atributo: "codCategoriaEscolhida1" -----------------------
//*------------------ Salva na Session =>  atributo: "codMontadoraEscolhida1" -----------------------
//*--------------------------------------------------------------------------------------------------

@WebServlet("/controller02")
public class controller02 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public controller02() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//* ---------------------------------------------------------------------------------------
		//* ---------  Bloqueia chamada direta desse servlet pelo usuário -------------------------
		//* ---------------------------------------------------------------------------------------
		
		HttpSession session = request.getSession(); 
		if (session.getAttribute("usuario") == null)
			getServletContext().getRequestDispatcher("/jsperrologin.jsp").forward(request, response);

		//*-----------------------------------------------------------------------------------------
		//*--------------     Recupera do Request a Categoria escolhido pelo usuário ---------------
		//*-----------------------------------------------------------------------------------------
		String codCategoriaEscolhida1 = request.getParameter("codcategoria1");
	
		//*-----------------------------------------------------------------------------------------
		//*--------------     Recupera do Request a Montadora escolhido pelo usuário ---------------
		//*-----------------------------------------------------------------------------------------
		String codMontadoraEscolhida1 = request.getParameter("codmontadora1");
		
		//*-----------------------------------------------------------------------------------------
		//*--------------     Recupera da Session a lista de Categorias ----------------------------
		//*-----------------------------------------------------------------------------------------
		
		@SuppressWarnings("unchecked")
		ArrayList<Categoria> listaCategorias1 = (ArrayList<Categoria>)session.getAttribute("listaCategorias1");
		
		//*-----------------------------------------------------------------------------------------
		//*--------------     Recupera da Session a lista de Montadoras ----------------------------
		//*-----------------------------------------------------------------------------------------

		@SuppressWarnings("unchecked")
		ArrayList<Montadora> listaMontadoras1 = (ArrayList<Montadora>)session.getAttribute("listaMontadoras1");

		//* -----------------------------------------------------------------------------------------
		//* ----------       Define nome da Categoria escolhida  ----------------------------------
		//* -----------------------------------------------------------------------------------------

		String nomeCategoriaEscolhida1 = null;
		int size1 = listaCategorias1.size();

		for (int i=0; i<size1;i++)  
			if(listaCategorias1.get(i).getCodCategoria().equals(codCategoriaEscolhida1)) {
				nomeCategoriaEscolhida1 = listaCategorias1.get(i).getDescCategoria();
				break;
			}

		//*------------------------------------------ ------------------------------------------------
		//* -------------  Define nome da Montadora escolhida  -------------------------------------
		//* ------------------------------------------------------------------------------------------

		String nomeMontadoraEscolhida1 = null;
		int size2 = listaMontadoras1.size();

		for (int i=0; i<size2;i++)  
			if(listaMontadoras1.get(i).getCodMontadora().equals(codMontadoraEscolhida1)) {
				nomeMontadoraEscolhida1 = listaMontadoras1.get(i).getDescMontadora();
				break;
			}
			
		Connection conn;
		
		try {
			
			//conn = DBHandlerLocal.getConn();
			conn = DBHandlerIntegrator.getConn();

			String preparedSQL = 

					"SELECT DISTINCT  M.descmontadora AS Montadora , " + 
							"T.mercadoparalelo AS 'Mercado Paralelo ', " +
							"T.coditem AS 'Código Interno ', " + 
							"T.descitem AS 'Descrição do Item ', " +
							"T.imagemitem AS 'Imagem do Item ', " + 
							"T.codigosoriginais AS 'Códigos Originais ' " + 
							

							"FROM montadora_item MI " + 

							"INNER JOIN tabitem T " + 
							"ON  T.coditem = MI.coditem " +  

							"INNER JOIN montadora M " + 
							"ON  M.codmontadora = MI.codmontadora " +

							"where T.codcategoria =?  and M.codmontadora = ?" +    
							"ORDER BY T.coditem , M.descmontadora " ; 

			PreparedStatement ps = conn.prepareStatement(preparedSQL);

			ps.setString(1, codCategoriaEscolhida1);
			ps.setString(2, codMontadoraEscolhida1);
			
			ResultSet rs = ps.executeQuery();

			String descmontadora; 
			String mercadoparalelo;
			String coditem;
			String descitem;
			Object imagemitem;
			String codigosoriginais;

			ArrayList<ResultJoin> listagemItens1 = new ArrayList<ResultJoin>();
		
			while (rs.next() ) {

				descmontadora = rs.getString(1);
				mercadoparalelo = rs.getString(2);
				coditem = rs.getString(3);
				descitem = rs.getString(4);
				imagemitem = rs.getObject(5);
				codigosoriginais = rs.getString(6);

				ResultJoin rj = new 
						ResultJoin(descmontadora, mercadoparalelo, coditem, descitem, imagemitem, codigosoriginais); 

				listagemItens1.add(rj);
			}
			
			//* ---------------------------------------------------------
			//*----------  Fechamento de conexões com o SGBD  -----------
			//* ---------------------------------------------------------

			ps.close();
			rs.close();
			conn.close();

			//*----------------------------------------------------------------------------------------------
			//* ------- Salva dados na Session --------------------------------------------------------------
			//* ------- View jsp01 será chamada com condição de erro 00005 se não houver itens --------------
			//*-------- View jsp02 será chamda para exibir os dados dos itens selecionados ------------------
			//* ---------------------------------------------------------------------------------------------
			
			session.setAttribute("listagemItens1", listagemItens1);
			session.setAttribute("nomeCategoriaEscolhida1", nomeCategoriaEscolhida1);
			session.setAttribute("nomeMontadoraEscolhida1", nomeMontadoraEscolhida1);
			session.setAttribute("codCategoriaEscolhida1", codCategoriaEscolhida1);
			session.setAttribute("codMontadoraEscolhida1", codMontadoraEscolhida1);
			
		
			if (listagemItens1.size() == 0) {
				session.setAttribute("msgerro", "00005");
				getServletContext().getRequestDispatcher("/jsp01.jsp").forward(request, response);  
			}
			
			else 	
				getServletContext().getRequestDispatcher("/jsp02.jsp").forward(request, response);  
		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
}
