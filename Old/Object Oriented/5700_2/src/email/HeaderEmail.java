package email;

public class HeaderEmail extends EmailDecorator{

	public HeaderEmail(EmailDecorator email) {
		super(email.email);
	}

	@Override
	public String buildEmail(String s) {
		return "Hi \n\n" + email.buildEmail(s);
		
	}

}
