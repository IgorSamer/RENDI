package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pessoa extends RecursiveTreeObject<Pessoa> {
	private StringProperty nome = new SimpleStringProperty();
	private StringProperty sobrenome = new SimpleStringProperty();
	private StringProperty data_nascimento = new SimpleStringProperty();
	private StringProperty email = new SimpleStringProperty();
	private Genero genero;
	private EstadoCivil estado_civil;
	private Telefone telefone;
	
	public Pessoa(String Nome, String Sobrenome, String Data_nascimento, String Email, Genero Genero, EstadoCivil Estado_civil, Telefone Telefone) {
		this.setNome(Nome);
		this.setSobrenome(Sobrenome);
		this.setData_nascimento(Data_nascimento);
		this.setEmail(Email);
		this.setGenero(Genero);
		this.setEstado_civil(Estado_civil);
		this.setTelefone(Telefone);
	}
	
	public Pessoa(String Nome, String Sobrenome) {
		this.setNome(Sobrenome);
		this.setSobrenome(Sobrenome);
	}
	
	public final String getNome() {
		return nome.get();
	}
	
	public final void setNome(String Nome) {
		nome.set(Nome);
	}
	
	public StringProperty nomeProperty() {
		return nome;
	}
	
	public final String getSobrenome() {
		return sobrenome.get();
	}
	
	public final void setSobrenome(String Sobrenome) {
		sobrenome.set(Sobrenome);
	}
	
	public StringProperty sobrenomeProperty() {
		return sobrenome;
	}
	
	public final String getData_nascimento() {
		return data_nascimento.get();
	}
	
	public final void setData_nascimento(String Data_nascimento) {
		data_nascimento.set(Data_nascimento);
	}
	
	public StringProperty data_nascimentoProperty() {
		return data_nascimento;
	}
	
	public final String getEmail() {
		return email.get();
	}
	
	public final void setEmail(String Email) {
		email.set(Email);
	}
	
	public StringProperty emailProperty() {
		return email;
	}
	
	public Genero getGenero() {
		return genero;
	}
	
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	public EstadoCivil getEstado_civil() {
		return estado_civil;
	}
	
	public void setEstado_civil(EstadoCivil estado_civil) {
		this.estado_civil = estado_civil;
	}
	
	public Telefone getTelefone() {
		return telefone;
	}
	
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
}