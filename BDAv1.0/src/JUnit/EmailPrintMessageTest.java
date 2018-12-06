package JUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import BDA.EmailAPI;

public class EmailPrintMessageTest {

	@Test
	public void test() throws Exception {
		EmailAPI e=new EmailAPI();
		e.printAllMessages();
	}

}
