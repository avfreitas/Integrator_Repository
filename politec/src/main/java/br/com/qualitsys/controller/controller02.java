package br.com.qualitsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import br.com.qualitsys.model.Aluno;

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

	/*
	static {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.mysql.cj.jdbc.Driver"); 
		config.setJdbcUrl("jdbc:mysql://localhost:3307/alunos?useTimezone=true&serverTimezone=UTC");
		config.setUsername("root");
		config.setPassword("maua");
		config.addDataSourceProperty("minimumIdle", "5");
		config.addDataSourceProperty("maximumPoolSize", "25");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		dataSource = new HikariDataSource(config);

*/
		/*--------------------------------------------------------------------------------------------------------------
		 * 
		 * Configuração do Connection Pool para a Integrator:
		 * 
*/
	 static {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.mysql.cj.jdbc.Driver"); 
		config.setJdbcUrl("jdbc:mysql://209.172.51.58:3306/qualitsy_politec?useTimezone=true&serverTimezone=UTC");
		config.setUsername("qualitsy_politec");
		config.setPassword("#MHmarcam#99#");
		config.addDataSourceProperty("minimumIdle", "5");
		config.addDataSourceProperty("maximumPoolSize", "25");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		dataSource = new HikariDataSource(config);
	   }
/* 
		 * 
		 * 
		 */ 

	public controller02() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		String cpfaluno = request.getParameter("cpfaluno");
		//request.setAttribute("codaluno", codaluno);

		Connection conn = pedeConexao(); 

		if ( conn == null ) {
			out.println("<b>Erro de Conexão ao Banco de Dados ....</b>");
			getServletContext().getRequestDispatcher("/index.jsp").include(request, response);
		}
		else  {
			
			String preparedSQL = 
					"SELECT RA, digito, cpf, nome, turma, grupo, curso, emailpessoal, emailgoogle, fone1, fone2, semestre "
							+ "FROM tabalunos WHERE CPF = ?";
			try {
				PreparedStatement ps = conn.prepareStatement(preparedSQL);
				ps.setString(1, cpfaluno);
				ResultSet rs = ps.executeQuery();
				
				String RA = null;
				String digito = null;
				String cpf = null;
				String nome = null;
				String turma = null;
				String grupo = null;
				String curso = null;
				String emailpessoal = null;
				String emailgoogle = null;
				String fone1 = null;
				String fone2 = null;
				String semestre = null;

				while (rs.next() ) {
					
					RA = rs.getString("RA");
					digito = rs.getString("digito");
					cpf = rs.getString("cpf");
					nome = rs.getString("nome");
					turma = rs.getString("turma");
					grupo = rs.getString("grupo");
					curso = rs.getString("curso");
					emailpessoal = rs.getString("emailpessoal");
					emailgoogle = rs.getString("emailgoogle");
					fone1 = rs.getString("fone1");
					fone2 = rs.getString("fone2");
					semestre = rs.getString("semestre");
				}

				if (RA == null) {
					out.println("<font color=" + "red>" + "<b>Aluno não existente!!!</b>" + "<font color=" + "black>");
					conn.close();
					getServletContext().getRequestDispatcher("/jsp01.jsp").include(request, response);
				}
				else {

					Aluno aluno = 
							new Aluno (RA, digito, cpf, nome, turma, grupo, curso, emailpessoal, emailgoogle, fone1, fone2, semestre);

					request.setAttribute("aluno", aluno);
					
					conn.close();
					getServletContext().getRequestDispatcher("/jsp02.jsp").include(request, response); }
					
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
