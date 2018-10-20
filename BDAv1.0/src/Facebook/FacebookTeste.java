package Facebook;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Version;
import com.restfb.types.*;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FacebookTeste {

	private static String accessToken;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String accessToken = "EAAfRHvz32fIBAGOL9h7FDEYYg56CQpKhN2YqcGBpGcLh4XeZBFnxox2K6AZCbCBDiY0oHCh3a4Ja3KizHq1ZA2DtbbKlwVZACo7fSZCM1S8wWX2t5i55B96p1ZCBNNh2AwTZBTmmiHG3gx9YodTiKbrPEuhbv9xuO040WsmOyTM3g0c6orG1qgjwfzbqk9Buj6MYdNqqLTjpAZDZD";
		
//		String accessToken = "EAAMlyA8qtXIBAAxttLP25x8HIuZBer2g6Eq3GZBLtom33G3R6s9aUZA7RBcQvJtmddaXhniJJ0CVZC6QX5dyIUF1CZBfDS3kVsA6hu9IaYR0vJvFtWJOEyw0tIQLA0tgDd2BlQXvicDbPVQ9NC182bEkfAT7OtAEZD";
		
		FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_11);
		AccessToken extendedAccessToken = fbClient.obtainExtendedAccessToken("2200255860300274", "3e00abedb5e6f6711d8a75f30baa95c3");
		User me = fbClient.fetchObject("me", User.class);
		
		
		System.out.println(me.getName());
		System.out.println(me.getLikes());
		
		System.out.println("ExtendedAccessToken: "+ extendedAccessToken.getAccessToken());
		System.out.println("Expires: " + extendedAccessToken.getExpires());
	}
    
}
