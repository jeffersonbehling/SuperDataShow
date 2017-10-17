import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ClientEdit extends JFrame {

	private JPanel contentPane;
	private final JLabel label = new JLabel("");
	private final JLabel lblName = new JLabel("Nome");
	private final JTextField txtName = new JTextField();
	private final JLabel lblCpfCnpj = new JLabel("CPF");
	private final JLabel lblEmail = new JLabel("E-mail");
	private final JTextField txtEmail = new JTextField();
	private JFormattedTextField txtCpf = new JFormattedTextField();
	private final JLabel lblRg = new JLabel("RG");
	private final JTextField txtRg = new JTextField();
	private final JLabel lblState = new JLabel("Estados");
	private final JComboBox comboState = new JComboBox();
	private final JLabel lblCity = new JLabel("Cidades");
	private final JComboBox comboCity = new JComboBox();
	private final JLabel lblStreet = new JLabel("Rua");
	private final JTextField txtStreet = new JTextField();
	private final JLabel lblBirthday = new JLabel("Data de Nascimento");
	private final JDateChooser txtBirthday;
	private final JLabel lblTelephone = new JLabel("Telefone");
	private final JTextField txtTelephone = new JTextField();
	private String cityName = "";
	private final JButton btnSave = new JButton("Salvar");
	private final JButton btnCancel = new JButton("Cancelar");
	private JFormattedTextField txtCnpj = new JFormattedTextField();
	private int clientId;
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnHome = new JMenu("Home");
	private final JMenu mnCadastros = new JMenu("Cadastros");
	private final JMenuItem mnClients = new JMenuItem("Clientes");
	private final JMenuItem mnProjectors = new JMenuItem("Projetores");
	private final JMenu mnLocaes = new JMenu("Loca\u00E7\u00E3o");
	private final JMenuItem mnLocation = new JMenuItem("Locar");
	private final JMenuItem mnReturn = new JMenuItem("Devolver");
	private final JMenuItem mnRenew = new JMenuItem("Renovar");
	private final JMenu mnEdit = new JMenu("Consultas");
	private final JMenuItem mnClient = new JMenuItem("Cliente");
	private final JMenuItem mnProjector = new JMenuItem("Projetor");
	private final JMenu mnExit = new JMenu("Sair");
	private Database database;

	/**
	 * Create the frame.
	 */
	public ClientEdit(Database database, int clientId, String cpfCnpj) {
		this.database = database;
		this.clientId = clientId;
		
		setResizable(false);
		btnCancel.setIcon(new ImageIcon("resource//error.png"));
		btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		btnSave.setIcon(new ImageIcon("resource//save.png"));
		btnSave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtTelephone.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtTelephone.setColumns(10);
		lblTelephone.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblBirthday.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtStreet.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtStreet.setColumns(10);
		lblStreet.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboCity.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCity.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboState.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblState.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtRg.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtRg.setColumns(10);
		lblRg.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtBirthday = new JDateChooser();
		
		try {
			fillComboStates();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCpfCnpj.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtName.setColumns(10);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon("resource//user-edit128.png"));
		
		if (cpfCnpj.length() == 11) {
			ResultSet rs = database.searchClientPhysicalPerson(clientId);
			
			try {
				if (rs.next()) {
					try {
						txtCnpj.setVisible(false);
						MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
						Date date = new Date();
						txtCpf = new JFormattedTextField(maskCpf);
						txtCpf.setText(rs.getString("pp.cpf"));
						txtName.setText(rs.getString("c.name"));
						
						txtEmail.setText(rs.getString("c.email"));
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						txtBirthday.setDate(rs.getDate("birthday"));
						txtTelephone.setText(rs.getString("c.telephone"));
						txtRg.setText(rs.getString("pp.rg"));
						txtStreet.setText(rs.getString("street"));
						comboState.setSelectedIndex(getIndexState(rs.getString("s.name"), rs.getString("ct.name")));
					} catch (ParseException e) {
						System.out.println("Error: " + e.getMessage());
					}
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			}
		} else {
			ResultSet rs = database.searchClientJuridicalPerson(clientId);
			
			try {
				if (rs.next()) {
					try {
						txtCnpj.setVisible(true);
						txtCpf.setVisible(false);
						txtBirthday.setVisible(false);
						txtRg.setVisible(false);
						lblRg.setVisible(false);
						lblBirthday.setVisible(false);
						lblCpfCnpj.setText("CNPJ");
						MaskFormatter maskCnpj = new MaskFormatter("##.###.###/####-##");
						Date date = new Date();
						txtCnpj = new JFormattedTextField(maskCnpj);
						txtCnpj.setText(rs.getString("jp.cnpj"));
						txtName.setText(rs.getString("c.name"));
						txtEmail.setText(rs.getString("c.email"));
						txtTelephone.setText(rs.getString("c.telephone"));
						txtStreet.setText(rs.getString("street"));
						
						comboState.setSelectedIndex(getIndexState(rs.getString("s.name"), rs.getString("ct.name")));
					} catch (ParseException e) {
						System.out.println("Error: " + e.getMessage());
					}
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}			
		initComponents();
		actions();
		actionsMenu();
	}
		
	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("resource//IFFarSoft.png"));
		setTitle("Editar Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(250, 90, 850, 600);
		
		setJMenuBar(menuBar);
		
		menuBar.add(mnHome);
		
		menuBar.add(mnCadastros);
		mnClients.setIcon(new ImageIcon("resource//user-silhouette.png"));
		
		mnCadastros.add(mnClients);
		mnProjectors.setIcon(new ImageIcon("resource//projector-item.png"));
		
		mnCadastros.add(mnProjectors);
		
		menuBar.add(mnEdit);
		mnClient.setIcon(new ImageIcon("resource//magnifier.png"));
		
		mnEdit.add(mnClient);
		mnProjector.setIcon(new ImageIcon("resource//magnifier.png"));
		
		mnEdit.add(mnProjector);
		
		menuBar.add(mnLocaes);
		mnLocation.setIcon(new ImageIcon("resource//projector-screen.png"));
		
		mnLocaes.add(mnLocation);
		mnReturn.setIcon(new ImageIcon("resource//back-arrow.png"));
		
		mnLocaes.add(mnReturn);
		mnRenew.setIcon(new ImageIcon("resource//refresh-page-arrow-button.png"));
		
		mnLocaes.add(mnRenew);
		
		menuBar.add(mnExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtCpf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(139)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtBirthday, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
													.addComponent(txtEmail, 269, 269, Short.MAX_VALUE)
													.addComponent(lblCpfCnpj, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(txtCpf, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
															.addComponent(lblRg, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
															.addComponent(txtRg, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
													.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
													.addComponent(txtCnpj, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
												.addGap(2))
											.addComponent(lblBirthday, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
										.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblName)
										.addPreferredGap(ComponentPlacement.RELATED)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(59)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtTelephone, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTelephone, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
											.addComponent(lblState, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
											.addComponent(comboState, 0, 263, Short.MAX_VALUE)
											.addComponent(lblCity, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
											.addComponent(comboCity, 0, 263, Short.MAX_VALUE)
											.addComponent(lblStreet, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtStreet))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(94)
									.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
							.addGap(198))))
		);
		txtCnpj.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblState, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCpfCnpj, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRg, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtRg, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtCnpj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCity, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboCity, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblStreet, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtStreet, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTelephone, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(lblBirthday, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtBirthday, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTelephone, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave))
					.addGap(57))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void actions() {
		comboState.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String stateName = comboState.getSelectedItem().toString();
				try {
					fillComboCities(stateName, "");
				} catch (Exception e) {
					System.out.println("Erro: " + e.getMessage());
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (txtName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha o campo Nome", "Dados Inválidos", JOptionPane.ERROR_MESSAGE);
					txtName.requestFocus();
				} else {
					String cpf;
					String cnpj;
					String name = txtName.getText();
					String email = txtEmail.getText();
					String telephone = txtTelephone.getText();
					String street = txtStreet.getText();
					int stateId = database.getStateId(comboState.getSelectedItem().toString());
					int cityId = database.getCityId(comboCity.getSelectedItem().toString(), stateId);
					
					if (lblCpfCnpj.getText().equals("CPF")) {
						cpf = txtCpf.getText().replace(".", "");
						cpf = cpf.replace("-", "");
						if (cpf.length() != 11) {
							JOptionPane.showMessageDialog(null, "Informe um CPF válido", "Dados Inválidos", JOptionPane.ERROR_MESSAGE);
							txtCpf.requestFocus();
						} else {
							if (txtRg.getText().length() != 10) {
								JOptionPane.showMessageDialog(null, "Informe um RG válido", "Dados Inválidos", JOptionPane.ERROR_MESSAGE);
								txtRg.requestFocus();
							} else {								
								String rg = txtRg.getText();
								String birthday = "";
								if (txtBirthday.getDate() != null) {
									SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
									birthday = dateFormat.format(txtBirthday.getDate());
								}
								
								boolean wasUpdated = database.updateClientPhysicalPerson(clientId, name, email, telephone, street, cpf, rg, birthday, cityId);
								
								if (wasUpdated) {
									JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso", "Dados atualizados", JOptionPane.INFORMATION_MESSAGE);
									dispose();
								} else {
									JOptionPane.showMessageDialog(null, "Falha ao atualizar informações do Cliente", "Dados não atualizados", JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					} else {
						cnpj = txtCnpj.getText().replace(".", "");
						cnpj = cnpj.replace("/", "");
						cnpj = cnpj.replace("-", "");
						if (cnpj.length() != 14) {
							JOptionPane.showMessageDialog(null, "Informe um CNPJ válido", "Dados Inválidos", JOptionPane.ERROR_MESSAGE);
							txtCnpj.requestFocus();
						} else {
							boolean wasUpdated = database.updateClientJuridicalPerson(clientId, name, email, telephone, street, cnpj, cityId);
							
							if (wasUpdated) {
								JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso", "Dados atualizados", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "Falha ao atualizar informações do Cliente", "Dados não atualizados", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			}
		});
		
	}
	
	public void fillComboStates() throws SQLException {
		database.connect();
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
	
	public void fillComboCities(String stateName, String cityName) throws SQLException {
		database.connect();
		ResultSet rs = database.cities(stateName);
		if (rs != null) {
			comboCity.setModel(new DefaultComboBoxModel(new String[] {}));
			int index = 0;
			while (rs.next()) {
				comboCity.insertItemAt(rs.getString("name"), index);
				index++;
			}
			comboCity.setSelectedItem(cityName);
		}
	}
	
	public int getIndexState(String stateName, String cityName) {
		try {
			fillComboCities(stateName, cityName);
		} catch (SQLException e) {}
		
		for (int i = 0; i < comboState.getItemCount(); i++) {
			if (stateName.equals(comboState.getItemAt(i))) {
				return i;
			}
		}
		return 0;
	}
	
	public int getIndexCity(String stateName, String cityName) {
		try {
			fillComboCities(stateName, "");
		} catch (SQLException e) {
			System.out.println("Error: "+ e.getMessage());
		}
		
		for (int i = 0; i < comboCity.getItemCount(); i++) {
			if (cityName.equals(comboCity.getItemAt(i))) {
				return i;
			}
		}
		return 0;
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
