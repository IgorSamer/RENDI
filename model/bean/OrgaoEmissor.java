package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class OrgaoEmissor extends RecursiveTreeObject<OrgaoEmissor> {
	private Integer id;
	private String nome;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}