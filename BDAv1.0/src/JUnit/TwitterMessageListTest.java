package JUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import BDA.GeneralMessage;
import BDA.TwitterAPI;

/**
 * testa a criacao da lista de tweets 

 * @author Adil
 * 
 */
//
public class TwitterMessageListTest {

	@Test
	public void test() {
		TwitterAPI test= new TwitterAPI();
		ArrayList<GeneralMessage> output= test.getList("ISCTEIUL");
		assertEquals(false,output.isEmpty());
	}

}
