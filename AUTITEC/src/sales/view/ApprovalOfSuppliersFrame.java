package sales.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

public class ApprovalOfSuppliersFrame extends JFrame {
	public ApprovalOfSuppliersFrame() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		JTabbedPane tab = new JTabbedPane();

		getContentPane().add(tab, BorderLayout.CENTER);
		JPanel panelBotao = new JPanel();
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		panelBotao.setLayout(fl);
		getContentPane().add(panelBotao, BorderLayout.SOUTH);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(getClass().getResource("/resources/cancel.png")));
		panelBotao.add(btnCancelar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setIcon(new ImageIcon(ApprovalOfSuppliersFrame.class.getResource("/resources/ok.png")));
		panelBotao.add(btnConfirmar);
		JPanel panel = new JPanel();
		tab.addTab("Laudo", panel);
		JPanel panelJust = new JPanel();
		tab.addTab("Finalização", panelJust);
		
		JLabel lblNewLabel_1 = new JLabel("O fornecedor acima mencionado está qualificado para o fornecimento à Autitec Industrial?");
		
		JRadioButton rdbtnSimEleSt = new JRadioButton("Sim ele está habilitado!");
		
		JRadioButton rdbtnNoPelosMotivos = new JRadioButton("Não, pelos motivos na justificativa a baixo!");
		
		ButtonGroup bg7 = new ButtonGroup();
		bg7.add(rdbtnNoPelosMotivos);
		bg7.add(rdbtnSimEleSt);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder("JUSTIFICATIVA"));
		
		GroupLayout gl_panelJust = new GroupLayout(panelJust);
		gl_panelJust.setHorizontalGroup(
			gl_panelJust.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelJust.createSequentialGroup()
					.addGroup(gl_panelJust.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelJust.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panelJust.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addGroup(gl_panelJust.createSequentialGroup()
									.addComponent(rdbtnSimEleSt)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnNoPelosMotivos))))
						.addGroup(gl_panelJust.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panelJust.setVerticalGroup(
			gl_panelJust.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelJust.createSequentialGroup()
					.addGap(24)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelJust.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnSimEleSt)
						.addComponent(rdbtnNoPelosMotivos))
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
					.addGap(80))
		);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);
		
		scrollPane_1.setViewportView(textArea_1);
		panelJust.setLayout(gl_panelJust);
		JLabel lblDataDeAprovao = new JLabel("Data da Qualificação");

		JLabel lblTipoDoProcesso = new JLabel("Tipo do Processo de Qualificação");

		JLabel lblNomeDoFornecedor = new JLabel("Nome do Fornecedor");

		JLabel lblMaterial = new JLabel("Material");

		JLabel lblDescrioDoServiomaterial = new JLabel("Descrição do Serviço/Material");

		JLabel lblQualificao = new JLabel("Qualificação:");

		textField = new JTextField();
		textField.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Fornecedor Novo", "Fornecedor Tradicional",
		        "Requalificação" }));

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel lblServio = new JLabel("Serviço");

		JLabel lblAtende = new JLabel("Atende");

		JLabel lblNewLabel = new JLabel("Evidencia");

		JLabel lblObservao = new JLabel("Observação");

		JLabel lblSistemaDeQualidade = new JLabel("Sistema de Qualidade");

		JRadioButton rdbtnSim = new JRadioButton("Sim");

		JRadioButton rdbtnNo = new JRadioButton("Não");

		JRadioButton rdbtnNa = new JRadioButton("NA");

		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(rdbtnNa);
		bg1.add(rdbtnNo);
		bg1.add(rdbtnSim);

		textField_3 = new JTextField();
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setColumns(10);

		JLabel lblHistricoDoFornecimento = new JLabel("Histórico do Fornecimento");

		JRadioButton rdbtnSim_1 = new JRadioButton("Sim");

		JRadioButton rdbtnNo_1 = new JRadioButton("Não");

		JRadioButton rdbtnNa_1 = new JRadioButton("NA");

		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(rdbtnNa_1);
		bg2.add(rdbtnNo_1);
		bg2.add(rdbtnSim_1);

		textField_5 = new JTextField();
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setColumns(10);

		JLabel lblCapacitaoDoFornecedor = new JLabel("Capacitação do Fornecedor");

		JRadioButton rdbtnSim_2 = new JRadioButton("Sim");

		JRadioButton rdbtnNo_2 = new JRadioButton("Não");

		JRadioButton rdbtnNa_2 = new JRadioButton("NA");

		ButtonGroup bg3 = new ButtonGroup();
		bg3.add(rdbtnNa_2);
		bg3.add(rdbtnNo_2);
		bg3.add(rdbtnSim_2);

		textField_7 = new JTextField();
		textField_7.setColumns(10);

		textField_8 = new JTextField();
		textField_8.setColumns(10);

		JComboBox comboBox_1 = new JComboBox();

		JComboBox comboBox_2 = new JComboBox();

		JLabel lblCliente = new JLabel("Cliente");

		JComboBox comboBox_3 = new JComboBox();

		JLabel lblData = new JLabel("Data");

		textField_9 = new JTextField();
		textField_9.setColumns(10);

		JLabel lblPreo = new JLabel("Preço");

		JRadioButton rdbtnBom = new JRadioButton("Bom");

		JRadioButton rdbtnRuim = new JRadioButton("Ruim");

		JRadioButton rdbtnPssimo = new JRadioButton("Péssimo");

		ButtonGroup bg4 = new ButtonGroup();
		bg4.add(rdbtnRuim);
		bg4.add(rdbtnPssimo);
		bg4.add(rdbtnBom);

		JLabel lblQualidade = new JLabel("Qualidade");

		JRadioButton rdbtnBom_1 = new JRadioButton("Bom");

		JRadioButton rdbtnRuim_1 = new JRadioButton("Ruim");

		JRadioButton rdbtnPssimo_1 = new JRadioButton("Péssimo");

		ButtonGroup bg5 = new ButtonGroup();
		bg5.add(rdbtnBom_1);
		bg5.add(rdbtnPssimo_1);
		bg5.add(rdbtnRuim_1);
		
		JLabel lblAtendimento = new JLabel("Atendimento");
		
		JRadioButton rdbtnBom_2 = new JRadioButton("Bom");
		
		JRadioButton rdbtnRuim_2 = new JRadioButton("Ruim");
		
		JRadioButton rdbtnPssimo_2 = new JRadioButton("Péssimo");
		
		ButtonGroup bg6 = new ButtonGroup();
		bg6.add(rdbtnPssimo_2);
		bg6.add(rdbtnRuim_2);
		bg6.add(rdbtnBom_2);
		
		JScrollPane scrollPane = new JScrollPane();

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblDataDeAprovao, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblMaterial, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblServio)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox_2, 0, 244, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNomeDoFornecedor)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblTipoDoProcesso)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblQualificao, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
											.addGap(86))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblDescrioDoServiomaterial, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 453, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblAtende)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblNewLabel)
											.addGap(183)))))
							.addGap(17)
							.addComponent(lblObservao)
							.addGap(110))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblSistemaDeQualidade)
									.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
									.addComponent(rdbtnSim)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnNo)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnNa))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblHistricoDoFornecimento)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(rdbtnSim_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnNo_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnNa_1))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCapacitaoDoFornecedor)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnSim_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnNo_2)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnNa_2))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCliente)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox_3, 0, 212, Short.MAX_VALUE)))
							.addGap(49)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_7, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
										.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
										.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
									.addGap(41)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_8, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
										.addComponent(textField_6, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
										.addComponent(textField_4, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(311)
									.addComponent(lblData)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_9, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)))
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(rdbtnBom)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnRuim)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnPssimo)
							.addGap(141)
							.addComponent(rdbtnBom_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(rdbtnRuim_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnPssimo_1)
									.addGap(100)
									.addComponent(rdbtnBom_2))
								.addComponent(lblQualidade))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(rdbtnRuim_2)
									.addGap(0)
									.addComponent(rdbtnPssimo_2))
								.addComponent(lblAtendimento))
							.addGap(62))))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(68)
					.addComponent(lblPreo)
					.addContainerGap(703, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeAprovao)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoDoProcesso)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNomeDoFornecedor)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaterial)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblServio)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblDescrioDoServiomaterial)
							.addGap(26)))
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQualificao)
						.addComponent(lblAtende)
						.addComponent(lblObservao)
						.addComponent(lblNewLabel))
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSistemaDeQualidade)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnNa)
						.addComponent(rdbtnNo)
						.addComponent(rdbtnSim))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHistricoDoFornecimento)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnNa_1)
						.addComponent(rdbtnNo_1)
						.addComponent(rdbtnSim_1))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCapacitaoDoFornecedor)
						.addComponent(rdbtnSim_2)
						.addComponent(rdbtnNo_2)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnNa_2))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCliente)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblData)
						.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPreo)
						.addComponent(lblAtendimento)
						.addComponent(lblQualidade))
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnBom)
						.addComponent(rdbtnRuim)
						.addComponent(rdbtnPssimo)
						.addComponent(rdbtnBom_2)
						.addComponent(rdbtnRuim_2)
						.addComponent(rdbtnPssimo_2)
						.addComponent(rdbtnPssimo_1)
						.addComponent(rdbtnRuim_1)
						.addComponent(rdbtnBom_1))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea);
		panel.setLayout(gl_panel);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8366385217334396998L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
}
