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
import javax.swing.JComboBox;
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
import model.MaterialModel;
import model.MaterialType;
import model.MeasureUnit;
import sales.controller.SalesController;
import userInterface.components.UpperTextField;
import util.ClearFrame;
import util.Icon;
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
	private JLabel lblMaterialType;
	private JLabel lblModel;
	private JComboBox<MaterialType> cboMaterialType;
	private JButton btnAddMaterialType;
	private JComboBox<MaterialModel> cboMaterialModel;
	private JButton btnAddMaterialModel;
	private JLabel lblMeasureUnit;

	private JComboBox<MeasureUnit> cboMeasureUnit;
	
	private JLabel lblwidth;
	private JTextField txtWidth;
	private JLabel lblLength;
	private JTextField txtLength;
	private JLabel lblHeigth;
	private JTextField txtHeigth;

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
		Icon.setIcon(this);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 450, 497);
		setPreferredSize(new Dimension(450, 497));
		setMinimumSize(new Dimension(450, 497));
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
		
		lblMaterialType = new JLabel("Tipo do material");
		 
		lblModel = new JLabel("Modelo");
		
		cboMaterialType = new JComboBox<MaterialType>();
		
		btnAddMaterialType = new JButton("Adicionar");
		btnAddMaterialType.setIcon(new ImageIcon(RegisterOfMaterialFrame.class.getResource("/resources/plus.png")));
		controller.fillMaterialType(cboMaterialType);
		
		cboMaterialModel = new JComboBox<MaterialModel>();
		
		btnAddMaterialModel = new JButton("Adicionar");
		btnAddMaterialModel.setIcon(new ImageIcon(RegisterOfMaterialFrame.class.getResource("/resources/plus.png")));
		controller.fillMaterialModels(cboMaterialModel);
		
		lblMeasureUnit = new JLabel("Unidade de Medida");
		
		cboMeasureUnit = new JComboBox<MeasureUnit>();
		controller.fillMeasureUnit(cboMeasureUnit);
		
		lblwidth = new JLabel("Largura");
		
		txtWidth = new JTextField();
		txtWidth.setColumns(10);
		
		lblLength = new JLabel("Comprimento");
		lblLength.setEnabled(false);
		
		txtLength = new JTextField();
		txtLength.setEnabled(false);
		txtLength.setColumns(10);
		
		lblHeigth = new JLabel("Altura");
		lblHeigth.setEnabled(false);
		
		txtHeigth = new JTextField();
		txtHeigth.setEnabled(false);
		txtHeigth.setColumns(10);
		
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE))
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblNCM)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtNCM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblInternalCode)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtInternalCode, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblMaterialType)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboMaterialType, 0, 219, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnAddMaterialType))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblModel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboMaterialModel, 0, 261, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnAddMaterialModel))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblMeasureUnit)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboMeasureUnit, 0, 319, Short.MAX_VALUE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblwidth)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblLength)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblHeigth)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtHeigth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNCM)
						.addComponent(txtNCM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInternalCode)
						.addComponent(txtInternalCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaterialType)
						.addComponent(cboMaterialType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddMaterialType))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblModel)
						.addComponent(cboMaterialModel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddMaterialModel))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMeasureUnit)
						.addComponent(cboMeasureUnit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblwidth)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLength)
						.addComponent(txtLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHeigth)
						.addComponent(txtHeigth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE))
		);
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
						else if(cboMaterialType.getSelectedIndex() == -1)ShowMessage.errorMessage(frame, "Erro", "Selecione o tipo do material");
						else if(txtWidth.getText().isEmpty())ShowMessage.errorMessage(frame, "Erro", "Insira um valor corresponde ao campo largura");
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
				else if(e.getSource().equals(btnAddMaterialType))new RegisterOfMaterialTypeView(frame).setVisible(true);
				else if(e.getSource().equals(btnAddMaterialModel))new RegisterOfMaterialModelView(frame).setVisible(true);
			}
		};
		btnConfirmar.addActionListener(btnAction);
		btnCancelar.addActionListener(btnAction);
		btnAddMaterialType.addActionListener(btnAction);
		btnAddMaterialModel.addActionListener(btnAction);
		
		
		ActionListener cboListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(cboMeasureUnit))allowBox();
			}
		};
		cboMeasureUnit.addActionListener(cboListener);
	}
	
	private void allowBox() {
		int i = cboMeasureUnit.getSelectedIndex();
		if(i == -1)return;
		else if(i == 1 || i == 4 || i == 7 || i == 14) {
			txtLength.setEnabled(true);
			txtHeigth.setEnabled(false);
			lblHeigth.setEnabled(false);
			lblLength.setEnabled(true);
		}else if(i == 2 || i == 5 || i == 8 || i == 15){
			txtLength.setEnabled(true);
			txtHeigth.setEnabled(true);
			lblHeigth.setEnabled(true);
			lblLength.setEnabled(true);
		}else {
			txtLength.setEnabled(false);
			txtHeigth.setEnabled(false);
			lblHeigth.setEnabled(false);
			lblLength.setEnabled(false);
		}
		
	}
	
	
	private Material createMaterial() {
		Material material = new Material();
		material.setName(txtName.getText());
		material.setDescrition(txtDescricao.getText());
		material.setNCM(txtNCM.getText());
		material.setInternalCode(txtInternalCode.getText());
		material.setAmmount(0);
		MaterialModel mm = (MaterialModel) cboMaterialModel.getSelectedItem();
		material.setModel(mm.getId());
		MaterialType mt = (MaterialType) cboMaterialType.getSelectedItem();
		material.setMaterialType(mt.getId());
		MeasureUnit mu = (MeasureUnit) cboMeasureUnit.getSelectedItem();
		material.setMeasureUnit(mu.getId());
		material.setWidth(Double.parseDouble(txtWidth.getText()));
		if(!txtLength.getText().isEmpty())material.setLength(Double.parseDouble(txtLength.getText()));
		if(!txtHeigth.getText().isEmpty())material.setHeigth(Double.parseDouble(txtHeigth.getText()));
		return material;
	}
	public void refresh() {
		cboMaterialType.removeAllItems();
		controller.fillMaterialType(cboMaterialType);
		cboMaterialModel.removeAllItems();
		controller.fillMaterialModels(cboMaterialModel);
	}
}
