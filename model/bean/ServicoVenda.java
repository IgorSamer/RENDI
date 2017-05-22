package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class ServicoVenda extends RecursiveTreeObject<ServicoVenda> {
	private Integer id;
	private String data;
	private String placa;
	private Double quilometragem;
	private Servico servico;
	private Funcionario funcionario;
	private Cliente cliente;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public Double getQuilometragem() {
		return quilometragem;
	}
	
	public void setQuilometragem(Double quilometragem) {
		this.quilometragem = quilometragem;
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