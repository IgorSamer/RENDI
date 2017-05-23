package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
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
	
	public final String getNome() {
		return nome.get();
	}
	
	public final void setNome(String Nome) {
		nome.set(Nome);
	}
	
	public StringProperty nomeProperty() {
		return nome;
	}
	
	public final String getSobreNome() {
		return sobrenome.get();
	}
	
	public final void setSobreNome(String SobreNome) {
		sobrenome.set(SobreNome);
	}
	
	public StringProperty sobrenomeProperty() {
		return sobrenome;
	}
	
	public final String getDataNascimento() {
		return data_nascimento.get();
	}
	
	public final void setDataNascimento(String DataNascimneto) {
		data_nascimento.set(DataNascimneto);
	}
	
	public StringProperty dataNascimentoProperty() {
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