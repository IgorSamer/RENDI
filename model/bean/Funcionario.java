package model.bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Funcionario extends Pessoa {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty data_admissao = new SimpleStringProperty();
	private StringProperty data_demissao = new SimpleStringProperty();
	private DoubleProperty salario = new SimpleDoubleProperty();
	private StringProperty foto = new SimpleStringProperty();
	private IntegerProperty ativo = new SimpleIntegerProperty();
	private Funcao funcao;
	private Setor setor;
	
	public Funcionario(Integer id, String nome, String sobrenome, String foto, Funcao funcao) {
		this.setId(id);
		this.setNome(nome);
		this.setSobreNome(sobrenome);
		this.setFoto(foto);
		this.setFuncao(funcao);
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
	
	public final void setData_demissao(Double Salario) {
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
	
	public static String getCaminhoFoto(String foto) {
		return "/view/img/" + foto;
	}
}