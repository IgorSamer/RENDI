package model.bean;

public class Funcionario extends Pessoa {
	private Integer id;
	private String data_admissao;
	private String data_demissao;
	private Double salario;
	private String foto;
	private Integer ativo;
	private Funcao funcao;
	private Setor setor;
	
	public Funcionario(Integer id, String nome, String sobrenome, String foto, Funcao funcao) {
		this.setId(id);
		this.setNome(nome);
		this.setSobrenome(sobrenome);
		this.setFoto(foto);
		this.setFuncao(funcao);
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getData_admissao() {
		return data_admissao;
	}
	
	public void setData_admissao(String data_admissao) {
		this.data_admissao = data_admissao;
	}
	
	public String getData_demissao() {
		return data_demissao;
	}
	
	public void setData_demissao(String data_demissao) {
		this.data_demissao = data_demissao;
	}
	
	public Double getSalario() {
		return salario;
	}
	
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public Integer getAtivo() {
		return ativo;
	}
	
	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}
	
	public Funcao getFuncao() {
		return funcao;
	}
	
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	
	public Setor getSetor() {
		return setor;
	}
	
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	public static String getCaminhoFoto(String foto) {
		return "/view/img/" + foto;
	}
}