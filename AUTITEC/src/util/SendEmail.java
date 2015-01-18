package util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class SendEmail {
	
	private String server;
	private String email;
	private String password;
	
	private HtmlEmail htmlEmail;
	
	public SendEmail() {
		
		Properties properties = new Properties();
		server = properties.getPropertie("EmailServer");
		email = properties.getPropertie("EmailAccount");
		password = properties.getPropertie("EmailPassword");
		
		htmlEmail = new HtmlEmail();
		htmlEmail.setHostName(server);
		htmlEmail.setSmtpPort(587);
		htmlEmail.setAuthentication(email, password);
		htmlEmail.setStartTLSEnabled(true);
	
	}
	
	public void send(String subject, String message) {
		
		try {
			
			htmlEmail.addTo(email);
			htmlEmail.setFrom(email);
			htmlEmail.setSubject(subject);
			htmlEmail.setHtmlMsg(message);
			
			htmlEmail.send();
			
			
		} catch (EmailException e) {
			ShowMessage.errorMessage(null, "Falha ao enviar o email", "Ocorreu uma falha ao enviar o email.\nPor favor consulte o suporte");
			e.printStackTrace();
		}
		
	}

}
