package sales.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import model.City;
import model.Material;
import model.State;
import model.Supplier;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.MaterialUpdateController;
import sales.controller.SupplierUpdateController;
import userInterface.components.ComboBoxAutoCompletion;
import userInterface.components.UpperTextField;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class SupplierUpdateFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7777642677659733607L;

	private JFrame frame;

	SupplierUpdateController controller;
	MaterialUpdateController productController;

	private JPanel principalPanel;
	private JPanel secundaryPane;
	private JTabbedPane tabbedPane;
	private JScrollPane scrollPane;
	private JPanel subPanel;

	private JComboBox<Material> cboProduto;
	private JComboBox<Supplier> cboSupplier;
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
	private JTextField txtPhone;

	private JLabel lblCep;
	private JLabel lblStreet;
	private JLabel lblNeighborhood;
	private JLabel lblPhone;
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
	private JButton btnAdicionar;

	private int cityId = -1;

	private JTextArea txtJustification;
	private JTable table;

	private List<Material> productList;

	/**
	 * Construtor inicialize a GUI o showMessage e o controller.
	 */

	public SupplierUpdateFrame() {
		controller = new SupplierUpdateController();
		productController = new MaterialUpdateController();
		this.frame = this;
		initialize();
		setListeners();
	}

	/**
	 * Função que define algumas propriedades do JFrame e controi a GUI.
	 */

	private void initialize() {
		setTitle("Atualização/Remoção de fornecedores");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 650, 510);
		setPreferredSize(new Dimension(650,510));
		Icon.setIcon(frame);
		initializePrincipal();
	}

	/**
	 * Cria o JPanel Principal.
	 */

	private void initializePrincipal() {
		principalPanel = new JPanel();
		tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Dados Cadastros", principalPanel);
		JLabel lblSelectSupplier = new JLabel("Selecione um fornecedor");

		cboSupplier = new JComboBox<Supplier>();
		controller.fillSuppliers(cboSupplier);
		cboAuto = new ComboBoxAutoCompletion(cboSupplier);

		lblCompanyName = new JLabel("Razão Social");

		txtCompanyName = new UpperTextField();
		txtCompanyName.setColumns(10);

		lblCnpj = new JLabel("CNPJ");

		try {
			txtCNPJ = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
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
		
		JLabel lblProdutos = new JLabel("Produtos");

		cboProduto = new JComboBox<Material>();
		productController.fillMaterials(cboProduto);
		cboProduto.setSelectedIndex(-1);
		JScrollPane scrollPane_1 = new JScrollPane();

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setEnabled(false);

		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblSelectSupplier)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboSupplier, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblProdutos)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboProduto, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAdicionar))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblCompanyName)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCompanyName, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblNeighborhood)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtNeighborhood, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblPhone)
									.addGap(6)
									.addComponent(txtPhone))
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblCnpj)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtCNPJ, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblCep)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCEP, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblStreet)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtStreet, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblState)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cboState, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblCity)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboCity, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblInscEst)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtStateInscrition, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblEmail)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1))
					.addGap(146))
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectSupplier)
						.addComponent(cboSupplier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCompanyName)
						.addComponent(txtCompanyName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCnpj)
						.addComponent(txtCNPJ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInscEst)
						.addComponent(txtStateInscrition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblState)
						.addComponent(cboState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCity)
						.addComponent(cboCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCep)
						.addComponent(txtCEP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStreet)
						.addComponent(txtStreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNeighborhood)
						.addComponent(txtNeighborhood, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPhone)
						.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProdutos)
						.addComponent(cboProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdicionar))
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(43))
		);

		table = new JTable();
		String[] header = new String[] { "Produto", "Descrição" };
		table.setModel(new DefaultTableModel(null, header) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6076310227138893239L;

			boolean[] columnEditables = new boolean[] { false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		});

		scrollPane_1.setViewportView(table);
		principalPanel.setLayout(gl_principalPanel);
		initializeSecondPane();
	}

	/**
	 * Inicializa o JPanel secundário.
	 */

	private void initializeSecondPane() {
		secundaryPane = new JPanel();
		tabbedPane.add("Informações de Certificados", secundaryPane);

		lblFiscalClassification = new JLabel("Classificação Fiscal");

		cboFiscalClassification = new JComboBox<String>();
		cboFiscalClassification.addItem("Lucro Presumido");
		cboFiscalClassification.addItem("Lucro Real");
		cboFiscalClassification.addItem("Simples Social");
		cboFiscalClassification.setSelectedIndex(-1);
		lblCertificate = new JLabel("Certificado");

		cboCertificate = new JComboBox<String>();
		cboCertificate.addItem("ISO 9001:2008");

		lblExpirationDate = new JLabel("Data de expiração");

		txtExpirationDate = CalendarFactory.createDateField();
		txtExpirationDate.setValue(null);

		lblCertificadoDoMaterial = new JLabel("Certificado do material");

		lblJustificao = new JLabel("Justificação");
		ButtonGroup bg = new ButtonGroup();
		rdbtnSim = new JRadioButton("Sim");
		rdbtnNo = new JRadioButton("Não");
		bg.add(rdbtnNo);
		bg.add(rdbtnSim);
		btnAnexarCertificado = new JButton("Anexar Certificado");

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		GroupLayout gl_secundaryPane = new GroupLayout(secundaryPane);
		gl_secundaryPane
		        .setHorizontalGroup(gl_secundaryPane
		                .createParallelGroup(Alignment.LEADING)
		                .addGroup(
		                        gl_secundaryPane
		                                .createSequentialGroup()
		                                .addContainerGap()
		                                .addGroup(
		                                        gl_secundaryPane
		                                                .createParallelGroup(Alignment.LEADING)
		                                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 530,
		                                                        Short.MAX_VALUE)
		                                                .addGroup(
		                                                        gl_secundaryPane
		                                                                .createSequentialGroup()
		                                                                .addGroup(
		                                                                        gl_secundaryPane
		                                                                                .createParallelGroup(
		                                                                                        Alignment.LEADING,
		                                                                                        false)
		                                                                                .addGroup(
		                                                                                        gl_secundaryPane
		                                                                                                .createSequentialGroup()
		                                                                                                .addComponent(
		                                                                                                        lblFiscalClassification)
		                                                                                                .addPreferredGap(
		                                                                                                        ComponentPlacement.RELATED)
		                                                                                                .addComponent(
		                                                                                                        cboFiscalClassification,
		                                                                                                        GroupLayout.PREFERRED_SIZE,
		                                                                                                        170,
		                                                                                                        GroupLayout.PREFERRED_SIZE))
		                                                                                .addGroup(
		                                                                                        gl_secundaryPane
		                                                                                                .createSequentialGroup()
		                                                                                                .addComponent(
		                                                                                                        lblCertificate)
		                                                                                                .addPreferredGap(
		                                                                                                        ComponentPlacement.RELATED)
		                                                                                                .addComponent(
		                                                                                                        cboCertificate,
		                                                                                                        0,
		                                                                                                        GroupLayout.DEFAULT_SIZE,
		                                                                                                        Short.MAX_VALUE)))
		                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                                .addComponent(lblExpirationDate)
		                                                                .addPreferredGap(ComponentPlacement.RELATED)
		                                                                .addComponent(txtExpirationDate,
		                                                                        GroupLayout.DEFAULT_SIZE, 164,
		                                                                        Short.MAX_VALUE))
		                                                .addGroup(
		                                                        gl_secundaryPane.createSequentialGroup()
		                                                                .addComponent(lblCertificadoDoMaterial)
		                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                                .addComponent(rdbtnSim)
		                                                                .addPreferredGap(ComponentPlacement.RELATED)
		                                                                .addComponent(rdbtnNo)
		                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                                .addComponent(btnAnexarCertificado))
		                                                .addComponent(lblJustificao)).addContainerGap()));
		gl_secundaryPane.setVerticalGroup(gl_secundaryPane.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_secundaryPane
		                .createSequentialGroup()
		                .addContainerGap()
		                .addGroup(
		                        gl_secundaryPane
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblFiscalClassification)
		                                .addComponent(cboFiscalClassification, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(ComponentPlacement.UNRELATED)
		                .addGroup(
		                        gl_secundaryPane
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblCertificate)
		                                .addComponent(cboCertificate, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(lblExpirationDate)
		                                .addComponent(txtExpirationDate, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(ComponentPlacement.UNRELATED)
		                .addGroup(
		                        gl_secundaryPane.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblCertificadoDoMaterial).addComponent(rdbtnSim)
		                                .addComponent(rdbtnNo).addComponent(btnAnexarCertificado))
		                .addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblJustificao).addGap(8)
		                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE).addContainerGap()));

		txtJustification = new JTextArea();
		scrollPane.setViewportView(txtJustification);
		secundaryPane.setLayout(gl_secundaryPane);
		initializeSub();
	}

	/**
	 * Inicialize o JPanel inferior com os botões de "ação".
	 */

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

	/**
	 * Configura os Listeners da "janela", botões e combobox.
	 */

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
				else if (e.getSource().equals(btnConfirmar)) {

					int i = ShowMessage.questionMessage(frame, "Atualização",
					        "Deseja realmente atualizar as informações desse fornecedor ?");

					if (i == JOptionPane.YES_OPTION) {
						controller.updateSupplier(newSupplier(), productList);
						controller.fillSuppliers(cboSupplier);
						btnAdicionar.setEnabled(false);
						ClearFrame.clear(frame);
					} else {

					}
				} else if (e.getSource().equals(btnApagar)) {
					String title = "Exclusão";
					String message = "Deseja realmente excluir o fornecedor \"" + txtCompanyName.getText() + "\"";
					int i = ShowMessage.questionMessage(frame, title, message);
					if (i == JOptionPane.YES_OPTION) {
						try {
							controller.deleteSupplier((Supplier) cboSupplier.getSelectedItem());
							controller.fillSuppliers(cboSupplier);
							ShowMessage.successMessage(frame, "Remoção", "O fornecedor foi deletado com sucesso!");
							ClearFrame.clear(frame);
							btnAdicionar.setEnabled(false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						txtCompanyName.requestFocus();
					}
				} else if (e.getSource().equals(btnAdicionar)) {
					if (cboProduto.getSelectedIndex() != -1) {
						Material product = (Material) cboProduto.getSelectedItem();
						DefaultTableModel tbl = (DefaultTableModel) table.getModel();
						if(!hasItem(tbl, product)) {
						productList.add(product);
						tbl.addRow(new Object[] { product, product.getDescrition() });
						}else {
							ShowMessage.errorMessage(frame, "Inserção de material", "Esse material já está na lista");
						}
					}
				}

			}
		};
		ActionListener cboListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(cboSupplier)) {
					fillField();
					btnAdicionar.setEnabled(true);
				}
			}
		};

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					if (table.getRowCount() != 0) {
						int i = table.getSelectedRow();
						DefaultTableModel tbl = (DefaultTableModel) table.getModel();
						Material product = (Material) tbl.getValueAt(i, 0);
						Supplier supplier = (Supplier) cboSupplier.getSelectedItem();
						if (!productList.contains(product) && !supplier.equals(null)) {
							controller.deleteSupplierProductAssociation(supplier.getId(), product.getId());
						}else {
							productList.remove(product);
						}
						tbl.removeRow(i);
					}
				}
			}
		});
		btnAdicionar.addActionListener(buttonListener);
		btnApagar.addActionListener(buttonListener);
		btnConfirmar.addActionListener(buttonListener);
		btnCancelar.addActionListener(buttonListener);
		cboSupplier.addActionListener(cboListener);
	}

	/**
	 * Preenche os campos da aplicação, após selecionado os fornecedor a partir
	 * do JComboBox<Supplier>.
	 */

	private void fillField() {
		if (cboSupplier.getSelectedIndex() != -1) {
			productList = new ArrayList<Material>();
			productList.clear();
			Supplier supplier = (Supplier) cboSupplier.getSelectedItem();
			txtCompanyName.setText(supplier.getCompanyName());
			txtCEP.setText(supplier.getCep());
			txtCNPJ.setText(supplier.getCNPJ());
			txtEmail.setText(supplier.getEmail());
			txtExpirationDate.setValue(supplier.getExpireCertificateDate());
			txtNeighborhood.setText(supplier.getNeighborhood());
			txtStateInscrition.setText(supplier.getStateRegistration());
			txtStreet.setText(supplier.getStreet());
			cboState.setSelectedIndex(supplier.getState().getId() - 1);
			int size = cboCity.getItemCount();
			for (int i = 0; i < size; i++) {
				cboCity.setSelectedIndex(i);
				City city = (City) cboCity.getSelectedItem();
				if (city.getId() == supplier.getCity().getId()) {
					cityId = i;
				}
			}
			cboCity.setSelectedIndex(cityId);
			txtPhone.setText(supplier.getPhone());
			txtJustification.setText(supplier.getJustificative());
			if (supplier.isMaterialCertication()) {
				rdbtnSim.setSelected(true);
			} else {
				rdbtnNo.setSelected(true);
			}
			if (supplier.isCertificated()) {
				cboCertificate.setSelectedIndex(0);
			}
			String fiscalClassification = supplier.getFiscalClassification();
			if (fiscalClassification == "Lucro Presumido") {
				cboFiscalClassification.setSelectedIndex(0);
			} else if (fiscalClassification == "Lucro Real") {
				cboFiscalClassification.setSelectedItem(1);
			} else if (fiscalClassification == "Simples Social") {
				cboFiscalClassification.setSelectedItem(2);
			} else {
				cboFiscalClassification.setSelectedItem(-1);
			}
			controller.fillProductTable(table, supplier);
		}
	}

	/**
	 * Cria um novo fornecedor com as informações contidas nos campos da
	 * aplicação.
	 */

	private Supplier newSupplier() {
		String razaoSocial = txtCompanyName.getText();
		String CNPJ = txtCNPJ.getText().replaceAll("\\.|-|/", "").replaceAll(" ", "");
		String cep = txtCEP.getText().replaceAll("\\.|-", "").replaceAll(" ", "");
		String email = txtEmail.getText();
		String neighborhood = txtNeighborhood.getText();
		State state = (State) cboState.getSelectedItem();
		City city = (City) cboCity.getSelectedItem();
		String stateInscrition = txtStateInscrition.getText().replaceAll("\\.", "").replaceAll(" ", "");
		String street = txtStreet.getText();
		String phone = txtPhone.getText().replaceAll("\\(", "").replaceAll(" ", "").replaceAll("\\)", "")
		        .replaceAll("-", "");
		String justificative = txtJustification.getText();
		String fiscalClassification = (String) cboFiscalClassification.getSelectedItem();
		Boolean materialCertication;
		if (rdbtnSim.isSelected()) {
			materialCertication = true;
		} else {
			materialCertication = false;
		}
		Date date = (Date) txtExpirationDate.getValue();
		java.sql.Date expireCertificateDate = new java.sql.Date(date.getTime());
		boolean certificado = false;
		if (cboCertificate.getSelectedIndex() != -1) {
			certificado = true;
		}
		Supplier supplier = new Supplier(razaoSocial, CNPJ);
		supplier.setCep(cep);
		supplier.setCertificated(certificado);
		supplier.setCityState(city, state);
		supplier.setEmail(email);
		supplier.setExpireCertificateDate(expireCertificateDate);
		supplier.setJustificative(justificative);
		supplier.setMaterialCertication(materialCertication);
		supplier.setNeighborhood(neighborhood);
		supplier.setPhone(phone);
		supplier.setStateRegistration(stateInscrition);
		supplier.setStreet(street);
		supplier.setFiscalClassification(fiscalClassification);
		Supplier s = (Supplier) cboSupplier.getSelectedItem();
		supplier.setId(s.getId());
		return supplier;
	}
	private boolean hasItem(DefaultTableModel tbl, Material obj) {
		for(int i = tbl.getRowCount() - 1; i>=0; i--) {
			Material product = (Material) tbl.getValueAt(i, 0);
			if(product.getId() == obj.getId()) {
				return true;
			}
		}
		return false;
	}
}
