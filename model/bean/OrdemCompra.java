package model.bean;

public class OrdemCompra {
	private Integer id;
	private String data;
	private String data_prevista;
	private Integer status;
	private String observacoes;
	private Double valor;
	private int parcelas;
	private int distancia_pagamento;
	private Fornecedor fornecedor;
	private Funcionario funcionario;
	private TipoPagamento tipo_pagamento;
	
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
	
	public String getData_prevista() {
		return data_prevista;
	}
	
	public void setData_prevista(String data_prevista) {
		this.data_prevista = data_prevista;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getObservacoes() {
		return observacoes;
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public int getParcelas() {
		return parcelas;
	}
	
	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}
	
	public int getDistancia_pagamento() {
		return distancia_pagamento;
	}
	
	public void setDistancia_pagamento(int distancia_pagamento) {
		this.distancia_pagamento = distancia_pagamento;
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
}