import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

public class ClientsSearch extends JFrame {

	private JPanel contentPane;
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnHome = new JMenu("Home");
	private final JLabel lblNewLabel = new JLabel("");
	private final JLabel lblClient = new JLabel("Cliente");
	private final JTextField txtClientName = new JTextField();
	private final JLabel lblClientResult = new JLabel("Resultado de Clientes");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable table = new JTable();
	private final JButton btnEdit = new JButton("Editar");
	private final JButton btnDelete = new JButton("Excluir");
	private DefaultTableModel model;
	private Helpers helpers = new Helpers();
	private Database database;
	private final JMenu mnCadastros = new JMenu("Cadastros");
	private final JMenuItem mnClients = new JMenuItem("Clientes");
	private final JMenuItem mnProjectors = new JMenuItem("Projetores");
	private final JMenu mnLocao = new JMenu("Loca\u00E7\u00E3o");
	private final JMenuItem mnLocation = new JMenuItem("Locar");
	private final JMenuItem mnReturn = new JMenuItem("Devolver");
	private final JMenuItem mnRenew = new JMenuItem("Renovar");
	private final JMenu mnSearch = new JMenu("Consultas");
	private final JMenuItem mnClient = new JMenuItem("Clientes");
	private final JMenuItem mnProjector = new JMenuItem("Projetores");
	private final JMenu mnExit = new JMenu("Sair");

	/**
	 * Create the frame.
	 */
	public ClientsSearch(Database database) {
		this.database = database;
		
		btnDelete.setEnabled(false);
		btnDelete.setIcon(new ImageIcon("resource//user-delete.png"));
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		btnEdit.setEnabled(false);
		btnEdit.setIcon(new ImageIcon("resource//user-edit.png"));
		btnEdit.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblClientResult.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtClientName.setToolTipText("Pesquise pelo nome...");
		txtClientName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtClientName.setColumns(10);
		lblClient.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setIcon(new ImageIcon("resource//user-search.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		initComponents();
		actions();
		actionsMenu();
	}
	private void initComponents() {
		setTitle("Consulta de Clientes");
		setIconImage(Toolkit.getDefaultToolkit().getImage("resource//IFFarSoft.png"));
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
		
		menuBar.add(mnSearch);
		mnClient.setIcon(new ImageIcon("resource//magnifier.png"));
		
		mnSearch.add(mnClient);
		mnProjector.setIcon(new ImageIcon("resource//magnifier.png"));
		
		mnSearch.add(mnProjector);
		
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(77)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtClientName, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblClient, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblClientResult, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 664, GroupLayout.PREFERRED_SIZE))
							.addGap(83)))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(255)
					.addComponent(btnEdit, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
					.addGap(55)
					.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
					.addGap(274))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblClient)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtClientName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblClientResult, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEdit)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Nome", "CPF/CNPJ", "E-mail", "Telefone", "Cidade", "Rua"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(152);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void actions() {
		txtClientName.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				if (txtClientName.getText().length() > 0) {
					ResultSet rs = database.searchClients(txtClientName.getText().toString());
					model = (DefaultTableModel) table.getModel();
					try {
						helpers.clearTable(model);
						while(rs.next()) {
							if (rs.getString("pp.id").equals(rs.getString("c.id"))) {				
								model.addRow(new String[] {rs.getString("id"), rs.getString("c.name"), rs.getString("pp.cpf"), rs.getString("email"), rs.getString("telephone"), rs.getString("ct.name"), rs.getString("street")});
							} else if (rs.getString("jp.id").equals(rs.getString("c.id"))) {
								model.addRow(new String[] {rs.getString("id"), rs.getString("c.name"), rs.getString("jp.cnpj"), rs.getString("email"), rs.getString("telephone") , rs.getString("ct.name"), rs.getString("street")});
							}
						}
					} catch (SQLException e) {
						System.out.println("Error: "+ e.getMessage());
					}
				} else {
					helpers.clearTable(model);
				}
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnEdit.setEnabled(true);
				btnDelete.setEnabled(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int clientId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				String cpfCnpj = table.getValueAt(table.getSelectedRow(), 2).toString();
				ClientEdit clientEdit = new ClientEdit(database, clientId, cpfCnpj);
				clientEdit.setVisible(true);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int clientId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				String client = table.getValueAt(table.getSelectedRow(),  1).toString();
				int reply= JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluír o Cliente " + client + "?", "Excluír Cliente", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					if (database.deleteClientPhysicalPerson(clientId)) {
						JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso", "Cliente excluído", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Esse cliente possui locações associadas, por favor, exclua-as antes", "Cliente com Dependência", JOptionPane.ERROR_MESSAGE);
					}
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
