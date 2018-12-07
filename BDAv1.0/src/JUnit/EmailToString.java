package JUnit;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.junit.Test;

import BDA.EmailAPI;

public class EmailToString {

	@Test
	public void test() throws MessagingException, IOException {
		EmailAPI e= new EmailAPI();
		Message msgs[] = e.getFolder().getMessages();
		@SuppressWarnings("static-access")
		String output=e.messageToString(msgs[0]);
		assertEquals(false,output.isEmpty());
	}

}
