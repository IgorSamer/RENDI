package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class PessoaJuridica extends RecursiveTreeObject<PessoaJuridica> {
	private String cnpj;
	private String inscricao_estadual;
	private String nome_fantasia;
	private String razao_social;
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getInscricao_estadual() {
		return inscricao_estadual;
	}
	
	public void setInscricao_estadual(String inscricao_estadual) {
		this.inscricao_estadual = inscricao_estadual;
	}
	
	public String getNome_fantasia() {
		return nome_fantasia;
	}
	
	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}
	
	public String getRazao_social() {
		return razao_social;
	}
	
	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}
}