package sales.view.register;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.Material;
import sales.controller.SalesController;
import userInterface.components.UpperTextField;
import util.ClearFrame;
import util.ShowMessage;

public class RegisterOfMaterialFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 532262099381038089L;

	private JPanel principalPanel;
	private JPanel bottonPanel;

	private UpperTextField txtName;

	private JButton btnConfirmar;
	private JButton btnCancelar;

	private SalesController controller;
	private RegisterOfMaterialFrame frame;

	private JTextArea txtDescricao;
	private JTextField txtNCM;
	private JTextField txtInternalCode;

	private JLabel lblName;

	private JPanel panel;

	private JLabel lblInternalCode;

	private JLabel lblNCM;

	public RegisterOfMaterialFrame() {
		frame = this;
		controller = new SalesController();
		initializePrincipal();
		setListeners();
	}

	/**
	 * Inicializa os elemento gráficos da aplicação
	 */

	private void initializePrincipal() {
		this.setTitle("Cadastro de Produto");
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 450, 338);
		setPreferredSize(new Dimension(450, 338));
		setMinimumSize(new Dimension(450, 338));
		principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		lblName = new JLabel("Nome");

		txtName = new UpperTextField();
		txtName.setColumns(10);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Descri\u00E7\u00E3o",
		        TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JSeparator separator = new JSeparator();

		lblNCM = new JLabel("Código NCM");

		txtNCM = new JTextField();
		txtNCM.setColumns(10);

		lblInternalCode = new JLabel("Código interno");

		txtInternalCode = new JTextField();
		txtInternalCode.setColumns(10);
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING).addGroup(
		        gl_principalPanel
		                .createSequentialGroup()
		                .addContainerGap()
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.TRAILING)
		                                .addGroup(
		                                        gl_principalPanel
		                                                .createSequentialGroup()
		                                                .addComponent(lblName)
		                                                .addPreferredGap(ComponentPlacement.RELATED)
		                                                .addComponent(txtName, GroupLayout.DEFAULT_SIZE, 383,
		                                                        Short.MAX_VALUE))
		                                .addComponent(separator, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
		                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
		                                .addGroup(
		                                        gl_principalPanel
		                                                .createSequentialGroup()
		                                                .addComponent(lblNCM)
		                                                .addPreferredGap(ComponentPlacement.RELATED)
		                                                .addComponent(txtNCM, GroupLayout.PREFERRED_SIZE,
		                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                                .addGap(18)
		                                                .addComponent(lblInternalCode)
		                                                .addPreferredGap(ComponentPlacement.RELATED)
		                                                .addComponent(txtInternalCode, GroupLayout.PREFERRED_SIZE, 174,
		                                                        GroupLayout.PREFERRED_SIZE))).addContainerGap()));
		gl_principalPanel.setVerticalGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_principalPanel
		                .createSequentialGroup()
		                .addContainerGap()
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblName)
		                                .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE))
		                .addGap(18)
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblNCM)
		                                .addComponent(txtNCM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE)
		                                .addComponent(lblInternalCode)
		                                .addComponent(txtInternalCode, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
		                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(ComponentPlacement.RELATED)
		                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)));
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		txtDescricao = new JTextArea();
		scrollPane.setViewportView(txtDescricao);
		principalPanel.setLayout(gl_principalPanel);
		initializeBotton();
	}

	/**
	 * Inicializa o Jpanel inferior com os componentes de confirmar e sair.
	 */

	private void initializeBotton() {
		bottonPanel = new JPanel();
		this.getContentPane().add(bottonPanel, BorderLayout.SOUTH);
		bottonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(RegisterOfMaterialFrame.class.getResource("/resources/cancel.png")));
		bottonPanel.add(btnCancelar);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setIcon(new ImageIcon(RegisterOfMaterialFrame.class.getResource("/resources/ok.png")));
		bottonPanel.add(btnConfirmar);
	}

	/**
	 * Adiciona os listeners aos componentes da classe.
	 */

	private void setListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeFrame(frame);
			}
		});
		ActionListener btnAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnCancelar))
					controller.closeFrame(frame);
				else if (e.getSource().equals(btnConfirmar)) {
					int i = ShowMessage.questionMessage(frame, "CADASTRO", "Deseja realmente cadastrar esse produto ?");
					if (i == JOptionPane.YES_OPTION) {
							Material material = null;
						if(txtName.getText().isEmpty()) ShowMessage.errorMessage(frame, "Erro", "Insira o nome do Material");
						else if(txtDescricao.getText().isEmpty()) ShowMessage.errorMessage(frame, "Erro", "Insira a descrição do material");
						else if(txtInternalCode.getText().isEmpty())ShowMessage.errorMessage(frame, "Erro", "Insira o código interno do material");
						else if(txtNCM.getText().isEmpty())ShowMessage.errorMessage(frame, "Erro", "Insira o  código NCM do produto");
						else {
							material = createMaterial();
						}
						try {
							controller.doMaterialRegister(material);
							ClearFrame.clear(frame);
						} catch (Exception erro) {
							erro.printStackTrace();
						}
					}else {
						txtName.requestFocus();
					}
				}
			}
		};
		btnConfirmar.addActionListener(btnAction);
		btnCancelar.addActionListener(btnAction);
	}
	
	private Material createMaterial() {
		Material material = new Material();
		material.setName(txtName.getText());
		material.setDescrition(txtDescricao.getText());
		material.setNCM(txtNCM.getText());
		material.setInternalCode(txtInternalCode.getText());
		return material;
	}
}
