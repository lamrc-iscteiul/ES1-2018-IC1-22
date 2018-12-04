package BDA;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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
				GeneralMessage msg = new GeneralMessage(GeneralMessage.TWITTER, status);
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
		
		configXML configXML = null;
		//lê ficheiro XML
		try {
			File file = new File("config.xml");
	        JAXBContext jaxbContext = JAXBContext.newInstance(configXML.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        configXML = (configXML) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Paging page = new Paging(1,TWEET_NUMBER);
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setDebugEnabled(true)
    	  .setOAuthConsumerKey(configXML.getTwitter().getConsumerKey())
    	  .setOAuthConsumerSecret(configXML.getTwitter().getConsumerKeySecret())
    	  .setOAuthAccessToken(configXML.getTwitter().getAccessToken())
    	  .setOAuthAccessTokenSecret(configXML.getTwitter().getAccessTokenSecret());
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
	public static String statusToString(Status status) {
		String result = "";
		
		result += "@"+ status.getUser().getName() + ": " + status.getText() + "\n" +
  			  "Retweets: " + status.getRetweetCount() + "         Favorites: " + status.getFavoriteCount();
		return result;
	}
}
