import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import java.util.Date;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

public class ProjectorsRegister extends JFrame {

	private JPanel contentPane;
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnHome = new JMenu("Home");
	private final JLabel lblProjector = new JLabel("");
	private final JLabel lblBrand = new JLabel("Marca");
	private final JTextField txtBrand = new JTextField();
	private final JLabel lblModel = new JLabel("Modelo");
	private final JTextField txtModel = new JTextField();
	private final JLabel lblSerialNumber = new JLabel("N\u00FAmero de S\u00E9rie");
	private final JTextField txtSerialNumber = new JTextField();
	private final JLabel lblPurchaseDate = new JLabel("Data da Compra");
	private final JDateChooser txtPurchaseDate = new JDateChooser(new Date());
	private final JLabel lblAnsiLumens = new JLabel("Ansi Lumens");
	private final JTextField txtAnsiLumens = new JTextField();
	private final JLabel lblDataUltTroca = new JLabel("Data da \u00FAltima troca de L\u00E2mpada");
	private final JDateChooser txtDateLastLampChange = new JDateChooser((Date) null);
	private final JLabel lblProjectorState = new JLabel("Estado do Projetor");
	private final JComboBox comboProjectorState = new JComboBox();
	private final JButton btnSave = new JButton("  Salvar");
	private final JLabel label = new JLabel("Developed by IFFarSoft");
	private final JLabel lblObrBrand = new JLabel("*");
	private final JLabel lblObrModel = new JLabel("*");
	private final JLabel lblObrSerialNumber = new JLabel("*");
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
	public ProjectorsRegister(Database database) {
		setResizable(false);
		setTitle("Projetores");
		this.database = database;
		
		lblObrSerialNumber.setForeground(Color.RED);
		lblObrSerialNumber.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblObrModel.setForeground(Color.RED);
		lblObrModel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblObrBrand.setForeground(Color.RED);
		lblObrBrand.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.GRAY);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		btnSave.setIcon(new ImageIcon("resource//save.png"));
		btnSave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblProjectorState.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDataUltTroca.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtAnsiLumens.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtAnsiLumens.setColumns(10);
		lblAnsiLumens.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPurchaseDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtSerialNumber.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtSerialNumber.setColumns(10);
		lblModel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtModel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtModel.setColumns(10);
		lblBrand.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtBrand.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtBrand.setColumns(10);
		lblProjector.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjector.setIcon(new ImageIcon("resource//projector.png"));
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
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(lblProjector, GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(119)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblProjectorState, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboProjectorState, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblBrand)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblObrBrand, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtBrand, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblSerialNumber)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblObrSerialNumber, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtSerialNumber, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
								.addComponent(lblAnsiLumens)
								.addComponent(txtAnsiLumens, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblPurchaseDate)
									.addComponent(txtModel, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblModel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblObrModel, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE))
									.addComponent(txtPurchaseDate, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
									.addComponent(lblDataUltTroca, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtDateLastLampChange, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSave, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
							.addGap(106))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 827, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblProjector)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBrand)
								.addComponent(lblObrBrand, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtBrand, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblModel)
								.addComponent(lblObrModel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtModel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSerialNumber)
								.addComponent(lblObrSerialNumber, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSerialNumber, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPurchaseDate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPurchaseDate, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAnsiLumens)
						.addComponent(lblDataUltTroca, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtAnsiLumens, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblProjectorState, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboProjectorState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtDateLastLampChange, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(91)
					.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
		);
		comboProjectorState.setModel(new DefaultComboBoxModel(new String[] {"Dispon\u00EDvel", "Manuten\u00E7\u00E3o"}));
		comboProjectorState.setSelectedIndex(0);
		comboProjectorState.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSerialNumber.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		contentPane.setLayout(gl_contentPane);
	}
	
	public void actions() {
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtBrand.getText().length() == 0) {
					txtBrand.requestFocus();
					JOptionPane.showMessageDialog(null, "Preencha a Marca do Projetor, por favor.", "Campo Obrigatório", JOptionPane.ERROR_MESSAGE);
				} else {
					if (txtModel.getText().length() == 0) {
						txtModel.requestFocus();
						JOptionPane.showMessageDialog(null, "Preencha o Modelo do Projetor, por favor.", "Campo Obrigatório", JOptionPane.ERROR_MESSAGE);
					} else {
						if (txtSerialNumber.getText().length() == 0) {
							txtSerialNumber.requestFocus();
							JOptionPane.showMessageDialog(null, "Preencha o Número de Série do Projetor, por favor.", "Campo Obrigatório", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				String brand = txtBrand.getText();
				String model = txtModel.getText();
				String serialNumber = txtSerialNumber.getText();
				String purchaseDate = null;
				if (txtPurchaseDate.getDate() != null) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					purchaseDate = dateFormat.format(txtPurchaseDate.getDate());
				}
				
				int ansiLumens = 0;
				boolean ansiLumensError = false;
				if (txtAnsiLumens.getText().length() != 0) {
					try {
						ansiLumens = Integer.parseInt(txtAnsiLumens.getText());
					} catch (Exception e) {
						ansiLumensError = true;
						txtAnsiLumens.requestFocus();
						JOptionPane.showMessageDialog(null, "Ansi Lumens aceita apenas Números", "Dados Inválidos", JOptionPane.ERROR_MESSAGE);
					}
				}
				if (!ansiLumensError) {
					String dateLastLampChange = null;
					if (txtDateLastLampChange.getDate() != null) {
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						dateLastLampChange = dateFormat.format(txtDateLastLampChange.getDate());
					}
					String projectorState = comboProjectorState.getSelectedItem().toString();
					
					boolean wasRegistered = database.registerProjector(brand, model, serialNumber, purchaseDate, ansiLumens, dateLastLampChange, projectorState);
					if (wasRegistered) {
						JOptionPane.showMessageDialog(null, "Projetor Cadastrado com sucesso", "Cadastro Realizado", JOptionPane.INFORMATION_MESSAGE);
						Home home = new Home(database);
						home.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Falha ao cadastrar projetor", "Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
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
