package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import BDA.EmailAPI;

public class EmailUserPasswordTest {

	/**
	 * Testa o email e a password
	
	 * @author Adil
	 * 
	 */
	//
	
	@Test
	public void test() throws Exception {
		EmailAPI test= new EmailAPI();
		test.setUserPass("Adil", "password");
		assertEquals("Adil",test.getUsername());
		assertEquals("password",test.getPassword());
	}

}
