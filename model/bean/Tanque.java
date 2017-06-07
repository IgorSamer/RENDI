package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tanque extends RecursiveTreeObject<Tanque> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	private FloatProperty capacidade = new SimpleFloatProperty();
	private StringProperty cor = new SimpleStringProperty();
	
	public Tanque(Integer Id, String Nome, Float Capacidade, String Cor) {
		this.setId(Id);
		this.setNome(Nome);
		this.setCapacidade(Capacidade);
		this.setCor(Cor);
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
	
	public final Float getCapacidade() {
		return capacidade.get();
	}
	
	public final void setCapacidade(Float Capacidade) {
		capacidade.set(Capacidade);
	}
	
	public FloatProperty quantidadeProperty() {
		return capacidade;
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
}