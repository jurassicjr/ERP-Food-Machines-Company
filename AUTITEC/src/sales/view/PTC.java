package sales.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.Client;
import model.Kit;
import model.Material;
import model.Product;
import model.Service;
import sales.controller.PTCController;
import userInterface.components.ComboBoxAutoCompletion;
import userInterface.components.JNumberFormatField;
import util.ClearFrame;
import util.ShowMessage;

public class PTC extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2031745677196812709L;
	private JPanel principalPanel;
	private JTextField txtRastreabilityCode;
	private JTextField txtTitle;
	private JTable productTable;
	private JTable kitTable;
	private JTable materialTable;
	private JLabel lblRastreabilityCode;
	private JLabel lblClient;
	private JComboBox<Client> cboClient;
	private JLabel lblTitle;
	private JLabel lblProducts;
	private JLabel lblService;
	private JComboBox<Service> cboService;
	private JButton btnAddService;
	private JScrollPane scrollPane_3;
	private JLabel lblUpload;
	private JTextField txtUpload;
	private JButton btnPath;
	private JLabel lblBruteValue;
	private JTextField txtBruteValue;
	private JLabel lblProductPrice;
	private JTextField txtProductPrice;
	private JLabel lblKitPrice;
	private JTextField txtKitPrice;
	private JLabel lblMaterialPrice;
	private JTextField txtPriceMaterial;
	private JButton btnCancel;
	private JButton btnConfirm;
	private JButton btnClear;
	private PTCController controller;
	private JComboBox<Product> cboProduct;
	private JButton btnAddProduct;
	private JLabel lblKits;
	private JComboBox<Kit> cboKits;
	private JButton btnAddKit;
	private JLabel lblMaterial;
	private JComboBox<Material> cboMaterial;
	private JButton btnAddMaterial;
	private JTable serviceTable;

	public PTC() {
		controller = new PTCController(this);
		initialize();
		setListeners();
	}

	private void initialize() {
		  setTitle("Registro de proposta técnica comercial!");
		  setBounds(100, 100, 701, 696);
		  setMinimumSize(new Dimension(701, 696));
		  setPreferredSize(new Dimension(701,696));
		  setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		  getContentPane().setLayout(new BorderLayout(0, 0));
		  initializePrincipal();
	}

	private void initializePrincipal() {
	    principalPanel = new JPanel();
	    getContentPane().add(principalPanel, BorderLayout.CENTER);
	    
	    lblRastreabilityCode = new JLabel("Numero de Ratreabilidade");
	    
	    txtRastreabilityCode = new JTextField();
	    txtRastreabilityCode.setColumns(10);
	    
	    lblClient = new JLabel("Cliente");
	    
	    cboClient = new JComboBox<Client>();
	    controller.fillClient(cboClient, false);
	    new ComboBoxAutoCompletion(cboClient);
	    
	    lblTitle = new JLabel("Título");
	    
	    txtTitle = new JTextField();
	    txtTitle.setColumns(10);
	    
	    lblProducts = new JLabel("Produtos");
	    
	    cboProduct = new JComboBox<Product>();
	    controller.fillProduct(cboProduct);
	    new ComboBoxAutoCompletion(cboProduct);
	    
	    
	    btnAddProduct = new JButton("Adicionar");
	    btnAddProduct.setIcon(new ImageIcon(PTC.class.getResource("/resources/plus.png")));
	    
	    JScrollPane scrollPane = new JScrollPane();
	    
	    lblKits = new JLabel("Kits");
	    
	    cboKits = new JComboBox<Kit>();
	    controller.fillKit(cboKits);
	    new ComboBoxAutoCompletion(cboKits);
	    
	    btnAddKit = new JButton("Adicionar");
	    btnAddKit.setIcon(new ImageIcon(PTC.class.getResource("/resources/plus.png")));
	    
	    JScrollPane scrollPane_1 = new JScrollPane();
	    
	    lblMaterial = new JLabel("Material");
	    
	    JScrollPane scrollPane_2 = new JScrollPane();
	    
	    cboMaterial = new JComboBox<Material>();
	    controller.fillMaterials(cboMaterial);
	    new ComboBoxAutoCompletion(cboMaterial);
	    
	    btnAddMaterial = new JButton("Adicionar");
	    
	    btnAddMaterial.setIcon(new ImageIcon(PTC.class.getResource("/resources/plus.png")));
	    
	    lblService = new JLabel("Serviço");
	    
	    cboService = new JComboBox<Service>();
	    controller.fillService(cboService);
	    new ComboBoxAutoCompletion(cboService);
	    
	    btnAddService = new JButton("Adicionar");
	    btnAddService.setIcon(new ImageIcon(PTC.class.getResource("/resources/plus.png")));
	    
	    scrollPane_3 = new JScrollPane();
	    
	    lblUpload = new JLabel("UPLOAD");
	    
	    txtUpload = new JTextField();
	    txtUpload.setColumns(10);
	    
	    btnPath = new JButton("Caminho");
	    btnPath.setIcon(new ImageIcon(PTC.class.getResource("/resources/open.png")));
	    
	    lblBruteValue = new JLabel("Valor Bruto da P.T.C");
	    
	    txtBruteValue = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
	    txtBruteValue.setColumns(10);
	    
	    lblProductPrice = new JLabel("Preço");
	    
	    txtProductPrice = new JTextField();
	    txtProductPrice.setColumns(10);
	    
	    lblKitPrice = new JLabel("Preço");
	    
	    txtKitPrice = new JTextField();
	    txtKitPrice.setColumns(10);
	    
	    lblMaterialPrice = new JLabel("Preço");
	    
	    txtPriceMaterial = new JTextField();
	    txtPriceMaterial.setColumns(10);
	    
	    GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	    gl_principalPanel.setHorizontalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING, false)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblProducts)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(cboProduct, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblRastreabilityCode)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtRastreabilityCode, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblClient)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(cboClient, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
	    					.addGap(18)
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblTitle)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtTitle, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblProductPrice)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtProductPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(btnAddProduct))))
	    				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
	    				.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
	    				.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblService)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboService, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(btnAddService))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblUpload)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtUpload, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(btnPath))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblBruteValue)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtBruteValue, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblKits)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboKits, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblKitPrice)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtKitPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(btnAddKit))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblMaterial)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblMaterialPrice)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtPriceMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(btnAddMaterial)))
	    			.addContainerGap())
	    );
	    gl_principalPanel.setVerticalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblRastreabilityCode)
	    				.addComponent(txtRastreabilityCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblClient)
	    				.addComponent(cboClient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblTitle)
	    				.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblProducts)
	    				.addComponent(cboProduct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblProductPrice)
	    				.addComponent(txtProductPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(btnAddProduct))
	    			.addGap(18)
	    			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblKits)
	    				.addComponent(cboKits, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblKitPrice)
	    				.addComponent(txtKitPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(btnAddKit))
	    			.addGap(20)
	    			.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblMaterial)
	    				.addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblMaterialPrice)
	    				.addComponent(txtPriceMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(btnAddMaterial))
	    			.addGap(20)
	    			.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblService)
	    				.addComponent(cboService, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(btnAddService))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblUpload)
	    				.addComponent(txtUpload, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(btnPath))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblBruteValue)
	    				.addComponent(txtBruteValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addContainerGap(94, Short.MAX_VALUE))
	    );
	    gl_principalPanel.linkSize(SwingConstants.VERTICAL, new Component[] {scrollPane, scrollPane_1, scrollPane_2, scrollPane_3});
	    
	    serviceTable = new JTable();
	    String[] serviceTableHeader = new String[] {"Serviço", "descrição"};
	    serviceTable.setModel(new DefaultTableModel(null, serviceTableHeader) {

			/**
			 * 
			 */
            private static final long serialVersionUID = 3976606458308421488L;
	   
            boolean[] columnEditables = new boolean[] {
					false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
	    });
	    scrollPane_3.setViewportView(serviceTable);
	    
	    materialTable = new JTable();
	    String[] materialTableHeader = new String[] {"Material", "Preço"};
	    materialTable.setModel(new DefaultTableModel(null, materialTableHeader) {

			/**
			 * 
			 */
            private static final long serialVersionUID = 7250548222777451750L;
            
            boolean[] columnEditables = new boolean[] {
					false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
	    	
	    });
	    scrollPane_2.setViewportView(materialTable);
	    
	    kitTable = new JTable();
	    String[] kitTableHeader = new String[] {"Kit", "Preço"};
	    kitTable.setModel(new DefaultTableModel(null, kitTableHeader) {

			/**
			 * 
			 */
            private static final long serialVersionUID = 278905383466848631L;
	    	
            boolean[] columnEditables = new boolean[] {
					false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
	    });
	    scrollPane_1.setViewportView(kitTable);
	    
	    productTable = new JTable();
	    String[] productTableHeader = new String[] {"Produto", "Preço"};
	    productTable.setModel(new DefaultTableModel(null, productTableHeader) {

			/**
			 * 
			 */
            private static final long serialVersionUID = 1835899774627173017L;
	    	
            boolean[] columnEditables = new boolean[] {
					false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
	    });
	    scrollPane.setViewportView(productTable);
	    principalPanel.setLayout(gl_principalPanel);
	    
	    initializeSub();
    }

	private void initializeSub() {
	    JPanel subPanel = new JPanel();
	    subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(subPanel, BorderLayout.SOUTH);
	    
	    btnCancel = new JButton("Cancelar");
	    btnCancel.setIcon(new ImageIcon(PTC.class.getResource("/resources/cancel.png")));
	    btnConfirm = new JButton("Confirmar");
	    btnConfirm.setIcon(new ImageIcon(PTC.class.getResource("/resources/ok.png")));
	    btnClear = new JButton("Limpar");
	    btnClear.setIcon(new ImageIcon(PTC.class.getResource("/resources/ClearFrame.png")));
	    
	    subPanel.add(btnClear);
	    subPanel.add(btnCancel);
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
				if(e.getSource().equals(btnConfirm))register();
				else if(e.getSource().equals(btnCancel))controller.close();
				else if(e.getSource().equals(btnClear))clearFrame();
				else if(e.getSource().equals(btnAddMaterial))insertMaterial();
				else if(e.getSource().equals(btnAddProduct))insertProduct();
				else if(e.getSource().equals(btnAddKit))insertKit();
				else if(e.getSource().equals(btnAddService))insertService();
			}
		};
		btnCancel.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		btnAddMaterial.addActionListener(buttonListener);
		btnAddProduct.addActionListener(buttonListener);
		btnAddKit.addActionListener(buttonListener);
		btnAddService.addActionListener(buttonListener);
	}
	
	private void clearFrame() {
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente limpar os campos da PTC ?");
		if(i == JOptionPane.YES_OPTION) {
			ClearFrame.clear(this);
			ClearFrame.clearTable(productTable);
			ClearFrame.clearTable(kitTable);
			ClearFrame.clearTable(materialTable);
		}
	}
	
	private void register() {
		int i = ShowMessage.questionMessage(this, "Registrar", "Deseja realmente registrar essa PTC ?");
		if(i == JOptionPane.NO_OPTION)return;
	}
	
	private void insertMaterial() {
		if(cboMaterial.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Selecione o Material", "Selecione o material para adicionar a P.T.C");				
			return;
		}
		if(txtPriceMaterial.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o valor do material");
			return;
		}
		controller.insertMaterial((Material) cboMaterial.getSelectedItem(),Double.parseDouble(txtPriceMaterial.getText().replaceAll("R|\\$", "").replaceAll(",",	 "\\.").trim()), materialTable);
	}
	
	private void insertProduct() {
		if(cboProduct.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Selecione o Produto", "Selecione o Produto para adicionar a P.T.C");				
			return;
		}
		if(txtProductPrice.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o valor do produto!");
		}
		
		controller.insertProduct((Product) cboProduct.getSelectedItem(),Double.parseDouble(txtProductPrice.getText().replaceAll("R|\\$", "").replaceAll(",",	 "\\.").trim()), productTable);
	}
	private void insertKit() {
		if(cboKits.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Selecione o Kit", "Selecione o Kit para adicionar a P.T.C");				
			return;
		}
		if(txtKitPrice.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o valor do kit");				
			return;
		}
		
		controller.insertKit((Kit) cboKits.getSelectedItem(),Double.parseDouble(txtKitPrice.getText().replaceAll("R|\\$", "").replaceAll(",",	 "\\.").trim()), kitTable);
	}
	
	private void insertService() {
		if(cboService.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Selecione o Serviço", "Selecione o Serviço para adicionar a P.T.C");				
			return;
		}
		
		controller.insertService((Service) cboService.getSelectedItem(), serviceTable);
	}
}
