package model.bean;

import java.util.ArrayList;

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
	private ArrayList<TanqueReparticao> reparticoes;
	
	public Tanque(Integer Id, String Nome, Float Capacidade, String Cor, ArrayList<TanqueReparticao> Reparticoes) {
		this.setId(Id);
		this.setNome(Nome);
		this.setCapacidade(Capacidade);
		this.setCor(Cor);
		this.setReparticoes(Reparticoes);
	}
	
	public Tanque(Integer Id) {
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

	public ArrayList<TanqueReparticao> getReparticoes() {
		return reparticoes;
	}

	public void setReparticoes(ArrayList<TanqueReparticao> Reparticoes) {
		this.reparticoes = Reparticoes;
	}
	
	@Override
	public String toString() {
		String tanqueInfo = this.getNome() + " (";
		
		for(int i = 0; i < this.getReparticoes().size(); i++) {
			if(i == 0) {
				tanqueInfo += this.getReparticoes().get(i).getCombustivel().getNome();
			} else {
				tanqueInfo += " - " + this.getReparticoes().get(i).getCombustivel().getNome();
			}
		}
		
		return tanqueInfo + ")";
	}
}