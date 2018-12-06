package BDA;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.*;
import com.restfb.types.Likes.LikeItem;
import com.restfb.types.Payment.Action;

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
	
	FacebookClient fbClient;
	
	final String groupID ="me/feed";
	public  ArrayList<GeneralMessage> getList() {
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
		
		// fbClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_11);
		 fbClient = new DefaultFacebookClient(accessToken, Version.LATEST);
		ArrayList<GeneralMessage> list = new ArrayList<GeneralMessage>();
		@SuppressWarnings("unused")
		User me = fbClient.fetchObject(groupID, User.class);
		Connection<Post> result = fbClient.fetchConnection(groupID,Post.class);
		for (List<Post> page : result) {
			for (Post aPost : page) {
			GeneralMessage msg = new GeneralMessage(GeneralMessage.FACEBOOK, aPost);
				if(aPost.getMessage() != null){
					list.add(msg);
				}
			}
		}
		return list;
	}
	
//	TODO Create the toString function for the post to show in the GUI
	public static String postToString(Post post) {
		String s = post.getCreatedTime()+ " - ES-2018" + System.lineSeparator()+"---------------------------------------------------------------------------------------"+ System.lineSeparator()+post.getMessage();
		return s;
	}
	public void novoPost(String S){
		GraphResponse publishMessageResponse = fbClient.publish(groupID,GraphResponse.class,Parameter.with("message", S));
	}
	
	
	public void comenta(Post post,String s) {
		String destpath= "/" + post.getId() + "/comments";
		Comment commented = fbClient.publish(destpath, Comment.class, Parameter.with("message", s));
	}
	
	public void like (Post post){
		String destpath= "/" + post.getId() + "/likes";
		LikeItem like=fbClient.publish(destpath, LikeItem.class);
	}

}