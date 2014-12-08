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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.MaskFormatter;

import model.City;
import model.State;
import model.Supplier;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.SalesController;

public class RegisterSuppliersFrame extends JFrame {
	private JTextField textField_8;
	private JFrame frame = this;
	private JButton btnCancelar;
	private static final long serialVersionUID = -3611459763736774158L;
	private JTextField txtCompanyName;
	private JTextField txtCNPJ;
	private JTextField txtStateRegister;
	private JTextField txtPhone;
	private JTextField txtCEP;
	private JTextField txtBairro;
	private JTextField txtEmail;
	private DateField txtdataExpiracao;
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
	private JComboBox<String> cboCertification;
	private JLabel lblRua;
	private JTextField txtStreet;
	private JTextArea txtJustifacao;
	private JComboBox<String> cboFiscalCertification;

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

		setTxtCompanyName(new JTextField());
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

		txtBairro = new JTextField();
		txtBairro.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail");

		txtEmail = new JTextField();
		txtEmail.setColumns(10);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		
		cboState = new JComboBox<State>();
		
		cboCity = new JComboBox<City>();
		
		lblRua = new JLabel("Rua");
		
		txtStreet = new JTextField();
		txtStreet.setColumns(10);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
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
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
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
					.addContainerGap(108, Short.MAX_VALUE))
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
				else if(e.getSource().equals(btnRegistrar)) {
					try {
	                    controller.doSupplierRegister(makeSupplier());
                    } catch (SQLException e1) {
	                    e1.printStackTrace();
                    }
				}
			}
		};
		btnCancelar.addActionListener(buttonListener);
		btnRegistrar.addActionListener(buttonListener);
	}
	private Supplier makeSupplier() {
		String cnpj =  txtCNPJ.getText().replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "");
		String companyName = getTxtCompanyName().getText();
		String stateRegister = txtStateRegister.getText().replaceAll("\\.", "");
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
		supplier.setCep(txtCEP.getText().replaceAll("\\.", "").replaceAll("-", ""));
		clear();
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
	}
	public JTextField getTxtCompanyName() {
	    return txtCompanyName;
    }

	public void setTxtCompanyName(JTextField txtCompanyName) {
	    this.txtCompanyName = txtCompanyName;
    }
}
