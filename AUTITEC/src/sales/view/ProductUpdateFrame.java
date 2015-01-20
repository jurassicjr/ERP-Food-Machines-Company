package sales.view;

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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import model.Material;
import sales.controller.MaterialUpdateController;
import userInterface.components.ComboBoxAutoCompletion;
import userInterface.components.UpperTextField;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;
import database.DataBase;

public class ProductUpdateFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2859084917577254070L;

	private JFrame frame = this;

	Icon icon;

	private UpperTextField txtName;

	private JLabel lblProdutomaterial;
	private JLabel lblNome;

	private JComboBox<Material> cboProduto;

	private MaterialUpdateController controller;

	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JButton btnApagar;

	private DataBase dataBase;

	private JTextArea txtDescricao;

	private JPanel panelDescrition;

	private JPanel panelDescricao;
	private JSeparator separator;

	public ProductUpdateFrame() {
		dataBase = new DataBase();
		dataBase.connect();
		controller = new MaterialUpdateController();
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
		setBounds(100, 100, 480, 300);
		setMinimumSize(new Dimension(480, 300));
		// Logo
		Icon.setIcon(frame);
		// Determina o Layout
		getContentPane().setLayout(new BorderLayout(0, 0));
		// Criar o panel e adiciona ao frame
		JPanel principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);

		lblProdutomaterial = new JLabel("Produto/Material");

		cboProduto = new JComboBox<Material>();
		controller.fillProducts(cboProduto);
		ComboBoxAutoCompletion cboAutoCompletation = new ComboBoxAutoCompletion(cboProduto);
		cboProduto.setSelectedIndex(-1);

		lblNome = new JLabel("Nome");

		txtName = new UpperTextField();
		txtName.setColumns(10);

		panelDescricao = new JPanel();
		panelDescricao.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Descri\u00E7\u00E3o",
		        TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		separator = new JSeparator();

		// Determina o layout do panel
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_principalPanel.createSequentialGroup()
							.addComponent(lblProdutomaterial)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboProduto, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_principalPanel.createSequentialGroup()
							.addComponent(lblNome)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
						.addComponent(panelDescricao, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProdutomaterial)
						.addComponent(cboProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panelDescricao, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(40))
		);
		panelDescricao.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelDescricao.add(scrollPane, BorderLayout.CENTER);

		txtDescricao = new JTextArea();
		scrollPane.setViewportView(txtDescricao);
		principalPanel.setLayout(gl_principalPanel);

		initializeSub();
	}

	private void initializeSub() {
		JPanel bottonPanel = new JPanel();
		getContentPane().add(bottonPanel, BorderLayout.SOUTH);
		bottonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		btnApagar = new JButton("Apagar");
		btnApagar.setIcon(new ImageIcon(ProductUpdateFrame.class.getResource("/resources/clear.png")));
		bottonPanel.add(btnApagar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(ProductUpdateFrame.class.getResource("/resources/cancel.png")));
		bottonPanel.add(btnCancelar);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setIcon(new ImageIcon(ProductUpdateFrame.class.getResource("/resources/ok.png")));
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
						String sql = "UPDATE Product SET name = ?, descricao = ? WHERE id = ?";
						Material produto = (Material) cboProduto.getSelectedItem();
						insertData = new Object[] { txtName.getText(), txtDescricao.getText(), produto.getId() };
						dataBase.executeUpdate(sql, insertData);
						String title = "Atualização/Remoção";
						String message = "Ação concluida com sucesso!";
						new ShowMessage();
						ShowMessage.successMessage(frame, title, message);
						ClearFrame.clear(frame);
					} else {
						txtName.requestFocus();
					}
				} else if (e.getSource().equals(btnApagar)) {
					int i = ShowMessage.questionMessage(frame, "APAGAR", "Deseja realmente apagar o produto \""
					        + txtName.getText() + " \"");
					if (i == JOptionPane.YES_OPTION) {
						String query ="DELETE FROM supplier_product_association where product = ?";
						String sql = "DELETE FROM Product WHERE id = ?";
						Material produto = (Material) cboProduto.getSelectedItem();
						dataBase.executeUpdate(query, produto.getId());
						dataBase.executeUpdate(sql, produto.getId());
						ClearFrame.clear(frame);
						cboProduto.removeItem(produto);
						ShowMessage.successMessage(frame, "Remoção", "Produto deletado com sucesso!");
					} else {
						txtName.requestFocus();
					}
				}
			}

		};
		cboProduto.addActionListener(cboListener);
		btnCancelar.addActionListener(buttonListerners);
		btnConfirmar.addActionListener(buttonListerners);
		btnApagar.addActionListener(buttonListerners);
	}

	private void fillFields(ResultSet rs) throws SQLException {
		rs.next();
		txtName.setText(rs.getString(2));
		txtDescricao.setText(rs.getString(3));
	}
}
