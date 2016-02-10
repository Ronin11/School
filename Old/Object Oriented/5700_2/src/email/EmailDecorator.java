package email;

public class EmailDecorator implements EmailInterface{
	protected Email email;
	private EmailDecorator composite;
	
	public EmailDecorator(Email email){
		this.email = email;
		HeaderEmail header = new HeaderEmail(this);
		FooterEmail footer = new FooterEmail(header);
		PsEmail ps = new PsEmail(footer);
		composite = ps;
	}

	public String buildEmail(String message) {
		return composite.buildEmail(message);
	}
	
	@Override
	public void sendEmail(String address) {
		// TODO Auto-generated method stub
		
	}

}
