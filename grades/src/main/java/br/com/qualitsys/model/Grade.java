package br.com.qualitsys.model;

public class Grade {
	
	private String idGrade;
	private String ano;
	private String semestreGrade;
	private Integer idCurso;
	
	public Grade(String idGrade, String ano, String semestreGrade, Integer idCurso) {
		 
		this.idGrade = idGrade;
		this.ano = ano;
		this.semestreGrade = semestreGrade;
		this.idCurso = idCurso;
	}
	
	public Grade() {
	
	}

	public String getIdGrade() {
		return idGrade;
	}

	public void setIdGrade(String idGrade) {
		this.idGrade = idGrade;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getSemestreGrade() {
		return semestreGrade;
	}

	public void setSemestreGrade(String semestreGrade) {
		this.semestreGrade = semestreGrade;
	}

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}
}
