package sales.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

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
import model.Produto;
import model.State;
import model.Supplier;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.SalesController;
import userInterface.components.UpperTextField;
import util.ClearFrame;
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

	private JLabel lblClassificaoFiscal;
	private JLabel lblCertificado;
	private JLabel lblDataDeExpirao;
	private JLabel lblCertificadoDoMaterial;
	private JLabel lblJustificado;
	private JLabel lblRua;
	
	private UpperTextField textField_8;
	private UpperTextField txtCompanyName;
	private UpperTextField txtBairro;
	private UpperTextField txtEmail;
	private UpperTextField txtStreet;

	private JTextField txtCNPJ;
	private JTextField txtStateRegister;
	private JTextField txtPhone;
	private JTextField txtCEP;

	private JButton btnCancelar;
	private JButton btnRegistrar;
	private JButton btnAnexarCertificado;
	private JButton btnAdicionar;
	
	private DateField txtdataExpiracao;
	
	private JRadioButton rdbtnSim;
	private JRadioButton rdbtnNo;
	private SalesController controller;

	private JComboBox<City> cboCity;
	private JComboBox<State> cboState;
	private JComboBox<String> cboCertification;
	private JComboBox<Produto> cboProduto;
	private JComboBox<String> cboFiscalCertification;

	private JTextArea txtJustifacao;

	private ShowMessage message;
	private Date data;
	private ClearFrame faxineira;
	private JTable table;
	
	public RegisterSuppliersFrame() {
		controller = new SalesController();
		initialize();
		setListeners();
	}

	private void initialize() {
		//setBounds(100, 100, 496, 404);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(500, 404));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setTitle("Registro de Fornecedores");
		getContentPane().setLayout(new BorderLayout(0, 0));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		initializeSub();
		initializePanel();
		initializeContact();
		tabbedPane.addTab("Contato",panel);
		tabbedPane.addTab("Certificações", panelCe);
		controller.fillStateAndCity(cboState, cboCity);
		controller.fillProducts(cboProduto);
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

		lblClassificaoFiscal = new JLabel("Classificação Fiscal");

		lblCertificado = new JLabel("Certificado");

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setIcon(new ImageIcon(RegisterSuppliersFrame.class.getResource("/resources/ok.png")));
		panelRodape.add(btnRegistrar);
		panelCe = new JPanel();

		cboFiscalCertification = new JComboBox<String>();
		cboFiscalCertification.setModel(new DefaultComboBoxModel<String>(
		        new String[] { "Lucro Presumido", "Lucro Real", "Simples" }));
		cboFiscalCertification.setSelectedIndex(-1);
		cboCertification = new JComboBox<String>();
		cboCertification.setModel(new DefaultComboBoxModel<String>(new String[] { "ISO 9001:2008" }));
		cboCertification.setSelectedIndex(-1);
		lblDataDeExpirao = new JLabel("Data de expiração");

		txtdataExpiracao = CalendarFactory.createDateField();
		txtdataExpiracao.setValue(null);

		lblCertificadoDoMaterial = new JLabel("Certificado do material");

		rdbtnSim = new JRadioButton("Sim");

		rdbtnNo = new JRadioButton("Não");

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnNo);
		bg.add(rdbtnSim);
		
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
		                                                                                                        lblClassificaoFiscal)
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
		                                                                                                        lblCertificado)
		                                                                                                .addPreferredGap(
		                                                                                                        ComponentPlacement.RELATED)
		                                                                                                .addComponent(
		                                                                                                        cboCertification,
		                                                                                                        0,
		                                                                                                        GroupLayout.DEFAULT_SIZE,
		                                                                                                        Short.MAX_VALUE)))
		                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                                .addComponent(lblDataDeExpirao)
		                                                                .addPreferredGap(ComponentPlacement.RELATED)
		                                                                .addComponent(txtdataExpiracao,
		                                                                        GroupLayout.DEFAULT_SIZE, 91,
		                                                                        Short.MAX_VALUE))
		                                                .addGroup(
		                                                        gl_panelCe.createSequentialGroup()
		                                                                .addComponent(lblCertificadoDoMaterial)
		                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                                .addComponent(rdbtnSim)
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
		                                .addComponent(lblClassificaoFiscal)
		                                .addComponent(cboFiscalCertification, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(ComponentPlacement.UNRELATED)
		                .addGroup(
		                        gl_panelCe
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblCertificado)
		                                .addComponent(cboCertification, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(lblDataDeExpirao)
		                                .addComponent(txtdataExpiracao, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addGap(18)
		                .addGroup(
		                        gl_panelCe.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblCertificadoDoMaterial).addComponent(rdbtnSim)
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

		JLabel lblRazoSocial = new JLabel("Razão Social");

		setTxtCompanyName(new UpperTextField());
		getTxtCompanyName().setColumns(10);

		JLabel lblCnpj = new JLabel("CNPJ");
		try {
			txtCNPJ = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtCNPJ.setColumns(10);

		JLabel lblInscest = new JLabel("Insc.Est");
		try {
			txtStateRegister = new JFormattedTextField(new MaskFormatter("###.###.###"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtStateRegister.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");

		try {
	        txtPhone = new JFormattedTextField(new MaskFormatter("(##)####-####"));
        } catch (ParseException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
        }
		txtPhone.setColumns(10);

		JLabel lblCep = new JLabel("CEP");
		try {
			txtCEP = new JFormattedTextField(new MaskFormatter("##.###-###"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtCEP.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade");

		JLabel lblBairro = new JLabel("Bairro");

		txtBairro = new UpperTextField();
		txtBairro.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail");

		txtEmail = new UpperTextField();
		txtEmail.setColumns(10);

		textField_8 = new UpperTextField() ;
		textField_8.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		
		cboState = new JComboBox<State>();
		
		cboCity = new JComboBox<City>();
		
		lblRua = new JLabel("Rua");
		
		txtStreet = new UpperTextField();
		txtStreet.setColumns(10);
		
		JLabel lblProdutos = new JLabel("Produtos");
		
		cboProduto = new JComboBox<Produto>();
		
		btnAdicionar = new JButton("Adicionar");
		
		scrollPaneProductTable = new JScrollPane();

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(scrollPaneProductTable, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblRazoSocial)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCompanyName, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblTelefone)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtPhone))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblCnpj)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtCNPJ, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblInscest)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtStateRegister, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblCep)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtCEP, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblEstado)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboState, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(lblCidade)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboCity, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(5)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblRua)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtStreet, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblBairro)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtBairro, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblEmail)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)))))
							.addGap(10))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblProdutos)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboProduto, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAdicionar)
							.addGap(125))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRazoSocial)
						.addComponent(txtCompanyName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCnpj)
						.addComponent(txtCNPJ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInscest)
						.addComponent(txtStateRegister, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefone)
						.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCep)
						.addComponent(txtCEP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEstado)
						.addComponent(cboState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cboCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCidade))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBairro)
						.addComponent(txtBairro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRua)
						.addComponent(txtStreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProdutos)
						.addComponent(cboProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdicionar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneProductTable, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Material", "Descri\u00E7\u00E3o"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class
			};
			@Override
            public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
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
				else if(e.getSource().equals(btnRegistrar)) {
					try {
	                    controller.doSupplierRegister(makeSupplier());
                    } catch (SQLException e1) {
	                    e1.printStackTrace();
                    }
				}else if(e.getSource().equals(btnAdicionar)) {
					DefaultTableModel tbl = (DefaultTableModel) table.getModel();
					Produto produto = (Produto) cboProduto.getSelectedItem();
					tbl.addRow(new Object[] {produto.getName(), produto.getDescricao()});
				}
			}
		};
		btnAdicionar.addActionListener(buttonListener);
		btnCancelar.addActionListener(buttonListener);
		btnRegistrar.addActionListener(buttonListener);
	}
	private Supplier makeSupplier() {
		data = new Date();
		message = new ShowMessage();
		data = (Date) txtdataExpiracao.getValue();
		String cnpj =  txtCNPJ.getText().replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "").replaceAll(" ", "");
		String stateRegister = txtStateRegister.getText().replaceAll("\\.", "").replaceAll(" ", "");
		String CEP = txtCEP.getText().replaceAll("\\.", "").replaceAll("-", "").replaceAll(" ", "");
		String justicacao = txtJustifacao.getText();
		if(cnpj.isEmpty()) {
			message.errorMessage(this, "Erro", "Insira o CNPJ");
			return null;
		}else if(getTxtCompanyName().getText().isEmpty()) {
			message.errorMessage(this, "Erro", "Insira o a Razão Social");
			return null;
		}else if(stateRegister.isEmpty()){
			message.errorMessage(this, "Erro", "Insira a inscrição estadual");
			return null;
		}else if(cboCity.getSelectedIndex() == -1) {
			message.errorMessage(this, "Erro", "Selecione uma cidade");
			return null;
		}else if(cboState.getSelectedIndex()== -1) {
			message.errorMessage(this, "Erro", "Selecione um estado");
			return null;
		}else if(txtStreet.getText().isEmpty()) {
			message.errorMessage(this, "Erro", "Insira a rua da empresa");
			return null;
		}else if(CEP.isEmpty()) {
			message.errorMessage(this, "Erro", "Insira os CEP");
			return null;
		}else if(rdbtnNo.isSelected()) {
			if(justicacao.isEmpty()) {
				message.errorMessage(this, "Erro", "Insira a justificativa");
				return null;
			}
		}else if(txtdataExpiracao.getValue().equals(null)) {
			message.errorMessage(this, "Erro", "Insira a data de expiração do Certificado");
			return null;
		}
		String companyName = getTxtCompanyName().getText();
		Supplier supplier = new Supplier(companyName, cnpj);
		supplier.setInscEstadual(stateRegister);
		supplier.setCityState((City)cboCity.getSelectedItem(), (State)cboState.getSelectedItem());
		supplier.setRua(txtStreet.getText());
		supplier.setEmail(txtEmail.getText());
		if(cboCertification.getSelectedIndex() != -1) {
			supplier.setCertificado(false);			
		}else {
			supplier.setCertificado(true);
		}
		supplier.setBairro(txtBairro.getText());
		supplier.setFiscalClassification(cboFiscalCertification.getSelectedItem().toString());
		if(rdbtnSim.isSelected()) {
			supplier.setMaterialCertication(true);
		}else {
			supplier.setMaterialCertication(false);
		}
		supplier.setJustificative(txtJustifacao.getText());
		supplier.setPhone(txtPhone.getText().replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("-", ""));
		supplier.setCep(CEP);
		java.sql.Date sqlDate = new java.sql.Date(data.getTime());
		supplier.setExpireCertificateDate(sqlDate);
		faxineira = new ClearFrame(frame);
		faxineira.clear();
		return supplier;
	}

	private void clear() {
		txtCNPJ.setText(null);
		txtCompanyName.setText(null);
		txtStateRegister.setText(null);
		cboCity.setSelectedIndex(-1);
		cboState.setSelectedIndex(-1);
		txtStreet.setText(null);
		txtEmail.setText(null);
		txtPhone.setText(null);
		txtCEP.setText(null);
		txtBairro.setText(null);
		cboCertification.setSelectedIndex(-1);
		txtJustifacao.setText(null);
		txtdataExpiracao.setValue(null);
		cboFiscalCertification.setSelectedItem(-1);
	}
	public JTextField getTxtCompanyName() {
	    return txtCompanyName;
    }

	public void setTxtCompanyName(UpperTextField txtCompanyName) {
	    this.txtCompanyName =  txtCompanyName;
    }
}
