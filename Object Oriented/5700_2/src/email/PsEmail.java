package email;

public class PsEmail extends EmailDecorator{

	public PsEmail(EmailDecorator email) {
		super(email.email);
	}
	
	@Override
	public String buildEmail(String s) {
		return email.buildEmail(s) + "\n\nPS: 	I may be drunk, Miss, but in the morning "
				+ "I will be sober and you will still be ugly. \n\t-Winston Churchill";
	}


	
}
