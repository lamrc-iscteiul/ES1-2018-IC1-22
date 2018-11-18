package Twitter;

import java.util.List;
import twitter4j.Status;
import twitter4j.TwitterException;

public class TwitterMain {

	public static void main(String[] args) {
		
		try {
			List<Status> status_iscte = TwitterAPI.getTimeline("ISCTEIUL");
			List<Status> status_biblio = TwitterAPI.getTimeline("BibliotecaISCTE");
			
			System.out.println(TwitterAPI.getTimelineText(status_iscte));
			
		} catch(TwitterException e) {
			e.printStackTrace();
		}
		
		
    	
	}

}
