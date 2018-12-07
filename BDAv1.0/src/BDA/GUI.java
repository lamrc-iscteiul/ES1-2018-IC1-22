package BDA;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.bind.JAXBException;

import twitter4j.Status;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class GUI {

	private JFrame frame;
	private JTextField textField;
	private Configuracoes config_page;
	private JList<GeneralMessage> list;
	private JTextArea txtrArea;
	private DefaultListModel<GeneralMessage> model;
	private ArrayList<GeneralMessage> original_list;
	private ListSelectionListener lsl;
	private FaceAPI face;
	private TwitterAPI tweet;
	private EmailAPI mail;
	private DefaultListModel<GeneralMessage> search_list;
	private JButton btnRetweetar;
	private JButton btnComentar;
	private JButton btnResponder;
	private JButton tglbtnLike;
	

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
	@SuppressWarnings("unchecked")
	private void initialize() {
		tweet = new TwitterAPI();
		mail = new EmailAPI();
		face = new FaceAPI();
//		Configuracoes.init();
		config_page = new Configuracoes();
		configXML c = config_page.getConfigs();
		System.out.println(c.getFiltros().getChckbxElearning());

		search_list = new DefaultListModel<GeneralMessage>();
		model = new DefaultListModel<GeneralMessage>();
		txtrArea = new JTextArea();
		list = new JList<>();
		original_list = new ArrayList<GeneralMessage>();

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
		btnPesquisar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pesquisar(textField.getText());

			}

		});

		JToggleButton tglbtnFacebook = new JToggleButton("Facebook");
		tglbtnFacebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tglbtnFacebook.isSelected()) {
					mudaRespostas(face.getList(), GeneralMessage.FACEBOOK);
				} else {
					list.removeListSelectionListener(lsl);
					txtrArea.setText(null);
					int size = model.getSize();

					for (int i = size - 1; i >= 0; i--) {
						GeneralMessage a = model.getElementAt(i);
						if (a.getType() == GeneralMessage.FACEBOOK) {
							model.removeElementAt(i);
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
				if (tglbtnEmail.isSelected()) {
					mudaRespostas(mail.getList(), GeneralMessage.EMAIL);
				} else {
					list.removeListSelectionListener(lsl);
					txtrArea.setText(null);
					int size = model.getSize();

					for (int i = size - 1; i >= 0; i--) {
						GeneralMessage a = model.getElementAt(i);
						if (a.getType() == GeneralMessage.EMAIL) {
							model.removeElementAt(i);
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
					responderMail(config_page.getText_email_user(), list.getSelectedValue().toString(),
							"RE: " + list.getSelectedValue().getMessage().getSubject());
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
				switch (type) {

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
				config_page.visible(true);
				;
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
				if (tglbtnTwitter.isSelected()) {
					// list.removeListSelectionListener(lsl);
					mudaRespostas(tweet.getList("@ISCTEIUL"), GeneralMessage.TWITTER);
				} else {
					list.removeListSelectionListener(lsl);
					txtrArea.setText(null);
					int size = model.getSize();

					for (int i = size - 1; i >= 0; i--) {
						GeneralMessage a = model.getElementAt(i);
						if (a.getType() == GeneralMessage.TWITTER) {
							model.removeElementAt(i);
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
				switch (type) {

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

		JLabel lblCritrio = new JLabel("Crit\u00E9rio:");
		lblCritrio.setBounds(858, 188, 92, 26);
		frame.getContentPane().add(lblCritrio);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = comboBox_1.getSelectedItem().toString();

				switch (s) {
				case "Data":
					ordenarDate();
					break;
				case "Alfabética":
					ordenarString();
					break;
				}
			}
		});
		comboBox_1.setBounds(858, 218, 119, 32);
		frame.getContentPane().add(comboBox_1);
		comboBox_1.addItem("Data");
		comboBox_1.addItem("Alfabética");
		
		if(!netIsAvailable()) {
			try {
				JOptionPane.showMessageDialog(null, "Sem ligação à internet :(");
				leServicos();
				tglbtnTwitter.setEnabled(false);
				tglbtnEmail.setEnabled(false);
				tglbtnFacebook.setEnabled(false);
				
				
				
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Given a String, represents on the GUI the GeneralMessages correspondent
	 * to that given String
	 * 
	 * @param p
	 *            String
	 */
	public void pesquisar(String p) {
		if (p.isEmpty()) {
			list.setModel(model);
		} else {
			search_list.clear();
			System.out.println(p);
			for (Object o : model.toArray()) {
				if (o.toString().toLowerCase().contains(p.toLowerCase())) {
					search_list.addElement((GeneralMessage) o);
				}
			}
			list.setModel(search_list);
		}
	}

	/**
	 * Given a ArrayList<GeneralMessage>, writes on the GUI
	 * 
	 * @param M
	 *            ArrayList<GeneralMessage>
	 */
	public void mudaRespostas(ArrayList<GeneralMessage> target_list, int button_type) {

		ArrayList<GeneralMessage> new_original_list = new ArrayList<GeneralMessage>();
		
		ArrayList<GeneralMessage> M = applyFilters(target_list);
		
		switch(button_type) {
		
		case GeneralMessage.TWITTER:
			for(GeneralMessage message : original_list) {
				if(!(message.getType()==GeneralMessage.TWITTER))
					new_original_list.add(message);
			}
			break;
			
		case GeneralMessage.EMAIL:
			for(GeneralMessage message : original_list) {
				if(!(message.getType()==GeneralMessage.EMAIL))
					new_original_list.add(message);
			}
			break;
			
		case GeneralMessage.FACEBOOK:
			for(GeneralMessage message : original_list) {
				if(!(message.getType()==GeneralMessage.FACEBOOK))
					new_original_list.add(message);
			}
			break;
		}

		lsl = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {

				int size = model.getSize();

				for (int i = size - 1; i >= 0; i--) {
					GeneralMessage a = model.getElementAt(i);
					if (list.getSelectedValue() == a) {
						int type = a.getType();
						switch (type) {

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
						// txtrArea.setText(a.getBody());
					}
				}
				if (list.getSelectedValue() != null) {
					if (list.getSelectedValue().getType() == GeneralMessage.TWITTER) {
						btnComentar.setEnabled(true);
						btnResponder.setEnabled(false);
						btnRetweetar.setEnabled(true);
						tglbtnLike.setEnabled(true);
					}
					if (list.getSelectedValue().getType() == GeneralMessage.FACEBOOK) {
						btnComentar.setEnabled(true);
						btnResponder.setEnabled(false);
						btnRetweetar.setEnabled(false);
						tglbtnLike.setEnabled(true);

					}
					if (list.getSelectedValue().getType() == GeneralMessage.EMAIL) {
						btnComentar.setEnabled(false);
						btnResponder.setEnabled(true);
						btnRetweetar.setEnabled(false);
						tglbtnLike.setEnabled(false);
					}
				}
			}
		};
		if (M.isEmpty()) {
			// P.addElement("Sem Resultados");
		} else {

			for (GeneralMessage R : M) {
				new_original_list.add(R);
				model.addElement(R);
			}
			list.setModel(model);
			list.addListSelectionListener(lsl);
		}
		
		original_list = new_original_list;
		
		try {
			guardaServicos();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Returns GUI frame
	 * 
	 * @return frame
	 */
	public Component getFrame() {
		// TODO Auto-generated method stub
		return frame;
	}

	/**
	 * Returns JList of GeneralMessage
	 * 
	 * @return JList
	 */
	public JList<GeneralMessage> getList() {
		// TODO Auto-generated method stub
		return list;
	}

	/**
	 * Opens a frame where a new Facebook post can be made
	 */
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

	/**
	 * Opens a new frame in order to submit a new Facebook comment
	 */
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

	/**
	 * Opens a new frame in order to submit a new Twitter comment
	 */
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

	/**
	 * Opens a frame where a new Tweet can be made
	 */
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

	/**
	 * Opens a frame where a reply to a e-mail can be made
	 * 
	 * @param de
	 *            String
	 * @param para
	 *            String
	 * @param ass
	 *            String
	 */

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

		// JTextPane textPane = new JTextPane();
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

	public void ordenarString() {
		ArrayList<GeneralMessage> l = new ArrayList<GeneralMessage>();
		int aux = model.getSize();
		for (int i = 0; i < aux; i++) {
			GeneralMessage s = model.getElementAt(i);
			l.add(s);
		}
		Collections.sort(l, GeneralMessage.ComparadorString);
		model.clear();
		for (int i = 0; i < aux; i++) {
			GeneralMessage s = l.get(i);
			model.addElement(s);
		}
	}

	public void ordenarDate() {
		ArrayList<GeneralMessage> l = new ArrayList<GeneralMessage>();
		int aux = model.getSize();
		for (int i = 0; i < aux; i++) {
			GeneralMessage s = model.getElementAt(i);
			l.add(s);
		}
		Collections.sort(l);
		Collections.reverse(l);
		model.clear();
		for (int i = 0; i < aux; i++) {
			GeneralMessage s = l.get(i);
			model.addElement(s);
		}
	}

	/**
	 * Gets a ListSelectionListener
	 * 
	 * @return ListSelectionListener
	 */
	public ListSelectionListener getLsl() {
		return lsl;
	}

	public ArrayList<GeneralMessage> applyFilters(ArrayList<GeneralMessage> list) {

		ArrayList<GeneralMessage> return_message = new ArrayList<GeneralMessage>();

		Filtros filtros = config_page.getConfigs().getFiltros();

		if (filtros.getChckbxTudo())
			return list;
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Date compare_date;
		
		System.out.println("Check for date!");
		if (filtros.getChckbxDias()) {
			calendar.add(Calendar.DATE, -7);
			compare_date = calendar.getTime();
		} else if (filtros.getChckbxh_1()) {
			calendar.add(Calendar.DATE, -2);
			compare_date = calendar.getTime();
		} else if (filtros.getChckbxh()){
			calendar.add(Calendar.DATE, -1);
			compare_date = calendar.getTime();
		} else {
			compare_date = new Date(Long.MIN_VALUE);
		}
		
		for (GeneralMessage m : list) {

			System.out.print("Comparing " + m.getDate().toString() + " with " + compare_date.toString() + "...");
			System.out.println(m.getDate().after(compare_date));
			
			if (m.getType() == GeneralMessage.TWITTER) {

				System.out.print("Comparing " + m.getStatus().getUser().getScreenName() + " with ISCTEIUL...");
				System.out.println(m.getStatus().getUser().getScreenName().equals("ISCTEIUL"));

				System.out.print("Comparing " + m.getStatus().getUser().getScreenName() + " with BibliotecaISCTE...");
				System.out.println(m.getStatus().getUser().getScreenName().equals("BibliotecaISCTE"));

				if (filtros.getChckbxIscteiul() 
						&& m.getStatus().getUser().getScreenName().equals("ISCTEIUL")
						&& m.getDate().after(compare_date))
					m.setSelected();
				else if (filtros.getChckbxBiblioteca()
						&& m.getStatus().getUser().getScreenName().equals("BibliotecaISCTE")
						&& m.getDate().after(compare_date))
					m.setSelected();
				else
					m.deselect();
			}

			if (m.getType() == GeneralMessage.EMAIL) {
				try {
					InternetAddress from = (InternetAddress) m.getMessage().getFrom()[0];
					String add = from.getAddress();
					String subject = m.getMessage().getSubject();

					System.out.print("Comparing " + subject + " with 2018...");
					System.out.println(subject.startsWith("2018"));

					System.out.print("Comparing " + add + " with geapc...");
					System.out.println(add.contains("geapq"));

					System.out.print("Comparing " + add + " with reitora...");
					System.out.println(add.contains("reitora"));

					System.out.print("Comparing " + add + " with tesouraria...");
					System.out.println(add.contains("tesouraria"));

					if (filtros.getChckbxElearning() && subject.startsWith("2018")
							&& m.getDate().after(compare_date))
						m.setSelected();
					else if (filtros.getChckbxFenix() && add.contains("geapq")
							&& m.getDate().after(compare_date))
						m.setSelected();
					else if (filtros.getChckbxReitora() && add.contains("reitora")
							&& m.getDate().after(compare_date))
						m.setSelected();
					else if (filtros.getChckbxTesouraria() && add.contains("tesouraria")
							&& m.getDate().after(compare_date))
						m.setSelected();
					else
						m.deselect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if (m.getType() == GeneralMessage.FACEBOOK && m.getDate().after(compare_date)) {
				m.setSelected();
			}
		}

		for (GeneralMessage m : list) {
			if (m.getSelected())
				return_message.add(m);
		}

		return return_message;

	}
	
	public void guardaServicos() throws IOException {
		FileOutputStream fileOut =
		         new FileOutputStream("file.dat");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         ArrayList<GeneralMessage> new_list = new  ArrayList<GeneralMessage>();
		         int size= model.getSize();
					
					for(GeneralMessage m : original_list) {
						if(!(m.getType() == GeneralMessage.EMAIL))
							new_list.add(m);
					}
		         out.writeObject(new_list);
		         out.close();
		         fileOut.close();
	}
	
	public void leServicos() throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream("file.dat");
	    ObjectInputStream in = new ObjectInputStream(fileIn);
	    ArrayList<GeneralMessage> M = new  ArrayList<GeneralMessage>();
	    M = (ArrayList<GeneralMessage>) in.readObject();
	    
	    mudaRespostas(M,-1);
	    
	    in.close();
	    fileIn.close();
	}
	
	/**
	 * Checks if there is a internet connection
	 * @return boolean
	 */
	private static boolean netIsAvailable() {
	    try {
	        final URL url = new URL("http://www.google.com");
	        final URLConnection conn = url.openConnection();
	        conn.connect();
	        conn.getInputStream().close();
	        return true;
	    } catch (MalformedURLException e) {
	        throw new RuntimeException(e);
	    } catch (IOException e) {
	        return false;
	    }
	}
}
