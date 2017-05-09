package model.bean;

public class Bomba {
	private Integer id;
	private Tanque tanque;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Tanque getTanque() {
		return tanque;
	}
	
	public void setTanque(Tanque tanque) {
		this.tanque = tanque;
	}
}