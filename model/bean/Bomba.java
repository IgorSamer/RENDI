package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Bomba extends RecursiveTreeObject<Bomba> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private Tanque tanque;
	
	public final Integer getId() {
		return id.get();
	}
	
	public final void setId(Integer Id) {
		id.set(Id);
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	
	public Tanque getTanque() {
		return tanque;
	}
	
	public void setTanque(Tanque tanque) {
		this.tanque = tanque;
	}
}