package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ProdutoOrdemCompra extends RecursiveTreeObject<ProdutoOrdemCompra> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private IntegerProperty quantidade = new SimpleIntegerProperty();
	private OrdemCompra ordemCompra;
	private Produto produto;
	
	public ProdutoOrdemCompra(Integer Id, Integer Quantidade, OrdemCompra OrdemCompra, Produto Produto) {
		this.setId(Id);
		this.setQuantidade(Quantidade);
		this.setOrdemCompra(OrdemCompra);
		this.setProduto(Produto);
	}
	
	public ProdutoOrdemCompra(Integer Id, Integer Quantidade, Produto Produto) {
		this.setId(Id);
		this.setQuantidade(Quantidade);
		this.setProduto(Produto);
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
	
	public final Integer getQuantidade() {
		return quantidade.get();
	}
	
	public final void setQuantidade(Integer Quantidade) {
		quantidade.set(Quantidade);
	}
	
	public IntegerProperty quantidadeProperty() {
		return quantidade;
	}

	public OrdemCompra getOrdemCompra() {
		return ordemCompra;
	}

	public void setOrdemCompra(OrdemCompra OrdemCompra) {
		this.ordemCompra = OrdemCompra;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto Produto) {
		this.produto = Produto;
	}
}