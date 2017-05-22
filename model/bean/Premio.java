package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Premio extends RecursiveTreeObject<Premio> {
	private Integer id;
	private String nome;
	private String descricao;
	private int quantidade_pontos;
	private String imagem;
	
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
	
	public int getQuantidade_pontos() {
		return quantidade_pontos;
	}
	
	public void setQuantidade_pontos(int quantidade_pontos) {
		this.quantidade_pontos = quantidade_pontos;
	}
	
	public String getImagem() {
		return imagem;
	}
	
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
}