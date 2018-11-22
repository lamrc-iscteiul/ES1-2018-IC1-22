package Testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import BDA.TwitterAPI;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * testa a função que devolve um dado tweet

 * @author Adil
 * 
 */
//

public class TwitterGetTweettest {

	@Test
	public void test() throws TwitterException {
		TwitterAPI test= new TwitterAPI();
		List<Status> output1= test.getTimeline("ISCTEIUL");
		assertEquals(false,test.getTweet(output1.get(1)).isEmpty()  );
	}

}
