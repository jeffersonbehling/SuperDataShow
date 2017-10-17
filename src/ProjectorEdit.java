import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.mysql.fabric.xmlrpc.base.Data;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
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

public class ProjectorEdit extends JFrame {

	private JPanel contentPane;
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnHome = new JMenu("Home");
	private final JLabel lblNewLabel = new JLabel("");
	private final JLabel lblBrand = new JLabel("Marca");
	private final JTextField txtBrand = new JTextField();
	private final JLabel lblModel = new JLabel("Modelo");
	private final JTextField txtModel = new JTextField();
	private final JTextField txtSerialNumber = new JTextField();
	private final JLabel lblAnsiLumens = new JLabel("Ansi Lumens");
	private final JTextField txtAnsiLumens = new JTextField();
	private final JLabel lblProjectorState = new JLabel("Estado do Projetor");
	private final JComboBox comboProjectorState = new JComboBox();
	private final JLabel lblSerialNumber = new JLabel("N\u00FAmero de S\u00E9rie");
	private final JLabel lblPurchaseDate = new JLabel("Data da Compra");
	private final JDateChooser txtPurchaseDate = new JDateChooser();
	private final JLabel lblDateLastLampChange = new JLabel("Data \u00FAltima troca de l\u00E2mpada");
	private final JDateChooser txtDateLastLampChange = new JDateChooser();
	private final JButton btnSave = new JButton("   Salvar");
	private final JButton btnCancel = new JButton("   Cancelar");
	private Database database;
	private int projectorId;
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
	public ProjectorEdit(Database database, int projectorId) {
		setResizable(false);
		this.database = database;
		this.projectorId = projectorId;
		btnCancel.setIcon(new ImageIcon("resource//error.png"));
		btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnSave.setIcon(new ImageIcon("resource//save.png"));
		btnSave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDateLastLampChange.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPurchaseDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSerialNumber.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboProjectorState.setModel(new DefaultComboBoxModel(new String[] {"Dispon\u00EDvel", "Manuten\u00E7\u00E3o"}));
		comboProjectorState.setSelectedIndex(0);
		comboProjectorState.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblProjectorState.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtAnsiLumens.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtAnsiLumens.setColumns(10);
		lblAnsiLumens.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtSerialNumber.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtSerialNumber.setColumns(10);
		txtModel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtModel.setColumns(10);
		lblModel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblBrand.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtBrand.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtBrand.setColumns(10);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("resource//projector.png"));
		
		ResultSet rs = database.getProjector(projectorId);
		try {
			if (rs.next()) {
				txtBrand.setText(rs.getString("brand"));
				txtModel.setText(rs.getString("model"));
				txtSerialNumber.setText(rs.getString("serial_number"));
				txtAnsiLumens.setText(rs.getString("ansi_lumens"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				txtPurchaseDate.setDate(rs.getDate("purchase_date"));
				if (rs.getDate("date_last_lamp_change") != null) {
					txtDateLastLampChange.setDate(rs.getDate("date_last_lamp_change"));
				}
				if (rs.getString("projector_state").equals("Locado")) {
					comboProjectorState.setModel(new DefaultComboBoxModel(new String[] {"Locado"}));
					comboProjectorState.setSelectedIndex(0);
				} else {
					comboProjectorState.setModel(new DefaultComboBoxModel(new String[] {"Dispon\u00EDvel", "Manuten\u00E7\u00E3o"}));
					comboProjectorState.setSelectedItem(rs.getString("projector_state"));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		}
		
		initComponents();
		actions();
		actionsMenu();
	}
	private void initComponents() {
		setTitle("Editar Projetor");
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(txtModel, Alignment.LEADING)
											.addComponent(txtBrand, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
											.addComponent(lblAnsiLumens, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtAnsiLumens, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblProjectorState, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
											.addComponent(comboProjectorState, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblBrand)
										.addComponent(lblModel, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
									.addGap(68))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
									.addGap(69)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblPurchaseDate, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSerialNumber, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSerialNumber, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
								.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDateLastLampChange, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPurchaseDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtDateLastLampChange, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(122)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBrand)
						.addComponent(lblSerialNumber, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtBrand, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSerialNumber, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblModel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtModel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPurchaseDate, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPurchaseDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAnsiLumens, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtAnsiLumens, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblDateLastLampChange, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDateLastLampChange, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblProjectorState, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboProjectorState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(47))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void actions() {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String brand = txtBrand.getText();
				String model = txtModel.getText();
				String serialNumber = txtSerialNumber.getText();
				int ansiLumens = Integer.parseInt(txtAnsiLumens.getText().toString());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String purchaseDate = dateFormat.format(txtPurchaseDate.getDate());
				String dateLastLampChange = dateFormat.format(txtDateLastLampChange.getDate());
				String projectorState = comboProjectorState.getSelectedItem().toString();
				
				if (brand.equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha o campo Marca, por favor.", "Dados Inválidos", JOptionPane.ERROR_MESSAGE);
					txtBrand.requestFocus();
				} else {
					if (model.equals("")) {
						JOptionPane.showMessageDialog(null, "Preencha o campo Modelo, por favor.", "Dados Inválidos", JOptionPane.ERROR_MESSAGE);
						txtModel.requestFocus();
					} else {
						if (serialNumber.equals("")) {
							JOptionPane.showMessageDialog(null, "Preencha o campo Número de Série, por favor.", "Dados Inválidos", JOptionPane.ERROR_MESSAGE);
							txtSerialNumber.requestFocus();
						} else {
							boolean wasUpdated = database.updateProjector(projectorId, brand, model, serialNumber, ansiLumens, projectorState, purchaseDate, dateLastLampChange);
							
							if (wasUpdated) {
								JOptionPane.showMessageDialog(null, "Projetor alterado com sucesso", "Alteração realizada", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "Falha ao alterar dados do Projetor", "Falha na alteração", JOptionPane.ERROR_MESSAGE);
							}
						}
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
