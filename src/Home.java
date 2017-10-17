import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPasswd;
	private JButton btnLogin = new JButton("Entrar");
	public static Database database;

	private static String user = "admin";
	private static String password = "admin";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					database = new Database();
					if (!database.connect()) {
						ErrorConnectToDatabase errorConnectToDatabase = new ErrorConnectToDatabase(database);
						errorConnectToDatabase.setVisible(true);
					} else {
						Home home = new Home(database);
						home.setVisible(true);
					}
				} catch (Exception e) {
					System.out.print("Erro: " +e.getMessage());
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Home(Database database) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("resource//IFFarSoft.png"));
		setTitle("Login");
		this.database = database;
		
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				database.disconnect();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 90, 850, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		mnAjuda.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnAjuda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Seja Bem-Vindo \n\n" + "Para acessar o sistema utilize:\n\n" +"Usuário: admin\n" +"Senha: admin");
			}
		});
		menuBar.add(mnAjuda);
		
		JMenu mnSair = new JMenu("Sair");
		mnSair.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				database.disconnect();
				dispose();
			}
		});
		
		menuBar.add(mnSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("resource//projetor128.png"));
		
		JLabel lblUsername = new JLabel("Usu\u00E1rio");
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtUsername.setColumns(10);
		
		JLabel lblPasswrd = new JLabel("Senha");
		lblPasswrd.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verifyCredentials();
			}
		});
		btnLogin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtPasswd = new JPasswordField();
		txtPasswd.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(293)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPasswrd)
								.addComponent(lblUsername)
								.addComponent(txtPasswd, 233, 233, 233)
								.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(373)
							.addComponent(btnLogin)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblUsername)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPasswrd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtPasswd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnLogin)
					.addContainerGap(164, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		actions();
	}
	
	public void verifyCredentials(){
		String passwordGet = new String(txtPasswd.getPassword());
		
		if (txtUsername.getText().equals(user) && passwordGet.equals(password)) {
			Actions actions = new Actions(database);
			actions.setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos", "Credenciais erradas", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void actions() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				database.disconnect();
			}
		});
	}
}