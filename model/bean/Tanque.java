package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Tanque extends RecursiveTreeObject<Tanque> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private FloatProperty capacidade = new SimpleFloatProperty();
	
	public Tanque(Integer Id, Float Capacidade) {
		this.setId(Id);
		this.setCapacidade(Capacidade);
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
	
	public final Float getCapacidade() {
		return capacidade.get();
	}
	
	public final void setCapacidade(Float Capacidade) {
		capacidade.set(Capacidade);
	}
	
	public FloatProperty quantidadeProperty() {
		return capacidade;
	}
}