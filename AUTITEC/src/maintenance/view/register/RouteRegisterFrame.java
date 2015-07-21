package maintenance.view.register;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

import maintenance.controller.RouteRegisterController;
import model.Client;
import model.Employee;
import model.Product;
import model.Route;
import model.RouteDestination;
import model.RouteProduct;
import model.Session;
import model.User;
import model.Vehicle;
import net.sf.nachocalendar.components.DateField;
import userInterface.components.FrameController;
import userInterface.components.RouteDestinationTreeNodeRenderer;
import userInterface.components.UpperTextField;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class RouteRegisterFrame extends JFrame
{
	private JPanel panelRouteData;
	private JLabel lblLbvehicle ;
	private JComboBox<Vehicle> cboVehicle;
	private JLabel lblDescription;
	private JLabel lblConductor;
	private JComboBox<Employee> cboConductor;
	private JPanel panelPaths;
	private JRadioButton rdbtnClient;
	private JRadioButton rdbtnOther;
	private JComboBox<Client> cboClient;
	private JLabel lblProduct;
	private JComboBox<Product> cboProduct;
	private JTextField txtOther;
	private JTextField txtQuantity;
	private JTextField txtDescription;
	private DateField txtDate;
	private JTextField txtInitialHodometer;
	private JLabel lblQuantity;
	private JList<RouteProduct> productList;
	private JButton btnAddProduct;
	private JButton btnRemoveProduct;
	private JButton btnAddDestination;
	private JLabel lblDate;
	private JLabel lblInitialHodometer;
	private JTree treeDestinies;
	private JButton btnCancel;
	private JButton btnConfirm;
	private RouteRegisterController controller;
	private String frameName = "Registro de trajeto";
	private ButtonGroup groupdestination;
	private RouteRegisterFrame thisFrame = this;
	private DefaultMutableTreeNode rootNode;
	private Vector<RouteProduct> vetProducts;
	private JButton btnRemoveDestination;
	public RouteRegisterFrame() 
	{
		controller = new RouteRegisterController();
		initialize();
		setListeners();
	}
	public void initialize()
	{	
		Icon.setIcon(this);
		setTitle(frameName);
		setBounds(100, 100, 786, 550);
		setMinimumSize(new Dimension(786, 550));
		setPreferredSize(new Dimension(786, 550));
		initializePrincipal();
		txtOther.setEnabled(false);
		cboClient.setEnabled(false);
		btnConfirm.setEnabled(false);
		controller.fillVehicleCbo(cboVehicle);
		controller.fillEmployeeCbo(cboConductor);
		controller.fillClientCbo(cboClient);
		controller.fillProductCbo(cboProduct);
		rootNode = new DefaultMutableTreeNode("Destinos");
		treeDestinies.setModel(new DefaultTreeModel(rootNode));
		vetProducts = new Vector<RouteProduct>();
		treeDestinies.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	}
	
	private void initializePrincipal()
	{
		
		panelRouteData = new JPanel();
		panelRouteData.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Detalhes do trajeto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		groupdestination = new ButtonGroup();
		lblLbvehicle = new JLabel("Veículo");
		
		cboVehicle = new JComboBox<Vehicle>();
		
		lblDescription = new JLabel("Descrição");
		
		lblConductor = new JLabel("Condutor");
		
		cboConductor = new JComboBox<Employee>();
		
		panelPaths = new JPanel();
		panelPaths.setBorder(new TitledBorder(null, "Novo destino", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		rdbtnClient = new JRadioButton("Cliente");

		rdbtnOther = new JRadioButton("Outro");
		
		groupdestination.add(rdbtnClient);
		groupdestination.add(rdbtnOther);
		cboClient = new JComboBox<Client>();
		
		txtOther = new UpperTextField();
		txtOther.setColumns(10);
		
		lblProduct = new JLabel("Produto");
		
		cboProduct = new JComboBox<Product>();
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		
		lblQuantity = new JLabel("Quantidade");
		
		productList = new JList<RouteProduct>();
		
		btnAddProduct = new JButton("Adicionar Produto");
		btnAddProduct.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/plus.png")));
		
		btnRemoveProduct = new JButton("Remover Produto");
		btnRemoveProduct.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/clear.png")));
		
		btnAddDestination = new JButton("Adicionar destino");
		btnAddDestination.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/plus.png")));
		
		
		txtDescription = new UpperTextField();
		txtDescription.setColumns(10);
		
		lblDate = new JLabel("Data");
		
		txtDate = new DateField();
		
		txtInitialHodometer = new JTextField();
		txtInitialHodometer.setColumns(10);
		
		lblInitialHodometer = new JLabel("Hodômetro inicial");
		

		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/cancel.png")));
		
		btnConfirm = new JButton("Confirmar");
		btnConfirm.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/ok.png")));
		
		treeDestinies = new JTree();
		RouteDestinationTreeNodeRenderer nodeRenderer = new RouteDestinationTreeNodeRenderer();
		treeDestinies.setCellRenderer(nodeRenderer);
		
		btnRemoveDestination = new JButton("Remover destino");
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelRouteData, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(79)
							.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
						.addComponent(treeDestinies, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
						.addComponent(btnRemoveDestination, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panelRouteData, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(treeDestinies, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemoveDestination)
							.addGap(51)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnCancel)
								.addComponent(btnConfirm)))))
		);
		GroupLayout gl_panelPaths = new GroupLayout(panelPaths);
		gl_panelPaths.setHorizontalGroup(
			gl_panelPaths.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPaths.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelPaths.createParallelGroup(Alignment.TRAILING)
						.addComponent(productList, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelPaths.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_panelPaths.createSequentialGroup()
								.addComponent(rdbtnClient)
								.addGap(6)
								.addComponent(cboClient, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelPaths.createSequentialGroup()
								.addComponent(rdbtnOther, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(txtOther, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelPaths.createSequentialGroup()
								.addComponent(lblProduct, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGap(249)
								.addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelPaths.createSequentialGroup()
								.addComponent(cboProduct, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
								.addGap(15)
								.addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelPaths.createSequentialGroup()
								.addComponent(btnAddProduct, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnRemoveProduct, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(10, Short.MAX_VALUE))
		);
		gl_panelPaths.setVerticalGroup(
			gl_panelPaths.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPaths.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_panelPaths.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnClient)
						.addGroup(gl_panelPaths.createSequentialGroup()
							.addGap(1)
							.addComponent(cboClient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(13)
					.addGroup(gl_panelPaths.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnOther)
						.addGroup(gl_panelPaths.createSequentialGroup()
							.addGap(1)
							.addComponent(txtOther, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(7)
					.addGroup(gl_panelPaths.createParallelGroup(Alignment.LEADING)
						.addComponent(lblProduct)
						.addComponent(lblQuantity))
					.addGap(2)
					.addGroup(gl_panelPaths.createParallelGroup(Alignment.LEADING)
						.addComponent(cboProduct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panelPaths.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddProduct, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRemoveProduct, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(productList, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelPaths.setLayout(gl_panelPaths);
		GroupLayout gl_panelRouteData = new GroupLayout(panelRouteData);
		gl_panelRouteData.setHorizontalGroup(
			gl_panelRouteData.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRouteData.createSequentialGroup()
					.addGap(4)
					.addComponent(lblLbvehicle, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelRouteData.createSequentialGroup()
					.addGap(4)
					.addComponent(cboVehicle, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelRouteData.createSequentialGroup()
					.addGap(4)
					.addComponent(lblDescription))
				.addGroup(gl_panelRouteData.createSequentialGroup()
					.addGap(4)
					.addComponent(txtDescription, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelRouteData.createSequentialGroup()
					.addGap(4)
					.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(78)
					.addComponent(lblInitialHodometer, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelRouteData.createSequentialGroup()
					.addGap(4)
					.addComponent(txtDate, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(txtInitialHodometer, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelRouteData.createSequentialGroup()
					.addGap(4)
					.addComponent(lblConductor, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelRouteData.createSequentialGroup()
					.addGap(4)
					.addComponent(cboConductor, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelRouteData.createSequentialGroup()
					.addGap(113)
					.addComponent(btnAddDestination, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelRouteData.createSequentialGroup()
					.addGap(4)
					.addComponent(panelPaths, GroupLayout.PREFERRED_SIZE, 388, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelRouteData.setVerticalGroup(
			gl_panelRouteData.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRouteData.createSequentialGroup()
					.addGap(5)
					.addComponent(lblLbvehicle)
					.addGap(1)
					.addComponent(cboVehicle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblDescription)
					.addGap(4)
					.addComponent(txtDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_panelRouteData.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDate)
						.addComponent(lblInitialHodometer))
					.addGroup(gl_panelRouteData.createParallelGroup(Alignment.LEADING)
						.addComponent(txtDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtInitialHodometer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addComponent(lblConductor)
					.addGap(3)
					.addComponent(cboConductor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(panelPaths, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(btnAddDestination))
		);
		panelRouteData.setLayout(gl_panelRouteData);
		getContentPane().setLayout(groupLayout);
		
	}
	private void setListeners()
	{
		ActionListener ButtonActions = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource().equals(btnCancel))
					FrameController.close(thisFrame, frameName);
				else
				if(e.getSource().equals(btnAddDestination))
					addDestination();
				else
				if(e.getSource().equals(btnAddProduct))
					addProduct();
				else
				if(e.getSource().equals(btnRemoveProduct))
					removeProduct();
				else
				if(e.getSource().equals(btnConfirm))
					insertRoute();
				else
				if(e.getSource().equals(btnRemoveDestination))
					removeDestination();
				
					
			}
		};
		
		ActionListener radioButtonActions = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(rdbtnClient))
				{
					cboClient.setEnabled(true);
					txtOther.setEnabled(false);
					txtOther.setText("");
				}
				else
				if(e.getSource().equals(rdbtnOther))
				{
					cboClient.setEnabled(false);
					cboClient.setSelectedItem(null);
					txtOther.setEnabled(true);
				}
				
				
			}
		};
		KeyListener OnlyDigitsKeyListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getSource().equals(txtInitialHodometer) || e.getSource().equals(txtQuantity))
				{
					if(!Character.isDigit(e.getKeyChar()))
						e.setKeyChar('\0');
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		
		rdbtnClient.addActionListener(radioButtonActions);
		rdbtnOther.addActionListener(radioButtonActions);
		
		btnCancel.addActionListener(ButtonActions);
		btnAddDestination.addActionListener(ButtonActions);
		btnAddProduct.addActionListener(ButtonActions);
		btnRemoveProduct.addActionListener(ButtonActions);
		btnConfirm.addActionListener(ButtonActions);
		btnRemoveDestination.addActionListener(ButtonActions);
		
		txtInitialHodometer.addKeyListener(OnlyDigitsKeyListener);
		txtQuantity.addKeyListener(OnlyDigitsKeyListener);
		
		FrameController.addConfirmationOnClose(this, frameName);
	}
	private Boolean verifyDestinationData()
	{
		Boolean res = true;
		try{
			res =  (cboClient.getSelectedItem()!=null || !txtOther.getText().isEmpty()) && 
				   (rdbtnClient.isSelected() || rdbtnOther.isSelected()) &&
				    !txtDescription.getText().isEmpty() &&
				   !txtInitialHodometer.getText().isEmpty();
			
			return res;
		}catch(Exception ex)
		{
			return false;
		}
		
	}
	private void addProduct()
	{
		if(cboProduct.getSelectedItem()!=null && !txtQuantity.getText().isEmpty())
		{
			RouteProduct routeProduct = new RouteProduct();
			routeProduct.setProduct((Product) cboProduct.getSelectedItem());
			Double quant = Double.parseDouble(txtQuantity.getText());
			routeProduct.setQuantity(quant);
			vetProducts.addElement(routeProduct);
			productList.setListData(vetProducts);
			cboProduct.setSelectedItem(null);
			txtQuantity.setText("");
		}
		else
		{
			ShowMessage.errorMessage(null, "Mensagem","Escolha o produto e insira uma quantidade");
			cboProduct.grabFocus();
		}
	}
	private void removeProduct()
	{
		if(productList.getSelectedValue()!=null)
		{
			RouteProduct product = productList.getSelectedValue();
			vetProducts.remove(product);
			productList.setListData(vetProducts);
			
		}
		else
			ShowMessage.errorMessage(null, "Mensagem","Escolha um produto");
	}
	private void addDestination()
	{
		
		if(verifyDestinationData())
		{
			RouteDestination destination = new RouteDestination();

			if(cboClient.getSelectedItem()!=null)
				destination.setClient((Client)cboClient.getSelectedItem());
			else
			{
				destination.setOtherDestination(txtOther.getText());
				destination.setClient(null);
			}
			DefaultMutableTreeNode node = new DefaultMutableTreeNode();
			node.setUserObject(destination);
			
			DefaultMutableTreeNode childNode;
			for(int i=0;i < productList.getModel().getSize();i++)
			{
				
				childNode = new DefaultMutableTreeNode();
				RouteProduct routeProduct = (RouteProduct) productList.getModel().getElementAt(i);
				routeProduct.setRouteDestination(destination);
				childNode.setUserObject(routeProduct);
				node.add(childNode);
			}
			if(productList.getModel().getSize() == 0)
			{
				childNode = new DefaultMutableTreeNode("Nenhum Produto");
				node.add(childNode);
			}
			rootNode.add(node);
				
				treeDestinies.setModel(new DefaultTreeModel(rootNode));
				vetProducts.removeAllElements();
				productList.setListData(vetProducts);
				groupdestination.clearSelection();
				txtOther.setText("");
				cboClient.setSelectedItem(null);
				txtOther.setEnabled(false);
				cboClient.setEnabled(false);
				btnConfirm.setEnabled(true);
				
		}
		else
			ShowMessage.errorMessage(null, "Mensagem","Prencha os campos do novo destino");
		
		
		
		
	}
	private void removeDestination()
	{
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeDestinies.getLastSelectedPathComponent();
			if(selectedNode!=null)
			{
				if(selectedNode != rootNode)
				{
					if(ShowMessage.questionMessage(thisFrame, "REGISTRO", "Deseja mesmo remover o destino selecionado?")==
							JOptionPane.YES_OPTION)
					{
						if(selectedNode.isLeaf())
						   selectedNode =(DefaultMutableTreeNode) selectedNode.getParent();
						
					   DefaultTreeModel model = (DefaultTreeModel) treeDestinies.getModel();
					   model.removeNodeFromParent(selectedNode);
					   model.reload(rootNode);	
					   if(rootNode.getChildCount() == 0)
						   btnConfirm.setEnabled(false);
					}
				}
			}
			else
				ShowMessage.errorMessage(null, "Mensagem","Escolha o destino para remover");
			
			
		
	}
	private void insertRoute()
	{
		Route route = new Route();
		
		route.setDescription(txtDescription.getText());
		Date date = (java.util.Date) txtDate.getValue();
		route.setDate(new java.sql.Date(date.getTime()));
		Double initialHodometer = Double.parseDouble(txtInitialHodometer.getText());
		route.setInitialKm(initialHodometer);
		route.setVehicle((Vehicle)cboVehicle.getSelectedItem());
		route.setConductor((Employee)cboConductor.getSelectedItem());
		route.setUser(Session.getInstance().getUser());
		if(ShowMessage.questionMessage(thisFrame, "REGISTRO", "Deseja mesmo registrar o trajeto ?")==
				JOptionPane.YES_OPTION)
		{
			if(controller.insertRoute(route))
			{
				
				for(int i = 0;i < rootNode.getChildCount();i++)
				{
					DefaultMutableTreeNode defNode =(DefaultMutableTreeNode) rootNode.getChildAt(i);
					RouteDestination destination = (RouteDestination) defNode.getUserObject();
					controller.insertRouteDestination(destination);
					for(int j=0;j<rootNode.getChildAt(i).getChildCount();j++)
					{
						defNode = (DefaultMutableTreeNode) rootNode.getChildAt(i).getChildAt(j);
						if(defNode.getUserObject() instanceof RouteProduct)
						{
							RouteProduct product  = (RouteProduct) defNode.getUserObject();
							controller.insertRouteProduct(product);	
						}
					}
				}
				ShowMessage.successMessage(null,"RESULTADO","Trajeto inserido com sucesso.");
				ClearFrame.clear(this);
				cboVehicle.grabFocus();
				rootNode.removeAllChildren();
				DefaultTreeModel model = (DefaultTreeModel) treeDestinies.getModel();
				model.reload();
			}
			
			
				
		}
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
