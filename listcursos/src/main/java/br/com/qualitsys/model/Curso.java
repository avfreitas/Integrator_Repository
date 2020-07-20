package br.com.qualitsys.model;

public class Curso {
	
	private Integer idCurso;
	private String nomeCurso;
	private String timestampCurso;
	private String datetimeCurso;
	
	public Curso(Integer idCurso, String nomeCurso) {
		 
		this.idCurso = idCurso;
		this.nomeCurso = nomeCurso;
		 
	}

	public Curso(	Integer idCurso, 
					String nomeCurso, 
					String timestampCurso, 
					String datetimeCurso) {
		 
		this.idCurso = idCurso;
		this.nomeCurso = nomeCurso;
		this.timestampCurso = timestampCurso;
		this.datetimeCurso = datetimeCurso;
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

	public String getTimestampCurso() {
		return timestampCurso;
	}

	public void setTimestampCurso(String timestampCurso) {
		this.timestampCurso = timestampCurso;
	}

	public String getDatetimeCurso() {
		return datetimeCurso;
	}

	public void setDatetimeCurso(String datetimeCurso) {
		this.datetimeCurso = datetimeCurso;
	}
}
