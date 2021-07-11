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

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import br.com.qualitsys.model.Categoria;
import br.com.qualitsys.model.Montadora;


//*------------------------------------------------------------------
//*-- Salva na Session =>  atributo: "nomeCategoriaEscolhida" 
//*-- Salva na Session =>  atributo: "codcategoria" 
//*-- Salva na Session =>  atributo: "listaMontadoras" 
//*------------------------------------------------------------------


@WebServlet("/controller02")
public class controller02 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/* -------------------------------------------------------------------------------------------------------
	 HikariCP Configuration:
     -----------------------------------------------------------------------------------------------------------
	We can use Java based configuration or we can use property file to configure HikariCP. 
	Let’s have a look at below properties.

	idleTimeout: Time in milliseconds for which connection object can stay in the pool as idle. 
	It works with minimumIdle and maximumPoolSize properties. After a specified time connection object will be released.

	connectionTimeout:  Time in milliseconds for which the client will wait for connection object from Pool. 
	If the time limit is reached then SQL Exception will be thrown.

	autoCommit: We can specify true or false and if it is set to true then it will automatically commit 
	every SQL statements you execute and if it is set to false then we need to commit SQL statements manually

	cachePrepStmts: Enable caching for Prepare Statement

	minimumIdle: Minimum number of connection objects needs to remain in the pool at any time.

	maximumPoolSize: Maximum number of connections that can stay in the pool.
	 * 
	 * */
	private static HikariDataSource dataSource = null;

	//*----------------------------------------------------------------------------------- 
	//*------- Configuração do Connection Pool para a MySQL local
	//* ----------------------------------------------------------------------------------



	static {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.mysql.cj.jdbc.Driver"); 
		config.setJdbcUrl("jdbc:mysql://localhost:3307/catpecas?useTimezone=true&serverTimezone=UTC");
		config.setUsername("root");
		config.setPassword("maua");
		config.addDataSourceProperty("minimumIdle", "5");
		config.addDataSourceProperty("maximumPoolSize", "25");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		dataSource = new HikariDataSource(config);
	}


	//*----------------------------------------------------------------------------------- 
	//*------- Configuração do Connection Pool para a Integrator:
	//* ----------------------------------------------------------------------------------


	/*
	 static {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.mysql.cj.jdbc.Driver"); 
		config.setJdbcUrl("jdbc:mysql://localhost:3306/qualitsy_politec?useTimezone=true&serverTimezone=UTC");
		config.setUsername("qualitsy_grades");
		config.setPassword("#MHmarcam#99#");
		config.addDataSourceProperty("minimumIdle", "5");
		config.addDataSourceProperty("maximumPoolSize", "25");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		dataSource = new HikariDataSource(config);
	   }

	 */

	public controller02() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		//Recupera do Request a Categoria escolhido pelo usuário
		String codcategoria = request.getParameter("codcategoria");
	
		HttpSession session = request.getSession(); 

		@SuppressWarnings("unchecked")
		ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>)session.getAttribute("listaCategorias");

		//Recupera nome da Categoria escolhida
		String nomeCategoriaEscolhida = null;
		int size = listaCategorias.size();

		for (int i=0; i<size;i++) {
			if(listaCategorias.get(i).getCodCategoria().equals(codcategoria)) {
				nomeCategoriaEscolhida = listaCategorias.get(i).getDescCategoria();
				break;
			}

		}

		Connection conn = pedeConexao(); 

		if ( conn == null ) {
			out.println("<b>Erro de Conexão ao Banco de Dados ....</b>");
			getServletContext().getRequestDispatcher("/jsp02.jsp").include(request, response);
		}
		else  {

			String preparedSQL = 
					"SELECT * FROM montadora order by descmontadora";
			try {
				PreparedStatement ps = conn.prepareStatement(preparedSQL);
				ResultSet rs = ps.executeQuery();

				if (rs == null) {
					out.println("<font color=" + "red>" + "<b>Erro - Acesso Tabela de Montadoras!!!</b>" + "<font color=" + "black>");
					ps.close();
					conn.close();
					getServletContext().getRequestDispatcher("/jsp01.jsp").include(request, response);
				}

				else {

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

					//Salva na Session o nome da Categoria escolhida
					session.setAttribute("nomeCategoriaEscolhida",nomeCategoriaEscolhida);

					//Salva na Session o código da Categoria escolhida
					session.setAttribute("codcategoria",codcategoria);

					//Salva na Session a Lista de Montadoras para ser usar pela view jsp03
					session.setAttribute("listaMontadoras", listaMontadoras);

					getServletContext().getRequestDispatcher("/jsp03.jsp").forward(request, response);  

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	//*---------------------------------------------------------------------------
	public static Connection pedeConexao() {

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
		} 

		catch (SQLException e) {
			e.printStackTrace(); 
			return null;
		} 

		catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		return conn; 
	}
	//*---------------------------------------------------------------------------------
}
