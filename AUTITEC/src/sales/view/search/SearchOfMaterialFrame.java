package sales.view.search;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import sales.controller.SearchOfMaterialController;
import util.ClearFrame;
import util.ShowMessage;

public class SearchOfMaterialFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -701640555656047134L;
	private JPanel principalPanel;
	private JTextField txtName;
	private JTable table;
	private JLabel lblMaximusAmmount;
	private JSpinner spinner;
	private JLabel lblQuantidadeMinima;
	private JSpinner spinnerMinimum;
	private JButton btnSearch;
	private JLabel lblName;
	private SearchOfMaterialController controller;
	private JScrollPane scrollPaneTable;
	private JFrame frame = this;
	
	public SearchOfMaterialFrame() {
		controller = new SearchOfMaterialController();
		initialize();
		setListeners();
	}

	private void initialize() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Consulta de Material");
		util.Icon.setIcon(this);
		setBounds(0, 0, 478, 407);
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
		
		lblMaximusAmmount = new JLabel("Quantidade Maxima");
		
		spinner = new JSpinner();
		
		lblQuantidadeMinima = new JLabel("Quantidade Minima");
		
		spinnerMinimum = new JSpinner();
		
		spinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int number = (int) spinner.getValue();
				if(number <= 0)
					spinner.setValue(0);
				
			}
		});
		spinnerMinimum.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int number = (int) spinnerMinimum.getValue();
				if(number <= 0)
					spinnerMinimum.setValue(0);
				
			}
		});
		
		
		btnSearch = new JButton("Buscar");
		btnSearch.setSelectedIcon(new ImageIcon(SearchOfMaterialFrame.class.getResource("/resources/ok.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblMaximusAmmount)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblQuantidadeMinima)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerMinimum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaximusAmmount)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuantidadeMinima)
						.addComponent(spinnerMinimum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch))
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		String[] header = new String[] {"Material", "Quantidade"};
		table.setModel(new DefaultTableModel(null, header) {


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
		
		KeyListener txtKeyListerner = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}
		
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getSource().equals(txtName))
				{
					if(txtName.getText().isEmpty())
						controller.queryAll(table);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getSource().equals(txtName))
				{
					if(e.getKeyChar() == KeyEvent.VK_ENTER)
						verify();
					
				}
			}
		};
		txtName.addKeyListener(txtKeyListerner);
	}
	
	private void verify() {
		int max = (int) spinner.getValue();
		int min = (int) spinnerMinimum.getValue();
		String name = txtName.getText();
		controller.search(table, max, min, name);		
	}
}
