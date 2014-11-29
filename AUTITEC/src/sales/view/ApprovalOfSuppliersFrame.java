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

import model.Produto;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.SalesController;

public class ApprovalOfSuppliersFrame extends JFrame {
	private JButton btnCancelar;
	private ApprovalOfSuppliersFrame frame = this;
	private SalesController controller;

	public ApprovalOfSuppliersFrame() {
		controller = new SalesController();
		this.initizalize();
		this.setListeners();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8366385217334396998L;
	private DateField txtDataQuali_1;
	private JTextField txtNomeFornecedor;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
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
	private JPanel panelBotao;
	private JButton btnConfirmar;
	private JLabel lblNewLabel_1;
	private JRadioButton rdbtnSimEleSt;
	private JPanel panelJust;
	private JPanel panel;
	private JRadioButton rdbtnSim;
	private JRadioButton rdbtnNo;
	private JLabel lblHistricoDoFornecimento;
	private JRadioButton rdbtnSim_1;
	private JRadioButton rdbtnNo_1;
	private JRadioButton rdbtnNa_1;
	private JLabel lblCapacitaoDoFornecedor;
	private JRadioButton rdbtnSim_2;
	private JRadioButton rdbtnNo_2;
	private JComboBox<Produto> cboMaterial;
	private JComboBox<String> comboBox_2;
	private JLabel lblCliente;
	private JComboBox<String> comboBox_3;
	private JLabel lblPreo;
	private JRadioButton rdbtnBom;
	private JRadioButton rdbtnRuim;
	private JRadioButton rdbtnPssimo;
	private JLabel lblQualidade;
	private JRadioButton rdbtnBom_1;
	private JRadioButton rdbtnRuim_1;
	private JRadioButton rdbtnPssimo_1;
	private JLabel lblAtendimento;
	private JRadioButton rdbtnBom_2;
	private JRadioButton rdbtnRuim_2;
	private JRadioButton rdbtnPssimo_2;
	private JRadioButton rdbtnNoPelosMotivos;
	private JScrollPane scrollPane_1;

	private void initizalize() {
		setLocationRelativeTo(null);
		this.setBounds(100, 100, 680, 530);
		setMinimumSize(new Dimension(680, 530));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle("Homologação de Fornecedores");
		getContentPane().setLayout(new BorderLayout(0, 0));
		ImageIcon img = new ImageIcon(getClass().getResource("/resources/Logo Frontal.png"));
		this.setIconImage(img.getImage());
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

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);

		scrollPane_1.setViewportView(textArea_1);
		panelJust.setLayout(gl_panelJust);

		new DateField();

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
				if (e.getSource().equals(btnCancelar))controller.closeFrame(frame);

			}
		};
		btnCancelar.addActionListener(ButtonListeners);
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

		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] { "Fornecedor Novo", "Fornecedor Tradicional",
		        "Requalificação" }));

		txtNomeFornecedor = new JTextField();
		txtNomeFornecedor.setColumns(10);

		JLabel lblServio = new JLabel("Serviço");

		JLabel lblAtende = new JLabel("Atende");

		JLabel lblNewLabel = new JLabel("Evidencia");

		JLabel lblObservao = new JLabel("Observação");

		JLabel lblSistemaDeQualidade = new JLabel("Sistema de Qualidade");

		rdbtnSim = new JRadioButton("Sim");

		rdbtnNo = new JRadioButton("Não");

		JRadioButton rdbtnNa = new JRadioButton("NA");
		bg1.add(rdbtnNa);
		bg1.add(rdbtnNo);
		bg1.add(rdbtnSim);

		textField_3 = new JTextField();
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setColumns(10);

		lblHistricoDoFornecimento = new JLabel("Histórico do Fornecimento");

		rdbtnSim_1 = new JRadioButton("Sim");

		rdbtnNo_1 = new JRadioButton("Não");

		rdbtnNa_1 = new JRadioButton("NA");
		bg2.add(rdbtnNa_1);
		bg2.add(rdbtnNo_1);
		bg2.add(rdbtnSim_1);

		textField_5 = new JTextField();
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setColumns(10);

		lblCapacitaoDoFornecedor = new JLabel("Capacitação do Fornecedor");

		rdbtnSim_2 = new JRadioButton("Sim");

		rdbtnNo_2 = new JRadioButton("Não");

		JRadioButton rdbtnNa_2 = new JRadioButton("NA");
		bg3.add(rdbtnNa_2);
		bg3.add(rdbtnNo_2);
		bg3.add(rdbtnSim_2);

		textField_7 = new JTextField();
		textField_7.setColumns(10);

		textField_8 = new JTextField();
		textField_8.setColumns(10);

		cboMaterial = new JComboBox<Produto>();

		comboBox_2 = new JComboBox<String>();

		lblCliente = new JLabel("Cliente");

		comboBox_3 = new JComboBox<String>();

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

		JScrollPane scrollPane = new JScrollPane();

		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea);

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
		                                                                                                ComponentPlacement.UNRELATED)
		                                                                                        .addComponent(
		                                                                                                txtDataQuali_1,
		                                                                                                GroupLayout.PREFERRED_SIZE,
		                                                                                                94,
		                                                                                                GroupLayout.PREFERRED_SIZE)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.RELATED,
		                                                                                                6,
		                                                                                                Short.MAX_VALUE)
		                                                                                        .addComponent(
		                                                                                                lblTipoDoProcesso)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.RELATED)
		                                                                                        .addComponent(
		                                                                                                comboBox,
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
		                                                                                                                                comboBox_3,
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
		                                                                                                                textField_7,
		                                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                                108,
		                                                                                                                Short.MAX_VALUE)
		                                                                                                        .addComponent(
		                                                                                                                textField_3,
		                                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                                108,
		                                                                                                                Short.MAX_VALUE)
		                                                                                                        .addComponent(
		                                                                                                                textField_5,
		                                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                                108,
		                                                                                                                Short.MAX_VALUE))
		                                                                                        .addGap(41)
		                                                                                        .addGroup(
		                                                                                                gl_panel.createParallelGroup(
		                                                                                                        Alignment.LEADING)
		                                                                                                        .addComponent(
		                                                                                                                textField_8,
		                                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                                152,
		                                                                                                                Short.MAX_VALUE)
		                                                                                                        .addComponent(
		                                                                                                                textField_6,
		                                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                                152,
		                                                                                                                Short.MAX_VALUE)
		                                                                                                        .addComponent(
		                                                                                                                textField_4,
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
		                                                                                                                        .addGap(113)
		                                                                                                                        .addComponent(
		                                                                                                                                lblObservao)
		                                                                                                                        .addGap(56))
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
		                                gl_panel.createParallelGroup(Alignment.LEADING, false)
		                                        .addGroup(
		                                                gl_panel.createSequentialGroup()
		                                                        .addComponent(lblMaterial, GroupLayout.PREFERRED_SIZE,
		                                                                43, GroupLayout.PREFERRED_SIZE)
		                                                        .addPreferredGap(ComponentPlacement.RELATED)
		                                                        .addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE,
		                                                                255, GroupLayout.PREFERRED_SIZE)
		                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                        .addComponent(lblServio)
		                                                        .addPreferredGap(ComponentPlacement.RELATED,
		                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                                                        .addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE,
		                                                                254, GroupLayout.PREFERRED_SIZE))
		                                        .addGroup(
		                                                gl_panel.createSequentialGroup()
		                                                        .addComponent(lblNomeDoFornecedor)
		                                                        .addPreferredGap(ComponentPlacement.RELATED)
		                                                        .addComponent(txtNomeFornecedor, GroupLayout.PREFERRED_SIZE,
		                                                                509, GroupLayout.PREFERRED_SIZE)))
		                        .addContainerGap(175, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_panel.createSequentialGroup()
		                .addGap(20)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(deAprovao)
		                                .addComponent(txtDataQuali_1, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE).addComponent(lblTipoDoProcesso))
		                .addGap(18)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblNomeDoFornecedor)
		                                .addComponent(txtNomeFornecedor, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addGap(18)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblMaterial)
		                                .addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE)
		                                .addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 20,
		                                        GroupLayout.PREFERRED_SIZE).addComponent(lblServio))
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
		                .addGap(18)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblAtende)
		                                .addComponent(lblQualificao).addComponent(lblObservao)
		                                .addComponent(lblNewLabel))
		                .addGap(9)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblSistemaDeQualidade)
		                                .addComponent(textField_3, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(textField_4, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(rdbtnNa).addComponent(rdbtnNo).addComponent(rdbtnSim))
		                .addGap(18)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblHistricoDoFornecimento)
		                                .addComponent(textField_5, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(textField_6, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(rdbtnNa_1).addComponent(rdbtnNo_1).addComponent(rdbtnSim_1))
		                .addGap(18)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblCapacitaoDoFornecedor)
		                                .addComponent(textField_7, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(textField_8, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(rdbtnNa_2).addComponent(rdbtnNo_2).addComponent(rdbtnSim_2))
		                .addPreferredGap(ComponentPlacement.UNRELATED)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblCliente)
		                                .addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
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
		                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

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
}
