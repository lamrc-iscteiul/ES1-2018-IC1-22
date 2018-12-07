package JUnit;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.restfb.types.Post;

import BDA.GeneralMessage;
import BDA.TwitterAPI;

import twitter4j.TwitterException;

public class GeneralMessageToStringTest {

	@Test
	public void test() {
		GeneralMessage gm= new GeneralMessage(5,new Post(),new Date());
		String output=gm.toString();
		assertEquals(false,output.isEmpty());
	}
	
	@Test
	public void test1() {
		GeneralMessage gm= new GeneralMessage(1,new Object(),new Date());
		String output=gm.toString();
		assertEquals(false,output.isEmpty());
	}
	
	@Test
	public void test2() {
		GeneralMessage gm= new GeneralMessage(2,new Object(),new Date());
		String output=gm.toString();
		assertEquals(false,output.isEmpty());
	}
	
	@Test
	public void test3() throws TwitterException {
		TwitterAPI ta=new TwitterAPI();
		
		GeneralMessage gm= new GeneralMessage(0,ta.getTimeline("ISCTEIUL").get(1),new Date());
		String output=gm.toString();
		assertEquals(false,output.isEmpty());
	}
}
