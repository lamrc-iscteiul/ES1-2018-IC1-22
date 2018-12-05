package JUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;

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
	
	@Test
	public void test3() {
		GUI gui= new GUI();
		TwitterAPI test= new TwitterAPI();
		ArrayList<GeneralMessage> output1= test.getList("ISCTEIUL");
		gui.mudaRespostas(output1);
		gui.getList().setSelectedValue(gui.getList().getModel().getElementAt(1), true);
		gui.getLsl().valueChanged(new ListSelectionEvent(gui.getList().getModel().getElementAt(1),1,1,false));
	}
}
