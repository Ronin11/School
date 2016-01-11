package observers;

import java.util.Observable;
import java.util.Observer;

import email.Email;
import race.*;

public class SupportObserver implements Observer{
	Racer racer;
	String emailAddress;
	
	public SupportObserver(String email){
		this.emailAddress = email;
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		racer = (Racer) o;
		Email email = new Email();
		email.sendEmail(emailAddress);
	}

}
