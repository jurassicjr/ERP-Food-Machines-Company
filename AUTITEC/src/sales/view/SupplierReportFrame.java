package sales.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import sales.controller.SupplierReportController;

public class SupplierReportFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3525897840693271161L;
	private JPanel principalPanel;

	private SupplierReportController controller;

	private JFrame frame;
	private JTable table;

	public SupplierReportFrame() {
		frame = this;
		controller = new SupplierReportController();
		initialize();
		setListeners();
		controller.fillTable(table);
	}

	private void initialize() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 400);
		setMinimumSize(new Dimension(640, 400));
		setTitle("Relátorio de Fornecedores");
		getContentPane().setLayout(new BorderLayout(0, 0));
		initializePrincipal();
	}

	private void initializePrincipal() {
		principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();

		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_principalPanel.createSequentialGroup().addContainerGap()
		                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE).addContainerGap()));
		gl_principalPanel.setVerticalGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_principalPanel.createSequentialGroup().addContainerGap()
		                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
		                .addContainerGap(132, Short.MAX_VALUE)));

		table = new JTable();
		scrollPane.setViewportView(table);
		principalPanel.setLayout(gl_principalPanel);

		String[] header;
		header = new String[] { "Razão Social", "CNPJ", "Inscrição Estadual", "Certificado de Material", "ISO" };
		table.setModel(new DefaultTableModel(null, header){

			/**
			 * 
			 */
            private static final long serialVersionUID = 3766254056726480305L;
            
            boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
		});

		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		
	}

	private void setListeners() {
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		};
	}
}
