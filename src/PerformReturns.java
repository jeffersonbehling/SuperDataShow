import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

public class PerformReturns extends JFrame {

	private JPanel contentPane;
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnHome = new JMenu("Home");
	private final JLabel lblNewLabel = new JLabel("");
	private final JLabel lblClient = new JLabel("Cliente");
	private final JTextField txtClientName = new JTextField();
	private final JLabel lblLocations = new JLabel("Loca\u00E7\u00F5es");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable table = new JTable();
	private final JButton btnRenew = new JButton("Renovar");
	private final JButton btnReturned = new JButton("Devolver");
	private DefaultTableModel model;
	private Helpers helpers = new Helpers();
	private final JMenu mnCadastros = new JMenu("Cadastros");
	private final JMenuItem mnClients = new JMenuItem("Clientes");
	private final JMenuItem mnProjectors = new JMenuItem("Projetores");
	private final JMenu mnConsultas = new JMenu("Consultas");
	private final JMenuItem mnClient = new JMenuItem("Clientes");
	private final JMenuItem mnProjector = new JMenuItem("Projetores");
	private final JMenu mnLocao = new JMenu("Loca\u00E7\u00E3o");
	private final JMenuItem mnLocation = new JMenuItem("Locar");
	private final JMenuItem mnReturn = new JMenuItem("Devolver");
	private final JMenuItem mnRenew = new JMenuItem("Renovar");
	private final JMenu mnExit = new JMenu("Sair");
	private Database database;

	/**
	 * Create the frame.
	 */
	public PerformReturns(Database database) {
		this.database = database;
		setResizable(false);
		btnReturned.setIcon(new ImageIcon("resource//back.png"));
		btnReturned.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		btnRenew.setIcon(new ImageIcon("resource//refresh.png"));
		btnRenew.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblLocations.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtClientName.setToolTipText("Pesquise pelo nome...");
		txtClientName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtClientName.setColumns(10);
		lblClient.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("resource//projector.png"));
		initComponents();
		actions();
		actionsMenu();
	}
	
	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("resource//IFFarSoft.png"));
		setTitle("Efetuar Devolu\u00E7\u00E3o");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 90, 850, 600);
		
		setJMenuBar(menuBar);
		
		menuBar.add(mnHome);
		
		menuBar.add(mnCadastros);
		mnClients.setIcon(new ImageIcon("resource//user-silhouette.png"));
		
		mnCadastros.add(mnClients);
		mnProjectors.setIcon(new ImageIcon("resource//projector-item.png"));
		
		mnCadastros.add(mnProjectors);
		
		menuBar.add(mnConsultas);
		mnClient.setIcon(new ImageIcon("resource//magnifier.png"));
		
		mnConsultas.add(mnClient);
		mnProjector.setIcon(new ImageIcon("resource//magnifier.png"));
		
		mnConsultas.add(mnProjector);
		
		menuBar.add(mnLocao);
		mnLocation.setIcon(new ImageIcon("resource//projector-screen.png"));
		
		mnLocao.add(mnLocation);
		mnReturn.setIcon(new ImageIcon("resource//back-arrow.png"));
		
		mnLocao.add(mnReturn);
		mnRenew.setIcon(new ImageIcon("resource//refresh-page-arrow-button.png"));
		
		mnLocao.add(mnRenew);
		
		menuBar.add(mnExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(66)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 708, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtClientName, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblClient)
						.addComponent(lblLocations, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(60, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(290, Short.MAX_VALUE)
					.addComponent(btnRenew, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnReturned)
					.addGap(280))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(lblClient)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtClientName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblLocations, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnReturned, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRenew))
					.addContainerGap(85, Short.MAX_VALUE))
		);
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Cliente", "Projetor", "Nro. S\u00E9rie", "Data de Loca\u00E7\u00E3o", "Data de Devolu\u00E7\u00E3o", "Pre\u00E7o"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.getColumnModel().getColumn(5).setPreferredWidth(110);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void actions() {
		txtClientName.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				if (txtClientName.getText().length() > 0) {
					ResultSet rs = database.searchClientLocationByName(txtClientName.getText().toString());
					model = (DefaultTableModel) table.getModel();
					
					try {
						if (rs != null) {
							helpers.clearTable(model);
							while (rs.next()) {
								model.addRow(new String[] {rs.getString("l.id"), rs.getString("name"), rs.getString("brand") + " - " + rs.getString("model"), rs.getString("serial_number"), rs.getString("location_date"), rs.getString("devolution_date"), rs.getString("price")});
							}
						}
					} catch (SQLException e) {
						System.out.println("Error: "+ e.getMessage());
					}
				}
			}
		});
		
		btnRenew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedColumn() >= 0) {
					int row = table.getSelectedRow();
					int locationId = Integer.parseInt(table.getValueAt(row, 0).toString());
					RenewLocation renewLocation = new RenewLocation(database, locationId);
					renewLocation.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um cliente primeiro");
				}
			}
		});
		
		btnReturned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedColumn() >= 0) {
					int row = table.getSelectedRow();
					int locationId = Integer.parseInt(table.getValueAt(row, 0).toString());
					boolean wasReturned = database.wasReturned(locationId);
					
					if (wasReturned) {
						JOptionPane.showMessageDialog(null, "A devolução foi realizada com sucesso.", "Devolução realizada", JOptionPane.INFORMATION_MESSAGE);
						
						ResultSet rs = database.searchClientLocationByName(txtClientName.getText().toString());
						model = (DefaultTableModel) table.getModel();
						
						try {
							if (rs != null) {
								helpers.clearTable(model);
								while (rs.next()) {
									model.addRow(new String[] {rs.getString("l.id"), rs.getString("name"), rs.getString("brand") + " - " + rs.getString("model"), rs.getString("serial_number"), rs.getString("location_date"), rs.getString("devolution_date"), rs.getString("price")});
								}
							}
						} catch (SQLException e) {
							System.out.println("Error: "+ e.getMessage());
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "Falha na devolução do Projetor.", "Falha na devolução", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um cliente primeiro");
				}
			}
		});
	}
	
	private void actionsMenu() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				database.disconnect();
			}
		});
		
		mnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Actions actions = new Actions(database);
				actions.setVisible(true);
				dispose();
			}
		});
		
		mnClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientsRegister clientsRegister;
				try {
					clientsRegister = new ClientsRegister(database);
					clientsRegister.setVisible(true);
					dispose();
				} catch (SQLException e) {
					System.out.println("Error: " + e.getMessage());
				}
				
			}
		});
		
		mnProjectors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProjectorsRegister projectorsRegister = new ProjectorsRegister(database);
				projectorsRegister.setVisible(true);
				dispose();
			}
		});
		
		mnLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocationsRegister locationsRegister = new LocationsRegister(database);
				locationsRegister.setVisible(true);
				dispose();
			}
		});
		
		mnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PerformReturns performReturns = new PerformReturns(database);
				performReturns.setVisible(true);
				dispose();
			}
		});
		
		mnRenew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PerformReturns performReturns = new PerformReturns(database);
				performReturns.setVisible(true);
				dispose();
			}
		});
		
		mnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientsSearch clientsSearch = new ClientsSearch(database);
				clientsSearch.setVisible(true);
				dispose();
			}
		});
		
		mnProjector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProjectorsSearch projectorsSearch = new ProjectorsSearch(database);
				projectorsSearch.setVisible(true);
				dispose();
			}
		});
		
		mnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
	}
}
