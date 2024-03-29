package br.com.qualitsys.model;

public class ResultJoin {
	
	private String descmontadora; 
	private String mercadoparalelo;
	private String coditem;
	private String descitem;
	private Object imagemitem;
	private String codigosoriginais;
	
	public ResultJoin(String descmontadora, String mercadoparalelo, String coditem, String descitem, Object imagemitem, String codigosoriginais) {
		 
		this.descmontadora = descmontadora;
		this.mercadoparalelo = mercadoparalelo;
		this.coditem = coditem;
		this.descitem = descitem;
		this.imagemitem = imagemitem;
		this.codigosoriginais = codigosoriginais;
	}

	public String getDescmontadora() {
		return descmontadora;
	}

	public void setDescmontadora(String descmontadora) {
		this.descmontadora = descmontadora;
	}

	public String getMercadoparalelo() {
		return mercadoparalelo;
	}

	public void setMercadoparalelo(String mercadoparalelo) {
		this.mercadoparalelo = mercadoparalelo;
	}

	public String getCoditem() {
		return coditem;
	}

	public void setCoditem(String coditem) {
		this.coditem = coditem;
	}

	public String getDescitem() {
		return descitem;
	}

	public void setDescitem(String descitem) {
		this.descitem = descitem;
	}

	public Object getImagemitem() {
		return imagemitem;
	}

	public void setImagemitem(Object imagemitem) {
		this.imagemitem = imagemitem;
	}

	public String getCodigosoriginais() {
		return codigosoriginais;
	}

	public void setCodigosoriginais(String codigosoriginais) {
		this.codigosoriginais = codigosoriginais;
	}

	 
}

