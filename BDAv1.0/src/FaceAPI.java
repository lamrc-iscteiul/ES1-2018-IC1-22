import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Version;
import com.restfb.types.*;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class FaceAPI {
	
	public static ArrayList<GeneralMessage> getList() {
		// TODO Auto-generated method stub
		configXML configXML = null;
		
		try {
			//lê ficheiro XML
			File file = new File("config.xml");
	        JAXBContext jaxbContext = JAXBContext.newInstance(configXML.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        configXML = (configXML) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String accessToken = configXML.getFacebook().getAccessToken();
		
		FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_11);
		
		@SuppressWarnings("unused")
		User me = fbClient.fetchObject("me", User.class);
		
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		ArrayList<GeneralMessage> list = new ArrayList<GeneralMessage>();
		for (List<Post> page : result) {
			for (Post aPost : page) {
			GeneralMessage msg = new GeneralMessage(GeneralMessage.FACEBOOK, aPost);
				if(aPost.getMessage() != null){
//					msg.setBody(aPost.getMessage());
//					msg.setData(aPost.getCreatedTime().toString());
//					msg.setSource("@João Pimenta");
//					msg.setType(GeneralMessage.FACEBOOK);
					list.add(msg);
				}
			}
			
		}
		System.out.println("termieni");
		return list;
	}
	
//	TODO Create the toString function for the post to show in the GUI
	public static String postToString(Post post) {
		String s = "";
		return s;
	}
}