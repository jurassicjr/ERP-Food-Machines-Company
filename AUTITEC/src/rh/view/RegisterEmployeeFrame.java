package rh.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import model.Bank;
import model.CBO;
import model.CNPJ;
import model.City;
import model.State;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import net.sf.nachocalendar.table.JTableCustomizer;
import rh.controller.RegisterEmployeeController;
import userInterface.components.ComboBoxAutoCompletion;
import userInterface.components.FileChooser;
import userInterface.components.UpperCaseEditor;
import userInterface.components.UpperTextField;
import util.Icon;

/**
 * Representa o frame de registro de funcionários
 */
public class RegisterEmployeeFrame extends JFrame {
	
	private static final long serialVersionUID = -4506446090751738206L;
	
	private JTabbedPane tabbedPane;
	private JPanel personalDataPanel;
	private JPanel buttonsPanel;
	private JPanel contactDataPanel;
	private JPanel dependentDataPanel;
	private JPanel jobDataPanel;
	private JPanel socialIntegrationProgramPanel;
	private JScrollPane dependentScrollPane;
	
	private UpperTextField txName;
	private UpperTextField txReservist;
	private UpperTextField txBirthPlace;
	private UpperTextField txNacionality;
	private UpperTextField txCtps;
	private UpperTextField txCptsCategory;
	private UpperTextField txAddress;
	private UpperTextField txNeighborhood;
	private JTextField txCbo; 
	private JTextField txSalary; 
	private UpperTextField txAgency; 
	private UpperTextField txAccount;
	private UpperTextField txCadastreNumber; 
	private UpperTextField txSocialIntegrationAgency; 
	private UpperTextField txSocialIntegrationAddress; 
	private UpperTextField txReservistCategory; 
	private JFormattedTextField txRg;
	private JFormattedTextField txVoter;
	private JFormattedTextField txCpf;
	private JFormattedTextField txDriverLicense;
	private JFormattedTextField txCep;
	private JFormattedTextField txPhone;
	private JFormattedTextField txCellphone;
	
	private DateField txBirth;
	private DateField txOptionDate;
	private DateField txAdmissionDate;
	private DateField txRetractionDate;
	private DateField txCadastreDate;
	
	private JComboBox<String> cbMaritalStatus;
	private JComboBox<String> cbDriverLicenseCategory;
	private JComboBox<String> cbGender;
	
	private JComboBox<String> cbSchooling;
	private JComboBox<State> cbState;
	private JComboBox<City> cbCity;
	private JComboBox<Bank> cbDepositaryBank;
	private JComboBox<Bank> cbBank;
	private JComboBox<CBO> cbJob;
	private JComboBox<String> cbPayment;
	private JComboBox<Bank> cbSocialIntegrationBank;
	private JComboBox<CNPJ> cbRegisterCnpj;
	
	private JLabel lblPicture;
	
	private JTable dependentTable;
	
	private JButton btnCancel;
	private JButton btnConfirme;
	
	private String picturePath;
	private RegisterEmployeeController controller;
	private FileChooser fileChooser;
	
	/**
	 * Cria o frame de registro de funcionário
	 */
	public RegisterEmployeeFrame() {
		
		controller = new RegisterEmployeeController(this);
		picturePath = null;
		fileChooser = new FileChooser(this);
		
		initialize();
		setListeners();		
	}

	/**
	 * Inicializa os componentes do frame
	 */
	private void initialize() {
		
		setBounds(100, 100, 829, 404);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(829, 404));
		setTitle("Registro de Funcionário");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Icon.setIcon(this);
		
		tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		initializePersonalDataPanel();
		tabbedPane.addTab("Dados Pessoais", personalDataPanel);
		
		initializeContactDataPanel();
		tabbedPane.addTab("Informações de Contato", contactDataPanel);
				
		initializeJobDataPanel();
		tabbedPane.addTab("Dados do Cargo", jobDataPanel);
		
		initializeSocialIntegrationProgramPanel();
		tabbedPane.addTab("Programa de Integração Social", socialIntegrationProgramPanel);
				
		initializeDependentDataPanel();
		tabbedPane.addTab("Dependentes", dependentDataPanel);
		
