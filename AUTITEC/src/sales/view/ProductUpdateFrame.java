package sales.view;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.Produto;
import sales.controller.SalesController;
import userInterface.components.ComboBoxAutoCompletion;
import util.ClearFrame;
import database.DataBase;

public class ProductUpdateFrame extends JFrame {
	private JTextField txtName;
	private JLabel lblProdutomaterial;
	private JComboBox<Produto> cboProduto;
	private JLabel lblNome;
	private JLabel lblDescrio;
	SalesController controller;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JButton btnApagar;
	private JFrame frame = this;
	private DataBase dataBase;
	private JTextArea txtDescricao;
	private ClearFrame faxineira;

	public ProductUpdateFrame() {
		dataBase = new DataBase();
		dataBase.connect();
		controller = new SalesController();
		initialize();
		setListener();
	}

	/**
	 * Inicializa os elemento gráficos da aplicação
	 */

	private void initialize() {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		// Adiciona o titulo
		this.setTitle("Atualização de Produtos/Materiais");
		// Determina o Layout
		getContentPane().setLayout(new BorderLayout(0, 0));
		// Criar o panel e adiciona ao frame
		JPanel principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);

		lblProdutomaterial = new JLabel("Produto/Material");

		cboProduto = new JComboBox<Produto>();
		controller.fillProducts(cboProduto);
		ComboBoxAutoCompletion cboAutoCompletation = new ComboBoxAutoCompletion(cboProduto);
		cboProduto.setSelectedIndex(-1);

		lblNome = new JLabel("Nome");

		txtName = new JTextField();
		txtName.setColumns(10);

		lblDescrio = new JLabel("Descrição");

		JScrollPane scrollPane = new JScrollPane();

		// Determina o layout do panel
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_principalPanel
		                .createSequentialGroup()
		                .addContainerGap()
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.LEADING)
		                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
		                                .addGroup(
		                                        gl_principalPanel
		                                                .createSequentialGroup()
		                                                .addComponent(lblProdutomaterial)
		                                                .addPreferredGap(ComponentPlacement.RELATED)
		                                                .addComponent(cboProduto, GroupLayout.PREFERRED_SIZE, 173,
		                                                        GroupLayout.PREFERRED_SIZE))
		                                .addGroup(
		                                        gl_principalPanel
		                                                .createSequentialGroup()
		                                                .addComponent(lblNome)
		                                                .addPreferredGap(ComponentPlacement.RELATED)
		                                                .addComponent(txtName, GroupLayout.DEFAULT_SIZE, 383,
		                                                        Short.MAX_VALUE)).addComponent(lblDescrio))
		                .addContainerGap()));
		gl_principalPanel.setVerticalGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_principalPanel
		                .createSequentialGroup()
		                .addContainerGap()
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblProdutomaterial)
		                                .addComponent(cboProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(ComponentPlacement.UNRELATED)
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblNome)
		                                .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblDescrio)
		                .addPreferredGap(ComponentPlacement.RELATED)
		                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE).addContainerGap()));

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
		btnApagar.setIcon(new ImageIcon(ProductUpdateFrame.class.getResource("/resources/1419366170_17-16.png")));
		bottonPanel.add(btnApagar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(ProductUpdateFrame.class.getResource("/resources/cancel.png")));
		bottonPanel.add(btnCancelar);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setIcon(new ImageIcon(ProductUpdateFrame.class.getResource("/resources/ok.png")));
		bottonPanel.add(btnConfirmar);
	}

	private void setListener() {
		faxineira = new ClearFrame(frame);
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
						Produto produto = (Produto) cboProduto.getSelectedItem();
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
					String sql = "UPDATE Product SET name = ?, descricao = ? WHERE id = ?";
					Produto produto = (Produto) cboProduto.getSelectedItem();
					insertData = new Object[] { txtName.getText(), txtDescricao.getText(), produto.getId() };
					dataBase.executeUpdate(sql, insertData);
					faxineira.clear();
				} else if (e.getSource().equals(btnApagar)) {
					String sql = "DELETE FROM Product WHERE id = ?";
					Produto produto = (Produto) cboProduto.getSelectedItem();
					dataBase.executeUpdate(sql, produto.getId());
					faxineira.clear();
					cboProduto.removeItem(produto);
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