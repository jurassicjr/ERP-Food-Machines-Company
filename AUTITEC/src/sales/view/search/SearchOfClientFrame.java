package sales.view.search;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import sales.controller.SearchOfClientController;
import util.Icon;

import javax.swing.JTable;

public class SearchOfClientFrame extends JDialog 
{
	private JTextField textFieldName;
	private JTextField textFieldCPF;
	private JTable tableClients;
	private SearchOfClientController controller;
	public SearchOfClientFrame() {
		controller = new SearchOfClientController();
		initialize();
		
	
	}
	public void initialize()
	{
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Consulta de Clientes");
		setBounds(0, 0, 542, 388);
		Icon.setIcon(this);
		setLocationRelativeTo(null);
		initializePrincipal();
		controller.queryAll(tableClients);
		textFieldName.grabFocus();
		
	}
	public void initializePrincipal()
	{
	getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 504, 333);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome ou Razão social");
		lblNewLabel.setBounds(10, 11, 215, 14);
		panel.add(lblNewLabel);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(10, 25, 215, 20);
		panel.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CPF ou CNPJ");
		lblNewLabel_1.setBounds(237, 11, 158, 14);
		panel.add(lblNewLabel_1);
		
		textFieldCPF = new JTextField();
		textFieldCPF.setBounds(237, 25, 158, 20);
		panel.add(textFieldCPF);
		textFieldCPF.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 484, 266);
		panel.add(scrollPane);
		
		tableClients = new JTable();
		scrollPane.setViewportView(tableClients);
		
		JButton btnSearch = new JButton("Buscar");
		btnSearch.setBounds(405, 24, 89, 23);
		panel.add(btnSearch);
		
	}
}
