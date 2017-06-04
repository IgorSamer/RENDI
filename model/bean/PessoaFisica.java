package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PessoaFisica extends RecursiveTreeObject<PessoaFisica> {
	private StringProperty rg = new SimpleStringProperty();
	private StringProperty cpf = new SimpleStringProperty();
	private Escolaridade escolaridade;
	
	public PessoaFisica(String Rg, String Cpf, Escolaridade Escolaridade) {
		this.setRg(Rg);
		this.setCpf(Cpf);
		this.setEscolaridade(Escolaridade);
	}
	
	public final String getRg() {
		return rg.get();
	}
	
	public final void setRg(String Rg) {
		rg.set(Rg);
	}
	
	public StringProperty rgProperty() {
		return rg;
	}
	
	public final String getCpf() {
		return cpf.get();
	}
	
	public final void setCpf(String Cpf) {
		cpf.set(Cpf);
	}
	
	public StringProperty cpfProperty() {
		return cpf;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}
}