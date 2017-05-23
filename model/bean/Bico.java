package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Bico extends RecursiveTreeObject<Bico> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private Bomba bomba;
	private TanqueReparticao reparticao;
	
	public Bico(Integer Id, Bomba Bomba, TanqueReparticao Reparticao) {
		this.setId(Id);
		this.setBomba(Bomba);
		this.setReparticao(Reparticao);
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
}