import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class GUI {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 530, 394);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Configura\u00E7\u00F5es", null, tabbedPane_1, null);
		
		JPanel panel = new JPanel();
		tabbedPane_1.addTab("E-mail", null, panel, null);
		
		textField = new JTextField();
		textField.setBounds(142, 21, 266, 32);
		textField.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(21, 24, 92, 26);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(21, 71, 92, 26);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(142, 68, 266, 32);
		
		JLabel lblAuthToken = new JLabel("Auth Token");
		lblAuthToken.setBounds(21, 118, 92, 26);
		
		textField_1 = new JTextField();
		textField_1.setBounds(142, 115, 266, 32);
		textField_1.setColumns(10);
		
		// Lê os campos do GUI e guarda no ficheiro XML
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBorder(null);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] string = new String[3];
				string[1]=textField.getText();
				string[2]=passwordField.getPassword().toString();
				string[0]=textField_1.getText();
				GuardaEmailXML.main(string);
			}
		});
		btnSalvar.setBounds(152, 187, 141, 35);
		panel.setLayout(null);
		panel.add(textField);
		panel.add(lblEmail);
		panel.add(lblPassword);
		panel.add(passwordField);
		panel.add(lblAuthToken);
		panel.add(textField_1);
		panel.add(btnSalvar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setVisible(false);
		tabbedPane_1.addTab("Facebook", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane_1.addTab("Twitter", null, panel_2, null);
		
		//Lê o ficheiro XML e escreve nos campos do GUI
		try {
	    	Email email;
	        File file = new File("config.xml");
	        JAXBContext jaxbContext = JAXBContext.newInstance(Email.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        email = (Email) unmarshaller.unmarshal(file);
	        textField.setText(email.email);
	        textField_1.setText(email.token);
	        passwordField.setText(email.password);
	
		 } catch (JAXBException e) {
		        e.printStackTrace();
         }
	
		
	}
}
