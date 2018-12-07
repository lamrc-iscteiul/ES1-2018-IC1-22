package JUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;

import org.junit.Test;


import BDA.EmailAPI;
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
	public void simpleOutput() {
		GUI gui= new GUI();
		JList<GeneralMessage> output = gui.getList();
		gui.pesquisar("ola");
		assertEquals(output,gui.getList());
	}
	
	@Test
	public void lists() {
		GUI gui= new GUI();
		JList<GeneralMessage> output = gui.getList();
		TwitterAPI test= new TwitterAPI();
		ArrayList<GeneralMessage> output1= test.getList("ISCTEIUL");
		gui.mudaRespostas(output1,1);
		assertEquals(false,output1.equals(output));
	}
	
	@Test
	public void pointers(){
		GUI gui= new GUI();
		TwitterAPI test= new TwitterAPI();
		ArrayList<GeneralMessage> output1= test.getList("ISCTEIUL");
		gui.mudaRespostas(output1,1);
		gui.getList().setSelectedValue(gui.getList().getModel().getElementAt(1), true);
		gui.getLsl().valueChanged(new ListSelectionEvent(gui.getList().getModel().getElementAt(1),1,1,false));
	}
	
	@Test
	public void comments_and_posts() {
		GUI gui= new GUI();
		gui.responderMail("smtp@outlook.com", "tu@gmail.com", "teste");
		gui.novoTweet();
		gui.novoPost();
		gui.comentaFacebook();
		gui.comentaTwitter();
	}
	
	@Test
	public void ordenar() {
		GUI gui= new GUI();
		gui.ordenarDate();
		gui.ordenarString();
	
	}
	
	@Test
	public void filters() {
		GUI gui= new GUI();
		TwitterAPI tt= new TwitterAPI();
		ArrayList<GeneralMessage> output=tt.getList("@22iscte");
		gui.getConfigPage().getConfigs().setFiltros(true, false, false, false, false, false, false, false, false, false);
		gui.applyFilters(output);
	
	
	}
	@Test
	public void filters1() {
		GUI gui= new GUI();
		EmailAPI e= new EmailAPI();
		ArrayList<GeneralMessage> output=e.getList();
		gui.getConfigPage().getConfigs().setFiltros(true, false, false, false, false, false, false, false, false, false);
		gui.applyFilters(output);
	
	
	}
	
}
	
	



