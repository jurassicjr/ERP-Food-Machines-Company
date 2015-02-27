package sales.view.search;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import sales.controller.SearchOfProductController;
import util.ClearFrame;

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
	}

	private void initializePrincipal() {
		principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);
		
		lblName = new JLabel("Nome");
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		btnSearch = new JButton("Buscar");
		btnSearch.setSelectedIcon(new ImageIcon(SearchOfMaterialFrame.class.getResource("/resources/ok.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
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
		String[] header = new String[] {"Produto", "descrição"};
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
		ClearFrame.clear(frame);
		if(!name.isEmpty()) {
		controller.simpleSearch(table, name);
		}
	}
}
