package br.com.qualitsys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tabalunos")
public class Aluno implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "RA")
	private String RA;
	
	@Column(name = "digito")
	private String digito;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "turma")
	private String turma;
	
	@Column(name = "grupo")
	private String grupo;
	
	@Column(name = "curso")
	private String curso;
	
	@Column(name = "emailpessoal")
	private String emailpessoal;
	
	@Column(name = "emailgoogle")
	private String emailgoogle;
	
	@Column(name = "fone1")
	private String fone1;
	
	@Column(name = "fone2")
	private String fone2;
	
	@Column(name = "semestre")
	private String semestre;
	
	
	public Aluno(String rA, String digito, String cpf, String nome, String turma, String grupo, String curso,
			String emailpessoal, String emailgoogle, String fone1, String fone2, String semestre) {
		
		this.RA = rA;
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
