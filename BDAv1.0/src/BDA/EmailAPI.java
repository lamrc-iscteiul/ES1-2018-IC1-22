package BDA;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.sun.mail.pop3.POP3Folder;
import com.sun.mail.pop3.POP3SSLStore;

public class EmailAPI {

	private Session session;
    private POP3SSLStore store;
    private static String username;
    private static String password;
    private POP3Folder folder;
    private ArrayList<GeneralMessage> messages = new ArrayList<GeneralMessage>();
    private Configuracoes config;
    
    public static String numberOfFiles = null;
    public static int toCheck = 0;
    public static Writer output = null;
    URLName url;
    public static String receiving_attachments="C:\\download";

    public EmailAPI()
    {
    	try {
	        session = null;
	        store = null;
	        
	        File file = new File("config.xml");
	        JAXBContext jaxbContext = JAXBContext.newInstance(configXML.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        configXML configXML = (configXML) unmarshaller.unmarshal(file);
			
	        setUserPass(configXML.getEmail().getEmail(), configXML.getEmail().getPassword());
	        connect();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    /**Sets user's username and password for e-mail login
     * 
     * @param username
     * @param password
     */
    public void setUserPass(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    /**Connects to the e-mail server
     * 
     * @throws Exception
     */
    public void connect()
    throws Exception
    {
        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties pop3Props = new Properties();
        pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
        pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false");
        pop3Props.setProperty("mail.pop3.port", "995");
        pop3Props.setProperty("mail.pop3.socketFactory.port", "995");
        url = new URLName("pop3", "pop.outlook.com", 995, "", username, password);
        session = Session.getInstance(pop3Props, null);
        store = new POP3SSLStore(session, url);
        store.connect();
        openFolder("INBOX");
        transformMessageToList();
    }

    public void openFolder(String folderName)
    throws Exception
    {
        folder = (POP3Folder)store.getFolder(folderName);
        System.out.println((new StringBuilder("For test----")).append(folder.getParent().getFullName()).toString());
        if(folder == null)
            throw new Exception("Invalid folder");
        try
        {
            folder.open(2);
            System.out.println((new StringBuilder("Folder name----")).append(folder.getFullName()).toString());
        }
        catch(Exception ex)
        {
            System.out.println((new StringBuilder("Folder Opening Exception..")).append(ex).toString());
        }
    }
    
    @SuppressWarnings("unused")
    public void transformMessageToList() throws Exception{
    	 folder.getMessages();
    	 Message msgs[] = folder.getMessages();
         FetchProfile fp = new FetchProfile();
         folder.fetch(msgs, fp);
         int max = msgs.length;
         int max2 = 5;
         for(int i = 0; i < max2; i++){
        	 Message message = msgs[msgs.length - (i + 1)];
        	 GeneralMessage general_message = new GeneralMessage(GeneralMessage.EMAIL, message,message.getSentDate());
        	 messages.add(general_message);
         }
    }
    
    public ArrayList<GeneralMessage> getMessages() {
    	return messages;
    }
    
    public POP3Folder getFolder() {
		return folder;
	}

//	public ArrayList<GeneralMessage> getList() {
//    	EmailAPI mail;
//    	ArrayList<GeneralMessage> list = new ArrayList<GeneralMessage>();
//		try {
//			
//			//lê ficheiro XML
//			File file = new File("config.xml");
//	        JAXBContext jaxbContext = JAXBContext.newInstance(configXML.class);
//	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//	        configXML configXML = (configXML) unmarshaller.unmarshal(file);
//			
//			mail = new EmailAPI();
//	        mail.setUserPass(configXML.getEmail().getEmail(), configXML.getEmail().getPassword());
//	        mail.connect();
//	        mail.openFolder("INBOX");
//	        mail.transformMessageToList();
//	        list = mail.getMessages();
//	        mail.disconnect();
//	        mail.finalize();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} catch (Throwable t) {
//			t.printStackTrace();
//		}
//		return list;
//    }
    
    public ArrayList<GeneralMessage> getList() {
		return messages;
    }

    public void closeFolder()
    throws Exception
    {
        folder.close(false);
    }

    public int getMessageCount()
    throws Exception
    {
        return folder.getMessageCount();
    }

    public int getNewMessageCount()
    throws Exception
    {
        return folder.getNewMessageCount();
    }

    public void disconnect()
    throws Exception
    {
        store.close();
    }

    @SuppressWarnings("unused")
    public void printAllMessages()
    throws Exception
    {
        Message msgs[] = folder.getMessages();
        FetchProfile fp = new FetchProfile();
        folder.fetch(msgs, fp);
        int max = msgs.length;
        int max2 = 5;
        for(int i = 0; i < max2; i++){
            Message message = msgs[msgs.length - (i + 1)];
            messageToString(msgs[i]);
            System.out.println("==============================");
            System.out.println("Email #" + (i + 1));
            System.out.println("Subject: " + message.getSubject());
            System.out.println("From: " + message.getFrom()[0]);
            System.out.println("Text: " + message.getContent().toString());
        }
    }

    public static int saveFile(File saveFile, Part part) throws Exception {

        BufferedOutputStream bos = new BufferedOutputStream( new FileOutputStream(saveFile) );

        byte[] buff = new byte[2048];
        InputStream is = part.getInputStream();
        int ret = 0, count = 0;
        while( (ret = is.read(buff)) > 0 ){
            bos.write(buff, 0, ret);
            count += ret;
        }
        bos.close();
        is.close();
        return count;
    }
    
    public static String messageToString(Message message) throws MessagingException, IOException {
        String result = "";
        try {
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        }catch (Exception e) {
        	e.printStackTrace();
        }
        return result;
    }

    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart)  throws MessagingException, IOException{
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
            } else if (bodyPart.getContent() instanceof MimeMultipart){
                result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
            }
        }
        return result;
    }

//    @SuppressWarnings("unused")
//    private static void dumpEnvelope(Message m) throws Exception
//    {
//		String body="";
//        String path="";
//        int size=0;
//        Object content = m.getContent();
//        if(content instanceof String){
//            body = (String)content;
//        }
//        else if(content instanceof Multipart)
//        {
//            Multipart mp = (Multipart)content;
//            for (int j=0; j < mp.getCount(); j++)
//            {
//                Part part = mp.getBodyPart(j);
//                String disposition = part.getDisposition();
//                //System.out.println("test disposition---->>"+disposition);
//                if (disposition == null) {
//                    // Check if plain
//                    MimeBodyPart mbp = (MimeBodyPart)part;
//                    if (mbp.isMimeType("text/plain")) {
//                        body += mbp.getContent().toString();
//                    }
//                    else if (mbp.isMimeType("TEXT/HTML")) {
//                        body += mbp.getContent().toString();
//                    }
//                    else {
//                        //unknown
//                    }
//                } else if ((disposition != null) &&
//                        (disposition.equals(Part.ATTACHMENT) || disposition.equals
//                        (Part.INLINE) || disposition.equals("ATTACHMENT") || disposition.equals("INLINE")) )
//                {
//                    // Check if plain
//                    MimeBodyPart mbp = (MimeBodyPart)part;
//                    if (mbp.isMimeType("text/plain")) {
//                        body += (String)mbp.getContent();
//                    }
//                    else if (mbp.isMimeType("TEXT/HTML")) {
//                        body += mbp.getContent().toString();
//                    }
//                    else {
//                        File savedir = new File(receiving_attachments);
//                        savedir.mkdirs();
//                        File savefile = new File(savedir+"\\"+part.getFileName());
//                        path = savefile.getAbsolutePath();
//                        size = saveFile( savefile, part);
//
//                    }
//                }
//            }
//        }
//
//    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static void enviarMail(String de, String destino, String assunto, String texto) throws AddressException, MessagingException {
		Properties props = null;
		props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.outlook.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.pwd", password);
        
        Session session2 = Session.getDefaultInstance(props,
        	    new Authenticator() {
        	        protected PasswordAuthentication  getPasswordAuthentication() {
        	        return new PasswordAuthentication(
        	                    username, password);
        	                }
        	    });
     //   session2.setDebug(true);
		
		MimeMessage message = new MimeMessage(session2);
		message.setFrom(new InternetAddress(de));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
		message.setSubject(assunto);
		message.setText(texto);
		Transport transport = session2.getTransport("smtp");
	    transport.connect("smtp.outlook.com", 587, username, password);
		transport.send(message);
	}
}
