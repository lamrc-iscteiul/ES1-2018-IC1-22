package BDA;


import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
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
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.DefaultListModel;
import javax.swing.BorderFactory;


import javax.swing.ImageIcon;

import java.awt.Toolkit;
 public class GUI {
 	
 	private JFrame frame,config;
	private JTextArea text_area;
	private JTextField search_box;
	private JPanel search_area;
	private DefaultListModel<GeneralMessage> model;
	private JList<GeneralMessage> news_list;
	private ArrayList<GeneralMessage> tudo;
	private JScrollPane scroll_pane;
	private JPanel Lista;
    private JScrollPane   list_pane;
    private JPanel coise,coise2,panelT;
 	private Facebook_GUI face;
 	private EmailAPI mail;
 	private TwitterAPI tweet;
 	private JButton Button2,Button;
 	private int i=2;
 	
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
		configu();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		face= new Facebook_GUI();
		tweet = new TwitterAPI();
		model = new DefaultListModel<GeneralMessage>();
		news_list = new JList<GeneralMessage>();
		panelT = new JPanel();
		panelT.setOpaque(false);
		panelT.setLayout(new GridLayout(0, 1, 0, 0));
		 Button = new JButton("");
		Button.setOpaque(false);
		Button.setBorderPainted(false);
	   	Button.setContentAreaFilled(false);
	   	Button.setFocusPainted(false);
		Button.setIcon(new ImageIcon("Images/twitter_Buton.png"));
		panelT.add(Button);
		Button.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent arg0) {
				for (GeneralMessage R :tweet.getList("@ISCTEIUL")) {
					//tudo.add(R);
				}
				mudaRespostas(tweet.getList("@ISCTEIUL"));
				muda(Button);
				Update();
				System.out.println("OI");
			}
		});
		 Button2 = new JButton("");
		Button2.setOpaque(false);
		Button2.setBorderPainted(false);
	   	Button2.setContentAreaFilled(false);
	   	Button2.setFocusPainted(false);
		Button2.setIcon(new ImageIcon("Images/mail_Button.png"));
		panelT.add(Button2);
		Button2.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("coise");
				for (GeneralMessage R : mail.getList()) {
					//tudo.add(R);
				}
				mudaRespostas(mail.getList());
				muda(Button2);
				Update();
			}
		});
		frame = new JFrame();
		frame.setBounds(0, 0, 800,735);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new JLabel(new ImageIcon("Images/background-teste4.jpg")));
		frame.setSize(1300, 700);
		
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
	         
	          
	    text_area = new JTextArea();
		text_area.setEditable(false);
		text_area.setFont(new Font("Arial",Font.PLAIN,16));
		text_area.setLineWrap(true);
		text_area.setWrapStyleWord(true);
		
		 scroll_pane= new JScrollPane(text_area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll_pane.setPreferredSize(new Dimension(900, 652));
		
		//importante
		scroll_pane.getViewport().setOpaque(false);
		 
		 
	    search_area = new JPanel();
		search_box = new JTextField();
		search_box.setPreferredSize(new Dimension(240, 25));
		JButton search_button = new JButton("Search");
		
		
		news_list.setFixedCellWidth(70);
	    list_pane = new JScrollPane(news_list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		list_pane.setPreferredSize(new Dimension(70, 40));
	  //  list_pane.setMaximumSize(new Dimension(70, 40));
		coise2= new JPanel();
		
		news_list.setModel(model);
		
		search_area.setLayout(new FlowLayout());
		search_area.add(search_box);
		search_area.add(search_button);

		JCheckBox chckbxFacebook = new JCheckBox("Facebook");
		chckbxFacebook.setOpaque(false);
		chckbxFacebook.setForeground(Color.WHITE);
		search_area.add(chckbxFacebook);
        chckbxFacebook.setSelected(true);
        
        JCheckBox chckbxTwitter = new JCheckBox("Twitter");
        chckbxTwitter.setOpaque(false);
		chckbxTwitter.setForeground(Color.WHITE);
        search_area.add(chckbxTwitter);
        chckbxTwitter.setSelected(true);
        
        JCheckBox chckbxEmail = new JCheckBox("E-mail");
        chckbxEmail.setOpaque(false);
		chckbxEmail.setForeground(Color.WHITE);
        search_area.add(chckbxEmail);
        chckbxEmail.setSelected(true);
        
		JButton config_button = new JButton("Configura\u00E7\u00F5es");
		class ListenerForSearchButton implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				config_button.setEnabled(false);
				String s=search_box.getText();
			}

		}
		search_area.add(config_button);
        search_area.setOpaque(false);
		//Listeners, etc
		frame.add(search_area, BorderLayout.NORTH);
		//frame.add(login);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		coise2.add(panelT);
		coise2.setOpaque(false);
		search_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				search_button.setEnabled(false);
				search_button.doClick();
				search_button.setEnabled(true);
				Update();
			}
		});
		
		config_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				config_button.setEnabled(false);
				config_button.doClick();
				config_button.setEnabled(true);
				configu();
			}
		});
		 
		 chckbxFacebook.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (chckbxFacebook.isSelected()){	
					}
					
					else {
					}
									}
			});
			chckbxTwitter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (chckbxTwitter.isSelected()){
					
					}
					else {
						
					}
					
				}
			});
			chckbxEmail.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
		
	}
	
	/**
	 * Atualiza os paineis
	 */
	private void Update(){
	
		coise= new JPanel();
		coise.add(search_area, BorderLayout.NORTH);
		scroll_pane.setBackground(new Color(0,0,0,115));
		coise.add(scroll_pane, BorderLayout.CENTER);
		coise.setOpaque(false);
		
		Border J =  BorderFactory.createEmptyBorder(10, 60, 10, 300);
		list_pane.setBorder(J);
		list_pane.setBackground(new Color(0,0,0,115));
		list_pane.setOpaque(false);
		news_list.setFixedCellWidth(300);
		news_list.setPreferredSize(new Dimension(300, 700));
		frame.add(list_pane, BorderLayout.WEST);
//		frame.add(search_area, BorderLayout.NORTH);
//		frame.add(scroll_pane, BorderLayout.EAST);
		frame.add(coise, BorderLayout.CENTER);
		frame.pack();

	}
	
	/**
	 * Inicializa a frame de configurações
	 */
	private void configu(){
		
		config = new JFrame();
		config.setTitle("Configura\u00E7\u00F5es");
		config.setBounds(100, 100, 530, 394);
		config.setContentPane(new JLabel(new ImageIcon("Images/putin.png")));
      	config.getContentPane().setLayout(new BorderLayout(0, 0));
		config.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		config.add(coise2);
		config.pack();
		config.setVisible(true);
		config.setLocation(310,0);
		config.setAlwaysOnTop(true);
		config.setResizable(false);
			}
	private void muda(JButton k){
		panelT.remove(k);
		config.pack();
	}
	
	/**
	 * Atualiza os paineis, recebendo uma lista
	 * @param M Lista de GeneralMessage
	 */
	public void mudaRespostas(ArrayList<GeneralMessage> M) {
		
		list_pane.removeAll();
		if (M.isEmpty()) {
			JLabel P = new JLabel("Sem Resultados");
		} else {
			
			JList<String> list = new JList<>();
			DefaultListModel<String> P = new DefaultListModel<String>();
			for (GeneralMessage R : M) {
				P.addElement(R.getBody());
			}
			list.setModel(P);
			JScrollPane scroll = new JScrollPane(list);
			list.addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					for (GeneralMessage R : M) {
						if (list.getSelectedValue().equals(R.getBody())) {
							text_area.setText(R.getBody());
							text_area.setVisible(true);
			               scroll_pane.add(text_area);
			               Update();
							break;
						}
					}

				}
			});
			list_pane=scroll;
			list_pane.setPreferredSize(new Dimension(70, 40));
			frame.pack();
		}
}
/*	void search(String s){
		
		for(String x: news_list){
			String[] aux= x.split(" ");
		
			for (int i=0;i!=aux.length;i++)
				if(aux[i].equals(s))
					pesquisa.add(x);
				}		
	}*/
 
 }