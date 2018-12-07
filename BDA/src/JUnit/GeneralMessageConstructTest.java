package JUnit;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.restfb.types.Post;
import com.sun.mail.iap.ParsingException;
import com.sun.mail.imap.protocol.Status;

import BDA.GeneralMessage;

public class GeneralMessageConstructTest {

	@Test
	public void test() {
		GeneralMessage gm= new GeneralMessage(2,new Post(),new Date());
		assertEquals(2, gm.getType());
	}
	
	@Test
	public void test2() throws ParsingException {
		GeneralMessage gm= new GeneralMessage(2,new Status(null),new Date());
		assertEquals(2, gm.getType());
	}


	@Test
	public void test3() {
		Post output= new Post();
		GeneralMessage gm= new GeneralMessage(2,output,new Date());
		assertEquals(output, gm.getPost());
	}
	

}
