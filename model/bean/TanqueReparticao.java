package model.bean;

public class TanqueReparticao {
	private Integer id;
	private Combustivel combustivel;
	private Tanque tanque;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Combustivel getCombustivel() {
		return combustivel;
	}
	
	public void setCombustivel(Combustivel combustivel) {
		this.combustivel = combustivel;
	}
	
	public Tanque getTanque() {
		return tanque;
	}
	
	public void setTanque(Tanque tanque) {
		this.tanque = tanque;
	}
}