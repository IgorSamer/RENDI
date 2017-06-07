package model.bean;

import java.util.ArrayList;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Fornecedor extends RecursiveTreeObject<Fornecedor> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty email = new SimpleStringProperty();
	private StringProperty nome_empresarial = new SimpleStringProperty();
	private StringProperty cnpj = new SimpleStringProperty();
	private StringProperty nome_fantasia = new SimpleStringProperty();
	private IntegerProperty status = new SimpleIntegerProperty();
	private ArrayList<Telefone> telefones;
	private Endereco endereco;
	
	public Fornecedor(Integer Id, String Email, String Nome_empresarial, String Cnpj, String Nome_fantasia, Integer Status, ArrayList<Telefone> Telefones, Endereco Endereco) {
		this.setId(Id);
		this.setEmail(Email);
		this.setNome_empresarial(Nome_empresarial);
		this.setCnpj(Cnpj);
		this.setNome_fantasia(Nome_fantasia);
		this.setStatus(Status);
		this.setTelefones(Telefones);
		this.setEndereco(Endereco);
	}
	
	public Fornecedor(String Nome_fantasia) {
		this.setNome_fantasia(Nome_fantasia);
	}
	
	public Fornecedor(Integer Id) {
		this.setId(Id);
	}
	
	public final Integer getId() {
		return id.get();
	}
	
	public final void setId(Integer Id) {
		id.set(Id);
	}
	
	public IntegerProperty idProperty() {
		return id;
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
	
	public final String getNome_empresarial() {
		return nome_empresarial.get();
	}
	
	public final void setNome_empresarial(String Nome_empresarial) {
		nome_empresarial.set(Nome_empresarial);
	}
	
	public StringProperty nome_empresarialProperty() {
		return nome_empresarial;
	}
	
	public final String getCnpj() {
		return cnpj.get();
	}
	
	public final void setCnpj(String Cnpj) {
		cnpj.set(Cnpj);
	}
	
	public StringProperty cnpjProperty() {
		return cnpj;
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
	
	public final Integer getStatus() {
		return status.get();
	}
	
	public final void setStatus(Integer Status) {
		status.set(Status);
	}
	
	public IntegerProperty statusProperty() {
		return status;
	}
	
	public ArrayList<Telefone> getTelefones() {
		return telefones;
	}
	
	public void setTelefones(ArrayList<Telefone> Telefones) {
		this.telefones = Telefones;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco Endereco) {
		this.endereco = Endereco;
	}
	
	@Override
	public String toString() {
		return this.getNome_fantasia();
	}
}