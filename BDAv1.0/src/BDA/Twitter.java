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
	
	 /**
		 *Getters and setters of the class attributes.
		 */
	public String getConsumerKey() {
		return ConsumerKey;
	}

	 /**
		 *Getters and setters of the class attributes.
		 */
	public void setConsumerKey(String consumerKey) {
		ConsumerKey = consumerKey;
	}
	 /**
		 *Getters and setters of the class attributes.
		 */
	public String getConsumerKeySecret() {
		return ConsumerKeySecret;
	}

	 /**
		 *Getters and setters of the class attributes.
		 */
	public void setConsumerKeySecret(String consumerKeySecret) {
		ConsumerKeySecret = consumerKeySecret;
	}
	 /**
		 *Getters and setters of the class attributes.
		 */
	public String getAccessToken() {
		return AccessToken;
	}
	 /**
		 *Getters and setters of the class attributes.
		 */
	
	public void setAccessToken(String accessToken) {
		AccessToken = accessToken;
	}
	 /**
		 *Getters and setters of the class attributes.
		 */
	public String getAccessTokenSecret() {
		return AccessTokenSecret;
	}

	 /**
		 *Getters and setters of the class attributes.
		 */
	public void setAccessTokenSecret(String accessTokenSecret) {
		AccessTokenSecret = accessTokenSecret;
	}
} 