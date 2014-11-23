package rh.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import userInterface.components.ComboBoxAutoCompletion;
import core.City;
import core.State;
import database.FillCBO;
import database.FillStateAndCity;

/**
 * Representa o frame de registro de funcionários
 */
public class RegisterEmployeeFrame extends JFrame {
	
	private static final long serialVersionUID = -4506446090751738206L;
	
	private JTabbedPane tabbedPane;
	private JPanel personalDataPanel;
	private JPanel buttonsPanel;
	private JPanel contactDataPanel;
	private JPanel bankingDataPanel;
	private JPanel dependentDataPanel;
	
	private JTextField txName;
	private JTextField txBirth;
	private JTextField txRg;
	private JTextField txVoter;
	private JTextField txReservist;
	private JTextField txCode;
	private JTextField txBirthPlace;
	private JTextField txNacionality;
	private JTextField txCpf;
	private JTextField txDriverLicense;
	private JTextField txCtps;
	private JTextField txCptsCategory;
	private JTextField txAddress;
	private JTextField txCep;
	private JTextField txNeighborhood;
	private JTextField txPhone;
	private JTextField txCellphone;
	
	private JComboBox<String> cbMaritalStatus;
	private JComboBox<String> cbDriverLicenseCategory;
	private JComboBox<String> cbGender;
	private JComboBox<String> cbReservistCategory;
	private JComboBox<String> cbSchooling;
	private JComboBox<State> cbState;
	private JComboBox<City> cbCity;
	
	private JButton btnCancel;
	private JButton btnConfirme;
	private JScrollPane dependentScrollPane;
	private JTable dependentTable;
	
	/**
	 * Cria o frame de registro de funcionário
	 */
	public RegisterEmployeeFrame() {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		initializePersonalDataPanel();
		tabbedPane.addTab("Dados Pessoais", personalDataPanel);
		
		initializeContactDataPanel();
		tabbedPane.addTab("Informações de Contato", contactDataPanel);
		
		bankingDataPanel = new JPanel();
		tabbedPane.add("Dados Bancários", bankingDataPanel);
		
		
		initializeDependentDataPanel();
		tabbedPane.addTab("Dependentes", dependentDataPanel);
		
		initializaButtonsPanel();
		
		new FillStateAndCity(cbState, cbCity);
		//new FillCBO(cb)

		new ComboBoxAutoCompletion(cbState);
		new ComboBoxAutoCompletion(cbCity);
		
	}
	
