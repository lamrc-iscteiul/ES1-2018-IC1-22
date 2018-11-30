

import java.util.ArrayList;
import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterAPI {
	
	private final static int TWEET_NUMBER = 100;
	
	/**Gets the List containing objects of the type GeneralMessage
	 * 
	 * @param user
	 * @return
	 */
	public static ArrayList<GeneralMessage> getList(String user) {
		ArrayList<GeneralMessage> list = new ArrayList<GeneralMessage>();
		try {
			List<Status> statuses = getTimeline(user);
			for(Status status : statuses) {
				GeneralMessage msg = new GeneralMessage();
				msg.setBody(status.getText());
				msg.setSource("@" + status.getUser().getName());
				msg.setSubject("");
				msg.setType(GeneralMessage.TWITTER);
				list.add(msg);
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/** Gets a user's tweets
	 * 
	 * @param user Desired user's usernam
	 * @returns List of the user's statuses
	 */
	public static List<Status> getTimeline(String user) throws TwitterException {
		
		Paging page = new Paging(1,TWEET_NUMBER);
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setDebugEnabled(true)
    	  .setOAuthConsumerKey("W1f0VvgWPfT8OBqVxvy4Mw")
    	  .setOAuthConsumerSecret("zKH2yAtRyefwsgOO8h8Szc4kru68iEm95QmIG7svw")
    	  .setOAuthAccessToken("36481851-VhzByC4f9MSsZES1QZQ4e4iBvA9bWGLyv9HKFpy7c")
    	  .setOAuthAccessTokenSecret("OahDuXF2Lhl5xlNYALhYZir6xSflAxKP9Zh89T05po");
    	TwitterFactory tf = new TwitterFactory(cb.build());
    	
    	Twitter twitter = tf.getInstance();
    	
    	List<Status> statuses = twitter.getUserTimeline(user, page);
    	
    	return statuses;
	}
	
	/** Prints a list of tweets on the console
	 * 
	 * @param statuses The list of statuses to print
	 */
	public static void printTimelineConsole(List<Status> statuses) {
		System.out.println("------------------------\n Showing home timeline \n------------------------");
 		int counter=1;
        for (Status status : statuses) {
			System.out.println("@"+ status.getUser().getName() + ": " + status.getText() + "\n" +
							   "Retweets: " + status.getRetweetCount() + "         Favorites: " + status.getFavoriteCount() + "\n" +
							   "---------------Tweet Nº " + counter + "---------------\n");	
			counter++;
         }
	}
	
	/**Gets tweets in a String
	 * 
	 * @param statuses The list of statuses to get as strings
	 * @returns The tweets printed in a String
	 */
	public static String getTimelineText(List<Status> statuses) {
		String result = "";
		result += "------------------------\n Showing user's timeline \n------------------------" + "\n";
 		int counter=1;
        for (Status status : statuses) {
//        	pesquisa.add(getTweet(status));
        	result += "@"+ status.getUser().getName() + ": " + status.getText() + "\n" +
        			  "Retweets: " + status.getRetweetCount() + "         Favorites: " + status.getFavoriteCount() + "\n" +
        			  "---------------Tweet Nº " + counter + "---------------\n";
        	counter++;
        }
        
        return result;
	}
	
	/**Gets a tweet as a String
	 * 
	 * @param status Tweet to print
	 * @return Tweet as a String
	 */
	public static String getTweet(Status status) {
		String result = "";
		
		result += "@"+ status.getUser().getName() + ": " + status.getText() + "\n" +
  			  "Retweets: " + status.getRetweetCount() + "         Favorites: " + status.getFavoriteCount();
		return result;
	}
}
