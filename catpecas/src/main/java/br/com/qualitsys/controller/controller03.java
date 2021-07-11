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

import br.com.qualitsys.model.Montadora;
import br.com.qualitsys.model.ResultJoin;


//*--------------------------------------------------------
//*--  Salva na Session: "listagemItens"
//*--  Salva na Session: "nomeMontadoraEscolhida"
//*------------------------------------------------------ 

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


	//*--------------------------------------------------------------------------------- 
	//*-------------- Configuração do Connection Pool para a Integrator:
	//*---------------------------------------------------------------------------------
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

	public controller03() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		//Recupera do Request a Montadora escolhida pelo usuário
		String codmontadora = request.getParameter("codmontadora");

		HttpSession session = request.getSession(); 

		@SuppressWarnings("unchecked")
		ArrayList<Montadora> listaMontadoras = (ArrayList<Montadora>)session.getAttribute("listaMontadoras");

		//Recupera nome da Categoria escolhida
		String nomeMontadoraEscolhida = null;
		int size = listaMontadoras.size();

		for (int i=0; i<size;i++) {
			if(listaMontadoras.get(i).getCodMontadora().equals(codmontadora)) {
				nomeMontadoraEscolhida = listaMontadoras.get(i).getDescMontadora();
				break;
			}

		}

		//Salva na Session a Montadora escolhida para ser usada na view jsp04
		session.setAttribute("nomeMontadoraEscolhida", nomeMontadoraEscolhida);


		String  codcategoria = (String)session.getAttribute("codcategoria");

		Connection conn = pedeConexao(); 

		if ( conn == null ) {
			out.println("<b>Erro de Conexão ao Banco de Dados ....</b>");
			getServletContext().getRequestDispatcher("/jsp02.jsp").include(request, response);
		}
		else  {

			String preparedSQL = 

					" SELECT DISTINCT  M.descmontadora AS Montadora , " + 
							"A.codproduto AS 'Código do Produto ' , " +
							"T.mercadoparalelo AS 'Mercado Paralelo ' , " +
							"T.coditem AS 'Código Interno ', " + 
							"T.descitem AS 'Descrição do Ítem ', " +
							"T.imagemitem AS 'Imagem do Ítem ' " + 

							"FROM montadora_item MI " + 

							"INNER JOIN tabitem T " + 
							"ON  T.coditem = MI.coditem " +  

							"INNER JOIN aplicacao A  " +  
							"ON A.coditem = MI.coditem " +

							"INNER JOIN montadora M " + 
							"ON  M.codmontadora = MI.codmontadora " +

							"where T.codcategoria =?  and  M.codmontadora =?  " +    
							"ORDER BY T.coditem , M.descmontadora " ; 

			try {

				PreparedStatement ps = conn.prepareStatement(preparedSQL);

				ps.setString(1, codcategoria);
				ps.setString(2, codmontadora);

				ResultSet rs = ps.executeQuery();


				if (rs == null) {
					out.println("<font color=" + "red>" + "<b>Erro - Acesso Tabela de Itens !!!</b>" + "<font color=" + "black>");

					ps.close();
					conn.close();
					getServletContext().getRequestDispatcher("/jsp01.jsp").include(request, response);
				}

				else {

					String descmontadora; 
					String codproduto;
					String mercadoparalelo;
					String coditem;
					String descitem;
					Object imagemitem;

					ArrayList<ResultJoin> listagemItens = new ArrayList<ResultJoin>();

					while (rs.next() ) {

						descmontadora = rs.getString(1);
						codproduto = rs.getString(2);
						mercadoparalelo = rs.getString(3);
						coditem = rs.getString(4);
						descitem = rs.getString(5);
						imagemitem = rs.getObject(6);

						ResultJoin rj = new 
								ResultJoin(descmontadora, codproduto, mercadoparalelo, coditem, descitem, imagemitem); 

						listagemItens.add(rj);
					}

					ps.close();
					rs.close();
					conn.close();

					//Salva na Session a Listagem da Itens para ser usada na view jsp04
					session.setAttribute("listagemItens", listagemItens);

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
