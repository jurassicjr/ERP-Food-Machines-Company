package rh.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;

/**
 * Responsável pela Janela de registro de novos funcionários
 */
public class RegisterEmployeeFrame extends JFrame {
	
	private static final long serialVersionUID = -5724629484141692770L;
	
	private JTabbedPane tabbedPane;
	
	private JPanel personalDataPanel;
	private JPanel bankingDataPanel;
	private JPanel buttonsPanel;
	
	private JTextField txName;
	private JTextField txCode;
	private JTextField txBirth;
	private JTextField txNacionality;
	private JTextField txBirthPlace;
	private JTextField txRg;
	private JTextField txCpf;
	private JTextField txCTPS;
	private JTextField txCtpsSeries;
	private JTextField txVoterRegistration;
	private JTextField txDriverLicense;
	private JTextField txReserve;
	private JTextField txAddress;
	private JTextField txNeighborhood;
	private JTextField txCep;
	private JTextField txPhone;
	private JTextField txCellPhone;
	
	private JComboBox<String> cbGender;
	private JComboBox<String> cbMaritalStatus;
	private JComboBox<String> cbSchooling;
	private JComboBox<String> cbDriverLicense;
	private JComboBox<String> cbReserveCategory;
	private JComboBox<String> cbState;
	private JComboBox<String> cbCity;
	
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Cria a janela de registro de funcionário
	 */
	public RegisterEmployeeFrame() {
		initialize();
	}

