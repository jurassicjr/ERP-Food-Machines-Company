package sales.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.MaskFormatter;

import model.City;
import model.State;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.SalesController;

public class RegisterSuppliersFrame extends JFrame {
	private JTextField textField_8;
	private JFrame frame = this;
	private JButton btnCancelar;
	private static final long serialVersionUID = -3611459763736774158L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txtCEP;
	private JTextField textField_6;
	private JTextField textField_7;
	private DateField txtdataExpiracao;
	private JTextField textField_9;
	private JPanel panel;
	private JPanel panelRodape;
	private JLabel lblClassificaoFiscal;
	private JLabel lblCertificado;
	private JButton btnRegistrar;
	private JPanel panelCe;
	private JLabel lblDataDeExpirao;
	private JLabel lblCertificadoDoMaterial;
	private JRadioButton rdbtnSim;
	private JRadioButton rdbtnNo;
	private JButton btnAnexarCertificado;
	private JLabel lblJustificado;
	private JScrollPane scrollPane;
	private JTabbedPane tabbedPane;
	private SalesController controller;
	private JComboBox<City> cboCity;
	private JComboBox<State> cboState;
	private JButton btnConfirmar;

	public RegisterSuppliersFrame() {
		controller = new SalesController();
		initialize();
		setListeners();
	}

	private void initialize() {
		//setBounds(100, 100, 496, 404);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(486, 404));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setTitle("Registro de Fornecedores");
		getContentPane().setLayout(new BorderLayout(0, 0));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		initializeContact();
		tabbedPane.addTab("Contato",panel);
		initializePanel();
		tabbedPane.addTab("Certificações", panelCe);
		initializeSub();
	}

	private void initializeSub() {
		panelRodape = new JPanel();
		getContentPane().add(panelRodape, BorderLayout.SOUTH);
		panelRodape.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setIcon(new ImageIcon(RegisterSuppliersFrame.class.getResource("/resources/ok.png")));
		panelRodape.add(btnConfirmar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(RegisterSuppliersFrame.class.getResource("/resources/cancel.png")));
		panelRodape.add(btnCancelar);
		
		controller.fillStateAndCity(cboState, cboCity);
	}

	private void initializePanel() {

		lblClassificaoFiscal = new JLabel("Classificação Fiscal");

		lblCertificado = new JLabel("Certificado");

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setIcon(new ImageIcon(RegisterSuppliersFrame.class.getResource("/resources/ok.png")));
		panelRodape.add(btnRegistrar);
		panelCe = new JPanel();

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(
		        new String[] { "Lucro Presumido", "Lucro Real", "Simples" }));

		JComboBox<String> cboCertificacaoIso = new JComboBox<String>();
		cboCertificacaoIso.setModel(new DefaultComboBoxModel<String>(new String[] { "ISO 9001:2008" }));
		cboCertificacaoIso.setSelectedIndex(-1);
		lblDataDeExpirao = new JLabel("Data de expiração");

		txtdataExpiracao = CalendarFactory.createDateField();
		txtdataExpiracao.setValue(null);

		lblCertificadoDoMaterial = new JLabel("Certificado do material");

		rdbtnSim = new JRadioButton("Sim");

		rdbtnNo = new JRadioButton("Não");

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
		                                                                                                        comboBox_1,
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
		                                                                                                        cboCertificacaoIso,
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
		                                .addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(ComponentPlacement.UNRELATED)
		                .addGroup(
		                        gl_panelCe
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblCertificado)
		                                .addComponent(cboCertificacaoIso, GroupLayout.PREFERRED_SIZE,
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

		textField_9 = new JTextField();
		scrollPane.setViewportView(textField_9);
		textField_9.setColumns(10);
		panelCe.setLayout(gl_panelCe);
	}

	/**
	 * 
	 */

	private void initializeContact() {
		panel = new JPanel();

		JLabel lblRazoSocial = new JLabel("Razão Social");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblCnpj = new JLabel("CNPJ");
		try {
			textField_1 = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		textField_1.setColumns(10);

		JLabel lblInscest = new JLabel("Insc.Est");
		try {
			textField_2 = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		textField_2.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");

		textField_3 = new JTextField();
		textField_3.setColumns(10);

		JLabel lblCep = new JLabel("CEP");
		try {
			txtCEP = new JFormattedTextField(new MaskFormatter("##.###-###"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtCEP.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade");

		JLabel lblBairro = new JLabel("Bairro");

		textField_6 = new JTextField();
		textField_6.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail");

		textField_7 = new JTextField();
		textField_7.setColumns(10);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		
		cboState = new JComboBox<State>();
		
		cboCity = new JComboBox<City>();

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblRazoSocial)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblTelefone)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_3))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCnpj)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblInscest)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_2))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCep)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCEP, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblCidade)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboCity, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblEstado)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboState, 0, 96, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblBairro)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblEmail)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_7)))
					.addGap(345))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRazoSocial)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCnpj)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInscest)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefone)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCep)
						.addComponent(txtCEP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCidade)
						.addComponent(lblEstado)
						.addComponent(cboState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cboCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBairro)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(133, Short.MAX_VALUE))
		);
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
			}
		};
		btnCancelar.addActionListener(buttonListener);
	}
}
