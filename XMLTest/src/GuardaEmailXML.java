

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class GuardaEmailXML {
    public static void main(String[] args) {

      Email email = new Email();
      email.setPassword(args[2]);
      email.setEmail(args[1]);
      email.setToken(args[0]);

      try {

        File file = new File("config.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Email.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(email, file);
        jaxbMarshaller.marshal(email, System.out);

          } catch (JAXBException e) {
        e.printStackTrace();
          }

    }
    
}