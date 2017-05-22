package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Tanque extends RecursiveTreeObject<Tanque> {
	private Integer id;
	private float capacidade;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public float getCapacidade() {
		return capacidade;
	}
	
	public void setCapacidade(float capacidade) {
		this.capacidade = capacidade;
	}
}