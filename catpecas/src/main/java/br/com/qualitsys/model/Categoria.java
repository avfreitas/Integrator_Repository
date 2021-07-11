package br.com.qualitsys.model;

public class Categoria {
	
	private String codCategoria; 
	private String descCategoria;
	
	public Categoria(String codCategoria, String descCategoria) {
		 
		this.codCategoria = codCategoria;
		this.descCategoria = descCategoria;
	}

	public String getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(String codCategoria) {
		this.codCategoria = codCategoria;
	}

	public String getDescCategoria() {
		return descCategoria;
	}

	public void setDescCategoria(String descCategoria) {
		this.descCategoria = descCategoria;
	} 
}


