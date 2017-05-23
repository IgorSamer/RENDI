package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Produto extends RecursiveTreeObject<Produto> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	private StringProperty descricao = new SimpleStringProperty();
	private DoubleProperty preco = new SimpleDoubleProperty();
	private FloatProperty quantidade = new SimpleFloatProperty();
	private Setor setor;
	private OrdemCompra ordem_compra;
	private UnidadeMedida unidade_medida;
	
	public Produto(Integer Id, String Nome, String Descricao, Double Preco, Float Quantidade, Setor Setor, OrdemCompra Ordem_compra, UnidadeMedida Unidade_medida) {
		this.setId(Id);
		this.setNome(Nome);
		this.setDescricao(Descricao);
		this.setPreco(Preco);
		this.setQuantidade(Quantidade);
		this.setSetor(Setor);
		this.setOrdem_compra(Ordem_compra);
		this.setUnidade_medida(Unidade_medida);
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
	
	public final String getNome() {
		return nome.get();
	}
	
	public final void setNome(String Nome) {
		nome.set(Nome);
	}
	
	public StringProperty nomeProperty() {
		return nome;
	}
	
	public final String getDescricao() {
		return descricao.get();
	}
	
	public final void setDescricao(String Descricao) {
		descricao.set(Descricao);
	}
	
	public final Float getQuantidade() {
		return quantidade.get();
	}
	
	public final void setQuantidade(Float Quantidade) {
		quantidade.set(Quantidade);
	}
	
	public FloatProperty quantidadeProperty() {
		return quantidade;
	}
	
	public final Double getPreco() {
		return preco.get();
	}
	
	public final void setPreco(Double Preco) {
		preco.set(Preco);
	}
	
	public DoubleProperty precoProperty() {
		return preco;
	}
	
	public Setor getSetor() {
		return setor;
	}
	
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	public OrdemCompra getOrdem_compra() {
		return ordem_compra;
	}
	
	public void setOrdem_compra(OrdemCompra Ordem_compra) {
		this.ordem_compra = Ordem_compra;
	}
	
	public UnidadeMedida getUnidade_medida() {
		return unidade_medida;
	}
	
	public void setUnidade_medida(UnidadeMedida Unidade_medida) {
		this.unidade_medida = Unidade_medida;
	}
}