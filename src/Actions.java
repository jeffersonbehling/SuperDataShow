import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.fabric.xmlrpc.base.Data;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Actions extends JFrame {

	private JPanel contentPane;
	private Database database;
	private JLabel lblAbout = new JLabel("Sobre");
	private JLabel lblRenew = new JLabel("Renovar/Devolver");
	private JLabel lblProjectorRegister = new JLabel("Cadastrar Projetor");
	private JLabel lblRental = new JLabel("Relat\u00F3rios");
	private JLabel lblLocationRegister = new JLabel("Cadastrar Loca\u00E7\u00E3o");
	private JLabel lblClientRegister = new JLabel("Cadastrar Cliente");
	private JLabel label = new JLabel("Copyright \u00A9 IFFarSoft - Todos os Direitos Reservados");
	private final JMenuItem mnClients = new JMenuItem("Clientes");
	private final JMenuItem mnProjectors = new JMenuItem("Projetores");
	private final JMenu mnLocations = new JMenu("Loca\u00E7\u00E3o");
	private final JMenuItem mnLocation = new JMenuItem("Locar");
	private final JMenuItem mnReturn = new JMenuItem("Devolver");
	private final JMenuItem mnRenew = new JMenuItem("Renovar");
	private final JMenu mnHome = new JMenu("Home");
	private final JMenu mnEdition = new JMenu("Consultas");
	private final JMenuItem mnClient = new JMenuItem("Clientes");
	private final JMenuItem mntmProjetor = new JMenuItem("Projetores");
	private final JMenu mnExit = new JMenu("Sair");
	private final JMenu mnDatabase = new JMenu("Database");
	private final JMenuItem mnCreateDatabase = new JMenuItem("Criar Database");
	private final JMenuItem mnCreateTables = new JMenuItem("Criar Tabelas");
	private final JMenuItem mnInsertStates = new JMenuItem("Inserir Estados");
	private final JMenuItem mnInsertCities = new JMenuItem("Inserir Cidades");
	/**
	 * Create the frame.
	 */
	public Actions(Database database) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("resource//IFFarSoft.png"));
		setTitle("Home");
		setResizable(false);
		this.database = database;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 90, 850, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
				
		menuBar.add(mnHome);
		
		JMenu mnMenu = new JMenu("Cadastros");
		menuBar.add(mnMenu);
		
		mnClients.setIcon(new ImageIcon("resource//user-silhouette.png"));
		
		mnMenu.add(mnClients);
		
		mnProjectors.setIcon(new ImageIcon("resource//projector-item.png"));
		
		mnMenu.add(mnProjectors);
		
		menuBar.add(mnEdition);
		mnClient.setIcon(new ImageIcon("resource//magnifier.png"));
		
		mnEdition.add(mnClient);
		mntmProjetor.setIcon(new ImageIcon("resource//magnifier.png"));
		
		mnEdition.add(mntmProjetor);
		
		menuBar.add(mnLocations);
		
		mnLocation.setIcon(new ImageIcon("resource//projector-screen.png"));
		
		mnLocations.add(mnLocation);
		
		mnReturn.setIcon(new ImageIcon("resource//back-arrow.png"));
		
		mnLocations.add(mnReturn);
		mnRenew.setIcon(new ImageIcon("resource//refresh-page-arrow-button.png"));
		
		mnLocations.add(mnRenew);
		
		menuBar.add(mnDatabase);
		
		mnCreateDatabase.setIcon(new ImageIcon("resource//database-create.png"));
		
		mnDatabase.add(mnCreateDatabase);
		mnCreateTables.setIcon(new ImageIcon("resource//table-grid.png"));
		
		mnDatabase.add(mnCreateTables);
		mnInsertStates.setIcon(new ImageIcon("resource//brazil-map.png"));
		
		mnDatabase.add(mnInsertStates);
		mnInsertCities.setIcon(new ImageIcon("resource//location-mark.png"));
		
		mnDatabase.add(mnInsertCities);
		
		menuBar.add(mnExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblLocationRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocationRegister.setIcon(new ImageIcon("resource//add.png"));
		lblLocationRegister.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblClientRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientRegister.setIcon(new ImageIcon("resource//user.png"));
		lblClientRegister.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblRental.setIcon(new ImageIcon("resource//newspaper.png"));
		lblRental.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	
		lblRenew.setHorizontalAlignment(SwingConstants.CENTER);
		lblRenew.setIcon(new ImageIcon("resource//update-arrows.png"));
		lblRenew.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblProjectorRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjectorRegister.setIcon(new ImageIcon("resource//projector.png"));
		lblProjectorRegister.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblAbout.setIcon(new ImageIcon("resource//warning.png"));
		lblAbout.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		label.setForeground(Color.GRAY);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(65)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblLocationRegister)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblProjectorRegister, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblClientRegister)))
							.addGap(198)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblRental, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblAbout, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
								.addComponent(lblRenew)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label, GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLocationRegister)
						.addComponent(lblRental))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRenew)
						.addComponent(lblClientRegister))
					.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProjectorRegister, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAbout, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		actions();
		actionsMenu();
	}
	
	private void actions() {
		lblLocationRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LocationsRegister locationsRegister = new LocationsRegister(database);
				locationsRegister.setVisible(true);
				dispose();
			}
		});
		
		lblClientRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
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
		
		lblProjectorRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ProjectorsRegister projectorsRegister = new ProjectorsRegister(database);
				projectorsRegister.setVisible(true);
				dispose();
			}
		});
		
		lblRental.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				RentalReports rentalReports = new RentalReports(database);
				rentalReports.setVisible(true);
				dispose();
			}
		});
		
		lblRenew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PerformReturns performReturns = new PerformReturns(database);
				performReturns.setVisible(true);
				dispose();
			}
		});
		
		lblAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				About about = new About(database);
				about.setVisible(true);
				dispose();
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
		
		mntmProjetor.addActionListener(new ActionListener() {
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
		
		mnCreateDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean wasCreateDatabase = database.createDatabase();
				if (wasCreateDatabase) {
					JOptionPane.showMessageDialog(null, "Base de Dados Criada com sucesso", "Banco de Dados criado", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao criar Base de Dados", "Falha na criação do BD", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		mnCreateTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean wasTables = database.createTables();
				if (wasTables) {
					JOptionPane.showMessageDialog(null, "Tabelas Criadas com sucesso", "Tabelas criadas", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao criar Tabelas", "Falha na criação das tabelas", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		mnInsertStates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean wasInserted = database.insertStates();
				if (wasInserted) {
					JOptionPane.showMessageDialog(null, "Todos os Estados foram inseridos", "Dados Inseridos", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Falha ao inserir dados", "Falha na inserção", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		mnInsertStates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean wasInserted = database.insertCities();
				if (wasInserted) {
					JOptionPane.showMessageDialog(null, "Todos as Cidades foram inseridos", "Dados Inseridos", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Falha ao inserir dados", "Falha na inserção", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
