package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Produto extends RecursiveTreeObject<Produto> {
	private Integer id;
	private String nome;
	private String descricao;
	private Double preco;
	private float quantidade;
	private Setor setor;
	private OrdemCompra ordem_compra;
	private UnidadeMedida unidade_medida;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public float getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
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
	
	public void setOrdem_compra(OrdemCompra ordem_compra) {
		this.ordem_compra = ordem_compra;
	}
	
	public UnidadeMedida getUnidade_medida() {
		return unidade_medida;
	}
	
	public void setUnidade_medida(UnidadeMedida unidade_medida) {
		this.unidade_medida = unidade_medida;
	}
}