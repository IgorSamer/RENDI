package model.bean;

public class CombustivelVenda {
	private Integer id;
	private String data;
	private Float litros;
	private Integer status;
	private Cliente cliente;
	private Funcionario funcionario;
	private Bico bico;
	
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
	
	public Float getLitros() {
		return litros;
	}
	
	public void setLitros(Float litros) {
		this.litros = litros;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Bico getBico() {
		return bico;
	}
	
	public void setBico(Bico bico) {
		this.bico = bico;
	}
}