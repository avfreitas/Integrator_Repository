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

import br.com.qualitsys.model.Curso;
import br.com.qualitsys.model.Grade;

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
 	 
	static {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.mysql.cj.jdbc.Driver"); 
		config.setJdbcUrl("jdbc:mysql://localhost:3307/politec?useTimezone=true&serverTimezone=UTC");
		config.setUsername("root");
		config.setPassword("maua");
		config.addDataSourceProperty("minimumIdle", "5");
		config.addDataSourceProperty("maximumPoolSize", "25");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		dataSource = new HikariDataSource(config);
}
 
		/*----------------------------------------------------------------------------------- 
		 * 
		 * Configuração do Connection Pool para a Integrator:
		 * ----------------------------------------------------------------------------------
/*
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
*/

	public controller02() {
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		//Recupera do Request o idcurso escolhido pelo usuário
		String idcurso = request.getParameter("idcurso");
		out.println("idcurso = " + idcurso);
		Integer idCursoNum = Integer.parseInt(idcurso);
		
		//Recupera da Session a lista de Cursos salva pelo Controller01
		HttpSession session = request.getSession(); 
		
		@SuppressWarnings("unchecked")
		ArrayList<Curso> listaCursos = (ArrayList<Curso>)session.getAttribute("listaCursos");
			
		//Recupera nome do curso escolhido
		String nomeCursoEscolhido = null;
		int size = listaCursos.size();
		
		for (int i=0; i<size;i++) {
			if(listaCursos.get(i).getIdCurso() == idCursoNum) {
				nomeCursoEscolhido = listaCursos.get(i).getNomeCurso();
				break;
			}
		}
	
		//Salva na Session o nome do curso escolhido
		session.setAttribute("nomeCursoEscolhido",nomeCursoEscolhido);
	
		Connection conn = pedeConexao(); 
		 
		if ( conn == null ) {
			out.println("<b>Erro de Conexão ao Banco de Dados ....</b>");
			getServletContext().getRequestDispatcher("/jsp02.jsp").include(request, response);
		}
		else  {
			
			String preparedSQL = 
					"SELECT * FROM tabgrades where idcurso = ?";
			try {
				PreparedStatement ps = conn.prepareStatement(preparedSQL);
				ps.setString(1, idcurso);
				ResultSet rs = ps.executeQuery();
			
				if (rs == null) {
					out.println("<font color=" + "red>" + "<b>Erro - Acesso Tabela de Grades!!!</b>" + "<font color=" + "black>");
					conn.close();
					getServletContext().getRequestDispatcher("/jsp01.jsp").include(request, response);
				}
				
				else {
							
							String idGrade = null;
							String ano = null;
							String semestreGrade = null;
							
							Grade grade;
							ArrayList<Grade> listaGrades = new ArrayList<Grade>();
							
							while (rs.next() ) {
								
								idGrade = rs.getString("idGrade");
								ano = rs.getString("anograde");
								semestreGrade = rs.getString("semestreGrade");
								grade = new Grade(idGrade, ano, semestreGrade, idCursoNum);
													 
								listaGrades.add(grade);
							}
							
							//Salva na Session a Lista de Grades para ser usar pela view jsp03
							session.setAttribute("listaGrades", listaGrades);
							
							//Salva na Session a lista de Grades para ser usada pelo Controller03
							session.setAttribute("listaGrades", listaGrades);
							
							getServletContext().getRequestDispatcher("/jsp03.jsp").forward(request, response);  
							conn.close();
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
