package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Cliente extends RecursiveTreeObject<Cliente> {
	private Integer id;
	private Pessoa pessoa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}