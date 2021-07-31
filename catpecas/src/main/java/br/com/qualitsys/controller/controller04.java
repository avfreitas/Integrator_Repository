

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

import br.com.qualitsys.model.Montadora;


//*-------------------------------------------------------------------------------------------------
//*------------       Módulo controller04   --------------------------------------------------------
//*-------------------------------------------------------------------------------------------------
//*-----------------       Salva na Session =>  atributo: "listaMontadoras"    ---------------------
//*-------------------------------------------------------------------------------------------------

@WebServlet("/controller04")
public class controller04 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public controller04() {

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
			
			//----------------------------------------------------------------------------------------------
			//--------------- Carga listBox com montadoras   -----------------------------------------------
			//----------------------------------------------------------------------------------------------

			String preparedSQL = "SELECT * FROM montadora order by descmontadora";
			PreparedStatement ps = conn.prepareStatement(preparedSQL);
			ResultSet rs = ps.executeQuery();
			String codMontadora = null;
			String descMontadora = null;

			Montadora montadora;
			ArrayList<Montadora> listaMontadoras = new ArrayList<Montadora>();

			while (rs.next() ) {
				codMontadora = rs.getString("codmontadora");
				descMontadora = rs.getString("descmontadora");
				montadora = new Montadora(codMontadora, descMontadora);
				listaMontadoras.add(montadora);
			}
		
			ps.close();
			rs.close();
			conn.close();

			//----------------------------------------------------------------------------------------------
			//--------   Save listaMontadoras na Session ---------------------------------------------------
			//--------   View jsp04 será chamada para exibir Listbox
			//----------------------------------------------------------------------------------------------
				
			session.setAttribute("listaMontadoras", listaMontadoras);

			getServletContext().getRequestDispatcher("/jsp04.jsp").forward(request, response);  

		}
		catch (Exception e) {
			getServletContext().getRequestDispatcher("/jsperrodb.jsp").forward(request, response);  
			e.printStackTrace();
		}
	}
}