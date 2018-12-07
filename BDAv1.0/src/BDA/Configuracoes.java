package BDA;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class Configuracoes {

	private JFrame configFrame;
	private static JTextField text_email_user;
	private static JTextField text_email_pass;
	private static JTextField text_twitter_consumerkey;
	private static JTextField text_twitter_consumersecret;
	private static JTextField text_twitter_accesstoken;
	private static JTextField text_twitter_accesstokensecret;
	private static JTextField text_facebook_accesstoken;
	private static configXML configXML;
	private static Configuracoes window;
	private static JCheckBox chckbxh;
	private static JCheckBox chckbxh_1;
	private static JCheckBox chckbxDias;
	private static JCheckBox chckbxIscteiul;
	private static JCheckBox chckbxBiblioteca;
	private static JCheckBox chckbxElearning;
	private static JCheckBox chckbxReitora;
	private static JCheckBox chckbxFenix;
	private static JCheckBox chckbxTesouraria;
	private static JCheckBox chckbxTudo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 window = new Configuracoes();
					//window.configFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void init() {
		window = new Configuracoes();
	}
	
	/**
	 * Create the application.
	 */
	public Configuracoes() {
		initialize();
	}

	/**
	 * Initialize the contents of the configFrame.
	 */
	private void initialize() {
		configFrame = new JFrame();
		configXML=new configXML();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage("icon.png");
		configFrame.setIconImage(img);
		
		configFrame.setBounds(100, 100, 530, 530);
		configFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		configFrame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("E-Mail", null, panel, null);
		
		text_email_user = new JTextField();
		text_email_user.setBounds(212, 21, 266, 32);
		text_email_user.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(21, 24, 170, 26);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(21, 71, 170, 26);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBorder(null);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 configXML.setEmail(text_email_user.getText(), text_email_pass.getText());
				 try {
					guardarXML();
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		});
		btnSalvar.setBounds(171, 133, 141, 35);
		panel.setLayout(null);
		panel.add(text_email_user);
		panel.add(lblEmail);
		panel.add(lblPassword);
		panel.add(btnSalvar);
		JLabel pic = new JLabel(new ImageIcon("bg.jpg"));
		panel.add(pic);
		
		text_email_pass = new JTextField();
		text_email_pass.setBounds(212, 68, 266, 32);
		panel.add(text_email_pass);
		text_email_pass.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("Twitter", null, panel_1, null);
		
		JButton button = new JButton("Salvar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configXML.setTwitter(text_twitter_consumerkey.getText(), text_twitter_consumersecret.getText(), text_twitter_accesstoken.getText(), text_twitter_accesstokensecret.getText());
			try {
				guardarXML();
			} catch (JAXBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		button.setBorder(null);
		button.setBounds(170, 363, 141, 35);
		panel_1.add(button);
		
		JLabel label_3 = new JLabel((Icon) null);
		label_3.setBounds(0, 0, 0, 0);
		panel_1.add(label_3);
		
		text_twitter_consumerkey = new JTextField();
		text_twitter_consumerkey.setBounds(212, 121, 266, 32);
		panel_1.add(text_twitter_consumerkey);
		text_twitter_consumerkey.setColumns(10);
		
		text_twitter_consumersecret = new JTextField();
		text_twitter_consumersecret.setBounds(212, 174, 266, 32);
		panel_1.add(text_twitter_consumersecret);
		text_twitter_consumersecret.setColumns(10);
		
		text_twitter_accesstoken = new JTextField();
		text_twitter_accesstoken.setBounds(212, 227, 266, 32);
		panel_1.add(text_twitter_accesstoken);
		text_twitter_accesstoken.setColumns(10);
		
		text_twitter_accesstokensecret = new JTextField();
		text_twitter_accesstokensecret.setBounds(212, 280, 266, 32);
		panel_1.add(text_twitter_accesstokensecret);
		text_twitter_accesstokensecret.setColumns(10);
		
		JLabel lblConsumerKey = new JLabel("ConsumerKey");
		lblConsumerKey.setBounds(21, 124, 170, 26);
		panel_1.add(lblConsumerKey);
		
		JLabel lblConsumersecret = new JLabel("ConsumerSecret");
		lblConsumersecret.setBounds(21, 177, 170, 26);
		panel_1.add(lblConsumersecret);
		
		JLabel lblAccesstoken = new JLabel("AccessToken");
		lblAccesstoken.setBounds(21, 230, 170, 26);
		panel_1.add(lblAccesstoken);
		
		JLabel lblAccesstokensecret = new JLabel("AccessTokenSecret");
		lblAccesstokensecret.setBounds(21, 283, 189, 26);
		panel_1.add(lblAccesstokensecret);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		tabbedPane.addTab("Facebook", null, panel_2, null);
		
		JButton button_1 = new JButton("Salvar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configXML.setFacebook(text_facebook_accesstoken.getText());
				try {
					guardarXML();
				} catch (JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setBorder(null);
		button_1.setBounds(170, 174, 141, 35);
		panel_2.add(button_1);
		
		JLabel label_5 = new JLabel((Icon) null);
		label_5.setBounds(0, 0, 0, 0);
		panel_2.add(label_5);
		
		text_facebook_accesstoken = new JTextField();
		text_facebook_accesstoken.setColumns(10);
		text_facebook_accesstoken.setBounds(212, 121, 266, 32);
		panel_2.add(text_facebook_accesstoken);
		
		JLabel label_6 = new JLabel("AccessToken");
		label_6.setBounds(21, 124, 170, 26);
		panel_2.add(label_6);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Filtros", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblFiltros = new JLabel("Tempo");
		lblFiltros.setBounds(21, 21, 92, 26);
		panel_3.add(lblFiltros);
		
		chckbxh = new JCheckBox("24h");
		chckbxh.setBounds(21, 54, 73, 35);
		panel_3.add(chckbxh);
		
		chckbxh_1 = new JCheckBox("48h");
		chckbxh_1.setBounds(107, 54, 73, 35);
		panel_3.add(chckbxh_1);
		
		chckbxDias = new JCheckBox("7 dias");
		chckbxDias.setBounds(21, 102, 179, 35);
		panel_3.add(chckbxDias);
		
		JLabel lblContas = new JLabel("Contas (Twitter)");
		lblContas.setBounds(238, 21, 157, 26);
		panel_3.add(lblContas);
		
		chckbxIscteiul = new JCheckBox("ISCTE-IUL");
		chckbxIscteiul.setBounds(248, 54, 179, 35);
		panel_3.add(chckbxIscteiul);
		
		chckbxBiblioteca = new JCheckBox("Biblioteca");
		chckbxBiblioteca.setBounds(248, 102, 179, 35);
		panel_3.add(chckbxBiblioteca);
		
		JLabel lblDeemail = new JLabel("De (E-Mail)");
		lblDeemail.setBounds(21, 167, 129, 26);
		panel_3.add(lblDeemail);
		
		chckbxElearning = new JCheckBox("E-Learning");
		chckbxElearning.setBounds(21, 208, 179, 35);
		panel_3.add(chckbxElearning);
		
		chckbxTesouraria = new JCheckBox("Tesouraria");
		chckbxTesouraria.setBounds(21, 256, 179, 35);
		panel_3.add(chckbxTesouraria);
		
		chckbxReitora = new JCheckBox("Reitora");
		chckbxReitora.setBounds(248, 208, 179, 35);
		panel_3.add(chckbxReitora);
		
		chckbxFenix = new JCheckBox("Fenix");
		chckbxFenix.setBounds(248, 256, 179, 35);
		panel_3.add(chckbxFenix);
		
		chckbxTudo = new JCheckBox("Tudo");
		chckbxTudo.setBounds(248, 304, 179, 35);
		panel_3.add(chckbxTudo);
		
		JButton btnSalvar_1 = new JButton("Salvar");
		btnSalvar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				configXML.setFiltros(chckbxh.isSelected(), chckbxh_1.isSelected(), chckbxDias.isSelected(), chckbxIscteiul.isSelected(), chckbxBiblioteca.isSelected(), chckbxElearning.isSelected(),chckbxReitora.isSelected(), chckbxFenix.isSelected(), chckbxTesouraria.isSelected(),chckbxTudo.isSelected());
				try {
					guardarXML();
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnSalvar_1.setBounds(173, 363, 141, 35);
		panel_3.add(btnSalvar_1);
	
		//escreve no GUI o que está no XML
		try {
			XMLtoGUI();
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	private void XMLtoGUI() throws JAXBException {
		lerXML();
		//email
		text_email_user.setText(configXML.getEmail().getEmail());
		text_email_pass.setText(configXML.getEmail().getPassword());
		text_facebook_accesstoken.setText(configXML.getFacebook().getAccessToken());
		text_twitter_consumerkey.setText(configXML.getTwitter().getConsumerKey());
		text_twitter_consumersecret.setText(configXML.getTwitter().getConsumerKeySecret());
		text_twitter_accesstoken.setText(configXML.getTwitter().getAccessToken());
		text_twitter_accesstokensecret.setText(configXML.getTwitter().getAccessTokenSecret());
		//Filtros
		chckbxh.setSelected(configXML.getFiltros().getChckbxh());
		chckbxh_1.setSelected(configXML.getFiltros().getChckbxh_1());
		chckbxDias.setSelected(configXML.getFiltros().getChckbxDias());
		chckbxIscteiul.setSelected(configXML.getFiltros().getChckbxIscteiul());
		chckbxBiblioteca.setSelected(configXML.getFiltros().getChckbxBiblioteca());
		chckbxElearning.setSelected(configXML.getFiltros().getChckbxElearning());
		chckbxReitora.setSelected(configXML.getFiltros().getChckbxReitora());
		chckbxFenix.setSelected(configXML.getFiltros().getChckbxFenix());
		chckbxTesouraria.setSelected(configXML.getFiltros().getChckbxTesouraria());
		chckbxTudo.setSelected(configXML.getFiltros().getChckbxTudo());
		
	}
	
	public void guardarXML() throws JAXBException {
		File file = new File("config.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(configXML.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(configXML, file);
        jaxbMarshaller.marshal(configXML, System.out);
	}
	
	public void lerXML() throws JAXBException {
		File file = new File("config.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(configXML.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        configXML = (configXML) unmarshaller.unmarshal(file);
	}
	
	public static String getText_email_user() {
		return text_email_user.getText();
	}
	
	public static void visible(boolean a) {
		window.configFrame.setVisible(a);
	}
	
	public static configXML getconf() {
		return configXML;
	}
}