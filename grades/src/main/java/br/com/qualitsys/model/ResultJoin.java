package br.com.qualitsys.model;

public class ResultJoin {
	private Integer grupo;
	private Integer idDisciplina;
	private String 	nomeDisciplina;
	private Integer cargaHoraria;
	
	public ResultJoin(Integer grupo, Integer idDisciplina, String nomeDisciplina, Integer cargaHoraria) {
		 
		this.grupo = grupo;
		this.idDisciplina = idDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.cargaHoraria = cargaHoraria;
	}
	
	public ResultJoin() {
		
	}

	public Integer getGrupo() {
		return grupo;
	}

	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}

	public Integer getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Integer idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
}
