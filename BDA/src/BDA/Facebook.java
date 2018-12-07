package BDA;

import javax.xml.bind.annotation.XmlElement;

public class Facebook {
	private String accessToken;

	Facebook() {
	}

	Facebook(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * Getters and setters of the class attributes.
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Getters and setters of the class attributes.
	 */
	@XmlElement
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}