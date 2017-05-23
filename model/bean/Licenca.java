package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Licenca extends RecursiveTreeObject<Licenca> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private String nome;
	private String descricao;
	private String anexo;
	private String protocolo;
	private String data_emissao;
	private String data_vencimento;
	private String data;
	private OrgaoEmissor orgao_emissor;
	private Funcionario funcionario;
	
	public final Integer getId() {
		return id.get();
	}
	
	public final void setId(Integer Id) {
		id.set(Id);
	}
	
	public IntegerProperty idProperty() {
		return id;
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
	
	public String getAnexo() {
		return anexo;
	}
	
	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}
	
	public String getProtocolo() {
		return protocolo;
	}
	
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	
	public String getData_emissao() {
		return data_emissao;
	}
	
	public void setData_emissao(String data_emissao) {
		this.data_emissao = data_emissao;
	}
	
	public String getData_vencimento() {
		return data_vencimento;
	}
	
	public void setData_vencimento(String data_vencimento) {
		this.data_vencimento = data_vencimento;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public OrgaoEmissor getOrgao_emissor() {
		return orgao_emissor;
	}
	
	public void setOrgao_emissor(OrgaoEmissor orgao_emissor) {
		this.orgao_emissor = orgao_emissor;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}