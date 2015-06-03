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

public class SearchOfSupplierFrame extends JDialog
{
	private JTextField textFieldCorporateName;
	private JTextField textFieldProductName;
	private JTable supplierTable;
	private JTextField textFieldCNPJ;
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
		textFieldCNPJ.setColumns(14);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 206, 481, 244);
		getContentPane().add(scrollPane);
		controller.queryAll(supplierTable);
		scrollPane.setViewportView(supplierTable);
		
	}
	public void initializePrincipal()
	{
		
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 481, 184);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblRazoSocial = new JLabel("Razão social");
		lblRazoSocial.setBounds(10, 11, 118, 14);
		panel.add(lblRazoSocial);
		
		textFieldCorporateName = new UpperTextField();
		textFieldCorporateName.setBounds(10, 29, 337, 20);
		panel.add(textFieldCorporateName);
		textFieldCorporateName.setColumns(10);
		
		JLabel lblFornecedorDoProduto = new JLabel("Fornecedor do produto");
		lblFornecedorDoProduto.setBounds(10, 107, 135, 14);
		panel.add(lblFornecedorDoProduto);
		
		textFieldProductName = new JTextField();
		textFieldProductName.setEnabled(false);
		textFieldProductName.setBounds(10, 132, 230, 20);
		panel.add(textFieldProductName);
		textFieldProductName.setColumns(10);
		
		btnSearchProduct = new JButton("Buscar Produto");
		btnSearchProduct.setBounds(250, 131, 124, 23);
		panel.add(btnSearchProduct);
		
		btnSearch = new JButton("Buscar");
		btnSearch.setBounds(382, 11, 89, 47);
		panel.add(btnSearch);
		
		JLabel CNPJ = new JLabel("Cnpj");
		CNPJ.setBounds(10, 60, 46, 14);
		panel.add(CNPJ);
		
		textFieldCNPJ = new JTextField();
		textFieldCNPJ.setBounds(10, 77, 222, 20);
		panel.add(textFieldCNPJ);
		textFieldCNPJ.setColumns(10);
		
		btnClearProduct = new JButton("Limpar");
		btnClearProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClearProduct.setBounds(382, 131, 89, 23);
		panel.add(btnClearProduct);
		supplierTable = new JTable();
		supplierTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
		FocusListener focusListener = new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(e.getSource().equals(textFieldCorporateName))
				{
					searchParamName = "corporate_name";
					searchParam = textFieldCorporateName.getText();
					textFieldCNPJ.setText("");
					textFieldProductName.setText("");
				}
				if(e.getSource().equals(textFieldCNPJ))
				{
					
						searchParamName = "cnpj";
						searchParam = textFieldCNPJ.getText();
						textFieldCorporateName.setText("");
						textFieldProductName.setText("");
				}
				else
				if(e.getSource().equals(textFieldProductName))	
				{
					searchParamName = "productName";
					searchParam = textFieldProductName.getText();
					textFieldCNPJ.setText("");
					textFieldCorporateName.setText("");
				}
				
			}
		};
		KeyListener keyListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getSource().equals(textFieldCNPJ))
					if(!Character.isDigit(e.getKeyChar()) || textFieldCNPJ.getText().length() >14)
						e.setKeyChar('\0');
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				if(e.getSource() instanceof JTextField)
				{
					JTextField tf = (JTextField) e.getSource();
					if(tf.getText().isEmpty())
						controller.queryAll(supplierTable);
				}
					if(e.getSource().equals(textFieldCorporateName))
					{
						searchParamName = "corporate_name";
						searchParam = textFieldCorporateName.getText();
						textFieldCNPJ.setText("");
						textFieldProductName.setText("");
					}
					else
					if(e.getSource().equals(textFieldCNPJ))
					{
							searchParamName = "cnpj";
							searchParam = textFieldCNPJ.getText();
							textFieldCorporateName.setText("");
							textFieldProductName.setText("");
					}
					else
					if(e.getSource().equals(textFieldProductName))	
					{
						searchParamName = "productName";
						searchParam = textFieldProductName.getText();
						textFieldCNPJ.setText("");
						textFieldCorporateName.setText("");
					}	
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER)
					controller.search(supplierTable,searchParam,searchParamName);
			}
		};
	
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnSearch))
				{
					if(!textFieldProductName.getText().isEmpty())
						searchParam = textFieldProductName.getText();
					
					if(searchParam == null )
						searchParam = "";
					
						controller.search(supplierTable,searchParam,searchParamName);
				}
					else
				if(e.getSource().equals(btnSearchProduct))	
				{
					searchParamName = "productName";
					SearchOfProductFrame productFrame = new SearchOfProductFrame();
					productFrame.setLocationRelativeTo(null);
					productFrame.setProductSelectable(true);
					productFrame.setTextFieldProduct(textFieldProductName);
					productFrame.setVisible(true);
					
				}
				else
				if(e.getSource().equals(btnClearProduct))
				{
					textFieldProductName.setText("");
					searchParamName = "";
					searchParam = "";
					controller.queryAll(supplierTable);
				}
			}
		};
		private JButton btnClearProduct;
	public void setListeners()
	{
	   textFieldCNPJ.addKeyListener(keyListener);
	   textFieldCorporateName.addKeyListener(keyListener);
	   textFieldProductName.addKeyListener(keyListener);
	   
	   btnSearch.addActionListener(buttonListener);
	   btnSearchProduct.addActionListener(buttonListener);
	   btnClearProduct.addActionListener(buttonListener);
	   
	   textFieldCNPJ.addFocusListener(focusListener);
	   textFieldCorporateName.addFocusListener(focusListener);
	   textFieldProductName.addFocusListener(focusListener);
	}
}
