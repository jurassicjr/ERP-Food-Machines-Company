package sales.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.SalesController;

public class SalesOrderFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1158130629436192909L;
	private DateField textDate;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTable table;
	private JButton btnCancelar;
	private JFrame frame = this;
	private JPanel panelRegistration;
	private JLabel lblData;
	private JPanel panelOrderDescription;
	private JScrollPane scrollPane;
	private JLabel lblRazoSocial;
	private JLabel lblEndereco;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblCep;
	private JLabel lblTelefone;
	private JPanel panelRodape;
	private JButton btnConcluir;
	private SalesController controller;

	public SalesOrderFrame() {
		controller = new SalesController();
		initialize();
		setListeners();
	}

	private void initialize() {
		this.setTitle("Pedido de compra");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		JTabbedPane tbPane = new JTabbedPane();
		getContentPane().add(tbPane, BorderLayout.CENTER);
		initializeRegistrationData();
		tbPane.addTab("Dados Cadastrais", panelRegistration);
		intializeOrderDescription();
		tbPane.addTab("Descrição do Pedido", panelOrderDescription);
		initializeSub();

	}

	private void initializeRegistrationData() {
		panelRegistration = new JPanel();
		lblData = new JLabel("Data");

		textDate = CalendarFactory.createDateField();
		textDate.setValue(null);
		lblRazoSocial = new JLabel("Razão Social");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblContato = new JLabel("Contato");

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		lblEndereco = new JLabel("Endereço");

		textField_2 = new JTextField();
		textField_2.setColumns(10);

		lblBairro = new JLabel("Bairro");

		textField_3 = new JTextField();
		textField_3.setColumns(10);

		lblCidade = new JLabel("Cidade");

		textField_4 = new JTextField();
		textField_4.setColumns(10);

		lblCep = new JLabel("CEP");

		try {
			textField_5 = new JFormattedTextField(new MaskFormatter("##.###-###"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		textField_5.setColumns(10);

		lblTelefone = new JLabel("Telefone");

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panelRegistration);
		gl_panel.setHorizontalGroup(gl_panel
		        .createParallelGroup(Alignment.LEADING)
		        .addGroup(
		                gl_panel.createSequentialGroup()
		                        .addContainerGap()
		                        .addGroup(
		                                gl_panel.createParallelGroup(Alignment.LEADING)
		                                        .addGroup(
		                                                gl_panel.createParallelGroup(Alignment.LEADING, false)
		                                                        .addGroup(
		                                                                gl_panel.createSequentialGroup()
		                                                                        .addComponent(lblData)
		                                                                        .addPreferredGap(
		                                                                                ComponentPlacement.RELATED)
		                                                                        .addComponent(textDate,
		                                                                                GroupLayout.PREFERRED_SIZE, 96,
		                                                                                GroupLayout.PREFERRED_SIZE))
		                                                        .addGroup(
		                                                                gl_panel.createSequentialGroup()
		                                                                        .addComponent(lblRazoSocial)
		                                                                        .addPreferredGap(
		                                                                                ComponentPlacement.RELATED)
		                                                                        .addComponent(textField,
		                                                                                GroupLayout.PREFERRED_SIZE,
		                                                                                345, GroupLayout.PREFERRED_SIZE)
		                                                                        .addPreferredGap(
		                                                                                ComponentPlacement.UNRELATED)
		                                                                        .addComponent(lblContato)
		                                                                        .addPreferredGap(
		                                                                                ComponentPlacement.RELATED)
		                                                                        .addComponent(textField_1,
		                                                                                GroupLayout.DEFAULT_SIZE, 111,
		                                                                                Short.MAX_VALUE)))
		                                        .addGroup(
		                                                gl_panel.createSequentialGroup()
		                                                        .addGroup(
		                                                                gl_panel.createParallelGroup(Alignment.LEADING,
		                                                                        false)
		                                                                        .addGroup(
		                                                                                gl_panel.createSequentialGroup()
		                                                                                        .addComponent(lblCidade)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.RELATED)
		                                                                                        .addComponent(
		                                                                                                textField_4,
		                                                                                                GroupLayout.PREFERRED_SIZE,
		                                                                                                205,
		                                                                                                GroupLayout.PREFERRED_SIZE)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.UNRELATED)
		                                                                                        .addComponent(lblCep)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.RELATED)
		                                                                                        .addComponent(
		                                                                                                textField_5))
		                                                                        .addGroup(
		                                                                                gl_panel.createSequentialGroup()
		                                                                                        .addComponent(
		                                                                                                lblEndereco)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.RELATED)
		                                                                                        .addComponent(
		                                                                                                textField_2,
		                                                                                                GroupLayout.PREFERRED_SIZE,
		                                                                                                319,
		                                                                                                GroupLayout.PREFERRED_SIZE)))
		                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                        .addGroup(
		                                                                gl_panel.createParallelGroup(Alignment.LEADING)
		                                                                        .addGroup(
		                                                                                gl_panel.createSequentialGroup()
		                                                                                        .addComponent(lblBairro)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.RELATED)
		                                                                                        .addComponent(
		                                                                                                textField_3,
		                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                163,
		                                                                                                Short.MAX_VALUE))
		                                                                        .addGroup(
		                                                                                gl_panel.createSequentialGroup()
		                                                                                        .addComponent(
		                                                                                                lblTelefone)
		                                                                                        .addPreferredGap(
		                                                                                                ComponentPlacement.RELATED)
		                                                                                        .addComponent(
		                                                                                                textField_6,
		                                                                                                GroupLayout.DEFAULT_SIZE,
		                                                                                                149,
		                                                                                                Short.MAX_VALUE)))))
		                        .addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_panel.createSequentialGroup()
		                .addContainerGap()
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblData)
		                                .addComponent(textDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(ComponentPlacement.UNRELATED)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblRazoSocial)
		                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE)
		                                .addComponent(lblContato)
		                                .addComponent(textField_1, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(ComponentPlacement.UNRELATED)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblEndereco)
		                                .addComponent(textField_2, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(lblBairro)
		                                .addComponent(textField_3, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(ComponentPlacement.UNRELATED)
		                .addGroup(
		                        gl_panel.createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblCidade)
		                                .addComponent(textField_4, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(lblCep)
		                                .addComponent(textField_5, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(lblTelefone)
		                                .addComponent(textField_6, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addContainerGap(111, Short.MAX_VALUE)));
		panelRegistration.setLayout(gl_panel);

	}

	private void intializeOrderDescription() {

		panelOrderDescription = new JPanel();

		scrollPane = new JScrollPane();
		GroupLayout gl_pnDesc = new GroupLayout(panelOrderDescription);
		gl_pnDesc.setHorizontalGroup(gl_pnDesc.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_pnDesc.createSequentialGroup().addContainerGap()
		                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE).addContainerGap()));
		gl_pnDesc.setVerticalGroup(gl_pnDesc.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_pnDesc.createSequentialGroup().addContainerGap()
		                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE).addContainerGap()));

		table = new JTable();
		scrollPane.setViewportView(table);
		panelOrderDescription.setLayout(gl_pnDesc);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(new String[][] { { "", "", "" }, }, new String[] { "Quantidade",
		        "Unidade", "Descrição", "Valor unitario", "Valor do IPI", "Valor Total" }));

	}

	private void initializeSub() {
		panelRodape = new JPanel();
		getContentPane().add(panelRodape, BorderLayout.SOUTH);
		panelRodape.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(SalesOrderFrame.class.getResource("/resources/cancel.png")));
		panelRodape.add(btnCancelar);

		btnConcluir
		= new JButton("Concluir");
		btnConcluir.setIcon(new ImageIcon(SalesOrderFrame.class.getResource("/resources/ok.png")));
		panelRodape.add(btnConcluir);
	}
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
				if(e.getSource().equals(btnCancelar))controller.closeFrame(frame);
			}
		};
		btnCancelar.addActionListener(buttonListener);
	}
}
