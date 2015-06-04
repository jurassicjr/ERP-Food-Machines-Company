package sales.view.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;

import sales.controller.SearchOfSupplierController;
import userInterface.components.UpperTextField;
import util.Icon;

import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class SearchOfSupplierFrame extends JDialog
{
	private JTextField txtCorporateName;
	private JTextField txtProductName;
	private JTable tableSuppliers;
	private JTextField txtCNPJ;
	private SearchOfSupplierController controller;
	private JButton btnSearch;
	private JButton btnSearchProduct;
	private String searchParam;
	private String searchParamName="";
	public SearchOfSupplierFrame()
	{
		controller = new SearchOfSupplierController();
		initialize();
		setListeners();	
	}
	
	public void initialize()
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Consulta de fornecedor");
		setBounds(0, 0, 517, 500);
		Icon.setIcon(this);
		initializePrincipal();
		setLocationRelativeTo(null);
		txtCNPJ.setColumns(14);
		JScrollPane scrollPane = new JScrollPane();
		controller.queryAll(tableSuppliers);
		scrollPane.setViewportView(tableSuppliers);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 481, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 481, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))
		);
		getContentPane().setLayout(groupLayout);
		
	}
	public void initializePrincipal()
	{
		
		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblCompanyName = new JLabel("Razão social");
		
		txtCorporateName = new UpperTextField();
		txtCorporateName.setColumns(10);
		
		JLabel lblProductName = new JLabel("Fornecedor do produto");
		
		txtProductName = new JTextField();
		txtProductName.setEnabled(false);
		txtProductName.setColumns(10);
		
		btnSearchProduct = new JButton("Buscar Produto");
		
		btnSearch = new JButton("Buscar");
		
		JLabel lblCnpj = new JLabel("Cnpj");
		
		txtCNPJ = new JTextField();
		txtCNPJ.setColumns(10);
		
		btnClearSelectedProduct = new JButton("Limpar");
		btnClearSelectedProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCompanyName, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCorporateName, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE))
							.addGap(35)
							.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblCnpj, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCNPJ, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProductName, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(txtProductName, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnSearchProduct, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addComponent(btnClearSelectedProduct, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblCompanyName)
							.addGap(4)
							.addComponent(txtCorporateName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addGap(2)
					.addComponent(lblCnpj)
					.addGap(3)
					.addComponent(txtCNPJ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(lblProductName)
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(txtProductName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSearchProduct)
						.addComponent(btnClearSelectedProduct)))
		);
		panel.setLayout(gl_panel);
		tableSuppliers = new JTable();
		tableSuppliers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
		FocusListener focusListener = new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(e.getSource().equals(txtCorporateName))
				{
					searchParamName = "corporate_name";
					searchParam = txtCorporateName.getText();
					txtCNPJ.setText("");
					txtProductName.setText("");
				}
				if(e.getSource().equals(txtCNPJ))
				{
					
						searchParamName = "cnpj";
						searchParam = txtCNPJ.getText();
						txtCorporateName.setText("");
						txtProductName.setText("");
				}
				else
				if(e.getSource().equals(txtProductName))	
				{
					searchParamName = "productName";
					searchParam = txtProductName.getText();
					txtCNPJ.setText("");
					txtCorporateName.setText("");
				}
				
			}
		};
		KeyListener keyListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getSource().equals(txtCNPJ))
					if(!Character.isDigit(e.getKeyChar()) || txtCNPJ.getText().length() >14)
						e.setKeyChar('\0');
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				if(e.getSource() instanceof JTextField)
				{
					JTextField tf = (JTextField) e.getSource();
					if(tf.getText().isEmpty())
						controller.queryAll(tableSuppliers);
				}
					if(e.getSource().equals(txtCorporateName))
					{
						searchParamName = "corporate_name";
						searchParam = txtCorporateName.getText();
						txtCNPJ.setText("");
						txtProductName.setText("");
					}
					else
					if(e.getSource().equals(txtCNPJ))
					{
							searchParamName = "cnpj";
							searchParam = txtCNPJ.getText();
							txtCorporateName.setText("");
							txtProductName.setText("");
					}
					else
					if(e.getSource().equals(txtProductName))	
					{
						searchParamName = "productName";
						searchParam = txtProductName.getText();
						txtCNPJ.setText("");
						txtCorporateName.setText("");
					}	
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER)
					controller.search(tableSuppliers,searchParam,searchParamName);
			}
		};
	
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnSearch))
				{
					if(!txtProductName.getText().isEmpty())
						searchParam = txtProductName.getText();
					
					if(searchParam == null )
						searchParam = "";
					
						controller.search(tableSuppliers,searchParam,searchParamName);
				}
					else
				if(e.getSource().equals(btnSearchProduct))	
				{
					searchParamName = "productName";
					SearchOfProductFrame productFrame = new SearchOfProductFrame();
					productFrame.setLocationRelativeTo(null);
					productFrame.setProductSelectable(true);
					productFrame.setTextFieldProduct(txtProductName);
					productFrame.setVisible(true);
					
				}
				else
				if(e.getSource().equals(btnClearSelectedProduct))
				{
					txtProductName.setText("");
					searchParamName = "";
					searchParam = "";
					controller.queryAll(tableSuppliers);
				}
			}
		};
		private JButton btnClearSelectedProduct;
		private JPanel panel;
	public void setListeners()
	{
	   txtCNPJ.addKeyListener(keyListener);
	   txtCorporateName.addKeyListener(keyListener);
	   txtProductName.addKeyListener(keyListener);
	   
	   btnSearch.addActionListener(buttonListener);
	   btnSearchProduct.addActionListener(buttonListener);
	   btnClearSelectedProduct.addActionListener(buttonListener);
	   
	   txtCNPJ.addFocusListener(focusListener);
	   txtCorporateName.addFocusListener(focusListener);
	   txtProductName.addFocusListener(focusListener);
	}
}
