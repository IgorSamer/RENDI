package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Telefone extends RecursiveTreeObject<Telefone> {
	private Integer id;
	private String numero;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
}