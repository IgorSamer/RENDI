package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Genero extends RecursiveTreeObject<Genero> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	
	public Genero(Integer Id, String Nome) {
		this.setId(Id);
		this.setNome(Nome);
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
	
	public final String getNome() {
		return nome.get();
	}
	
	public final void setNome(String Nome) {
		nome.set(Nome);
	}
	
	public StringProperty nomeProperty() {
		return nome;
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}
}