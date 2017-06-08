package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Combustivel extends RecursiveTreeObject<Combustivel> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	private DoubleProperty preco = new SimpleDoubleProperty();
	private StringProperty cor = new SimpleStringProperty();
	
	public Combustivel(Integer Id, String Nome, Double Preco, String Cor) {
		this.setId(Id);
		this.setNome(Nome);
		this.setPreco(Preco);
		this.setCor(Cor);
	}
	
	public Combustivel(Integer Id) {
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
	
	public final String getNome() {
		return nome.get();
	}
	
	public final void setNome(String Nome) {
		nome.set(Nome);
	}
	
	public StringProperty nomeProperty() {
		return nome;
	}
	
	public final Double getPreco() {
		return preco.get();
	}
	
	public final void setPreco(Double Preco) {
		preco.set(Preco);
	}
	
	public DoubleProperty precoProperty() {
		return preco;
	}
	
	public final String getCor() {
		return cor.get();
	}
	
	public final void setCor(String Cor) {
		cor.set(Cor);
	}
	
	public StringProperty corProperty() {
		return cor;
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}
}