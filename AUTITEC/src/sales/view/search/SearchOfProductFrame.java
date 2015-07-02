package sales.view.search;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SingleSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import product.view.UpdateOfProductFrame;
import product.view.search.SearchOfKitFrame;
import model.Product;
import model.Session;
import sales.controller.SearchOfProductController;
import util.ShowMessage;

public class SearchOfProductFrame extends JFrame{

	/**
	 * 
	 */
    private static final long serialVersionUID = 1731153376035329416L;
    private JPanel principalPanel;
	private JTextField txtName;
	private JTable table;
	private JButton btnSearch;
	private JLabel lblName;
	private SearchOfProductController controller;
	private JFrame frame = this;
	private Product selectedProduct;
	private SearchOfKitFrame requesterFrame;
	private JTextField textFieldProduct;
	
	public void setTextFieldProduct(JTextField tf)
	{
		textFieldProduct = tf;	
	}
	
	public void setRequesterFrame(SearchOfKitFrame reqFrame)
	{
		
		requesterFrame = reqFrame;
	}
	public Product getSelectedProduct()
	{
		return selectedProduct;
		
	}
	public void setSeletectedProduct(Product product)
	{
		product = selectedProduct;
		
	}
	
	public SearchOfProductFrame() {
		controller = new SearchOfProductController();
		initialize();
		setListeners();
	}

	private void initialize() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Consulta de Produtos");
		setBounds(0, 0, 478, 382);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		initializePrincipal();
		controller.queryAll(table);
		
	}
    
	
	private void initializePrincipal() {
		principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);
		
		lblName = new JLabel("Nome");
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		btnSearch = new JButton("Buscar");
		btnSearch.setSelectedIcon(new ImageIcon(SearchOfMaterialFrame.class.getResource("/resources/ok.png")));
		
		
		   KeyListener txtKeyListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getSource().equals(txtName)){
					if(txtName.getText().isEmpty())
						controller.queryAll(table);	
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getSource().equals(txtName)){
					
					if(e.getKeyChar() == KeyEvent.VK_ENTER)
						verify();
				}
			}
		   };
		txtName.addKeyListener(txtKeyListener);
		
		JScrollPane scrollPane = new JScrollPane();
		
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
					.addGap(44))
		);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] header = new String[] {"Produto", "Descrição"};
		table.setModel(new DefaultTableModel(null, header) {

			
            /**
			 * 
			 */
            private static final long serialVersionUID = 6737039895901587009L;
			
            boolean[] columnEditables = new boolean[] {
					false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		principalPanel.setLayout(gl_principalPanel);
	}

	private void setListeners() {
		table.addMouseListener(mouseListener);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeFrame(frame);
			}
			
		});
		
		
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnSearch))verify();
			}
		};
		btnSearch.addActionListener(buttonListener);
	}

	private void verify() {
		String name = txtName.getText();
		if(!name.isEmpty()) 
			controller.simpleSearch(table, name);	
		else
			controller.queryAll(table);
			
	}
	
	
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
						   ShowMessage.errorMessage(null,"Solicite permissão"," Você não tem permissão para atualizar/excluir produtos");
					   else
					   {
						      UpdateOfProductFrame fr =  new UpdateOfProductFrame();
							  String productName = table.getModel().getValueAt(line,0).toString();
							  Product product  = controller.getProductByName(productName);
							  fr.setSelectedProduct(product);
							  fr.addWindowListener(windowListener);
							  fr.setVisible(true);
							  
						 
					   }
					   
					}
				}
			}
				
	};
	
	
	
	
	public void setProductSelectable(Boolean flag)
	{
		if(flag)
		{
			SearchOfProductFrame fr = this;
			MouseListener ac = new MouseListener() {
				
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
							  String productName = 	table.getModel().getValueAt(line,0).toString();
							  selectedProduct = controller.getProductByName(productName);
							  textFieldProduct.setText(selectedProduct.getId()+" - "+selectedProduct.getName());
							  frame.dispose();
							}
						}
					}
						
			};
			table.addMouseListener(ac);
			
		}
		
	}
	WindowListener windowListener = new WindowListener() {
		
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			controller.queryAll(table);
			
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			
			
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
}
