package BDA;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.FetchProfile;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.URLName;
import javax.mail.internet.MimeBodyPart;

import com.sun.mail.pop3.POP3Folder;
import com.sun.mail.pop3.POP3SSLStore;

public class EmailAPI {

	private Session session;
    private POP3SSLStore store;
    private String username;
    private String password;
    private POP3Folder folder;
    private ArrayList<GeneralMessage> messages = new ArrayList<GeneralMessage>();
    
    public static String numberOfFiles = null;
    public static int toCheck = 0;
    public static Writer output = null;
    URLName url;
    public static String receiving_attachments="C:\\download";

    public EmailAPI() throws Exception
    {
        session = null;
        store = null;	
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
    private void transformMessageToList() throws Exception{
    	 Message msgs[] = folder.getMessages();
         FetchProfile fp = new FetchProfile();
         folder.fetch(msgs, fp);
         int max = msgs.length;
         int max2 = 5;
         for(int i = 0; i < max2; i++){
        	 Message message = msgs[msgs.length - (i + 1)];
        	 GeneralMessage message2 = new GeneralMessage();
        	 message2.setBody(message.getContent().toString());
        	 message2.setSource(message.getFrom()[0].toString());
        	 message2.setSubject(message.getSubject());
        	 message2.setType(GeneralMessage.EMAIL);
         }
    }
    
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
            dumpEnvelope(msgs[i]);
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

    @SuppressWarnings("unused")
    private static void dumpEnvelope(Message m) throws Exception
    {
		String body="";
        String path="";
        int size=0;
        Object content = m.getContent();
        if(content instanceof String){
            body = (String)content;
        }
        else if(content instanceof Multipart)
        {
            Multipart mp = (Multipart)content;
            for (int j=0; j < mp.getCount(); j++)
            {
                Part part = mp.getBodyPart(j);
                String disposition = part.getDisposition();
                //System.out.println("test disposition---->>"+disposition);
                if (disposition == null) {
                    // Check if plain
                    MimeBodyPart mbp = (MimeBodyPart)part;
                    if (mbp.isMimeType("text/plain")) {
                        body += mbp.getContent().toString();
                    }
                    else if (mbp.isMimeType("TEXT/HTML")) {
                        body += mbp.getContent().toString();
                    }
                    else {
                        //unknown
                    }
                } else if ((disposition != null) &&
                        (disposition.equals(Part.ATTACHMENT) || disposition.equals
                        (Part.INLINE) || disposition.equals("ATTACHMENT") || disposition.equals("INLINE")) )
                {
                    // Check if plain
                    MimeBodyPart mbp = (MimeBodyPart)part;
                    if (mbp.isMimeType("text/plain")) {
                        body += (String)mbp.getContent();
                    }
                    else if (mbp.isMimeType("TEXT/HTML")) {
                        body += mbp.getContent().toString();
                    }
                    else {
                        File savedir = new File(receiving_attachments);
                        savedir.mkdirs();
                        File savefile = new File(savedir+"\\"+part.getFileName());
                        path = savefile.getAbsolutePath();
                        size = saveFile( savefile, part);

                    }
                }
            }
        }

    }
}