	/**
	 * Inicializa os elementos gráficos da janela de registro de usuário
	 */
	private void initialize() {
		
		setBounds(100, 100, 655, 500);
		setMinimumSize(new Dimension(655, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Registro de Funcionário");
		
		tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		initializePersonalDataPanel();
		tabbedPane.addTab("Dados Pessoais", personalDataPanel);
		
		initializeBankingDatePanel();
		tabbedPane.addTab("Dados Bancários", bankingDataPanel);
		
		initializeButtonsPanel();
		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);			

	}

	/**
	 * Inicializa os elementos gráficos para os dados pessoais
	 */
	private void initializePersonalDataPanel() {
		
		personalDataPanel = new JPanel();
		
		JLabel lblName = new JLabel("Nome");
		txName = new JTextField();
		
		JLabel lblPicture = new JLabel();
		ImageIcon image = new ImageIcon(getClass().getResource("/resources/profile.png"));
		ImageIcon thumbnail = new ImageIcon(image.getImage().getScaledInstance(80, -1, Image.SCALE_DEFAULT));
		lblPicture.setIcon(thumbnail);
		lblPicture.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
 		
		JLabel lblCode = new JLabel("Código");
		txCode = new JTextField();
		
		JLabel lblBirth = new JLabel("Data de Nascimento");
		txBirth = new JTextField();
		
		JLabel lblGender = new JLabel("Sexo");
		cbGender = new JComboBox<String>();
		cbGender.setModel(new DefaultComboBoxModel<String>(new String[] {"Masculino", "Feminino"}));
		
		JLabel lblMaritalStatus = new JLabel("Estado Civil");
		
		cbMaritalStatus = new JComboBox<String>();
		cbMaritalStatus.setModel(new DefaultComboBoxModel<String>(new String[] {"Solteiro(a)", "Casado(a)", "Divorciado(a)", "Viúvo(a)"}));
		
		JLabel lblNacionality = new JLabel("Nacionalidade");
		txNacionality = new JTextField();
		
		JLabel lblBirthPlace = new JLabel("Local de Nascimento");
		txBirthPlace = new JTextField();
		
		JLabel lblSchooling = new JLabel("Escolaridade");
		cbSchooling = new JComboBox<String>();
		cbSchooling.setModel(new DefaultComboBoxModel<String>(new String[] {"Ensino Fundamental Incompleto", "Ensino Fundamental Completo", "Ensino Médio Incompleto", "Ensino Médio Completo", "Ensino Superior Incompleto", "Ensino Superior Completo", "Pós-Graduação"}));
		
		JLabel lblRg = new JLabel("RG");
		txRg = new JTextField();
		
		JLabel lblCpf = new JLabel("CPF");
		txCpf = new JTextField();
		
		JLabel lblCtps = new JLabel("CTPS");
		txCTPS = new JTextField();
		
		JLabel lblCtpsSeries = new JLabel("Série");
		txCtpsSeries = new JTextField();
		
		JLabel lblVoterRegistration = new JLabel("Título de Eleitor");
		txVoterRegistration = new JTextField();
		
		JLabel lblDriverLicense = new JLabel("CNH");
		txDriverLicense = new JTextField();
		
		JLabel lblDriverLicenseCategory = new JLabel("Categoria CNH");
		cbDriverLicense = new JComboBox<String>();
		cbDriverLicense.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "B", "C", "D", "E"}));
		
		JLabel lblReserve = new JLabel("Carteira de Reservista");
		txReserve = new JTextField();
		
		JLabel lblReserveCategory = new JLabel("Categoria");
		cbReserveCategory = new JComboBox<String>();
		cbReserveCategory.setModel(new DefaultComboBoxModel<String>(new String[] {"1ª Categoria", "2ª Categoria"}));
		
		JLabel lblAddress = new JLabel("Endereço");
		txAddress = new JTextField();
		
		JLabel lblNeighborhood = new JLabel("Bairro");
		txNeighborhood = new JTextField();
		
		JLabel lblCep = new JLabel("CEP");
		txCep = new JTextField();
		
		JLabel lblState = new JLabel("Estado");
		cbState = new JComboBox<String>();
		
		JLabel lblCity = new JLabel("Cidade");
		cbCity = new JComboBox<String>();
		
		JLabel lblPhone = new JLabel("Telefone");
		txPhone = new JTextField();
		
		JLabel lblCellPhone = new JLabel("Celular");
		txCellPhone = new JTextField();
		
		GroupLayout personalDataPanelLayout = new GroupLayout(personalDataPanel);
		personalDataPanelLayout.setHorizontalGroup(
			personalDataPanelLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(personalDataPanelLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(personalDataPanelLayout.createSequentialGroup()
							.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(personalDataPanelLayout.createSequentialGroup()
									.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(personalDataPanelLayout.createSequentialGroup()
											.addComponent(lblName)
											.addGap(18)
											.addComponent(txName, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
											.addGap(18)
											.addComponent(lblCode)
											.addGap(18))
										.addGroup(personalDataPanelLayout.createSequentialGroup()
											.addComponent(lblBirth)
											.addGap(18)
											.addComponent(txBirth, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
											.addGap(18)
											.addComponent(lblGender)
											.addGap(18)
											.addComponent(cbGender, 0, 79, Short.MAX_VALUE)
											.addGap(18)
											.addComponent(lblMaritalStatus)
											.addGap(10)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(cbMaritalStatus, 0, 94, Short.MAX_VALUE)
										.addGroup(personalDataPanelLayout.createSequentialGroup()
											.addComponent(txCode, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
											.addGap(2)))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(personalDataPanelLayout.createSequentialGroup()
									.addComponent(lblNacionality)
									.addGap(18)
									.addComponent(txNacionality, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblBirthPlace)
									.addGap(18)
									.addComponent(txBirthPlace, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
							.addGap(18)
							.addComponent(lblPicture, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addGap(12))
						.addGroup(personalDataPanelLayout.createSequentialGroup()
							.addComponent(lblSchooling)
							.addGap(18)
							.addComponent(cbSchooling, 0, 172, Short.MAX_VALUE)
							.addGap(374))
						.addGroup(personalDataPanelLayout.createSequentialGroup()
							.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(personalDataPanelLayout.createSequentialGroup()
									.addComponent(lblVoterRegistration)
									.addGap(18)
									.addComponent(txVoterRegistration, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
								.addGroup(personalDataPanelLayout.createSequentialGroup()
									.addComponent(lblRg)
									.addGap(18)
									.addComponent(txRg, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblCpf)))
							.addGap(18)
							.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(personalDataPanelLayout.createSequentialGroup()
									.addComponent(txCpf, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblCtps))
								.addGroup(personalDataPanelLayout.createSequentialGroup()
									.addComponent(lblDriverLicense)
									.addGap(18)
									.addComponent(txDriverLicense, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)))
							.addGap(18)
							.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(personalDataPanelLayout.createSequentialGroup()
									.addComponent(txCTPS, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblCtpsSeries))
								.addGroup(personalDataPanelLayout.createSequentialGroup()
									.addComponent(lblDriverLicenseCategory)
									.addGap(18)
									.addComponent(cbDriverLicense, 0, 38, Short.MAX_VALUE)))
							.addGap(18)
							.addComponent(txCtpsSeries, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(personalDataPanelLayout.createSequentialGroup()
							.addComponent(lblReserve)
							.addGap(18)
							.addComponent(txReserve, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblReserveCategory)
							.addGap(18)
							.addComponent(cbReserveCategory, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addGap(178))
						.addGroup(personalDataPanelLayout.createSequentialGroup()
							.addComponent(lblAddress)
							.addGap(18)
							.addComponent(txAddress, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblNeighborhood)
							.addGap(18)
							.addComponent(txNeighborhood, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblCep)
							.addGap(18)
							.addComponent(txCep, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(personalDataPanelLayout.createSequentialGroup()
							.addComponent(lblState)
							.addGap(18)
							.addComponent(cbState, 0, 135, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblCity)
							.addGap(18)
							.addComponent(cbCity, 0, 132, Short.MAX_VALUE)
							.addGap(237))
						.addGroup(personalDataPanelLayout.createSequentialGroup()
							.addComponent(lblPhone)
							.addGap(18)
							.addComponent(txPhone, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblCellPhone)
							.addGap(18)
							.addComponent(txCellPhone, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
							.addGap(287))))
		);
		personalDataPanelLayout.setVerticalGroup(
			personalDataPanelLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(personalDataPanelLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblPicture, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addComponent(cbMaritalStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(personalDataPanelLayout.createSequentialGroup()
							.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblName)
								.addComponent(txName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCode)
								.addComponent(txCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBirth)
								.addComponent(txBirth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGender)
								.addComponent(cbGender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMaritalStatus))
							.addGap(18)
							.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNacionality)
								.addComponent(txNacionality, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBirthPlace)
								.addComponent(txBirthPlace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSchooling)
						.addComponent(cbSchooling, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRg)
						.addComponent(txRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCpf)
						.addComponent(txCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCtps)
						.addComponent(txCTPS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCtpsSeries)
						.addComponent(txCtpsSeries, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVoterRegistration)
						.addComponent(txVoterRegistration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDriverLicense)
						.addComponent(txDriverLicense, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDriverLicenseCategory)
						.addComponent(cbDriverLicense, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblReserve)
						.addComponent(txReserve, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReserveCategory)
						.addComponent(cbReserveCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress)
						.addComponent(txAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNeighborhood)
						.addComponent(txNeighborhood, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCep)
						.addComponent(txCep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblState)
						.addComponent(cbState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCity)
						.addComponent(cbCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(personalDataPanelLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhone)
						.addComponent(txPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCellPhone)
						.addComponent(txCellPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		personalDataPanel.setLayout(personalDataPanelLayout);
		
	}
	
	private void initializeBankingDatePanel() {
		
		bankingDataPanel = new JPanel();
		
	}
	
	/**
	 * Inicializa os elementos gráficos dos botões de confirmação e cancelamento
	 */
	private void initializeButtonsPanel() {
		
		buttonsPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonsPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		
		cancelButton = new JButton("Cancelar");
		cancelButton.setIcon(new ImageIcon(getClass().getResource("/resources/cancel.png")));
		buttonsPanel.add(cancelButton);
		
		okButton = new JButton("Registrar Funcionário");
		okButton.setIcon(new ImageIcon(getClass().getResource("/resources/ok.png")));
		buttonsPanel.add(okButton);
		
	}
	
}
