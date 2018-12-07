package JUnit;



import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;

import BDA.EmailAPI;

public class EmailSend {

	@Test
	public void test() throws AddressException, MessagingException {
		EmailAPI e=new EmailAPI();
		e.enviarMail("smtp@outlook.com", "tu@gmail.com", "teste", "ES");
	}

}
