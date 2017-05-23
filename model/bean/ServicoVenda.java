package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ServicoVenda extends RecursiveTreeObject<ServicoVenda> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty data = new SimpleStringProperty();
	private StringProperty placa = new SimpleStringProperty();
	private DoubleProperty quilometragem = new SimpleDoubleProperty();
	private Servico servico;
	private Funcionario funcionario;
	private Cliente cliente;
	
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
	
	public final String getPlaca() {
		return data.get();
	}
	
	public final void setPlaca(String Placa) {
		placa.set(Placa);
	}
	
	public StringProperty placaProperty() {
		return placa;
	}
	
	public final Double getQuilometragem() {
		return quilometragem.get();
	}
	
	public final void setQuilometragem(Double Quilometragem) {
		quilometragem.set(Quilometragem);
	}
	
	public DoubleProperty quilometragemProperty() {
		return quilometragem;
	}
	
	public Servico getServico() {
		return servico;
	}
	
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}