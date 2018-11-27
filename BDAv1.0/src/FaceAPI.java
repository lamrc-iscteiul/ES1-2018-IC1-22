import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Version;
import com.restfb.types.*;


import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class FaceAPI {
	
	public static ArrayList<GeneralMessage> getList() {
		// TODO Auto-generated method stub
		String accessToken = "EAAfRHvz32fIBAFrkul9cyURlM2Q4QUgTAuiaZBsX2LjJS2VTIZBYwMftYZCC8wFcpBZAaiNr9wwzVJfzbwwJ7vmTbZAWBxzHoOeSZBIBXSQukobE3lHlt5eTRbZCXWZBjYmrJebceVUZCfwvyDQvlJ1xltVayNdP42kPcAQ7KFMTILZAmH230rCG550fhfkzzahzC696eiqQf4hwZDZD";
		
		FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_11);
		User me = fbClient.fetchObject("me", User.class);
		
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		ArrayList<GeneralMessage> list = new ArrayList<GeneralMessage>();
		for (List<Post> page : result) {
			for (Post aPost : page) {
			GeneralMessage msg = new GeneralMessage();
			if(aPost.getMessage() != null){
				msg.setBody(aPost.getMessage());
				msg.setData(aPost.getCreatedTime().toString());
			msg.setSource("@João Pimenta");
			msg.setType(GeneralMessage.FACEBOOK);
			list.add(msg);
			}
			}
			
		}
		System.out.println("termieni");
		return list;
	}
	
	
}