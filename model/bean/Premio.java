package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Premio extends RecursiveTreeObject<Premio> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	private StringProperty descricao = new SimpleStringProperty();
	private IntegerProperty quantidade_pontos = new SimpleIntegerProperty();
	private StringProperty imagem = new SimpleStringProperty();
	
	public Premio(Integer Id, String Nome, String Descricao, Integer Quantidade_pontos, String Imagem) {
		this.setId(Id);
		this.setNome(Nome);
		this.setDescricao(Descricao);
		this.setQuantidade_pontos(Quantidade_pontos);
		this.setImagem(Imagem);
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
	
	public final String getNome() {
		return nome.get();
	}
	
	public final void setNome(String Nome) {
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
	
	public final Integer getQuantidade_pontos() {
		return id.get();
	}
	
	public final void setQuantidade_pontos(Integer Quantidade_pontos) {
		quantidade_pontos.set(Quantidade_pontos);
	}
	
	public IntegerProperty quantidade_pontosProperty() {
		return quantidade_pontos;
	}
	
	public final String getImagem() {
		return imagem.get();
	}
	
	public final void setImagem(String Imagem) {
		imagem.set(Imagem);
	}
	
	public StringProperty imagemProperty() {
		return imagem;
	}
}