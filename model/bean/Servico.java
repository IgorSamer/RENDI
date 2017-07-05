package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Servico extends RecursiveTreeObject<Servico> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	private DoubleProperty preco = new SimpleDoubleProperty();
	private Setor setor;
	
	public Servico(Integer Id, String Nome, Double Preco, Setor Setor) {
		this.setId(Id);
		this.setNome(Nome);
		this.setPreco(Preco);
		this.setSetor(Setor);
	}
	
	public Servico(Integer Id, String Nome, Double Preco) {
		this.setId(Id);
		this.setNome(Nome);
		this.setPreco(Preco);
	}
	
	public Servico(Integer Id) {
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
	
	public Setor getSetor() {
		return setor;
	}
	
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}
}