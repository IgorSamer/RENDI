package model.bean;

import java.util.ArrayList;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrdemCompra extends RecursiveTreeObject<OrdemCompra> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty data = new SimpleStringProperty();
	private StringProperty data_prevista = new SimpleStringProperty();
	private OrdemCompraStatus status;
	private StringProperty observacoes = new SimpleStringProperty();
	private DoubleProperty valor = new SimpleDoubleProperty();
	private IntegerProperty parcelas = new SimpleIntegerProperty();
	private IntegerProperty distancia_pagamento = new SimpleIntegerProperty();
	private Fornecedor fornecedor;
	private Funcionario funcionario;
	private TipoPagamento tipo_pagamento;
	private ArrayList<ProdutoOrdemCompra> produtos;
	
	public OrdemCompra(Integer Id, String Data, String Data_prevista, OrdemCompraStatus Status, String Observacoes, Double Valor, Integer Parcelas, Integer Distancia_pagamento, Fornecedor Fornecedor, Funcionario Funcionario, TipoPagamento Tipo_pagamento, ArrayList<ProdutoOrdemCompra> Produtos) {
		this.setId(Id);
		this.setData(Data);
		this.setData_prevista(Data_prevista);
		this.setStatus(Status);
		this.setObservacoes(Observacoes);
		this.setValor(Valor);
		this.setParcelas(Parcelas);
		this.setDistancia_pagamento(Distancia_pagamento);
		this.setFornecedor(Fornecedor);
		this.setFuncionario(Funcionario);
		this.setTipo_pagamento(Tipo_pagamento);
		this.setProdutos(Produtos);
	}
	
	public OrdemCompra(Integer Id) {
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
	
	public final String getData() {
		return data.get();
	}
	
	public final void setData(String Data) {
		data.set(Data);
	}
	
	public StringProperty dataProperty() {
		return data;
	}
	
	public final String getData_prevista() {
		return data_prevista.get();
	}
	
	public final void setData_prevista(String Data_prevista) {
		data_prevista.set(Data_prevista);
	}
	
	public StringProperty data_previstaProperty() {
		return data_prevista;
	}
	
	public final String getObservacoes() {
		return observacoes.get();
	}
	
	public final void setObservacoes(String Observacoes) {
		observacoes.set(Observacoes);
	}
	
	public StringProperty observacoesProperty() {
		return observacoes;
	}
	
	public final Double getValor() {
		return valor.get();
	}
	
	public final void setValor(Double Valor) {
		valor.set(Valor);
	}
	
	public DoubleProperty valorProperty() {
		return valor;
	}
	
	public final Integer getParcelas() {
		return parcelas.get();
	}
	
	public final void setParcelas(Integer Parcelas) {
		parcelas.set(Parcelas);
	}
	
	public IntegerProperty parcelasProperty() {
		return parcelas;
	}
	
	public final Integer getDistancia_pagamento() {
		return distancia_pagamento.get();
	}
	
	public final void setDistancia_pagamento(Integer Distancia_pagamento) {
		distancia_pagamento.set(Distancia_pagamento);
	}
	
	public IntegerProperty distancia_pagamentoProperty() {
		return distancia_pagamento;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public TipoPagamento getTipo_pagamento() {
		return tipo_pagamento;
	}
	
	public void setTipo_pagamento(TipoPagamento tipo_pagamento) {
		this.tipo_pagamento = tipo_pagamento;
	}
	
	public ArrayList<ProdutoOrdemCompra> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(ArrayList<ProdutoOrdemCompra> Produtos) {
		this.produtos = Produtos;
	}

	public OrdemCompraStatus getStatus() {
		return status;
	}

	public void setStatus(OrdemCompraStatus status) {
		this.status = status;
	}
}