package model.bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Caixa {
	private DoubleProperty vendasTotal = new SimpleDoubleProperty();
	private StringProperty vendasMes = new SimpleStringProperty();
	private StringProperty vendasData = new SimpleStringProperty();
	
	public Caixa(Double VendasTotal, String VendasMes) {
		this.setVendasTotal(VendasTotal);
		this.setVendasMes(VendasMes);
	}
	
	public Caixa(String VendasData, Double VendasTotal) {
		this.setVendasData(VendasData);
		this.setVendasTotal(VendasTotal);
	}
	
	public final Double getVendasTotal() {
		return vendasTotal.get();
	}
	
	public final void setVendasTotal(Double VendasTotal) {
		vendasTotal.set(VendasTotal);
	}
	
	public DoubleProperty vendasTotalProperty() {
		return vendasTotal;
	}
	
	public final String getVendasMes() {
		return vendasMes.get();
	}
	
	public final void setVendasMes(String VendasMes) {
		vendasMes.set(VendasMes);
	}
	
	public StringProperty vendasMesProperty() {
		return vendasMes;
	}
	
	public final String getVendasData() {
		return vendasData.get();
	}
	
	public final void setVendasData(String VendasData) {
		vendasData.set(VendasData);
	}
	
	public StringProperty vendasDataProperty() {
		return vendasData;
	}
}