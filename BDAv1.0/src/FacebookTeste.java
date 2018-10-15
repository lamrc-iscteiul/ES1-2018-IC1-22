import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Version;
import com.restfb.types.*;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public class FacebookTeste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String accessToken = "EAAMlyA8qtXIBAMfqC05IGrhi7SUkOIDPVNlo7EjZCuizjeHVV6ajPAOJLdEXVkN0eE34MMIDRnlaFpoxLVjKk9DR9rvAZBgYspAnyfLryl4MU5QI3yONzodzgswoXQUEKFhCmA7yfbRnvtJZB0xdoDczmZCpLW18eOm3p6gClVppZAzxJIZAdkI7DbAVHQiXRHlTjQb9ZCy3AZDZD";
		
		FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_11);
		AccessToken extendedAccessToken = fbClient.obtainExtendedAccessToken("885966108276082", "cbda060b0449553f15326859364fde29");
		User me = fbClient.fetchObject("me", User.class);
		
		
		System.out.println(me.getName());
		System.out.println(me.getLikes());
		
		System.out.println("ExtendedAccessToken: "+ extendedAccessToken.getAccessToken());
		System.out.println("Expires: " + extendedAccessToken.getExpires());
	}
	
    
}