	/**
	 * Inicializa os componentes gráficos para registro dos dados pessoais
	 */
	private void initializePersonalDataPanel() {
		
		personalDataPanel = new JPanel();
		
		JLabel lblName = new JLabel("Nome");
		txName = new JTextField();
		
		JLabel lblBirth = new JLabel("Data de Nascimento");
		txBirth = new JTextField();
		
		JLabel lblNacionality = new JLabel("Nacionalidade");
		txNacionality = new JTextField();
		
		JLabel lblRg = new JLabel("Registro Geral (RG)");
		txRg = new JTextField();
		
		JLabel lblVoter = new JLabel("Título de Eleitor");
		txVoter = new JTextField();
		
		JLabel lblReservist = new JLabel("Reservista");
		txReservist = new JTextField();		
		
		JLabel lblCode = new JLabel("Código");
		txCode = new JTextField();
		
		JLabel lblMaritalStatus = new JLabel("Estado Civil");
		cbMaritalStatus = new JComboBox<String>();
		cbMaritalStatus.setModel(new DefaultComboBoxModel<String>(new String[] {"Solteiro(a)", "Casado(a)", "Divorciado(a)", "Viúvo(a)"}));
		
		JLabel lblBirthPlace = new JLabel("Local de Nascimento");
		txBirthPlace = new JTextField();
		
		JLabel lblCpf = new JLabel("CPF");
		txCpf = new JTextField();
		
		JLabel lblDriverLicense = new JLabel("Carteira de Habilitação");
		txDriverLicense = new JTextField();
		
		JLabel lblDriverLicenseCategory = new JLabel("Categoria");		
		cbDriverLicenseCategory = new JComboBox<String>();
		cbDriverLicenseCategory.setModel(new DefaultComboBoxModel<String>(new String[] {"Categoria A", "Categoria B", "Categoria C", "Categoria D", "Categoria E"}));
		
		JLabel lblCtps = new JLabel("CTPS");
		txCtps = new JTextField();
		
		JLabel lblCptsCategory = new JLabel("Categoria");
		txCptsCategory = new JTextField();
		
		JLabel lblGender = new JLabel("Sexo");
		cbGender = new JComboBox<String>();
		cbGender.setModel(new DefaultComboBoxModel<String>(new String[] {"Masculino", "Feminino"}));
		
		JLabel lblReservistCategory = new JLabel("Categoria");
		cbReservistCategory = new JComboBox<String>();
		cbReservistCategory.setModel(new DefaultComboBoxModel<String>(new String[] {"1ª Categoria", "2ª Categoria"}));
		
		JLabel lblSchooling = new JLabel("Escolaridade");
		cbSchooling = new JComboBox<String>();
		cbSchooling.setModel(new DefaultComboBoxModel<String>(new String[] {"Fundamental Incompleto", "Fundamental Completo", "Médio Incompleto", "Médio Completo", "Técnico Incompleto", "Técnico Completo", "Superior Incompleto", "Superior Completo", "Pós-Graduado"}));
		
		JLabel lblPicture = new JLabel("New label");
		
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
									.addComponent(txName, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
									.addGap(21)
									.addComponent(lblCode)
									.addGap(18)
									.addComponent(txCode, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
									.addComponent(lblBirth)
									.addGap(18)
									.addComponent(txBirth, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblGender)
									.addGap(18)
									.addComponent(cbGender, 0, 161, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblMaritalStatus)
									.addGap(18)
									.addComponent(cbMaritalStatus, 0, 126, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
									.addComponent(lblNacionality)
									.addGap(18)
									.addComponent(txNacionality, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
									.addGap(19)
									.addComponent(lblBirthPlace)
									.addGap(18)
									.addComponent(txBirthPlace, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblPicture, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblRg)
							.addGap(18)
							.addComponent(txRg, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblCpf)
							.addGap(18)
							.addComponent(txCpf, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblCtps)
							.addGap(18)
							.addComponent(txCtps, 95, 95, 95)
							.addGap(18)
							.addComponent(lblCptsCategory)
							.addGap(18)
							.addComponent(txCptsCategory, 0, 74, Short.MAX_VALUE)
							.addGap(117))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblVoter)
							.addGap(18)
							.addComponent(txVoter, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblDriverLicense)
							.addGap(18)
							.addComponent(txDriverLicense, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblDriverLicenseCategory)
							.addGap(18)
							.addComponent(cbDriverLicenseCategory, 0, 92, Short.MAX_VALUE)
							.addGap(117))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblSchooling)
							.addGap(18)
							.addComponent(cbSchooling, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblReservist)
							.addGap(18)
							.addComponent(txReservist, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblReservistCategory)
							.addGap(18)
							.addComponent(cbReservistCategory, 0, 101, Short.MAX_VALUE)
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
								.addComponent(txName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCode))
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
								.addComponent(lblDriverLicense)
								.addComponent(txDriverLicense, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbDriverLicenseCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDriverLicenseCategory))
							.addGap(18)
							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblReservistCategory)
								.addComponent(cbReservistCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txReservist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSchooling)
								.addComponent(cbSchooling, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblReservist))))
					.addContainerGap(539, Short.MAX_VALUE))
		);
		personalDataPanel.setLayout(layout);

		
	}

	/**
	 * Inicializa os componentes gráficos para o registo das informações de contato
	 */
	private void initializeContactDataPanel() {
		
		contactDataPanel = new JPanel();
		
		
		JLabel lblAddress = new JLabel("Endereço");
		txAddress = new JTextField();
		
		JLabel lblCep = new JLabel("CEP");
		txCep = new JTextField();
		
		JLabel lblState = new JLabel("Estado");
		cbState = new JComboBox<State>();
		
		JLabel lblCity = new JLabel("Cidade");
		cbCity = new JComboBox<City>();
		cbCity.setEnabled(false);
		
		JLabel lblNeighborhood = new JLabel("Bairro");
		txNeighborhood = new JTextField();	
		
		JLabel lblPhone = new JLabel("Telefone");
		txPhone = new JTextField();
		
		JLabel lblCellphone = new JLabel("Celular");
		txCellphone = new JTextField();
		
		GroupLayout layout = new GroupLayout(contactDataPanel);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblCep)
							.addGap(18)
							.addComponent(txCep, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblState)
							.addGap(18)
							.addComponent(cbState, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCity)
							.addGap(18)
							.addComponent(cbCity, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblAddress)
							.addGap(18)
							.addComponent(txAddress, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNeighborhood)
							.addGap(18)
							.addComponent(txNeighborhood, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblPhone)
							.addGap(18)
							.addComponent(txPhone, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblCellphone)
							.addGap(18)
							.addComponent(txCellphone, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(35, Short.MAX_VALUE))
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
						.addComponent(lblState)
						.addComponent(lblCity)
						.addComponent(cbCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
			new String[][] {{"", "", ""},},
			new String[] {"Nome", "Relação", "Data de Nascimento/Casamento"		}
		));
		
		dependentScrollPane.setViewportView(dependentTable);	
		
	}
	
	/**
	 * Inicializa os componentes ggráficos dos botões inferiores
	 */
	private void initializaButtonsPanel() {
		
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


}
