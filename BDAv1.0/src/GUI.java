import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {

	private JFrame frame;
	private JTextField textField;
	JList<GeneralMessage> list = new JList<>();
	JTextArea txtrArea = new JTextArea();
	DefaultListModel<GeneralMessage> P = new DefaultListModel<GeneralMessage>();
	ListSelectionListener lsl;
	DefaultListModel<GeneralMessage> search_list = new DefaultListModel<GeneralMessage>();
	JButton btnRetweetar;
	JButton btnComentar;
	JButton btnResponder;
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
		TwitterAPI tweet = new TwitterAPI();
		EmailAPI mail = null;
		FaceAPI face = new FaceAPI();
		try {
			mail = new EmailAPI();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage("Images/icon.png");
		frame.setIconImage(img);
		
		textField = new JTextField();
		textField.setBounds(21, 21, 654, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(696, 20, 141, 35);
		frame.getContentPane().add(btnPesquisar);
		btnPesquisar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pesquisar(textField.getText());
				
			}
	
		}
		);
		
		JToggleButton tglbtnFacebook = new JToggleButton("Facebook");
		tglbtnFacebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			if(tglbtnFacebook.isSelected()) {
				mudaRespostas(face.getList());
			} else {
				list.removeListSelectionListener(lsl);
				txtrArea.setText(null);
				int size= P.getSize();
				
				for(int i=size-1; i>=0;i--) {
					GeneralMessage a=P.getElementAt(i);
					if(a.getType()==GeneralMessage.TWITTER) {
						P.removeElementAt(i);
						}
					}
				}
			}
		});
		tglbtnFacebook.setBounds(858, 76, 119, 35);
		frame.getContentPane().add(tglbtnFacebook);
		
		JToggleButton tglbtnEmail = new JToggleButton("E-Mail");
		tglbtnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			if(tglbtnEmail.isSelected()) {
				mudaRespostas(mail.getList());
			} else {
				list.removeListSelectionListener(lsl);
				txtrArea.setText(null);
				int size= P.getSize();
				
				for(int i=size-1; i>=0;i--) {
					GeneralMessage a=P.getElementAt(i);
					if(a.getType()==GeneralMessage.EMAIL) {
						P.removeElementAt(i);
						}
					}
				}
				
			}
		});
		tglbtnEmail.setBounds(858, 132, 119, 35);
		frame.getContentPane().add(tglbtnEmail);
		
		JLabel lblAes = new JLabel("A\u00E7\u00F5es");
		lblAes.setBounds(21, 495, 92, 26);
		frame.getContentPane().add(lblAes);
		
		btnResponder = new JButton("Responder");
		btnResponder.setBounds(21, 532, 141, 35);
		frame.getContentPane().add(btnResponder);
		
		btnRetweetar = new JButton("ReTweetar");
		btnRetweetar.setBounds(188, 532, 141, 35);
		frame.getContentPane().add(btnRetweetar);
		
		btnComentar = new JButton("Comentar");
		btnComentar.setBounds(350, 532, 141, 35);
		frame.getContentPane().add(btnComentar);
		
		JButton btnConfiguraes = new JButton("Configura\u00E7\u00F5es");
		btnConfiguraes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Configuracoes.main(null);
			}
		});
		btnConfiguraes.setBounds(782, 532, 165, 35);
		frame.getContentPane().add(btnConfiguraes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(350, 74, 487, 400);
		frame.getContentPane().add(scrollPane);
			scrollPane.setViewportView(txtrArea);
		
			txtrArea.setText("Bem-vind@!");
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(21, 74, 308, 400);
			frame.getContentPane().add(scrollPane_1);
			scrollPane_1.setViewportView(list);
			
			JToggleButton tglbtnTwitter = new JToggleButton("Twitter");
			tglbtnTwitter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(tglbtnTwitter.isSelected()) {
						//list.removeListSelectionListener(lsl);
					mudaRespostas(tweet.getList("@ISCTEIUL"));
					}
					else {
						list.removeListSelectionListener(lsl);
						txtrArea.setText(null);
						int size= P.getSize();
						
						for(int i=size-1; i>=0;i--) {
							GeneralMessage a=P.getElementAt(i);
							if(a.getType()==GeneralMessage.TWITTER) {
								P.removeElementAt(i);
							}
						}
					}
				}
			});
			tglbtnTwitter.setBounds(858, 20, 119, 35);
			frame.getContentPane().add(tglbtnTwitter);
	}
	

	public void pesquisar(String p){
		search_list.clear();
		System.out.println(p);
		for(Object o:P.toArray()){
				if(o.toString().contains(p) ){
					search_list.addElement((GeneralMessage) o);
					break;
				}
		}	
		list.setModel(search_list);	
	}

	
public void mudaRespostas(ArrayList<GeneralMessage> M) {
	lsl = new ListSelectionListener() {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			int size= P.getSize();
			
			for(int i=size-1; i>=0;i--) {
				GeneralMessage a=P.getElementAt(i);
				if(list.getSelectedValue()==a) {
					txtrArea.setText(a.getBody());
				}
			}
			if(list.getSelectedValue()!=null) {
			if(list.getSelectedValue().getType()==GeneralMessage.TWITTER) {
				btnComentar.setEnabled(false);
				btnResponder.setEnabled(false);
				btnRetweetar.setEnabled(true);
			}
			if(list.getSelectedValue().getType()==GeneralMessage.FACEBOOK) {
				btnComentar.setEnabled(true);
				btnResponder.setEnabled(false);
				btnRetweetar.setEnabled(false);
			}
			if(list.getSelectedValue().getType()==GeneralMessage.EMAIL) {
				btnComentar.setEnabled(false);
				btnResponder.setEnabled(true);
				btnRetweetar.setEnabled(false);
			}
			}
		}
	};
		if (M.isEmpty()) {
			//P.addElement("Sem Resultados");
		} else {
			
			for (GeneralMessage R : M) {
				P.addElement(R);
			}
			list.setModel(P);
			list.addListSelectionListener(lsl);
		}
}
	
	
	
}
