package sales.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import model.Client;
import model.Material;
import model.Supplier;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.ApprovalOfSupplierController;
import userInterface.components.ComboBoxAutoCompletion;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;
import database.dao.ApprovalOfSupplierDAO;

public class ApprovalOfSuppliersFrame extends JFrame {
	private static final long serialVersionUID = -8366385217334396998L;

	private ApprovalOfSuppliersFrame frame = this;
	private ApprovalOfSupplierController controller;

	private JPanel panelBotao;
	private JPanel panelJust;
	private JPanel panel;

	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane;

	private DateField txtQualificationDate;

	private JTextField txtEvidence;
	private JTextField txtObservation_1;
	private JTextField txtEvidence_2;
	private JTextField txtObservation_2;
	private JTextField txtEvidence_3;
	private JTextField txtObservation_3;

	private ButtonGroup bg1;
	private ButtonGroup bg2;
	private ButtonGroup bg3;
	private ButtonGroup bg4;
	private ButtonGroup bg5;
	private ButtonGroup bg6;

	private JLabel deAprovao;
	private JLabel lblTipoDoProcesso;
	private JLabel lblNomeDoFornecedor;
	private JLabel lblMaterial;
	private JLabel lblDescrioDoServiomaterial;
	private JLabel lblQualificao;
	private JLabel lblNewLabel_1;
	private JLabel lblHistricoDoFornecimento;
	private JLabel lblCapacitaoDoFornecedor;
	private JLabel lblCliente;
	private JLabel lblPreo;
	private JLabel lblQualidade;
	private JLabel lblAtendimento;
	private JLabel lblService;
	private JLabel lblAtende;
	private JLabel lblNewLabel;
	private JLabel lblObservao;
	private JLabel lblSistemaDeQualidade;

	private JButton btnConfirmar;
	private JButton btnCancelar;

	private JRadioButton rdbtnSimEleSt;
	private JRadioButton rdbtnSim;
	private JRadioButton rdbtnNo;
	private JRadioButton rdbtnSim_1;
	private JRadioButton rdbtnNo_1;
	private JRadioButton rdbtnNa_1;
	private JRadioButton rdbtnSim_2;
	private JRadioButton rdbtnNo_2;
	private JRadioButton rdbtnBom;
	private JRadioButton rdbtnRuim;
	private JRadioButton rdbtnPssimo;
	private JRadioButton rdbtnBom_1;
	private JRadioButton rdbtnRuim_1;
	private JRadioButton rdbtnPssimo_1;
	private JRadioButton rdbtnBom_2;
	private JRadioButton rdbtnRuim_2;
	private JRadioButton rdbtnPssimo_2;
	private JRadioButton rdbtnNoPelosMotivos;
	private JRadioButton rdbtnNa;
	private JRadioButton rdbtnNa_2;

	private JComboBox<Material> cboMaterial;
	private JComboBox<String> cboService;
	private JComboBox<Client> cboClient;
	private JComboBox<Object> cboQualificationType;
	private JComboBox<Supplier> cboSupplier;

	private JTextArea txtMaterialDescription;
	private JTextArea txtJustification;

	private boolean approved;

	private int serviceRate;

	private int qualityRate;

	private int priceRate;

	private int qualitySystem;

	private int recordOfDelivering;

	private int supplierCapacity;


	public ApprovalOfSuppliersFrame() {
		controller = new ApprovalOfSupplierController();
		this.initizalize();
		this.setListeners();
	}

	private void initizalize() {
		setLocationRelativeTo(null);
		Icon.setIcon(frame);
		this.setBounds(100, 100, 811, 574);
		setMinimumSize(new Dimension(811, 574));
		setPreferredSize(new Dimension(811,574));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle("Homologação de Fornecedores");
		getContentPane().setLayout(new BorderLayout(0, 0));
		JTabbedPane tab = new JTabbedPane();
		getContentPane().add(tab, BorderLayout.CENTER);
		initializeLaudo();
		tab.addTab("Laudo", panel);
		initializeFinalization();
		tab.addTab("Finalização", panelJust);
		initializeSub();
	}

