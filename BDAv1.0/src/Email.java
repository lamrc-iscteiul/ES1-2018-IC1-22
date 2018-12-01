
import javax.xml.bind.annotation.XmlElement;

public class Email {
   private String email;
   private String password;
	
    Email(){}
    Email(String email, String password){
    	this.email=email;
    	this.password=password;
    }

   public String getEmail() {
        return email;
    }
   @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

} 