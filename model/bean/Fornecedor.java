package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Fornecedor extends RecursiveTreeObject<Fornecedor> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty email = new SimpleStringProperty();
	private StringProperty razao_social = new SimpleStringProperty();
	private StringProperty cnpj = new SimpleStringProperty();
	private StringProperty inscricao_estadual = new SimpleStringProperty();
	private StringProperty nome_fantasia = new SimpleStringProperty();
	private IntegerProperty status = new SimpleIntegerProperty();
	private Telefone telefone;
	
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
	
	public final String getRazao_social() {
		return razao_social.get();
	}
	
	public final void setRazao_social(String Razao_social) {
		razao_social.set(Razao_social);
	}
	
	public StringProperty razao_socialProperty() {
		return razao_social;
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
	
	public final String getInscricao_estadual() {
		return inscricao_estadual.get();
	}
	
	public final void setInscricao_estadual(String Inscricao_estadual) {
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
	
	public final Integer getStatus() {
		return status.get();
	}
	
	public final void setStatus(Integer Status) {
		status.set(Status);
	}
	
	public IntegerProperty statusProperty() {
		return status;
	}
	
	public Telefone getTelefone() {
		return telefone;
	}
	
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
}