package sales.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.Employee;
import model.Material;
import model.PTC;
import model.PurchaseRequisition;
import model.PurchaseRequisitionAssociation;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.SalesRequisitionControllerFrame;
import userInterface.components.ComboBoxAutoCompletion;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class PurchaseRequisitionFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7516160610003247856L;

	private JFrame frame = this;

	private JPanel panelRequisition;
	
	private JScrollPane scrollPane;

	private DateField txtDate;

	private JLabel lblRequester;
	private JLabel lblRequisitioNumber;
	private JLabel lblPriority;
	private JLabel lblPTC;
	private JLabel lblSector;
	private JLabel lblDate;
	private JLabel lblProduct;
	private JLabel lblAmmount;


	private JComboBox<String> cboSector;
	private JComboBox<Material> cboMaterial;
	private JComboBox<String> cboPriority;
	private JComboBox<Employee> cboRequester;

	private ComboBoxAutoCompletion cbac;

	private JButton btnInsert;
	private JButton btnCancelar;

	private JTable table;

	private SalesRequisitionControllerFrame controller;

	private JTextField txtRequisitionNumber;
	private JTextField txtAmmount;

	private JComboBox<PTC> cboPTC;

	private JButton btnSave;

	private JTextArea txtJustification;

	public PurchaseRequisitionFrame() {
		controller = new SalesRequisitionControllerFrame(this);		
		initialize();
		setListeners();
	}
	
	public PurchaseRequisitionFrame(PurchaseRequisition sr) {
		controller = new SalesRequisitionControllerFrame(this);		
		initialize();
		setListeners();
		fillFields(sr);
	}
	
	/**
	 * Inicializa os elemento gráficos da aplicação
	 */

	private void initialize() {
		this.setTitle("Requisição de Compras");
		setBounds(100, 100, 782, 463);
		setPreferredSize(new Dimension(1000, 463));
		setMinimumSize(new Dimension(1000,  463));
		getContentPane().setLayout(new BorderLayout(0, 0));
		Icon.setIcon(this);
		panelRequisition = new JPanel();
		getContentPane().add(panelRequisition, BorderLayout.CENTER);

		lblRequester = new JLabel("Requisitante");

		cboRequester = new JComboBox<Employee>();
		controller.fillEmployees(cboRequester);
		
		lblPriority = new JLabel("Prioridade");

		cboPriority = new JComboBox<String>();
		cboPriority.addItem("Urgente");
		cboPriority.addItem("Alta");
		cboPriority.addItem("Média");
		cboPriority.addItem("Baixa");

		lblRequisitioNumber = new JLabel("Nº da Req.");

		txtRequisitionNumber = new JTextField("");
		Border border = BorderFactory.createLineBorder(Color.BLUE);
		txtRequisitionNumber.setBorder(border);

		lblSector = new JLabel("Setor");

		cboSector = new JComboBox<String>();
		cboSector.addItem("Produção/Serviço");
		cboSector.addItem("Vendas");
		cboSector.addItem("Financeiro");
		cboSector.addItem("RH");
		cboSector.addItem("Diretoria/Gerencia");

		lblDate = new JLabel("Data");

		txtDate = CalendarFactory.createDateField();
		txtDate.setValue(null);

		lblProduct = new JLabel("Inserir Produto:");

		cboMaterial = new JComboBox<Material>();
		controller.fillMaterials(cboMaterial);
		cboMaterial.setSelectedIndex(-1);
		cbac = new ComboBoxAutoCompletion(cboMaterial);
				
		btnInsert = new JButton("Inserir");
		btnInsert.setIcon(new ImageIcon(PurchaseRequisitionFrame.class.getResource("/resources/plus.png")));

		scrollPane = new JScrollPane();

		lblAmmount = new JLabel("Quantidade");

		txtAmmount = new JTextField();
		txtAmmount.setColumns(10);
		
		lblPTC = new JLabel("P.T.C");
		
		cboPTC = new JComboBox<PTC>();
		controller.fillPTC(cboPTC);
		cboPTC.setSelectedIndex(-1);
		new ComboBoxAutoCompletion(cboPTC);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Justificativa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JSeparator separator = new JSeparator();
		
		GroupLayout gl_panel = new GroupLayout(panelRequisition);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblSector)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cboSector, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblRequester)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cboRequester, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblPriority)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cboPriority, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblPTC)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cboPTC, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblProduct)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboMaterial, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblAmmount)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtAmmount, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnInsert, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblRequisitioNumber)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtRequisitionNumber, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblDate)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtDate, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))))
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(cboRequester, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRequester)
								.addComponent(lblPriority)
								.addComponent(cboPriority, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRequisitioNumber)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(txtRequisitionNumber, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtDate, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblSector)
							.addComponent(cboSector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPTC)
							.addComponent(cboPTC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblDate)))
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduct)
						.addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAmmount)
						.addComponent(txtAmmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInsert))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
		);
		
		txtJustification = new JTextArea();
		txtJustification.setLineWrap(true);
		scrollPane_1.setViewportView(txtJustification);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		String[] header = new String[] {"Material", "Quantidade", "Área de uso"};
		
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
		btnCancelar.setIcon(new ImageIcon(PurchaseRequisitionFrame.class.getResource("/resources/cancel.png")));
		panel_1.add(btnCancelar);

		btnSave = new JButton("Salvar");
		btnSave.setIcon(new ImageIcon(PurchaseRequisitionFrame.class.getResource("/resources/Save.png")));
		panel_1.add(btnSave);
	}
	
	/**
	 * Adiciona Listener aos componetes da classe
	 */

	private void setListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			    controller.close();
			}
		});
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnCancelar)) {
					controller.close();
				}else if (e.getSource().equals(btnInsert)) {
					Material material;
					String area;
					if(cboSector.getSelectedIndex() == -1) {
						ShowMessage.errorMessage(frame, "Erro", "Selecione para qual setor é o material!");
						return;
					}else {
						area = (String) cboSector.getSelectedItem();						
					}
					double ammount;
					if(txtAmmount.getText() == "" || txtAmmount.getText().equalsIgnoreCase("0")) {
						ShowMessage.errorMessage(frame, "Erro", "Insira a quantidade correta do produto");
						return;
					}else {
						ammount = Double.parseDouble(txtAmmount.getText());						
					}
					if(cboMaterial.getSelectedIndex() == -1) {
						ShowMessage.errorMessage(frame, "Erro", "Selecione um produto para adicionar!");
						return;
					}else {
						material = (Material) cboMaterial.getSelectedItem();						
					}
					controller.addMaterial(material, ammount, table, area);
				}else if(e.getSource().equals(btnSave))save();
			}
		};
		btnSave.addActionListener(buttonListener);
		btnCancelar.addActionListener(buttonListener);
		btnInsert.addActionListener(buttonListener);
	}
	
	public void save() {
		int i = ShowMessage.questionMessage(this, "Registro", "Deseja realmente realizar o registro da requisição de compra");
		if(i == JOptionPane.NO_OPTION)return;
		boolean isOk = isOk();
		if(!isOk)return;
		Employee e = (Employee) cboRequester.getSelectedItem();
		String priority = (String) cboPriority.getSelectedItem();
		Date date = (Date) txtDate.getValue();
		String requisitionNumber = txtRequisitionNumber.getText();
		PTC ptc = (PTC) cboPTC.getSelectedItem();
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		List<PurchaseRequisitionAssociation> associationList = new ArrayList<PurchaseRequisitionAssociation>();
		for(int cont =0; cont < table.getRowCount(); cont++) {
			Material m = (Material) tbl.getValueAt(cont, 0);
			double ammount = (double) tbl.getValueAt(cont, 1);
			String section = (String) tbl.getValueAt(cont, 2);
			PurchaseRequisitionAssociation sra = new PurchaseRequisitionAssociation(m, ammount, section);
			associationList.add(sra);
		}
		PurchaseRequisition sr = new PurchaseRequisition(e, requisitionNumber, date, priority, ptc);
		sr.setAssociationList(associationList);
		String justification = txtJustification.getText();
		sr.setJustification(justification);
		controller.register(sr);
		ShowMessage.successMessage(this, "Sucesso", "Registro de requisição de compra concluido com sucesso");
		ClearFrame.clear(this);
	}

	private boolean isOk() {
	    if(cboRequester.getSelectedIndex() == -1) {
	    	ShowMessage.errorMessage(this, "Erro", "Selecione o solicitande da requisião!");
	    	return false;
	    }
	    if(cboPriority.getSelectedIndex() == -1) {
	    	ShowMessage.errorMessage(this, "Erro", "Selecione qual a prioridade desse pedido de compra!");
	    	return false;
	    }if(cboPTC.getSelectedIndex() == -1) {
	    	ShowMessage.errorMessage(this, "Erro", "Selecione à qual P.T.C este produto pertence");
	    	return false;
	    }if(table.getRowCount() == 0) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira ao menos um material à requisição de compra!");
	    	return false;
	    }if(txtDate.getValue() == null) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira a data que o pedido foi realizado!");
	    	return false;
	    }if(txtRequisitionNumber.getText().isEmpty()) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira o numero da requisição");
	    	return false;
	    }
	    return true;
    }
	
	private void fillFields(PurchaseRequisition sr){
		ClearFrame.clear(this);
		ClearFrame.clearTable(table);
		cboRequester.setSelectedItem(sr.getRequisitor());
		cboRequester.setEnabled(false);
		cboPriority.setSelectedItem(sr.getPrority());
		cboPriority.setEnabled(false);
		cboPTC.setSelectedItem(sr.getPtc());
		cboPTC.setEnabled(false);
		txtDate.setValue(sr.getDate());
		txtDate.setEnabled(false);
		txtRequisitionNumber.setText(sr.getRequisitionNumber());
		txtRequisitionNumber.setEnabled(false);
		txtJustification.setText(sr.getJustification());
		txtJustification.setEnabled(false);
		
		addRowToTable(sr.getAssociationList(), table);
		btnInsert.setEnabled(false);
	}

	private void addRowToTable(List<PurchaseRequisitionAssociation> list, JTable tabl) {
			for (PurchaseRequisitionAssociation sra : list) {
				((DefaultTableModel) tabl.getModel()).addRow(new Object[] { null, null, null });
				int row = table.getRowCount() - 1;
				table.setValueAt(sra, row, 0);
				table.setValueAt(sra.getAmmount(), row, 1);
				table.setValueAt(sra.getSection(), row, 2);	 
            }
    }
}
