import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class RenewLocation extends JFrame {

	private JPanel contentPane;
	private int locationId;
	private final JLabel lblNewLabel = new JLabel("");
	private final JLabel lblDevolutionDate = new JLabel("Data de Devolu\u00E7\u00E3o");
	private final JDateChooser txtDevolutionDate = new JDateChooser();
	private final JLabel lblPrice = new JLabel("Pre\u00E7o");
	private final JTextField txtPrice = new JTextField();
	private final JButton btnSave = new JButton("  Salvar");
	private final JLabel label = new JLabel("Developed by IFFarSoft");
	private final JButton btnExit = new JButton("  Sair");
	private Database database;

	/**
	 * Create the frame.
	 */
	public RenewLocation(Database database, int locationId) {
		this.database = database;
		setResizable(false);
		this.locationId = locationId;
		btnExit.setIcon(new ImageIcon("resource//error.png"));
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.GRAY);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnSave.setIcon(new ImageIcon("resource//save.png"));
		btnSave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtPrice.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtPrice.setColumns(10);
		lblPrice.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDevolutionDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("resource//refresh64.png"));
		initComponents();
		actions();
	}
	
	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("resource//IFFarSoft.png"));
		setTitle("Renovar Projetor");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
				.addComponent(label, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(120)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDevolutionDate)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(txtDevolutionDate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblPrice, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addComponent(btnSave)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
							.addComponent(txtPrice, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)))
					.addContainerGap(98, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDevolutionDate)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtDevolutionDate, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void actions() {
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtDevolutionDate.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Preencha a Data de Devolução, por favor.", "Dados Inválidos", JOptionPane.ERROR_MESSAGE);
					txtDevolutionDate.requestFocus();
				} else {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String devolutionDate = dateFormat.format(txtDevolutionDate.getDate());
					Date locationDate = new Date();
					String location = dateFormat.format(locationDate);
					float price = 0;
					if (txtPrice.getText() != null) {
						price =  Float.parseFloat(txtPrice.getText().toString());
					}
					boolean wasRenew = database.renewLocation(locationId, location, devolutionDate, price);
					if (wasRenew) {
						JOptionPane.showMessageDialog(null, "Renovação realizada com sucesso", "Renovação Realizada", JOptionPane.INFORMATION_MESSAGE);
						PerformReturns performReturns = new PerformReturns(database);
						performReturns.setVisible(true);
						dispose();
					}
				}
			}
		});
	}
}
