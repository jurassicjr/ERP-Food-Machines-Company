package sales.view;

import java.awt.BorderLayout;
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
import java.util.Date;
import java.util.List;

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
import javax.swing.table.DefaultTableModel;

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
import sales.controller.PTCUpdateController;
import userInterface.components.ComboBoxAutoCompletion;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class PTCUpdateFrame extends JFrame{

	/**
	 * 
	 */
    private static final long serialVersionUID = -8102259736461804030L;
	private JTextField txtRastreabilityCode;
	private JTextField txtTitle;
	private JTextField txtProductPrice;
	private JTextField txtProductAmmount;
	private JTextField txtPath;
	private JTextField txtBrutePrice;
	private JTextField txtBrutePricePlusAliquot;
	private JTextField txtKitPrice;
	private JTextField txtKitAmmount;
	private JTextField txtMaterialPrice;
	private JTextField txtMaterialAmmount;
	private JTextField txtSuggestedPrice;
	private JTextField txtFinalPrice;

	private JTextField txtAliquot;
	private JTextField txtContribuition;
	private JTextField txtDiscount;
	
	private JTable productTable;
	private JTable materialTable;
	private JTable kitTable;
	private JTable serviceTable;
	private JPanel principalPanel;
	private JLabel lblProduct;
	private JComboBox<Product> cboProduct;
	private JLabel lblRastreabilityCode;
	private JLabel lblClient;
	private JComboBox<Client> cboClient;
	private JLabel lblCNPJ;
	private JComboBox<CNPJ> cboCNPJ;
	private JLabel lblTitle;
	private JLabel lblProductPrice;
	private JLabel lblProductAmmount;
	private JButton btnAddProduct;
	private JLabel lblService;
	private JComboBox<Service> cboService;
	private JButton btnAddService;
	private JLabel lblUpload;
	private JButton btnPath;
	private JLabel lblBrutePrice;
	private JLabel lblAliquot;
	private JLabel lblBrutePricePlusAliquot;
	private JLabel lblKit;
	private JComboBox<Kit> cboKit;
	private JLabel lblKitPrice;
	private JLabel lblKitAmmount;
	private JButton btnAddKit;
	private JLabel lblMaterial;
	private JComboBox<Material> cboMaterial;
	private JLabel lblMaterialPrice;
	private JLabel lblMaterialAmmount;
	private JButton btnAddMaterial;
	private JLabel lblContribuition;
	private JLabel suggestedPrice;
	private JLabel lblDiscount;
	private JLabel lblFinalPrice;
	private JLabel lblPtc;
	private JComboBox<PTC> cboPTC;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	private JPanel subPanel;
	private PTCUpdateController controller;
	private JLabel lblCreationDate;
	private DateField txtCreationDate;

    public PTCUpdateFrame() {
    	controller = new PTCUpdateController(this);
    	initialize();
    	setListener();
    }
    
    public PTCUpdateFrame(PTC ptc) {
    	controller = new PTCUpdateController(this);
    	initialize();
    	setListener();
    	fillFields(ptc);
    }

	private void initialize() {
	    setTitle("Atualização de P.T.C");
	    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    setBounds(100, 100, 756, 767);
	    setMinimumSize(new Dimension(756,767));
	    setPreferredSize(new Dimension(756, 767));
	    Icon.setIcon(this);
	    getContentPane().setLayout(new BorderLayout(0, 0));
	    initializePrincipal();
    }

	private void initializePrincipal() {
	    principalPanel = new JPanel();
	    getContentPane().add(principalPanel, BorderLayout.CENTER);
	    
	    JScrollPane scrollPane = new JScrollPane();
	    
	    lblProduct = new JLabel("Produtos");
	    
	    cboProduct = new JComboBox<Product>();
	    controller.fillProduct(cboProduct);
	    new ComboBoxAutoCompletion(cboProduct);
	    lblRastreabilityCode = new JLabel("Numero de Ratreabilidade");
	    
	    txtRastreabilityCode = new JTextField();
	    txtRastreabilityCode.setEnabled(false);
	    txtRastreabilityCode.setColumns(10);
	    
	    lblClient = new JLabel("Cliente");
	    
	    cboClient = new JComboBox<Client>();
	    controller.fillClient(cboClient, false);
	    new ComboBoxAutoCompletion(cboClient);
	    
	    lblCNPJ = new JLabel("CNPJ");
	    
	    cboCNPJ = new JComboBox<CNPJ>();
	    controller.fillCnpj(cboCNPJ);;
	    new ComboBoxAutoCompletion(cboCNPJ);
	    
	    lblTitle = new JLabel("Título");
	    
	    txtTitle = new JTextField();
	    txtTitle.setColumns(10);
	    
	    lblProductPrice = new JLabel("Preço");
	    
	    txtProductPrice = new JTextField();
	    txtProductPrice.setColumns(10);
	    
	    lblProductAmmount = new JLabel("Quantidade");
	    
	    txtProductAmmount = new JTextField();
	    txtProductAmmount.setText("1");
	    txtProductAmmount.setColumns(10);
	    
	    btnAddProduct = new JButton("Adicionar");
	    
	    JScrollPane scrollPane_1 = new JScrollPane();
	    
	    JScrollPane scrollPane_2 = new JScrollPane();
	    
	    JScrollPane scrollPane_3 = new JScrollPane();
	    
	    lblService = new JLabel("Serviço");
	    
	    cboService = new JComboBox<Service>();
	    controller.fillService(cboService);
	    new ComboBoxAutoCompletion(cboService);
	    
	    btnAddService = new JButton("Adicionar");
	    
	    lblUpload = new JLabel("UPLOAD");
	    
	    txtPath = new JTextField();
	    txtPath.setColumns(10);
	    
	    btnPath = new JButton("Caminho");
	    
	    lblBrutePrice = new JLabel("Valor Bruto da P.T.C");
	    
	    txtBrutePrice = new JTextField();
	    txtBrutePrice.setColumns(10);
	    
	    lblAliquot = new JLabel("Alíquota");

        txtAliquot = new JTextField();//new JFormattedTextField(new MaskFormatter("##.##%"));
        
	   
	    lblBrutePricePlusAliquot = new JLabel("Valor Bruto + Alíquota");
	    
	    txtBrutePricePlusAliquot = new JTextField();
	    txtBrutePricePlusAliquot.setColumns(10);
	    
	    lblKit = new JLabel("Kits");
	    
	    cboKit = new JComboBox<Kit>();
	    controller.fillKit(cboKit);
	    new ComboBoxAutoCompletion(cboKit);
	    
	    lblKitPrice = new JLabel("Preço");
	    
	    txtKitPrice = new JTextField();
	    txtKitPrice.setColumns(10);
	    
	    lblKitAmmount = new JLabel("Quantidade");
	    
	    txtKitAmmount = new JTextField();
	    txtKitAmmount.setText("1");
	    txtKitAmmount.setColumns(10);
	    
	    btnAddKit = new JButton("Adicionar");
	    
	    lblMaterial = new JLabel("Material");
	    
	    cboMaterial = new JComboBox<Material>();
	    controller.fillMaterials(cboMaterial);
	    new ComboBoxAutoCompletion(cboMaterial);
	    
	    lblMaterialPrice = new JLabel("Preço");
	    
	    txtMaterialPrice = new JTextField();
	    txtMaterialPrice.setColumns(10);
	    
	    lblMaterialAmmount = new JLabel("Quantidade");
	    
	    txtMaterialAmmount = new JTextField();
	    txtMaterialAmmount.setText("1");
	    txtMaterialAmmount.setColumns(10);
	    
	    btnAddMaterial = new JButton("Adicionar");
	    
	    lblContribuition = new JLabel("Margem de Contribuição");
	    
	    txtContribuition = new JTextField();//new JFormattedTextField(new MaskFormatter("##.##%"));
        
	    
	    suggestedPrice = new JLabel("Valor Sugerido");
	    
	    txtSuggestedPrice = new JTextField();
	    txtSuggestedPrice.setColumns(10);
	    
	    lblDiscount = new JLabel("Desconto");
	    
	    txtDiscount = new JTextField();//new JFormattedTextField(new MaskFormatter("##.##%"));
        
	    
	    lblFinalPrice = new JLabel("Valor Final");
	    
	    txtFinalPrice = new JTextField();
	    txtFinalPrice.setColumns(10);
	    
	    lblPtc = new JLabel("PTC");
	    
	    cboPTC = new JComboBox<PTC>();
	    controller.fillPTC(cboPTC);
	    
	    lblCreationDate = new JLabel("Data");
	    
	    txtCreationDate = CalendarFactory.createDateField();
	    
	    GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	    gl_principalPanel.setHorizontalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblClient, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(cboClient, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblProduct, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(cboProduct, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblProductPrice, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(txtProductPrice, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
	    					.addGap(10)
	    					.addComponent(lblProductAmmount, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(txtProductAmmount, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
	    					.addGap(7)
	    					.addComponent(btnAddProduct, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
	    				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 720, GroupLayout.PREFERRED_SIZE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblKit, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(cboKit, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblKitPrice, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(txtKitPrice, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
	    					.addGap(10)
	    					.addComponent(lblKitAmmount, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(txtKitAmmount, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
	    					.addGap(14)
	    					.addComponent(btnAddKit, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
	    				.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 720, GroupLayout.PREFERRED_SIZE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblMaterial, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblMaterialPrice, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(txtMaterialPrice, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
	    					.addGap(10)
	    					.addComponent(lblMaterialAmmount, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(txtMaterialAmmount, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
	    					.addGap(21)
	    					.addComponent(btnAddMaterial, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
	    				.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 720, GroupLayout.PREFERRED_SIZE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblService, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(cboService, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(btnAddService, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
	    				.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 720, GroupLayout.PREFERRED_SIZE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblUpload, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(txtPath, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
	    					.addGap(10)
	    					.addComponent(btnPath, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblBrutePrice, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(txtBrutePrice, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblAliquot, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(txtAliquot, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblBrutePricePlusAliquot, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(txtBrutePricePlusAliquot, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(lblCreationDate)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtCreationDate, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblContribuition, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(txtContribuition, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(suggestedPrice, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(txtSuggestedPrice, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblDiscount, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
	    					.addGap(4)
	    					.addComponent(txtDiscount, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblFinalPrice, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtFinalPrice, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING, false)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblPtc)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(cboPTC, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblRastreabilityCode, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
	    							.addGap(4)
	    							.addComponent(txtRastreabilityCode, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)))
	    					.addGap(18)
	    					.addComponent(lblCNPJ, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboCNPJ, 0, 348, Short.MAX_VALUE)))
	    			.addContainerGap())
	    );
	    gl_principalPanel.setVerticalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap(13, Short.MAX_VALUE)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblPtc)
	    				.addComponent(cboPTC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(3)
	    					.addComponent(lblRastreabilityCode))
	    				.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    					.addComponent(txtRastreabilityCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    					.addComponent(lblCNPJ))
	    				.addComponent(cboCNPJ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(3)
	    					.addComponent(lblClient))
	    				.addComponent(cboClient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(3)
	    					.addComponent(lblTitle))
	    				.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(5)
	    					.addComponent(lblProduct))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(2)
	    					.addComponent(cboProduct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(5)
	    					.addComponent(lblProductPrice))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(2)
	    					.addComponent(txtProductPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(5)
	    					.addComponent(lblProductAmmount))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(2)
	    					.addComponent(txtProductAmmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    				.addComponent(btnAddProduct, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(5)
	    					.addComponent(lblKit))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(2)
	    					.addComponent(cboKit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(5)
	    					.addComponent(lblKitPrice))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(2)
	    					.addComponent(txtKitPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(5)
	    					.addComponent(lblKitAmmount))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(2)
	    					.addComponent(txtKitAmmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    				.addComponent(btnAddKit, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
	    			.addGap(20)
	    			.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(5)
	    					.addComponent(lblMaterial))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(2)
	    					.addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(5)
	    					.addComponent(lblMaterialPrice))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(2)
	    					.addComponent(txtMaterialPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(5)
	    					.addComponent(lblMaterialAmmount))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(2)
	    					.addComponent(txtMaterialAmmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    				.addComponent(btnAddMaterial, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
	    			.addGap(20)
	    			.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
	    			.addGap(11)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(5)
	    					.addComponent(lblService))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(2)
	    					.addComponent(cboService, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    				.addComponent(btnAddService, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
	    			.addGap(11)
	    			.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(5)
	    					.addComponent(lblUpload))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(2)
	    					.addComponent(txtPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    				.addComponent(btnPath, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(3)
	    					.addComponent(lblBrutePrice))
	    				.addComponent(txtBrutePrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(3)
	    					.addComponent(lblAliquot))
	    				.addComponent(txtAliquot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(3)
	    					.addComponent(lblBrutePricePlusAliquot))
	    				.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    					.addComponent(txtBrutePricePlusAliquot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    					.addComponent(lblCreationDate)
	    					.addComponent(txtCreationDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(3)
	    					.addComponent(lblContribuition))
	    				.addComponent(txtContribuition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(3)
	    					.addComponent(suggestedPrice))
	    				.addComponent(txtSuggestedPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(3)
	    					.addComponent(lblDiscount))
	    				.addComponent(txtDiscount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(3)
	    					.addComponent(lblFinalPrice))
	    				.addComponent(txtFinalPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addContainerGap())
	    );
	    
	    serviceTable = new JTable();
	    String[] serviceTableHeader = new String[] {"Serviço", "Descrição"};
	    serviceTable.setModel(new DefaultTableModel(null, serviceTableHeader) {

			/**
			 * 
			 */
            private static final long serialVersionUID = -5534215681959839116L;
	    	
            boolean[] columnEditables = new boolean[] {
					false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
	    	
	    });
	    scrollPane_3.setViewportView(serviceTable);
	    
	    kitTable = new JTable();
	    String[] kitTableHeader = new String[] {"Kit", "Preço", "Quantidade"};
	    kitTable.setModel(new DefaultTableModel(null, kitTableHeader) {

			/**
			 * 
			 */
            private static final long serialVersionUID = 1886829355003930076L;
	    	
            boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
	    	
	    });
	    scrollPane_1.setViewportView(kitTable);
	    
	    materialTable = new JTable();
	    String[] materialTableHeader = new String[] {"Material", "Preço", "Quantidade"};
	    materialTable.setModel(new DefaultTableModel(null, materialTableHeader) {

			/**
			 * 
			 */
            private static final long serialVersionUID = -6309280774821381870L;
	    	
            boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
	    	
	    });
	    scrollPane_2.setViewportView(materialTable);
	    
	    productTable = new JTable();
	    String[] productTableHeader = new String[] {"Produto", "Preço", "Quantidade"};
	    productTable.setModel(new DefaultTableModel(null, productTableHeader) {

			/**
			 * 
			 */
            private static final long serialVersionUID = -2357255531085341843L;
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
	    subPanel = new JPanel();
	    
	    subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(subPanel, BorderLayout.SOUTH);
	    
	    btnConfirm = new JButton("Confirmar");
	    btnConfirm.setIcon(new ImageIcon(PTCUpdateFrame.class.getResource("/resources/ok.png")));
	    btnCancel = new JButton("Cancelar");
	    btnCancel.setIcon(new ImageIcon(PTCUpdateFrame.class.getResource("/resources/cancel.png")));
	    btnClear = new JButton("Limpar");
	    btnClear.setIcon(new ImageIcon(PTCUpdateFrame.class.getResource("/resources/ClearFrame.png")));
	    
	    subPanel.add(btnClear);
	    subPanel.add(btnCancel);
	    subPanel.add(btnConfirm);
    }
	
	private void setListener() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.close();
			}
		});
		
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnConfirm))update();
				else if(e.getSource().equals(btnCancel))controller.close();
				else if(e.getSource().equals(btnClear))clearFrame();
				else if(e.getSource().equals(btnAddMaterial))insertMaterial();
				else if(e.getSource().equals(btnAddProduct))insertProduct();
				else if(e.getSource().equals(btnAddKit))insertKit();
				else if(e.getSource().equals(btnAddService))insertService();
			}
		};
		btnConfirm.addActionListener(buttonListener);
		btnCancel.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		btnAddMaterial.addActionListener(buttonListener);
		btnAddProduct.addActionListener(buttonListener);
		btnAddKit.addActionListener(buttonListener);
		btnAddService.addActionListener(buttonListener);
		
		ActionListener cboListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(cboMaterial))showValueForMaterial();
				else if(e.getSource().equals(cboProduct))showValueForProduct();
				else if(e.getSource().equals(cboKit))showValueForKit();
				else if(e.getSource().equals(cboPTC))fillFields();
			}
		};
		cboMaterial.addActionListener(cboListener);
		cboProduct.addActionListener(cboListener);
		cboKit.addActionListener(cboListener);
		cboPTC.addActionListener(cboListener);
		
		FocusListener txtFocusLister = new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(e.getSource().equals(txtBrutePrice))calculeBrutePrice();
				else if(e.getSource().equals(txtBrutePricePlusAliquot))calculateAliquot();
				else if(e.getSource().equals(txtFinalPrice))calculateFinalPrice();
				else if(e.getSource().equals(txtSuggestedPrice))calculateSuggestedPrice();
			}
		};
		txtBrutePrice.addFocusListener(txtFocusLister);
		txtBrutePricePlusAliquot.addFocusListener(txtFocusLister);
		txtFinalPrice.addFocusListener(txtFocusLister);
		txtSuggestedPrice.addFocusListener(txtFocusLister);
		
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
	}
	
	private void update() {
		int i = ShowMessage.questionMessage(this, "ATUALIZAR", "Deseja realmente atualizar essa PTC ?");
		if(i == JOptionPane.NO_OPTION)return;
		boolean isOK = verifyFields();
		if(isOK) {
			String title = txtTitle.getText();
			Client client = (Client) cboClient.getSelectedItem();
			double bruteValue = Double.parseDouble(txtBrutePrice.getText().replaceAll(",", "\\."));
			double aliquot = Double.parseDouble(txtAliquot.getText().replaceAll(",", "\\.").replaceAll("%", ""));
			double aliquotPlusBrute = Double.parseDouble(txtBrutePricePlusAliquot.getText().replaceAll(",", "\\."));
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
			Date date = (Date) txtCreationDate.getValue();
			
			PTC ptc = (PTC) cboPTC.getSelectedItem();
			ptc.setAliquot(aliquot);
			ptc.setAliquotPlusBrute(aliquotPlusBrute);
			ptc.setBrutePrice(bruteValue);
			ptc.setContribuition(contribuition);
			ptc.setCreationDate(date);
			ptc.setDiscount(discount);
			ptc.setFinalPrice(finalPrice);
			ptc.setSuggestedPrice(suggestedPrice);
			ptc.setTitle(title);
			ptc.setClient(client);
			
			ptc.setKitList(kitList);
			ptc.setMaterialList(materialList);
			ptc.setProductList(productList);
			ptc.setServiceList(serviceList);
			ptc.setCnpj(cnpj);
			ptc.setRastreabilityCode(rastreabilityCode);
			
			
			controller.update(ptc);
			ShowMessage.successMessage(this, "Sucesso", "Registro de P.T.C realizado com sucesso!");
			ClearFrame.clear(this);
			ClearFrame.clearTable(kitTable);
			ClearFrame.clearTable(materialTable);
			ClearFrame.clearTable(productTable);
			ClearFrame.clearTable(serviceTable);
			cboPTC.removeAllItems();
			controller.fillPTC(cboPTC);
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
		    double bruteValue = Double.parseDouble(txtBrutePrice.getText().replaceAll(",", "."));
		    if(bruteValue<0.1) {
		    	ShowMessage.errorMessage(this, "Erro", "Verifique o valor bruto!");
		    	return false;
		    }
		    double aliquot = Double.parseDouble(txtAliquot.getText().replaceAll(",", "\\.").replaceAll("%", ""));
		    if(aliquot <= 0) {
		    	ShowMessage.errorMessage(this, "Erro", "Verifique o valor da aliquota!");
		    	return false;
		    }
		    double aliquotPlusBrute = Double.parseDouble(txtBrutePricePlusAliquot.getText().replaceAll(",", "\\."));
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
		    if(txtCreationDate.getValue() == null) {
		    	ShowMessage.errorMessage(this, "Erro", "Insira a data da atualização da P.T.C");
		    	return false;
		    }
		    if(cboPTC.getSelectedIndex() == -1) {
		    	ShowMessage.errorMessage(this, "Erro", "Selecione a PTC que está modificando");
		    	return false;
		    }
			return true;
	    }		
	
	private void clearFrame() {
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente limpar os campos da atualização de P.T.C");
		if(i == JOptionPane.YES_OPTION) {
			ClearFrame.clear(this);
			ClearFrame.clearTable(productTable);
			ClearFrame.clearTable(kitTable);
			ClearFrame.clearTable(serviceTable);
			ClearFrame.clearTable(materialTable);
		}
	}
	
	private void calculateSuggestedPrice() {
		double contribuition = Double.parseDouble(txtContribuition.getText().replaceAll(",", "\\.").replaceAll("%", ""));
		double bruteValuePlusAliquot = Double.parseDouble(txtBrutePricePlusAliquot.getText().replaceAll(",", "\\."));
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
		double bruteValue = Double.parseDouble(txtBrutePrice.getText().replaceAll(",", "\\."));
		double value = bruteValue + (bruteValue*(aliquot/100));
		DecimalFormat df = new DecimalFormat("0.00");
		txtBrutePricePlusAliquot.setText(df.format(value));
	}
	
	private void calculeBrutePrice() {
		double material = controller.calculateTablePrice(materialTable);
		double product = controller.calculateTablePrice(productTable);
		double kit = controller.calculateTablePrice(kitTable);
		double total = material+product+kit;
		DecimalFormat df = new DecimalFormat("0.00");
		txtBrutePrice.setText(String.valueOf(df.format(total)));
	}
	
	private void showValueForKit() {
		if(cboKit.getSelectedIndex() == -1)return;
		Kit k = (Kit) cboKit.getSelectedItem();
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
			txtMaterialPrice.setText("");
			return;
		}
		txtMaterialPrice.setText(String.valueOf(i.getEntryValue()));
	}
	
	private void deleteRow(JTable table) {
		int i = table.getSelectedRow();
		if(i == -1)return;
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		tbl.removeRow(i);
	}
	
	private void insertMaterial() {
		if(cboMaterial.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Selecione o Material", "Selecione o material para adicionar a P.T.C");				
			return;
		}
		if(txtMaterialPrice.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o valor do material");
			return;
		}
		if(txtMaterialAmmount.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira a quantidade de material!");
			return;
		}
		
		controller.insertMaterial((Material) cboMaterial.getSelectedItem(),Double.parseDouble(txtMaterialPrice.getText().replaceAll("R|\\$", "").replaceAll(",","\\.").trim()), Integer.parseInt(txtMaterialAmmount.getText()), materialTable);
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
		if(cboKit.getSelectedIndex() == -1) {
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
		
		controller.insertKit((Kit) cboKit.getSelectedItem(),Double.parseDouble(txtKitPrice.getText().replaceAll("R|\\$", "").replaceAll(",",	 "\\.").trim()), Integer.parseInt(txtKitAmmount.getText()), kitTable);
	}
	
	private void insertService() {
		if(cboService.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Selecione o Serviço", "Selecione o Serviço para adicionar a P.T.C");				
			return;
		}
		
		controller.insertService((Service) cboService.getSelectedItem(), serviceTable);
	}
	
	private void fillFields() {
		if(cboPTC.getSelectedIndex() == -1)return;
		PTC p = (PTC) cboPTC.getSelectedItem();
		ClearFrame.clearTable(kitTable);
		ClearFrame.clearTable(materialTable);
		ClearFrame.clearTable(productTable);
		ClearFrame.clearTable(serviceTable);
		txtAliquot.setText(String.valueOf(p.getAliquot()));
		txtBrutePrice.setText(String.valueOf(p.getBrutePrice()));
		txtBrutePricePlusAliquot.setText(String.valueOf(p.getAliquotPlusBrute()));
		txtContribuition.setText(String.valueOf(p.getContribuition()));
		txtDiscount.setText(String.valueOf(p.getDiscount()));
		txtFinalPrice.setText(String.valueOf(p.getFinalPrice()));
		txtSuggestedPrice.setText(String.valueOf(p.getSuggestedPrice()));
		cboCNPJ.setSelectedItem(p.getCnpj());
		cboClient.setSelectedItem(p.getClient());
		String code = p.getRastreabilityCode();
		char change = code.charAt(6);
		int ascii = change;
		ascii += 1;
		change = (char) ascii;
		code = code.substring(0, 6);
		code = code + change;
		txtRastreabilityCode.setText(code);
		txtTitle.setText(p.getTitle());
		addRowtoMaterialTable(materialTable, p.getMaterialList());
		addRowtoProductTable(productTable, p.getProductList());
		addRowtoKitTable(kitTable, p.getKitList());
		addRowtoServiceTable(serviceTable, p.getServiceList());
	}

	private void fillFields(PTC p) {
		ClearFrame.clear(this);
		ClearFrame.clearTable(kitTable);
		ClearFrame.clearTable(materialTable);
		ClearFrame.clearTable(productTable);
		ClearFrame.clearTable(serviceTable);
		txtAliquot.setText(String.valueOf(p.getAliquot()));
		txtBrutePrice.setText(String.valueOf(p.getBrutePrice()));
		txtBrutePricePlusAliquot.setText(String.valueOf(p.getAliquotPlusBrute()));
		txtContribuition.setText(String.valueOf(p.getContribuition()));
		txtDiscount.setText(String.valueOf(p.getDiscount()));
		txtFinalPrice.setText(String.valueOf(p.getFinalPrice()));
		txtSuggestedPrice.setText(String.valueOf(p.getSuggestedPrice()));
		cboCNPJ.setSelectedItem(p.getCnpj());
		cboClient.setSelectedItem(p.getClient());
		String code = p.getRastreabilityCode();
		txtRastreabilityCode.setText(code);
		txtTitle.setText(p.getTitle());
		addRowtoMaterialTable(materialTable, p.getMaterialList());
		addRowtoProductTable(productTable, p.getProductList());
		addRowtoKitTable(kitTable, p.getKitList());
		addRowtoServiceTable(serviceTable, p.getServiceList());
		txtAliquot.setEnabled(false);
		txtBrutePrice.setEnabled(false);
		txtBrutePricePlusAliquot.setEnabled(false);
		txtContribuition.setEnabled(true);
		txtCreationDate.setEnabled(false);
		txtDiscount.setEnabled(false);
		txtFinalPrice.setEnabled(false);
		txtKitAmmount.setEnabled(false);
		txtKitPrice.setEnabled(false);
		txtMaterialAmmount.setEnabled(false);
		txtMaterialPrice.setEnabled(false);
		txtPath.setEnabled(false);
		txtProductAmmount.setEnabled(false);
		txtProductPrice.setEnabled(false);
		txtRastreabilityCode.setEnabled(false);
		txtSuggestedPrice.setEnabled(false);
		txtTitle.setEnabled(false);
		cboClient.setEnabled(false);
		cboCNPJ.setEnabled(false);
		cboKit.setEnabled(false);
		cboMaterial.setEnabled(false);
		cboProduct.setEnabled(false);
		cboPTC.setEnabled(false);
		cboService.setEnabled(false);
		btnAddKit.setEnabled(false);
		btnAddMaterial.setEnabled(false);
		btnAddProduct.setEnabled(false);
		btnAddService.setEnabled(false);
		btnClear.setEnabled(false);
		btnConfirm.setEnabled(false);
		btnPath.setEnabled(false);
    }

	private void addRowtoServiceTable(JTable table, List<Service> serviceList) {
	    for (Service s : serviceList) {
	    	((DefaultTableModel) table.getModel()).addRow(new Object[] { null, null, null });
			int row = table.getRowCount() - 1;
			table.setValueAt(s, row, 0);
			table.setValueAt(s.getObservation(), row, 1);
        }
    }

	private void addRowtoKitTable(JTable table, List<Kit> kitList) {
	    for (Kit k : kitList) {
	    	((DefaultTableModel) table.getModel()).addRow(new Object[] { null, null, null });
			DecimalFormat df = new DecimalFormat("0.00");
			int row = table.getRowCount() - 1;
			table.setValueAt(k, row, 0);
			table.setValueAt(df.format(k.getAuxPrice()), row, 1);
			table.setValueAt(k.getAuxAmmount(), row, 2);
        }
    }

	private void addRowtoProductTable(JTable table, List<Product> productList) {
	    for (Product p : productList) {
	    	((DefaultTableModel) table.getModel()).addRow(new Object[] { null, null, null });
			DecimalFormat df = new DecimalFormat("0.00");
			int row = table.getRowCount() - 1;
			table.setValueAt(p, row, 0);
			table.setValueAt(df.format(p.getAuxPrice()), row, 1);
			table.setValueAt(p.getAuxAmmount(), row, 2);
        }
    }

	private void addRowtoMaterialTable(JTable table, List<Material> list) {
	    for (Material o : list) {
	    	((DefaultTableModel) table.getModel()).addRow(new Object[] { null, null, null });
			DecimalFormat df = new DecimalFormat("0.00");
			int row = table.getRowCount() - 1;
			table.setValueAt(o, row, 0);
			table.setValueAt(df.format(o.getAuxPrice()), row, 1);
			table.setValueAt(o.getAuxAmmount(), row, 2);
        }
    }
}
