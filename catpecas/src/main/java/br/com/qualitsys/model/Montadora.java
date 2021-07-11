package br.com.qualitsys.model;

public class Montadora {
	
	private String codMontadora;
	private String descMontadora;
	
	public Montadora(String codMontadora, String descMontadora) {
		 
		this.codMontadora = codMontadora;
		this.descMontadora = descMontadora;
	}

	public String getCodMontadora() {
		return codMontadora;
	}

	public void setCodMontadora(String codMontadora) {
		this.codMontadora = codMontadora;
	}

	public String getDescMontadora() {
		return descMontadora;
	}

	public void setDescMontadora(String descMontadora) {
		this.descMontadora = descMontadora;
	}

}
