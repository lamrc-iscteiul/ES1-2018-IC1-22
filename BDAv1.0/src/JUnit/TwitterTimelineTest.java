package JUnit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import BDA.TwitterAPI;
import twitter4j.Status;
import twitter4j.TwitterException;

public class TwitterTimelineTest {

	@Test
	public void test() throws TwitterException {
		TwitterAPI tt= new TwitterAPI();
		List<Status> statuses = tt.getTimeline("@22iscte");
		tt.retweet(statuses.get(1));
		tt.favorite(statuses.get(1));
		String output=tt.getTimelineText(statuses);
		tt.tweetar("ola");
		tt.reply(statuses.get(1), "there are only 2 genders");
		assertEquals(false, output.isEmpty());
	}

}
