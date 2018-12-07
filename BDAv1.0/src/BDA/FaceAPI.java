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
	
	final String pageid ="me/feed";
	
	/**
	 *Reads the AcessToken from the XML file, connects to the facebook page and gets the list of posts made by the page ES-2018.
	 */
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
		 fbClient = new DefaultFacebookClient(accessToken, Version.LATEST);
		ArrayList<GeneralMessage> list = new ArrayList<GeneralMessage>();
		Connection<Post> result = fbClient.fetchConnection(pageid,Post.class);
		for (List<Post> page : result) {
			for (Post aPost : page) {
			GeneralMessage msg = new GeneralMessage(GeneralMessage.FACEBOOK, aPost,aPost.getCreatedTime());
				if(aPost.getMessage() != null){
					list.add(msg);
				}
			}
		}
		return list;
	}
	
//	TODO Create the toString function for the post to show in the GUI
	/**
	 *Creates a String of the post information to be shown in the interface.
	 */
	public static String postToString(Post post) {
		String s = post.getCreatedTime()+ " - ES-2018" + System.lineSeparator()+"---------------------------------------------------------------------------------------"+ System.lineSeparator()+post.getMessage();
		return s;
	}
	/**
	 *Creates a new Post in the facebook page with the string information.
	 */
	public void novoPost(String S){
		GraphResponse novoPost = fbClient.publish(pageid,GraphResponse.class,Parameter.with("message", S));
	}
	
	/**
	 *Creates a comment in the post .
	 */
	public void comenta(Post post,String s) {
		String destpath= "/" + post.getId() + "/comments";
		Comment comment = fbClient.publish(destpath, Comment.class, Parameter.with("message", s));
	}
	
	/**
	 *puts a like in the post .
	 */
	public void like (Post post){
		String destpath= "/" + post.getId() + "/likes";
		LikeItem like=fbClient.publish(destpath, LikeItem.class);
	}

}