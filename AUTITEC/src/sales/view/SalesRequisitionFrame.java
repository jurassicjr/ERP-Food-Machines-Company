package sales.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

import model.Material;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.MaterialUpdateController;
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
	private JComboBox<Material> cboProduto;
	private JComboBox<String> cboPrioridade;
	private JComboBox<String> cboName;
	private ComboBoxAutoCompletion cbac;

	private JButton btnInserir;
	private JButton btnCancelar;
	private JButton btnAddToSalesOrder;

	private JTable table;
	private JTable SalesOrderTable;

	private SalesController controller;

	private MaterialUpdateController controllerProduct;

	private JLabel lblPrioridade;

	private JButton btnPickUpFromSalesOrder;


	public SalesRequisitionFrame() {
		controllerProduct = new MaterialUpdateController();
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
		setBounds(100, 100, 720, 375);
		setPreferredSize(new Dimension(610, 360));
		panelRequisition = new JPanel();
		getContentPane().add(panelRequisition, BorderLayout.CENTER);

		lblNome = new JLabel("Nome");

		cboName = new JComboBox<String>();

		lblPrioridade = new JLabel("Prioridade");

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

		cboProduto = new JComboBox<Material>();
		controllerProduct.fillMaterials(cboProduto);
		cboProduto.setSelectedIndex(-1);
		cbac = new ComboBoxAutoCompletion(cboProduto);
				
		btnInserir = new JButton("Inserir");

		scrollPane = new JScrollPane();

		btnAddToSalesOrder = new JButton("+ P.D.C");

		scrollPane_1 = new JScrollPane();

		lblQuantidade = new JLabel("Quantidade");

		txtQuantidade = new JTextField();
		txtQuantidade.setColumns(10);
		
		btnPickUpFromSalesOrder = new JButton("- P.D.C");
		
		GroupLayout gl_panel = new GroupLayout(panelRequisition);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNome)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboName, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblSetro)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboSetor, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblInserirProduto)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboProduto, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblQuantidade)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtQuantidade, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnInserir))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblPrioridade)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cboPrioridade, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblNDoPedido))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblResponsvel)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtResponsable, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblData)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(label, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)))))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAddToSalesOrder, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPickUpFromSalesOrder, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, 0, 0, Short.MAX_VALUE)))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(cboPrioridade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNDoPedido))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNome)
							.addComponent(cboName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPrioridade)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSetro)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(cboSetor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblResponsvel)
							.addComponent(txtResponsable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblData)
							.addComponent(txtDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInserirProduto)
						.addComponent(cboProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuantidade)
						.addComponent(txtQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInserir))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(48)
							.addComponent(btnAddToSalesOrder, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnPickUpFromSalesOrder, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addGap(21))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
							.addContainerGap())))
		);

		SalesOrderTable = new JTable();
		String[] salesOrderTableHeader = new String[] {"Quantidade", "Material", "Area de Uso"};
		SalesOrderTable.setModel(new DefaultTableModel(null, salesOrderTableHeader) {

			/**
			 * 
			 */
            private static final long serialVersionUID = -617596618744472709L;
			
            boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		scrollPane_1.setViewportView(SalesOrderTable);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		String[] header = new String[] {"Quantidade", "Material", "Área de uso"};
		
		table.setModel(new DefaultTableModel(null, header) {

			/**
			 * 
			 */
            private static final long serialVersionUID = -617596618744472709L;
			
            boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
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
				}else if(e.getSource().equals(btnAddToSalesOrder)) {
					DefaultTableModel tbl = (DefaultTableModel) SalesOrderTable.getModel();
					DefaultTableModel tblSalesRequisition =  (DefaultTableModel) table.getModel();
					int i = table.getSelectedRow();
					if(i != -1) {
					Object[] row = new Object[] { tblSalesRequisition.getValueAt(i, 0), tblSalesRequisition.getValueAt(i, 1), tblSalesRequisition.getValueAt(i, 2)};
					tbl.addRow(row);
					tblSalesRequisition.removeRow(i);
					}
				}else if(e.getSource().equals(btnPickUpFromSalesOrder)) {
					DefaultTableModel tbl = (DefaultTableModel) SalesOrderTable.getModel();
					DefaultTableModel tblSalesRequisition =  (DefaultTableModel) table.getModel();
					int i = SalesOrderTable.getSelectedRow();
					if(i != -1) {
					Object[] row = new Object[] { tbl.getValueAt(i, 0), tbl.getValueAt(i, 1), tbl.getValueAt(i, 2)};
					tblSalesRequisition.addRow(row);
					tbl.removeRow(i);
					}
				}
			}
		};
		btnPickUpFromSalesOrder.addActionListener(buttonListener);
		btnAddToSalesOrder.addActionListener(buttonListener);
		btnCancelar.addActionListener(buttonListener);
		btnInserir.addActionListener(buttonListener);

	}
}