	private void initializeFinalization() {
		panelJust = new JPanel();

		lblNewLabel_1 = new JLabel(
		        "O fornecedor acima mencionado está qualificado para o fornecimento à Autitec Industrial?");

		rdbtnSimEleSt = new JRadioButton("Sim ele está habilitado!");

		rdbtnNoPelosMotivos = new JRadioButton("Não, pelos motivos na justificativa a baixo!");

		ButtonGroup bg7 = new ButtonGroup();
		bg7.add(rdbtnNoPelosMotivos);
		bg7.add(rdbtnSimEleSt);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder("JUSTIFICATIVA"));

		GroupLayout gl_panelJust = new GroupLayout(panelJust);
		gl_panelJust
		        .setHorizontalGroup(gl_panelJust
		                .createParallelGroup(Alignment.LEADING)
		                .addGroup(
		                        gl_panelJust
		                                .createSequentialGroup()
		                                .addGroup(
		                                        gl_panelJust
		                                                .createParallelGroup(Alignment.LEADING)
		                                                .addGroup(
		                                                        gl_panelJust
		                                                                .createSequentialGroup()
		                                                                .addGap(18)
		                                                                .addGroup(
		                                                                        gl_panelJust
		                                                                                .createParallelGroup(
		                                                                                        Alignment.LEADING)
		                                                                                .addComponent(lblNewLabel_1)
		                                                                                .addGroup(
		                                                                                        gl_panelJust
		                                                                                                .createSequentialGroup()
		                                                                                                .addComponent(
		                                                                                                        rdbtnSimEleSt)
		                                                                                                .addPreferredGap(
		                                                                                                        ComponentPlacement.RELATED)
		                                                                                                .addComponent(
		                                                                                                        rdbtnNoPelosMotivos))))
		                                                .addGroup(
		                                                        gl_panelJust
		                                                                .createSequentialGroup()
		                                                                .addContainerGap()
		                                                                .addComponent(scrollPane_1,
		                                                                        GroupLayout.DEFAULT_SIZE, 618,
		                                                                        Short.MAX_VALUE))).addContainerGap()));
		gl_panelJust.setVerticalGroup(gl_panelJust.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_panelJust
		                .createSequentialGroup()
		                .addGap(24)
		                .addComponent(lblNewLabel_1)
		                .addPreferredGap(ComponentPlacement.UNRELATED)
		                .addGroup(
		                        gl_panelJust.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnSimEleSt)
		                                .addComponent(rdbtnNoPelosMotivos)).addGap(18)
		                .addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE).addGap(80)));

		txtJustification = new JTextArea();
		txtJustification.setLineWrap(true);
		txtJustification.setWrapStyleWord(true);

		scrollPane_1.setViewportView(txtJustification);
		panelJust.setLayout(gl_panelJust);

		new DateField();

	}

	private void initializeLaudo() {

		panel = new JPanel();

		bg1 = new ButtonGroup();

		bg2 = new ButtonGroup();

		bg3 = new ButtonGroup();

		bg4 = new ButtonGroup();

		bg5 = new ButtonGroup();

		bg6 = new ButtonGroup();

		deAprovao = new JLabel("Data da Qualificação");

		lblTipoDoProcesso = new JLabel("Tipo do Processo de Qualificação");

		lblNomeDoFornecedor = new JLabel("Nome do Fornecedor");

		lblMaterial = new JLabel("Material");

		lblDescrioDoServiomaterial = new JLabel("Descrição do Serviço/Material");

		lblQualificao = new JLabel("Qualificação:");
		txtQualificationDate = CalendarFactory.createDateField();
		txtQualificationDate.setValue(null);

		cboQualificationType = new JComboBox<Object>();
		cboQualificationType.addItem("Fornecedor Novo");
		cboQualificationType.addItem("Fornecedor Tradicional");
		cboQualificationType.addItem("Requalificação");
		cboQualificationType.setSelectedIndex(-1);

		lblService = new JLabel("Serviço");

		lblAtende = new JLabel("Atende");

		lblNewLabel = new JLabel("Evidencia");

		lblObservao = new JLabel("Observação");

		lblSistemaDeQualidade = new JLabel("Sistema de Qualidade");

		rdbtnSim = new JRadioButton("Sim");

		rdbtnNo = new JRadioButton("Não");

		rdbtnNa = new JRadioButton("NA");

		bg1.add(rdbtnNa);
		bg1.add(rdbtnNo);
		bg1.add(rdbtnSim);

		txtEvidence = new JTextField();
		txtEvidence.setColumns(10);

		txtObservation_1 = new JTextField();
		txtObservation_1.setColumns(10);

		lblHistricoDoFornecimento = new JLabel("Histórico do Fornecimento");

		rdbtnSim_1 = new JRadioButton("Sim");

		rdbtnNo_1 = new JRadioButton("Não");

		rdbtnNa_1 = new JRadioButton("NA");
		bg2.add(rdbtnNa_1);
		bg2.add(rdbtnNo_1);
		bg2.add(rdbtnSim_1);

		txtEvidence_2 = new JTextField();
		txtEvidence_2.setColumns(10);

		txtObservation_2 = new JTextField();
		txtObservation_2.setColumns(10);

		lblCapacitaoDoFornecedor = new JLabel("Capacitação do Fornecedor");

		rdbtnSim_2 = new JRadioButton("Sim");

		rdbtnNo_2 = new JRadioButton("Não");

		rdbtnNa_2 = new JRadioButton("NA");

		bg3.add(rdbtnNa_2);
		bg3.add(rdbtnNo_2);
		bg3.add(rdbtnSim_2);

		txtEvidence_3 = new JTextField();
		txtEvidence_3.setColumns(10);

		txtObservation_3 = new JTextField();
		txtObservation_3.setColumns(10);

		cboMaterial = new JComboBox<Material>();
		controller.fillProducts(cboMaterial);
		cboMaterial.setSelectedIndex(-1);
		cboService = new JComboBox<String>();
		cboService.setSelectedIndex(-1);

		lblCliente = new JLabel("Cliente");

		cboClient = new JComboBox<Client>();
		controller.fillClient(cboClient);
		ComboBoxAutoCompletion cboAClient = new ComboBoxAutoCompletion(cboClient);
		cboClient.setSelectedIndex(-1);

		lblPreo = new JLabel("Preço");

		rdbtnBom = new JRadioButton("Bom");

		rdbtnRuim = new JRadioButton("Ruim");

		rdbtnPssimo = new JRadioButton("Péssimo");

		bg4.add(rdbtnRuim);
		bg4.add(rdbtnPssimo);
		bg4.add(rdbtnBom);

		lblQualidade = new JLabel("Qualidade");

		rdbtnBom_1 = new JRadioButton("Bom");

		rdbtnRuim_1 = new JRadioButton("Ruim");

		rdbtnPssimo_1 = new JRadioButton("Péssimo");

		bg5.add(rdbtnBom_1);
		bg5.add(rdbtnPssimo_1);
		bg5.add(rdbtnRuim_1);

		lblAtendimento = new JLabel("Atendimento");

		rdbtnBom_2 = new JRadioButton("Bom");

		rdbtnRuim_2 = new JRadioButton("Ruim");

		rdbtnPssimo_2 = new JRadioButton("Péssimo");

		bg6.add(rdbtnPssimo_2);
		bg6.add(rdbtnRuim_2);
		bg6.add(rdbtnBom_2);

		scrollPane = new JScrollPane();

		txtMaterialDescription = new JTextArea();
		txtMaterialDescription.setLineWrap(true);
		txtMaterialDescription.setWrapStyleWord(true);
		scrollPane.setViewportView(txtMaterialDescription);

		cboSupplier = new JComboBox<Supplier>();
		controller.fillSuppliers(cboSupplier);
		ComboBoxAutoCompletion cbo = new ComboBoxAutoCompletion(cboSupplier);
		cboSupplier.setSelectedIndex(-1);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(deAprovao, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtQualificationDate, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDescrioDoServiomaterial, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblHistricoDoFornecimento))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
										.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
											.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
												.addComponent(rdbtnSim)
												.addComponent(rdbtnSim_1))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(rdbtnNo)
													.addGap(514))
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(rdbtnNo_1)
													.addGap(512)))))))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblNomeDoFornecedor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboSupplier, 0, 198, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblTipoDoProcesso)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboQualificationType, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(rdbtnBom)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnRuim)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnPssimo)
									.addGap(79)
									.addComponent(rdbtnBom_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblQualidade)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(rdbtnRuim_1)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(rdbtnPssimo_1)))
									.addGap(46)
									.addComponent(rdbtnBom_2)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblAtendimento)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(rdbtnRuim_2)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(rdbtnPssimo_2))))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(58)
									.addComponent(lblPreo)))
							.addContainerGap(168, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblCliente)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboClient, 0, 357, Short.MAX_VALUE)
							.addGap(386))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblQualificao, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
											.addGap(69))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblSistemaDeQualidade)
											.addGap(29)))
									.addGap(125))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCapacitaoDoFornecedor)
									.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
									.addComponent(rdbtnSim_2)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnNo_2)
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(rdbtnNa_1)
										.addComponent(rdbtnNa)
										.addComponent(rdbtnNa_2))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtEvidence_2, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
										.addComponent(txtEvidence, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
										.addComponent(txtEvidence_3, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtObservation_2)
										.addComponent(txtObservation_3, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
										.addComponent(txtObservation_1))
									.addContainerGap())
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(145)
									.addComponent(lblObservao)
									.addGap(185)
									.addComponent(lblNewLabel)
									.addGap(66))))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
							.addComponent(lblAtende)
							.addGap(544))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblMaterial, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblService)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboService, 0, 419, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(deAprovao)
						.addComponent(txtQualificationDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNomeDoFornecedor)
						.addComponent(cboSupplier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cboQualificationType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipoDoProcesso))
					.addGap(29)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaterial)
						.addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblService)
						.addComponent(cboService, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAtende)
								.addComponent(lblNewLabel)
								.addComponent(lblObservao, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQualificao)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(40)
							.addComponent(lblDescrioDoServiomaterial)
							.addGap(62)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtObservation_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEvidence, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbtnNa)
								.addComponent(rdbtnNo)
								.addComponent(rdbtnSim))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtObservation_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEvidence_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbtnNa_1)
								.addComponent(rdbtnNo_1)
								.addComponent(rdbtnSim_1)
								.addComponent(lblHistricoDoFornecimento))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtObservation_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEvidence_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbtnNa_2)
								.addComponent(rdbtnNo_2)
								.addComponent(rdbtnSim_2)
								.addComponent(lblCapacitaoDoFornecedor)))
						.addComponent(lblSistemaDeQualidade))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cboClient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCliente))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPreo)
						.addComponent(lblQualidade)
						.addComponent(lblAtendimento))
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnBom)
						.addComponent(rdbtnRuim)
						.addComponent(rdbtnPssimo)
						.addComponent(rdbtnPssimo_2)
						.addComponent(rdbtnRuim_2)
						.addComponent(rdbtnBom_2)
						.addComponent(rdbtnPssimo_1)
						.addComponent(rdbtnRuim_1)
						.addComponent(rdbtnBom_1))
					.addContainerGap())
		);

		panel.setLayout(gl_panel);
	}

	private void initializeSub() {
		panelBotao = new JPanel();
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		panelBotao.setLayout(fl);
		getContentPane().add(panelBotao, BorderLayout.SOUTH);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(getClass().getResource("/resources/cancel.png")));
		panelBotao.add(btnCancelar);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setIcon(new ImageIcon(ApprovalOfSuppliersFrame.class.getResource("/resources/ok.png")));
		panelBotao.add(btnConfirmar);
	}

	private void setListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeFrame(frame);
			}
		});
		ActionListener ButtonListeners = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnCancelar)) {
					controller.closeFrame(frame);
				} else if (e.getSource().equals(btnConfirmar)) {
					if (isComplete()) {
						approve();
						ClearFrame.clear(frame);
					}
				}
			}
		};
		btnConfirmar.addActionListener(ButtonListeners);
		btnCancelar.addActionListener(ButtonListeners);
	}

	private boolean isComplete() {
		boolean complete = false;
		String erro = null;
		if (txtQualificationDate.getValue().equals(null))
			erro = "Insita a data da qualificação";
		else if (txtMaterialDescription.getText() == "" || txtMaterialDescription.getText().equals(null))
			erro = "Insira um descrição do material";
		else if (txtJustification.getText() == "" || txtJustification.getText().equals(null))
			erro = "Indira uma justificação";
		else if (cboClient.getSelectedIndex() == -1)
			erro = "Selecione um cliente";
		//else if (cboMaterial.getSelectedIndex() == -1)
		//	erro = "Selecione um material";
		else if (cboQualificationType.getSelectedIndex() == -1)
			erro = "Selecione um processo de qualificação";
		//else if (cboService.getSelectedIndex() == -1)
		//	erro = "Selecione um Seviço";
		else if (cboSupplier.getSelectedIndex() == -1)
			erro = "Selecione um fornecedor";
		else if (bg1.getSelection().equals(null))
			erro = "Selecione um opção para o sistema de qualidade";
		else if (bg2.getSelection().equals(null))
			erro = "Selecione uma opção para o histórico de fornecedor";
		else if (bg3.getSelection().equals(null))
			erro = "Selecione uma opção para a capacitação do fornecedor";
		else if (bg4.getSelection().equals(null))
			erro = "Selecione uma opção para o preço";
		else if (bg5.getSelection().equals(null))
			erro = "Selecione uma opção para a qualidade";
		else if (bg6.getSelection().equals(null))
			erro = "Selecione um opção para o atendimento";
		else
			complete = true;
		if (!complete) {
			String title = "Erro ao homologar fornecedor";
			ShowMessage.errorMessage(frame, title, erro);
			return complete;
		}
		return complete;
	}

	private void approve() {
		Date qualificationDate = (Date) txtQualificationDate.getValue();
		Supplier s = (Supplier) cboSupplier.getSelectedItem();
		int supplierId = s.getId();
		String qualificationType = (String) cboQualificationType.getSelectedItem();
		Material p = (Material) cboMaterial.getSelectedItem();
		String material = p.getName();
		String service = (String) cboService.getSelectedItem();
		if (rdbtnSim.isSelected()) {
			qualitySystem = 0;
		} else if (rdbtnNo.isSelected()) {
			qualitySystem = 1;
		} else if (rdbtnNa.isSelected()) {
			qualitySystem = 2;
		}
		String qualityEvidence = txtEvidence.getText();
		String qualityObservation = txtObservation_1.getText();
		if(rdbtnSim_1.isSelected()) {
			recordOfDelivering = 0;
		}else if(rdbtnNo_1.isSelected()) {
			recordOfDelivering = 1; 
		}else if(rdbtnNa_1.isSelected()) {
			 recordOfDelivering = 2;
		}
		String recordOfDeliveringEvidence = txtEvidence_2.getText();
		String recordOfDeliveringObservation = txtObservation_2.getText();
		if(rdbtnSim_2.isSelected()) {
			supplierCapacity = 0;
		}else if(rdbtnNo_2.isSelected()) {
			 supplierCapacity = 1;
		}else if(rdbtnNa_2.isSelected()) {
			 supplierCapacity = 2;
		}
		String supplierCapacityEvidence = txtEvidence_3.getText();
		String supplierCapacityObservation = txtObservation_3.getText();
		Client c = (Client) cboClient.getSelectedItem();
		int client = c.getId();
		String descrition = txtMaterialDescription.getText();
		if(rdbtnBom.isSelected()) {
			priceRate = 0;
		}else if(rdbtnRuim.isSelected()) {
			priceRate = 1;
		}else if(rdbtnPssimo.isSelected()) {
			priceRate = 2;
		}
		if(rdbtnBom_1.isSelected()) {
			qualityRate = 0;
		}else if(rdbtnRuim_1.isSelected()) {
			qualityRate = 1;
		}else if(rdbtnPssimo_1.isSelected()) {
			qualityRate = 2;
		}
		if(rdbtnBom_2.isSelected()) {
			serviceRate = 0;
		}else if(rdbtnRuim_2.isSelected()) {
			serviceRate = 1;
		}else if(rdbtnPssimo_2.isSelected()) {
			serviceRate = 2;
		}
		if(rdbtnSimEleSt.isSelected()) {
			approved = true;
		}else {
			approved = false;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qualificationDate", qualificationDate);
		map.put("supplier", supplierId);
		map.put("qualificationType", qualificationType);
		map.put("material", material);
		map.put("service", service);
		map.put("qualitySystem", qualitySystem);
		map.put("qualityEvidence", qualityEvidence);
		map.put("qualityObservation", qualityObservation);
		map.put("recordOfDelivering", recordOfDelivering);
		map.put("recordOfDeliveringEvidence", recordOfDeliveringEvidence);
		map.put("recordOfDeliveringObservation", recordOfDeliveringObservation);
		map.put("descrition", descrition);
		map.put("supplierCapacity", supplierCapacity);
		map.put("supplierCapacityEvidence", supplierCapacityEvidence);
		map.put("supplierCapacityObservation", supplierCapacityObservation);
		map.put("priceRate", priceRate);
		map.put("qualityRate", qualityRate);
		map.put("serviceRate", serviceRate);
		map.put("approved", approved);
		ApprovalOfSupplierDAO aDAO = new ApprovalOfSupplierDAO();
		aDAO.persistRegister(map);
	}
}
