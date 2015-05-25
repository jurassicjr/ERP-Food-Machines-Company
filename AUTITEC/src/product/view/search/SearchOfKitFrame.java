package product.view.search;

import java.awt.BorderLayout;
import java.awt.ScrollPane;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JButton;

import product.controller.SearchOfKitControlller;


import java.awt.Font;

import javax.swing.ListSelectionModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.xml.ws.handler.MessageContext.Scope;

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
	public SearchOfKitFrame() {
		
		controller = new SearchOfKitControlller();
		initialize();
	}
	private void initialize() {
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Consulta de Kits");
		util.Icon.setIcon(this);
		setBounds(0, 0, 478, 407);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		initializePrincipal();
		controller.queryAll(table);
		DefaultTableModel modelo  = (DefaultTableModel) table.getModel();
		modelo.addRow(new Object[]{"aaaa","aaa"});
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

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		String[] header = new String[] {"Nome do kit", "Descrição"};
		table.setModel(new DefaultTableModel(null,header){
			  private static final long serialVersionUID = 6737039895901587009L;
				
	            boolean[] columnEditables = new boolean[] {
						false, false
				};
				
				@Override
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
				
			});
		
		JList list = new JList();
		GroupLayout gl_pnPrincipal = new GroupLayout(pnPrincipal);
		gl_pnPrincipal.setHorizontalGroup(
			gl_pnPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pnPrincipal.createSequentialGroup()
					.addGroup(gl_pnPrincipal.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_pnPrincipal.createSequentialGroup()
							.addContainerGap()
							.addComponent(list, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_pnPrincipal.createSequentialGroup()
							.addContainerGap()
							.addComponent(table, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_pnPrincipal.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_pnPrincipal.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_pnPrincipal.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldProductName, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
									.addGap(14)
									.addComponent(btnSearchProduct)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddProduct)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRemoveProduct))
								.addGroup(gl_pnPrincipal.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(labelDescription)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textFieldKitName, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSearch))
								.addComponent(lbProduct))))
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(list, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		pnPrincipal.setLayout(gl_pnPrincipal);
		
	}
}

