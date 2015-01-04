package sales.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import model.Product;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.ProductUpdateController;
import sales.controller.SalesController;
import userInterface.components.ComboBoxAutoCompletion;
import userInterface.components.UpperTextField;

public class SalesRequisitionFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7516160610003247856L;

	private JFrame frame = this;

	private JPanel panelRequisition;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;

	private JTextField txtResponsable;

	private DateField txtDate;

	private JLabel lblNome;
	private JLabel lblNDoPedido;
	private JLabel label;
	private JLabel lblSetro;
	private JLabel lblResponsvel;
	private JLabel lblData;
	private JLabel lblInserirProduto;
	private JLabel lblQuantidade;

	private JTextField txtQuantidade;

	private JComboBox<String> cboSetor;
	private JComboBox<Product> cboProduto;
	private JComboBox<String> cboPrioridade;
	private JComboBox<String> cboName;
	private ComboBoxAutoCompletion cbac;

	private JButton btnInserir;
	private JButton btnCancelar;
	private JButton btnAdicionarAPedidos;

	private JTable table;
	private JTable table_1;

	private SalesController controller;

	private ProductUpdateController controllerProduct;


	public SalesRequisitionFrame() {
		controllerProduct = new ProductUpdateController();
		controller = new SalesController();
		initialize();
		setListeners();

	}
	
	/**
	 * Inicializa os elemento gráficos da aplicação
	 */

	private void initialize() {
		this.setName("Requisição de compras");
		getContentPane().setLayout(new BorderLayout(0, 0));
		this.setTitle("Requisição de Compras");
		panelRequisition = new JPanel();
		getContentPane().add(panelRequisition, BorderLayout.CENTER);

		lblNome = new JLabel("Nome");

		cboName = new JComboBox<String>();

		JLabel lblPrioridade = new JLabel("Prioridade");

		cboPrioridade = new JComboBox<String>();
		cboPrioridade.addItem("Urgente");
		cboPrioridade.addItem("Alta");
		cboPrioridade.addItem("Média");
		cboPrioridade.addItem("Baixa");

		lblNDoPedido = new JLabel("Nº do Pedido");

		label = new JLabel("");
		Border border = BorderFactory.createLineBorder(Color.BLUE);
		label.setBorder(border);

		lblSetro = new JLabel("Setor");

		cboSetor = new JComboBox<String>();
		cboSetor.addItem("Produção/Serviço");
		cboSetor.addItem("Vendas");
		cboSetor.addItem("Financeiro");
		cboSetor.addItem("RH");
		cboSetor.addItem("Diretoria/Gerencia");

		lblResponsvel = new JLabel("Responsável");

		txtResponsable = new UpperTextField();
		txtResponsable.setColumns(10);

		lblData = new JLabel("Data");

		txtDate = CalendarFactory.createDateField();
		txtDate.setValue(null);

		lblInserirProduto = new JLabel("Inserir Produto:");

		cboProduto = new JComboBox<Product>();
		controllerProduct.fillProducts(cboProduto);
		cboProduto.setSelectedIndex(-1);
		cbac = new ComboBoxAutoCompletion(cboProduto);
				
		btnInserir = new JButton("Inserir");

		scrollPane = new JScrollPane();

		btnAdicionarAPedidos = new JButton("+ P.D.C");

		scrollPane_1 = new JScrollPane();

		lblQuantidade = new JLabel("Quantidade");

		txtQuantidade = new JTextField();
		txtQuantidade.setColumns(10);
		
		GroupLayout gl_panel = new GroupLayout(panelRequisition);
		gl_panel.setHorizontalGroup(gl_panel
		        .createParallelGroup(Alignment.LEADING)
		        .addGroup(
		                gl_panel.createSequentialGroup()
		                        .addContainerGap()
		                        .addGroup(
		                                gl_panel.createParallelGroup(Alignment.TRAILING, false)
		                                        .addGroup(
		                                                gl_panel.createSequentialGroup()
		                                                        .addGroup(
		                                                                gl_panel.createParallelGroup(Alignment.LEADING,
		                                                                        false)
		                                                                        .addGroup(
		                                                                                gl_panel.createSequentialGroup()
		                                                                                        .addComponent(
		                                                                                                lblInserirProduto)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.UNRELATED)
		                                                                                        .addComponent(
		                                                                                                cboProduto,
		                                                                                                0,
		                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                Short.MAX_VALUE))
		                                                                        .addGroup(
		                                                                                gl_panel.createSequentialGroup()
		                                                                                        .addComponent(lblNome)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.RELATED)
		                                                                                        .addComponent(
		                                                                                                cboName,
		                                                                                                GroupLayout.PREFERRED_SIZE,
		                                                                                                225,
		                                                                                                GroupLayout.PREFERRED_SIZE))
		                                                                        .addGroup(
		                                                                                gl_panel.createSequentialGroup()
		                                                                                        .addComponent(lblSetro)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.RELATED)
		                                                                                        .addComponent(
		                                                                                                cboSetor,
		                                                                                                0,
		                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                Short.MAX_VALUE)))
		                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                        .addGroup(
		                                                                gl_panel.createParallelGroup(Alignment.TRAILING)
		                                                                        .addGroup(
		                                                                                gl_panel.createSequentialGroup()
		                                                                                        .addGroup(
		                                                                                                gl_panel.createParallelGroup(
		                                                                                                        Alignment.LEADING)
		                                                                                                        .addGroup(
		                                                                                                                gl_panel.createSequentialGroup()
		                                                                                                                        .addComponent(
		                                                                                                                                lblPrioridade)
		                                                                                                                        .addPreferredGap(
		                                                                                                                                ComponentPlacement.RELATED)
		                                                                                                                        .addComponent(
		                                                                                                                                cboPrioridade,
		                                                                                                                                GroupLayout.PREFERRED_SIZE,
		                                                                                                                                133,
		                                                                                                                                GroupLayout.PREFERRED_SIZE)
		                                                                                                                        .addPreferredGap(
		                                                                                                                                ComponentPlacement.UNRELATED)
		                                                                                                                        .addComponent(
		                                                                                                                                lblNDoPedido))
		                                                                                                        .addGroup(
		                                                                                                                gl_panel.createSequentialGroup()
		                                                                                                                        .addComponent(
		                                                                                                                                lblResponsvel)
		                                                                                                                        .addPreferredGap(
		                                                                                                                                ComponentPlacement.RELATED)
		                                                                                                                        .addComponent(
		                                                                                                                                txtResponsable,
		                                                                                                                                GroupLayout.PREFERRED_SIZE,
		                                                                                                                                159,
		                                                                                                                                GroupLayout.PREFERRED_SIZE)
		                                                                                                                        .addPreferredGap(
		                                                                                                                                ComponentPlacement.UNRELATED)
		                                                                                                                        .addComponent(
		                                                                                                                                lblData)))
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.RELATED)
		                                                                                        .addGroup(
		                                                                                                gl_panel.createParallelGroup(
		                                                                                                        Alignment.LEADING,
		                                                                                                        false)
		                                                                                                        .addComponent(
		                                                                                                                txtDate,
		                                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                                Short.MAX_VALUE)
		                                                                                                        .addComponent(
		                                                                                                                label,
		                                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                                148,
		                                                                                                                Short.MAX_VALUE)))
		                                                                        .addGroup(
		                                                                                Alignment.LEADING,
		                                                                                gl_panel.createSequentialGroup()
		                                                                                        .addComponent(
		                                                                                                lblQuantidade)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.RELATED)
		                                                                                        .addComponent(
		                                                                                                txtQuantidade,
		                                                                                                GroupLayout.PREFERRED_SIZE,
		                                                                                                42,
		                                                                                                GroupLayout.PREFERRED_SIZE)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.RELATED)
		                                                                                        .addComponent(
		                                                                                                btnInserir))))
		                                        .addGroup(
		                                                gl_panel.createSequentialGroup()
		                                                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE,
		                                                                329, GroupLayout.PREFERRED_SIZE)
		                                                        .addGap(10)
		                                                        .addComponent(btnAdicionarAPedidos,
		                                                                GroupLayout.PREFERRED_SIZE, 76,
		                                                                GroupLayout.PREFERRED_SIZE)
		                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                        .addComponent(scrollPane_1, 0, 0, Short.MAX_VALUE)))
		                        .addGap(227)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_panel.createSequentialGroup()
		                .addGap(32)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.TRAILING, false)
		                                .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        Short.MAX_VALUE)
		                                .addGroup(
		                                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                                .addComponent(cboPrioridade, GroupLayout.PREFERRED_SIZE,
		                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                                .addComponent(lblNDoPedido))
		                                .addGroup(
		                                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                                .addComponent(lblNome)
		                                                .addComponent(cboName, GroupLayout.PREFERRED_SIZE,
		                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                                .addComponent(lblPrioridade)))
		                .addGap(18)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.LEADING)
		                                .addComponent(lblSetro)
		                                .addGroup(
		                                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                                .addComponent(cboSetor, GroupLayout.PREFERRED_SIZE,
		                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                                .addComponent(lblResponsvel)
		                                                .addComponent(txtResponsable, GroupLayout.PREFERRED_SIZE,
		                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                                .addComponent(lblData)
		                                                .addComponent(txtDate, GroupLayout.PREFERRED_SIZE,
		                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.TRAILING)
		                                .addGroup(
		                                        gl_panel.createSequentialGroup()
		                                                .addPreferredGap(ComponentPlacement.RELATED)
		                                                .addComponent(btnAdicionarAPedidos, GroupLayout.PREFERRED_SIZE,
		                                                        48, GroupLayout.PREFERRED_SIZE).addGap(74))
		                                .addGroup(
		                                        gl_panel.createSequentialGroup()
		                                                .addGap(50)
		                                                .addGroup(
		                                                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                                                .addComponent(lblInserirProduto)
		                                                                .addComponent(cboProduto,
		                                                                        GroupLayout.PREFERRED_SIZE,
		                                                                        GroupLayout.DEFAULT_SIZE,
		                                                                        GroupLayout.PREFERRED_SIZE)
		                                                                .addComponent(lblQuantidade)
		                                                                .addComponent(txtQuantidade,
		                                                                        GroupLayout.PREFERRED_SIZE,
		                                                                        GroupLayout.DEFAULT_SIZE,
		                                                                        GroupLayout.PREFERRED_SIZE)
		                                                                .addComponent(btnInserir))
		                                                .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                .addGroup(
		                                                        gl_panel.createParallelGroup(Alignment.TRAILING)
		                                                                .addComponent(scrollPane_1, Alignment.LEADING,
		                                                                        GroupLayout.DEFAULT_SIZE, 170,
		                                                                        Short.MAX_VALUE)
		                                                                .addComponent(scrollPane, Alignment.LEADING,
		                                                                        GroupLayout.DEFAULT_SIZE, 170,
		                                                                        Short.MAX_VALUE)).addContainerGap()))));

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Quantidade", "Material",
		        "Area de uso" }));
		scrollPane_1.setViewportView(table_1);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(new Object[][] { { "01", "Cilindro de Arg", "Produ\u00E7\u00E3o" }, },
		        new String[] { "Quantidade", "Material", "Area de Uso" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 4093860116521909659L;
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		panelRequisition.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(SalesRequisitionFrame.class.getResource("/resources/cancel.png")));
		panel_1.add(btnCancelar);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(SalesRequisitionFrame.class.getResource("/resources/Save.png")));
		panel_1.add(btnSalvar);

		JButton btnGeraPedidoDe = new JButton("Gera Pedido de Compra");
		btnGeraPedidoDe.setIcon(new ImageIcon(SalesRequisitionFrame.class.getResource("/resources/ok.png")));
		panel_1.add(btnGeraPedidoDe);
	}
	
	/**
	 * Adiciona Listener aos componetes da classe
	 */

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
				else if (e.getSource().equals(btnInserir)) {
					String name;
					String area = "Produção";
					int quantidade;
					name = cboProduto.getSelectedItem().toString();
					quantidade = Integer.parseInt(txtQuantidade.getText());
					DefaultTableModel mdl = (DefaultTableModel) table.getModel();
					mdl.addRow(new Object[] { quantidade, name, area });
				}
			}
		};
		btnCancelar.addActionListener(buttonListener);
		btnInserir.addActionListener(buttonListener);

	}

}
