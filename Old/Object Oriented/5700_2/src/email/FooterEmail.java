package email;

public class FooterEmail extends EmailDecorator {

	public FooterEmail(EmailDecorator email) {
		super(email.email);
	}
	
	@Override
	public String buildEmail(String s) {
		return email.buildEmail(s) + "\nFOOTER\n";
		
	}

}
