package email;

public interface EmailInterface {
	String buildEmail(String message);
	void sendEmail(String address);
}
