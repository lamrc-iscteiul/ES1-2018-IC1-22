package BDA;

import javax.xml.bind.annotation.XmlElement;

public class Twitter {
	private String ConsumerKey;
	private String ConsumerKeySecret;
	private String AccessToken;
	private String AccessTokenSecret;
	
	Twitter(){}
	Twitter(String ConsumerKey, String ConsumerKeySecret, String AccessToken, String AccessTokenSecret){
		this.ConsumerKey=ConsumerKey;
		this.ConsumerKeySecret=ConsumerKeySecret;
		this.AccessToken=AccessToken;
		this.AccessTokenSecret=AccessTokenSecret;
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