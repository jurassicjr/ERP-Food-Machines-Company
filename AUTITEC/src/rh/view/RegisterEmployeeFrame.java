package rh.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegisterEmployeeFrame extends JFrame {
	
	private static final long serialVersionUID = -6639785855579308708L;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_4;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField txCellPhone;

	/**
	 * Create the application.
	 */
	public RegisterEmployeeFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 697, 457);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JLabel lblNome = new JLabel("Nome");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblCtps = new JLabel("CTPS");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblSrie = new JLabel("Série");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		
		JLabel lblSexo = new JLabel("Sexo");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Feminino"}));
		
		JLabel lblEstadoCivil = new JLabel("Estado Civil");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Solteiro(a)", "Casado(a)", "Divorciado(a)", "Vi\u00FAvo(a)"}));
		
		JLabel lblNacionalidade = new JLabel("Nacionalidade");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JLabel lblLocalDeNascimento = new JLabel("Local de Nascimento");
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		JLabel lblEscolaridade = new JLabel("Escolaridade");
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Ensino Fundamental Incompleto", "Ensino Fundamental Completo", "Ensino Médio Incompleto", "Ensino Médio Completo", "Ensino Superior Incompleto", "Ensino Superior Completo", "Pós-Graduação"}));
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBorder(new LineBorder(Color.red));
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblRegistroGeralrg = new JLabel("Registro Geral (RG)");
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		
		JLabel lblTtuloDeEleitor = new JLabel("Título de Eleitor");
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		
		JLabel lblCnh = new JLabel("CNH");
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		
		JLabel lblCategoriaCnh = new JLabel("Categoria CNH");
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C", "D", "E"}));
		
		JLabel lblCarteiraDeReservista = new JLabel("Carteira Reservista");
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria");
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"1ª Categoria", "2ª Categoria"}));
		
		JLabel lblEndereo = new JLabel("Endereço");
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro");
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP");
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		
		JLabel lblCidade = new JLabel("Estado");
		
		JComboBox comboBox_5 = new JComboBox();
		
		JLabel lblNewLabel_2 = new JLabel("Cidade");
		
		JComboBox comboBox_6 = new JComboBox();
		
		JLabel lblTelefone = new JLabel("Telefone");
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular");
		
		txCellPhone = new JTextField();
		txCellPhone.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNacionalidade)
						.addComponent(lblEscolaridade)
						.addComponent(lblTtuloDeEleitor)
						.addComponent(lblCarteiraDeReservista)
						.addComponent(lblEndereo)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNome)
								.addComponent(lblDataDeNascimento)
								.addComponent(lblRegistroGeralrg)
								.addComponent(lblCidade)
								.addComponent(lblTelefone))
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_9, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
										.addComponent(textField_7, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
										.addComponent(textField_11, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(8)
											.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblCpf)
												.addComponent(lblCnh))
											.addGap(18)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(textField_10, 110, 110, 110)
												.addComponent(textField_8, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
											.addGap(18)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(lblCtps)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
													.addComponent(lblSrie)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(lblCategoriaCnh)
													.addGap(18)
													.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(18)
											.addComponent(lblCategoria)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
											.addGap(26)
											.addComponent(lblLocalDeNascimento)
											.addGap(18)
											.addComponent(textField_6, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
											.addGap(27)
											.addComponent(lblSexo)
											.addGap(18)
											.addComponent(comboBox, 0, 71, Short.MAX_VALUE)
											.addGap(36)
											.addComponent(lblEstadoCivil)
											.addGap(23)
											.addComponent(comboBox_1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGap(0))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(textField, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
											.addGap(18)
											.addComponent(lblNewLabel)
											.addGap(18)
											.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
									.addGap(18)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(textField_12, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblBairro)
									.addGap(15)
									.addComponent(textField_13, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
									.addGap(10)
									.addComponent(lblCep)
									.addGap(18)
									.addComponent(textField_14))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox_5, 0, 110, Short.MAX_VALUE)
										.addComponent(textField_15, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_2)
										.addComponent(lblCelular))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(txCellPhone, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
											.addGap(69))
										.addComponent(comboBox_6, 0, 174, Short.MAX_VALUE))
									.addGap(192)))))
					.addGap(20))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDataDeNascimento)
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSexo)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEstadoCivil)
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNacionalidade)
								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLocalDeNascimento)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEscolaridade)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRegistroGeralrg)
						.addComponent(lblCtps)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCpf)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSrie))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTtuloDeEleitor)
						.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCnh)
						.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategoriaCnh)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCarteiraDeReservista)
						.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategoria)
						.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndereo)
						.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCep)
						.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBairro))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCidade)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_2)
							.addComponent(comboBox_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTelefone)
						.addComponent(textField_15, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(txCellPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCelular)))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
}
