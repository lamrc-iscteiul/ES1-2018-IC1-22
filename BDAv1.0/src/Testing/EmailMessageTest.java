package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import BDA.EmailAPI;

public class EmailMessageTest {

	
	/**
	 * Erro acontece pois o file tem que ser aberto antes de se poder usar a função transformToMessage(), desta forma surge um erro de null pointer exception
	
	 * @author Adil
	 * 
	 */
	//
	@Test
	public void test() throws Exception {
		EmailAPI test= new EmailAPI();
		test.transformMessageToList();
		assertEquals(false,test.getMessages().isEmpty());
		
	}

}
