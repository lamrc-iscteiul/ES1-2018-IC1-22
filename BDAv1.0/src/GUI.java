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
 public class GUI {
 	private JFrame frame;
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
	}
 	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnEmail = new JMenu("E-Mail");
		menuBar.add(mnEmail);
		
		JMenuItem mntmTeste = new JMenuItem("Teste");
		mnEmail.add(mntmTeste);
		
		JMenu mnFacebook = new JMenu("Facebook");
		menuBar.add(mnFacebook);
		
		JMenu mnTwitter = new JMenu("Twitter");
		menuBar.add(mnTwitter);
		
		JMenu mnConfiguraes = new JMenu("Configura\u00E7\u00F5es");
		menuBar.add(mnConfiguraes);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.NORTH);
		splitPane.setDividerLocation(350);
		
		JLabel lblBomDiaAcademia = new JLabel("Bom Dia Academia");
		lblBomDiaAcademia.setHorizontalAlignment(SwingConstants.CENTER);
		splitPane.setLeftComponent(lblBomDiaAcademia);
		
		//Data
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate localDate = LocalDate.now();
				
		JLabel lblData = new JLabel(dtf.format(localDate));
		lblData.setHorizontalAlignment(SwingConstants.RIGHT);
		splitPane.setRightComponent(lblData);
 }}
