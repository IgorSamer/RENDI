package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Fidelidade extends RecursiveTreeObject<Fidelidade> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private IntegerProperty pontuacao = new SimpleIntegerProperty();
	private Cliente cliente;
	
	public final Integer getId() {
		return id.get();
	}
	
	public final void setId(Integer Id) {
		id.set(Id);
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	
	public final Integer getPontuacao() {
		return pontuacao.get();
	}
	
	public final void setPontuacao(Integer Pontuacao) {
		pontuacao.set(Pontuacao);
	}
	
	public IntegerProperty pontuacaoProperty() {
		return pontuacao;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}