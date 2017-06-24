package model.bean;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	private final String email = "rendiposto@gmail.com";
	private final String senha = "huehuehue";
	private String para;
	private String assunto;
	private String mensagem;

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public void enviar() {
		Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
        	new javax.mail.Authenticator() {
        		protected PasswordAuthentication getPasswordAuthentication() {
        			return new PasswordAuthentication(email, senha);
        		}
        	}
        );
        
        try {
        	Message message = new MimeMessage(session);
        	message.setFrom(new InternetAddress(this.email));
        	
        	Address[] toUser = InternetAddress.parse(this.getPara());  

        	message.setRecipients(Message.RecipientType.TO, toUser);
        	message.setSubject(this.getAssunto());
        	message.setContent(this.getMensagem(), "text/html; charset=utf-8");
        	
        	Transport.send(message);
        } catch (MessagingException e) {
        	throw new RuntimeException(e);
        }
	}
}