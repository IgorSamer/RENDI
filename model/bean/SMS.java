package model.bean;

import br.com.facilitamovel.bean.SmsSimples;
import br.com.facilitamovel.service.SendMessage;

public class SMS {
	private String usuario = "rendi";
	private String senha = "rendi321";
	private String destinatario;
	private String mensagem;

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public void enviar() {
		SmsSimples sms = new SmsSimples();
		sms.setUser(this.usuario);
		sms.setPassword(this.senha);
		sms.setDestinatario(this.getDestinatario());
		sms.setMessage(this.getMensagem());
		
		try {
			SendMessage.simpleSend(sms);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}