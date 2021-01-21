package br.com.qualitsys.model;

import java.io.Serializable;

public class Aluno implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String RA = null;
	private String digito = null;
	private String cpf = null;
	private String nome = null;
	private String turma = null;
	private String grupo = null;
	private String curso = null;
	private String emailpessoal = null;
	private String emailgoogle = null;
	private String fone1 = null;
	private String fone2 = null;
	private String semestre = null;
	
	
	public Aluno(String RA, String digito, String cpf, String nome, String turma, String grupo, String curso,
			String emailpessoal, String emailgoogle, String fone1, String fone2, String semestre) {
		
		this.RA = RA;
		this.digito = digito;
		this.cpf = cpf;
		this.nome = nome;
		this.turma = turma;
		this.grupo = grupo;
		this.curso = curso;
		this.emailpessoal = emailpessoal;
		this.emailgoogle = emailgoogle;
		this.fone1 = fone1;
		this.fone2 = fone2;
		this.semestre = semestre;
	}
	
	
	public Aluno() {
		RA = null;
		digito = null;
		cpf = null;
		nome = null;
		turma = null;
		grupo = null;
		curso = null;
		emailpessoal = null;
		emailgoogle = null;
		fone1 = null;
		fone2 = null;
		semestre = null;
	}


	public String getRA() {
		return RA;
	}


	public void setRA(String rA) {
		RA = rA;
	}


	public String getDigito() {
		return digito;
	}


	public void setDigito(String digito) {
		this.digito = digito;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTurma() {
		return turma;
	}


	public void setTurma(String turma) {
		this.turma = turma;
	}


	public String getGrupo() {
		return grupo;
	}


	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}


	public String getCurso() {
		return curso;
	}


	public void setCurso(String curso) {
		this.curso = curso;
	}


	public String getEmailpessoal() {
		return emailpessoal;
	}


	public void setEmailpessoal(String emailpessoal) {
		this.emailpessoal = emailpessoal;
	}


	public String getEmailgoogle() {
		return emailgoogle;
	}


	public void setEmailgoogle(String emailgoogle) {
		this.emailgoogle = emailgoogle;
	}


	public String getFone1() {
		return fone1;
	}


	public void setFone1(String fone1) {
		this.fone1 = fone1;
	}


	public String getFone2() {
		return fone2;
	}


	public void setFone2(String fone2) {
		this.fone2 = fone2;
	}


	public String getSemestre() {
		return semestre;
	}


	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
}
