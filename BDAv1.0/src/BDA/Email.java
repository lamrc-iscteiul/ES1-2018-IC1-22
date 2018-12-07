package BDA;

import javax.xml.bind.annotation.XmlElement;

public class Email {
   private String email;
   private String password;
	/**
	 * Email constructor
	 */
    Email(){}
    /**
     * Email constructor
     * @param email String
     * @param password String
     */
    Email(String email, String password){
    	this.email=email;
    	this.password=password;
    }

   /**
    * Returns email 
    * @return String
    */
   public String getEmail() {
        return email;
    }
   /**
    * Sets email
    * @param email string
    */
   @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }
/**
 * Returns password
 * @return password String
 */
    public String getPassword() {
        return password;
    }
 /**
  * Sets password
  * @param password String
  */
    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

} 