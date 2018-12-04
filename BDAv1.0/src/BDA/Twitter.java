
import javax.xml.bind.annotation.XmlElement;

public class Twitter {
	private String username;
	private String password;
	private String ConsumerKey;
	private String ConsumerKeySecret;
	private String AccessToken;
	private String AccessTokenSecret;
	
	Twitter(){}
	Twitter(String username, String password, String ConsumerKey, String ConsumerKeySecret, String AccessToken, String AccessTokenSecret){
		this.username=username;
		this.password=password;
		this.ConsumerKey=ConsumerKey;
		this.ConsumerKeySecret=ConsumerKeySecret;
		this.AccessToken=AccessToken;
		this.AccessTokenSecret=AccessTokenSecret;
	}
	
    public String getUsername() {
        return username;
    }

    @XmlElement
   
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement(name="twitterpass")
    public void setPassword(String password) {
        this.password = password;
    }

	public String getConsumerKey() {
		return ConsumerKey;
	}

	
	public void setConsumerKey(String consumerKey) {
		ConsumerKey = consumerKey;
	}

	public String getConsumerKeySecret() {
		return ConsumerKeySecret;
	}

	
	public void setConsumerKeySecret(String consumerKeySecret) {
		ConsumerKeySecret = consumerKeySecret;
	}

	public String getAccessToken() {
		return AccessToken;
	}

	
	public void setAccessToken(String accessToken) {
		AccessToken = accessToken;
	}

	public String getAccessTokenSecret() {
		return AccessTokenSecret;
	}

	
	public void setAccessTokenSecret(String accessTokenSecret) {
		AccessTokenSecret = accessTokenSecret;
	}
} 