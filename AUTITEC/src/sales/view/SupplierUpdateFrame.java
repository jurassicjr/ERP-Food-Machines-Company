package sales.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.MaskFormatter;

import model.City;
import model.State;
import model.Supplier;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.SalesController;
import userInterface.components.ComboBoxAutoCompletion;
import userInterface.components.UpperTextField;
import util.Icon;

public class SupplierUpdateFrame extends JFrame {
	
	/**
	 * 
	 */
    private static final long serialVersionUID = -7777642677659733607L;

	private JFrame frame;
	
	private Icon icon;
	
	SalesController controller;
	
	private JPanel principalPanel;
	private JPanel secundaryPane;
	private JTabbedPane tabbedPane;
	private JScrollPane scrollPane;
	private JPanel subPanel;

	private JComboBox<Supplier> cboFornecedor;
	private JComboBox<State> cboState;
	private JComboBox<City> cboCity;
	private JComboBox<String> cboFiscalClassification;
	private JComboBox<String> cboCertificate;

	private ComboBoxAutoCompletion cboAuto;

	private JLabel lblCompanyName;
	private JLabel lblCnpj;
	private JLabel lblInscEst;
	private JLabel lblEmail;
	private JLabel lblState;
	private JLabel lblCity;
	
	private DateField txtExpirationDate;

	private JTextField txtCompanyName;
	private JTextField txtCNPJ;
	private JTextField txtStateInscrition;
	private JTextField txtEmail;
	private JTextField txtCEP;
	private JTextField txtStreet;
	private JTextField txtNeighborhood;
	private JTextField txtCelPhone;
	private JTextField txtPhone;

	private JLabel lblCep;
	private JLabel lblStreet;
	private JLabel lblNeighborhood;
	private JLabel lblPhone;
	private JLabel lblCelPhone;
	private JLabel lblFiscalClassification;
	private JLabel lblCertificate;
	private JLabel lblExpirationDate;
	private JLabel lblCertificadoDoMaterial;
	private JLabel lblJustificao;

	private JRadioButton rdbtnSim;
	private JRadioButton rdbtnNo;

	private JButton btnAnexarCertificado;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JButton btnApagar;


	public SupplierUpdateFrame() {
		controller = new SalesController();
		this.frame = this;
		initialize();
		setListeners();
	}

