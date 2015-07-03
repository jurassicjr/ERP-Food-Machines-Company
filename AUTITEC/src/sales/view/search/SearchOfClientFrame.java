package sales.view.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import model.Client;
import model.Session;
import sales.controller.SearchOfClientController;
import sales.view.update.UpdateClientFrame;
import util.Icon;
import util.ShowMessage;

public class SearchOfClientFrame extends JDialog 
{
	private JTextField txtName;
	private JTextField txtCPF;
	private JTable tableClients;
	private SearchOfClientController controller;
	private String fieldToSearch;
	private JButton btnSearch;
	public SearchOfClientFrame() {
		controller = new SearchOfClientController();
		initialize();
		setListeners();
	
	}
	public void initialize()
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Consulta de Clientes");
		setBounds(0, 0, 542, 388);
		Icon.setIcon(this);
		initializePrincipal();
		controller.queryAll(tableClients);
		txtName.grabFocus();
		tableClients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE))
		);
		getContentPane().setLayout(groupLayout);
		
		
	}
	public void setListeners()
	{
		txtCPF.addKeyListener(txtSearchKeyListener);
		txtName.addKeyListener(txtSearchKeyListener);
		btnSearch.addActionListener(buttonListener);
		tableClients.addMouseListener(mouseListener);
	}
	public void initializePrincipal()
	{
		
		panel = new JPanel();
		panel.setLayout(null);
		
		JLabel lblCompanyName = new JLabel("Nome ou Razão social");
		lblCompanyName.setBounds(10, 11, 215, 14);
		panel.add(lblCompanyName);
		
		txtName = new JTextField();
		txtName.setBounds(10, 25, 215, 20);
		panel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblCnpj = new JLabel("CPF ou CNPJ");
		lblCnpj.setBounds(237, 11, 158, 14);
		panel.add(lblCnpj);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(237, 25, 158, 20);
		panel.add(txtCPF);
		txtCPF.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 484, 266);
		panel.add(scrollPane);
		
		tableClients = new JTable();
		scrollPane.setViewportView(tableClients);
		
	    btnSearch = new JButton("Buscar");
		btnSearch.setBounds(405, 24, 89, 23);
		panel.add(btnSearch);
		
	}
	ActionListener buttonListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnSearch))
			  if(validFields())
				  search();
			  else
				  ShowMessage.errorMessage(null,"Campos da busca","Preencha ao menos um campo para a busca" );
			
				
		}
	};
	
	KeyListener txtSearchKeyListener = new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {
		   if(e.getSource().equals(txtName))
		   {
		      txtCPF.setText("");
		      fieldToSearch = "name/company";
		   }
		      else
		   if(e.getSource().equals(txtCPF))
		   {
			   if(!Character.isDigit(e.getKeyChar()))
					e.setKeyChar('\0');
			   
			   fieldToSearch = "cpf/cnpj";
			   txtName.setText("");
		   }
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			  if(e.getSource().equals(txtName))
			   {
			      if(txtName.getText().isEmpty())
			    	  controller.queryAll(tableClients);
			   }
			  else
			  if(e.getSource().equals(txtCPF))
				   {
				  	  if(txtCPF.getText().isEmpty())
				  		  controller.queryAll(tableClients);
				   } 
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			
			 if(e.getSource().equals(txtName) || e.getSource().equals(txtCPF))
			   {
			     if(e.getKeyChar() == KeyEvent.VK_ENTER)
			    	 if(validFields())
			    		 search();
			   }
			 
			
		}
	};
	private JPanel panel;
	public boolean validFields()
	{
		return !txtCPF.getText().isEmpty() || !txtName.getText().isEmpty();
		
	}
	public void search()
	{
		
		String param = !txtName.getText().isEmpty() ? txtName.getText():txtCPF.getText();
		System.err.println(fieldToSearch);
		controller.search(tableClients,param, fieldToSearch);
			
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
					int line = tableClients.getSelectedRow();
					if(line > -1)
					{
					   if(!Session.getInstance().havePermission("UPD_CLI"))
						   ShowMessage.errorMessage(null,"Solicite permissão"," Você não tem permissão para atualizar/excluir clientes");
					   else
					   {
						      UpdateClientFrame fr =  new UpdateClientFrame();
							  Client client  = controller.getClientByName(line);
							  fr.setSelectedClient(client);
							  fr.addWindowListener(windowListener);
							  fr.setVisible(true);
						 
					   }
					   
					}
				}
			
		}
	};
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
			controller.queryAll(tableClients);
			
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
}
