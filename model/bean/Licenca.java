package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Licenca extends RecursiveTreeObject<Licenca> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	private StringProperty descricao = new SimpleStringProperty();
	private StringProperty anexo = new SimpleStringProperty();
	private StringProperty protocolo = new SimpleStringProperty();
	private StringProperty data_emissao = new SimpleStringProperty();
	private StringProperty data_vencimento = new SimpleStringProperty();
	private StringProperty data = new SimpleStringProperty();
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
	
	public final String getNome() {
		return nome.get();
	}
	
	public final void setId(String Nome) {
		nome.set(Nome);
	}
	
	public StringProperty nomeProperty() {
		return nome;
	}
	
	public final String getDescricao() {
		return descricao.get();
	}
	
	public final void setDescricao(String Descricao) {
		descricao.set(Descricao);
	}
	
	public StringProperty descricaoProperty() {
		return descricao;
	}
	
	public final String getAnexo() {
		return anexo.get();
	}
	
	public final void setAnexo(String Anexo) {
		anexo.set(Anexo);
	}
	
	public StringProperty anexoProperty() {
		return anexo;
	}
	
	public final String getProtocolo() {
		return protocolo.get();
	}
	
	public final void setProtocolo(String Protocolo) {
		protocolo.set(Protocolo);
	}
	
	public StringProperty protocoloProperty() {
		return protocolo;
	}
	
	public final String getData_emissao() {
		return data_emissao.get();
	}
	
	public final void setData_emissao(String Data_emissao) {
		data_emissao.set(Data_emissao);
	}
	
	public StringProperty data_emissaoProperty() {
		return data_emissao;
	}
	
	public final String getData_vencimento() {
		return data_vencimento.get();
	}
	
	public final void setData_vencimento(String Data_vencimento) {
		data_vencimento.set(Data_vencimento);
	}
	
	public StringProperty data_vencimentoProperty() {
		return data_vencimento;
	}
	
	public final String getData() {
		return data.get();
	}
	
	public final void setData(String Data) {
		data.set(Data);
	}
	
	public StringProperty dataProperty() {
		return data;
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