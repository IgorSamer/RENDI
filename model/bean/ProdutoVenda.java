package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProdutoVenda extends RecursiveTreeObject<ProdutoVenda> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty data = new SimpleStringProperty();
	private IntegerProperty unidades = new SimpleIntegerProperty();
	private Produto produto;
	private Funcionario funcionario;
	private Cliente cliente;
	
	public ProdutoVenda(Integer Id, String Data, Integer Unidades, Produto Produto, Funcionario Funcionario, Cliente Cliente) {
		this.setId(Unidades);
		this.setData(Data);
		this.setUnidades(Unidades);
		this.setProduto(Produto);
		this.setFuncionario(Funcionario);
		this.setCliente(Cliente);
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
	
	public final Integer getUnidades() {
		return unidades.get();
	}
	
	public final void setUnidades(Integer Unidades) {
		unidades.set(Unidades);
	}
	
	public IntegerProperty unidadesProperty() {
		return unidades;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
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