	private void initialize() {
		setTitle("Atualização/Remoção de fornecedores");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0,0));
		icon = new Icon();
		icon.setIcon(frame);
		initializePrincipal();
	}

	private void initializePrincipal() {
		principalPanel = new JPanel();
		tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Dados Cadastros", principalPanel);
		JLabel lblSelectSupplier = new JLabel("Selecione um fornecedor");
		
		cboFornecedor = new JComboBox<Supplier>();
		
		cboAuto = new ComboBoxAutoCompletion(cboFornecedor);
		
		lblCompanyName = new JLabel("Razão Social");
		
		txtCompanyName = new UpperTextField();
		txtCompanyName.setColumns(10);
		
		lblCnpj = new JLabel("CNPJ");
		
		try {
	        txtCNPJ = new JFormattedTextField(new MaskFormatter("##.##.###/####-##"));
        } catch (ParseException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		
		lblInscEst = new JLabel("Insc.Est");
		
		try {
	        txtStateInscrition = new JFormattedTextField(new MaskFormatter("###.###.###"));
        } catch (ParseException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		txtStateInscrition.setColumns(10);
		
		lblEmail = new JLabel("E-mail");
		
		txtEmail = new UpperTextField();
		txtEmail.setColumns(10);
		
		lblState = new JLabel("Estado");
		
		cboState = new JComboBox<State>();
		
		lblCity = new JLabel("Cidade");
		
		cboCity = new JComboBox<City>();
		
		controller.fillStateAndCity(cboState, cboCity);
		
		lblCep = new JLabel("CEP");
		
		try {
	        txtCEP = new JFormattedTextField(new MaskFormatter("##.###-###"));
        } catch (ParseException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		txtCEP.setColumns(10);
		
		lblStreet = new JLabel("Rua");
		
		txtStreet = new UpperTextField();
		txtStreet.setColumns(10);
		
		lblNeighborhood = new JLabel("Bairro");
		
		txtNeighborhood = new UpperTextField();
		txtNeighborhood.setColumns(10);
		
		lblPhone = new JLabel("Telefone");
		
		try {
	        txtPhone = new JFormattedTextField(new MaskFormatter("(##)####-####"));
        } catch (ParseException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		txtPhone.setColumns(10);
		
		lblCelPhone = new JLabel("Celular");
		
		try {
	        txtCelPhone = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
        } catch (ParseException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		
		txtCelPhone.setColumns(10);
		
		
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblSelectSupplier)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboFornecedor, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblCompanyName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCompanyName, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblCnpj)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCNPJ, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblNeighborhood)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtNeighborhood))
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblCep)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCEP))
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblState)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboState, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblInscEst)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtStateInscrition, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblPhone)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblCelPhone)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCelPhone, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblStreet)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtStreet, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblEmail)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblCity)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboCity, 0, 269, Short.MAX_VALUE)))))
					.addContainerGap())
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectSupplier)
						.addComponent(cboFornecedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCompanyName)
						.addComponent(txtCompanyName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCnpj)
						.addComponent(txtCNPJ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInscEst)
						.addComponent(txtStateInscrition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblState)
						.addComponent(cboState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCity)
						.addComponent(cboCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCep)
						.addComponent(txtCEP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStreet)
						.addComponent(txtStreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNeighborhood)
						.addComponent(txtNeighborhood, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPhone)
						.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCelPhone)
						.addComponent(txtCelPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(168, Short.MAX_VALUE))
		);
		principalPanel.setLayout(gl_principalPanel);
		initializeSecondPane();
	}
	private void initializeSecondPane() {
		secundaryPane = new JPanel();
		tabbedPane.add("Informações de Certificados", secundaryPane);
		
		lblFiscalClassification = new JLabel("Classificação Fiscal");
		
		cboFiscalClassification = new JComboBox<String>();
		cboFiscalClassification.addItem("Lucro presumido");
		cboFiscalClassification.addItem("Lucro Real");
		cboFiscalClassification.addItem("Simples Social");
		
		lblCertificate = new JLabel("Certificado");
		
		cboCertificate = new JComboBox<String>();
		cboCertificate.addItem("ISO 9001:2008");
		
		lblExpirationDate = new JLabel("Data de expiração");
		
		txtExpirationDate = CalendarFactory.createDateField();
		txtExpirationDate.setValue(null);
		
		lblCertificadoDoMaterial = new JLabel("Certificado do material");
		
		lblJustificao = new JLabel("Justificação");
		
		rdbtnSim = new JRadioButton("Sim");
		
		rdbtnNo = new JRadioButton("Não");
		
		btnAnexarCertificado = new JButton("Anexar Certificado");
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		GroupLayout gl_secundaryPane = new GroupLayout(secundaryPane);
		gl_secundaryPane.setHorizontalGroup(
			gl_secundaryPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_secundaryPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_secundaryPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
						.addGroup(gl_secundaryPane.createSequentialGroup()
							.addGroup(gl_secundaryPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_secundaryPane.createSequentialGroup()
									.addComponent(lblFiscalClassification)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboFiscalClassification, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_secundaryPane.createSequentialGroup()
									.addComponent(lblCertificate)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboCertificate, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblExpirationDate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtExpirationDate, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
						.addGroup(gl_secundaryPane.createSequentialGroup()
							.addComponent(lblCertificadoDoMaterial)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnSim)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnNo)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAnexarCertificado))
						.addComponent(lblJustificao))
					.addContainerGap())
		);
		gl_secundaryPane.setVerticalGroup(
			gl_secundaryPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_secundaryPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_secundaryPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFiscalClassification)
						.addComponent(cboFiscalClassification, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_secundaryPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCertificate)
						.addComponent(cboCertificate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblExpirationDate)
						.addComponent(txtExpirationDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_secundaryPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCertificadoDoMaterial)
						.addComponent(rdbtnSim)
						.addComponent(rdbtnNo)
						.addComponent(btnAnexarCertificado))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblJustificao)
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTextArea txtJustification = new JTextArea();
		scrollPane.setViewportView(txtJustification);
		secundaryPane.setLayout(gl_secundaryPane);
		initializeSub();
	}
	private void initializeSub() {
		subPanel = new JPanel();
		getContentPane().add(subPanel, BorderLayout.SOUTH);
		subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnApagar = new JButton("Apagar");
		btnApagar.setIcon(new ImageIcon(SupplierUpdateFrame.class.getResource("/resources/clear.png")));
		subPanel.add(btnApagar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(SupplierUpdateFrame.class.getResource("/resources/cancel.png")));
		subPanel.add(btnCancelar);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setIcon(new ImageIcon(SupplierUpdateFrame.class.getResource("/resources/ok.png")));
		subPanel.add(btnConfirmar);
		
	}
	private void setListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			    controller.closeFrame(frame);
			}
		});
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnCancelar))controller.closeFrame(frame);
				
			}
		};
		btnCancelar.addActionListener(buttonListener);
	}
}
