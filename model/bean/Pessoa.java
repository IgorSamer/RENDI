package model.bean;

import java.util.ArrayList;

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
	private ArrayList<Telefone> telefones;
	private PessoaFisica pessoa_fisica;
	private PessoaJuridica pessoa_juridica;
	private Endereco endereco;
	
	public Pessoa(String Nome, String Sobrenome, String Data_nascimento, String Email, Genero Genero, EstadoCivil Estado_civil, ArrayList<Telefone> Telefones, PessoaFisica Pessoa_fisica, Endereco Endereco) {
		this.setNome(Nome);
		this.setSobrenome(Sobrenome);
		this.setData_nascimento(Data_nascimento);
		this.setEmail(Email);
		this.setGenero(Genero);
		this.setEstado_civil(Estado_civil);
		this.setTelefones(Telefones);
		this.setPessoa_fisica(Pessoa_fisica);
		this.setEndereco(Endereco);
	}
	
	public Pessoa(String Nome, String Sobrenome) {
		this.setNome(Nome);
		this.setSobrenome(Sobrenome);
	}
	
	public Pessoa(String Nome, String Email, ArrayList<Telefone> Telefones) {
		this.setNome(Nome);
		this.setEmail(Email);
		this.setTelefones(Telefones);
	}
	
	public Pessoa(String Nome, String Sobrenome, String Email, PessoaFisica Pessoa_fisica, Endereco Endereco) {
		this.setNome(Nome);
		this.setSobrenome(Sobrenome);
		this.setEmail(Email);
		this.setPessoa_fisica(Pessoa_fisica);
		this.setEndereco(Endereco);
	}
	
	public Pessoa(String Nome, String Sobrenome, String Email, PessoaJuridica Pessoa_juridica, Endereco Endereco) {
		this.setNome(Nome);
		this.setSobrenome(Sobrenome);
		this.setEmail(Email);
		this.setPessoa_juridica(Pessoa_juridica);
		this.setEndereco(Endereco);
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
	
	public void setEstado_civil(EstadoCivil Estado_civil) {
		this.estado_civil = Estado_civil;
	}
	
	public ArrayList<Telefone> getTelefones() {
		return telefones;
	}
	
	public void setTelefones(ArrayList<Telefone> Telefones) {
		this.telefones = Telefones;
	}
	
	public PessoaFisica getPessoa_fisica() {
		return pessoa_fisica;
	}
	
	public void setPessoa_fisica(PessoaFisica Pessoa_fisica) {
		this.pessoa_fisica = Pessoa_fisica;
	}
	
	public PessoaJuridica getPessoa_juridica() {
		return pessoa_juridica;
	}
	
	public void setPessoa_juridica(PessoaJuridica Pessoa_juridica) {
		this.pessoa_juridica = Pessoa_juridica;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco Endereco) {
		this.endereco = Endereco;
	}
}