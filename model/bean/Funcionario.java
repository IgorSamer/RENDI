package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Funcionario  extends RecursiveTreeObject<Funcionario> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty data_admissao = new SimpleStringProperty();
	private StringProperty data_demissao = new SimpleStringProperty();
	private DoubleProperty salario = new SimpleDoubleProperty();
	private StringProperty foto = new SimpleStringProperty();
	private IntegerProperty ativo = new SimpleIntegerProperty();
	private Funcao funcao;
	private Setor setor;
	private Pessoa pessoa;
	
	public Funcionario(Integer Id, String Data_admissao, String Data_demissao, Double Salario, String Foto, Integer Ativo, Funcao Funcao, Setor Setor, Pessoa Pessoa) {
		this.setId(Id);
		this.setData_admissao(Data_admissao);
		this.setData_demissao(Data_demissao);
		this.setSalario(Salario);
		this.setFoto(Foto);
		this.setAtivo(Ativo);
		this.setFuncao(Funcao);
		this.setSetor(Setor);
		this.setPessoa(Pessoa);
	}
	
	public Funcionario(Integer Id, String Foto, Double Salario, Funcao Funcao, Setor Setor, Pessoa Pessoa) {
		this.setId(Id);
		this.setFoto(Foto);
		this.setSalario(Salario);
		this.setFuncao(Funcao);
		this.setPessoa(Pessoa);
		this.setSetor(Setor);
	}

	public final Integer getId() {
		return id.get();
	}
	
	public final void setId(Integer Id) {
		id.set(Id);
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	
	public final String getData_admissao() {
		return data_admissao.get();
	}
	
	public final void setData_admissao(String Data_admissao) {
		data_admissao.set(Data_admissao);
	}
	
	public StringProperty data_admissaoProperty() {
		return data_admissao;
	}
	
	public final String getData_demissao() {
		return data_demissao.get();
	}
	
	public final void setData_demissao(String Data_demissao) {
		data_demissao.set(Data_demissao);
	}
	
	public StringProperty data_demissaoProperty() {
		return data_demissao;
	}
	
	public final Double getSalario() {
		return salario.get();
	}
	
	public final void setSalario(Double Salario) {
		salario.set(Salario);
	}
	
	public DoubleProperty salarioProperty() {
		return salario;
	}
	
	public final String getFoto() {
		return foto.get();
	}
	
	public final void setFoto(String Foto) {
		foto.set(Foto);
	}
	
	public StringProperty fotoProperty() {
		return foto;
	}
	
	public final Integer getAtivo() {
		return ativo.get();
	}
	
	public final void setAtivo(Integer Ativo) {
		ativo.set(Ativo);
	}
	
	public IntegerProperty ativoProperty() {
		return ativo;
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
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public static String getCaminhoFoto(String foto) {
		return "/view/img/" + foto;
	}
}