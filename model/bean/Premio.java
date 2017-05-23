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
	
	public final Integer getQuantPontos() {
		return id.get();
	}
	
	public final void setQuantPontos(Integer Quant_pontos) {
		quantidade_pontos.set(Quant_pontos);
	}
	
	public IntegerProperty quantPontosProperty() {
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