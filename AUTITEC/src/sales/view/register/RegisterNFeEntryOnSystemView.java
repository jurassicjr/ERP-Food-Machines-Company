package sales.view.register;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import model.CFOPExit;
import model.Material;
import model.Nfe;
import model.NfeMaterialRelation;
import model.PurchaseOrder;
import model.Supplier;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.RegisterNFeEntryOnSystemController;
import userInterface.components.ComboBoxAutoCompletion;
import userInterface.components.JNumberFormatField;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;
import core.City;
import core.State;
import database.FillStateAndCity;

public class RegisterNFeEntryOnSystemView extends JFrame{
    
	private static final long serialVersionUID = -6272240881146741L;
	private JPanel principalPanel;
	private JLabel lblFiscalNoteData;

	private JTextField txtAccessKey;
	private JTextField txtExitHour;
	private JTextField txtNfeModel;
	private JTextField txtDanfeSerial;
	private JTextField txtFiscalNumber;
	private JTextField txtCFOP;
	private JTextField txtFreightCnpj;
	private JTextField txtMaterialCod;
	private JTextField txtAmmount;
	private JTextField txtCompanyName;
	private JTextField txtTotalValue;
	private JTextField txtMaterialCfop;
	private JTextField txtCstIcms;
	private JTextField txtAliquotIcms;
	private JTextField txtStreet;
	private JTextField txtCalculatedBaseIcms;
	private JTextField txtTaxIcms;
	private JTextField txtCstIpi;
	private JTextField txtAliquotIpi;
	private JTextField txtCalculatedBaseIpi;
	private JTextField txtTaxIpi;
	private JTextField txtCstCofins;
	private JTextField txtAliquotCofins;
	private JTextField txtCalculatedBaseCofins;
	private JTextField txtTaxCofins;
	private JTextField txtCstPis;
	private JTextField txtAliquotPis;
	private JTextField txtCalculatedBasePis;
	private JTextField txtTaxPis;
	private JTextField txtNcm;
	private JTextField txtFinalCost;
	private JTextField txtFinalUnitCost;
	private JTextField txtSalesPercent;
	private JTextField txtFinalSalesValue;
	private JTextField txtANTTCode;
	
	private DateField txtExitDate;
	
	private JLabel lblDanfeModel;
	private JLabel lblDanfeSerial;
	private JLabel lblFiscalNumber;
	private JLabel lblEmissionDate;
	private JLabel lblExitDate;
	private JLabel lblExitHour;
	private JLabel lblCFOP;
	private JLabel lblCofins;
	private JLabel lblClassificationDescription;
	private JLabel lblDescrioDaClassificao;
	private JLabel lblSupplier;
	private JLabel lblSelectSupplier;
	private JLabel lblcnpj;
	private JLabel txtcnpj;
	private JLabel lblFreightCnpj;
	private JLabel lblFreight;
	private JLabel lblStreet;
	private JLabel lblCompanyName;
	private JLabel lblFreightModality;
	private JLabel lblState;
	private JLabel lblCity;
	private JLabel lblANTTCode;
	private JLabel lblMaterialDescription;
	private JLabel lblMeasureUnit;
	private JLabel txtMeasureUnit;
	private JLabel lblUnValue;
	private JLabel txtUnValue;
	private JLabel lblMaterialCfop;
	private JLabel lblMaterialType;
	private JLabel lblAliquot;
	private JLabel lblCalculatedBase;
	private JLabel lblTax;
	private JLabel lblIpi;
	private JLabel lblPis;
	private JLabel lblNcm;
	private JLabel lblFinalCoust;
	private JLabel lblFinalUnitCoust;
	private JLabel lblSalesPercent;
	private JLabel lblFinalSalesValue;
	private JLabel lblAccessKey;
	private JLabel lblAmmount;
	private JLabel lblMaterialCod;
	private JLabel lblTotalValue;
	private JLabel lblCst;
	private JLabel lblTaxation;
	private JLabel lblIcms;
	
	private JSeparator separator_1;
	private JSeparator separator_2;

	private JPanel productPanel;
	private JTabbedPane tabbedPanel;
	private JSeparator separator_3;
	
	private JComboBox<Supplier> cboSuppliers;
	private JComboBox<String> cboFreightModality;
	private JComboBox<State> cboState;
	private JComboBox<City> cboCity;
	private JComboBox<String> cboMaterialType;
	private JComboBox<Material> cboMaterial;
	
	private JTable table;
	
	private DateField txtEmissionDate;

	private RegisterNFeEntryOnSystemController controller;
	
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	private JButton btnAdd;
	
	private List<NfeMaterialRelation> nfeRelationList;
	
	private Nfe nfe;
	private JLabel lblPurchaseOrder;
	private JComboBox<PurchaseOrder> cboPurchaseOrder;

	public RegisterNFeEntryOnSystemView() {
		controller = new RegisterNFeEntryOnSystemController(this);
		initialize();
		setListeners();
		nfeRelationList = new ArrayList<NfeMaterialRelation>();
	}

	private void initialize() {
		setTitle("REGISTRO DE NOTA FISCAL DE ENTRADA");
		Icon.setIcon(this);
		setBounds(100, 100, 757, 614);
		setPreferredSize(new Dimension(757, 565));
		setMinimumSize(new Dimension(757, 565));
		getContentPane().setLayout(new BorderLayout(0, 0));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setFocusable(true);
		initializeTabbed();
    }

	private void initializeTabbed() {
	    tabbedPanel = new JTabbedPane();
	    
	    initializePrincipal();
	    initializeProduct();
	    tabbedPanel.addTab("DADOS PRINCIPAIS", principalPanel);
	    tabbedPanel.addTab("PRODUTO", productPanel);
	    
	   
    }

