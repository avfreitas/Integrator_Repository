package br.com.qualitsys.model;

public class Aluno {
	
	private String codAluno;
	private String nomeAluno;
	private String fone1Aluno;
	private String fone2Aluno;
	private String emailPessoalAluno;
	private String emailGoogleAluno;
	private String cursoAluno;
	
		
	public Aluno(String codAluno, 
			String nomeAluno, 
			String fone1Aluno, 
			String fone2Aluno, 
			String emailPessoalAluno,
			String emailGoogleAluno, 
			String cursoAluno) {
		
		this.codAluno = codAluno;
		this.nomeAluno = nomeAluno;
		this.fone1Aluno = fone1Aluno;
		this.fone2Aluno = fone2Aluno;
		this.emailPessoalAluno = emailPessoalAluno;
		this.emailGoogleAluno = emailGoogleAluno;
		this.cursoAluno = cursoAluno;
	}
	public String getCodAluno() {
		return codAluno;
	}
	public void setCodAluno(String codAluno) {
		this.codAluno = codAluno;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getFone1Aluno() {
		return fone1Aluno;
	}
	public void setFone1Aluno(String fone1Aluno) {
		this.fone1Aluno = fone1Aluno;
	}
	public String getFone2Aluno() {
		return fone2Aluno;
	}
	public void setFone2Aluno(String fone2Aluno) {
		this.fone2Aluno = fone2Aluno;
	}
	public String getEmailPessoalAluno() {
		return emailPessoalAluno;
	}
	public void setEmailPessoalAluno(String emailPessoalAluno) {
		this.emailPessoalAluno = emailPessoalAluno;
	}
	public String getEmailGoogleAluno() {
		return emailGoogleAluno;
	}
	public void setEmailGoogleAluno(String emailGoogleAluno) {
		this.emailGoogleAluno = emailGoogleAluno;
	}
	public String getCursoAluno() {
		return cursoAluno;
	}
	public void setCursoAluno(String cursoAluno) {
		this.cursoAluno = cursoAluno;
	}
}
