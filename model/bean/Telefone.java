package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Telefone extends RecursiveTreeObject<Telefone> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty numero = new SimpleStringProperty();
	
	public final Integer getId() {
		return id.get();
	}
	
	public final void setId(Integer Id) {
		id.set(Id);
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	
	public final String getNumero() {
		return numero.get();
	}
	
	public final void setNumero(String Numero) {
		numero.set(Numero);
	}
	
	public StringProperty numeroProperty() {
		return numero;
	}
}