package model.bean;

public class Bico {
	private Integer id;
	private Bomba bomba;
	private TanqueReparticao reparticao;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Bomba getBomba() {
		return bomba;
	}
	
	public void setBomba(Bomba bomba) {
		this.bomba = bomba;
	}
	
	public TanqueReparticao getReparticao() {
		return reparticao;
	}
	
	public void setReparticao(TanqueReparticao reparticao) {
		this.reparticao = reparticao;
	}
}