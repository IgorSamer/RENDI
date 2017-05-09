package model.bean;

public class Fidelidade {
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