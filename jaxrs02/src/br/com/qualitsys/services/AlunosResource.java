package br.com.qualitsys.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import br.com.qualitsys.model.Aluno;

@Path("/alunos")
public class AlunosResource {
		
	private List<Aluno> alunoDB; 
	
	private static HikariDataSource dataSource = null;

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

	}
 
	public AlunosResource() {
		this.alunoDB  = new ArrayList<Aluno>();
	}
	
	@GET
    @Produces("application/json") 
	public String displayAlunos() throws JsonProcessingException {
		
			Connection conn = pedeConexao(); 
			
			if ( conn == null )  
				 return "<html><title>Hello</title><body><h1>Erro de Acesso ao Banco de Dados !!!</h1><body></html>";
			 
			else  {  
						String preparedSQL = "SELECT * FROM tabalunos";
				try {
					PreparedStatement ps = conn.prepareStatement(preparedSQL);
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
						Aluno aluno = new Aluno(RA, digito, cpf, nome, turma, grupo, curso, emailpessoal, emailgoogle, fone1, fone2, semestre);
						this.alunoDB.add(aluno);
					}
					
					conn.close();
			 
					if (RA == null)  {  
						return("<font color=" + "red>" + "<b>Erro  no processamento de alunos !!!</b>" + "<font color=" + "black>");
					}
					else {
						//----   Chamada do Jackson para geração do Json -----
						ObjectMapper mapper = new ObjectMapper(); 
						String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(alunoDB); 
						return jsonResult;
					}	
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return "<html><title>Hello</title><body><h1>Erro na criacao da lista JSON de Alunos !!!</h1><body></html>";
		}
	}
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
			return  null;
		} 
		return conn; 
	}
}
