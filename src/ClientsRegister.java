import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenuItem;

public class ClientsRegister extends JFrame {

	private JPanel contentPane;
	private final JLabel lblImagem = new JLabel("");
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnHome = new JMenu("Home");
	private final JLabel lblClientType = new JLabel("Tipo de Cliente");
	private final JComboBox comboClientType = new JComboBox();
	private final JLabel lblNome = new JLabel("Nome");
	private final JTextField txtName = new JTextField();
	private final JLabel lblCpfCnpj = new JLabel("CPF");
	private JTextField txtCpf;
	private final JLabel lblRg = new JLabel("RG");
	private JTextField txtRg;
	private JTextField txtCnpj;
	private final JLabel lblState = new JLabel("Estado");
	private final JComboBox comboState = new JComboBox();
	private final JLabel lblCity = new JLabel("Cidade");
	private final JComboBox comboCity = new JComboBox();
	private final JLabel lblStreet = new JLabel("Rua");
	private final JTextField txtStreet = new JTextField();
	private final JLabel lblEmail = new JLabel("E-mail");
	private final JTextField txtEmail = new JTextField();
	private final JLabel lblTelephone = new JLabel("Telefone");
	private final JTextField txtTelephone = new JTextField();
	private final JLabel lblBirthday = new JLabel("Data de Nasscimento");
	private final JDateChooser txtBirthday = new JDateChooser();
	private JButton btnSave = new JButton("  Salvar");
	private JLabel lblObriCpfCnpj = new JLabel("*");
	private JLabel lblObriRg = new JLabel("*");
	private JLabel lblObriNome = new JLabel("*");
	private Database database;
	private final JLabel label = new JLabel("Copyright \u00A9 IFFarSoft - Todos os Direitos Reservados");
	private final JMenu mnCadastros = new JMenu("Cadastros");
	private final JMenuItem mnClients = new JMenuItem("Clientes");
	private final JMenuItem mnProjectors = new JMenuItem("Projetores");
	private final JMenu mnLocao = new JMenu("Loca\u00E7\u00E3o");
	private final JMenuItem mnLocation = new JMenuItem("Locar");
	private final JMenuItem mnReturn = new JMenuItem("Devolver");
	private final JMenuItem mnRenew = new JMenuItem("Renovar");
	private final JMenu mnEdio = new JMenu("Edi\u00E7\u00E3o");
	private final JMenuItem mnClient = new JMenuItem("Cliente");
	private final JMenuItem mnProjector = new JMenuItem("Projetor");
	private final JMenu mnExit = new JMenu("Sair");
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ClientsRegister(Database database) throws SQLException {
		setResizable(false);
		this.database = database;
		
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.GRAY);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("resource//IFFarSoft.png"));
		lblBirthday.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTelephone.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtTelephone.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtTelephone.setColumns(10);
		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		lblStreet.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtStreet.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtStreet.setColumns(10);
		comboCity.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCity.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		comboState.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblState.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCpfCnpj.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtName.setColumns(10);
		
		try {
			MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
			MaskFormatter maskRg = new MaskFormatter("##########");
			MaskFormatter maskCnpj = new MaskFormatter("##.###.###/####-##");
			txtCpf = new JFormattedTextField(maskCpf);
			txtRg = new JFormattedTextField(maskRg);
			txtCnpj = new JFormattedTextField(maskCnpj);
			txtCnpj.setVisible(false);
		} catch(Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		comboClientType.setModel(new DefaultComboBoxModel(new String[] {"Pessoa F\u00EDsica", "Pessoa Jur\u00EDdica"}));
		comboClientType.setSelectedIndex(0);
		comboClientType.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblClientType.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblImagem.setIcon(new ImageIcon("resource//users.png"));
		initComponents();
		actions();
		fillComboStates();
		comboState.setSelectedIndex(0);
		fillComboCities(comboState.getSelectedItem().toString());
		comboCity.setSelectedIndex(0);
		actionsMenu();
	}
	
	private void initComponents() {
		setTitle("Adicionar Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 90, 850, 600);
		
		setJMenuBar(menuBar);
		
		menuBar.add(mnHome);
		
		menuBar.add(mnCadastros);
		mnClients.setIcon(new ImageIcon("resource//user-silhouette.png"));
		
		mnCadastros.add(mnClients);
		mnProjectors.setIcon(new ImageIcon("resource//projector-item.png"));
		
		mnCadastros.add(mnProjectors);
		
		menuBar.add(mnLocao);
		mnLocation.setIcon(new ImageIcon("resource//projector-screen.png"));
		
		mnLocao.add(mnLocation);
		mnReturn.setIcon(new ImageIcon("resource//back-arrow.png"));
		
		mnLocao.add(mnReturn);
		mnRenew.setIcon(new ImageIcon("resource//refresh-page-arrow-button.png"));
		
		mnLocao.add(mnRenew);
		
		menuBar.add(mnEdio);
		mnClient.setIcon(new ImageIcon("resource//pencil-striped-symbol-for-interface-edit-buttons.png"));
		
		mnEdio.add(mnClient);
		mnProjector.setIcon(new ImageIcon("resource//pencil-striped-symbol-for-interface-edit-buttons.png"));
		
		mnEdio.add(mnProjector);
		
		menuBar.add(mnExit);
		contentPane = new JPanel();
		contentPane.setForeground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
			
		btnSave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnSave.setIcon(new ImageIcon("resource//save.png"));
		
		
		lblObriNome.setForeground(Color.RED);
		lblObriNome.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		lblObriCpfCnpj.setForeground(Color.RED);
		lblObriCpfCnpj.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				
		lblObriRg.setForeground(Color.RED);
		lblObriRg.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStreet)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblNome)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblObriNome))
										.addComponent(comboClientType, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblClientType)
										.addComponent(txtName, 340, 340, 340)
										.addComponent(txtCnpj, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(comboState, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(comboCity, 0, 0, Short.MAX_VALUE))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
													.addComponent(txtCpf, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(lblCpfCnpj)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblObriCpfCnpj, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)))
												.addGap(18)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(lblRg)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblObriRg, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE))
													.addComponent(txtRg, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))))
										.addComponent(txtStreet, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE))
									.addGap(25))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblState)
									.addGap(139)
									.addComponent(lblCity)
									.addGap(139)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEmail)
								.addComponent(lblTelephone)
								.addComponent(lblBirthday)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(txtBirthday, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(txtTelephone, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnSave)
									.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)))
							.addGap(76)))
					.addGap(76))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(344)
					.addComponent(lblImagem)
					.addContainerGap(556, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 804, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(86, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblImagem)
					.addGap(22)
					.addComponent(lblClientType)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboClientType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(lblEmail)
						.addComponent(lblObriNome))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblCpfCnpj)
									.addComponent(lblRg))
								.addComponent(lblObriCpfCnpj, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblObriRg, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCnpj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblState)
								.addComponent(lblCity))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(comboState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblStreet))
								.addComponent(comboCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtStreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 166, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTelephone)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTelephone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblBirthday)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtBirthday, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
							.addComponent(btnSave)
							.addGap(22)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
		);
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblRg.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		contentPane.setLayout(gl_contentPane);
	}
	
	public void actions() {
		comboClientType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboClientType.getSelectedIndex() == 0) {
					lblImagem.setIcon(new ImageIcon("resource//users.png"));
					lblCpfCnpj.setText("CPF");
					txtCnpj.setVisible(false);
					txtCpf.setVisible(true);
					lblRg.setVisible(true);
					txtRg.setVisible(true);
					lblObriRg.setVisible(true);
					lblBirthday.setVisible(true);
					txtBirthday.setVisible(true);
				} else {
					lblImagem.setIcon(new ImageIcon("resource//manufacturer.png"));
					lblCpfCnpj.setText("CNPJ");
					txtCpf.setVisible(false);
					txtCnpj.setVisible(true);
					lblObriRg.setVisible(false);
					lblRg.setVisible(false);
					txtRg.setVisible(false);
					txtRg.setVisible(false);
					lblBirthday.setVisible(false);
					txtBirthday.setVisible(false);
				}
			}
		});
		
		comboState.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String stateName = comboState.getSelectedItem().toString();
				try {
					fillComboCities(stateName);
				} catch (Exception e) {
					System.out.println("Erro: " + e.getMessage());
				}
			}
		});
		
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String domain = new String(txtEmail.getText().substring(txtEmail.getText().lastIndexOf
						('@') + 1, txtEmail.getText().length()));
				
				if (txtEmail.getText().length() > 0 && (!txtEmail.getText().contains("@") || !domain.contains("."))) {
					JOptionPane.showMessageDialog(null, "E-mail Inválido", "Dados Inválidos", JOptionPane.ERROR_MESSAGE);
					txtEmail.setForeground(Color.RED);
					txtEmail.requestFocus();
				} else {
					txtEmail.setForeground(Color.BLACK);
				}
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cpf = "";
				String rg = "";
				String birthday = null;
				String cnpj = "";
				if (txtName.getText().length() == 0) {
					txtName.requestFocus();
					JOptionPane.showMessageDialog(null, "Preencha o nome, por favor.", "Campo Obrigatório", JOptionPane.ERROR_MESSAGE);
				} else {
					if (comboClientType.getSelectedIndex() == 0) {
						cpf = txtCpf.getText().replace(" ", "");
						if (cpf.length() != 14) {
							txtCpf.requestFocus();
							JOptionPane.showMessageDialog(null, "Preencha o CPF, por favor.", "Campo Obrigatório", JOptionPane.ERROR_MESSAGE);
						} else {
							cpf = txtCpf.getText().replace(".", "");
							cpf = cpf.replace("-", "");
							rg = txtRg.getText().replace(" ", "");
							if (rg.length() != 10) {
								txtRg.requestFocus();
								JOptionPane.showMessageDialog(null, "Preencha o RG, por favor.", "Campo Obrigatório", JOptionPane.ERROR_MESSAGE);
							}
						}
						String name = txtName.getText();
						String email = txtEmail.getText();
						String telephone = txtTelephone.getText();
						String street = txtStreet.getText();
						String stateName = comboState.getSelectedItem().toString();
						String cityName = comboCity.getSelectedItem().toString();
						int client_type = 0; // Physical Person
						if (txtBirthday.getDate() != null) {
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							birthday = dateFormat.format(txtBirthday.getDate());
						}
						if (database.cpfIsAlreadyUse(cpf)) {
							JOptionPane.showMessageDialog(null, "CPF já está sendo usado.", "Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
						} else {
							boolean wasRegistered = database.registerClient(name, email, telephone, street, stateName, cityName, client_type, cpf, rg, birthday, cnpj);
							if (wasRegistered) {
								JOptionPane.showMessageDialog(null, "Cliente Cadastrado com sucesso", "Cadastro Realizado", JOptionPane.INFORMATION_MESSAGE);
								Home home = new Home(database);
								home.setVisible(true);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "Falha ao cadastrar Cliente", "Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
							}
						}
					} else {
						cnpj = txtCnpj.getText().replace(" ", "");
						if (cnpj.length() != 18) {
							JOptionPane.showMessageDialog(null, "Preencha o CNPJ, por favor.", "Campo Obrigatório", JOptionPane.ERROR_MESSAGE);
						} else {
							cnpj = cnpj.replace(".", "");
							cnpj = cnpj.replace("/", "");
							cnpj = cnpj.replace("-", "");
						}
						String name = txtName.getText();
						String email = txtEmail.getText();
						String telephone = txtTelephone.getText();
						String street = txtStreet.getText();
						String stateName = comboState.getSelectedItem().toString();
						String cityName = comboCity.getSelectedItem().toString();
						int client_type = 1; // Juridical Person
						if (database.cnpjIsAlreadyUse(cnpj)) {
							JOptionPane.showMessageDialog(null, "CNPJ já está sendo usado.", "Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
						} else {
							boolean wasRegistered = database.registerClient(name, email, telephone, street, stateName, cityName, client_type, cpf, rg, birthday, cnpj);
							if (wasRegistered) {
								JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "Cadastro Realizado", JOptionPane.INFORMATION_MESSAGE);
								Home home = new Home(database);
								home.setVisible(true);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "Falha ao cadastrar Cliente", "Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				database.disconnect();
			}
		});
	}
	
	public void fillComboStates() throws SQLException {
		ResultSet rs = database.states();
		if (rs != null) {
			comboState.setModel(new DefaultComboBoxModel(new String[] {}));
			int index = 0;
			while (rs.next()) {
				comboState.insertItemAt(rs.getString("name"), index);
				index++;
			}
		}
	}
	
	public void fillComboCities(String stateName) throws SQLException {
		ResultSet rs = database.cities(stateName);
		if (rs != null) {
			comboCity.setModel(new DefaultComboBoxModel(new String[] {}));
			int index = 0;
			while (rs.next()) {
				comboCity.insertItemAt(rs.getString("name"), index);
				index++;
			}
			comboCity.setSelectedIndex(0);
		}
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
