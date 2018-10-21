package GUI;
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
	 * Launch the application.
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
	 * Create the application.
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
		frame.setContentPane(new JLabel(new ImageIcon("Images/44445352_244735456205143_5607783552499318784_n.jpg")));
		
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
					panel_3.removeAll();
					if (chckbxFacebook.isSelected()){
						System.out.println("hei");
						panel_3.add(face.getPanel());
						
					}
					
					else {
						
					}
					scroll.updateUI();
				}
			});
			chckbxTwitter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					panel_3.removeAll();
					if (chckbxTwitter.isSelected()){
					panel_3.add(tweet.getPanel());
					}
					else {
						
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
	public JPanel getPanel() {
		return panel_3;
	}
	public void setPanel(JPanel panel_3) {
		this.panel_3 = panel_3;
	}
	/*private Component createContent() {
		 Image background = Toolkit.getDefaultToolkit().createImage("C:\\Users\\Lu\u00EDs\\Documents\\ES\\isThisTheRealLife-checkboxother\\background-teste1.jpg");
	        JPanel panel = new JPanel() {
	            @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                g.drawImage(background, 0, 0, null);
	            }
	        };

	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	        
	            scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
	            
	            panel.add(scroll);
	        panel.setOpaque(false);
	        panel.setBackground(new Color(0,0,0,0));
			return panel;
	 }
	*/
	
}