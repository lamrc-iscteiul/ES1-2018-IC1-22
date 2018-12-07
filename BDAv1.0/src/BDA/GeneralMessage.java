package BDA;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;

import com.restfb.types.Post;

import twitter4j.Status;

public class GeneralMessage implements Comparable<GeneralMessage>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int TWITTER = 0;
	public final static int EMAIL = 1;
	public final static int FACEBOOK = 2;

	private int type;
	private Status twitter_status;
	private Message mail_message;
	private Post facebook_post;
	private boolean selected = false;
	private Date data;

	/**
	 * Creates a General Message Object and saves the type of object and the
	 * object creation Data.
	 */

	public GeneralMessage(int type, Object obj, Date data) {
		this.type = type;
		this.data = data;
		switch (type) {

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

	/**
	 * Getters of the class attributes.
	 */
	public int getType() {
		return type;
	}

	/**
	 * Getters and setters of the class attributes.
	 */
	public Message getMessage() {
		return mail_message;
	}

	/**
	 * Getters and setters of the class attributes.
	 */
	public Status getStatus() {
		return twitter_status;
	}

	/**
	 * Getters and setters of the class attributes.
	 */
	public Post getPost() {
		return facebook_post;
	}

	/**
	 * Getters and setters of the class attributes.
	 */
	public Date getDate() {
		return this.data;
	}

	/**
	 * Creates a String with the basic information of the Objectj.
	 */

	@Override
	public String toString() {
		String s = "";

		switch (type) {

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

	/**
	 * String comparator to sort the list of object, based on the alphabetical
	 * order.
	 */

	public static Comparator<GeneralMessage> ComparadorString = new Comparator<GeneralMessage>() {
		@Override
		public int compare(GeneralMessage o1, GeneralMessage o2) {

			return o1.toString().compareToIgnoreCase(o2.toString());
		}
	};

	/**
	 * Date comparator to sort the list of object, based on the Date.
	 */

	@Override
	public int compareTo(GeneralMessage arg0) {

		return this.getDate().compareTo(arg0.getDate());
	}

	public Date getDate2() {

		switch (type) {

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