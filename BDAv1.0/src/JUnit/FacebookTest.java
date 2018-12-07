package JUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import BDA.FaceAPI;
import BDA.GeneralMessage;

public class FacebookTest {

	@Test
	public void test() {
		FaceAPI fb= new FaceAPI();
		ArrayList<GeneralMessage> output=fb.getList();
		assertEquals(false,output.isEmpty());
	}

}
