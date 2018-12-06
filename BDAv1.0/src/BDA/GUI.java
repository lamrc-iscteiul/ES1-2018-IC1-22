package BDA;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.mail.MessagingException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import twitter4j.Status;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class GUI {

	private JFrame frame;
	private JTextField textField;
	JList<GeneralMessage> list = new JList<>();
	JTextArea txtrArea = new JTextArea();
	DefaultListModel<GeneralMessage> P = new DefaultListModel<GeneralMessage>();
	ListSelectionListener lsl;
	FaceAPI face;
	TwitterAPI tweet;
	EmailAPI mail;
	public ListSelectionListener getLsl() {
		return lsl;
	}

	DefaultListModel<GeneralMessage> search_list = new DefaultListModel<GeneralMessage>();
	JButton btnRetweetar;
	JButton btnComentar;
	JButton btnResponder;
	JButton tglbtnLike;
	//Configuracoes configuracoes= new Configuracoes();
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
	tweet = new TwitterAPI();
	mail = new EmailAPI();
	face = new FaceAPI();
	Configuracoes.init();
	frame = new JFrame();
	frame.setTitle("BDA Project");
	frame.setBounds(100, 100, 1024, 710);
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
				if(a.getType()==GeneralMessage.FACEBOOK) {
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
	btnResponder.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		try {
			responderMail(Configuracoes.getText_email_user(),list.getSelectedValue().toString(),"RE: " + list.getSelectedValue().getMessage().getSubject());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	});
	btnResponder.setBounds(21, 532, 141, 35);
	frame.getContentPane().add(btnResponder);
	
	btnRetweetar = new JButton("ReTweetar");
	btnRetweetar.setBounds(183, 588, 141, 35);
	frame.getContentPane().add(btnRetweetar);
	btnRetweetar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tweet.retweet(list.getSelectedValue().getStatus());
		}
	});
	
	btnComentar = new JButton("Comentar");
	btnComentar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int type = list.getSelectedValue().getType();
			switch(type) {
			
			case GeneralMessage.TWITTER:
				comentaTwitter();
				break;
			case GeneralMessage.FACEBOOK:
			comentaFacebook();
			break;
			}
		}
	});
	btnComentar.setBounds(183, 532, 141, 35);
	frame.getContentPane().add(btnComentar);
	
	JButton btnConfiguraes = new JButton("Configura\u00E7\u00F5es");
	btnConfiguraes.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Configuracoes.visible(true);;
		}
	});
	btnConfiguraes.setBounds(812, 588, 165, 35);
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
		
		tglbtnLike = new JButton("Like");
		tglbtnLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int type = list.getSelectedValue().getType();
				switch(type) {
				
				case GeneralMessage.TWITTER:
					tweet.favorite(list.getSelectedValue().getStatus());
					break;
				case GeneralMessage.FACEBOOK:
				face.like(list.getSelectedValue().getPost());
				break;
				
				}
			}
		});
		tglbtnLike.setBounds(21, 588, 141, 35);
		frame.getContentPane().add(tglbtnLike);
		
		JButton btnNovoTweet = new JButton("Novo Tweet");
		btnNovoTweet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novoTweet();
			}
		});
		btnNovoTweet.setBounds(345, 532, 148, 91);
		frame.getContentPane().add(btnNovoTweet);
		
		JButton btnNovoPost = new JButton("Novo Post");
			btnNovoPost.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					novoPost();
					
				}
			});
			btnNovoPost.setBounds(514, 532, 148, 91);
			frame.getContentPane().add(btnNovoPost);
			btnRetweetar.setEnabled(false);
			btnComentar.setEnabled(false);
			btnResponder.setEnabled(false);
			tglbtnLike.setEnabled(false);
			
			JLabel lblOrdenar = new JLabel("Ordem:");
			lblOrdenar.setBounds(858, 188, 92, 26);
			frame.getContentPane().add(lblOrdenar);
			
			JComboBox<String> comboBox = new JComboBox();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(comboBox.getSelectedItem().equals("Crescente")) {
					//codigo	
					}else {
						//codigo
					}
				}
			});
			comboBox.setBounds(858, 220, 119, 32);
			frame.getContentPane().add(comboBox);
			comboBox.addItem("Crescente");
			comboBox.addItem("Decrescente");
	}
	

	public void pesquisar(String p){
		if(p.isEmpty()) {
			list.setModel(P);
		}else {
			
		
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
	}

	
public void mudaRespostas(ArrayList<GeneralMessage> M) {
lsl = new ListSelectionListener() {

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		
		
		int size= P.getSize();
		
		for(int i=size-1; i>=0;i--) {
			GeneralMessage a = P.getElementAt(i);
			if(list.getSelectedValue()==a) {
				int type = a.getType();
				switch(type) {
				
				case GeneralMessage.TWITTER:
					txtrArea.setText(TwitterAPI.statusToString(a.getStatus()));
					break;
				case GeneralMessage.EMAIL:
					try {
						txtrArea.setText(EmailAPI.messageToString(a.getMessage()));
					} catch (MessagingException | IOException e) {
						e.printStackTrace();
					}
					break;
				case GeneralMessage.FACEBOOK:
					txtrArea.setText(FaceAPI.postToString(a.getPost()));
					break;
				}
//					txtrArea.setText(a.getBody());
			}
		}
		if(list.getSelectedValue()!=null) {
		if(list.getSelectedValue().getType()==GeneralMessage.TWITTER) {
			btnComentar.setEnabled(true);
			btnResponder.setEnabled(false);
			btnRetweetar.setEnabled(true);
			tglbtnLike.setEnabled(true);
		}
		if(list.getSelectedValue().getType()==GeneralMessage.FACEBOOK) {
			btnComentar.setEnabled(true);
			btnResponder.setEnabled(false);
			btnRetweetar.setEnabled(false);
			tglbtnLike.setEnabled(true);
			
		}
		if(list.getSelectedValue().getType()==GeneralMessage.EMAIL) {
			btnComentar.setEnabled(false);
			btnResponder.setEnabled(true);
			btnRetweetar.setEnabled(false);
			tglbtnLike.setEnabled(false);
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

		public Component getFrame() {
	// TODO Auto-generated method stub
	return frame;
}

		public JList<GeneralMessage> getList() {
	// TODO Auto-generated method stub
	return list;
}

		public void novoPost() {
	JFrame newPost = new JFrame();
	newPost.setResizable(false);
	newPost.setVisible(true);
	newPost.setBounds(100, 100, 450, 400);
	newPost.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	newPost.getContentPane().setLayout(null);
	
	JLabel lblNovoPostfacebook = new JLabel("Novo post (Facebook)");
lblNovoPostfacebook.setBounds(21, 21, 247, 26);
newPost.getContentPane().add(lblNovoPostfacebook);

JTextPane textPane = new JTextPane();
textPane.setBounds(21, 65, 402, 187);
newPost.getContentPane().add(textPane);

JButton btnPublicar = new JButton("Publicar");
	btnPublicar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			face.novoPost(textPane.getText());
			newPost.dispose();
		}
	});
	btnPublicar.setBounds(282, 273, 141, 35);
	newPost.getContentPane().add(btnPublicar);
}

		public void comentaFacebook() {
	JFrame newComment = new JFrame();
	newComment.setResizable(false);
	newComment.setVisible(true);
	newComment.setBounds(100, 100, 450, 400);
	newComment.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	newComment.getContentPane().setLayout(null);
	
	JLabel lblNovoCommentfacebook = new JLabel("Novo Comment (Facebook)");
lblNovoCommentfacebook.setBounds(21, 21, 247, 26);
newComment.getContentPane().add(lblNovoCommentfacebook);

JTextPane textPane = new JTextPane();
textPane.setBounds(21, 65, 402, 187);
newComment.getContentPane().add(textPane);

JButton btnPublicar = new JButton("Publicar");
	btnPublicar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			face.comenta(list.getSelectedValue().getPost(), textPane.getText());
			newComment.dispose();
		}
	});
	btnPublicar.setBounds(282, 273, 141, 35);
	newComment.getContentPane().add(btnPublicar);
}

		public void comentaTwitter() {
	JFrame newCommentTwitter = new JFrame();
	newCommentTwitter.setResizable(false);
	newCommentTwitter.setVisible(true);
	newCommentTwitter.setBounds(100, 100, 450, 400);
	newCommentTwitter.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	newCommentTwitter.getContentPane().setLayout(null);
	
	JLabel lblNovoCommentfacebook = new JLabel("Novo Comment (Twitter)");
lblNovoCommentfacebook.setBounds(21, 21, 247, 26);
newCommentTwitter.getContentPane().add(lblNovoCommentfacebook);

JTextPane textPane = new JTextPane();
textPane.setBounds(21, 65, 402, 187);
newCommentTwitter.getContentPane().add(textPane);

JButton btnPublicar = new JButton("Publicar");
	btnPublicar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tweet.reply(list.getSelectedValue().getStatus(), textPane.getText());
			newCommentTwitter.dispose();
		}
	});
	btnPublicar.setBounds(282, 273, 141, 35);
	newCommentTwitter.getContentPane().add(btnPublicar);
}


		public void novoTweet() {
	JFrame newTweet = new JFrame();
	newTweet.setResizable(false);
	newTweet.setVisible(true);
	newTweet.setBounds(100, 100, 450, 400);
	newTweet.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	newTweet.getContentPane().setLayout(null);
	
	JLabel lblNovoPostfacebook = new JLabel("Novo Tweet");
lblNovoPostfacebook.setBounds(21, 21, 247, 26);
newTweet.getContentPane().add(lblNovoPostfacebook);

JTextPane textPane = new JTextPane();
textPane.setBounds(21, 65, 402, 187);
newTweet.getContentPane().add(textPane);

JButton btnPublicar = new JButton("Tweetar");
	btnPublicar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			tweet.tweetar(textPane.getText());
			newTweet.dispose();
		}
	});
	btnPublicar.setBounds(282, 273, 141, 35);
	newTweet.getContentPane().add(btnPublicar);
}

public void responderMail(String de, String para, String ass) {
	JTextField textDe = new JTextField();
	JTextField textPara = new JTextField();
	JTextField txtRe = new JTextField();
	JTextPane reply_text;
	frame = new JFrame();
	frame.setVisible(true);
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
	textDe.setText(de);
	
	txtRe.setText(ass);
	txtRe.setBounds(112, 115, 291, 32);
	frame.getContentPane().add(txtRe);
	txtRe.setColumns(10);
	
	textDe.setBounds(85, 18, 318, 32);
	frame.getContentPane().add(textDe);
	textDe.setColumns(10);
	textPara.setText(para);
	textPara.setEditable(false);
	
	textPara.setBounds(85, 68, 318, 32);
	frame.getContentPane().add(textPara);
	textPara.setColumns(10);
	
//	JTextPane textPane = new JTextPane();
	reply_text = new JTextPane();
	reply_text.setBounds(21, 156, 382, 196);
	frame.getContentPane().add(reply_text);
	
	JButton btnEnviar = new JButton("Enviar");
	btnEnviar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				mail.enviarMail(textDe.getText(), textPara.getText(), txtRe.getText(), reply_text.getText());
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
	
}
}
