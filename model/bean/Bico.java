package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bico extends RecursiveTreeObject<Bico> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	private Bomba bomba;
	private TanqueReparticao reparticao;
	
	public Bico(Integer Id, String Nome, Bomba Bomba, TanqueReparticao Reparticao) {
		this.setId(Id);
		this.setNome(Nome);
		this.setBomba(Bomba);
		this.setReparticao(Reparticao);
	}
	
	public Bico(Integer Id, String Nome, TanqueReparticao Reparticao) {
		this.setId(Id);
		this.setNome(Nome);
		this.setReparticao(Reparticao);
	}
	
	public Bico(Integer Id) {
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
	
	public Bomba getBomba() {
		return bomba;
	}
	
	public void setBomba(Bomba bomba) {
		this.bomba = bomba;
	}
	
	public TanqueReparticao getReparticao() {
		return reparticao;
	}
	
	public void setReparticao(TanqueReparticao reparticao) {
		this.reparticao = reparticao;
	}
	
	@Override
	public String toString() {
		return this.getNome() + " (" + this.getReparticao().getCombustivel().getNome() + ")";
	}
}