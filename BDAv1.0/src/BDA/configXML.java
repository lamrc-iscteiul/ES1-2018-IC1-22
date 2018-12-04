
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class configXML {
	@XmlElement
	private Email email;
	@XmlElement
	private Facebook facebook;
	@XmlElement
	private Twitter twitter;
	
	configXML(){
		email=new Email();
		facebook=new Facebook();
		twitter =new Twitter();
	};
	
	configXML(Email email, Facebook facebook, Twitter twitter){
		this.email=email;
		this.facebook=facebook;
		this.twitter=twitter;
	}
	
	public void setEmail(String email, String password) {
		this.email.setEmail(email);
		this.email.setPassword(password);
	}
	
	public void setTwitter(String username, String password, String consumerKey, String consumerKeySecret, String accessToken, String accessTokenSecret){
		this.twitter.setUsername(username);
		this.twitter.setPassword(password);
		this.twitter.setConsumerKey(consumerKey);
		this.twitter.setConsumerKeySecret(consumerKeySecret);
		this.twitter.setAccessToken(accessToken);
		this.twitter.setAccessTokenSecret(accessTokenSecret);
	}
	
	public void setFacebook(String username, String password, String accessToken) {
		this.facebook.setUsername(username);
		this.facebook.setPassword(password);
		this.facebook.setAccessToken(accessToken);
	}
	
	public Email getEmail() {
		return email;
	}
	
	public Facebook getFacebook() {
		return facebook;
	}
	
	public Twitter getTwitter() {
		return twitter;
	}
}
