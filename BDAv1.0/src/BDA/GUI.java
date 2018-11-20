package BDA;
import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JScrollBar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Scrollbar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import java.awt.Toolkit;
 public class GUI {
 	private JFrame frame;
 	private JPanel panel_3;
 	private JScrollPane scroll;
 	private Facebook_GUI face;
 	private Twitter_GUI tweet;
 	//private Component []  informacao;
 	/**
	 * Lança a aplicação.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
					window.frame.setTitle("BDA");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 	/**
	 * Cria a aplicação.
	 */
	public GUI() {
		initialize();
		construct();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		face= new Facebook_GUI();
		tweet = new Twitter_GUI();
		
		frame = new JFrame();
		frame.setBounds(0, 0, 800,735);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new JLabel(new ImageIcon("Images/background-teste4.jpg")));
		
		//Data
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate localDate = LocalDate.now();
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
	    JSplitPane splitPane = new JSplitPane();
	    splitPane.setEnabled(false);
	    frame.getContentPane().add(splitPane, BorderLayout.NORTH);
	    splitPane.setDividerLocation(350);
	    JLabel lblData = new JLabel(dtf.format(localDate));
	    lblData.setHorizontalAlignment(SwingConstants.RIGHT);
	    splitPane.setRightComponent(lblData);
	    
	    JLabel lblBomDiaAcademia = new JLabel("Bom Dia Academia");
	    splitPane.setLeftComponent(lblBomDiaAcademia);
	    lblBomDiaAcademia.setHorizontalAlignment(SwingConstants.CENTER);
	          JPanel panel = new JPanel();
	          panel.setOpaque(false);
	          frame.getContentPane().add(panel, BorderLayout.SOUTH);
	          
	          JCheckBox chckbxFacebook = new JCheckBox("Facebook");
	          panel.add(chckbxFacebook);
	          chckbxFacebook.setSelected(true);
	          
	          JCheckBox chckbxTwitter = new JCheckBox("Twitter");
	          panel.add(chckbxTwitter);
	          chckbxTwitter.setSelected(true);
	          
	          JCheckBox chckbxEmail = new JCheckBox("E-mail");
	          panel.add(chckbxEmail);
	          chckbxEmail.setSelected(true);
	          JPanel panel_1= new JPanel();
	          panel_1.setOpaque(false);
	          
	          JTextField text=new JTextField();
	          text.setMinimumSize(new Dimension(100,200));
	          frame.getContentPane().add(text, BorderLayout.NORTH);
	          
	          JButton search= new JButton("SEARCH");
	          search.addActionListener(new ActionListener() {
	          
	        	  
				@Override
				public void actionPerformed(ActionEvent arg0) {
					tweet.search(text.getText());
					System.out.println(tweet.getPesquisa());
					
				}
	        	  
	          });
	          
	          
	   frame.getContentPane().add(search, BorderLayout.EAST);
	           panel_3 = new JPanel();
	             panel_3.setOpaque(false);
	           panel_3.setLayout(new GridLayout(2, 0,0 , 2));
	           
	           panel_3.add(face.getPanel());
	           panel_3.add(tweet.getPanel());
	           panel_3.setOpaque(false);
	          // panel_3.setBackground(new Color(0,0,0,0));
	         
	            scroll= new JScrollPane(panel_3,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	            scroll.getViewport().setOpaque(false);
	            scroll.setOpaque(false);
	             // scroll.setVisible(true);
		          frame.getContentPane().add(scroll, BorderLayout.CENTER);
		 
		 chckbxFacebook.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (chckbxFacebook.isSelected()){
						panel_3.add(face.getPanel());
						
					}
					
					else {
						panel_3.remove(face.getPanel());
					}
					scroll.updateUI();
				}
			});
			chckbxTwitter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (chckbxTwitter.isSelected()){
					panel_3.add(tweet.getPanel());
					}
					else {
						panel_3.remove(tweet.getPanel());
					}
					scroll.updateUI();
				}
			});
			chckbxEmail.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
		
	}
	private void construct(){
		
		

		
	}
	/**
	 * Função retorna um painel
	 * @return JPanel que representa um painel
	 */
	public JPanel getPanel() {
		return panel_3;
	}
	
	/**
	 * Função define um painel JPanel
	 * @param panel_3 JPanel que representa um painel
	 */
	public void setPanel(JPanel panel_3) {
		this.panel_3 = panel_3;
	}
}