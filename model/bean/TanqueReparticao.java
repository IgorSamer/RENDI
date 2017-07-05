package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TanqueReparticao extends RecursiveTreeObject<TanqueReparticao> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private Combustivel combustivel;
	private Tanque tanque;
	
	public TanqueReparticao(Integer Id, Combustivel Combustivel, Tanque Tanque) {
		this.setId(Id);
		this.setCombustivel(Combustivel);
		this.setTanque(Tanque);
	}
	
	public TanqueReparticao(Combustivel Combustivel) {
		this.setCombustivel(Combustivel);
	}
	
	public TanqueReparticao(Integer Id, Combustivel Combustivel) {
		this.setId(Id);
		this.setCombustivel(Combustivel);
	}
	
	public TanqueReparticao(Integer Id) {
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
	
	public Combustivel getCombustivel() {
		return combustivel;
	}
	
	public void setCombustivel(Combustivel combustivel) {
		this.combustivel = combustivel;
	}
	
	public Tanque getTanque() {
		return tanque;
	}
	
	public void setTanque(Tanque tanque) {
		this.tanque = tanque;
	}
	
	@Override
	public String toString() {
		return this.getCombustivel().getNome();
	}
}