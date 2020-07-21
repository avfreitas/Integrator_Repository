package br.com.qualitsys.scpe.controller.dto;
import br.com.qualitsys.scpe.model.Curso;

public class CursoDto {
	
	private Integer idCurso; 
	private String nomeCurso;
	
	public CursoDto (Curso curso) {
		this.idCurso = curso.getIdCurso();
		this.nomeCurso = curso.getNomeCurso();
	}

	public CursoDto (Integer codCurso, String nomeCurso) {
		this.idCurso = codCurso;
		this.nomeCurso = nomeCurso;
	}
	
	public Integer getIdCurso() {
		return idCurso;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
}
	

