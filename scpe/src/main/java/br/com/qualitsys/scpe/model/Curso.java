package br.com.qualitsys.scpe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Curso {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Integer idCurso;
	private String nomeCurso;
	private String timestampCurso;
	private String datetimeCurso;
	
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

