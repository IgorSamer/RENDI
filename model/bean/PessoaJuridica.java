package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PessoaJuridica extends RecursiveTreeObject<PessoaJuridica> {
	private StringProperty cnpj = new SimpleStringProperty();
	private StringProperty inscricao_estadual = new SimpleStringProperty();
	private StringProperty nome_fantasia = new SimpleStringProperty();
	private StringProperty razao_social = new SimpleStringProperty();
	
	public PessoaJuridica(String Cnpj, String Inscricao_estadual, String Nome_fantasia, String Razao_social) {
		this.setCpnj(Cnpj);
		this.setIncricao_estadual(Inscricao_estadual);
		this.setNome_fantasia(Nome_fantasia);
		this.setRazaoSocial(Razao_social);
	}
	
	public final String getCnpj() {
		return cnpj.get();
	}
	
	public final void setCpnj(String Cnpj) {
		cnpj.set(Cnpj);
	}
	
	public StringProperty cnpjProperty() {
		return cnpj;
	}
	
	public final String getIncricao_estadual() {
		return inscricao_estadual.get();
	}
	
	public final void setIncricao_estadual(String Inscricao_estadual) {
		inscricao_estadual.set(Inscricao_estadual);
	}
	
	public StringProperty inscricao_estadualProperty() {
		return inscricao_estadual;
	}
	
	public final String getNome_fantasia() {
		return nome_fantasia.get();
	}
	
	public final void setNome_fantasia(String Nome_fantasia) {
		nome_fantasia.set(Nome_fantasia);
	}
	
	public StringProperty nome_fantasiaProperty() {
		return nome_fantasia;
	}
	
	public final String getRazao_social() {
		return razao_social.get();
	}
	
	public final void setRazaoSocial(String Razao_social) {
		razao_social.set(Razao_social);
	}
	
	public StringProperty razao_socialProperty() {
		return razao_social;
	}
}