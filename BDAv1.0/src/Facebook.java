
import javax.xml.bind.annotation.XmlElement;


public class Facebook {
	private String username;
	private String password;
	private String accessToken;
	
	Facebook(){}
	Facebook(String username, String password, String accessToken){
		this.username=username;
		this.password=password;
		this.accessToken=accessToken;
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

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }


	public String getAccessToken() {
		return accessToken;
	}

	@XmlElement
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
} 