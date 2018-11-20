package BDA;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import twitter4j.Status;
import twitter4j.TwitterException;
/**
 * Representa o GUI que diz respeito ao Twitter.
 * @author Adil, Luis
 * @version 1.0
 */
public class Twitter_GUI  {
	private JPanel panel;
	private JTextArea feed;
	private JFrame frame;
	private ArrayList<String> pesquisa= new ArrayList<String>(); 
	
	//private Component[] informacaoTweet;
	/**
	 * Inicializa o GUI do Twitter.
	 */
	public Twitter_GUI() {
		Adiciona();
	}

/**
 * Adiciona ao painel os componentes necess�rios (bot�es, imagens, etc...).
 */
	private void Adiciona(){
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		JButton Button = new JButton("");
		Button.setOpaque(false);
		Button.setBorderPainted(false);
	   	Button.setContentAreaFilled(false);
	   	Button.setFocusPainted(false);
		Button.setIcon(new ImageIcon("Images/Twitter button.png"));
		panel.add(Button);
		
		Button.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent arg0) {
				feed();
			}
		});
		}
	
	/**
	 * Fun��o que mostra os resultados da pesquisa feita pelo utilizador
	 */
	void search(String s){
		for(String x: TwitterAPI.getPesquisa()){
			String[] aux= x.split(" ");
		
			for (int i=0;i!=aux.length;i++)
				if(aux[i].equals(s))
					pesquisa.add(x);
				}		
	}
	
	public void setPesquisa(ArrayList<String> pesquisa) {
		this.pesquisa = pesquisa;
	}

	/**
	 * Fun��o que atualiza o painel, depois do login, para que o feed apare�a.
	 */
	private void feed(){
		
		pesquisa.clear();
		List<Status> status_iscte = null;				
		feed = new JTextArea ( 80,2);
		JScrollPane scrollArea = new JScrollPane(feed);
		try {
			status_iscte = TwitterAPI.getTimeline("ISCTEIUL");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			List<Status> status_biblio = TwitterAPI.getTimeline("BibliotecaISCTE");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		feed.append(TwitterAPI.getTimelineText(status_iscte));
		
		feed.setForeground(Color.WHITE);
		feed.setBackground(new Color(0,0,0,115));
		JScrollPane scroll = new JScrollPane(feed);			
		scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
		panel.removeAll();			
		panel.add(scroll);
	}

public ArrayList<String> getPesquisa() {
		return pesquisa;
	}

/**
 * Obtem um painel do tipo JPanel.
 * @return um painel to tipo JPanel
 */
	public JPanel getPanel() {
		return panel;
	}

/**
 * Define um painel do tipo JPanel.
 * @param panel um painel do tipo JPanel
 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
}