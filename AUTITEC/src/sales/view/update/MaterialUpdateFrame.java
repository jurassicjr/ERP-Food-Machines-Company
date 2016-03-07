package sales.view.update;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import sales.controller.MaterialUpdateController;
import sales.controller.SalesController;
import sales.view.register.RegisterOfMaterialFrame;
import userInterface.components.UpperTextField;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;
import database.DataBase;

public class MaterialUpdateFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2859084917577254070L;

	private JPanel principalPanel;
	private JPanel bottonPanel;

	private UpperTextField txtName;

	private JButton btnConfirmar;
	private JButton btnCancelar;

	private SalesController controller;
	private MaterialUpdateFrame frame = this;

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

	private DataBase dataBase;
	private JLabel lblMaterial;
	private JComboBox<Material> cboProduto;

	private JButton btnLimpar;

	private MaterialUpdateController materialUpdateController;


	public MaterialUpdateFrame() {
		dataBase = new DataBase();
		dataBase.connect();
		controller = new MaterialUpdateController();
		materialUpdateController = new MaterialUpdateController();
		initialize();
		setListener();
	}

	/**
	 * Inicializa os elemento gráficos da aplicação
	 */


	private void initialize() {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		// Adiciona o titulo
		this.setTitle("Atualização de Materiais");
		setBounds(100, 100, 480, 539);
		setMinimumSize(new Dimension(480, 300));
		// Logo
		Icon.setIcon(frame);
		// Determina o Layout
		getContentPane().setLayout(new BorderLayout(0, 0));
		// Criar o panel e adiciona ao frame
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
		
		lblMaterial = new JLabel("Material");
		
		cboProduto = new JComboBox<Material>();
		materialUpdateController.fillMaterials(cboProduto);
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblMaterial)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboProduto, 0, 402, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblNCM)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtNCM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblInternalCode)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtInternalCode, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblMaterialType)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboMaterialType, 0, 249, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnAddMaterialType))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblModel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboMaterialModel, 0, 291, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnAddMaterialModel))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblMeasureUnit)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboMeasureUnit, 0, 349, Short.MAX_VALUE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
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
			gl_principalPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaterial)
						.addComponent(cboProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
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
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setIcon(new ImageIcon(MaterialUpdateFrame.class.getResource("/resources/ClearFrame.png")));
		bottonPanel.add(btnLimpar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(RegisterOfMaterialFrame.class.getResource("/resources/cancel.png")));
		bottonPanel.add(btnCancelar);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setIcon(new ImageIcon(RegisterOfMaterialFrame.class.getResource("/resources/ok.png")));
		bottonPanel.add(btnConfirmar);
	}

	private void setListener() {

		ClearFrame.clear(frame);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeFrame(frame);
			}
		});
		ActionListener cboListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cboProduto.getSelectedIndex() != -1) {
					String sql = "SELECT *FROM Product WHERE name = ?";
					try {
						Material produto = (Material) cboProduto.getSelectedItem();
						fillFields(dataBase.executeQuery(sql, produto.getName()));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		};
		ActionListener buttonListerners = new ActionListener() {
			private Object[] insertData;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnCancelar)) {
					controller.closeFrame(frame);
				} else if (e.getSource().equals(btnConfirmar)) {
					int i = ShowMessage.questionMessage(frame, "Atualização", "Deseja realmente atualizar o produto \""
					        + txtName.getText() + "\"");
					if (i == JOptionPane.YES_OPTION) {
						int x, y, z;
						if(txtWidth.getText().isEmpty())x = 0;
						else x = Integer.parseInt(txtWidth.getText());
						if(txtLength.getText().isEmpty())y = 0;
						else y = Integer.parseInt(txtLength.getText());
						if(txtHeigth.getText().isEmpty())z = 0;
						else z = Integer.parseInt(txtHeigth.getText());
						String sql = "UPDATE Product SET name = ?, descricao = ?, internal_code = ?, ncm = ?, model = ?, measure_unit = ?, material_type = ?, x = ?, y = ?, z = ? WHERE id = ?";
						Material produto = (Material) cboProduto.getSelectedItem();
						insertData = new Object[] { txtName.getText(), txtDescricao.getText(), txtInternalCode.getText(), txtNCM.getText() ,produto.getId(), cboMaterialModel.getSelectedIndex() +1,
						cboMeasureUnit.getSelectedIndex() + 1, cboMaterialType.getSelectedIndex() + 1, x, y, z};
						dataBase.executeUpdate(sql, insertData);
						String title = "Atualização/Remoção";
						String message = "Ação concluida com sucesso!";
						new ShowMessage();
						ShowMessage.successMessage(frame, title, message);
						ClearFrame.clear(frame);
					} else {
						txtName.requestFocus();
					}
				} else if (e.getSource().equals(btnLimpar)) {
					int i = ShowMessage.questionMessage(frame, "APAGAR", "Deseja realmente apagar o material \""
					        + txtName.getText() + " \"");
					if (i == JOptionPane.YES_OPTION) {
						String invetorySql = "DELETE FROM inventory WHERE material = ?";
						String query ="DELETE FROM supplier_product_association where product = ?";
						String materialRelation = "DELETE FROM material_relationship WHERE material = ?";
						String sql = "DELETE FROM Product WHERE id = ?";
						Material produto = (Material) cboProduto.getSelectedItem();
						dataBase.executeUpdate(materialRelation, produto.getId());
						dataBase.executeUpdate(invetorySql, produto.getId());
						dataBase.executeUpdate(query, produto.getId());
						dataBase.executeUpdate(sql, produto.getId());
						ClearFrame.clear(frame);
						cboProduto.removeItem(produto);
						ShowMessage.successMessage(frame, "Remoção", "Produto deletado com sucesso!");
					} else if(e.getSource().equals(cboMeasureUnit)){
						allowBox();
					}else {
						txtName.requestFocus();
					}
				}
			}

		};
		cboProduto.addActionListener(cboListener);
		btnCancelar.addActionListener(buttonListerners);
		btnConfirmar.addActionListener(buttonListerners);
		btnLimpar.addActionListener(buttonListerners);
	}
    public void setSelectedMaterial(Material material){
    		cboProduto.setSelectedIndex(material.getId() - 1);
    	 	fill(material);
    }
    public void fill(Material material){
    	txtName.setText(material.getName());
    	txtDescricao.setText(material.getDescrition());
    	txtInternalCode.setText(material.getInternalCode());
    	txtHeigth.setText(String.valueOf(material.getHeigth()));
    	txtLength.setText(String.valueOf(material.getLength()));
    	txtWidth.setText(String.valueOf(material.getWidth()));
    	cboMaterialModel.setSelectedIndex(material.getModel() - 1);
    	cboMaterialType.setSelectedIndex(material.getMaterialType() - 1);
    	cboMeasureUnit.setSelectedIndex(material.getMeasureUnit() - 1);
    	txtNCM.setText(material.getNCM());
    	txtName.grabFocus();
    }
    
	private void fillFields(ResultSet rs) throws SQLException {
		rs.next();
		txtName.setText(rs.getString("name"));
		txtDescricao.setText(rs.getString("descricao"));
		txtInternalCode.setText(rs.getString("internal_code"));
		txtNCM.setText(rs.getString("ncm"));
		txtHeigth.setText(String.valueOf(rs.getInt("z")));
		txtLength.setText(String.valueOf(rs.getInt("y")));
		txtWidth.setText(String.valueOf(rs.getInt("x")));
		cboMaterialModel.setSelectedIndex(rs.getInt("model") -1);
		cboMaterialType.setSelectedIndex(rs.getInt("material_type") -1);
		cboMeasureUnit.setSelectedIndex(rs.getInt("measure_unit") -1);
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
}
