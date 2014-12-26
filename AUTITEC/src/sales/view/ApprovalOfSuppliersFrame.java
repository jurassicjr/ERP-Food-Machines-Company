package sales.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
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

import model.Product;
import model.Supplier;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.SalesController;
import util.ShowMessage;

public class ApprovalOfSuppliersFrame extends JFrame {

	public ApprovalOfSuppliersFrame() {
		controller = new SalesController();
		this.initizalize();
		this.setListeners();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8366385217334396998L;

	private ApprovalOfSuppliersFrame frame = this;
	private SalesController controller;

	private JPanel panelBotao;
	private JPanel panelJust;
	private JPanel panel;

	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane;

	private DateField txtDataQuali_1;

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

	private JComboBox<Product> cboMaterial;
	private JComboBox<String> cboService;
	private JComboBox<String> cboClient;
	private JComboBox<Object> cboQualificationProcess;
	private JComboBox<Supplier> cboSupplier;

	private JTextArea txtMaterialDescription;
	private JTextArea txtJustification;

	private void initizalize() {
		setLocationRelativeTo(null);
		this.setBounds(100, 100, 680, 530);
		setMinimumSize(new Dimension(680, 530));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle("Homologação de Fornecedores");
		getContentPane().setLayout(new BorderLayout(0, 0));
		// ImageIcon img = new
		// ImageIcon(getClass().getResource("/resources/Logo Frontal.png"));
		// this.setIconImage(img.getImage());
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
		txtDataQuali_1 = CalendarFactory.createDateField();
		txtDataQuali_1.setValue(null);

		cboQualificationProcess = new JComboBox<Object>();
		cboQualificationProcess.setModel(new DefaultComboBoxModel<Object>(new String[] { "Fornecedor Novo",
		        "Fornecedor Tradicional", "Requalificação" }));

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

		cboMaterial = new JComboBox<Product>();

		cboService = new JComboBox<String>();

		lblCliente = new JLabel("Cliente");

		cboClient = new JComboBox<String>();

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

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
		        .createParallelGroup(Alignment.LEADING)
		        .addGroup(
		                gl_panel.createSequentialGroup()
		                        .addContainerGap()
		                        .addGroup(
		                                gl_panel.createParallelGroup(Alignment.TRAILING)
		                                        .addGroup(
		                                                gl_panel.createSequentialGroup()
		                                                        .addComponent(rdbtnBom)
		                                                        .addPreferredGap(ComponentPlacement.RELATED)
		                                                        .addComponent(rdbtnRuim)
		                                                        .addPreferredGap(ComponentPlacement.RELATED)
		                                                        .addComponent(rdbtnPssimo)
		                                                        .addGap(79)
		                                                        .addComponent(rdbtnBom_1)
		                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                        .addGroup(
		                                                                gl_panel.createParallelGroup(Alignment.LEADING)
		                                                                        .addComponent(lblQualidade)
		                                                                        .addGroup(
		                                                                                gl_panel.createSequentialGroup()
		                                                                                        .addComponent(
		                                                                                                rdbtnRuim_1)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.UNRELATED)
		                                                                                        .addComponent(
		                                                                                                rdbtnPssimo_1)))
		                                                        .addGap(46)
		                                                        .addComponent(rdbtnBom_2)
		                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                        .addGroup(
		                                                                gl_panel.createParallelGroup(Alignment.LEADING)
		                                                                        .addComponent(lblAtendimento)
		                                                                        .addGroup(
		                                                                                gl_panel.createSequentialGroup()
		                                                                                        .addComponent(
		                                                                                                rdbtnRuim_2)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.UNRELATED)
		                                                                                        .addComponent(
		                                                                                                rdbtnPssimo_2)))
		                                                        .addGap(176))
		                                        .addGroup(
		                                                gl_panel.createSequentialGroup()
		                                                        .addGroup(
		                                                                gl_panel.createParallelGroup(Alignment.LEADING)
		                                                                        .addGroup(
		                                                                                gl_panel.createSequentialGroup()
		                                                                                        .addComponent(
		                                                                                                deAprovao,
		                                                                                                GroupLayout.PREFERRED_SIZE,
		                                                                                                100,
		                                                                                                GroupLayout.PREFERRED_SIZE)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.RELATED)
		                                                                                        .addComponent(
		                                                                                                txtDataQuali_1,
		                                                                                                GroupLayout.PREFERRED_SIZE,
		                                                                                                94,
		                                                                                                GroupLayout.PREFERRED_SIZE)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.RELATED,
		                                                                                                12,
		                                                                                                Short.MAX_VALUE)
		                                                                                        .addComponent(
		                                                                                                lblTipoDoProcesso)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.RELATED)
		                                                                                        .addComponent(
		                                                                                                cboQualificationProcess,
		                                                                                                GroupLayout.PREFERRED_SIZE,
		                                                                                                242,
		                                                                                                GroupLayout.PREFERRED_SIZE))
		                                                                        .addGroup(
		                                                                                gl_panel.createSequentialGroup()
		                                                                                        .addGroup(
		                                                                                                gl_panel.createParallelGroup(
		                                                                                                        Alignment.TRAILING)
		                                                                                                        .addGroup(
		                                                                                                                gl_panel.createSequentialGroup()
		                                                                                                                        .addComponent(
		                                                                                                                                lblHistricoDoFornecimento)
		                                                                                                                        .addPreferredGap(
		                                                                                                                                ComponentPlacement.RELATED,
		                                                                                                                                14,
		                                                                                                                                Short.MAX_VALUE)
		                                                                                                                        .addComponent(
		                                                                                                                                rdbtnSim_1)
		                                                                                                                        .addPreferredGap(
		                                                                                                                                ComponentPlacement.RELATED)
		                                                                                                                        .addComponent(
		                                                                                                                                rdbtnNo_1)
		                                                                                                                        .addPreferredGap(
		                                                                                                                                ComponentPlacement.RELATED)
		                                                                                                                        .addComponent(
		                                                                                                                                rdbtnNa_1))
		                                                                                                        .addGroup(
		                                                                                                                gl_panel.createSequentialGroup()
		                                                                                                                        .addComponent(
		                                                                                                                                lblCapacitaoDoFornecedor)
		                                                                                                                        .addPreferredGap(
		                                                                                                                                ComponentPlacement.UNRELATED)
		                                                                                                                        .addComponent(
		                                                                                                                                rdbtnSim_2)
		                                                                                                                        .addPreferredGap(
		                                                                                                                                ComponentPlacement.RELATED)
		                                                                                                                        .addComponent(
		                                                                                                                                rdbtnNo_2)
		                                                                                                                        .addPreferredGap(
		                                                                                                                                ComponentPlacement.RELATED)
		                                                                                                                        .addComponent(
		                                                                                                                                rdbtnNa_2))
		                                                                                                        .addGroup(
		                                                                                                                gl_panel.createSequentialGroup()
		                                                                                                                        .addComponent(
		                                                                                                                                lblCliente)
		                                                                                                                        .addPreferredGap(
		                                                                                                                                ComponentPlacement.RELATED)
		                                                                                                                        .addComponent(
		                                                                                                                                cboClient,
		                                                                                                                                0,
		                                                                                                                                226,
		                                                                                                                                Short.MAX_VALUE))
		                                                                                                        .addGroup(
		                                                                                                                gl_panel.createSequentialGroup()
		                                                                                                                        .addGroup(
		                                                                                                                                gl_panel.createParallelGroup(
		                                                                                                                                        Alignment.LEADING)
		                                                                                                                                        .addGroup(
		                                                                                                                                                gl_panel.createSequentialGroup()
		                                                                                                                                                        .addComponent(
		                                                                                                                                                                lblSistemaDeQualidade)
		                                                                                                                                                        .addPreferredGap(
		                                                                                                                                                                ComponentPlacement.RELATED,
		                                                                                                                                                                33,
		                                                                                                                                                                Short.MAX_VALUE)
		                                                                                                                                                        .addComponent(
		                                                                                                                                                                rdbtnSim))
		                                                                                                                                        .addComponent(
		                                                                                                                                                lblQualificao,
		                                                                                                                                                GroupLayout.PREFERRED_SIZE,
		                                                                                                                                                64,
		                                                                                                                                                GroupLayout.PREFERRED_SIZE))
		                                                                                                                        .addPreferredGap(
		                                                                                                                                ComponentPlacement.RELATED)
		                                                                                                                        .addGroup(
		                                                                                                                                gl_panel.createParallelGroup(
		                                                                                                                                        Alignment.LEADING)
		                                                                                                                                        .addComponent(
		                                                                                                                                                lblAtende)
		                                                                                                                                        .addGroup(
		                                                                                                                                                gl_panel.createSequentialGroup()
		                                                                                                                                                        .addComponent(
		                                                                                                                                                                rdbtnNo)
		                                                                                                                                                        .addPreferredGap(
		                                                                                                                                                                ComponentPlacement.UNRELATED)
		                                                                                                                                                        .addComponent(
		                                                                                                                                                                rdbtnNa)))))
		                                                                                        .addGap(49)
		                                                                                        .addGroup(
		                                                                                                gl_panel.createParallelGroup(
		                                                                                                        Alignment.LEADING)
		                                                                                                        .addComponent(
		                                                                                                                txtEvidence_3,
		                                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                                108,
		                                                                                                                Short.MAX_VALUE)
		                                                                                                        .addComponent(
		                                                                                                                txtEvidence,
		                                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                                108,
		                                                                                                                Short.MAX_VALUE)
		                                                                                                        .addComponent(
		                                                                                                                txtEvidence_2,
		                                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                                108,
		                                                                                                                Short.MAX_VALUE))
		                                                                                        .addGap(41)
		                                                                                        .addGroup(
		                                                                                                gl_panel.createParallelGroup(
		                                                                                                        Alignment.LEADING)
		                                                                                                        .addComponent(
		                                                                                                                txtObservation_3,
		                                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                                152,
		                                                                                                                Short.MAX_VALUE)
		                                                                                                        .addComponent(
		                                                                                                                txtObservation_2,
		                                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                                152,
		                                                                                                                Short.MAX_VALUE)
		                                                                                                        .addComponent(
		                                                                                                                txtObservation_1,
		                                                                                                                Alignment.TRAILING,
		                                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                                152,
		                                                                                                                Short.MAX_VALUE)))
		                                                                        .addGroup(
		                                                                                gl_panel.createSequentialGroup()
		                                                                                        .addComponent(
		                                                                                                lblDescrioDoServiomaterial,
		                                                                                                GroupLayout.PREFERRED_SIZE,
		                                                                                                144,
		                                                                                                GroupLayout.PREFERRED_SIZE)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.RELATED)
		                                                                                        .addGroup(
		                                                                                                gl_panel.createParallelGroup(
		                                                                                                        Alignment.LEADING)
		                                                                                                        .addGroup(
		                                                                                                                Alignment.TRAILING,
		                                                                                                                gl_panel.createSequentialGroup()
		                                                                                                                        .addComponent(
		                                                                                                                                lblNewLabel)
		                                                                                                                        .addGap(116)
		                                                                                                                        .addComponent(
		                                                                                                                                lblObservao)
		                                                                                                                        .addGap(54))
		                                                                                                        .addComponent(
		                                                                                                                scrollPane))))
		                                                        .addGap(175))))
		        .addGroup(
		                gl_panel.createSequentialGroup().addGap(68).addComponent(lblPreo)
		                        .addContainerGap(703, Short.MAX_VALUE))
		        .addGroup(
		                gl_panel.createSequentialGroup()
		                        .addContainerGap()
		                        .addGroup(
		                                gl_panel.createParallelGroup(Alignment.LEADING)
		                                        .addGroup(
		                                                gl_panel.createSequentialGroup()
		                                                        .addComponent(lblMaterial, GroupLayout.PREFERRED_SIZE,
		                                                                43, GroupLayout.PREFERRED_SIZE)
		                                                        .addPreferredGap(ComponentPlacement.RELATED)
		                                                        .addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE,
		                                                                255, GroupLayout.PREFERRED_SIZE))
		                                        .addGroup(
		                                                gl_panel.createSequentialGroup()
		                                                        .addComponent(lblNomeDoFornecedor)
		                                                        .addPreferredGap(ComponentPlacement.RELATED)
		                                                        .addComponent(cboSupplier, 0, GroupLayout.DEFAULT_SIZE,
		                                                                Short.MAX_VALUE)))
		                        .addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblService).addGap(4)
		                        .addComponent(cboService, 0, 262, Short.MAX_VALUE).addGap(175)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_panel.createSequentialGroup()
		                .addGap(20)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.LEADING)
		                                .addGroup(
		                                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                                .addComponent(deAprovao)
		                                                .addComponent(cboQualificationProcess,
		                                                        GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                                        GroupLayout.PREFERRED_SIZE)
		                                                .addComponent(lblTipoDoProcesso))
		                                .addComponent(txtDataQuali_1, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addGap(18)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblNomeDoFornecedor)
		                                .addComponent(cboSupplier, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addGap(11)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblMaterial)
		                                .addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(lblService)
		                                .addComponent(cboService, GroupLayout.PREFERRED_SIZE, 20,
		                                        GroupLayout.PREFERRED_SIZE))
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.LEADING)
		                                .addGroup(
		                                        gl_panel.createSequentialGroup().addGap(40)
		                                                .addComponent(lblDescrioDoServiomaterial))
		                                .addGroup(
		                                        gl_panel.createSequentialGroup()
		                                                .addGap(18)
		                                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 54,
		                                                        GroupLayout.PREFERRED_SIZE)))
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.LEADING)
		                                .addGroup(
		                                        gl_panel.createSequentialGroup()
		                                                .addGap(18)
		                                                .addGroup(
		                                                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                                                .addComponent(lblAtende)
		                                                                .addComponent(lblQualificao)))
		                                .addGroup(
		                                        gl_panel.createSequentialGroup()
		                                                .addGap(18)
		                                                .addGroup(
		                                                        gl_panel.createParallelGroup(Alignment.LEADING)
		                                                                .addComponent(lblNewLabel)
		                                                                .addComponent(lblObservao))))
		                .addGap(9)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblSistemaDeQualidade)
		                                .addComponent(txtEvidence, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(txtObservation_1, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(rdbtnNa).addComponent(rdbtnNo).addComponent(rdbtnSim))
		                .addGap(18)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblHistricoDoFornecimento)
		                                .addComponent(txtEvidence_2, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(txtObservation_2, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(rdbtnNa_1).addComponent(rdbtnNo_1).addComponent(rdbtnSim_1))
		                .addGap(18)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblCapacitaoDoFornecedor)
		                                .addComponent(txtEvidence_3, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(txtObservation_3, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(rdbtnNa_2).addComponent(rdbtnNo_2).addComponent(rdbtnSim_2))
		                .addPreferredGap(ComponentPlacement.UNRELATED)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblCliente)
		                                .addComponent(cboClient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE))
		                .addGap(18)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblPreo)
		                                .addComponent(lblQualidade).addComponent(lblAtendimento))
		                .addGap(7)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnBom)
		                                .addComponent(rdbtnRuim).addComponent(rdbtnPssimo).addComponent(rdbtnPssimo_2)
		                                .addComponent(rdbtnRuim_2).addComponent(rdbtnBom_2).addComponent(rdbtnPssimo_1)
		                                .addComponent(rdbtnRuim_1).addComponent(rdbtnBom_1))
		                .addContainerGap(10, Short.MAX_VALUE)));

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
					if(isComplete()) {
						
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
		if (txtDataQuali_1.getValue().equals(null))
			erro = "Insita a data da qualificação";
		else if (txtMaterialDescription.getText() == "" || txtMaterialDescription.getText().equals(null))
			erro = "Insira um descrição do material";
		else if (txtJustification.getText() == "" || txtJustification.getText().equals(null))
			erro = "Indira uma justificação";
		else if (cboClient.getSelectedIndex() == -1)
			erro = "Selecione um cliente";
		else if (cboMaterial.getSelectedIndex() == -1)
			erro = "Selecione um material";
		else if (cboQualificationProcess.getSelectedIndex() == -1)
			erro = "Selecione um processo de qualificação";
		else if (cboService.getSelectedIndex() == -1)
			erro = "Selecione um Seviço";
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
}
