package JUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import BDA.Configuracoes;

public class ConfigTest {

	@Test
	public void test() {
		Configuracoes config= new Configuracoes();
		assertEquals(false,config.getText_email_user().isEmpty());
	}

}
