package br.com.qualitsys.model;

public class ResultJoin {
	
	private String descmontadora; 
	private String codproduto;
	private String mercadoparalelo;
	private String coditem;
	private String descitem;
	private Object imagemitem;
	
	public ResultJoin(String descmontadora, String codproduto, String mercadoparalelo, String coditem, String descitem, Object imagemitem) {
		 
		this.descmontadora = descmontadora;
		this.codproduto = codproduto;
		this.mercadoparalelo = mercadoparalelo;
		this.coditem = coditem;
		this.descitem = descitem;
		this.imagemitem = imagemitem;
	}

	public String getDescmontadora() {
		return descmontadora;
	}

	public void setDescmontadora(String descmontadora) {
		this.descmontadora = descmontadora;
	}

	public String getCodproduto() {
		return codproduto;
	}

	public void setCodproduto(String codproduto) {
		this.codproduto = codproduto;
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

	 
}

