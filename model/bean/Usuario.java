package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario extends RecursiveTreeObject<Usuario> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty usuario = new SimpleStringProperty();
	private StringProperty senha = new SimpleStringProperty();
	private StringProperty data = new SimpleStringProperty();
	private IntegerProperty ativo = new SimpleIntegerProperty();
	private Funcionario funcionario;
	
	public Usuario(Integer Id, String Usuario, String Senha, String Data, Integer Ativo, Funcionario Funcionario) {
		this.setId(Id);
		this.setUsuario(Usuario);
		this.setSenha(Senha);
		this.setData(Data);
		this.setAtivo(Ativo);
		this.setFuncionario(Funcionario);
	}
	
	public Usuario(String Usuario, String Senha, String Data, Integer Ativo) {
		this.setUsuario(Usuario);
		this.setSenha(Senha);
		this.setData(Data);
		this.setAtivo(Ativo);
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
	
	public final String getUsuario() {
		return usuario.get();
	}
	
	public final void setUsuario(String Usuario) {
		usuario.set(Usuario);
	}
	
	public StringProperty usuarioProperty() {
		return usuario;
	}
	
	public final String getSenha() {
		return senha.get();
	}
	
	public final void setSenha(String Senha) {
		senha.set(Senha);
	}
	
	public StringProperty senhaProperty() {
		return senha;
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
	
	public final Integer getAtivo() {
		return ativo.get();
	}
	
	public final void setAtivo(Integer Ativo) {
		ativo.set(Ativo);
	}
	
	public IntegerProperty ativoProperty() {
		return ativo;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}