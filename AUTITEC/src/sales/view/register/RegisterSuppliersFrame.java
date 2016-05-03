package sales.view.register;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import model.City;
import model.Material;
import model.State;
import model.Supplier;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.MaterialUpdateController;
import sales.controller.SalesController;
import userInterface.components.UpperTextField;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class RegisterSuppliersFrame extends JFrame {

	private static final long serialVersionUID = -3611459763736774158L;

	private JFrame frame = this;

	private JPanel panel;
	private JPanel panelRodape;
	private JPanel panelCe;

	private JTabbedPane tabbedPane;

	private JScrollPane scrollPane;
	private JScrollPane scrollPaneProductTable;

	private JLabel lblFiscalClassification;
	private JLabel lblCertify;
	private JLabel lblExpirationDate;
	private JLabel lblCertificadoDoMaterial;
	private JLabel lblJustificado;
	private JLabel lblStreet;

	private UpperTextField textField_8;
	private UpperTextField txtCompanyName;
	private UpperTextField txtNeighborhood;
	private UpperTextField txtEmail;
	private UpperTextField txtStreet;

	private JTextField txtCNPJ;
	private JTextField txtStateRegister;
	private JTextField txtPhone;
	private JTextField txtCEP;

	private JButton btnCancelar;
	private JButton btnRegister;
	private JButton btnAnexarCertificado;
	private JButton btnAdd;

	private DateField txtExpirationDate;

	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private SalesController controller;

	private JComboBox<City> cboCity;
	private JComboBox<State> cboState;
	private JComboBox<String> cboCertification;
	private JComboBox<Material> cboMaterial;
	private JComboBox<String> cboFiscalCertification;

	private JTextArea txtJustifacao;

	private Date data;

	private JTable table;

	private MaterialUpdateController controllerProduct;

	private JLabel lblProduct;
	private JLabel lblState;
	private JLabel lblEmail;
	private JLabel lblNeighborhood;
	private JLabel lblCity;
	private JLabel lblCep;
	private JLabel lblCelphone;
	private JLabel lblStateInscrition;
	private JLabel lblCnpj;
	private JLabel lblCompanyName;

	public RegisterSuppliersFrame() {
		controller = new SalesController();
		controllerProduct = new MaterialUpdateController();
		initialize();
		setListeners();
	}

	private void initialize() {
		setBounds(100, 100, 510, 538);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(510, 538));
		setPreferredSize(new Dimension(510,538));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setTitle("Registro de Fornecedores");
		Icon.setIcon(this);
		getContentPane().setLayout(new BorderLayout(0, 0));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		initializeSub();
		initializePanel();
		initializeContact();
		tabbedPane.addTab("Contato", panel);
		tabbedPane.addTab("Certificações", panelCe);
		controller.fillStateAndCity(cboState, cboCity);
		controllerProduct.fillMaterials(cboMaterial);
	}

	private void initializeSub() {
		panelRodape = new JPanel();
		getContentPane().add(panelRodape, BorderLayout.SOUTH);
		panelRodape.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(RegisterSuppliersFrame.class.getResource("/resources/cancel.png")));
		panelRodape.add(btnCancelar);

	}

	private void initializePanel() {

		lblFiscalClassification = new JLabel("Classificação Fiscal");

		lblCertify = new JLabel("Certificado");

		btnRegister = new JButton("Registrar");
		btnRegister.setIcon(new ImageIcon(RegisterSuppliersFrame.class.getResource("/resources/ok.png")));
		panelRodape.add(btnRegister);
		panelCe = new JPanel();

		cboFiscalCertification = new JComboBox<String>();
		cboFiscalCertification.setModel(new DefaultComboBoxModel<String>(new String[] { "Lucro Presumido",
		        "Lucro Real", "Simples Social" }));
		cboFiscalCertification.setSelectedIndex(-1);
		
		cboCertification = new JComboBox<String>();
		cboCertification.setModel(new DefaultComboBoxModel<String>(new String[] { "ISO 9001:2008", "Não possui certificado" }));
		cboCertification.setSelectedIndex(-1);
		
		lblExpirationDate = new JLabel("Data de expiração");

		txtExpirationDate = CalendarFactory.createDateField();
		txtExpirationDate.setValue(null);

		lblCertificadoDoMaterial = new JLabel("Certificado do material");

		rdbtnYes = new JRadioButton("Sim");

		rdbtnNo = new JRadioButton("Não");

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnNo);
		bg.add(rdbtnYes);

		btnAnexarCertificado = new JButton("Anexar Certificado");

		lblJustificado = new JLabel("Justificação");

		scrollPane = new JScrollPane();
		GroupLayout gl_panelCe = new GroupLayout(panelCe);
		gl_panelCe
		        .setHorizontalGroup(gl_panelCe
		                .createParallelGroup(Alignment.LEADING)
		                .addGroup(
		                        gl_panelCe
		                                .createSequentialGroup()
		                                .addContainerGap()
		                                .addGroup(
		                                        gl_panelCe
		                                                .createParallelGroup(Alignment.LEADING)
		                                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 457,
		                                                        Short.MAX_VALUE)
		                                                .addGroup(
		                                                        gl_panelCe
		                                                                .createSequentialGroup()
		                                                                .addGroup(
		                                                                        gl_panelCe
		                                                                                .createParallelGroup(
		                                                                                        Alignment.LEADING,
		                                                                                        false)
		                                                                                .addGroup(
		                                                                                        gl_panelCe
		                                                                                                .createSequentialGroup()
		                                                                                                .addComponent(
		                                                                                                        lblFiscalClassification)
		                                                                                                .addPreferredGap(
		                                                                                                        ComponentPlacement.RELATED)
		                                                                                                .addComponent(
		                                                                                                        cboFiscalCertification,
		                                                                                                        GroupLayout.PREFERRED_SIZE,
		                                                                                                        170,
		                                                                                                        GroupLayout.PREFERRED_SIZE))
		                                                                                .addGroup(
		                                                                                        gl_panelCe
		                                                                                                .createSequentialGroup()
		                                                                                                .addComponent(
		                                                                                                        lblCertify)
		                                                                                                .addPreferredGap(
		                                                                                                        ComponentPlacement.RELATED)
		                                                                                                .addComponent(
		                                                                                                        cboCertification,
		                                                                                                        0,
		                                                                                                        GroupLayout.DEFAULT_SIZE,
		                                                                                                        Short.MAX_VALUE)))
		                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                                .addComponent(lblExpirationDate)
		                                                                .addPreferredGap(ComponentPlacement.RELATED)
		                                                                .addComponent(txtExpirationDate,
		                                                                        GroupLayout.DEFAULT_SIZE, 91,
		                                                                        Short.MAX_VALUE))
		                                                .addGroup(
		                                                        gl_panelCe.createSequentialGroup()
		                                                                .addComponent(lblCertificadoDoMaterial)
		                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                                .addComponent(rdbtnYes)
		                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                                .addComponent(rdbtnNo)
		                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                                .addComponent(btnAnexarCertificado))
		                                                .addComponent(lblJustificado)).addContainerGap()));
		gl_panelCe.setVerticalGroup(gl_panelCe.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_panelCe
		                .createSequentialGroup()
		                .addContainerGap()
		                .addGroup(
		                        gl_panelCe
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblFiscalClassification)
		                                .addComponent(cboFiscalCertification, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(ComponentPlacement.UNRELATED)
		                .addGroup(
		                        gl_panelCe
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblCertify)
		                                .addComponent(cboCertification, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(lblExpirationDate)
		                                .addComponent(txtExpirationDate, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addGap(18)
		                .addGroup(
		                        gl_panelCe.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblCertificadoDoMaterial).addComponent(rdbtnYes)
		                                .addComponent(rdbtnNo).addComponent(btnAnexarCertificado))
		                .addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblJustificado)
		                .addPreferredGap(ComponentPlacement.RELATED)
		                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE).addContainerGap()));

		txtJustifacao = new JTextArea();
		scrollPane.setViewportView(txtJustifacao);
		panelCe.setLayout(gl_panelCe);
	}

	/**
	 * 
	 */

	private void initializeContact() {
		panel = new JPanel();

		lblCompanyName = new JLabel("Razão Social");

		setTxtCompanyName(new UpperTextField());
		getTxtCompanyName().setColumns(10);

		lblCnpj = new JLabel("CNPJ");
		try {
			txtCNPJ = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtCNPJ.setColumns(10);

		lblStateInscrition = new JLabel("Insc.Est");
		
		txtStateRegister = new JTextField();
		txtStateRegister.setColumns(10);

		lblCelphone = new JLabel("Telefone");

		try {
			txtPhone = new JFormattedTextField(new MaskFormatter("(##)####-####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtPhone.setColumns(10);

		lblCep = new JLabel("CEP");
		try {
			txtCEP = new JFormattedTextField(new MaskFormatter("##.###-###"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtCEP.setColumns(10);

		lblCity = new JLabel("Cidade");

		lblNeighborhood = new JLabel("Bairro");

		txtNeighborhood = new UpperTextField();
		txtNeighborhood.setColumns(10);


		lblEmail = new JLabel("E-mail");
		txtEmail = new UpperTextField();
		txtEmail.setColumns(10);

		textField_8 = new UpperTextField();
		textField_8.setColumns(10);

		lblState = new JLabel("Estado");

		cboState = new JComboBox<State>();

		cboCity = new JComboBox<City>();

		lblStreet = new JLabel("Rua");

		txtStreet = new UpperTextField();
		txtStreet.setColumns(10);

		lblProduct = new JLabel("Produtos");

		cboMaterial = new JComboBox<Material>();

		btnAdd = new JButton("Adicionar");

		scrollPaneProductTable = new JScrollPane();

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblProduct)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAdd))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCompanyName)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCompanyName, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCnpj)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCNPJ, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCep)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblStateInscrition)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtStateRegister, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)))))
							.addGap(103))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPaneProductTable, GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblStreet)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtStreet, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblNeighborhood)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtNeighborhood, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(lblEmail)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)))
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblCelphone)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtPhone, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
											.addGap(47))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblState)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cboState, 0, 202, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblCity)
											.addGap(4)))
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(cboCity, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(txtCEP, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))))
							.addGap(103))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCompanyName)
						.addComponent(txtCompanyName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCnpj)
						.addComponent(txtCNPJ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStateInscrition)
						.addComponent(txtStateRegister, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCelphone)
						.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCEP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCep))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblState)
						.addComponent(cboState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cboCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCity))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNeighborhood)
						.addComponent(txtNeighborhood, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStreet)
						.addComponent(txtStreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduct)
						.addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd))
					.addGap(18)
					.addComponent(scrollPaneProductTable, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
		);

		table = new JTable();
		String[] header = new String[] {"Nome", "Descrição"};
		table.setModel(new DefaultTableModel(null, header) {

			/**
			 * 
			 */
            private static final long serialVersionUID = -4143239579340526383L;
			
            boolean[] columnEditables = new boolean[] {
					false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
				
		scrollPaneProductTable.setViewportView(table);
		panel.setLayout(gl_panel);
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
				if (e.getSource().equals(btnCancelar))
					controller.closeFrame(frame);
				else if (e.getSource().equals(btnRegister)) {
					int i = ShowMessage.questionMessage(frame, "CADASTRO",
					        "Deseja realmente cadastar esse fornecedor ?");
					if (i == JOptionPane.YES_OPTION) {
						try {
							controller.doSupplierRegister(makeSupplier());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else {
						txtCompanyName.requestFocus();
					}
				} else if (e.getSource().equals(btnAdd)) {
					DefaultTableModel tbl = (DefaultTableModel) table.getModel();
					Material produto = (Material) cboMaterial.getSelectedItem();
					tbl.addRow(new Object[] { produto, produto.getDescrition() });
				}
			}
		};
		btnAdd.addActionListener(buttonListener);
		btnCancelar.addActionListener(buttonListener);
		btnRegister.addActionListener(buttonListener);
	}

	private Supplier makeSupplier() {
		data = new Date();
		data = (Date) txtExpirationDate.getValue();
		String cnpj = txtCNPJ.getText().replaceAll("\\.|-|/", "").replaceAll(" ", "");
		String stateRegister = txtStateRegister.getText().replaceAll("\\.", "").replaceAll(" ", "");
		String CEP = txtCEP.getText().replaceAll("\\.", "").replaceAll("-", "").replaceAll(" ", "");
		String justicacao = txtJustifacao.getText();
		if (cnpj.isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o CNPJ");
			return null;
		} else if (getTxtCompanyName().getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o a Razão Social");
			return null;
		} else if (stateRegister.isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira a inscrição estadual");
			return null;
		} else if (cboCity.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Erro", "Selecione uma cidade");
			return null;
		} else if (cboState.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Erro", "Selecione um estado");
			return null;
		} else if (txtStreet.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira a rua da empresa");
			return null;
		} else if (CEP.isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira os CEP");
			return null;
		} else if (rdbtnNo.isSelected()) {
			if (justicacao.isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Insira a justificativa");
				return null;
			}
		} else if (txtExpirationDate.getValue().equals(null)) {
			ShowMessage.errorMessage(this, "Erro", "Insira a data de expiração do Certificado");
			return null;
		}
		String companyName = getTxtCompanyName().getText();
		Supplier supplier = new Supplier(companyName, cnpj);
		supplier.setStateRegistration(stateRegister);
		supplier.setCityState((City) cboCity.getSelectedItem(), (State) cboState.getSelectedItem());
		supplier.setStreet(txtStreet.getText());
		supplier.setEmail(txtEmail.getText());
		if (cboCertification.getSelectedIndex() != -1) {
			supplier.setCertificated(true);
		} else {
			supplier.setCertificated(false);
		}
		supplier.setNeighborhood(txtNeighborhood.getText());
		supplier.setFiscalClassification(cboFiscalCertification.getSelectedItem().toString());
		if (rdbtnYes.isSelected()) {
			supplier.setMaterialCertication(true);
		} else {
			supplier.setMaterialCertication(false);
		}
		supplier.setJustificative(txtJustifacao.getText());
		supplier.setPhone(txtPhone.getText().replaceAll("\\(|\\)|-", ""));
		supplier.setCep(CEP);
		java.sql.Date sqlDate = new java.sql.Date(data.getTime());
		supplier.setExpireCertificateDate(sqlDate);
		
		List<Material> material = new ArrayList<Material>();
			for(int i = 0; i < table.getRowCount(); i++) {
				Material product = (Material) table.getValueAt(i, 0);
				material.add(product);
			}
		supplier.setMaterial(material);
		ClearFrame.clear(frame);
		return supplier;
		
	}

	public JTextField getTxtCompanyName() {
		return txtCompanyName;
	}

	public void setTxtCompanyName(UpperTextField txtCompanyName) {
		this.txtCompanyName = txtCompanyName;
	}
}
