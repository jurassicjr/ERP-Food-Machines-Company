package sales.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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

public class SalesRequisitionFrame extends JFrame {
	
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTable table_1;

	public SalesRequisitionFrame() {
		this.setName("Requisição de compras");
		getContentPane().setLayout(new BorderLayout(0, 0));
		this.setTitle("Requisição de Compras");
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblNome = new JLabel("Nome");

		JComboBox comboBox = new JComboBox();

		JLabel lblPrioridade = new JLabel("Prioridade");

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addItem("Urgente");
		comboBox_1.addItem("Alta");
		comboBox_1.addItem("Média");
		comboBox_1.addItem("Baixa");

		JLabel lblNDoPedido = new JLabel("Nº do Pedido");

		JLabel label = new JLabel("");
		Border border = BorderFactory.createLineBorder(Color.BLUE);
		label.setBorder(border);

		JLabel lblSetro = new JLabel("Setor");

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.addItem("Produção/Serviço");
		comboBox_2.addItem("Vendas");
		comboBox_2.addItem("Financeiro");
		comboBox_2.addItem("RH");
		comboBox_2.addItem("Diretoria/Gerencia");

		JLabel lblResponsvel = new JLabel("Responsável");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblData = new JLabel("Data");

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel lblInserirProduto = new JLabel("Inserir Produto:");

		JComboBox<String> comboBox_3 = new JComboBox<String>();
<<<<<<< HEAD
=======
		comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] { "Valvula Borboleta", "Valvula Solenoie",
		        "Tubulação de 1/2 em aço inox" }));
		comboBox_3.setEditable(true);
		//comboBox_3.showPopup();
		ComboBoxAutoCompletion cbac = new ComboBoxAutoCompletion(comboBox_3);
		// AutoCompleteDecorator.decorate(comboBox_3);
>>>>>>> branch 'master' of ssh://git@bitbucket.org/pedrohreis/autitec.git
		JButton btnInserir = new JButton("Inserir");

		JScrollPane scrollPane = new JScrollPane();

		JButton btnAdicionarAPedidos = new JButton("+ P.D.C");
		btnAdicionarAPedidos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
		        .createParallelGroup(Alignment.LEADING)
		        .addGroup(
		                gl_panel.createSequentialGroup()
		                        .addContainerGap()
		                        .addGroup(
		                                gl_panel.createParallelGroup(Alignment.LEADING, false)
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
		                                                                                                comboBox_3,
		                                                                                                0,
		                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                Short.MAX_VALUE))
		                                                                        .addGroup(
		                                                                                gl_panel.createSequentialGroup()
		                                                                                        .addComponent(lblNome)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.RELATED)
		                                                                                        .addComponent(
		                                                                                                comboBox,
		                                                                                                GroupLayout.PREFERRED_SIZE,
		                                                                                                225,
		                                                                                                GroupLayout.PREFERRED_SIZE))
		                                                                        .addGroup(
		                                                                                gl_panel.createSequentialGroup()
		                                                                                        .addComponent(lblSetro)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.RELATED)
		                                                                                        .addComponent(
		                                                                                                comboBox_2,
		                                                                                                0,
		                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                Short.MAX_VALUE)))
		                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                        .addGroup(
		                                                                gl_panel.createParallelGroup(Alignment.LEADING)
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
		                                                                                                                                comboBox_1,
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
		                                                                                                                                textField,
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
		                                                                                                                textField_1)
		                                                                                                        .addComponent(
		                                                                                                                label,
		                                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                                148,
		                                                                                                                Short.MAX_VALUE)))
		                                                                        .addComponent(btnInserir)))
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
		                                                .addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE,
		                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                                .addComponent(lblNDoPedido))
		                                .addGroup(
		                                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                                .addComponent(lblNome)
		                                                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
		                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                                .addComponent(lblPrioridade)))
		                .addGap(18)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.LEADING)
		                                .addComponent(lblSetro)
		                                .addGroup(
		                                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                                .addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE,
		                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                                .addComponent(lblResponsvel)
		                                                .addComponent(textField, GroupLayout.PREFERRED_SIZE,
		                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                                .addComponent(lblData)
		                                                .addComponent(textField_1, GroupLayout.PREFERRED_SIZE,
		                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.LEADING)
		                                .addGroup(
		                                        Alignment.TRAILING,
		                                        gl_panel.createSequentialGroup()
		                                                .addPreferredGap(ComponentPlacement.RELATED)
		                                                .addComponent(btnAdicionarAPedidos, GroupLayout.PREFERRED_SIZE,
		                                                        48, GroupLayout.PREFERRED_SIZE).addGap(74))
		                                .addGroup(
		                                        Alignment.TRAILING,
		                                        gl_panel.createSequentialGroup()
		                                                .addGap(50)
		                                                .addGroup(
		                                                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                                                .addComponent(lblInserirProduto)
		                                                                .addComponent(comboBox_3,
		                                                                        GroupLayout.PREFERRED_SIZE,
		                                                                        GroupLayout.DEFAULT_SIZE,
		                                                                        GroupLayout.PREFERRED_SIZE)
		                                                                .addComponent(btnInserir))
		                                                .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                .addGroup(
		                                                        gl_panel.createParallelGroup(Alignment.TRAILING)
		                                                                .addComponent(scrollPane_1, Alignment.LEADING,
		                                                                        GroupLayout.DEFAULT_SIZE, 162,
		                                                                        Short.MAX_VALUE)
		                                                                .addComponent(scrollPane, Alignment.LEADING,
		                                                                        GroupLayout.DEFAULT_SIZE, 162,
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
			Class[] columnTypes = new Class[] { String.class, Object.class, Object.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		panel.setLayout(gl_panel);

	}
}
