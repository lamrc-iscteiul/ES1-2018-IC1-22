package BDA;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * testa a lista de Status

 * @author Adil
 * 
 */
//
public class TwitterStatusesTest {

	@Test
	public void test() throws TwitterException {
		TwitterAPI test= new TwitterAPI();
		List<Status> output= test.getTimeline("ISCTEIUL");
		assertEquals(false,output.isEmpty());

	}
}
