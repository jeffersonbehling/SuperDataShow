import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.SendingContext.RunTime;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ErrorConnectToDatabase extends JFrame {

	private JPanel contentPane;
	private JLabel lblErrorConnect = new JLabel("");
	private JLabel lblMessage = new JLabel("O servi\u00E7o do MySQL deve estar ativo para utilizar o sistema");
	private JLabel lblClickButton = new JLabel("Clique no bot\u00E3o abaixo para iniciar o servi\u00E7o");
	private JButton btnInitMysql = new JButton("Iniciar");
	private Helpers helpers = new Helpers();
	private Database database;

	/**
	 * Create the frame.
	 */
	public ErrorConnectToDatabase(Database database) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resource//IFFarSoft.png"));
		setTitle("Servidor MySQL");
		this.database = database;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 90, 850, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		lblErrorConnect.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblErrorConnect.setIcon(new ImageIcon("resource//database.png"));
		lblMessage.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblClickButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		btnInitMysql.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		btnInitMysql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblErrorConnect.setIcon(new ImageIcon("resource//gif.gif"));
				
				if (btnInitMysql.getText().equals("Iniciar")) {
					helpers.initMysql();
					
					btnInitMysql.setText("Página Inicial");
				} else {
					Home home = new Home(database);
					home.setVisible(true);
					dispose();
				}
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblErrorConnect, GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMessage)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(39)
									.addComponent(lblClickButton)
									.addPreferredGap(ComponentPlacement.RELATED, 56, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(140)
									.addComponent(btnInitMysql)
									.addPreferredGap(ComponentPlacement.RELATED, 155, GroupLayout.PREFERRED_SIZE)))
							.addGap(224))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblErrorConnect, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addGap(71)
					.addComponent(lblMessage)
					.addGap(18)
					.addComponent(lblClickButton)
					.addGap(18)
					.addComponent(btnInitMysql)
					.addContainerGap(208, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
