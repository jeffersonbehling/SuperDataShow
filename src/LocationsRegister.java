import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;

import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.MaskFormatter;

import javafx.scene.control.TableColumn;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JDesktopPane;
import java.awt.Panel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JMenuItem;

public class LocationsRegister extends JFrame {

	private JPanel contentPane;
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnHome = new JMenu("Home");
	private final JLabel lblImage = new JLabel("");
	
	private DefaultTableModel model;
	private DefaultTableModel modelProjectors;
	private DefaultTableModel modelClients;
	private final JLabel lblClientType = new JLabel("Tipo de Cliente");
	private JTextField txtCpf;
	private JTextField txtCnpj;
	private JComboBox comboClientType = new JComboBox();
	private JLabel lblCpfCnpj = new JLabel("CPF");
	private final JButton btnSearchClient = new JButton("Procurar");
	private final JLabel lblClientName = new JLabel("Nome");
	private final JTextField txtClientName = new JTextField();
	private final JLabel lblClientId = new JLabel("ID");
	private final JTextField txtClientId = new JTextField();
	private final JLabel label = new JLabel("");
	private Date devolutionDate = new Date();
	private final JLabel lblSerialNumber = new JLabel("N\u00FAmero de S\u00E9rie");
	private final JTextField txtSerialNumber = new JTextField();
	private final JButton btnSearchSerialNumber = new JButton("Procurar");
	private final JLabel lblProjectorId = new JLabel("ID");
	private final JTextField txtProjectorId = new JTextField();
	private final JLabel lblProjectorBrandModel = new JLabel("Marca");
	private final JTextField txtProjectorBrandModel = new JTextField();
	private final JLabel lblLocationDate = new JLabel("Data de Loca\u00E7\u00E3o");
	private final JDateChooser txtLocationDate = new JDateChooser(new Date());
	private final JLabel lblDevolutionDate = new JLabel("Data de Loca\u00E7\u00E3o");
	private final JDateChooser txtDevolutionDate;
	private final JLabel lblPrice = new JLabel("Pre\u00E7o");
	private final JTextField txtPrice = new JTextField();
	private final JButton btnSave = new JButton("  Salvar");
	private boolean projectorIsVisible = false;
	private boolean clientIsVisible = false;
	private Database database;
	private final JMenu mnCadastros = new JMenu("Cadastros");
	private final JMenuItem mnClients = new JMenuItem("Clientes");
	private final JMenuItem mnProjectors = new JMenuItem("Projetores");
	private final JMenu mnConsultas = new JMenu("Consultas");
	private final JMenuItem mnClient = new JMenuItem("Clientes");
	private final JMenuItem mnProjector = new JMenuItem("Projetores");
	private final JMenu mnLoca = new JMenu("Loca\u00E7\u00E3o");
	private final JMenuItem mnLocation = new JMenuItem("Locar");
	private final JMenuItem mnReturn = new JMenuItem("Devolver");
	private final JMenuItem mnRenew = new JMenuItem("Renovar");
	private final JMenu mnExit = new JMenu("Sair");

