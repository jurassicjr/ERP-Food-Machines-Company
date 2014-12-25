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
import javax.swing.JComboBox;
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

import model.City;
import model.State;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.SalesController;
import userInterface.components.UpperTextField;

public class SalesOrderFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1158130629436192909L;
	private JFrame frame = this;

	private JPanel panelRegistration;
	private JPanel panelOrderDescription;
	private JPanel panelRodape;

	private JScrollPane scrollPane;
	
	private DateField textDate;
	
	private UpperTextField txtCompanyName;
	private UpperTextField txtContact;
	private UpperTextField txtStreet;
	private UpperTextField txtBlock;
	private JTextField txtCEP;
	private JTextField txtPhone;
	private JTextField txtCelPhone;
	
	private JTable table;
	private JLabel lblData;
	private JLabel lblRazoSocial;
	private JLabel lblEndereco;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblCep;
	private JLabel lblTelefone;
	private JLabel lblCelular;
	private JLabel lblEstado;
	
	private JButton btnCancelar;
	private JButton btnConcluir;
	
	private JComboBox<City> cboCity;
	private JComboBox<State> cboState;

	private SalesController controller;

	/**
	 * Constutor.
	 */
	public SalesOrderFrame() {
		controller = new SalesController();
		initialize();
		setListeners();
	}

	/**
	 * Inicializa a interface gráfica principal.
	 */
	
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
	
	/**
	 * Inicia o Jpanel principal de resgistro.
	 */
	
	private void initializeRegistrationData() {
		panelRegistration = new JPanel();
		lblData = new JLabel("Data");

		textDate = CalendarFactory.createDateField();
		textDate.setValue(null);
		lblRazoSocial = new JLabel("Razão Social");

		txtCompanyName = new UpperTextField();
		txtCompanyName.setColumns(10);

		JLabel lblContato = new JLabel("Contato");

		txtContact = new UpperTextField();
		txtContact.setColumns(10);

		lblEndereco = new JLabel("Endereço");

		txtStreet = new UpperTextField();
		txtStreet.setColumns(10);

		lblBairro = new JLabel("Bairro");

		txtBlock = new UpperTextField();
		txtBlock.setColumns(10);

		lblCidade = new JLabel("Cidade");

		lblCep = new JLabel("CEP");

		try {
			txtCEP = new JFormattedTextField(new MaskFormatter("##.###-###"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtCEP.setColumns(10);

		lblTelefone = new JLabel("Telefone");

		try {
	        txtPhone = new JFormattedTextField(new MaskFormatter("(##)####-####"));
        } catch (ParseException e) {
	        e.printStackTrace();
        }
		
		txtPhone.setColumns(10);
		
		cboCity = new JComboBox<City>();
		
		lblEstado = new JLabel("Estado");
		
		cboState = new JComboBox<State>();
		controller.fillStateAndCity(cboState, cboCity);
		
		lblCelular = new JLabel("Celular");
		
		
		try {
	        txtCelPhone = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
        } catch (ParseException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		
		txtCelPhone.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panelRegistration);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblData)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textDate, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblRazoSocial)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCompanyName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblEndereco)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtStreet, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblTelefone)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtPhone))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblEstado)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cboState, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCidade)
										.addComponent(lblCelular))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtCelPhone, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
										.addComponent(cboCity, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblContato)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtContact, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblBairro)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtBlock, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCep)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCEP, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblData)
						.addComponent(textDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRazoSocial)
						.addComponent(txtCompanyName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblContato)
						.addComponent(txtContact, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndereco)
						.addComponent(txtStreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBairro)
						.addComponent(txtBlock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(cboCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblEstado)
							.addComponent(cboState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCep)
							.addComponent(txtCEP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblCidade))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefone)
						.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCelular)
						.addComponent(txtCelPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(90, Short.MAX_VALUE))
		);
		panelRegistration.setLayout(gl_panel);

	}
	
	/**
	 * Inicializa a interface de descrição do pedido.
	 */

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
	
	/**
	 * Inicializa o panel inferior que contém os botões de finalização e cancelamento.
	 */

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
	
	/**
	 * Adiciona listener aos componentes da classe.
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
				if(e.getSource().equals(btnCancelar))controller.closeFrame(frame);
			}
		};
		btnCancelar.addActionListener(buttonListener);
	}
}
