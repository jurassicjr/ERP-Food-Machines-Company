package sales.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
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
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.Material;
import model.PurchaseOrder;
import model.PurchaseOrderAssociation;
import model.PurchaseRequisition;
import model.PurchaseRequisitionAssociation;
import model.Supplier;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.PurchaseOrderController;
import userInterface.components.JNumberFormatField;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class PurchaseOrderFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6539729701716766043L;
	
	private JPanel descriptionPanel;
	private JPanel principalPanel;
	private JPanel subPanel;

	private JTable table;
	
	private DateField txtDeliveryDate;

	private JButton btnCancel;
	private JButton btnAdd;
	private JButton btnClear;
	private JButton btnConfirm;
	
	private JTextField txtSalesMan;
	private JTextField txtPhone;
	private JTextField txtUnitValue;
	private JTextField txtIpi;
	private JTextField txtFreight;
	private JTextArea txtObservation;
	
	private JComboBox<String> cboPaymentMethod;
	private JComboBox<PurchaseRequisition> cboPurchaseRequisition;
	private JComboBox<Supplier> cboSupplier;
	private JComboBox<Material> cboMaterial;
	private JComboBox<String> cboShippingCompany;
	
	private JLabel lblPaymentMethod;
	private JLabel lblPurchaseRequisition;
	private JLabel lblSupplier;
	private JLabel txtBuyNumber;
	private JLabel lblPhone;
	private JLabel lblSalesMan;
	private JLabel lblAmmount;
	private JLabel lblMaterial;
	private JLabel lblDeliveryDate;
	private JLabel lblUnitValue;
	private JLabel lblShippingCompany;
	private JLabel lblIpi;
	private JLabel lblFreight;
	private JLabel lblBuyNumber;

	private JScrollPane scrollPanelObservationAndDetails;
	private JScrollPane scrollPane;
	
	private JSpinner spnAmmount;

	private PurchaseOrderController controller;

	public PurchaseOrderFrame() {
		
		controller = new PurchaseOrderController(this);
		initialize();
		setListeners();
	}

	private void initialize() {
		this.setTitle("Pedidos de Compra");
		Icon.setIcon(this);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 835, 426);
		setPreferredSize(new Dimension(835, 426));
		setMinimumSize(new Dimension(835, 426));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		initializePrincipal();
	}

	private void initializePrincipal() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		descriptionPanel = new JPanel();
		principalPanel = new JPanel();
		
		tabbedPane.addTab("Dados Cadastrais", null, principalPanel, null);
		tabbedPane.addTab("Descrição do Pedido", descriptionPanel);
				
		lblPurchaseRequisition = new JLabel("Requisição de Compras");
		cboPurchaseRequisition = new JComboBox<PurchaseRequisition>();
		controller.fillSalesRequisitionCbo(cboPurchaseRequisition);
		cboPurchaseRequisition.setSelectedIndex(-1);
		
		lblSupplier = new JLabel("Fornecedor");
		cboSupplier = new JComboBox<Supplier>();

		lblBuyNumber = new JLabel("Nº Pedido");
		txtBuyNumber = new JLabel("");
		Border border = BorderFactory.createLineBorder(Color.BLUE);
		txtBuyNumber.setBorder(border);

		lblSalesMan = new JLabel("Vendedor");
		txtSalesMan = new JTextField();
		txtSalesMan.setColumns(10);
	
		lblPhone = new JLabel("Tel:.");
		txtPhone = new JTextField();
		txtPhone.setColumns(10);

		scrollPanelObservationAndDetails = new JScrollPane();
		scrollPanelObservationAndDetails.setViewportBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Observa\u00E7\u00F5es e Detalhes de Compra", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPanelObservationAndDetails.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		txtObservation = new JTextArea();
		txtObservation.setLineWrap(true);
		scrollPanelObservationAndDetails.setViewportView(txtObservation);

		lblMaterial = new JLabel("Material");
		cboMaterial = new JComboBox<Material>();

		lblAmmount = new JLabel("Quantidade");
		spnAmmount = new JSpinner();
		NumberEditor numberEditor = new JSpinner.NumberEditor(spnAmmount, "#.##");
		spnAmmount.setEditor(numberEditor);
		lblDeliveryDate = new JLabel("Data da Entrega");
		txtDeliveryDate = CalendarFactory.createDateField();

		lblUnitValue = new JLabel("Valor Unitário");
		txtUnitValue = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
		txtUnitValue.setColumns(10);

		lblShippingCompany = new JLabel("Transportadora");
		cboShippingCompany = new JComboBox<String>();
		cboShippingCompany.addItem("Carro Próprio");
		cboShippingCompany.addItem("Por Conta do Fornecedor!");
		controller.fillShippingCompany(cboShippingCompany);

		lblIpi = new JLabel("I.P.I");
		txtIpi = new JNumberFormatField(new DecimalFormat("0.00"));
		txtIpi.setColumns(10);

		lblFreight = new JLabel("Frete");
		txtFreight = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
		txtFreight.setColumns(10);

		lblPaymentMethod = new JLabel("Forma de Pagamento");
		cboPaymentMethod = new JComboBox<String>();
		cboPaymentMethod.addItem("A vistá no Cartão");
		cboPaymentMethod.addItem("Transferência Bancária");
		cboPaymentMethod.addItem("Faturada");
		cboPaymentMethod.addItem("Cheque");

		btnAdd = new JButton("Adicionar Material");
		btnAdd.setIcon(new ImageIcon(PurchaseOrderFrame.class.getResource("/resources/plus.png")));

		scrollPane = new JScrollPane();

		GroupLayout gl_panelDescricao = new GroupLayout(descriptionPanel);
		gl_panelDescricao.setHorizontalGroup(
			gl_panelDescricao.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelDescricao.createSequentialGroup()
					.addGroup(gl_panelDescricao.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelDescricao.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelDescricao.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelDescricao.createSequentialGroup()
									.addGroup(gl_panelDescricao.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelDescricao.createSequentialGroup()
											.addComponent(lblIpi)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtIpi, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panelDescricao.createSequentialGroup()
											.addComponent(lblUnitValue)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtUnitValue, 163, 163, 163)))
									.addGap(18)
									.addGroup(gl_panelDescricao.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelDescricao.createSequentialGroup()
											.addComponent(lblFreight)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtFreight, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
											.addGap(18)
											.addComponent(lblPaymentMethod)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cboPaymentMethod, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panelDescricao.createSequentialGroup()
											.addComponent(lblShippingCompany)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cboShippingCompany, 0, 466, Short.MAX_VALUE))))
								.addGroup(gl_panelDescricao.createSequentialGroup()
									.addComponent(lblMaterial)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboMaterial, 0, 354, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblAmmount)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spnAmmount, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblDeliveryDate)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtDeliveryDate, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panelDescricao.createSequentialGroup()
							.addGap(12)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE))
						.addGroup(gl_panelDescricao.createSequentialGroup()
							.addContainerGap(667, Short.MAX_VALUE)
							.addComponent(btnAdd)))
					.addContainerGap())
		);
		gl_panelDescricao.setVerticalGroup(
			gl_panelDescricao.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDescricao.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDescricao.createParallelGroup(Alignment.BASELINE)
						.addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spnAmmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDeliveryDate)
						.addComponent(txtDeliveryDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMaterial)
						.addComponent(lblAmmount))
					.addGap(18)
					.addGroup(gl_panelDescricao.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUnitValue)
						.addComponent(txtUnitValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblShippingCompany)
						.addComponent(cboShippingCompany, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelDescricao.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIpi)
						.addComponent(txtIpi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cboPaymentMethod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPaymentMethod)
						.addComponent(lblFreight)
						.addComponent(txtFreight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnAdd)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		table = new JTable();
		String[] header = new String[] { "Material", "Quantidade",
		        "Valor Unitário", "Valor do IPI", "Valor Total" };
		table.setModel(new DefaultTableModel(new Object[][] {},header) {

			/**
			 * 
			 */
            private static final long serialVersionUID = 2164810286675606611L;
			
            boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		descriptionPanel.setLayout(gl_panelDescricao);
		
		GroupLayout gl_panel = new GroupLayout(principalPanel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(scrollPanelObservationAndDetails))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblSalesMan)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtSalesMan))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblPurchaseRequisition)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboPurchaseRequisition, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblSupplier)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboSupplier, 0, 236, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblBuyNumber)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtBuyNumber, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblPhone)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtPhone, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtBuyNumber, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBuyNumber)
						.addComponent(lblPurchaseRequisition)
						.addComponent(cboPurchaseRequisition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSupplier)
						.addComponent(cboSupplier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSalesMan)
						.addComponent(txtSalesMan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPhone)
						.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPanelObservationAndDetails, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
					.addGap(43))
		);

		principalPanel.setLayout(gl_panel);
		initializeSub();
	}
	
	private void initializeSub() {
		subPanel = new JPanel();
		
		getContentPane().add(subPanel, BorderLayout.SOUTH);
		
		subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnClear = new JButton("Limpar");
		btnClear.setIcon(new ImageIcon(PurchaseOrderFrame.class.getResource("/resources/ClearFrame.png")));
		subPanel.add(btnClear);

		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(PurchaseOrderFrame.class.getResource("/resources/cancel.png")));
		subPanel.add(btnCancel);

		btnConfirm = new JButton("Confirmar");
		btnConfirm.setIcon(new ImageIcon(PurchaseOrderFrame.class.getResource("/resources/ok.png")));
		subPanel.add(btnConfirm);

	}

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
				if(e.getSource().equals(btnCancel))controller.close();
				else if(e.getSource().equals(btnAdd))addItemToTable();
				else if(e.getSource().equals(btnClear))clearFrame();
				else if(e.getSource().equals(btnConfirm))confirm();
			}
		};
		btnCancel.addActionListener(buttonListener);
		btnAdd.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
		
		ActionListener cboListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(cboPurchaseRequisition))fillSuppliers();
				else if(e.getSource().equals(cboSupplier))fillMaterial();
				else if(e.getSource().equals(cboMaterial))fillAmmount();
			}
		};
		cboSupplier.addActionListener(cboListener);
		cboPurchaseRequisition.addActionListener(cboListener);
		cboMaterial.addActionListener(cboListener);
		
		KeyListener tableKeyListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getSource().equals(table) && e.getKeyCode() == KeyEvent.VK_DELETE)deleteRow(table);
			}
		};
		table.addKeyListener(tableKeyListener);
	}
	
	private void confirm() {
		int i = ShowMessage.questionMessage(this, "Registrar", "Deseja realmente registrar esse pedido de compras ?");
		if(i == JOptionPane.NO_OPTION)return;
		boolean isOk = verify();
		if(!isOk)return;
		String paymentMethod = (String) cboPaymentMethod.getSelectedItem();
		PurchaseRequisition purchaseRequisition = (PurchaseRequisition) cboPurchaseRequisition.getSelectedItem();
		String shippingCompany = (String) cboShippingCompany.getSelectedItem();
		Supplier supplier = (Supplier) cboSupplier.getSelectedItem();
		Date deliveryDate =  (Date) txtDeliveryDate.getValue();
		String contactPhone = txtPhone.getText();
		double freight = Double.parseDouble(txtFreight.getText().replaceAll("R|\\$", "").replaceAll(",", ".").trim());
		String observation =txtObservation.getText();
		String salesMan = txtSalesMan.getText();
		List<PurchaseOrderAssociation> purchaseOrderAssociationList = createPurchaseOrderAssociationList();
		double totalValue = sumAllValues(purchaseOrderAssociationList);
		PurchaseOrder po = new PurchaseOrder(paymentMethod, purchaseRequisition, shippingCompany, supplier, new Date(System.currentTimeMillis()), deliveryDate,
				contactPhone, freight, salesMan, totalValue, purchaseOrderAssociationList);
		po.setObservation(observation);
		boolean complete = verifyRequisition(purchaseOrderAssociationList);
		int status = 0;
		if(!complete) {
			int a =ShowMessage.questionMessage(this, "Fechamento", "Deseja encerrar a requisição de compras ?");
			if(a == JOptionPane.YES_OPTION)status = 1;
			else status = 2;
		}
		controller.register(po, status);
		ClearFrame.clear(this);
		ClearFrame.clearTable(table);
		ShowMessage.successMessage(this, "sucesso", "Ordem de compra efetuada com sucesso!");
	}
	
	private boolean verifyRequisition(List<PurchaseOrderAssociation> purchaseOrderAssociationList) {
	    PurchaseRequisition pr = (PurchaseRequisition) cboPurchaseRequisition.getSelectedItem();
	    List<PurchaseRequisitionAssociation> list = pr.getAssociationList();
	    List<Material> matList = new ArrayList<Material>();
	    for (PurchaseOrderAssociation poa : purchaseOrderAssociationList) {
	    		matList.add(poa.getMaterial());
        }
	    for (PurchaseRequisitionAssociation pra : list) {
            if(!matList.contains(pra.getMaterial())) {
            	return false;
            }
        }
	    return true;
    }
	
	private double sumAllValues(List<PurchaseOrderAssociation> purchaseOrderAssociationList) {
	    double total = 0;
		for (PurchaseOrderAssociation poa : purchaseOrderAssociationList) {
	        total += poa.getCompostPrice();
        }
		return total;
    }

	private List<PurchaseOrderAssociation> createPurchaseOrderAssociationList() {
	    List<PurchaseOrderAssociation> list = new ArrayList<PurchaseOrderAssociation>();
		for(int i = 0; i<table.getRowCount(); i++) {
	    	PurchaseOrderAssociation poa = (PurchaseOrderAssociation) table.getValueAt(i, 0);
	    	list.add(poa);
	    }
		return list;
    }

	private boolean verify() {
	    if(cboPurchaseRequisition.getSelectedIndex() == -1) {
	    	ShowMessage.errorMessage(this, "Erro", "Selecione uma requisição de compra!");
	    	return false;
	    }
	    if(cboPaymentMethod.getSelectedIndex() == -1) {
	    	ShowMessage.errorMessage(this, "Erro", "Selecione o metodo de pagamento!");
	    	return false;
	    }
	    if(cboShippingCompany.getSelectedIndex() == -1) {
	    	ShowMessage.errorMessage(this, "Erro", "Selecione a transportadora!");
	    	return false;
	    }
	    if(cboSupplier.getSelectedIndex() == -1) {
	    	ShowMessage.errorMessage(this, "Erro", "Selecione um fornecedor");
	    	return false;
	    }
	    if(txtDeliveryDate.getValue() == null && new Date((long) txtDeliveryDate.getValue()).compareTo(new Date(System.currentTimeMillis())) < 0) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira uma previsão de entrega válida");
	    	return false;
	    }
	    if(txtPhone.getText().trim().isEmpty()) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira um telefone de contado com o o vendedor");
	    	return false;
	    }
	    if(txtSalesMan.getText().trim().isEmpty()) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira o nome do vendedor responsável pelo pedido de compras em questão!");
	    	return false;
	    }

		return true;
    }

	private void deleteRow(JTable table) {
		int i = table.getSelectedRow();
		if(i == -1)return;
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		Material m = (Material) tbl.getValueAt(i, 0);
		cboMaterial.addItem(m);
		tbl.removeRow(i);
	}
	
	private void fillAmmount() {
		if(cboMaterial.getSelectedIndex() == -1)return;
		PurchaseRequisition pr = (PurchaseRequisition) cboPurchaseRequisition.getSelectedItem();
		List<PurchaseRequisitionAssociation> associationList = pr.getAssociationList();
		for (PurchaseRequisitionAssociation purchaseRequisitionAssociation : associationList) {
	        if(purchaseRequisitionAssociation.getMaterial().equals(cboMaterial.getSelectedItem())) {
	        	double ammount = purchaseRequisitionAssociation.getAmmount();
	        	spnAmmount.setValue(ammount);
	        }
        }
	}
	
	private void fillSuppliers(){
		if(cboPurchaseRequisition.getSelectedIndex() == -1)return;
		controller.fillSuppliers(cboSupplier, (PurchaseRequisition) cboPurchaseRequisition.getSelectedItem());
	}
	
	private void fillMaterial() {
		if(cboSupplier.getSelectedIndex() == -1)return;
		controller.fillMaterial(cboMaterial, (PurchaseRequisition) cboPurchaseRequisition.getSelectedItem(),(Supplier) cboSupplier.getSelectedItem());
		ClearFrame.clearTable(table);
	}
	private void clearFrame() {
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente limpar a janela ?");
		if(i == JOptionPane.NO_OPTION)return;
		ClearFrame.clear(this);
		ClearFrame.clearTable(table);
	}
	
	private void addItemToTable() {
		if(cboMaterial.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Erro", "Selecione um material para adicionar a tabela");
			return;
		}
		if(Double.parseDouble(spnAmmount.getValue().toString()) <= 0) {
			ShowMessage.errorMessage(this, "Erro", "Insira uma quantidade maior que zero");
			return;
		}
		if(txtIpi.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira a porcentagem do ipi");
		}
		
		Material m = (Material) cboMaterial.getSelectedItem();
		double ammount = Double.parseDouble(spnAmmount.getValue().toString());
		double unitValue = Double.parseDouble(txtUnitValue.getText().replaceAll("R|\\$", "").replaceAll(",", ".").trim());
		double ipi = Double.parseDouble(txtIpi.getText().replaceAll("R|\\$", "").replaceAll(",", ".").trim());
		double total = ((unitValue * (ipi/100)) + unitValue) * ammount;
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		PurchaseRequisition pr = (PurchaseRequisition) cboPurchaseRequisition.getSelectedItem();
		List<PurchaseRequisitionAssociation> associationList = pr.getAssociationList();
		for (PurchaseRequisitionAssociation pra : associationList) {
	        if(m.equals(pra.getMaterial())) {
	        	if(pra.getAmmount() - ammount < 0) {
	        		ShowMessage.errorMessage(this, "Erro", "Está comprando material a mais do que o solicitado!");
	        		return;
	        	}else if(pra.getAmmount() - ammount != 0) {
	        		ShowMessage.errorMessage(this, "Quantidade", "Foi solicitado um maior quantidade desse produto");
	        		spnAmmount.setValue(pra.getAmmount());
	        	}else if(pra.getAmmount() - ammount == 0) {
	        		cboMaterial.removeItem(m);
	        	}
	        }
        }
		PurchaseOrderAssociation poa = new PurchaseOrderAssociation(m, unitValue, ipi, total, ammount);
		Object[] rowData = new Object[] {poa, ammount, unitValue,ipi, total};
		tbl.addRow(rowData);
	}
}