	private void initializeProduct() {
	    productPanel = new JPanel();
	    lblMaterialCod = new JLabel("Mat. Cod");
	    
	    txtMaterialCod = new JTextField();
	    txtMaterialCod.setEditable(false);
	    txtMaterialCod.setColumns(10);
	    
	    lblMaterialDescription = new JLabel("Descrição");
	    
	    lblMeasureUnit = new JLabel("Medida");
	    
	    txtMeasureUnit = new JLabel("");
	    txtMeasureUnit.setHorizontalAlignment(SwingConstants.CENTER);
	    txtMeasureUnit.setBorder(new LineBorder(Color.black, 1));
	    
	    lblAmmount = new JLabel("Quantidade");
	    
	    txtAmmount = new JTextField();
	    txtAmmount.setColumns(10);
	    
	    lblTotalValue = new JLabel("Valor Total");
	    
	    txtTotalValue = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
	    txtTotalValue.setColumns(10);
	    
	    lblUnValue = new JLabel("Valor Unitário");
	    
	    txtUnValue = new JLabel();
	    txtUnValue.setBorder(new LineBorder(Color.black, 1));
	    
	    lblMaterialCfop = new JLabel("CFOP");
	    
	    try {
	        txtMaterialCfop = new JFormattedTextField(new MaskFormatter("####"));
        } catch (ParseException e1) {
	        e1.printStackTrace();
        }
	    txtMaterialCfop.setColumns(10);
	    
	    lblMaterialType = new JLabel("Tipo de Mercadoria");
	    
	    cboMaterialType = new JComboBox<String>();
	    cboMaterialType.addItem("00 - Material para Revenda");
	    cboMaterialType.addItem("01 - Matéria-Prima");
	    cboMaterialType.addItem("02 - Embalagem");
	    cboMaterialType.addItem("03 - Produto em Processo");
	    cboMaterialType.addItem("04 - Produto Acabado");
	    cboMaterialType.addItem("05 - Subproduto");
	    cboMaterialType.addItem("06 - Produto Intermediário");
	    cboMaterialType.addItem("07 - Material de Uso e Consumo");
	    cboMaterialType.addItem("08 - Ativo Mobilizado");
	    cboMaterialType.addItem("09 - Serviços");
	    cboMaterialType.addItem("10 - Outros Insumos");
	    cboMaterialType.addItem("99 - Outras");
	    
	    lblTaxation = new JLabel("Tributação");
	    
	    separator_3 = new JSeparator();
	    
	    lblCst = new JLabel("CST");
	    
	    lblIcms = new JLabel("ICMS");
	    
	    try {
	        txtCstIcms = new JFormattedTextField(new MaskFormatter("###"));
        } catch (ParseException e) {
	        e.printStackTrace();
        }
	    txtCstIcms.setColumns(10);
	    
	    txtAliquotIcms = new JTextField();
	    txtAliquotIcms.setColumns(10);
	    
	    lblAliquot = new JLabel("% Aliq");
	    
	    txtCalculatedBaseIcms = new JTextField();
	    txtCalculatedBaseIcms.setEditable(false);
	    txtCalculatedBaseIcms.setColumns(10);
	    
	    lblCalculatedBase = new JLabel("Base Calc.");
	    
	    txtTaxIcms = new JTextField();
	    txtTaxIcms.setColumns(10);
	    
	    lblTax = new JLabel("R$ Imposto");
	    
	    lblIpi = new JLabel("IPI");
	    
	    try {
	        txtCstIpi = new JFormattedTextField(new MaskFormatter("###"));
        } catch (ParseException e) {
	        e.printStackTrace();
        }
	    txtCstIpi.setColumns(10);
	    
	    txtAliquotIpi = new JTextField();
	    txtAliquotIpi.setColumns(10);
	    
	    txtCalculatedBaseIpi = new JTextField();
	    txtCalculatedBaseIpi.setEditable(false);
	    txtCalculatedBaseIpi.setColumns(10);
	    
	    txtTaxIpi = new JTextField();
	    txtTaxIpi.setColumns(10);
	    
	    lblCofins = new JLabel("COFINS");
	    
	    try {
	        txtCstCofins = new JFormattedTextField(new MaskFormatter("###"));
        } catch (ParseException e) {
	        e.printStackTrace();
        }
	    txtCstCofins.setColumns(10);
	    
	    txtAliquotCofins = new JTextField();
	    txtAliquotCofins.setColumns(10);
	    
	    txtCalculatedBaseCofins = new JTextField();
	    txtCalculatedBaseCofins.setEditable(false);
	    txtCalculatedBaseCofins.setColumns(10);
	    
	    txtTaxCofins = new JTextField();
	    txtTaxCofins.setColumns(10);
	    
	    lblPis = new JLabel("PIS");
	    
	    try {
	        txtCstPis = new JFormattedTextField(new MaskFormatter("###"));
        } catch (ParseException e) {
	        e.printStackTrace();
        }
	    txtCstPis.setColumns(10);
	    
	    txtAliquotPis = new JTextField();
	    txtAliquotPis.setColumns(10);
	    
	    txtCalculatedBasePis = new JTextField();
	    txtCalculatedBasePis.setEditable(false);
	    txtCalculatedBasePis.setColumns(10);
	    
	    txtTaxPis = new JTextField();
	    txtTaxPis.setColumns(10);
	    
	    lblNcm = new JLabel("NCM");
	    
	    txtNcm = new JTextField();
	    txtNcm.setColumns(10);
	    
	    lblFinalCoust = new JLabel("R$ Custo Final");
	    
	    txtFinalCost = new JTextField();
	    txtFinalCost.setColumns(10);
	    
	    lblFinalUnitCoust = new JLabel("R$ Unit. Final");
	    
	    txtFinalUnitCost = new JTextField();
	    txtFinalUnitCost.setColumns(10);
	    
	    lblSalesPercent = new JLabel("% Venda");
	    
	    txtSalesPercent = new JTextField();
	    txtSalesPercent.setColumns(10);
	    
	    lblFinalSalesValue = new JLabel("F.Venda");
	    
	    txtFinalSalesValue = new JTextField();
	    txtFinalSalesValue.setColumns(10);
	    
	    JScrollPane scrollPane = new JScrollPane();
	    
	    btnAdd = new JButton("Adicionar");
	    btnAdd.setIcon(new ImageIcon(RegisterNFeEntryOnSystemView.class.getResource("/resources/plus.png")));
	    
	    cboMaterial = new JComboBox<Material>();
	    new ComboBoxAutoCompletion(cboMaterial);
	    
	    cboMaterial.setSelectedIndex(-1);
	    
	    GroupLayout gl_productPanel = new GroupLayout(productPanel);
	    gl_productPanel.setHorizontalGroup(
	    	gl_productPanel.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(gl_productPanel.createSequentialGroup()
	    			.addGroup(gl_productPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_productPanel.createSequentialGroup()
	    					.addContainerGap()
	    					.addGroup(gl_productPanel.createParallelGroup(Alignment.LEADING)
	    						.addGroup(gl_productPanel.createSequentialGroup()
	    							.addComponent(lblTaxation)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(separator_3, GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE))
	    						.addGroup(gl_productPanel.createSequentialGroup()
	    							.addComponent(lblMaterialCod)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtMaterialCod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(lblMaterialDescription)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
	    							.addGap(18)
	    							.addComponent(lblMeasureUnit)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtMeasureUnit, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
	    						.addGroup(gl_productPanel.createSequentialGroup()
	    							.addComponent(lblAmmount)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtAmmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    							.addGap(18)
	    							.addComponent(lblTotalValue)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtTotalValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    							.addGap(18)
	    							.addComponent(lblUnValue)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtUnValue, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
	    							.addGap(5)
	    							.addComponent(lblMaterialCfop)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtMaterialCfop, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
	    						.addGroup(gl_productPanel.createSequentialGroup()
	    							.addComponent(lblMaterialType)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(cboMaterialType, 0, 628, Short.MAX_VALUE))
	    						.addGroup(gl_productPanel.createSequentialGroup()
	    							.addComponent(lblNcm)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtNcm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(lblFinalCoust)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtFinalCost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(lblFinalUnitCoust)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtFinalUnitCost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(lblSalesPercent)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtSalesPercent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(lblFinalSalesValue)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtFinalSalesValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
	    				.addGroup(gl_productPanel.createSequentialGroup()
	    					.addGap(12)
	    					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 563, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
	    				.addGroup(gl_productPanel.createSequentialGroup()
	    					.addGap(105)
	    					.addGroup(gl_productPanel.createParallelGroup(Alignment.LEADING)
	    						.addGroup(gl_productPanel.createSequentialGroup()
	    							.addGroup(gl_productPanel.createParallelGroup(Alignment.LEADING)
	    								.addComponent(lblIpi)
	    								.addComponent(lblIcms))
	    							.addGap(18)
	    							.addGroup(gl_productPanel.createParallelGroup(Alignment.LEADING)
	    								.addGroup(gl_productPanel.createSequentialGroup()
	    									.addGap(33)
	    									.addComponent(lblCst)
	    									.addGap(73)
	    									.addComponent(lblAliquot)
	    									.addGap(84)
	    									.addComponent(lblCalculatedBase)
	    									.addGap(79)
	    									.addComponent(lblTax))
	    								.addGroup(gl_productPanel.createSequentialGroup()
	    									.addGroup(gl_productPanel.createParallelGroup(Alignment.LEADING, false)
	    										.addGroup(gl_productPanel.createSequentialGroup()
	    											.addGap(2)
	    											.addComponent(txtCstIcms, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    											.addGap(10)
	    											.addComponent(txtAliquotIcms, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    											.addGap(10)
	    											.addComponent(txtCalculatedBaseIcms, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
	    										.addGroup(gl_productPanel.createSequentialGroup()
	    											.addPreferredGap(ComponentPlacement.RELATED)
	    											.addComponent(txtCstIpi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    											.addPreferredGap(ComponentPlacement.UNRELATED)
	    											.addComponent(txtAliquotIpi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    											.addPreferredGap(ComponentPlacement.UNRELATED)
	    											.addGroup(gl_productPanel.createParallelGroup(Alignment.LEADING)
	    												.addComponent(txtCalculatedBaseCofins, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
	    												.addComponent(txtCalculatedBaseIpi)
	    												.addComponent(txtCalculatedBasePis, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))))
	    									.addGroup(gl_productPanel.createParallelGroup(Alignment.LEADING)
	    										.addGroup(gl_productPanel.createSequentialGroup()
	    											.addPreferredGap(ComponentPlacement.UNRELATED)
	    											.addGroup(gl_productPanel.createParallelGroup(Alignment.LEADING, false)
	    												.addComponent(txtTaxCofins)
	    												.addComponent(txtTaxIpi)
	    												.addComponent(txtTaxPis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	    										.addGroup(gl_productPanel.createSequentialGroup()
	    											.addGap(10)
	    											.addComponent(txtTaxIcms, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))))))
	    						.addGroup(gl_productPanel.createSequentialGroup()
	    							.addGroup(gl_productPanel.createParallelGroup(Alignment.LEADING)
	    								.addComponent(lblCofins)
	    								.addComponent(lblPis))
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addGroup(gl_productPanel.createParallelGroup(Alignment.LEADING)
	    								.addGroup(gl_productPanel.createSequentialGroup()
	    									.addComponent(txtCstPis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    									.addPreferredGap(ComponentPlacement.RELATED)
	    									.addComponent(txtAliquotPis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    								.addGroup(gl_productPanel.createSequentialGroup()
	    									.addComponent(txtCstCofins, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    									.addPreferredGap(ComponentPlacement.RELATED)
	    									.addComponent(txtAliquotCofins, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))))
	    			.addContainerGap())
	    );
	    gl_productPanel.setVerticalGroup(
	    	gl_productPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_productPanel.createSequentialGroup()
	    			.addGroup(gl_productPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_productPanel.createSequentialGroup()
	    					.addGap(20)
	    					.addComponent(lblMeasureUnit))
	    				.addGroup(gl_productPanel.createSequentialGroup()
	    					.addGap(17)
	    					.addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_productPanel.createSequentialGroup()
	    					.addGap(20)
	    					.addComponent(lblMaterialDescription))
	    				.addGroup(gl_productPanel.createSequentialGroup()
	    					.addGap(17)
	    					.addComponent(txtMaterialCod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_productPanel.createSequentialGroup()
	    					.addGap(20)
	    					.addComponent(lblMaterialCod))
	    				.addGroup(gl_productPanel.createSequentialGroup()
	    					.addGap(16)
	    					.addComponent(txtMeasureUnit, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
	    			.addGap(18)
	    			.addGroup(gl_productPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_productPanel.createParallelGroup(Alignment.BASELINE)
	    					.addComponent(lblAmmount)
	    					.addComponent(txtAmmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    					.addComponent(lblTotalValue)
	    					.addComponent(txtTotalValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    					.addComponent(lblUnValue)
	    					.addComponent(txtUnValue, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_productPanel.createParallelGroup(Alignment.BASELINE)
	    					.addComponent(lblMaterialCfop)
	    					.addComponent(txtMaterialCfop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	    			.addGap(18)
	    			.addGroup(gl_productPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblMaterialType)
	    				.addComponent(cboMaterialType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGroup(gl_productPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_productPanel.createSequentialGroup()
	    					.addGap(18)
	    					.addComponent(lblTaxation))
	    				.addGroup(gl_productPanel.createSequentialGroup()
	    					.addGap(24)
	    					.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addGroup(gl_productPanel.createParallelGroup(Alignment.TRAILING)
	    				.addGroup(gl_productPanel.createSequentialGroup()
	    					.addComponent(lblIcms)
	    					.addGap(26)
	    					.addComponent(lblIpi))
	    				.addGroup(gl_productPanel.createSequentialGroup()
	    					.addGroup(gl_productPanel.createParallelGroup(Alignment.TRAILING, false)
	    						.addGroup(gl_productPanel.createSequentialGroup()
	    							.addComponent(lblCst)
	    							.addPreferredGap(ComponentPlacement.RELATED))
	    						.addGroup(gl_productPanel.createSequentialGroup()
	    							.addComponent(lblAliquot)
	    							.addGap(6))
	    						.addGroup(gl_productPanel.createSequentialGroup()
	    							.addComponent(lblCalculatedBase)
	    							.addGap(6))
	    						.addGroup(gl_productPanel.createSequentialGroup()
	    							.addComponent(lblTax)
	    							.addGap(6)))
	    					.addGap(1)
	    					.addGroup(gl_productPanel.createParallelGroup(Alignment.LEADING)
	    						.addComponent(txtCstIcms, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    						.addComponent(txtAliquotIcms, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    						.addComponent(txtCalculatedBaseIcms, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    						.addComponent(txtTaxIcms, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    					.addGap(15)
	    					.addGroup(gl_productPanel.createParallelGroup(Alignment.BASELINE)
	    						.addComponent(txtAliquotIpi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    						.addComponent(txtCalculatedBaseIpi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    						.addComponent(txtTaxIpi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    						.addComponent(txtCstIpi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
	    			.addGap(18)
	    			.addGroup(gl_productPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblCofins)
	    				.addComponent(txtCstCofins, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(txtAliquotCofins, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(txtCalculatedBaseCofins, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(txtTaxCofins, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addGroup(gl_productPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblPis)
	    				.addComponent(txtCstPis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(txtAliquotPis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(txtCalculatedBasePis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(txtTaxPis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(36)
	    			.addGroup(gl_productPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblNcm)
	    				.addComponent(txtNcm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblFinalCoust)
	    				.addComponent(txtFinalCost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblFinalUnitCoust)
	    				.addComponent(txtFinalUnitCost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblSalesPercent)
	    				.addComponent(txtSalesPercent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblFinalSalesValue)
	    				.addComponent(txtFinalSalesValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addGroup(gl_productPanel.createParallelGroup(Alignment.TRAILING)
	    				.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
	    				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
	    			.addContainerGap())
	    );
	    
	    table = new JTable();
	    String[] header = new String[] {"Material", "Quantidade", "R$ Unitário", "R$ Total", "Base",  "IPI", "ICMS"};
	    table.setModel(new DefaultTableModel(null, header) {

			/**
			 * 
			 */
            private static final long serialVersionUID = 996324409197934461L;
            
            boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
	    	
	    });
	    scrollPane.setViewportView(table);
	    productPanel.setLayout(gl_productPanel);
	    
	    
    }

	private void initializePrincipal() {
		lblPurchaseOrder = new JLabel("Pedido de Compra");
		
		cboPurchaseOrder = new JComboBox<PurchaseOrder>();
		controller.fillPurchaseOrder(cboPurchaseOrder);
		new ComboBoxAutoCompletion(cboPurchaseOrder);

		principalPanel = new JPanel();
		lblAccessKey = new JLabel("Informar numero da DANFE (Chave de Acesso)");
	    
	    try {
	        txtAccessKey = new JFormattedTextField(new MaskFormatter("#############################################"));
        } catch (ParseException txtEmissionDate) {
	        txtEmissionDate.printStackTrace();
        }
	    txtAccessKey.setColumns(10);
	    txtAccessKey.setHorizontalAlignment(JTextField.CENTER);
	    
	    lblFiscalNoteData = new JLabel("Dados da Nota Fiscal");
	    JSeparator separator = new JSeparator();
	    
	    try {
	        txtExitHour = new JFormattedTextField(new MaskFormatter("##:##"));
        } catch (ParseException e) {
	        e.printStackTrace();
        }
	    txtExitHour.setHorizontalAlignment(SwingConstants.CENTER);
	    txtExitHour.setColumns(10);
	    
	    txtEmissionDate = CalendarFactory.createDateField();
	    
	    txtNfeModel = new JTextField();
	    txtNfeModel.setHorizontalAlignment(SwingConstants.CENTER);
	    txtNfeModel.setColumns(10);
	    
	    txtDanfeSerial = new JTextField();
	    txtDanfeSerial.setHorizontalAlignment(SwingConstants.CENTER);
	    txtDanfeSerial.setColumns(10);
	    
	    try {
	        txtFiscalNumber = new JFormattedTextField(new MaskFormatter("######"));
	        txtFiscalNumber.setHorizontalAlignment(SwingConstants.CENTER);
        } catch (ParseException e) {
	        e.printStackTrace();
        }
	    txtFiscalNumber.setColumns(10);
	    
	    txtExitDate = CalendarFactory.createDateField();
	    
	    lblDanfeModel = new JLabel("Modelo");
	    
	    lblDanfeSerial = new JLabel("Série");
	    
	    lblFiscalNumber = new JLabel("Número Fiscal");
	    
	    lblEmissionDate = new JLabel("Data da Emissão");
	    
	    lblExitDate = new JLabel("Data da Saída");
	    
	    lblExitHour = new JLabel("Horário de Saída");
	    
	    lblCFOP = new JLabel("CFOP");
	    
	    txtCFOP = new JTextField();
	    txtCFOP.setHorizontalAlignment(SwingConstants.CENTER);
	    txtCFOP.setColumns(10);
	    
	    lblClassificationDescription = new JLabel("");
	    lblClassificationDescription.setBorder(new LineBorder(Color.BLACK, 1));
	    
	    lblDescrioDaClassificao = new JLabel("Descrição da Classificação");
	    
	    lblSupplier = new JLabel("Fornecedor");
	    
	    separator_1 = new JSeparator();
	    
	    lblSelectSupplier = new JLabel("Selecione o Fornecedor");
	    
	    cboSuppliers = new JComboBox<Supplier>();
	    controller.fillSuppliers(cboSuppliers);
	    cboSuppliers.setSelectedIndex(-1);
	    
	    lblcnpj = new JLabel("cpf/cnpj");
	    
	    txtcnpj = new JLabel("");
	    txtcnpj.setBorder(new LineBorder(Color.black, 1));
	    
	    lblFreight = new JLabel("Trasnporte");
	    
	    separator_2 = new JSeparator();
	    
	    lblFreightModality = new JLabel("Modalidade do Frete");
	    
	    cboFreightModality = new JComboBox<String>();
	    cboFreightModality.addItem("9 - Sem Frete");
	    cboFreightModality.addItem("0 - Por Conta do Emitente");
	    cboFreightModality.addItem("1 - Por Conta do destinatário/Remetente");
	    cboFreightModality.addItem("2 - Por Conta de Terceiros");
	    
	    
	    lblFreightCnpj = new JLabel("cpf/cnpj");
	    
	    txtFreightCnpj = new JTextField();
	    txtFreightCnpj.setColumns(10);
	    
	    lblCompanyName = new JLabel("Razão Social");
	    
	    txtCompanyName = new JTextField();
	    txtCompanyName.setColumns(10);
	    
	    lblStreet = new JLabel("Endereço");
	    
	    txtStreet = new JTextField();
	    txtStreet.setColumns(10);
	    
	    lblState = new JLabel("Estado");
	    
	    cboState = new JComboBox<State>();
	    
	    lblCity = new JLabel("Cidade");
	    
	    cboCity = new JComboBox<City>();
	    new FillStateAndCity(cboState, cboCity);
	    lblANTTCode = new JLabel("Código ANTT");
	    
	    txtANTTCode = new JTextField();
	    txtANTTCode.setColumns(10);
	    
	    
	    
	    GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	    gl_principalPanel.setHorizontalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	    				.addComponent(txtAccessKey, GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblFiscalNoteData)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 622, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(Alignment.LEADING, gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblFreightModality)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboFreightModality, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblFreightCnpj)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtFreightCnpj, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
	    				.addGroup(Alignment.LEADING, gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblStreet)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtStreet, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblState)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboState, 0, 317, Short.MAX_VALUE))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblFreight)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(separator_2, GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE))
	    				.addGroup(Alignment.LEADING, gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblCompanyName)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtCompanyName, GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblCity)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboCity, 0, 346, Short.MAX_VALUE)
	    					.addGap(18)
	    					.addComponent(lblANTTCode)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtANTTCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    					.addGap(173))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblSelectSupplier)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(cboSuppliers, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
	    							.addGap(18)
	    							.addComponent(lblcnpj)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtcnpj, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblSupplier)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)))
	    					.addGap(4))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addGap(30)
	    							.addComponent(lblCFOP))
	    						.addComponent(txtCFOP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    					.addGap(18)
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    						.addComponent(lblDescrioDaClassificao)
	    						.addComponent(lblClassificationDescription, GroupLayout.PREFERRED_SIZE, 622, GroupLayout.PREFERRED_SIZE)))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addGap(4)
	    							.addComponent(lblExitHour))
	    						.addComponent(txtExitHour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    					.addGap(3)
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addGap(22)
	    							.addComponent(txtNfeModel, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addGap(69)
	    							.addComponent(lblDanfeModel)))
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addGap(13)
	    							.addComponent(txtEmissionDate, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addGap(22)
	    							.addComponent(lblEmissionDate)))
	    					.addGap(10)
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING, false)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblDanfeSerial)
	    							.addGap(31))
	    						.addComponent(txtDanfeSerial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    					.addGap(18)
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblFiscalNumber)
	    							.addGap(10))
	    						.addComponent(txtFiscalNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    					.addGap(18)
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    						.addComponent(lblExitDate)
	    						.addComponent(txtExitDate, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
	    					.addGap(61))
	    				.addGroup(Alignment.LEADING, gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblPurchaseOrder)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboPurchaseOrder, 0, 625, Short.MAX_VALUE))
	    				.addComponent(lblAccessKey, Alignment.LEADING))
	    			.addContainerGap())
	    );
	    gl_principalPanel.setVerticalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addGap(21)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblPurchaseOrder)
	    				.addComponent(cboPurchaseOrder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(13)
	    			.addComponent(lblAccessKey)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(txtAccessKey, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(18)
	    					.addComponent(lblFiscalNoteData))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(24)
	    					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	    								.addComponent(lblDanfeModel)
	    								.addComponent(lblExitHour))
	    							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    							.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    								.addComponent(txtNfeModel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    								.addComponent(txtExitHour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    							.addPreferredGap(ComponentPlacement.RELATED))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblFiscalNumber)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtFiscalNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblExitDate)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtExitDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblDanfeSerial)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtDanfeSerial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	    					.addGap(18)
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    						.addComponent(lblDescrioDaClassificao)
	    						.addComponent(lblCFOP)))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(lblEmissionDate)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtEmissionDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	    				.addComponent(lblClassificationDescription, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    				.addComponent(txtCFOP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	    				.addComponent(lblSupplier)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    					.addGap(6)))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblSelectSupplier)
	    				.addComponent(cboSuppliers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblcnpj)
	    				.addComponent(txtcnpj, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(18)
	    					.addComponent(lblFreight))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(24)
	    					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblFreightModality)
	    				.addComponent(cboFreightModality, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblFreightCnpj)
	    				.addComponent(txtFreightCnpj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblCompanyName)
	    				.addComponent(txtCompanyName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblStreet)
	    				.addComponent(txtStreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblState)
	    				.addComponent(cboState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblCity)
	    				.addComponent(cboCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblANTTCode)
	    				.addComponent(txtANTTCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addContainerGap())
	    );
	    principalPanel.setLayout(gl_principalPanel);
	    getContentPane().add(tabbedPanel, BorderLayout.CENTER);
	    
	    initializeSub();
    }
	
	private void initializeSub() {
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(subPanel, BorderLayout.SOUTH);
		
		btnConfirm = new JButton("Confirmar");
		btnConfirm.setIcon(new ImageIcon(RegisterNFeEntryOnSystemView.class.getResource("/resources/ok.png")));
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(RegisterNFeEntryOnSystemView.class.getResource("/resources/cancel.png")));
		btnClear = new JButton("Limpar");
		btnClear.setIcon(new ImageIcon(RegisterNFeEntryOnSystemView.class.getResource("/resources/ClearFrame.png")));
		
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
		
		
		FocusListener focusListener = new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(e.getSource().equals(txtNfeModel))nfeModel();
				else if(e.getSource().equals(txtDanfeSerial))danfeSerial();
				else if(e.getSource().equals(txtCFOP))cfop(e);
				else if(e.getSource().equals(txtExitHour))verifyHour();
				else if(e.getSource().equals(txtFreightCnpj))formatFreightCnpj();
				else if(e.getSource().equals(txtMaterialCfop))cfop(e);
				else if(e.getSource().equals(txtCstIcms))cst(e);
				else if(e.getSource().equals(txtCstCofins))cst(e);
				else if(e.getSource().equals(txtCstIpi))cst(e);
				else if(e.getSource().equals(txtCstPis))cst(e);
				else if(e.getSource().equals(txtCalculatedBaseIcms))calculatedBase(txtCalculatedBaseIcms);
				else if(e.getSource().equals(txtCalculatedBaseCofins))calculatedBase(txtCalculatedBaseCofins);
				else if(e.getSource().equals(txtCalculatedBaseIpi))calculatedBase(txtCalculatedBaseIpi);
				else if(e.getSource().equals(txtCalculatedBasePis))calculatedBase(txtCalculatedBasePis);
				else if(e.getSource().equals(txtTotalValue))parcialValue();
				else if(e.getSource().equals(txtTaxIcms))aliquotIcms();
				else if(e.getSource().equals(txtTaxCofins))aliquotCofins();
				else if(e.getSource().equals(txtTaxPis))aliquotPis();
				else if(e.getSource().equals(txtTaxIpi))aliquotIpi();
				else if(e.getSource().equals(txtFinalCost))finalCoust();
				else if(e.getSource().equals(cboFreightModality))changeTab();
				else if(e.getSource().equals(txtANTTCode))tabbedPanel.setSelectedIndex(1);
				else if(e.getSource().equals(txtSalesPercent))salesPercent();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
			}
		};
		txtNfeModel.addFocusListener(focusListener);
		txtDanfeSerial.addFocusListener(focusListener);
		txtCFOP.addFocusListener(focusListener);
		txtExitHour.addFocusListener(focusListener);
		txtFreightCnpj.addFocusListener(focusListener);
		txtMaterialCfop.addFocusListener(focusListener);
		txtCstIcms.addFocusListener(focusListener);
		txtCstCofins.addFocusListener(focusListener);
		txtCstIpi.addFocusListener(focusListener);
		txtCstPis.addFocusListener(focusListener);
		txtTotalValue.addFocusListener(focusListener);
		txtCalculatedBaseIcms.addFocusListener(focusListener);
		txtCalculatedBaseCofins.addFocusListener(focusListener);
		txtCalculatedBaseIpi.addFocusListener(focusListener);
		txtCalculatedBasePis.addFocusListener(focusListener);
		txtTaxIcms.addFocusListener(focusListener);
		txtTaxCofins.addFocusListener(focusListener);
		txtTaxPis.addFocusListener(focusListener);
		txtTaxIpi.addFocusListener(focusListener);
		txtFinalCost.addFocusListener(focusListener);
		cboFreightModality.addFocusListener(focusListener);
		txtANTTCode.addFocusListener(focusListener);
		txtSalesPercent.addFocusListener(focusListener);
		
		ActionListener cboListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(cboSuppliers))fillSupplierCnpj();
				else if(e.getSource().equals(cboMaterial))fillMaterialCode();
				else if(e.getSource().equals(cboPurchaseOrder))fillMaterials();
			}
		};
		cboPurchaseOrder.addActionListener(cboListener);
		cboSuppliers.addActionListener(cboListener);
		cboMaterial.addActionListener(cboListener);
		
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnClear)) clearFrame(false);
				else if(e.getSource().equals(btnAdd))addMaterialToTable();
				else if(e.getSource().equals(btnCancel))controller.close();
				else if(e.getSource().equals(btnConfirm))confirm();
			}
		};
		btnCancel.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		btnAdd.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
		
		KeyListener keyListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getSource().equals(table))remove(e);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getSource().equals(table))remove(e);
			}
		};
		table.addKeyListener(keyListener);
	}
	
	private void fillMaterials() {
		if(cboPurchaseOrder.getSelectedIndex() == -1)return;
		controller.fillMaterialCboOrPurchaseOrder(cboMaterial, (PurchaseOrder) cboPurchaseOrder.getSelectedItem());
	}
	
	/*
	 * Solicita confirmação para prosseguir com o resgistro de nfe, e verifica se os requisitos foram preenchidos
	 * caso tenham sido preenchidos a função tenta realizar seu registro junto ao banco de dados.
	 */
	private void confirm() {
		if(cboPurchaseOrder.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Erro", "Selecione um fornecedor");
			return;
		}
		PurchaseOrder res = (PurchaseOrder) cboPurchaseOrder.getSelectedItem();
		int i = ShowMessage.questionMessage(this, "Registrar", "Deseja realmente registar essa nota fiscal?");
		if(i == JOptionPane.NO_OPTION)return;
		boolean isOk = verifyDataNfe();
		if(!isOk)return;
		//nfeRelationList.forEach(s -> System.out.println(s));
		nfe.setPurchaseOrder(res);
		controller.registerNFe(nfeRelationList, nfe);
		ShowMessage.successMessage(this, "Sucesso", "Registro de NF concluido com sucesso!");
		clearFrame(true);
		controller.fillPurchaseOrder(cboPurchaseOrder);
	}
	
	/*
	 * Remove da tabela um material/produto previamente adicionado.
	 */
	private void remove(KeyEvent e) {
		if(e.getKeyCode() ==  KeyEvent.VK_DELETE) {
			if(table.getSelectedRow() == -1)return;
			DefaultTableModel tbl = (DefaultTableModel) table.getModel();
			nfeRelationList.remove(tbl.getValueAt(table.getSelectedRow(), 0));
			NfeMaterialRelation nfemr =  (NfeMaterialRelation) tbl.getValueAt(table.getSelectedRow(), 0);
			Material mat = nfemr.getMaterial();
			tbl.removeRow(table.getSelectedRow());
			cboMaterial.addItem(mat);
		}
	}
	/*
	 * Verifica se algum valor foi inserido caso não tenha sido inserido considera como 0
	 * caso haja algum valor adiciona essa porcentagem inserida ao valor final unitario, e
	 * popula no campo txtFinalSalesValues (valor final de venda).
	 */
	private void salesPercent() {
		if(txtSalesPercent.getText().trim().isEmpty()) {
			txtSalesPercent.setText("0");
			if(txtFinalUnitCost.getText().trim().isEmpty())return;
			txtFinalSalesValue.setText(txtFinalUnitCost.getText());
		}else {
			if(txtFinalUnitCost.getText().isEmpty())return;
			double value = Double.parseDouble(txtFinalUnitCost.getText().replaceAll("R|\\$", "").replaceAll(",", ".").trim());
			double percent = Double.parseDouble(txtSalesPercent.getText().replaceAll(",", "."));
			percent = percent/ 100;
			value = (value * percent) + value;
			String sValue = new DecimalFormat("#.##").format(value);
			txtFinalSalesValue.setText(String.valueOf("R$ " + sValue));
		}
	}
	
	private void clearFrame(boolean b) {
		if(!b) {
			int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente limpar a janela ?");
			if(i == JOptionPane.NO_OPTION)return;
			ClearFrame.clear(this);			
		}else {
			ClearFrame.clear(this);
		}
	}
	
	/*
	 * Verifica os requisitos de inserção e caso tudo seja atendido adiciona um material/produto à tabela da nota fiscal.
	 */
	private void addMaterialToTable() {
		boolean isOk = verifyData();
		if(!isOk)return;
		Material material = (Material) cboMaterial.getSelectedItem();
		double ammount = Double.parseDouble(txtAmmount.getText().replaceAll(",", "."));
		double unValue = Double.parseDouble(txtUnValue.getText().replaceAll("R|\\$", "").replaceAll(",", ".").trim());
		CFOPExit materialCFOP = controller.getCFOP(txtMaterialCfop.getText());
		String cstIcms = txtCstIcms.getText();
		String cstPis = txtCstPis.getText();
		String cstCofins = txtCstCofins.getText();
		String cstIpi = txtCstIpi.getText();
		double aliquotIcms = (txtAliquotIcms.getText().isEmpty()? 0 :Double.parseDouble(txtAliquotIcms.getText().replaceAll(",", ".")));
		double aliquotPis = (txtAliquotPis.getText().isEmpty()? 0 :Double.parseDouble(txtAliquotPis.getText().replaceAll(",", "."))); 
		double aliquotIpi = (txtAliquotIpi.getText().isEmpty()? 0 :Double.parseDouble(txtAliquotIpi.getText().replaceAll(",", ".")));
		double aliquotCofins = (txtAliquotCofins.getText().isEmpty()? 0 :Double.parseDouble(txtAliquotCofins.getText().replaceAll(",", ".")));
		double totalIcms = (txtTaxIcms.getText().replaceAll("R|\\$", "").replaceAll(",", "").trim().isEmpty() ? 0 : Double.parseDouble(txtTaxIcms.getText().replaceAll("R|\\$", "").replaceAll(",", ".").trim()));
		double totalIpi = (txtTaxIpi.getText().replaceAll("R|\\$", "").replaceAll(",", "").trim().isEmpty() ? 0 : Double.parseDouble(txtTaxIpi.getText().replaceAll("R|\\$", "").replaceAll(",", ".").trim()));
		double totalPis = (txtTaxPis.getText().replaceAll("R|\\$", "").replaceAll(",", "").trim().isEmpty() ? 0 : Double.parseDouble(txtTaxPis.getText().replaceAll("R|\\$", "").replaceAll(",", ".").trim()));
		double totalCofins = (txtTaxCofins.getText().replaceAll("R|\\$", "").replaceAll(",", "").trim().isEmpty() ? 0 : Double.parseDouble(txtTaxCofins.getText().replaceAll("R|\\$", "").replaceAll(",", ".").trim()));
		String danfe = txtAccessKey.getText();
		String exitHour = txtExitHour.getText();
		String model = txtNfeModel.getText();
		Date emissionDate = (Date) txtEmissionDate.getValue();
		Date exitDate = (Date) txtExitDate.getValue();
		String danfeSerial = txtDanfeSerial.getText();
		String fiscalNumber = txtFiscalNumber.getText();
		Supplier supplier = (Supplier) cboSuppliers.getSelectedItem();
		int freight = cboFreightModality.getSelectedIndex();
		CFOPExit cfop = controller.getCFOP(txtCFOP.getText());
		double totalValue = Double.parseDouble(txtTotalValue.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim());
		int typeIndex = cboMaterialType.getSelectedIndex();
		double finalCost = Double.parseDouble(txtFinalCost.getText().replaceAll("R|\\$", "").replaceAll(",", "."));
		double unFinalCost = Double.parseDouble(txtFinalUnitCost.getText().replaceAll("R|\\$", "").replaceAll(",", "\\."));
		double salesPercent = Double.parseDouble(txtSalesPercent.getText().replaceAll(",", "\\."));
		double finalSalesValue = Double.parseDouble(txtFinalSalesValue.getText().replaceAll(",", "\\.").replaceAll("R|\\$", ""));
		double basValue = (txtCalculatedBaseIcms.getText().replaceAll("R|\\$", "").replaceAll(",|\\.", "").trim().isEmpty() ? 0 : Double.parseDouble(txtCalculatedBaseIcms.getText().replaceAll("R|\\$", "").replaceAll(",", ".")));
		if(table.getRowCount()>0) {			
			nfe = new Nfe(danfe, exitHour, model, emissionDate, exitDate, danfeSerial, fiscalNumber, cfop, supplier, freight);
		}
		NfeMaterialRelation nfemr = new NfeMaterialRelation(material, totalValue, unValue, materialCFOP, typeIndex, cstIcms,
				cstPis, cstCofins, cstIpi, finalCost, unFinalCost, salesPercent, finalSalesValue, ammount, nfe,
				aliquotPis, aliquotIpi, aliquotCofins, aliquotIcms, totalIcms, totalPis, totalIpi, totalCofins, basValue);
		Object[] obj = new Object[]{nfemr, ammount, unValue,totalValue, txtCalculatedBaseIcms.getText(), txtTaxIpi.getText(), txtTaxIcms.getText()};
		
		boolean isInTable = verifyTable(nfemr);
		if(!isInTable)return;
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		tbl.addRow(obj);
		nfeRelationList.add(nfemr);
		cboMaterial.removeItem(material);
		clearMaterialTab();
	}
	
	/*
	 * Limpa o formulário sem limpar as informações contidas na tabela.
	 */
	private void clearMaterialTab() {
	   ClearFrame.clearWithoutTable(productPanel);
	    
    }

	/*
	 * Verifica se o produto em que o usuário está tentando adicionar à nota fiscal já não foi inserido,
	 * caso já tenha sido inserido uma notificação é disparada ao usuário.
	 */
	private boolean verifyTable(NfeMaterialRelation mat) {
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		int i = tbl.getRowCount();
		for(int cont = 0; cont<i; cont++) {
			NfeMaterialRelation m = (NfeMaterialRelation) tbl.getValueAt(cont, 0);
			if(m.equals(mat)) {
				ShowMessage.errorMessage(this, "Erro", "Esse produto já foi adicionado!");
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Verifica os dados da primeira parte da nota fiscal para que o material/produto possa ser inserido à ela.
	 */
	private boolean verifyDataNfe() {
		String accesKey = txtAccessKey.getText();
		
		String exitHour = txtExitHour.getText().trim();
		String nfeModel = txtNfeModel.getText().trim();
		String danfeSerial = txtDanfeSerial.getText().trim();
		String fiscalNumber = txtFiscalNumber.getText().trim();
		String generalCFOP = txtCFOP.getText().trim();
		int supplierIndex = cboSuppliers.getSelectedIndex();
		int freightModalityIndex = cboFreightModality.getSelectedIndex();
		
		if(freightModalityIndex > 0) {
			String street = txtStreet.getText();
			String cnpj = txtcnpj.getText();
			int cityIndex = cboCity.getSelectedIndex();
			//int stateIndex = cboState.getSelectedIndex();
			String companyName = txtCompanyName.getText();
			String anttCode = txtANTTCode.getText();
			if(street.isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Insira o endereço do responsável pelo frete!");
				return false;
			}
			if(cnpj.isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Insira o cnpj/cpf do responsável pelo transporte");
				return false;
			}
			if(cityIndex == -1) {
				ShowMessage.errorMessage(this, "Erro", "Selecione uma cidade");
				return false;
			}
			if(companyName.isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Insira o nome da pessoa ou empresa responsável pelo transporte");
				return false;
			}
			if(anttCode.isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Insira o código ANTT");
				return false;
			}
		}
		if(freightModalityIndex == -1) {
			ShowMessage.errorMessage(this, "Erro", "Selecione a modalidade do frete");
			return false;
		}if(supplierIndex == -1) {
			ShowMessage.errorMessage(this, "Erro", "Selecione um fornecedor");
			return false;
		}if(accesKey.isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira a chave de acesso");
			return false;
		}if(exitHour.replaceAll(":", "").trim().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira a hora de saída");
			return false;
		}if(danfeSerial.isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira a serie da nota fiscal");
			return false;
		}if(txtExitDate.getValue() == null) {
			ShowMessage.errorMessage(this, "Erro", "Insira a data de saída");
			return false;
		}if(txtEmissionDate.getValue() == null) {
			ShowMessage.errorMessage(this, "Erro", "Insira a data de emissão");
			return false;
		}if(nfeModel.isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o modelo da nota fiscal");
			return false;
		}if(generalCFOP.isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o CFOP geral da nota fiscal");
			return false;
		}if(fiscalNumber.isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o numero fiscal da nota");
			return false;
		}else if(nfeRelationList.isEmpty()) {
			ShowMessage.errorMessage(this, "Error", "A nota fiscal não possui produtos");
			return false;
		}else {
			return true;
		}
	}
	
	/*
	 * Verifica todos os dados da nota fiscal para realização do registro junto ao banco de dados.
	 * caso tenha algo irregular uma notificação ao usuário é disparada.
	 */
	private boolean verifyData() {
		String accesKey = txtAccessKey.getText();
	
		String exitHour = txtExitHour.getText().trim();
		String nfeModel = txtNfeModel.getText().trim();
		String danfeSerial = txtDanfeSerial.getText().trim();
		String fiscalNumber = txtFiscalNumber.getText().trim();
		String generalCFOP = txtCFOP.getText().trim();
		int supplierIndex = cboSuppliers.getSelectedIndex();
		int freightModalityIndex = cboFreightModality.getSelectedIndex();
		
		int materialIndex = cboMaterial.getSelectedIndex();
		double ammount = (txtAmmount.getText().isEmpty() ? 0 :Double.parseDouble(txtAmmount.getText()));
		double totalValue = Double.parseDouble(txtTotalValue.getText().replaceAll("R|\\$", "").replaceAll(",", ".").trim());
		String materialCFOP = (txtMaterialCfop.getText().isEmpty() ? null : txtMaterialCfop.getText());
		int materialTypeIndex = cboMaterialType.getSelectedIndex();
		
		String ncm = txtNcm.getText();
		String finalCoust = txtFinalCost.getText();
		String finalUnitCoust = txtFinalUnitCost.getText();
		String salesPercent = txtSalesPercent.getText();
		String finalSalesValues = txtFinalSalesValue.getText();
		
		String cofins = txtCstCofins.getText();
		String Icms = txtCstIcms.getText();
		String ipi = txtCstIpi.getText();
		String pis = txtCstPis.getText();
		
		if(freightModalityIndex > 0) {
			String street = txtStreet.getText();
			String cnpj = txtcnpj.getText();
			int cityIndex = cboCity.getSelectedIndex();
			//int stateIndex = cboState.getSelectedIndex();
			String companyName = txtCompanyName.getText();
			String anttCode = txtANTTCode.getText();
			if(street.isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Insira o endereço do responsável pelo frete!");
				return false;
			}
			if(cnpj.isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Insira o cnpj/cpf do responsável pelo transporte");
				return false;
			}
			if(cityIndex == -1) {
				ShowMessage.errorMessage(this, "Erro", "Selecione uma cidade");
				return false;
			}
			if(companyName.isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Insira o nome da pessoa ou empresa responsável pelo transporte");
				return false;
			}
			if(anttCode.isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Insira o código ANTT");
				return false;
			}
		}
		if(freightModalityIndex == -1) {
			ShowMessage.errorMessage(this, "Erro", "Selecione a modalidade do frete");
			return false;
		}if(supplierIndex == -1) {
			ShowMessage.errorMessage(this, "Erro", "Selecione um fornecedor");
			return false;
		}if(accesKey.isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira a chave de acesso");
			return false;
		}if(exitHour.replaceAll(":", "").trim().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira a hora de saída");
			return false;
		}if(danfeSerial.isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira a serie da nota fiscal");
			return false;
		}if(txtExitDate.getValue() == null) {
			ShowMessage.errorMessage(this, "Erro", "Insira a data de saída");
			return false;
		}if(txtEmissionDate.getValue() == null) {
			ShowMessage.errorMessage(this, "Erro", "Insira a data de emissão");
			return false;
		}if(nfeModel.isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o modelo da nota fiscal");
			return false;
		}if(generalCFOP.isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o CFOP geral da nota fiscal");
			return false;
		}if(fiscalNumber.isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o numero fiscal da nota");
			return false;
		}if(materialIndex == -1) {
			ShowMessage.errorMessage(this, "Erro", "Selecione o material");
			return false;
		}if(ammount <= 0) {
			ShowMessage.errorMessage(this, "Erro", "Insira a quantidade de material");
			return false;
		}if(totalValue <=0) {
			ShowMessage.errorMessage(this, "Erro", "Verifique o valor total");
			return false;
		}if(materialCFOP.isEmpty() ) {
			ShowMessage.errorMessage(this, "Erro", "Insira o CFOP do material");
			return false;
		}if(materialTypeIndex == -1) {
			ShowMessage.errorMessage(this, "Erro", "Selecione o tipo do material");
			return false;
		}if(ncm.isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o nsm do produto)");
			return false;
		}if(finalCoust.replaceAll("R|\\$", "").replaceAll(",", ".").trim().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Verifique o custo final");
			return false;
		}if(finalUnitCoust.replaceAll("R|\\$", "").replaceAll(",", ".").trim().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Verifique o custo final unitário");
			return false;
		}if(salesPercent.replaceAll("R|\\$", "").replaceAll(",", ".").trim().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Verifique o custo final");
			return false;
		}if(finalSalesValues.replaceAll("R|\\$", "").replaceAll(",", ".").trim().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Verifique o custo final");
			return false;
		}if(cofins.isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o cst do cofins");
			return false;
		}else if(!cofins.equalsIgnoreCase("007")) {
			String cofinsAliquot = txtAliquotCofins.getText().replaceAll(",", ".");
			String taxCofins = txtTaxCofins.getText().replaceAll("R|\\$", "").replaceAll(",", ".").trim();
			if(cofinsAliquot.isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Insira o valor da aliquota do cofins");
				return false;
			}if(taxCofins.isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Verifique o valor do imposto cofins");
				return false;
			}
		}
		if(Icms.isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o cst do Icms");
			return false;
		}else if(!Icms.equalsIgnoreCase("000")) {
			String icmsAliquot = txtAliquotIcms.getText().replaceAll(",", ".");
			String taxIcms = txtTaxIcms.getText().replaceAll("R|\\$", "").replaceAll(",", ".").trim();
			if(icmsAliquot.isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Insira o valor da aliquota do icms");
				return false;
			}if(taxIcms.isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Verifique o valor do imposto icms");
				return false;
			}
		}
		if(pis.isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o cst do pis");
			return false;
		}else if(!pis.equalsIgnoreCase("007")) {
			String pisAliquot = txtAliquotPis.getText().replaceAll(",", ".");
			String taxPis = txtTaxPis.getText().replaceAll("R|\\$", "").replaceAll(",", ".").trim();
			if(pisAliquot.isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Insira o valor da aliquota do pis");
				return false;
			}if(taxPis.isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Verifique o valor do imposto pis");
				return false;
			}
		}
		if(ipi.isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o cst do ipi");
			return false;
		}else if(!ipi.equalsIgnoreCase("000")) {
			String ipiAliquot = txtAliquotIpi.getText().replaceAll(",", ".");
			String taxIpi = txtTaxIpi.getText().replaceAll("R|\\$", "").replaceAll(",", ".").trim();
			if(ipiAliquot.isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Insira o valor da aliquota do ipi");
				return false;
			}if(taxIpi.isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Verifique o valor do imposto ipi");
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Habilita ou desabilita campos referentes a entregra dos materiais/produtos no ato de mudançã de aba 
	 * dependendo da modelidade de frete selecionado previamente.
	 */
	private void changeTab() {
		if(cboFreightModality.getSelectedIndex() == 0) {
			txtFreightCnpj.setEnabled(false);
			cboCity.setEnabled(false);
			cboState.setEnabled(false);
			txtStreet.setEnabled(false);
			txtCompanyName.setEnabled(false);
			txtANTTCode.setEnabled(false);
			tabbedPanel.setSelectedIndex(1);
		}else{
			txtFreightCnpj.setEnabled(true);
			cboCity.setEnabled(true);
			cboState.setEnabled(true);
			txtStreet.setEnabled(true);
			txtCompanyName.setEnabled(true);
			txtANTTCode.setEnabled(true);
		}
	}
	
	private void finalCoust() {
		double unitValue = Double.parseDouble(txtUnValue.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim());
		double tax = (txtTaxIpi.getText().isEmpty() ? 0 :Double.parseDouble(txtTaxIpi.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.")));
		double ammount = Double.parseDouble(txtAmmount.getText().replaceAll(",", "."));
		if(unitValue == 0 || tax == 0 || ammount == 0)return;
		double div = tax/ammount;
		unitValue += div;
		String sUnitValue = new DecimalFormat("#.##").format(unitValue);
		txtFinalUnitCost.setText("R$ " + sUnitValue);
		txtFinalCost.setText(String.valueOf("R$ " + (new DecimalFormat("#.##").format((div*ammount) + unitValue))));
	}
	
	/*
	 * Verifica se o campo de aliquota IPI não se encontra em branco, caso o campo não esteja em branco ele divide
	 * o valor da aliquota por 100(descobrindo porcentagem) e multiplica pelo valor do produto depôs popula o campo
	 * txtTaxIpi com o valor do imposto.
	 */
	private void aliquotIpi() {
		if(txtAliquotIpi.getText().isEmpty() || txtCalculatedBaseIpi.getText().isEmpty())return;
		else {
			double aliquot = Double.parseDouble(txtAliquotIpi.getText().replaceAll(",", "\\."));
			double value = Double.parseDouble(txtCalculatedBaseIpi.getText().replaceAll("R|\\$", "").replaceAll(",", "\\."));
			double tax = (aliquot/100) * value;
			txtTaxIpi.setText(String.valueOf(new DecimalFormat("#.##").format(tax)));
		}
	}
	
	/*
	 * Verifica se o campo de aliquota PIS não se encontra em branco, caso o campo não esteja em branco ele divide
	 * o valor da aliquota por 100(descobrindo porcentagem) e multiplica pelo valor do produto depôs popula o campo
	 * txtTaxPis com o valor do imposto.
	 */
	private void aliquotPis() {
		if(txtAliquotPis.getText().isEmpty() || txtCalculatedBasePis.getText().isEmpty())return;
		else {
			double aliquot = Double.parseDouble(txtAliquotPis.getText().replaceAll(",", "."));
			double value = Double.parseDouble(txtCalculatedBasePis.getText().replaceAll("R|\\$", "").replaceAll(",", "."));
			double tax = (aliquot/100) * value;
			txtTaxPis.setText(String.valueOf(new DecimalFormat("#.##").format(tax)));
		}
	}
	
	/*
	 * Verifica se o campo de aliquota Cofins não se encontra em branco, caso o campo não esteja em branco ele divide
	 * o valor da aliquota por 100(descobrindo porcentagem) e multiplica pelo valor do produto depôs popula o campo
	 * txtTaxCofins com o valor do imposto.
	 */
	private void aliquotCofins() {
		if(txtAliquotCofins.getText().isEmpty() || txtCalculatedBaseCofins.getText().isEmpty())return;
		else {
			double aliquot = Double.parseDouble(txtAliquotCofins.getText().replaceAll(",", "."));
			double value = Double.parseDouble(txtCalculatedBaseCofins.getText().replaceAll("R|\\$", "").replaceAll(",", "."));
			double tax = (aliquot/100) * value;
			txtTaxCofins.setText(String.valueOf(new DecimalFormat("#.##").format(tax)));
		}
	}
	
	/*
	 * Verifica se o campo de aliquota Icms não se encontra em branco, caso o campo não esteja em branco ele divide
	 * o valor da aliquota por 100(descobrindo porcentagem) e multiplica pelo valor do produto depôs popula o campo
	 * txtTaxIcms com o valor do imposto.
	 */
	private void aliquotIcms() {
		if(txtAliquotIcms.getText().isEmpty() || txtCalculatedBaseIcms.getText().isEmpty())return;
		else {
			double aliquot = Double.parseDouble(txtAliquotIcms.getText().replaceAll(",", "."));
			double value = Double.parseDouble(txtCalculatedBaseIcms.getText().replaceAll("R|\\$", "").replaceAll(",", "."));
			double tax = (aliquot/100) * value;
			txtTaxIcms.setText(String.valueOf(new DecimalFormat("#.##").format(tax)));
		}
	}
	
	/*
	 * Pega o valor total da nota referente ao material ou produto em questão e sua respectiva quantidade e realiza da divisão
	 * do total pela quantidade obtendo o valor por produto que é populado no campo txtUnValue. 
	 */
	private void parcialValue() {
		if(txtTotalValue.getText().replaceAll("R|\\$", "").replaceAll(",|0", "").trim().isEmpty() || txtAmmount.getText().isEmpty())return;
		else {
			double total = Double.parseDouble(txtTotalValue.getText().replaceAll("R|\\$", "").replaceAll(",", ".").trim());
			double ammount = Double.parseDouble(txtAmmount.getText());
			double parcial = total / ammount;
			txtUnValue.setText("R$ " + new DecimalFormat("#.##").format(parcial));
		}
	}
	
	/*
	 * Calcula a "Base de Calculo" usado para obtenção de impostos como pis, icms e outros.
	 */
	private void calculatedBase(JTextField txtBase) {
		if(cboFreightModality.getSelectedIndex() == 0) {
			if(txtTotalValue.getText().replaceAll("R|\\$", "").replaceAll(",|0", "").trim().isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Insira o valor total do material nessa nota");
				return;	
			}
			String bc = txtTotalValue.getText();
			txtBase.setText(bc);
		}
	}
	
	/*
	 * Preenche automaticamente os códigos mais convencionais dos sequintes impostos: icms, cofins, pis e ipi.
	 */
	private void cst(FocusEvent e) {
		if(e.getSource().equals(txtCstIcms)) {
			if(txtCstIcms.getText().trim().isEmpty()) {
				txtCstIcms.setText("000");
			}else {
				String cst = txtCstIcms.getText();
				boolean isCst = controller.verifyIcmsCst(cst);
				if(!isCst) {
					ShowMessage.errorMessage(this, "Erro", "Cst de icms não existente");
				}
			}
		}else if(e.getSource().equals(txtCstCofins)) {
			if(txtCstCofins.getText().trim().isEmpty() || txtCstCofins.getText().equalsIgnoreCase("007")) {
				txtCstCofins.setText("007");
				txtAliquotCofins.setEnabled(false);
				txtTaxCofins.setEnabled(false);
				txtCstPis.requestFocus();
			}else {
				txtAliquotCofins.setEnabled(true);
				txtTaxCofins.setEnabled(true);			
				String cst = txtCstCofins.getText();
				boolean isCst = controller.verifyCofinsCst(cst);
				if(!isCst) {
					ShowMessage.errorMessage(this, "Erro", "Cst de cofins não existente");
				}
			}
		}else if(e.getSource().equals(txtCstIpi)) {
			if(txtCstIpi.getText().trim().isEmpty()) {
				txtCstIpi.setText("000");
			}else {
				String cst = txtCstCofins.getText();
				boolean isCst = controller.verifyIpiCst(cst);
				if(!isCst) {
					ShowMessage.errorMessage(this, "Erro", "Cst de ipi não existente");
				}
			}
		}else if(e.getSource().equals(txtCstPis) || txtCstCofins.getText().equalsIgnoreCase("007")) {
			if(txtCstPis.getText().trim().isEmpty()) {
				txtCstPis.setText("007");
				txtAliquotPis.setEnabled(false);
				txtTaxPis.setEnabled(false);
				txtFinalCost.requestFocus();
			}else {
				txtAliquotPis.setEnabled(true);
				txtTaxPis.setEnabled(true);
				String cst = txtCstCofins.getText();
				boolean isCst = controller.verifyPisCst(cst);
				if(!isCst) {
					ShowMessage.errorMessage(this, "Erro", "Cst de pis não existente");
				}
			}	
		}
	}
	
	/*
	 * Estrutura o cnpj da empresa que realizou o frete das mercadorias contidas na nota fiscal em questão.
	 */
	private void formatFreightCnpj() {
		if(txtFreightCnpj.getText().isEmpty())return;
		else if(txtFreightCnpj.getText().length() == 11) {
			String value = txtFreightCnpj.getText();
			value = value.substring(0, 3) + "." + value.substring(3, 6) + "." + value.substring(6, 9) + "-" + value.substring(9, 11);
			txtFreightCnpj.setText(value);
		}else if(txtFreightCnpj.getText().length() == 14) {
			String value = txtFreightCnpj.getText();
			value = value.substring(0, 2) + "." + value.substring(2, 5) + "." + value.substring(5, 8) + "/" + value.substring(8, 12) + "-" + value.substring(12, 14);
			txtFreightCnpj.setText(value);
		}else {
			ShowMessage.errorMessage(this, "Erro", "CPF ou CNPJ inválido!");
			txtFreightCnpj.setText("");
			txtFreightCnpj.requestFocus();
		}
	}
	
	private void fillMaterialCode() {
		Material m = (Material) cboMaterial.getSelectedItem();
		if(m == null)return;
		int materialCode = m.getId();
		int measureUnit = m.getMeasureUnit();
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "m");
		map.put(2, "m²");
		map.put(3, "m³");
		map.put(4, "cm");
		map.put(5, "cm²");
		map.put(6, "cm³");
		map.put(7, "mm");
		map.put(8, "mm²");
		map.put(9, "mm³");
		map.put(10, "unitário");
		map.put(11, "kg");
		map.put(12, "g");
		map.put(13, "mg");
		map.put(14, "pol");
		map.put(15, "pol²");
		map.put(16, "pol³");
		txtMaterialCod.setText(String.valueOf(materialCode));
		txtMeasureUnit.setText(map.get(measureUnit));
		txtNcm.setText(m.getNCM());
	}
	
	/*
	 * Verifica se a hora de saída do material referente a nota fiscal realmente existe.
	 */
	private void verifyHour() {
		if(txtExitHour.getText().replaceAll(":", "").trim().isEmpty()) {
			Date date = new Date();
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(date);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			String h = String.valueOf(hour);
			String m = String.valueOf(minute);
			if(h.length() == 1) h = "0" + h;
			if(m.length() == 1) m = "0" + m; 
			txtExitHour.setText(h + ":" + m);
		}else {
			if(txtExitHour.getText().substring(0, 2).trim().isEmpty())return;
			else if(txtExitHour.getText().substring(3, 5).trim().isEmpty())return;
			int hour = Integer.parseInt(txtExitHour.getText().substring(0, 2));
			int minute = Integer.parseInt(txtExitHour.getText().substring(3, 5));
			if(hour > 24 || hour <0) {
				ShowMessage.errorMessage(this, "Erro", "Não existe esse horário!");
				txtExitHour.setText("");
				txtExitHour.requestFocus(); 
				return;
			}else if(minute > 60 || minute < 0) {
				ShowMessage.errorMessage(this, "Erro", "Não existe esse horário!");
				txtExitHour.setText("");
				txtExitHour.requestFocus();
				return;
			}			
		}
	}
	
	/*
	 * Preenche os cnpj's de fornecedores.
	 */
	private void fillSupplierCnpj() {
		Supplier supplier = (Supplier) cboSuppliers.getSelectedItem();
		if(supplier == null)return;
		String cnpj = supplier.getCNPJ();
		String cnpjSeqOne = cnpj.substring(0, 2);
		String cnpjSeqTwo = cnpj.substring(2, 5);
		String cnpjSeqThree = cnpj.substring(5, 8);
		String cnpjSeqFour = cnpj.substring(8,12);
		String cnpjSeqFive = cnpj.substring(12, 14);
		cnpj = cnpjSeqOne + "." + cnpjSeqTwo + "." + cnpjSeqThree + "/" + cnpjSeqFour + "-" + cnpjSeqFive;
		txtcnpj.setText(cnpj);
		
	}
	
	/*
	 * Caso o focu do campo cfop seja perdido, a função popula os campos de CFOP com o mais convêncial caso nada seja inserido
	 * e caso algo tenha sido inserido verifica na base de dados se esse código realmente é valido.
	 */
	private void cfop(FocusEvent e) {
		if(e.getSource().equals(txtCFOP)) {
			if(txtCFOP.getText().isEmpty()) {
				txtCFOP.setText("5102");
				CFOPExit cfop = controller.getCFOP("5102");
				lblClassificationDescription.setText(cfop.getNat());
			}else {
				CFOPExit cfop = controller.getCFOP(txtCFOP.getText());
				if(cfop == null) {
					ShowMessage.errorMessage(this, "Erro", "Número de CFOP não existente");
					txtCFOP.requestFocus();
					txtCFOP.setText("");
					return;
				}
				lblClassificationDescription.setText(cfop.getNat());
			}
		}else if(e.getSource().equals(txtMaterialCfop)) {
			if(txtMaterialCfop.getText().trim().isEmpty()) {
				txtMaterialCfop.setText("5102");
				CFOPExit cfop = controller.getCFOP("5102");
				ShowMessage.successMessage(this, "CFOP", "CFOP - " + cfop.getNat());
			}else {
				CFOPExit cfop = controller.getCFOP(txtMaterialCfop.getText());
				if(cfop == null) {
					ShowMessage.errorMessage(this, "Erro", "Numero de CFOP não existente");
					txtMaterialCfop.requestFocus();
					txtMaterialCfop.setText("");
					return;
				}
				lblClassificationDescription.setText(cfop.getNat());
			}
		}
	}
	
	private void danfeSerial() {
		if(txtDanfeSerial.getText().isEmpty())txtDanfeSerial.setText("001");
	} 
	
	/*
	 * Verifica se algo foi preenchido no campo de modelo da nfe
	 * caso sim verifica se o código é valido, caso não seja notifica o usuário
	 * caso não popula o campo com o modelo de mais comum caso.
	 */
	private void nfeModel() {
		if(txtNfeModel.getText().isEmpty()) {
			txtNfeModel.setText("01");
		}else {
			HashMap<Integer, String> nfeModelMap = new HashMap<Integer, String>();
			nfeModelMap.put(01, "50 - Notas Fiscal, modelos 1 e 1-A");
			nfeModelMap.put(02, "60(ECF) - 61(não ECF), Nota Fiscal de Venda a Consumidor, modelo 2");
			nfeModelMap.put(04, "61 - Nota Fiscal de Produtor, modelo 4");
			nfeModelMap.put(06, "50 - Nota Fiscal/Conta de Energia Elétrica");
			nfeModelMap.put(07, "Nota Fiscal de de Serviço de Transporte");
			nfeModelMap.put(8, "70 - Conhecimento de Transporte Rodoviário Cargas, modelo 8 - Tomador do Serviço");
			Integer model = Integer.parseInt(txtNfeModel.getText());
			if(nfeModelMap.containsKey(model)) {
				String Modelmeta = nfeModelMap.get(model);
			}else {
				ShowMessage.errorMessage(this, "Erro", "Código do modelo não conhecido");
				txtNfeModel.setText("");
				txtNfeModel.requestFocus();
			}
		}
	}
}
