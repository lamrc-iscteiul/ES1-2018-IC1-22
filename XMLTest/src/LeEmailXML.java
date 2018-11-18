import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class LeEmailXML {
		 public static void main(String[] args) {
try {
	    	Email email;
	        File file = new File("config.xml");
	        JAXBContext jaxbContext = JAXBContext.newInstance(Email.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        email = (Email) unmarshaller.unmarshal(file);
	        System.out.println(email.email);
	        System.out.println(email.password);
	        System.out.println(email.token);
	
		 } catch (JAXBException e) {
		        e.printStackTrace();
         }
}
}
