package BDA;
import java.util.Comparator;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;

import com.restfb.types.Post;

import twitter4j.Status;

public class GeneralMessage implements Comparable<GeneralMessage>{

	public final static int TWITTER  = 0;
	public final static int EMAIL    = 1;
	public final static int FACEBOOK = 2;
	
	private int type;
	private Status twitter_status;
	private Message mail_message;
	private Post facebook_post;
	private Date data;
	
	public GeneralMessage(int type, Object obj, Date data) {
		this.type = type;
		this.data=data;
		switch(type) {
		
		case GeneralMessage.TWITTER:
			twitter_status = (Status) obj;
			break;
		
		case GeneralMessage.EMAIL:
			mail_message = (Message) obj;
			break;
			
		case GeneralMessage.FACEBOOK:
			facebook_post = (Post) obj;
			break;
			
		default:
			break;
		}
	}
	
	public int getType() {
		return type;
	}
	
	public Message getMessage() {
		return mail_message;
	}

	public Status getStatus() {
		return twitter_status;
	}

	public Post getPost() {
		return facebook_post;
	}
	
	@Override
	public String toString() {
		String s = "";
		
		switch(type) {
		
		case GeneralMessage.TWITTER:
			s = "@" + twitter_status.getUser().getName() + ": " + twitter_status.getText();
			break;
		
		case GeneralMessage.EMAIL:
			try {
				s = mail_message.getFrom()[0].toString();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case GeneralMessage.FACEBOOK:
			s = facebook_post.getMessage();
			break;
			
		default:
			s = "Error: Type not supported!";
			break;
		}
		
		return s;
	}
	
	public static Comparator<GeneralMessage> ComparadorString = new Comparator<GeneralMessage>(){
	    @Override
	    public int compare(GeneralMessage o1, GeneralMessage o2) {

	    	return o1.toString().compareToIgnoreCase(o2.toString());
	    }
	};
	
	

	@Override
	public int compareTo(GeneralMessage arg0) {
		
		return this.getDate().compareTo(arg0.getDate());
	}
	
	public  Date getDate(){
		return this.data;
	}
	
	
}