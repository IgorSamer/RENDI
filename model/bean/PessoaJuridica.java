package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PessoaJuridica extends RecursiveTreeObject<PessoaJuridica> {
	private StringProperty cnpj = new SimpleStringProperty();
	private StringProperty inscricao_estadual = new SimpleStringProperty();
	private StringProperty nome_fantasia = new SimpleStringProperty();
	private StringProperty razao_social = new SimpleStringProperty();
	
	public final String getCnpj() {
		return cnpj.get();
	}
	
	public final void setCpng(String Cnpj) {
		cnpj.set(Cnpj);
	}
	
	public StringProperty cnpjProperty() {
		return cnpj;
	}
	
	public final String getIncricaoEstadual() {
		return inscricao_estadual.get();
	}
	
	public final void setIncricaoEstadual(String Inscri_Estadual) {
		inscricao_estadual.set(Inscri_Estadual);
	}
	
	public StringProperty inscri_estadualProperty() {
		return inscricao_estadual;
	}
	
	public final String getNomeFantasia() {
		return nome_fantasia.get();
	}
	
	public final void setNomeFantasia(String Nome_fantasia) {
		nome_fantasia.set(Nome_fantasia);
	}
	
	public StringProperty nomeFantasiaProperty() {
		return nome_fantasia;
	}
	
	public final String getRazaoSocial() {
		return razao_social.get();
	}
	
	public final void setRazaoSocial(String RazaoSocial) {
		razao_social.set(RazaoSocial);
	}
	
	public StringProperty razaoSocialProperty() {
		return razao_social;
	}
}