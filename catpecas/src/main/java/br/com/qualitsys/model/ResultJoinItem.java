package br.com.qualitsys.model;

public class ResultJoinItem {
	
	private String descitem; 
	private String codigosoriginais;
	private String mercadoparalelo;
	
	public ResultJoinItem(String descitem, String codigosoriginais, String mercadoparalelo) {
		 
		this.descitem = descitem;
		this.codigosoriginais = codigosoriginais;
		this.mercadoparalelo = mercadoparalelo;
	}

	public String getDescitem() {
		return descitem;
	}

	public void setDescitem(String descitem) {
		this.descitem = descitem;
	}

	public String getCodigosoriginais() {
		return codigosoriginais;
	}

	public void setCodigosoriginais(String codigosoriginais) {
		this.codigosoriginais = codigosoriginais;
	}

	public String getMercadoparalelo() {
		return mercadoparalelo;
	}

	public void setMercadoparalelo(String mercadoparalelo) {
		this.mercadoparalelo = mercadoparalelo;
	}
 
}

