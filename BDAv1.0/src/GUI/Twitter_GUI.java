package GUI;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Twitter_GUI  {
	private JPanel panel;
	//private Component[] informacaoTweet;
	
	public Twitter_GUI() {
		Adiciona();
	}


	private void Adiciona(){
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		JButton Button = new JButton("LOGIN-Twitter");
		//Button.setOpaque(false);
		 //Button.setBorderPainted(false);
	   	 //Button.setContentAreaFilled(false);
	   	 //Button.setFocusPainted(false);
		//Button.setIcon(new ImageIcon("C:\\Users\\Lu\u00EDs\\Documents\\ES\\isThisTheRealLife\\Twitter button.png"));
		
		panel.add(Button);
		
		
		
		
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		}
	private void feed(){
		
	}


	public JPanel getPanel() {
		return panel;
	}


	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
}