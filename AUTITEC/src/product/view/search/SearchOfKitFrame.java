package product.view.search;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

import model.Product;
import model.Session;
import product.controller.SearchOfKitControlller;
import product.view.UpdateOfProductFrame;
import sales.view.search.SearchOfProductFrame;
import util.Icon;
import util.ShowMessage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class SearchOfKitFrame extends  JDialog 
{
	private JTextField textFieldKitName;
	private JLabel lbProduct;
	private JButton btnAddProduct;
	private JButton btnSearch;
	private JButton btnRemoveProduct;
	private JButton btnSearchProduct;
	private JTextField textFieldProductName;
	private SearchOfKitControlller controller;
	private JTable table;
	private JScrollPane scrollPane_2;
	private Product selectedProduct;
	private JList productsList;
	private SearchOfKitFrame thisFrame = this;
	private Vector vetProduct;

	KeyListener keyListener = new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getSource().equals(textFieldKitName))
				if(textFieldKitName.getText().isEmpty())
					controller.search(table,textFieldKitName.getText(),vetProduct);				
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getSource().equals(textFieldKitName))
				if(e.getKeyChar() == KeyEvent.VK_ENTER)
					controller.search(table,textFieldKitName.getText(),vetProduct);	
		}
	};
	
	public Product getSelectedProduct()
	{
		return selectedProduct;
	}
	public SearchOfKitFrame() {
		
		controller = new SearchOfKitControlller();
		initialize();
		setListeners();
	}
	private void initialize() {
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Consulta de Kits");
		setBounds(0, 0, 478, 407);
		Icon.setIcon(this);
		setLocationRelativeTo(null);
		initializePrincipal();
		controller.queryAll(table);
		textFieldProductName.setEnabled(false);
		vetProduct = new Vector<Product>();
	}
	public void setListeners()
	{
		btnSearchProduct.addActionListener(buttonListener);
		textFieldKitName.addKeyListener(keyListener);
		btnAddProduct.addActionListener(buttonListener);
		btnRemoveProduct.addActionListener(buttonListener);
		btnSearch.addActionListener(buttonListener);
	}
	private void initializePrincipal() 
	{
		JPanel pnPrincipal = new JPanel();
		getContentPane().add(pnPrincipal, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_principalPanel = new GroupLayout(this);
		pnPrincipal.add(scrollPane);
		JLabel labelDescription = new JLabel("Nome do kit");
		
		textFieldKitName = new JTextField();
		textFieldKitName.setColumns(10);
		
		btnSearch = new JButton("Buscar");
		
		lbProduct = new JLabel("Que contenham os produtos");
		
		textFieldProductName = new JTextField();
		textFieldProductName.setColumns(10);
		
		btnSearchProduct = new JButton("Buscar Produto");
		
		
		btnAddProduct = new JButton("+");
		btnAddProduct.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		btnRemoveProduct = new JButton("-");
		btnRemoveProduct.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		scrollPane_2 = new JScrollPane();
		
		scrollPane_1 = new JScrollPane();
		GroupLayout gl_pnPrincipal = new GroupLayout(pnPrincipal);
		gl_pnPrincipal.setHorizontalGroup(
			gl_pnPrincipal.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnPrincipal.createSequentialGroup()
					.addGroup(gl_pnPrincipal.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnPrincipal.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_pnPrincipal.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnPrincipal.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(labelDescription)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textFieldKitName, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSearch))
								.addComponent(lbProduct)
								.addGroup(gl_pnPrincipal.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldProductName, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
									.addGap(14)
									.addComponent(btnSearchProduct)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddProduct)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRemoveProduct))))
						.addGroup(gl_pnPrincipal.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
						.addGroup(gl_pnPrincipal.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_pnPrincipal.setVerticalGroup(
			gl_pnPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnPrincipal.createSequentialGroup()
					.addGroup(gl_pnPrincipal.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnPrincipal.createSequentialGroup()
							.addGap(18)
							.addComponent(labelDescription)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lbProduct))
						.addGroup(gl_pnPrincipal.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_pnPrincipal.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSearch)
								.addComponent(textFieldKitName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(1)
					.addGroup(gl_pnPrincipal.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRemoveProduct)
						.addComponent(btnAddProduct)
						.addComponent(btnSearchProduct)
						.addComponent(textFieldProductName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setFillsViewportHeight(true);
		
				
		productsList = new JList();
		scrollPane_2.setViewportView(productsList);
		pnPrincipal.setLayout(gl_pnPrincipal);
		
	}
	
	ActionListener buttonListener = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnSearchProduct))
			{
				SearchOfProductFrame productFrame  = new SearchOfProductFrame();
				productFrame.setTextFieldProduct(textFieldProductName);
				productFrame.setProductSelectable(true);
				productFrame.setVisible(true);
			}
			else
			if(e.getSource().equals(btnAddProduct))
			{
				if(!textFieldProductName.getText().isEmpty())
				{
					String productName = textFieldProductName.getText().split("-")[1].trim();
					controller.addProduct(productName, vetProduct);
					productsList.setListData(vetProduct);
					textFieldProductName.setText("");
				}		
			}
			else
				if(e.getSource().equals(btnRemoveProduct))
				{
				  if(productsList.getModel().getSize() > 0)
				  {
					Integer selectedIndex = productsList.getSelectedIndex();
					if(selectedIndex > -1)
					{
						controller.removeProduct(vetProduct,selectedIndex);
						productsList.setListData(vetProduct);
					}
				  }
				   
				    if(productsList.getModel().getSize() == 0)
				    	controller.search(table,textFieldProductName.getText(),vetProduct);  
				}
				else
					if(e.getSource().equals(btnSearch))
						controller.search(table,textFieldKitName.getText(),vetProduct);
		}
	};
	
	private JScrollPane scrollPane_1;
	MouseListener mouseListener = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2)
			{	
					int line = table.getSelectedRow();
					if(line > -1)
					{
					   if(!Session.getInstance().havePermission("UPD_PROD"))
						   ShowMessage.errorMessage(null,"Solicite permissão"," Você não tem permissão para atualizar/excluir kits");
					   else
					   {
						   
						 
					   }
					   
					}
				}
			}
				
	};
	

}

