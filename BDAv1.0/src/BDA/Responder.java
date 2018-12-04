package BDA;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.mail.MessagingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Responder {

	private JFrame frame;
	private static JTextField textDe = new JTextField();
	private static JTextField textPara = new JTextField();
	//static Responder window = new Responder();
	//private static EmailAPI email= new EmailAPI();
	private static JTextField txtRe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Responder window = new Responder();
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
	public Responder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDe = new JLabel("De:");
		lblDe.setBounds(21, 21, 92, 26);
		frame.getContentPane().add(lblDe);
		
		JLabel lblPara = new JLabel("Para:");
		lblPara.setBounds(21, 71, 92, 26);
		frame.getContentPane().add(lblPara);
		textDe.setEditable(false);
		
		
		textDe.setBounds(85, 18, 318, 32);
		frame.getContentPane().add(textDe);
		textDe.setColumns(10);
		textPara.setEditable(false);
		
		textPara.setBounds(85, 68, 318, 32);
		frame.getContentPane().add(textPara);
		textPara.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(21, 156, 382, 196);
		frame.getContentPane().add(textPane);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EmailAPI.enviarMail(textDe.getText(), textPara.getText().substring(textPara.getText().indexOf("<")+1,textPara.getText().indexOf(">")), txtRe.getText(),textPane.getText());
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
			}
		});
		btnEnviar.setBounds(262, 373, 141, 35);
		frame.getContentPane().add(btnEnviar);
		
		JLabel lblAssunto = new JLabel("Assunto:");
		lblAssunto.setBounds(21, 118, 92, 26);
		frame.getContentPane().add(lblAssunto);
		
		txtRe = new JTextField();
		txtRe.setText("RE:");
		txtRe.setBounds(112, 115, 291, 32);
		frame.getContentPane().add(txtRe);
		txtRe.setColumns(10);
	}



	public static void setTextDe(String textDe) {
		Responder.textDe.setText(textDe);
	}

	public static void setTextAssunto(String textAssunto) {
		Responder.txtRe.setText(textAssunto);
	}

	public static void setTextPara(String textPara) {
		Responder.textPara.setText(textPara);
	}
}
