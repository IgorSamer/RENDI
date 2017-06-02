package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Endereco extends RecursiveTreeObject<Endereco> {
	private StringProperty uf = new SimpleStringProperty();
	private StringProperty cidade = new SimpleStringProperty();
	private StringProperty cep = new SimpleStringProperty();
	private StringProperty bairro = new SimpleStringProperty();
	private StringProperty rua = new SimpleStringProperty();
	private IntegerProperty numero = new SimpleIntegerProperty();
	
	public Endereco(String Uf, String Cidade, String Cep, String Bairro, String Rua, Integer Numero) {
		this.setUf(Uf);
		this.setCidade(Cidade);
		this.setCep(Cep);
		this.setBairro(Bairro);
		this.setRua(Rua);
		this.setNumero(Numero);
	}
	
	public Endereco(String Cidade) {
		this.setCidade(Cidade);
	}
	
	public final String getUf() {
		return uf.get();
	}
	
	public final void setUf(String Uf) {
		uf.set(Uf);
	}
	
	public StringProperty ufProperty() {
		return uf;
	}
	
	public final String getCidade() {
		return cidade.get();
	}
	
	public final void setCidade(String Cidade) {
		cidade.set(Cidade);
	}
	
	public StringProperty cidadeProperty() {
		return cidade;
	}
	
	public final String getCep() {
		return cep.get();
	}
	
	public final void setCep(String Cep) {
		cep.set(Cep);
	}
	
	public StringProperty cepProperty() {
		return cep;
	}
	
	public final String getBairro() {
		return bairro.get();
	}
	
	public final void setBairro(String Bairro) {
		bairro.set(Bairro);
	}
	
	public StringProperty bairroProperty() {
		return bairro;
	}
	
	public final String getRua() {
		return rua.get();
	}
	
	public final void setRua(String Rua) {
		rua.set(Rua);
	}
	
	public StringProperty ruaProperty() {
		return rua;
	}
	
	public final Integer getNumero() {
		return numero.get();
	}
	
	public final void setNumero(Integer Numero) {
		numero.set(Numero);
	}
	
	public IntegerProperty numeroProperty() {
		return numero;
	}
}