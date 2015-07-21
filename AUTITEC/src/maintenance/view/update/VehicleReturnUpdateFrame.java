package maintenance.view.update;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.text.MaskFormatter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import maintenance.controller.VehicleUpdateReturnController;
import maintenance.view.register.VehicleRegisterFrame;
import model.Route;
import model.RouteDestination;
import model.RouteProduct;
import net.sf.nachocalendar.components.DateField;
import userInterface.components.FrameController;
import userInterface.components.RouteDestinationTreeNodeRenderer;
import util.Icon;
import util.ShowMessage;

public class VehicleReturnUpdateFrame extends JFrame {
	
	private VehicleUpdateReturnController controller;
	private String frameName = "Registro de retorno do trajeto";
	private JPanel panelData;
	private JTree treeRoutes;	
	private JLabel lbReturnHodometer;
	private static final long serialVersionUID = 1L;
	private JTextField txtReturnHodometer;
	private DateField txtDate;
	private JFormattedTextField txtReturnHour;
	private JTextArea txtObs;
	private DefaultMutableTreeNode rootNode;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnRemove;
	private VehicleReturnUpdateFrame thisFrame = this;

	public VehicleReturnUpdateFrame() 
	{
		controller = new VehicleUpdateReturnController();
		initialize();
		setListeners();
	}
	public void initialize()
	{
		
		Icon.setIcon(this);
		setTitle(frameName);
		setBounds(100, 100, 582, 483);
		setMinimumSize(new Dimension(582, 483));
		setPreferredSize(new Dimension(582, 483));
		initializePrincipal();
		rootNode = new DefaultMutableTreeNode("Trajetos em andamento");
		treeRoutes.setCellRenderer(new RouteDestinationTreeNodeRenderer());
		treeRoutes.setModel(new DefaultTreeModel(rootNode));
		treeRoutes.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		getActiveRoutes();
	}
	public void initializePrincipal()
	{
	getContentPane().setLayout(null);
		
		panelData = new JPanel();
		panelData.setBounds(10, 11, 546, 422);
		getContentPane().add(panelData);
		panelData.setLayout(null);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(10,11,526,280);
		treeRoutes = new JTree();
		scroll.setViewportView(treeRoutes);
		panelData.add(scroll);
		
		txtReturnHodometer = new JTextField();
		txtReturnHodometer.setBounds(10, 316, 132, 20);
		panelData.add(txtReturnHodometer);
		txtReturnHodometer.setColumns(10);
		
		lbReturnHodometer = new JLabel("Hodômetro");
		lbReturnHodometer.setBounds(10, 302, 132, 14);
		panelData.add(lbReturnHodometer);
		
		JLabel lbReturnDate = new JLabel("Data");
		lbReturnDate.setBounds(312, 302, 48, 14);
		panelData.add(lbReturnDate);
		
		JLabel lbReturnHour = new JLabel("Hora");
		lbReturnHour.setBounds(442, 302, 46, 14);
		panelData.add(lbReturnHour);
		
		txtDate = new DateField();
		txtDate.setBounds(312, 316, 112, 20);
		panelData.add(txtDate);

		
		try {
			txtReturnHour = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtReturnHour.setBounds(442, 316, 94, 20);
		
		panelData.add(txtReturnHour);

		txtObs= new JTextArea();
		txtObs.setBounds(10, 358, 526, 28);
		panelData.add(txtObs);
		
		JLabel lbReturnObs = new JLabel("Observações");
		lbReturnObs.setBounds(10, 343, 132, 14);
		panelData.add(lbReturnObs);
		

		btnConfirm = new JButton("Confirmar");
		btnConfirm.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/ok.png")));
		btnConfirm.setBounds(415, 397, 121, 23);
		panelData.add(btnConfirm);
		
		btnRemove = new JButton("Excluir");
		btnRemove.setBounds(284, 397, 121, 23);
		btnRemove.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/1419366170_17-16.png")));
		panelData.add(btnRemove);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/cancel.png")));
		btnCancel.setBounds(153, 397, 121, 23);
		panelData.add(btnCancel);
	}
	/**
	 * 
	 */
	private void setListeners()
	{
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnCancel))
					FrameController.close(thisFrame,frameName);
				else
				if(e.getSource().equals(btnRemove))
				{
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeRoutes.getLastSelectedPathComponent();
					if(selectedNode!=null)
					{
						if(selectedNode != rootNode)
						{
							if(ShowMessage.questionMessage(thisFrame, "REGISTRO", "Deseja mesmo remover o trajeto selecionado?")==
									JOptionPane.YES_OPTION)
							{
								controller.deleteRoute(selectedNode);
								DefaultTreeModel model = (DefaultTreeModel) treeRoutes.getModel();
								model.removeNodeFromParent(selectedNode);
								model.reload(rootNode);	
							}
						}
					}
			   }
				else
				if(e.getSource().equals(btnConfirm))
				{
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeRoutes.getLastSelectedPathComponent();
					if(selectedNode!=null)
					{
						if(selectedNode != rootNode && selectedNode.getUserObject() instanceof Route)
						{
							if(verifyUpdateData())
							{
								if(ShowMessage.questionMessage(thisFrame, "REGISTRO", "Deseja mesmo atualizar"
										+ " o trajeto selecionado?")==
										JOptionPane.YES_OPTION)
								{
									Route route = (Route)selectedNode.getUserObject();
									Calendar cal = Calendar.getInstance();
									Date date =(java.util.Date) txtDate.getValue();
									cal.setTime(date);
									cal.set(Calendar.HOUR,0);
									cal.set(Calendar.MINUTE,0);
									cal.set(Calendar.SECOND, 0);
									String h = txtReturnHour.getText().split(":")[0];
									String m = txtReturnHour.getText().split(":")[1];
									cal.add(Calendar.HOUR,Integer.parseInt(h));
									cal.add(Calendar.MINUTE,Integer.parseInt(m));
									route.setReturnDate(new java.sql.Date(cal.getTime().getTime()));
									route.setReturnKm(Double.parseDouble(txtReturnHodometer.getText()));
									route.setReturnObs(txtObs.getText());
									controller.updateRoute(route);
									getActiveRoutes();
								}
							}
							else
								ShowMessage.errorMessage(null,"Mensagem","Preecha todos os dados para atualização");
						}
					}
				}
			}
		};
		
		
		KeyListener keyListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getSource().equals(txtReturnHour) || e.getSource().equals(txtReturnHodometer))
				{
					if(!Character.isDigit(e.getKeyChar()))
						e.setKeyChar('\0');
						
					
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getSource().equals(txtReturnHour))
				{
					try{
						String hour = txtReturnHour.getText().replace(":","");
						if(Integer.parseInt(hour) > 2359)
						{
							txtReturnHour.setValue("00:00");
						}
							
						
					}catch(Exception ex)
					{
						
					}
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		txtReturnHour.addKeyListener(keyListener);
		txtReturnHodometer.addKeyListener(keyListener);
		btnCancel.addActionListener(buttonListener);
		btnRemove.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
		FrameController.addConfirmationOnClose(this, frameName);
		
		
		
	}
	private boolean verifyUpdateData()
	{
		try {
			return txtDate.getValue()!=null && 
				   !txtReturnHodometer.getText().isEmpty() &&
				   Integer.parseInt(txtReturnHour.getText().replace(":", "")) <=2359;
			
		} catch (Exception e) {
			return false;
		}
	}
	private void getActiveRoutes()
	{
		rootNode.removeAllChildren();
		ArrayList<Route> routes = controller.getActiveRoutes();
		for(Route route:routes)
		{
			DefaultMutableTreeNode node = new DefaultMutableTreeNode();
			node.setUserObject(route);
			rootNode.add(node);
			ArrayList<RouteDestination> destinations = controller.getDestinationsByRoute(route);
			for(RouteDestination destination:destinations)
			{
				DefaultMutableTreeNode childNode = new DefaultMutableTreeNode();
				childNode.setUserObject(destination);
				node.add(childNode);
				ArrayList<RouteProduct> products  =new ArrayList<RouteProduct>();
				products =  controller.getProductssByRouteDestination(destination);
				
				for(RouteProduct prod:products)
				{
					DefaultMutableTreeNode productNode = new DefaultMutableTreeNode();
					productNode.setUserObject(prod);
					childNode.add(productNode);
					
				}
				if(products.size() == 0)
				{
					DefaultMutableTreeNode productNode = new DefaultMutableTreeNode("Nenhum Produto");
					childNode.add(productNode);
				}
			}
		}
		treeRoutes.setModel(new DefaultTreeModel(rootNode));
	}
}
