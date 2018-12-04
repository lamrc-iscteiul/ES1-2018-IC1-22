package JUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JList;

import org.junit.Test;

import BDA.GUI;
import BDA.GeneralMessage;
import BDA.TwitterAPI;

public class GUITesting {

	@Test
	public void test() {
		GUI gui= new GUI();
		assertEquals(true,gui.getFrame().isEnabled());
	}


	@Test
	public void test1() {
		GUI gui= new GUI();
		JList<GeneralMessage> output = gui.getList();
		gui.pesquisar("ola");
		assertEquals(output,gui.getList());
	}
	
	@Test
	public void test2() {
		GUI gui= new GUI();
		JList<GeneralMessage> output = gui.getList();
		TwitterAPI test= new TwitterAPI();
		ArrayList<GeneralMessage> output1= test.getList("ISCTEIUL");
		gui.mudaRespostas(output1);
		assertEquals(false,output1.equals(output));
	}
	
	
}
