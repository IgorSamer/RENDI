package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CombustivelVenda extends RecursiveTreeObject<CombustivelVenda> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty data = new SimpleStringProperty();
	private FloatProperty litros = new SimpleFloatProperty();
	private IntegerProperty status = new SimpleIntegerProperty();
	private Cliente cliente;
	private Funcionario funcionario;
	private Bico bico;
	
	public CombustivelVenda(Integer Id, String Data, Float Litros, Integer Status, Cliente Cliente, Funcionario Funcionario, Bico Bico) {
		this.setId(Id);
		this.setData(Data);
		this.setLitros(Litros);
		this.setStatus(Status);
		this.setCliente(Cliente);
		this.setFuncionario(Funcionario);
		this.setBico(Bico);
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
	
	public final Float getLitros() {
		return litros.get();
	}
	
	public final void setLitros(Float Litros) {
		litros.set(Litros);
	}
	
	public FloatProperty litrosProperty() {
		return litros;
	}
	
	public final Integer getStatus() {
		return status.get();
	}
	
	public final void setStatus(Integer Status) {
		status.set(Status);
	}
	
	public IntegerProperty statusProperty() {
		return status;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Bico getBico() {
		return bico;
	}
	
	public void setBico(Bico bico) {
		this.bico = bico;
	}
}