	/**
	 * Create the frame.
	 */
	public LocationsRegister(Database database) {
		setTitle("Loca\u00E7\u00E3o");
		this.database = database;
		
		btnSave.setIcon(new ImageIcon("resource//save.png"));
		btnSave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setResizable(false);
		txtPrice.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtPrice.setColumns(10);
		lblPrice.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDevolutionDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblLocationDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtProjectorBrandModel.setEditable(false);
		txtProjectorBrandModel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtProjectorBrandModel.setColumns(10);
		lblProjectorBrandModel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtProjectorId.setEditable(false);
		txtProjectorId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtProjectorId.setColumns(10);
		lblProjectorId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblProjectorId.setVisible(false);
		lblProjectorBrandModel.setVisible(false);
		txtProjectorId.setVisible(false);
		txtProjectorBrandModel.setVisible(false);
		btnSave.setEnabled(false);
		lblLocationDate.setVisible(false);
		txtLocationDate.setVisible(false);
		lblDevolutionDate.setVisible(false);
		lblPrice.setVisible(false);
		txtPrice.setVisible(false);
		
		// setting 7 days from the current date
		devolutionDate.setDate(devolutionDate.getDate() + 7);
		txtDevolutionDate = new JDateChooser(devolutionDate);
		txtDevolutionDate.setVisible(false);
		
		btnSearchSerialNumber.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtSerialNumber.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtSerialNumber.setColumns(10);
		lblSerialNumber.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtClientId.setEditable(false);
		txtClientId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtClientId.setVisible(false);
		txtClientId.setColumns(10);
		lblClientId.setVisible(false);
		lblClientName.setVisible(false);
		lblClientId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtClientName.setEditable(false);
		txtClientName.setVisible(false);
		txtClientName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtClientName.setColumns(10);
		lblClientName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblClientType.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setIcon(new ImageIcon("resource//logo-superdatashow.png"));
		
		try {
			MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
			MaskFormatter maskCnpj = new MaskFormatter("##.###.###/####-##");
			txtCpf = new JFormattedTextField(maskCpf);
			txtCnpj = new JFormattedTextField(maskCnpj);
			txtCnpj.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			txtCnpj.setVisible(false);
			txtCpf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		initComponents();
		actions();
		actionsMenu();
	}
	
	private void initComponents() {
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
		
		menuBar.add(mnLoca);
		mnLocation.setIcon(new ImageIcon("resource//projector-screen.png"));
		
		mnLoca.add(mnLocation);
		mnReturn.setIcon(new ImageIcon("resource//back-arrow.png"));
		
		mnLoca.add(mnReturn);
		mnRenew.setIcon(new ImageIcon("resource//refresh-page-arrow-button.png"));
		
		mnLoca.add(mnRenew);
		
		menuBar.add(mnExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		comboClientType.setModel(new DefaultComboBoxModel(new String[] {"Pessoa F\u00EDsica", "Pessoa Jur\u00EDdica"}));
		comboClientType.setSelectedIndex(0);
		comboClientType.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	
		lblCpfCnpj.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblImage, GroupLayout.PREFERRED_SIZE, 818, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(118)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblDevolutionDate, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblLocationDate)
										.addPreferredGap(ComponentPlacement.RELATED))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblClientName, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED))
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(lblClientType)
														.addComponent(comboClientType, 0, 254, Short.MAX_VALUE)
														.addGroup(gl_contentPane.createSequentialGroup()
															.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(txtCnpj, 163, 163, 163))
														.addComponent(lblCpfCnpj, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblClientId, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(btnSearchClient)
													.addGap(47))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(txtClientId, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
													.addGap(130))))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(txtLocationDate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(txtClientName, Alignment.LEADING, 254, 254, Short.MAX_VALUE)
												.addComponent(txtDevolutionDate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
											.addPreferredGap(ComponentPlacement.RELATED)))))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblSerialNumber, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblProjectorId, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(txtSerialNumber, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(btnSearchSerialNumber, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
												.addComponent(txtProjectorId, 293, 293, 293)
												.addComponent(txtProjectorBrandModel, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
												.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
											.addGap(33))
										.addComponent(lblProjectorBrandModel, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
									.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(337)
							.addComponent(label)))
					.addGap(6))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblImage)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label)
									.addGap(18)
									.addComponent(lblClientType)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboClientType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblCpfCnpj, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblSerialNumber, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(btnSearchClient, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(txtSerialNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(btnSearchSerialNumber, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtCnpj, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblClientId, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblProjectorId, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
									.addGap(38)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblProjectorBrandModel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtProjectorBrandModel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblPrice)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblClientName, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtClientName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblLocationDate)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtLocationDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblDevolutionDate, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(178)
									.addComponent(txtClientId, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
									.addGap(142)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDevolutionDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(29))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(178)
							.addComponent(txtProjectorId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 191, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(112))
		);
		
		btnSearchClient.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		contentPane.setLayout(gl_contentPane);
	}
	
	public void clearTable(DefaultTableModel model) {
		for (int i = 0; i < model.getRowCount(); i++) {
			model.removeRow(i);
		}
		for (int i = 0; i < model.getRowCount(); i++) {
			model.removeRow(i);
		}
	}
	
	public void actions() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				database.disconnect();
			}
		});
		
		comboClientType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboClientType.getSelectedIndex() == 0) {
					txtCpf.setVisible(true);
					lblCpfCnpj.setText("CPF");
					txtCnpj.setVisible(false);
				} else if (comboClientType.getSelectedIndex() == 1) {
					txtCpf.setVisible(false);
					lblCpfCnpj.setText("CNPJ");
					txtCnpj.setVisible(true);
				}
			}
		});
		
		btnSearchClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboClientType.getSelectedIndex() == 0) {
					String cpf = txtCpf.getText().replace(" ", "");
					if (cpf.length() != 14) {
						clientIsVisible = false;
						JOptionPane.showMessageDialog(null, "Preencha o CPF, por favor.", "Dados Inválidos", JOptionPane.ERROR_MESSAGE);
					} else {
						cpf = cpf.replace(".", "");
						cpf = cpf.replace("-", "");
						ResultSet rs = database.searchClientsByCpf(cpf);
						try {
							if (rs != null && rs.next()) {
								clientIsVisible = true;
								lblClientName.setVisible(true);
								txtClientName.setVisible(true);
								txtClientId.setText(rs.getString("id"));
								txtClientName.setText(rs.getString("name"));
							} else {
								clientIsVisible = false;
								int addClient = JOptionPane.showConfirmDialog(null, "Nenhum cliente encontrado. Deseja adicionar um novo Cliente?", "Nenhum registro encontrado", JOptionPane.YES_NO_OPTION);
								if (addClient == JOptionPane.YES_OPTION) {
									ClientsRegister clientsRegister = new ClientsRegister(database);
									clientsRegister.setVisible(true);
									dispose();
								}
							}
						} catch (SQLException ex) {
							System.out.println("Error: " + ex.getMessage());
						}
					}
				} else {
					String cnpj = txtCnpj.getText().replace(" ", "");
					if (cnpj.length() != 18) {
						clientIsVisible = false;
						JOptionPane.showMessageDialog(null, "Preencha o CNPJ, por favor.", "Dados Inválidos", JOptionPane.ERROR_MESSAGE);
					} else {
						cnpj = cnpj.replace(".", "");
						cnpj = cnpj.replace("/", "");
						cnpj = cnpj.replace("-", "");
						ResultSet rs = database.searchClientsByCnpj(cnpj);
						try {
							if (rs != null && rs.next()) {
								clientIsVisible = true;
								lblClientName.setVisible(true);
								txtClientName.setVisible(true);
								txtClientId.setText(rs.getString("id"));
								txtClientName.setText(rs.getString("name"));
							} else {
								clientIsVisible = false;
								int addClient = JOptionPane.showConfirmDialog(null, "Nenhum cliente encontrado. Deseja adicionar um novo Cliente?", "Nenhum registro encontrado", JOptionPane.YES_NO_OPTION);
								if (addClient == JOptionPane.YES_OPTION) {
									ClientsRegister clientsRegister = new ClientsRegister(database);
									clientsRegister.setVisible(true);
									dispose();
								}
							}
						} catch (SQLException ex) {
							System.out.println("Error: " + ex.getMessage());
						}
					}
				}
				if (projectorIsVisible) {
					lblDevolutionDate.setVisible(true);
					lblLocationDate.setVisible(true);
					txtLocationDate.setVisible(true);
					txtDevolutionDate.setVisible(true);
					lblPrice.setVisible(true);
					txtPrice.setVisible(true);
					btnSave.setEnabled(true);
				}
			}
		});
		btnSearchSerialNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtSerialNumber.getText().length() == 0) {
					projectorIsVisible = false;
					lblProjectorBrandModel.setVisible(false);
					txtProjectorBrandModel.setVisible(false);
					JOptionPane.showMessageDialog(null, "Preencha o Número de Série, por favor.", "Dados Inválidos", JOptionPane.ERROR_MESSAGE);
				} else {
					String serialNumber = txtSerialNumber.getText();
					ResultSet rs = database.searchProjectorBySerialNumber(serialNumber);
					try {
						if (rs != null && rs.next()) {
							projectorIsVisible = true;
							lblProjectorBrandModel.setVisible(true);
							txtProjectorBrandModel.setVisible(true);
							txtProjectorId.setText(rs.getString("id"));
							txtProjectorBrandModel.setText(rs.getString("brand") + " - " + rs.getString("model"));
							if (clientIsVisible) {
								lblDevolutionDate.setVisible(true);
								lblLocationDate.setVisible(true);
								txtLocationDate.setVisible(true);
								txtDevolutionDate.setVisible(true);
								lblPrice.setVisible(true);
								txtPrice.setVisible(true);
								btnSave.setEnabled(true);
							}
						} else {
							projectorIsVisible = false;
							JOptionPane.showMessageDialog(null, "Projetor não encontrado ou está indisponível para locação.", "Nenhum registro encontrado", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (SQLException ex) {
						System.out.println("Error: " + ex.getMessage());
					}
				}
			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtLocationDate.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Preencha a data de Locação, por favor.", "Dados Inválidos", JOptionPane.ERROR_MESSAGE);
					txtLocationDate.requestFocus();
				} else {
					if (txtDevolutionDate.getDate() == null) {
						JOptionPane.showMessageDialog(null, "Preencha a data de Devolução, por favor.", "Dados Inválidos", JOptionPane.ERROR_MESSAGE);
						txtDevolutionDate.requestFocus();
					} else {
						int clientId = Integer.parseInt(txtClientId.getText());
						int projectorId = Integer.parseInt(txtProjectorId.getText());
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String locationDate = dateFormat.format(txtLocationDate.getDate());
						String devolutionDate = dateFormat.format(txtDevolutionDate.getDate());
						float price = 0;
						if (!txtPrice.getText().equals("")) {
							price = Float.parseFloat(txtPrice.getText());
						}
						
						boolean wasRegistered = database.registerLocation(locationDate, devolutionDate, price, clientId, projectorId);
						if (wasRegistered) {
							JOptionPane.showMessageDialog(null, "Locação realizada com sucesso", "Locação Realizada", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Falha ao fazer locação", "Falha na Locação", JOptionPane.ERROR_MESSAGE);
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