		initializeButtonsPanel();		
		
		controller.fillStateAndCity(cbState, cbCity);
		controller.fillCBO(cbJob, txCbo);
		controller.fillBanks(cbBank);
		controller.fillBanks(cbDepositaryBank);
		controller.fillBanks(cbSocialIntegrationBank);
		controller.fillCnpj(cbRegisterCnpj);	

		new ComboBoxAutoCompletion(cbState);
		new ComboBoxAutoCompletion(cbCity);
		new ComboBoxAutoCompletion(cbJob);
		new ComboBoxAutoCompletion(cbBank);
		new ComboBoxAutoCompletion(cbDepositaryBank);
		new ComboBoxAutoCompletion(cbSocialIntegrationBank);
		new ComboBoxAutoCompletion(cbRegisterCnpj);
				
	}
	
	/**
	 * Inicializa os componentes gráficos para registro dos dados pessoais
	 */
	private void initializePersonalDataPanel() {
		
		personalDataPanel = new JPanel();
		
		JLabel lblName = new JLabel("Nome");
		txName = new UpperTextField();
		
		JLabel lblBirth = new JLabel("Data de Nascimento");
		txBirth = CalendarFactory.createDateField();
		txBirth.setValue(null);
		
		JLabel lblNacionality = new JLabel("Nacionalidade");
		txNacionality = new UpperTextField();
		
		JLabel lblRg = new JLabel("Registro Geral (RG)");
		try { txRg = new JFormattedTextField(new MaskFormatter("##.###.###-A")); }
		catch (ParseException e) { e.printStackTrace(); }
				
		JLabel lblVoter = new JLabel("Título de Eleitor");
		try { txVoter = new JFormattedTextField(new MaskFormatter("#### #### #### ####")); }
		catch (ParseException e) { e.printStackTrace(); }
		
		JLabel lblReservist = new JLabel("Reservista");
		txReservist = new UpperTextField();
		txReservist.setEditable(false);
		
		JLabel lblMaritalStatus = new JLabel("Estado Civil");
		cbMaritalStatus = new JComboBox<String>();
		cbMaritalStatus.setModel(new DefaultComboBoxModel<String>(new String[] {"Solteiro(a)", "Casado(a)", "Divorciado(a)", "Viúvo(a)"}));
		cbMaritalStatus.setSelectedIndex(-1);
		
		JLabel lblBirthPlace = new JLabel("Local de Nascimento");
		txBirthPlace = new UpperTextField();
		
		JLabel lblCpf = new JLabel("CPF");
		try { txCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##")); } 
		catch (ParseException e) { e.printStackTrace(); }
			
		JLabel lblDriverLicense = new JLabel("Carteira de Habilitação");
		try { txDriverLicense = new JFormattedTextField(new MaskFormatter("###########")); }
		catch (ParseException e) { e.printStackTrace(); }
		
		JLabel lblDriverLicenseCategory = new JLabel("Categoria");		
		cbDriverLicenseCategory = new JComboBox<String>();
		cbDriverLicenseCategory.setModel(new DefaultComboBoxModel<String>(new String[] {"Categoria A", "Categoria B", "Categoria AB", "Categoria AC", "Categoria AD", "Categoria AE", "Categoria C", "Categoria D", "Categoria E"}));
		cbDriverLicenseCategory.setSelectedIndex(-1);
		
		JLabel lblCtps = new JLabel("CTPS");
		txCtps = new UpperTextField();
		
		JLabel lblCptsCategory = new JLabel("Categoria");
		txCptsCategory = new UpperTextField();
		
		JLabel lblGender = new JLabel("Sexo");
		cbGender = new JComboBox<String>();
		cbGender.setModel(new DefaultComboBoxModel<String>(new String[] {"Masculino", "Feminino"}));
		cbGender.setSelectedIndex(-1);
		
		JLabel lblReservistCategory = new JLabel("Categoria");
		txReservistCategory = new UpperTextField();
		txReservistCategory.setEditable(false);
		
		JLabel lblSchooling = new JLabel("Escolaridade");
		cbSchooling = new JComboBox<String>();
		cbSchooling.setModel(new DefaultComboBoxModel<String>(new String[] {"Fundamental Incompleto", "Fundamental Completo", "Médio Incompleto", "Médio Completo", "Técnico Incompleto", "Técnico Completo", "Superior Incompleto", "Superior Completo", "Pós-Graduado"}));
		cbSchooling.setSelectedIndex(-1);
		
		lblPicture = new JLabel();	
		int labelWidth = 95;
		ImageIcon imagem = new ImageIcon(getClass().getResource("/resources/profile.png"));
		ImageIcon thumbnail = null;
		if(imagem.getIconWidth() > labelWidth){       
			thumbnail = new ImageIcon(imagem.getImage().getScaledInstance(labelWidth, -1, Image.SCALE_DEFAULT));  
		}
		else thumbnail = imagem;
		lblPicture.setIcon(thumbnail);
		lblPicture.setBorder(new EtchedBorder());
		
		GroupLayout layout = new GroupLayout(personalDataPanel);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
									.addComponent(lblName)
									.addGap(18)
									.addComponent(txName, GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
									.addComponent(lblBirth)
									.addGap(18)
									.addComponent(txBirth, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblGender)
									.addGap(18)
									.addComponent(cbGender, 0, 158, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblMaritalStatus)
									.addGap(18)
									.addComponent(cbMaritalStatus, 0, 125, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
									.addComponent(lblNacionality)
									.addGap(18)
									.addComponent(txNacionality, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
									.addGap(19)
									.addComponent(lblBirthPlace)
									.addGap(18)
									.addComponent(txBirthPlace, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblPicture, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblRg)
							.addGap(18)
							.addComponent(txRg, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblCpf)
							.addGap(18)
							.addComponent(txCpf, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblCtps)
							.addGap(18)
							.addComponent(txCtps, 95, 95, 95)
							.addGap(18)
							.addComponent(lblCptsCategory)
							.addGap(18)
							.addComponent(txCptsCategory, 0, 73, Short.MAX_VALUE)
							.addGap(117))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblVoter)
							.addGap(18)
							.addComponent(txVoter, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblDriverLicense)
							.addGap(18)
							.addComponent(txDriverLicense, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblDriverLicenseCategory)
							.addGap(18)
							.addComponent(cbDriverLicenseCategory, 0, 91, Short.MAX_VALUE)
							.addGap(117))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblSchooling)
							.addGap(18)
							.addComponent(cbSchooling, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblReservist)
							.addGap(18)
							.addComponent(txReservist, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblReservistCategory)
							.addGap(18)
							.addComponent(txReservistCategory, 0, 99, Short.MAX_VALUE)
							.addGap(117))))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPicture, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblName)
								.addComponent(txName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBirth)
								.addComponent(txBirth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGender)
								.addComponent(cbGender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMaritalStatus)
								.addComponent(cbMaritalStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNacionality)
								.addComponent(txNacionality, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBirthPlace)
								.addComponent(txBirthPlace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRg)
								.addComponent(txRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCpf)
								.addComponent(txCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCtps)
								.addComponent(txCtps, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCptsCategory)
								.addComponent(txCptsCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVoter)
								.addComponent(txVoter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txDriverLicense, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbDriverLicenseCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDriverLicenseCategory)
								.addComponent(lblDriverLicense))
							.addGap(18)
							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblReservistCategory)
								.addComponent(txReservistCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txReservist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSchooling)
								.addComponent(cbSchooling, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblReservist))))
					.addContainerGap(81, Short.MAX_VALUE))
		);
		personalDataPanel.setLayout(layout);
		
	}

	/**
	 * Inicializa os componentes gráficos para o registo das informações de contato
	 */
	private void initializeContactDataPanel() {
		
		contactDataPanel = new JPanel();
				
		JLabel lblAddress = new JLabel("Endereço");
		txAddress = new UpperTextField();
		
		JLabel lblCep = new JLabel("CEP");
		try { txCep = new JFormattedTextField(new MaskFormatter("##.###-###")); }
		catch (ParseException e) { e.printStackTrace(); }
		
		JLabel lblState = new JLabel("Estado");
		cbState = new JComboBox<State>();
		
		JLabel lblCity = new JLabel("Cidade");
		cbCity = new JComboBox<City>();
		cbCity.setEnabled(false);
		
		JLabel lblNeighborhood = new JLabel("Bairro");
		txNeighborhood = new UpperTextField();	
		
		JLabel lblPhone = new JLabel("Telefone");
		try { txPhone = new JFormattedTextField(new MaskFormatter("(##) ####-####")); }
		catch (ParseException e) { e.printStackTrace(); }
		
		JLabel lblCellphone = new JLabel("Celular");
		try { txCellphone = new JFormattedTextField(new MaskFormatter("(##) #####-####")); }
		catch (ParseException e) { e.printStackTrace(); }
		
		GroupLayout layout = new GroupLayout(contactDataPanel);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblCep)
							.addGap(18)
							.addComponent(txCep, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblState)
							.addGap(12)
							.addComponent(cbState, 0, 87, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblCity)
							.addGap(18)
							.addComponent(cbCity, 0, 205, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblAddress)
							.addGap(18)
							.addComponent(txAddress, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblNeighborhood)
							.addGap(18)
							.addComponent(txNeighborhood, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblPhone)
							.addGap(18)
							.addComponent(txPhone, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblCellphone)
							.addGap(18)
							.addComponent(txCellphone, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
							.addGap(211)))
					.addGap(35))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress)
						.addComponent(txAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNeighborhood)
						.addComponent(txNeighborhood, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCep)
						.addComponent(txCep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCity)
						.addComponent(cbCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblState))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhone)
						.addComponent(txPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCellphone)
						.addComponent(txCellphone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(195, Short.MAX_VALUE))
		);
		contactDataPanel.setLayout(layout);		
	}
	
	/**
	 * Inicializa os componentes gráficos para o registro de dependentes
	 */
	private void initializeDependentDataPanel() {
		
		dependentDataPanel = new JPanel();
		dependentDataPanel.setLayout(new BorderLayout(0, 0));
		
		dependentScrollPane = new JScrollPane();
		dependentDataPanel.add(dependentScrollPane, BorderLayout.CENTER);
		
		dependentTable = new JTable();
		dependentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dependentTable.setCellSelectionEnabled(true);
        dependentTable.getTableHeader().setReorderingAllowed(false);
		
		dependentTable.setModel(new DefaultTableModel(
			new Object[][] {{"", "", ""},},
			new String[] {"Nome", "Relação", "Data de Nascimento/Casamento"}
		){
			
			private static final long serialVersionUID = -7331676150193648559L;
			
			Class<?>[] columnTypes = new Class[] {String.class, String.class, Date.class};
			
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			
		});
		
		JTableCustomizer.setEditorForRow(dependentTable, 2);
		dependentTable.setDefaultEditor(String.class, new UpperCaseEditor());
		
		dependentScrollPane.setViewportView(dependentTable);	
		
	}
		
	/**
	 * Inicializa os componentes gráficos para registro das opções do cargo
	 */
	private void initializeJobDataPanel() {
		
		jobDataPanel = new JPanel();
		
		JLabel lblAdmissionDate = new JLabel("Data de Admissão");
		txAdmissionDate = new DateField();
		txAdmissionDate.setValue(null);
		
		JLabel lblJob = new JLabel("Natureza do Cargo");
		cbJob = new JComboBox<CBO>();
		
		JLabel lblCbo = new JLabel("CBO");
		txCbo = new JTextField();
		txCbo.setEditable(false);
		
		JLabel lblSalary = new JLabel("Salário Inicial R$");
		txSalary = new JTextField();
		
		JLabel lblPayment = new JLabel("Forma de Pagamento");
		cbPayment = new JComboBox<String>();
		cbPayment.setModel(new DefaultComboBoxModel<String>(new String[] {"Mensal", "Horista", "Contrato", "Semanal", "Quinzenal"}));
		cbPayment.setSelectedIndex(-1);
		
		JPanel guaranteeFundDataPanel = new JPanel();
		guaranteeFundDataPanel.setBorder(new TitledBorder(null, "Fundo de Garantia por Tempo de Servi\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel bankingDataPanel = new JPanel();
		bankingDataPanel.setBorder(new TitledBorder("Dados Bancários"));
		
		JLabel lblRegisterCnpj = new JLabel("CNPJ de Registro");
		cbRegisterCnpj = new JComboBox<CNPJ>();
		
		GroupLayout jobDataPanelLayout = new GroupLayout(jobDataPanel);
		jobDataPanelLayout.setHorizontalGroup(
			jobDataPanelLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(jobDataPanelLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jobDataPanelLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(guaranteeFundDataPanel, GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
						.addComponent(bankingDataPanel, GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
						.addGroup(jobDataPanelLayout.createSequentialGroup()
							.addGroup(jobDataPanelLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(jobDataPanelLayout.createSequentialGroup()
									.addComponent(lblSalary)
									.addGap(18)
									.addComponent(txSalary, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblPayment))
								.addGroup(jobDataPanelLayout.createSequentialGroup()
									.addComponent(lblAdmissionDate)
									.addGap(18)
									.addComponent(txAdmissionDate, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblJob)))
							.addGap(18)
							.addGroup(jobDataPanelLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(jobDataPanelLayout.createSequentialGroup()
									.addComponent(cbJob, 0, 350, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblCbo)
									.addGap(18)
									.addComponent(txCbo, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
								.addGroup(jobDataPanelLayout.createSequentialGroup()
									.addComponent(cbPayment, 0, 161, Short.MAX_VALUE)
									.addGap(318))))
						.addGroup(jobDataPanelLayout.createSequentialGroup()
							.addComponent(lblRegisterCnpj)
							.addGap(18)
							.addComponent(cbRegisterCnpj, 0, 558, Short.MAX_VALUE)))
					.addContainerGap())
		);
		jobDataPanelLayout.setVerticalGroup(
			jobDataPanelLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(jobDataPanelLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jobDataPanelLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdmissionDate)
						.addComponent(txAdmissionDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbJob, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txCbo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCbo)
						.addComponent(lblJob))
					.addGap(18)
					.addGroup(jobDataPanelLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSalary)
						.addComponent(txSalary, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPayment)
						.addComponent(cbPayment, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(jobDataPanelLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRegisterCnpj)
						.addComponent(cbRegisterCnpj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
					.addComponent(bankingDataPanel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(guaranteeFundDataPanel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		jobDataPanel.setLayout(jobDataPanelLayout);
		
		JLabel lblBank = new JLabel("Banco");
		cbBank = new JComboBox<Bank>();
		
		JLabel lblAgency = new JLabel("Agência");
		txAgency = new UpperTextField();
		
		JLabel lblAccount = new JLabel("Conta");
		txAccount = new UpperTextField();
		
		GroupLayout bankingDataPanellayout = new GroupLayout(bankingDataPanel);
		bankingDataPanellayout.setHorizontalGroup(
			bankingDataPanellayout.createParallelGroup(Alignment.LEADING)
				.addGroup(bankingDataPanellayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBank)
					.addGap(18)
					.addComponent(cbBank, 0, 299, Short.MAX_VALUE)
					.addGap(35)
					.addComponent(lblAgency)
					.addGap(18)
					.addComponent(txAgency, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblAccount)
					.addGap(18)
					.addComponent(txAccount, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
					.addContainerGap())
		);
		bankingDataPanellayout.setVerticalGroup(
			bankingDataPanellayout.createParallelGroup(Alignment.LEADING)
				.addGroup(bankingDataPanellayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(bankingDataPanellayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBank)
						.addComponent(cbBank, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAgency)
						.addComponent(txAgency, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAccount)
						.addComponent(txAccount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		bankingDataPanel.setLayout(bankingDataPanellayout);
		
		JLabel lblRetractionDate = new JLabel("Data de Retratação");
		txRetractionDate = new DateField();
		txRetractionDate.setValue(null);
		
		JLabel lblDepositaryBank = new JLabel("Banco Depositário");
		cbDepositaryBank = new JComboBox<Bank>();
		
		JLabel lblOptionDate = new JLabel("Data de Opção");
		txOptionDate = new DateField();
		txOptionDate.setValue(null);		
		
		GroupLayout guaranteeFundDataPanelLayout = new GroupLayout(guaranteeFundDataPanel);
		guaranteeFundDataPanelLayout.setHorizontalGroup(
			guaranteeFundDataPanelLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(guaranteeFundDataPanelLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblOptionDate)
					.addGap(18)
					.addComponent(txOptionDate, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblRetractionDate)
					.addGap(18)
					.addComponent(txRetractionDate, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblDepositaryBank)
					.addGap(18)
					.addComponent(cbDepositaryBank, 0, 203, Short.MAX_VALUE)
					.addContainerGap())
		);
		guaranteeFundDataPanelLayout.setVerticalGroup(
			guaranteeFundDataPanelLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(guaranteeFundDataPanelLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(guaranteeFundDataPanelLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOptionDate)
						.addComponent(txOptionDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbDepositaryBank, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRetractionDate)
						.addComponent(txRetractionDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDepositaryBank))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		guaranteeFundDataPanel.setLayout(guaranteeFundDataPanelLayout);

	}
	
	/**
	 * Inicializa os componentes gráficos para registro de progrmas de integração social
	 */
	private void initializeSocialIntegrationProgramPanel() {
		
		socialIntegrationProgramPanel = new JPanel();
		
		JLabel lblCadastreDate = new JLabel("Data de Cadastro");
		txCadastreDate = new DateField();
		txCadastreDate.setValue(null);
		
		JLabel lblCadastreNumber = new JLabel("Número de Cadastro");
		txCadastreNumber = new UpperTextField();
		
		JLabel lblSocialIntegrationBank = new JLabel("Banco");
		cbSocialIntegrationBank = new JComboBox<Bank>();
		
		JLabel lblSocialIntegrationAgency = new JLabel("Agência");
		txSocialIntegrationAgency = new UpperTextField();
		
		JLabel lblSocialIntegrationAddress = new JLabel("Endereço");
		txSocialIntegrationAddress = new UpperTextField();
		
		GroupLayout socialIntegrationProgramPanellayout = new GroupLayout(socialIntegrationProgramPanel);
		socialIntegrationProgramPanellayout.setHorizontalGroup(
			socialIntegrationProgramPanellayout.createParallelGroup(Alignment.LEADING)
				.addGroup(socialIntegrationProgramPanellayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(socialIntegrationProgramPanellayout.createParallelGroup(Alignment.LEADING)
						.addGroup(socialIntegrationProgramPanellayout.createSequentialGroup()
							.addComponent(lblSocialIntegrationBank)
							.addGap(18)
							.addComponent(cbSocialIntegrationBank, 0, 170, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblSocialIntegrationAgency)
							.addGap(18)
							.addComponent(txSocialIntegrationAgency, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblSocialIntegrationAddress)
							.addGap(26)
							.addComponent(txSocialIntegrationAddress, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
							.addGap(117))
						.addGroup(socialIntegrationProgramPanellayout.createSequentialGroup()
							.addComponent(lblCadastreDate)
							.addGap(18)
							.addComponent(txCadastreDate, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblCadastreNumber)
							.addGap(18)
							.addComponent(txCadastreNumber, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
							.addGap(208))))
		);
		socialIntegrationProgramPanellayout.setVerticalGroup(
			socialIntegrationProgramPanellayout.createParallelGroup(Alignment.LEADING)
				.addGroup(socialIntegrationProgramPanellayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(socialIntegrationProgramPanellayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCadastreDate)
						.addComponent(txCadastreDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCadastreNumber)
						.addComponent(txCadastreNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(socialIntegrationProgramPanellayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSocialIntegrationBank)
						.addComponent(cbSocialIntegrationBank, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSocialIntegrationAgency)
						.addComponent(txSocialIntegrationAgency, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txSocialIntegrationAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSocialIntegrationAddress))
					.addContainerGap(233, Short.MAX_VALUE))
		);
		socialIntegrationProgramPanel.setLayout(socialIntegrationProgramPanellayout);
		
	}
	
	/**
	 * Inicializa os componentes ggráficos dos botões inferiores
	 */
	private void initializeButtonsPanel() {
		
		buttonsPanel = new JPanel();
		
		FlowLayout flowLayout = (FlowLayout) buttonsPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(getClass().getResource("/resources/cancel.png")));
		buttonsPanel.add(btnCancel);
		
		btnConfirme = new JButton("Confirmar");
		btnConfirme.setIcon(new ImageIcon(getClass().getResource("/resources/ok.png")));
		buttonsPanel.add(btnConfirme);
		
	}
	
	/**
	 * Define as ações dos elementos gráficos
	 */
	private void setListeners() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeFrame();
			}
		});
				
		dependentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
            	dependentTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE); //se não havia terminado a edição  
            }
        });
		
		dependentTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
            	tableKeyPressed(e);
            }
        });
		
		lblPicture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				picturePath = controller.loadProfilePicture(fileChooser, lblPicture);
			}			
		});
		
		
		ActionListener btnListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource().equals(btnCancel)) controller.closeFrame();
				else if(e.getSource().equals(btnConfirme)) registerEmployee();
				
			}
		};
		
		btnCancel.addActionListener(btnListener);
		btnConfirme.addActionListener(btnListener);
		
		cbGender.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if(cbGender.getSelectedIndex() == 0) {
					txReservist.setEditable(true);
					txReservistCategory.setEditable(true);
				}
				else {
					txReservist.setEditable(false);
					txReservistCategory.setEditable(false);
				}
				
			}
		});
					
	}
		
	/**
	 * Trata o evento de tecla pressionada sobre a tabela 
	 * 
	 * @param e Objeto do evento de pressão do botão
	 */
	private void tableKeyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN){ //a tecla pressionada foi enter
            if(dependentTable.getSelectedRow() == dependentTable.getModel().getRowCount() -1){ //se for a última linha da tabela
            	DefaultTableModel model = (DefaultTableModel) dependentTable.getModel();
                model.addRow(new Object[]{null, null}); //adiciona a nova linha
            }
        }
		
	}
	
	/**
	 * Invoca o método para registrar o funcionário
	 */
	private void registerEmployee() {
						
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("name", txName.getText());
		data.put("birth", txBirth.getValue());
		data.put("gender", cbGender.getSelectedIndex());
		data.put("maritial_status", cbMaritalStatus.getSelectedIndex());
		data.put("nacionality", txNacionality.getText());
		data.put("birth_place", txBirthPlace.getText());
		data.put("rg", txRg.getText());
		data.put("cpf", txCpf.getText());
		data.put("cpts", txCtps.getText());
		data.put("cpts_category", txCptsCategory.getText());
		data.put("voter", txVoter.getText());
		data.put("driver_license", txDriverLicense.getText());
		data.put("driver_license_category", cbDriverLicenseCategory.getSelectedIndex());
		data.put("schooling", cbSchooling.getSelectedIndex());
		data.put("reservist", txReservist.getText());
		data.put("reservist_category", txReservistCategory.getText());
		data.put("picture", picturePath);
		data.put("address", txAddress.getText());
		data.put("neighborhood", txNeighborhood.getText());
		data.put("cep", txCep.getText());
		data.put("city", cbCity.getSelectedItem());
		data.put("phone", txPhone.getText());
		data.put("cellphone", txCellphone.getText());
		data.put("admission_date", txAdmissionDate.getValue());
		data.put("job", cbJob.getSelectedItem());
		data.put("registration_cnpj", cbRegisterCnpj.getSelectedItem());
		data.put("salary", txSalary.getText());
		data.put("payment", cbPayment.getSelectedIndex());
		data.put("bank", cbBank.getSelectedItem());
		data.put("agency", txAgency.getText());
		data.put("account", txAccount.getText());
		data.put("option_date", txOptionDate.getValue());
		data.put("retraction_date", txRetractionDate.getValue());
		data.put("depositary_bank", cbDepositaryBank.getSelectedItem());
		data.put("cadastre_date", txCadastreDate.getValue());
		data.put("social_integration_cadastre_number", txCadastreNumber.getText());
		data.put("social_integration_bank", cbSocialIntegrationBank.getSelectedItem());
		data.put("social_integration_agency", txSocialIntegrationAgency.getText());
		data.put("social_integration_address", txSocialIntegrationAddress.getText());
		data.put("dependents", dependentTable);
		
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));  
		controller.registerEmployee(data);
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
	}
}
