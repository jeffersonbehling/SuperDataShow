import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

public class About extends JFrame {

	private JPanel contentPane;
	private Database database;
	private JLabel label = new JLabel("");
	private JLabel lbl1 = new JLabel("Equipe Desenvolvedora");
	private JLabel lbl2 = new JLabel("Jefferson Vantuir Behling (jefferson.behling@gmail.com)");
	private JLabel lbl3 = new JLabel("Sander Mossi Migotto (sander.mm97@gmail.com)");
	private JLabel lbl4 = new JLabel("Desenvolvido utilizando Swing");
	private JLabel lbl5 = new JLabel("Banco de Dados Mysql");
	private JLabel lbl6 = new JLabel("Desenvolvimento de Logomarcas");
	private JLabel lbl7 = new JLabel("Douglas Felipe Kappaun (douglas.kappaun@hotmail.com)");
	private JLabel lbl8 = new JLabel("Copyright \u00A9 IFFarSoft - Todos os Direitos Reservados");

	/**
	 * Create the frame.
	 */
	public About(Database database) {
		setResizable(false);
		this.database = database;
		initComponents();
	}
	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("resource//IFFarSoft.png"));
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(250, 90, 850, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		label.setIcon(new ImageIcon("resource//projetor128.png"));
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lbl2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbl3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbl4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbl5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbl8.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lbl6.setHorizontalAlignment(SwingConstants.CENTER);
		lbl6.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lbl7.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(270)
							.addComponent(lbl8))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label, GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lbl6, GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(246, Short.MAX_VALUE)
					.addComponent(lbl7)
					.addGap(237))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl1, GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl4, GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl3, GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl2, GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl5, GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label)
					.addGap(18)
					.addComponent(lbl1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbl2)
					.addGap(8)
					.addComponent(lbl3)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lbl4)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lbl5)
					.addGap(36)
					.addComponent(lbl6)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbl7)
					.addPreferredGap(ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
					.addComponent(lbl8))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
