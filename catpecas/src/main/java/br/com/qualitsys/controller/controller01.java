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

//*----------------------------------------------------------
//*-- Salva na Session =>  atributo: "listaCategorias" 
//*----------------------------------------------------------


@WebServlet("/controller01")
public class controller01 extends HttpServlet {

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
	//*---- Configuração do Connection Pool para a MySQL local 
	//*-----------------------------------------------------------------------------------
	
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
	//*---- Configuração do Connection Pool para a Integrator:
	//*-----------------------------------------------------------------------------------
 
	
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
	
	
	public controller01() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		Connection conn = pedeConexao(); 

		if ( conn == null ) {
			out.println("<b>Erro de Conexão ao Banco de Dados ....</b>");
			getServletContext().getRequestDispatcher("/index.jsp").include(request, response);
		}
		else  {

			String preparedSQL = "SELECT * FROM categoria order by desccategoria";
			
			try {
				PreparedStatement ps = conn.prepareStatement(preparedSQL);
				ResultSet rs = ps.executeQuery();
			

				if (rs == null) {
					out.println("<font color=" + "red>" + "<b>Erro - Acesso Tabela de Categorias!!!</b>" + "<font color=" + "black>");
					
					ps.close();
					conn.close(); 
					
					getServletContext().getRequestDispatcher("/jsp01.jsp").include(request, response);
				}

				else {
				
					String codCategoria = null;
					String descCategoria = null;

					Categoria categoria;
					ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();

					while (rs.next() ) {
						codCategoria = rs.getString("codcategoria");
						descCategoria = rs.getString("desccategoria");
						categoria = new Categoria(codCategoria,descCategoria);
						listaCategorias.add(categoria);
					}
					
					ps.close();
					rs.close();
					conn.close(); 
					
					HttpSession session = request.getSession(); 
					
					//Salva na Session a lista de Categorias obtida do Banco de Dados p/view jsp02
					session.setAttribute("listaCategorias", listaCategorias);
					
					getServletContext().getRequestDispatcher("/jsp02.jsp").forward(request, response);  
			
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
