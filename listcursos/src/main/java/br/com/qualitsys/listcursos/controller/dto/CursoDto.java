package br.com.qualitsys.listcursos.controller.dto;

import br.com.qualitsys.model.Curso;

public class CursoDto {
	
	private Integer idCurso;
	private String nomeCurso;
	
	public CursoDto(Integer idCurso, String nomeCurso) {
		 
		this.idCurso = idCurso;
		this.nomeCurso = nomeCurso;
	}
	public CursoDto(Curso curso) {
		 
		this.idCurso = curso.getIdCurso();
		this.nomeCurso = curso.getNomeCurso();
	}
	public Integer getIdCurso() {
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
