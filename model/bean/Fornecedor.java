package model.bean;

public class Fornecedor {
	private Integer id;
	private String email;
	private String razao_social;
	private String cnpj;
	private String inscricao_estadual;
	private String nome_fantasia;
	private Integer status;
	private Telefone telefone;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRazao_social() {
		return razao_social;
	}
	
	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getInscricao_estadual() {
		return inscricao_estadual;
	}
	
	public void setInscricao_estadual(String inscricao_estadual) {
		this.inscricao_estadual = inscricao_estadual;
	}
	
	public String getNome_fantasia() {
		return nome_fantasia;
	}
	
	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Telefone getTelefone() {
		return telefone;
	}
	
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
}