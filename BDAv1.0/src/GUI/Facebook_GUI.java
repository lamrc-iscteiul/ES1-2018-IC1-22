package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.synth.SynthSeparatorUI;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;


/**
 * Representa o GUI que diz respeito ao Facebook.
 * @author Adil, Luis
 * @version 1.0
 */
public class Facebook_GUI  {
	private JPanel panel;
	private JButton Button;
	//private Component[] informacaoFace;
	
	/**
	 * Inicializa o GUI do Twitter.
	 */
	public Facebook_GUI() {
		Adiciona();
	}

	/**
	 * Adiciona ao painel os componentes necessários (botões, imagens, etc...).
	 */
	private void Adiciona(){
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(0,1, 0, 0));
		 Button = new JButton("LOGIN-Facebook");
		 Button.setOpaque(false);
		 Button.setBorderPainted(false);
	   	 Button.setContentAreaFilled(false);
	   	 Button.setFocusPainted(false);
		 Button.setIcon(new ImageIcon("Images/imageedit_1_4496880657.png"));
		 //Image background = Toolkit.getDefaultToolkit().createImage("C:\\Users\\Lu\u00EDs\\Documents\\ES\\isThisTheRealLife-checkboxother\\background-teste1.jpg");
		
		 panel.add(Button);
		 Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.out.println("woeruwoe");	
			}
		});
		//informacaoFace= new Component[] ;
		}
	
	/**
	 * EMPTY
	 */
	private void feed(){
		
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