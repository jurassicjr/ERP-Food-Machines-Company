package sales.view;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ApprovalOfSuppliersFrame extends JFrame {
	public ApprovalOfSuppliersFrame() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblDataDeAprovao = new JLabel("Data da Qualificação");
		
		JLabel lblTipoDoProcesso = new JLabel("Tipo do Processo de Qualificação");
		
		JLabel lblNomeDoFornecedor = new JLabel("Nome do Fornecedor");
		
		JLabel lblMaterial = new JLabel("Material");
		
		JLabel lblDescrioDoServiomaterial = new JLabel("Descrição do Serviço/Material");
		
		JLabel lblQualificao = new JLabel("Qualificação:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Fornecedor Novo", "Fornecedor Tradicional", "Requalificação"}));
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblServio = new JLabel("Serviço");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblAtende = new JLabel("Atende");
		
		JLabel lblNewLabel = new JLabel("Evidencia");
		
		JLabel lblObservao = new JLabel("Observação");
		
		JLabel lblSistemaDeQualidade = new JLabel("Sistema de Qualidade");
		
		JRadioButton rdbtnSim = new JRadioButton("Sim");
		
		JRadioButton rdbtnNo = new JRadioButton("Não");
		
		JRadioButton rdbtnNa = new JRadioButton("NA");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblHistricoDoFornecimento = new JLabel("Histórico do Fornecimento");
		
		JRadioButton rdbtnSim_1 = new JRadioButton("Sim");
		
		JRadioButton rdbtnNo_1 = new JRadioButton("Não");
		
		JRadioButton rdbtnNa_1 = new JRadioButton("NA");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		JLabel lblCapacitaoDoFornecedor = new JLabel("Capacitação do Fornecedor");
		
		JRadioButton rdbtnSim_2 = new JRadioButton("Sim");
		
		JRadioButton rdbtnNo_2 = new JRadioButton("Não");
		
		JRadioButton rdbtnNa_2 = new JRadioButton("NA");
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		
		JComboBox comboBox_2 = new JComboBox();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblDataDeAprovao, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
							.addGap(474))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblMaterial, GroupLayout.PREFERRED_SIZE, 51, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblServio)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNomeDoFornecedor, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblTipoDoProcesso, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox, 0, 186, Short.MAX_VALUE)))
							.addGap(240))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblDescrioDoServiomaterial, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblQualificao, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
									.addGap(83)))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblAtende)
									.addGap(138)
									.addComponent(lblNewLabel)
									.addGap(134)
									.addComponent(lblObservao))
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE))
							.addGap(125))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblSistemaDeQualidade)
									.addGap(18)
									.addComponent(rdbtnSim)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnNo)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnNa))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblHistricoDoFornecimento)
									.addPreferredGap(ComponentPlacement.RELATED)
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
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnNa_2)))
							.addGap(23)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_7, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
								.addComponent(textField_5, 156, 156, 156)
								.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
							.addGap(41)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_8, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
								.addComponent(textField_6, 138, 138, 138)
								.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
							.addGap(75))))
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
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNomeDoFornecedor)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaterial)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblServio)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescrioDoServiomaterial)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQualificao)
						.addComponent(lblAtende)
						.addComponent(lblNewLabel)
						.addComponent(lblObservao))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSistemaDeQualidade)
						.addComponent(rdbtnSim)
						.addComponent(rdbtnNo)
						.addComponent(rdbtnNa)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHistricoDoFornecimento)
						.addComponent(rdbtnSim_1)
						.addComponent(rdbtnNo_1)
						.addComponent(rdbtnNa_1)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCapacitaoDoFornecedor)
						.addComponent(rdbtnSim_2)
						.addComponent(rdbtnNo_2)
						.addComponent(rdbtnNa_2)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}

	/**
	 * 
	 */
    private static final long serialVersionUID = -8366385217334396998L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
}
