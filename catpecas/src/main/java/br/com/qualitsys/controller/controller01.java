package br.com.qualitsys.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.qualitsys.model.Categoria;
import br.com.qualitsys.model.Montadora;

//*-------------------------------------------------------------------------------------------------
//*---------------------------       Módulo controller01       -------------------------------------
//*-------------------------------------------------------------------------------------------------
//*----------------- Salva na Session =>  atributo: "listaCategorias1" ------------------------------
//*----------------- Salva na Session =>  atributo: "listaMontadoras1" ------------------------------
//*-------------------------------------------------------------------------------------------------

@WebServlet("/controller01")
public class controller01 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public controller01() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		//* ---------------------------------------------------------------------------------------
		//* ---------  Bloqueia chamada direta desse servlet pelo usuário -------------------------
		//* ---------------------------------------------------------------------------------------
	
		HttpSession session = request.getSession(); 
		if (session.getAttribute("usuario") == null)
			getServletContext().getRequestDispatcher("/jsperrologin.jsp").forward(request, response);

		Connection conn;
		try {
			
			//conn = DBHandlerLocal.getConn();
			conn = DBHandlerIntegrator.getConn();
			
			//-------------------------------------------------------------------------------------
			//--------------- Carga listBox com categorias  ---------------------------------------
			//-------------------------------------------------------------------------------------

			String preparedSQL = "SELECT * FROM categoria order by desccategoria";
			PreparedStatement ps = conn.prepareStatement(preparedSQL);
			
			ResultSet rs = ps.executeQuery();
			
			String codCategoria = null;
			String descCategoria = null;

			Categoria categoria;
			ArrayList<Categoria> listaCategorias1 = new ArrayList<Categoria>();

			while (rs.next() ) {
				codCategoria = rs.getString("codcategoria");
				descCategoria = rs.getString("desccategoria");
				categoria = new Categoria(codCategoria,descCategoria);
				listaCategorias1.add(categoria);
			}

			//-------------------------------------------------------------------------------------
			//--------------- Carga listBox com montadoras   --------------------------------------
			//-------------------------------------------------------------------------------------

			preparedSQL = "SELECT * FROM montadora order by descmontadora";
			ps = conn.prepareStatement(preparedSQL);
			
			rs = ps.executeQuery();
			
			String codMontadora = null;
			String descMontadora = null;

			Montadora montadora;
			ArrayList<Montadora> listaMontadoras1 = new ArrayList<Montadora>();

			while (rs.next() ) {
				codMontadora = rs.getString("codmontadora");
				descMontadora = rs.getString("descmontadora");
				montadora = new Montadora(codMontadora, descMontadora);
				listaMontadoras1.add(montadora);
			}

			ps.close();
			rs.close();
			conn.close();
			
			//--------------------------------------------------------------------------------------
			//--------   Save listaCategorias e listaMontadoras na Session -------------------------
			//--------------------------------------------------------------------------------------
							
			session.setAttribute("listaCategorias1", listaCategorias1);
			session.setAttribute("listaMontadoras1", listaMontadoras1);
			
			//--------------------------------------------------------------------------------------
			//--------   View jsp01 será chamada para exibir Listbox
			//--------------------------------------------------------------------------------------
			
			getServletContext().getRequestDispatcher("/jsp01.jsp").forward(request, response);  

		}
		catch (Exception e) {
			getServletContext().getRequestDispatcher("/jsperrodb.jsp").forward(request, response);  
			e.printStackTrace();
		}
	}
}