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
	private boolean selected = false;
	
	public GeneralMessage(int type, Object obj) {
		this.type = type;
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

	    	return o1.toString().compareTo(o2.toString());
	    }
	};
	
	public static Comparator<GeneralMessage> ComparadorDate = new Comparator<GeneralMessage>(){
	    @Override
	    public int compare(GeneralMessage o1, GeneralMessage o2) {

	    	int a = o1.getType();
	    	int b = o2.getType();
	    	Date d1=null;
	    	Date d2=null;
	    	
	    	switch(a) {
	    	case GeneralMessage.EMAIL:
	    		try {
					d1 = o1.getMessage().getSentDate();
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		break;
	    	case GeneralMessage.FACEBOOK:
	    		d1 = o1.getPost().getCreatedTime();
	    		break;
	    	case GeneralMessage.TWITTER:
	    		d1 = o1.getStatus().getCreatedAt();
	    		break;
	    	}
	    	
	    	switch(b) {
	    	case GeneralMessage.EMAIL:
	    		try {
					d2 = o1.getMessage().getSentDate();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		break;
	    	case GeneralMessage.FACEBOOK:
	    		d2 = o1.getPost().getCreatedTime();
	    		System.out.println(d2);
	    		break;
	    	case GeneralMessage.TWITTER:
	    		d2 = o1.getStatus().getCreatedAt();
	    		break;
	    	}
	    	
	    	
	    	return d1.compareTo(d2);
	    }
	};

	@Override
	public int compareTo(GeneralMessage arg0) {
		
		return this.getType()-arg0.getType();
	}

	public Date getDate() {
		
		switch(type) {
		
		case TWITTER:
			return twitter_status.getCreatedAt();
			
		case EMAIL:
			try {
				return mail_message.getSentDate();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case FACEBOOK:
			return facebook_post.getCreatedTime();
		}
		
		return new Date();
	}
	
	public void setSelected() {
		this.selected = true;
	}
	
	public void deselect() {
		this.selected = false;
	}
	
	public boolean getSelected() {
		return selected;
	}
}