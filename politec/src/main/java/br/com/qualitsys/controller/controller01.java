package br.com.qualitsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.qualitsys.model.Aluno;

@WebServlet("/controller01")
public class controller01 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public controller01() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		String codaluno = request.getParameter("codaluno");
		request.setAttribute("codaluno", codaluno);
			
		Connection conn = ConectaDB(); 

		if ( conn == null ) {
			out.println("<b>Erro de Conexão ao Banco de Dados ....</b>");
			getServletContext().getRequestDispatcher("/index.jsp").include(request, response);
		}
		else  {
				String preparedSQL = 
					"SELECT RA, digito, cpf, nome, turma, grupo, curso, emailpessoal, emailgoogle, fone1, fone2, semestre "
							+ "FROM tabalunos WHERE RA = ?";
			try {
				PreparedStatement ps = conn.prepareStatement(preparedSQL);
				ps.setString(1, codaluno);
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
					out.println("Aluno não existente!!!");
					getServletContext().getRequestDispatcher("/jsp01.jsp").include(request, response);
				}
				else {
				
				Aluno aluno = 
						new Aluno (RA, digito, cpf, nome, turma, grupo, curso, emailpessoal, emailgoogle, fone1, fone2, semestre);

				request.setAttribute("aluno", aluno);
				getServletContext().getRequestDispatcher("/jsp02.jsp").include(request, response); }

			} catch (SQLException e) {
				

				e.printStackTrace();
			}
		}
	}

	//*---------------------------------------------------------------------------
	public static Connection ConectaDB() {

		try {
			
			//*--------------------- Conexao Integrator --------------------------
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			String user = "qualitsy_politec";
			String password = "#MHmarcam#99#";

			Connection conn = 
					DriverManager.getConnection("jdbc:mysql://209.172.51.58:3306/qualitsy_politec",user,password);


			//*-------------------- Conexao localhost -------------------------------
			//  DriverManager.getConnection("jdbc:mysql://localhost:3307/alunos?useTimezone=true&serverTimezone=UTC&" +
			//			                        "user=root&password=maua");		

			return conn; 

		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	//*---------------------------------------------------------------------------------
}
