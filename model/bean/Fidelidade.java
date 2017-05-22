package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Fidelidade extends RecursiveTreeObject<Fidelidade> {
	private Integer id;
	private int pontuacao;
	private Cliente cliente;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getPontuacao() {
		return pontuacao;
	}
	
	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}