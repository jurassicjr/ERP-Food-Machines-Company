package sales.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.DecimalFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import model.Client;
import model.Kit;
import model.Material;
import model.Product;
import userInterface.components.JNumberFormatField;

public class PTC extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2031745677196812709L;
	private JPanel principalPanel;
	private JTextField txtRastreabilityCode;
	private JTextField txtTitle;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JLabel lblRastreabilityCode;
	private JLabel lblClient;
	private JComboBox<Client> cboClient;
	private JLabel lblTitle;
	private JLabel lblProducts;
	private JLabel lblServio;
	private JComboBox cboService;
	private JButton btnAddService;
	private JScrollPane scrollPane_3;
	private JLabel lblUpload;
	private JTextField txtUpload;
	private JButton btnPath;
	private JLabel lblBruteValue;
	private JTextField txtBruteValue;

	public PTC() {
		initialize();
	}

	private void initialize() {
		  setTitle("Registro de proposta técnica comercial!");
		  setMinimumSize(new Dimension(400, 400));
		  setPreferredSize(new Dimension(400,400));
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
	    
	    lblTitle = new JLabel("Título");
	    
	    txtTitle = new JTextField();
	    txtTitle.setColumns(10);
	    
	    lblProducts = new JLabel("Produtos");
	    
	    JComboBox<Product> cboProduct = new JComboBox<Product>();
	    
	    JButton btnAdd = new JButton("Adicionar");
	    btnAdd.setIcon(new ImageIcon(PTC.class.getResource("/resources/plus.png")));
	    
	    JScrollPane scrollPane = new JScrollPane();
	    
	    JLabel lblKits = new JLabel("Kits");
	    
	    JComboBox<Kit> cboKits = new JComboBox<Kit>();
	    
	    JButton btnAddKit = new JButton("Adicionar");
	    btnAddKit.setIcon(new ImageIcon(PTC.class.getResource("/resources/plus.png")));
	    
	    JScrollPane scrollPane_1 = new JScrollPane();
	    
	    JLabel lblMaterial = new JLabel("Material");
	    
	    JScrollPane scrollPane_2 = new JScrollPane();
	    
	    JComboBox<Material> cboMaterial = new JComboBox<Material>();
	    
	    JButton btnAddMaterial = new JButton("Adicionar");
	    
	    btnAddMaterial.setIcon(new ImageIcon(PTC.class.getResource("/resources/plus.png")));
	    
	    lblServio = new JLabel("Serviço");
	    
	    cboService = new JComboBox();
	    
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
	    						.addComponent(btnAdd)))
	    				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblKits)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboKits, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(btnAddKit))
	    				.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblMaterial)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(btnAddMaterial))
	    				.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblServio)
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
	    					.addComponent(txtBruteValue, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
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
	    				.addComponent(btnAdd))
	    			.addGap(18)
	    			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblKits)
	    				.addComponent(cboKits, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(btnAddKit))
	    			.addGap(20)
	    			.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblMaterial)
	    				.addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(btnAddMaterial))
	    			.addGap(20)
	    			.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblServio)
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
	    			.addContainerGap(100, Short.MAX_VALUE))
	    );
	    gl_principalPanel.linkSize(SwingConstants.VERTICAL, new Component[] {scrollPane, scrollPane_1, scrollPane_2, scrollPane_3});
	    
	    table_2 = new JTable();
	    scrollPane_2.setViewportView(table_2);
	    
	    table_1 = new JTable();
	    scrollPane_1.setViewportView(table_1);
	    
	    table = new JTable();
	    scrollPane.setViewportView(table);
	    principalPanel.setLayout(gl_principalPanel);
	    
	    initializeSub();
    }

	private void initializeSub() {
	    JPanel subPanel = new JPanel();
	    subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(subPanel, BorderLayout.SOUTH);
	    
	    JButton btnCancel = new JButton("Cancelar");
	    btnCancel.setIcon(new ImageIcon(PTC.class.getResource("/resources/cancel.png")));
	    JButton btnConfirm = new JButton("Confirmar");
	    btnConfirm.setIcon(new ImageIcon(PTC.class.getResource("/resources/ok.png")));
	    JButton btnClear = new JButton("Limpar");
	    btnClear.setIcon(new ImageIcon(PTC.class.getResource("/resources/ClearFrame.png")));
	    
	    subPanel.add(btnClear);
	    subPanel.add(btnCancel);
	    subPanel.add(btnConfirm);
    }
}
