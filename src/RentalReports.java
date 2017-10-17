import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView.TableCell;

import com.mysql.fabric.xmlrpc.base.Data;
import com.sun.prism.paint.Color;

import javafx.scene.control.TableColumn;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

public class RentalReports extends JFrame {

	private JPanel contentPane;
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnHome = new JMenu("Home");
	private final JLabel lblNewLabel = new JLabel("");
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final JRadioButton rdbtnComplet = new JRadioButton("Completo");
	private final JRadioButton rdbtnMonthly = new JRadioButton("Mensal");
	private final JRadioButton rdbtnDaily = new JRadioButton("Di\u00E1rio");
	private final JRadioButton rdbtnArrears = new JRadioButton("Atrasados");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable table = new JTable();
	private DefaultTableModel model;
	private Helpers helpers = new Helpers();
	private Database database;
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

	/**
	 * Create the frame.
	 */
	public RentalReports(Database database) {
		setResizable(false);
		this.database = database;
		buttonGroup.add(rdbtnComplet);
		buttonGroup.add(rdbtnMonthly);
		buttonGroup.add(rdbtnDaily);
		buttonGroup.add(rdbtnArrears);
				
		rdbtnArrears.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		rdbtnDaily.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		rdbtnMonthly.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		rdbtnComplet.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("resource//newspaper.png"));
		initComponents();
		actions();
		actionsMenu();
	}
	private void initComponents() {
		setTitle("Relat\u00F3rios de Loca\u00E7\u00F5es");
		setIconImage(Toolkit.getDefaultToolkit().getImage("resource//IFFarSoft.png"));
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
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(rdbtnComplet)
							.addGap(18)
							.addComponent(rdbtnMonthly)
							.addGap(18)
							.addComponent(rdbtnDaily)
							.addGap(18)
							.addComponent(rdbtnArrears)
							.addGap(247))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 724, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(50, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnArrears)
						.addComponent(rdbtnDaily)
						.addComponent(rdbtnMonthly)
						.addComponent(rdbtnComplet))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(94, Short.MAX_VALUE))
		);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cliente", "Projetor", "Data de Loca\u00E7\u00E3o", "Data de Devolu\u00E7\u00E3o", "Pre\u00E7o", "Situa\u00E7\u00E3o"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(125);
		table.getColumnModel().getColumn(0).setMinWidth(125);
		table.getColumnModel().getColumn(1).setPreferredWidth(125);
		table.getColumnModel().getColumn(1).setMinWidth(125);
		table.getColumnModel().getColumn(2).setPreferredWidth(105);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(105);
		table.getColumnModel().getColumn(3).setMinWidth(105);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void actions() {
		rdbtnComplet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = database.locationsReport(1);
				model = (DefaultTableModel) table.getModel();
				try {
					helpers.clearTable(model);
					while(rs.next()) {
						if (rs.getInt("returned") == 0) {
							model.addRow(new String[] {rs.getString("name"), rs.getString("brand") + " - " + rs.getString("model"), rs.getString("location_date"), rs.getString("devolution_date"), rs.getString("price"), "Locado"});
						} else {
							model.addRow(new String[] {rs.getString("name"), rs.getString("brand") + " - " + rs.getString("model"), rs.getString("location_date"), rs.getString("devolution_date"), rs.getString("price"), "Devolvido"});
						}
						
					}
				} catch (SQLException ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
		});
		
		rdbtnMonthly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = database.locationsReport(2);
				model = (DefaultTableModel) table.getModel();
				try {
					helpers.clearTable(model);
					while(rs.next()) {
						if (rs.getInt("returned") == 0) {
							model.addRow(new String[] {rs.getString("name"), rs.getString("brand") + " - " + rs.getString("model"), rs.getString("location_date"), rs.getString("devolution_date"), rs.getString("price"), "Locado"});
						} else {
							model.addRow(new String[] {rs.getString("name"), rs.getString("brand") + " - " + rs.getString("model"), rs.getString("location_date"), rs.getString("devolution_date"), rs.getString("price"), "Devolvido"});
						}
						
					}
				} catch (SQLException ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
		});
		
		rdbtnDaily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultSet rs = database.locationsReport(3);
				model = (DefaultTableModel) table.getModel();
				try {
					helpers.clearTable(model);
					while(rs.next()) {
						if (rs.getInt("returned") == 0) {
							model.addRow(new String[] {rs.getString("name"), rs.getString("brand") + " - " + rs.getString("model"), rs.getString("location_date"), rs.getString("devolution_date"), rs.getString("price"), "Locado"});
						} else {
							model.addRow(new String[] {rs.getString("name"), rs.getString("brand") + " - " + rs.getString("model"), rs.getString("location_date"), rs.getString("devolution_date"), rs.getString("price"), "Devolvido"});
						}
						
					}
				} catch (SQLException ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
		});
		
		rdbtnArrears.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultSet rs = database.locationsReport(4);
				model = (DefaultTableModel) table.getModel();
				try {
					helpers.clearTable(model);
					while(rs.next()) {
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String locationDate = dateFormat.format(rs.getString("location_date"));
						String devolutionDate = dateFormat.format(rs.getString("devolution_date"));
						if (rs.getInt("returned") == 0) {
							
							model.addRow(new String[] {rs.getString("name"), rs.getString("brand") + " - " + rs.getString("model"), locationDate, devolutionDate, rs.getString("price"), "Locado"});
						} else {
							model.addRow(new String[] {rs.getString("name"), rs.getString("brand") + " - " + rs.getString("model"), locationDate, devolutionDate, rs.getString("price"), "Devolvido"});
						}
					}
				} catch (SQLException ex) {
					System.out.println("Error: " + ex.getMessage());
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
