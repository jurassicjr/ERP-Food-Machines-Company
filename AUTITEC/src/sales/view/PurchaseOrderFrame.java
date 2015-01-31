package sales.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class PurchaseOrderFrame extends JFrame {
	/**
	 * 
	 */
    private static final long serialVersionUID = 6539729701716766043L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTable table;
	private JTextField textField_8;
	private JButton btnCancelar;
	private PurchaseOrderFrame pof;
	
	public PurchaseOrderFrame() {
		pof = this;
	    initialize();
	    setListeners();
    }
	
	private void initialize() {
	this.setTitle("Pedidos de Compra");
	Border border = BorderFactory.createLineBorder(Color.BLUE);
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	getContentPane().add(tabbedPane, BorderLayout.CENTER);
	
	JPanel panelDescricao = new JPanel();
	
	JPanel panel = new JPanel();
	tabbedPane.addTab("Dados Cadastrais", null, panel, null);
	tabbedPane.addTab("Descrição do Pedido", panelDescricao);
	
	JScrollPane scrollPane = new JScrollPane();
	
	JLabel lblFormaDePagamento = new JLabel("Forma de Pagamento");
	
	JComboBox comboBox_2 = new JComboBox();
	comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Cartão à vista", "Transferência", "Faturada", "Cheque"}));
	
	JLabel lblTransportadora = new JLabel("Transportadora");
	
	JComboBox comboBox_3 = new JComboBox();
	comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Carro Proprio"}));
	
	textField_8 = new JTextField();
	textField_8.setColumns(10);
	
	JLabel lblDataDaEntrega = new JLabel("Data da Entrega");
	GroupLayout gl_panelDescricao = new GroupLayout(panelDescricao);
	gl_panelDescricao.setHorizontalGroup(
		gl_panelDescricao.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panelDescricao.createSequentialGroup()
				.addGroup(gl_panelDescricao.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelDescricao.createSequentialGroup()
						.addGap(12)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE))
					.addGroup(gl_panelDescricao.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelDescricao.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, gl_panelDescricao.createSequentialGroup()
								.addComponent(lblTransportadora)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(comboBox_3, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(Alignment.LEADING, gl_panelDescricao.createSequentialGroup()
								.addComponent(lblFormaDePagamento)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
						.addComponent(lblDataDaEntrega)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap())
	);
	gl_panelDescricao.setVerticalGroup(
		gl_panelDescricao.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panelDescricao.createSequentialGroup()
				.addGap(13)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panelDescricao.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblFormaDePagamento)
					.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblDataDaEntrega))
				.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
				.addGroup(gl_panelDescricao.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblTransportadora)
					.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap())
	);
	
	table = new JTable();
	table.setModel(new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
			"Quantidade", "Unidade", "Descri\u00E7\u00E3o", "Valor Unit\u00E1rio", "Valor do IPI", "Valor Total"
		}
	));
	scrollPane.setViewportView(table);
	panelDescricao.setLayout(gl_panelDescricao);
	
	JLabel lblData = new JLabel("Data");
	
	textField = new JTextField();
	textField.setColumns(10);
	
	JLabel lblNewLabel = new JLabel("");
	lblNewLabel.setBorder(border);
	
	JLabel lblNPedido = new JLabel("Nº Pedido");
	
	JLabel lblFornecedor = new JLabel("Fornecedor");
	
	JComboBox comboBox = new JComboBox();
	
	JLabel lblRazoSocial = new JLabel("Razão Social");
	
	textField_1 = new JTextField();
	textField_1.setColumns(10);
	
	JLabel lblContato = new JLabel("Contato");
	
	textField_2 = new JTextField();
	textField_2.setColumns(10);
	
	JLabel lblEndereo = new JLabel("Endereço");
	
	textField_3 = new JTextField();
	textField_3.setColumns(10);
	
	JLabel lblBairro = new JLabel("Bairro");
	
	textField_4 = new JTextField();
	textField_4.setColumns(10);
	
	JLabel lblCidadeuf = new JLabel("Cidade/UF");
	
	textField_5 = new JTextField();
	textField_5.setColumns(10);
	
	JLabel lblCep = new JLabel("C.E.P");
	
	textField_6 = new JTextField();
	textField_6.setColumns(10);
	
	JLabel lblTel = new JLabel("Tel.:");
	
	textField_7 = new JTextField();
	textField_7.setColumns(10);
	
	JLabel lblPedidoDeCompra = new JLabel("Pedido de Compra");
	
	JComboBox comboBox_1 = new JComboBox();
	
	JLabel lblObrigatrioCertificadoDe = new JLabel("OBRIGATÓRIO CERTIFICADO DE QUALIDADE!");
	GroupLayout gl_panel = new GroupLayout(panel);
	gl_panel.setHorizontalGroup(
		gl_panel.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panel.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblData)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 376, Short.MAX_VALUE)
								.addComponent(lblNPedido)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblFornecedor)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblPedidoDeCompra)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBox_1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblEndereo)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_3))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblRazoSocial)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblCidadeuf)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(lblCep)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_6)))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblTel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_7, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblContato)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblBairro)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))))))
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(196)
						.addComponent(lblObrigatrioCertificadoDe)))
				.addContainerGap())
	);
	gl_panel.setVerticalGroup(
		gl_panel.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panel.createSequentialGroup()
				.addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblData)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblNPedido))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblFornecedor)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblPedidoDeCompra)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblRazoSocial)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblContato)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblEndereo)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblBairro)
					.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblCidadeuf)
					.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblCep)
					.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblTel)
					.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
				.addComponent(lblObrigatrioCertificadoDe)
				.addContainerGap())
	);
	panel.setLayout(gl_panel);
	
	JPanel panel_1 = new JPanel();
	getContentPane().add(panel_1, BorderLayout.SOUTH);
	panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
	
	btnCancelar = new JButton("Cancelar");
	btnCancelar.setIcon(new ImageIcon(PurchaseOrderFrame.class.getResource("/resources/cancel.png")));
	panel_1.add(btnCancelar);
	
	JButton btnFinalizar = new JButton("Finalizar");
	btnFinalizar.setIcon(new ImageIcon(PurchaseOrderFrame.class.getResource("/resources/ok.png")));
	panel_1.add(btnFinalizar);
	
	}
	
	private void setListeners() {
		ActionListener  buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pof.dispose();
			}
		};
		btnCancelar.addActionListener(buttonListener);
	}
}
