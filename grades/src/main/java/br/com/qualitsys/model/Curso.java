package br.com.qualitsys.model;

public class Curso {
	
	private Integer idCurso;
	private String nomeCurso;
	
	public Curso(Integer idCurso, String nomeCurso) {
		 
		this.idCurso = idCurso;
		this.nomeCurso = nomeCurso;
	}
	
	public Curso() {
		
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
}
