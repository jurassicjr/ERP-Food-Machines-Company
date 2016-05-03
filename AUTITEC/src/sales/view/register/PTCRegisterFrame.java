package sales.view.register;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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
import javax.swing.text.MaskFormatter;

import model.CNPJ;
import model.Client;
import model.Inventory;
import model.Kit;
import model.Material;
import model.PTC;
import model.Product;
import model.Service;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.PTCController;
import userInterface.components.ComboBoxAutoCompletion;
import util.ClearFrame;
import util.ShowMessage;

public class PTCRegisterFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2031745677196812709L;
	private JPanel principalPanel;
	
	private JTextField txtRastreabilityCode;
	private JTextField txtTitle;
	private JTextField txtBruteValue;
	private JTextField txtUpload;
	private JTextField txtKitPrice;
	private JTextField txtProductPrice;
	private JTextField txtPriceMaterial;
	
	private JTable productTable;
	private JTable kitTable;
	private JTable materialTable;
	private JTable serviceTable;
	
	private JLabel lblRastreabilityCode;
	private JLabel lblClient;
	private JLabel lblTitle;
	private JLabel lblProducts;
	private JLabel lblService;
	private JLabel lblUpload;
	private JLabel lblBruteValue;
	private JLabel lblProductPrice;
	private JLabel lblKitPrice;
	private JLabel lblMaterialPrice;
	private JLabel lblKits;
	private JLabel lblMaterialAmmount;
	private JLabel lblProductAmmount;
	private JLabel lblKitAmmount;
	private JLabel lblAliquot;
	private JLabel lblContribuition;
	private JLabel lblValorBruto;
	private JLabel lblFinalPrice;
	private JLabel lblSuggestedPrice;
	private JLabel lblDiscount;
	private JLabel lblMaterial;
	
	private JComboBox<Material> cboMaterial;
	private JComboBox<Client> cboClient;
	private JComboBox<Service> cboService;
	private JComboBox<Product> cboProduct;
	private JComboBox<Kit> cboKits;

	private JButton btnPath;
	private JButton btnAddService;
	private JButton btnCancel;
	private JButton btnConfirm;
	private JButton btnClear;
	private JButton btnAddMaterial;
	private JButton btnAddProduct;
	private JButton btnAddKit;
	
	private JScrollPane scrollPane_3;
	private PTCController controller;
	private JTextField txtProductAmmount;
	private JTextField txtKitAmmount;
	private JTextField txtMaterialAmmount;
	private JTextField txtAliquot;
	private JTextField txtAliquotPlusBruteValue;
	private JTextField txtContribuition;
	private JTextField txtSuggestedPrice;
	private JTextField txtDiscount;
	private JTextField txtFinalPrice;

	private JComboBox<CNPJ> cboCNPJ;

	private DateField txtDate;

	public PTCRegisterFrame() {
		controller = new PTCController(this);
		initialize();
		setListeners();
	}

	private void initialize() {
		  setTitle("Registro de proposta técnica comercial!");
		  setBounds(100, 100, 756, 734);
		  setMinimumSize(new Dimension(756, 734));
		  setPreferredSize(new Dimension(756,734));
		  setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		  getContentPane().setLayout(new BorderLayout(0, 0));
		  initializePrincipal();
	}

	private void initializePrincipal() {
	    principalPanel = new JPanel();
	    getContentPane().add(principalPanel, BorderLayout.CENTER);
	    
	    lblRastreabilityCode = new JLabel("Numero de Ratreabilidade");
	    
	    try {
	        txtRastreabilityCode = new JFormattedTextField(new MaskFormatter("#####-'A'"));
        } catch (ParseException e1) {
	        e1.printStackTrace();
        }
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
	    btnAddProduct.setIcon(new ImageIcon(PTCRegisterFrame.class.getResource("/resources/plus.png")));
	    
	    JScrollPane scrollPane = new JScrollPane();
	    
	    lblKits = new JLabel("Kits");
	    
	    cboKits = new JComboBox<Kit>();
	    controller.fillKit(cboKits);
	    new ComboBoxAutoCompletion(cboKits);
	    
	    btnAddKit = new JButton("Adicionar");
	    btnAddKit.setIcon(new ImageIcon(PTCRegisterFrame.class.getResource("/resources/plus.png")));
	    
	    JScrollPane scrollPane_1 = new JScrollPane();
	    
	    lblMaterial = new JLabel("Material");
	    
	    JScrollPane scrollPane_2 = new JScrollPane();
	    
	    cboMaterial = new JComboBox<Material>();
	    controller.fillMaterials(cboMaterial);
	    new ComboBoxAutoCompletion(cboMaterial);
	    
	    btnAddMaterial = new JButton("Adicionar");
	    
	    btnAddMaterial.setIcon(new ImageIcon(PTCRegisterFrame.class.getResource("/resources/plus.png")));
	    
	    lblService = new JLabel("Serviço");
	    
	    cboService = new JComboBox<Service>();
	    controller.fillService(cboService);
	    new ComboBoxAutoCompletion(cboService);
	    
	    btnAddService = new JButton("Adicionar");
	    btnAddService.setIcon(new ImageIcon(PTCRegisterFrame.class.getResource("/resources/plus.png")));
	    
	    scrollPane_3 = new JScrollPane();
	    
	    lblUpload = new JLabel("UPLOAD");
	    
	    txtUpload = new JTextField();
	    txtUpload.setColumns(10);
	    
	    btnPath = new JButton("Caminho");
	    btnPath.setIcon(new ImageIcon(PTCRegisterFrame.class.getResource("/resources/open.png")));
	    
	    lblBruteValue = new JLabel("Valor Bruto da P.T.C");
	    
	    txtBruteValue = new JTextField();
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
	    
	    lblMaterialAmmount = new JLabel("Quantidade");
	    
	    txtProductAmmount = new JTextField();
	    txtProductAmmount.setText("1");
	    txtProductAmmount.setColumns(10);
	    
	    lblProductAmmount = new JLabel("Quantidade");
	    
	    txtKitAmmount = new JTextField();
	    txtKitAmmount.setText("1");
	    txtKitAmmount.setColumns(10);
	    
	    lblKitAmmount = new JLabel("Quantidade");
	    
	    txtMaterialAmmount = new JTextField();
	    txtMaterialAmmount.setText("1");
	    txtMaterialAmmount.setColumns(10);
	    
	    lblAliquot = new JLabel("Alíquota");
	    
	    try {
	        txtAliquot = new JFormattedTextField(new MaskFormatter("##.##%"));
        } catch (ParseException e) {
	        e.printStackTrace();
        }
	    txtAliquot.setColumns(10);
	    
	    lblValorBruto = new JLabel("Valor Bruto + Alíquota");
	    
	    txtAliquotPlusBruteValue = new JTextField();
	    txtAliquotPlusBruteValue.setColumns(10);
	    
	    lblContribuition = new JLabel("Margem");
	    
	    try {
	        txtContribuition = new JFormattedTextField(new MaskFormatter("##.##%"));
        } catch (ParseException e) {
	        e.printStackTrace();
        }
	    txtContribuition.setColumns(10);
	    
	    lblSuggestedPrice = new JLabel("Valor Sugerido");
	    
	    txtSuggestedPrice = new JTextField();
	    txtSuggestedPrice.setColumns(10);
	    
	    lblDiscount = new JLabel("Desconto");
	    
	    try {
	        txtDiscount = new JFormattedTextField(new MaskFormatter("##.##%"));
        } catch (ParseException e) {
	        e.printStackTrace();
        }
	    txtDiscount.setColumns(10);
	    
	    lblFinalPrice = new JLabel("Valor Final");
	    
	    txtFinalPrice = new JTextField();
	    txtFinalPrice.setColumns(10);
	    
	    JLabel lblCnpj = new JLabel("CNPJ");
	    
	    cboCNPJ = new JComboBox<CNPJ>();
		controller.fillCnpj(cboCNPJ);
	    
	    JLabel lblDataDeCriao = new JLabel("Data de Criação");
	    
	    txtDate = CalendarFactory.createDateField();
	    
	    GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	    gl_principalPanel.setHorizontalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
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
	    							.addComponent(lblCnpj)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(cboCNPJ, 0, 348, Short.MAX_VALUE))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblTitle)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtTitle, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblProductPrice)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtProductPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(lblMaterialAmmount)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtProductAmmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    							.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
	    							.addComponent(btnAddProduct))))
	    				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
	    				.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
	    				.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
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
	    					.addComponent(lblKits)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboKits, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblKitPrice)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtKitPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(lblProductAmmount)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtKitAmmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
	    					.addComponent(btnAddKit))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblMaterial)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblMaterialPrice)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtPriceMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(lblKitAmmount)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtMaterialAmmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
	    					.addComponent(btnAddMaterial))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblBruteValue)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtBruteValue, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblSuggestedPrice)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtSuggestedPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(lblDiscount)))
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING, false)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(txtDiscount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(lblFinalPrice)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtFinalPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    							.addGap(18)
	    							.addComponent(lblDataDeCriao)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtDate))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addGap(8)
	    							.addComponent(lblAliquot)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtAliquot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    							.addGap(18)
	    							.addComponent(lblValorBruto)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtAliquotPlusBruteValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    							.addGap(16)
	    							.addComponent(lblContribuition)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtContribuition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	    					.addGap(5)))
	    			.addContainerGap())
	    );
	    gl_principalPanel.setVerticalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblRastreabilityCode)
	    				.addComponent(txtRastreabilityCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblCnpj)
	    				.addComponent(cboCNPJ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
	    				.addComponent(btnAddProduct)
	    				.addComponent(lblMaterialAmmount)
	    				.addComponent(txtProductAmmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblKits)
	    				.addComponent(cboKits, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblKitPrice)
	    				.addComponent(txtKitPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(btnAddKit)
	    				.addComponent(lblProductAmmount)
	    				.addComponent(txtKitAmmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(20)
	    			.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblMaterial)
	    				.addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblMaterialPrice)
	    				.addComponent(txtPriceMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(btnAddMaterial)
	    				.addComponent(lblKitAmmount)
	    				.addComponent(txtMaterialAmmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
	    				.addComponent(txtBruteValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(txtContribuition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblAliquot)
	    				.addComponent(txtAliquot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblValorBruto)
	    				.addComponent(txtAliquotPlusBruteValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblContribuition))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblSuggestedPrice)
	    				.addComponent(txtSuggestedPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblDiscount)
	    				.addComponent(txtDiscount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblFinalPrice)
	    				.addComponent(txtFinalPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblDataDeCriao)
	    				.addComponent(txtDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addContainerGap(18, Short.MAX_VALUE))
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
    String[] materialTableHeader = new String[] {"Material", "Preço", "Quantidade"};
	    materialTable.setModel(new DefaultTableModel(null, materialTableHeader) {

			/**
			 * 
			 */
            private static final long serialVersionUID = 7250548222777451750L;
            
            boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
	    	
	    });
	    scrollPane_2.setViewportView(materialTable);
	    
	    kitTable = new JTable();
	    String[] kitTableHeader = new String[] {"Kit", "Preço", "Quantidade"};
	    kitTable.setModel(new DefaultTableModel(null, kitTableHeader) {

			/**
			 * 
			 */
            private static final long serialVersionUID = 278905383466848631L;
	    	
            boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
	    });
	    scrollPane_1.setViewportView(kitTable);
	    
	    productTable = new JTable();
	    String[] productTableHeader = new String[] {"Produto", "Preço", "Quantidade"};
	    productTable.setModel(new DefaultTableModel(null, productTableHeader) {

			/**
			 * 
			 */
            private static final long serialVersionUID = 1835899774627173017L;
	    	
            boolean[] columnEditables = new boolean[] {
					false, false, false
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
	    btnCancel.setIcon(new ImageIcon(PTCRegisterFrame.class.getResource("/resources/cancel.png")));
	    btnConfirm = new JButton("Confirmar");
	    btnConfirm.setIcon(new ImageIcon(PTCRegisterFrame.class.getResource("/resources/ok.png")));
	    btnClear = new JButton("Limpar");
	    btnClear.setIcon(new ImageIcon(PTCRegisterFrame.class.getResource("/resources/ClearFrame.png")));
	    
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
				else if(e.getSource().equals(btnConfirm))register();
			}
		};
		btnCancel.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		btnAddMaterial.addActionListener(buttonListener);
		btnAddProduct.addActionListener(buttonListener);
		btnAddKit.addActionListener(buttonListener);
		btnAddService.addActionListener(buttonListener);
		
		KeyListener tableKeyListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getSource().equals(materialTable) && e.getKeyCode() == KeyEvent.VK_DELETE)deleteRow(materialTable);
				else if(e.getSource().equals(productTable) && e.getKeyCode() == KeyEvent.VK_DELETE)deleteRow(productTable);
				else if(e.getSource().equals(kitTable)&& e.getKeyCode() == KeyEvent.VK_DELETE)deleteRow(kitTable);
				else if(e.getSource().equals(serviceTable) && e.getKeyCode() == KeyEvent.VK_DELETE)deleteRow(serviceTable);
			}
		};
		materialTable.addKeyListener(tableKeyListener);
		productTable.addKeyListener(tableKeyListener);
		kitTable.addKeyListener(tableKeyListener);
		serviceTable.addKeyListener(tableKeyListener);
		
		ActionListener cboListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(cboMaterial))showValueForMaterial();
				else if(e.getSource().equals(cboProduct))showValueForProduct();
				else if(e.getSource().equals(cboKits))showValueForKit();
			}
		};
		cboMaterial.addActionListener(cboListener);
		cboProduct.addActionListener(cboListener);
		cboKits.addActionListener(cboListener);
		
		FocusListener txtFocusLister = new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(e.getSource().equals(txtBruteValue))calculeBrutePrice();
				else if(e.getSource().equals(txtAliquotPlusBruteValue))calculateAliquot();
				else if(e.getSource().equals(txtFinalPrice))calculateFinalPrice();
				else if(e.getSource().equals(txtSuggestedPrice))calculateSuggestedPrice();
			}
		};
		txtBruteValue.addFocusListener(txtFocusLister);
		txtAliquotPlusBruteValue.addFocusListener(txtFocusLister);
		txtFinalPrice.addFocusListener(txtFocusLister);
		txtSuggestedPrice.addFocusListener(txtFocusLister);
	}
	
	private void calculateSuggestedPrice() {
		double contribuition = Double.parseDouble(txtContribuition.getText().replaceAll(",", "\\.").replaceAll("%", ""));
		double bruteValuePlusAliquot = Double.parseDouble(txtAliquotPlusBruteValue.getText().replaceAll(",", "\\."));
		double value = bruteValuePlusAliquot + (bruteValuePlusAliquot*(contribuition/100));
		DecimalFormat df = new DecimalFormat("0.00");
		txtSuggestedPrice.setText(df.format(value));
	}
	
	private void calculateFinalPrice() {
		double discont = Double.parseDouble(txtDiscount.getText().replaceAll(",", "\\.").replaceAll("%", ""));
		double suggestedPrice = Double.parseDouble(txtSuggestedPrice.getText().replaceAll(",", "\\."));
		double value = suggestedPrice -(suggestedPrice*(discont/100));
		DecimalFormat df = new DecimalFormat("0.00");
		txtFinalPrice.setText(df.format(value));
	}
	private void calculateAliquot() {
		double aliquot = Double.parseDouble(txtAliquot.getText().replaceAll(",", "\\.").replaceAll("%", ""));
		double bruteValue = Double.parseDouble(txtBruteValue.getText().replaceAll(",", "\\."));
		double value = bruteValue + (bruteValue*(aliquot/100));
		DecimalFormat df = new DecimalFormat("0.00");
		txtAliquotPlusBruteValue.setText(df.format(value));
	}
	
	private void calculeBrutePrice() {
		double material = controller.calculateTablePrice(materialTable);
		double product = controller.calculateTablePrice(productTable);
		double kit = controller.calculateTablePrice(kitTable);
		double total = material+product+kit;
		DecimalFormat df = new DecimalFormat("0.00");
		txtBruteValue.setText(String.valueOf(df.format(total)));
	}
	
	private void showValueForKit() {
		if(cboKits.getSelectedIndex() == -1)return;
		Kit k = (Kit) cboKits.getSelectedItem();
		int id = k.getId();
		double kitValue = controller.getProductListforKit(id);
		DecimalFormat df = new DecimalFormat("0.00");
		txtKitPrice.setText(df.format(kitValue));
	}
	
	private void showValueForProduct() {
		if(cboProduct.getSelectedIndex() == -1)return;
		Product p = (Product) cboProduct.getSelectedItem();
		int id = p.getId();
		List<Inventory> list = controller.getInventoryListForProduct(id);
		double total = 0.0;
		for (Inventory inventory : list) {
	        double value = inventory.getEntryValue();
	        if(value <0.1) {
	        	int  i = ShowMessage.questionMessage(this, "Erro", "Um dos materiais do produto não tem histórico de entrada no inventário, gostaria de continuar?");
	        	if(i == JOptionPane.NO_OPTION)return;
	        }
	        total += inventory.getEntryValue();
        }
		DecimalFormat df =new DecimalFormat("0.00");
		txtProductPrice.setText(String.valueOf(df.format(total)));
	}
	
	private void showValueForMaterial() {
		if(cboMaterial.getSelectedIndex() == -1)return;
		Material m = (Material) cboMaterial.getSelectedItem();
		int id = m.getId();
		Inventory i = controller.getInventory(id);
		if(i == null) {
			txtPriceMaterial.setText("");
			return;
		}
		txtPriceMaterial.setText(String.valueOf(i.getEntryValue()));
	}
	
	private void deleteRow(JTable table) {
		int i = table.getSelectedRow();
		if(i == -1)return;
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		tbl.removeRow(i);
	}
	
	
	private void clearFrame() {
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente limpar os campos da PTC ?");
		if(i == JOptionPane.YES_OPTION) {
			ClearFrame.clear(this);
			ClearFrame.clearTable(productTable);
			ClearFrame.clearTable(kitTable);
			ClearFrame.clearTable(materialTable);
			ClearFrame.clearTable(serviceTable);
			txtMaterialAmmount.setText("1");
			txtProductAmmount.setText("1");
			txtKitAmmount.setText("1");
		}
	}
	
	private void register() {
		int i = ShowMessage.questionMessage(this, "Registrar", "Deseja realmente registrar essa PTC ?");
		if(i == JOptionPane.NO_OPTION)return;
		boolean isOK = verifyFields();
		if(isOK) {
			String title = txtTitle.getText();
			Client client = (Client) cboClient.getSelectedItem();
			double bruteValue = Double.parseDouble(txtBruteValue.getText().replaceAll(",", "\\."));
			double aliquot = Double.parseDouble(txtAliquot.getText().replaceAll(",", "\\.").replaceAll("%", ""));
			double aliquotPlusBrute = Double.parseDouble(txtAliquotPlusBruteValue.getText().replaceAll(",", "\\."));
			double contribuition = Double.parseDouble(txtContribuition.getText().replaceAll(",", "\\.").replaceAll("%", ""));
			double suggestedPrice = Double.parseDouble(txtSuggestedPrice.getText().replaceAll(",", "\\."));
			double discount = Double.parseDouble(txtDiscount.getText().replaceAll(",", "\\.").replaceAll("%", ""));
			double finalPrice = Double.parseDouble(txtFinalPrice.getText().replaceAll(",", "\\."));
			List<Material> materialList = (List<Material>) controller.fillListOfComponents(materialTable);
			List<Product> productList = (List<Product>) controller.fillListOfComponents(productTable);
			List<Kit> kitList = (List<Kit>) controller.fillListOfComponents(kitTable);
			List<Service> serviceList = (List<Service>) controller.fillListOfComponents(serviceTable);
			CNPJ cnpj = (CNPJ) cboCNPJ.getSelectedItem();
			String rastreabilityCode = txtRastreabilityCode.getText();
			Date date = (Date) txtDate.getValue();
			
			PTC ptc = new PTC(title, suggestedPrice, aliquot, bruteValue, aliquotPlusBrute, discount, finalPrice, client,contribuition, date);
			ptc.setKitList(kitList);
			ptc.setMaterialList(materialList);
			ptc.setProductList(productList);
			ptc.setServiceList(serviceList);
			ptc.setCnpj(cnpj);
			ptc.setRastreabilityCode(rastreabilityCode);
			
			
			controller.register(ptc);
			ShowMessage.successMessage(this, "Sucesso", "Registro de P.T.C realizado com sucesso!");
			ClearFrame.clear(this);
			ClearFrame.clearTable(kitTable);
			ClearFrame.clearTable(materialTable);
			ClearFrame.clearTable(productTable);
			ClearFrame.clearTable(serviceTable);
			
			txtMaterialAmmount.setText("1");
			txtProductAmmount.setText("1");
			txtKitAmmount.setText("1");
		}
	}
	
	
	private boolean verifyFields() {
	    String title = txtTitle.getText();
	    if(title.isEmpty()) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira o título da P.T.C!");
	    	return false;
	    }
	    if(cboClient.getSelectedIndex() == -1) {
	    	ShowMessage.errorMessage(this, "Erro", "Selecione para qual cliente é a P.T.C!");
	    	return false;
	    }
	    double bruteValue = Double.parseDouble(txtBruteValue.getText().replaceAll(",", "."));
	    if(bruteValue<0.1) {
	    	ShowMessage.errorMessage(this, "Erro", "Verifique o valor bruto!");
	    	return false;
	    }
	    double aliquot = Double.parseDouble(txtAliquot.getText().replaceAll(",", "\\.").replaceAll("%", ""));
	    if(aliquot <= 0) {
	    	ShowMessage.errorMessage(this, "Erro", "Verifique o valor da aliquota!");
	    	return false;
	    }
	    double aliquotPlusBrute = Double.parseDouble(txtAliquotPlusBruteValue.getText().replaceAll(",", "\\."));
	    if(aliquotPlusBrute<0.1) {
	    	ShowMessage.errorMessage(this, "Erro", "Verifique o valor da aliquota+bruto");
	    	return false;
	    }
	    double contribuition = Double.parseDouble(txtContribuition.getText().replaceAll(",", "\\.").replaceAll("%", ""));
	    if(contribuition <=0) {
	    	ShowMessage.errorMessage(this, "Erro", "Verique a margem de contribuição!");
	    	return false;
	    }
	    double suggestedPrice = Double.parseDouble(txtSuggestedPrice.getText().replaceAll(",", "\\."));
	    if(suggestedPrice<0.1) {
	    	ShowMessage.errorMessage(this, "Erro", "Verique o preço sugerido!");
	    	return false;
	    }
	    double discount = Double.parseDouble(txtDiscount.getText().replaceAll(",", "\\.").replaceAll("%", ""));
	    if(discount <= 0) {
	    	ShowMessage.errorMessage(this, "Erro", "Verifique o desconto!");
	    	return false;
	    }
	    double finalValue = Double.parseDouble(txtFinalPrice.getText().replaceAll(",", "\\."));
	    if(finalValue <0.1) {
	    	ShowMessage.questionMessage(this, "Erro", "Verifique o valor de Saída!");
	    	return false;
	    }
	    if(cboCNPJ.getSelectedIndex() == -1) {
	    	ShowMessage.errorMessage(this, "Erro", "Selecione por qual CNPJ está realizando está P.T.C?");
	    	return false;
	    }
	    if(txtDate.getValue() == null) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira a data de criação da P.T.C");
	    	return false;
	    }
		return true;
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
		if(txtMaterialAmmount.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira a quantidade de material!");
			return;
		}
		
		controller.insertMaterial((Material) cboMaterial.getSelectedItem(),Double.parseDouble(txtPriceMaterial.getText().replaceAll("R|\\$", "").replaceAll(",","\\.").trim()), Integer.parseInt(txtMaterialAmmount.getText()), materialTable);
	}
	
	
	private void insertProduct() {
		if(cboProduct.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Selecione o Produto", "Selecione o Produto para adicionar a P.T.C");				
			return;
		}
		if(txtProductPrice.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o valor do produto!");
			return;
		}
		if(txtProductAmmount.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira a quantidade do produto");
			return;
		}
		controller.insertProduct((Product) cboProduct.getSelectedItem(),Double.parseDouble(txtProductPrice.getText().replaceAll("R|\\$", "").replaceAll(",",	 "\\.").trim()),Integer.parseInt(txtProductAmmount.getText()), productTable);
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
		if(txtKitPrice.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira a quantidade de kit");
			return;
		}
		
		controller.insertKit((Kit) cboKits.getSelectedItem(),Double.parseDouble(txtKitPrice.getText().replaceAll("R|\\$", "").replaceAll(",",	 "\\.").trim()), Integer.parseInt(txtKitAmmount.getText()), kitTable);
	}
	
	private void insertService() {
		if(cboService.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Selecione o Serviço", "Selecione o Serviço para adicionar a P.T.C");				
			return;
		}
		
		controller.insertService((Service) cboService.getSelectedItem(), serviceTable);
	}
}
