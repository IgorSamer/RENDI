package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Premiacao extends RecursiveTreeObject<Premiacao> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty data = new SimpleStringProperty();
	private Fidelidade fidelidade;
	private Premio premio;
	
	public Premiacao(Integer Id, String Data, Fidelidade Fidelidade, Premio Premio) {
		this.setId(Id);
		this.setData(Data);
		this.setFidelidade(Fidelidade);
		this.setPremio(Premio);
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
	
	public final String getData() {
		return data.get();
	}
	
	public final void setData(String Data) {
		data.set(Data);
	}
	
	public StringProperty dataProperty() {
		return data;
	}
	
	public Fidelidade getFidelidade() {
		return fidelidade;
	}
	
	public void setFidelidade(Fidelidade fidelidade) {
		this.fidelidade = fidelidade;
	}
	
	public Premio getPremio() {
		return premio;
	}
	
	public void setPremio(Premio premio) {
		this.premio = premio;
	}
}