package Facebook;

import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.Post;

public class FacebookAPI {

	public static void main(String[] args) {
		
		String accessToken = "EAAfRHvz32fIBAKhMDUMptmVtxda4ZByGrAMZC4MeiXVqsI88WhlZAUceNtSyrJLeqVFdW7LcfblkdE9GWNMrjvb1ybAxtIAvlx4R3uSZBTZBldnHZBH1H1VydbF0IU3xOtQa7yvWPthRIvBzbGZCxt72YL0BQhw7oW8rB2SOGi4RAZDZD";
		
		FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_11);
		
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		System.out.println("\nPosts:");
		int counter = 0;
		int counterTotal = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "Inform"
				if (aPost.getMessage() != null && aPost.getMessage().contains("YouTube")) {
					System.out.println("---- Post "+ counter + " ----");
					System.out.println("Id: "+"fb.com/"+aPost.getId());
					System.out.println("Message: "+aPost.getMessage());
					System.out.println("Created: "+aPost.getCreatedTime());
					counter++;
				}
				counterTotal++;
			}
		}
		System.out.println("-------------\nNº of Results: " + counter+"/"+counterTotal);
	}
}
