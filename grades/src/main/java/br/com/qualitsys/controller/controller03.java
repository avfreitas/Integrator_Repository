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

import br.com.qualitsys.model.Grade;
import br.com.qualitsys.model.ResultJoin;

@WebServlet("/controller03")
public class controller03 extends HttpServlet {
	
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
 
*/ 
		//*--------------------------------------------------------------------------------- 
		//*-------------- Configuração do Connection Pool para a Integrator:
		//*---------------------------------------------------------------------------------
 
	 static {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.mysql.cj.jdbc.Driver"); 
		config.setJdbcUrl("jdbc:mysql://localhost:3306/qualitsy_politec?useTimezone=true&serverTimezone=UTC");
		config.setUsername("qualitsy_politec");
		config.setPassword("#MHmarcam#99#");
		config.addDataSourceProperty("minimumIdle", "5");
		config.addDataSourceProperty("maximumPoolSize", "25");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		dataSource = new HikariDataSource(config);
	   }
 

	public controller03() {
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		//Recupera do Request o idgrade escolhido pelo usuário
		String idGradeEscolhida = request.getParameter("idgrade");
		out.println("idgrade = " + idGradeEscolhida);
		
		//Recupera da Session a lista de Grades salva pelo Controller02
		HttpSession session = request.getSession(); 
		
		@SuppressWarnings("unchecked")
		ArrayList<Grade> listaGrades = (ArrayList<Grade>)session.getAttribute("listaGrades");
		 
		//Recupera dados da grade escolhida
		String anoGradeEscolhida = null;
		String semestreGradeEscolhida = null; 
		
		int size = listaGrades.size();
		
		out.println("Tamanho da grade: " + size);
		
		for (int i=0; i<size;i++) {
			if(listaGrades.get(i).getIdGrade().equals(idGradeEscolhida)) {
				anoGradeEscolhida = listaGrades.get(i).getAno();
				anoGradeEscolhida = anoGradeEscolhida.substring(0, 4);
				semestreGradeEscolhida = listaGrades.get(i).getSemestreGrade();
			}
		}
		
		out.println("Ano da Grade Escolhida: " + anoGradeEscolhida);
		out.println("Semestre da Grade Escolhida: " + semestreGradeEscolhida);
	
	 	//Salva na Session os dados da grade escolhida
		
		session.setAttribute("idGradeEscolhida",idGradeEscolhida);
		session.setAttribute("anoGradeEscolhida",anoGradeEscolhida);
		session.setAttribute("semestreGradeEscolhida",semestreGradeEscolhida);
	 
		Connection conn = pedeConexao(); 
		 
		if ( conn == null ) {
			out.println("<b>Erro de Conexão ao Banco de Dados ....</b>");
			getServletContext().getRequestDispatcher("/jsp02.jsp").include(request, response);
		}
		else  {
			
			String preparedSQL = 
					"select disciplinacursograde.grupo, disciplinacursograde.iddisciplina, " + 
					"tabdisciplinas.nomeDisciplina, disciplinacursograde.cargaHoraria " + 
					"from disciplinacursograde " + 
					"inner join tabdisciplinas " +
					"on disciplinacursograde.iddisciplina = tabdisciplinas.iddisciplina " +
					"and disciplinacursograde.idGrade=? order by disciplinacursograde.grupo " +
					" asc , tabdisciplinas.nomeDisciplina asc"; 
			try {
				PreparedStatement ps = conn.prepareStatement(preparedSQL);
				ps.setString(1, idGradeEscolhida);
				ResultSet rs = ps.executeQuery();
				
			
				if (rs == null) {
					out.println("<font color=" + "red>" + "<b>Erro - Acesso Tabela de Grades!!!</b>" + "<font color=" + "black>");
					conn.close();
					getServletContext().getRequestDispatcher("/jsp01.jsp").include(request, response);
				}
				
				else {
							
							Integer grupo = null;
							Integer idDisciplina = null;
							String nomeDisciplina = null;
							Integer cargaHoraria = null;
						
							ArrayList<ResultJoin> listagemGrade = new ArrayList<ResultJoin>();
							
							while (rs.next() ) {
								
								grupo = rs.getInt(1);
								idDisciplina = rs.getInt(2);
								nomeDisciplina = rs.getString(3);
								cargaHoraria = rs.getInt(4);
								
								ResultJoin rj = new 
										ResultJoin(grupo,idDisciplina,nomeDisciplina,cargaHoraria); 
								
								listagemGrade.add(rj);
							}
							
							conn.close();
						
							//Salva no Request a Listagem da Grade para ser usada pela view jsp04
							request.setAttribute("listagemGrade", listagemGrade);
						
							getServletContext().getRequestDispatcher("/jsp04.jsp").forward(request, response);  
						 
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
