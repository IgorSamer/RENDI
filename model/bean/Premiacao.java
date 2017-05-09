package model.bean;

public class Premiacao {
	private Integer id;
	private String data;
	private Fidelidade fidelidade;
	private Premio premio;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public Fidelidade getFidelidade() {
		return fidelidade;
	}
	
	public void setFidelidade(Fidelidade fidelidade) {
		this.fidelidade = fidelidade;
	}
	
	public Premio getPremio() {
		return premio;
	}
	
	public void setPremio(Premio premio) {
		this.premio = premio;
	}
}