package email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email implements EmailInterface{
	protected Email email;
	
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	
	String gmailUserName = "";
	String gmailPassword = "";
	
	public Email(){
		
	}
	
	@Override
	public String buildEmail(String message) {
		return null;
	}

	@Override
	//For this email to work, you must allow the gmail to sign in from less secure apps.
	public void sendEmail(String address)   {  
		try {
			mailServerProperties = System.getProperties();
			mailServerProperties.put("mail.smtp.port", "587");
			mailServerProperties.put("mail.smtp.auth", "true");
			mailServerProperties.put("mail.smtp.starttls.enable", "true");
			
			getMailSession = Session.getDefaultInstance(mailServerProperties, null);
			generateMailMessage = new MimeMessage(getMailSession);
			
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
			generateMailMessage.setSubject("Race Update");
			EmailDecorator decorator = new EmailDecorator(this);
			String emailBody = decorator.buildEmail("Your racer has been updated");
			generateMailMessage.setContent(emailBody, "text/html");
			
			Transport transport = getMailSession.getTransport("smtp");
	 
			// Enter your correct gmail UserID and Password
			// if you have 2FA enabled then provide App Specific Password
			transport.connect("smtp.gmail.com", gmailUserName, gmailPassword);
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
