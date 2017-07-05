package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Cliente extends RecursiveTreeObject<Cliente> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private Pessoa pessoa;
	
	public Cliente(Integer Id, Pessoa Pessoa) {
		this.setId(Id);
		this.setPessoa(Pessoa);
	}
	
	public Cliente(Integer Id) {
		this.setId(Id);
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
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	@Override
	public String toString() {
		return this.getPessoa().getNome() + " " + this.getPessoa().getSobrenome();
	}
}