package BDA;

import javax.xml.bind.annotation.XmlElement;


public class Facebook {
	private String accessToken;
	
	Facebook(){}
	Facebook(String accessToken){
		this.accessToken=accessToken;
	}


	public String getAccessToken() {
		return accessToken;
	}

	@XmlElement
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
} 