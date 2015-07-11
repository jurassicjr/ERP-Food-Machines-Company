package maintenance.view.search;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import maintenance.controller.VehicleSearchController;
import userInterface.components.FrameController;
import userInterface.components.UpperTextField;

public class VehicleSearchFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtVehicleDescription;
	private JButton btnSearch;
	private VehicleSearchController controller;
	private JTable vehicleTable;
	private JPanel panelData;
	public VehicleSearchFrame()
	{
		controller = new VehicleSearchController();
		initialize();
		initializePrincipal();
		setListeners();
		controller.queryAll(vehicleTable);
		txtVehicleDescription.grabFocus();
		
	}
	
	private void initialize() {
		setTitle("Consulta de veículos");
		util.Icon.setIcon(this);
		setMinimumSize(new Dimension(424,303));
		setBounds(0, 0, 424, 303);
		setPreferredSize(new Dimension(424,303));
		initializePrincipal();	
	}

	private void initializePrincipal() 
	{
		getContentPane().setLayout(null);
		
		panelData = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		txtVehicleDescription = new UpperTextField();
		
		
		JLabel lblDescrio = new JLabel("Descrição");
		
		btnSearch = new JButton("Busca");
		GroupLayout gl_panelData = new GroupLayout(panelData);
		gl_panelData.setHorizontalGroup(
			gl_panelData.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelData.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panelData.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(lblDescrio, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.LEADING, gl_panelData.createSequentialGroup()
							.addComponent(txtVehicleDescription, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)))
					.addGap(28))
		);
		gl_panelData.setVerticalGroup(
			gl_panelData.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelData.createSequentialGroup()
					.addGap(12)
					.addComponent(lblDescrio)
					.addGap(3)
					.addGroup(gl_panelData.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtVehicleDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch))
					.addGap(20)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
					.addContainerGap())
		);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(panelData, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelData, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
		);
		getContentPane().setLayout(groupLayout);
		vehicleTable = new JTable();
		scrollPane.setViewportView(vehicleTable);
		panelData.setLayout(gl_panelData);
	}
	
	private void setListeners()
	{
		
		ActionListener buttonActions = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnSearch))
					search();
				
			}
		};
		KeyListener keyListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getSource().equals(txtVehicleDescription))
					if(txtVehicleDescription.getText().isEmpty())
						controller.queryAll(vehicleTable);
					
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getSource().equals(txtVehicleDescription))
					if(e.getKeyChar() == KeyEvent.VK_ENTER)
						search();
			}
		};
		
		btnSearch.addActionListener(buttonActions);
		txtVehicleDescription.addKeyListener(keyListener);
		FrameController.addConfirmationOnClose(this);
		
	}
	private void search()
	{
		if(txtVehicleDescription.getText().isEmpty())
			controller.queryAll(vehicleTable);
		else
			controller.searchByDescription(vehicleTable,txtVehicleDescription.getText());
	}
}
