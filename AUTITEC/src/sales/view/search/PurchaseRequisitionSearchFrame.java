package sales.view.search;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import model.PTC;
import model.PurchaseRequisition;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.PurchaseRequisitionSearchController;
import sales.view.PurchaseRequisitionFrame;
import userInterface.components.ComboBoxAutoCompletion;
import util.ClearFrame;
import util.Icon;

public class PurchaseRequisitionSearchFrame extends JFrame{

	/**
	 * 
	 */
    private static final long serialVersionUID = -4358140421701795519L;
	private DateField txtInicialDate;
	private DateField txtFinalDate;
	
	private JTextField txtRequisition;

	private JTable table;
	
	private PurchaseRequisitionSearchController controller;
	
	private JLabel lblPTC;
	private JLabel lblDate;
	private JLabel lblTo;
	private JLabel lblStatus;
	
	private JComboBox<String> cboStatus;
	private JComboBox<PTC> cboPTC;

	private JButton btnSearch;
	private JButton btnAll;
	private JPanel subPanel;
	private JButton btnExit;
	private JSeparator separator;
	private JButton btnClear;

    public PurchaseRequisitionSearchFrame() {
    	controller = new PurchaseRequisitionSearchController(this);
    	initialize();
    	setListeners();
    }

	private void initialize() {
	    setBounds(100, 100, 792, 401);
	    setPreferredSize(new Dimension(792, 401));
	    setMinimumSize(new Dimension(576,356));
	    setTitle("Acompanhamento de Requisição de Compra");
	    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    Icon.setIcon(this);
	    getContentPane().setLayout(new BorderLayout(0, 0));
	    initializePrincipal();
    }

	private void initializePrincipal() {
	    JPanel principalPanel = new JPanel();
	    getContentPane().add(principalPanel, BorderLayout.CENTER);
	    
	    JLabel lblRequisition = new JLabel("nº Requisição");
	    
	    txtRequisition = new JTextField();
	    txtRequisition.setColumns(10);
	    
	    lblPTC = new JLabel(" PTC");
	    
	    lblDate = new JLabel("Data de Ger.");
	    
	    txtInicialDate = CalendarFactory.createDateField();
	    
	    lblTo = new JLabel("até");
	    
	    txtFinalDate = CalendarFactory.createDateField();
	    
	    lblStatus = new JLabel("Status");
	    
	    cboStatus = new JComboBox<String>();
	    cboStatus.addItem("Pendente");
	    cboStatus.addItem("Concluido");
	    cboStatus.addItem("semi-concluido/aberto");
	    cboStatus.addItem("semi-concluido/fechado");
	    cboStatus.addItem("Negado");
	    
	    btnSearch = new JButton("Pesquisar");
	    btnSearch.setIcon(new ImageIcon(PurchaseRequisitionSearchFrame.class.getResource("/resources/view.png")));
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    
	    btnAll = new JButton("Todos");
	    btnAll.setIcon(new ImageIcon(PurchaseRequisitionSearchFrame.class.getResource("/resources/update.png")));
	    
	    cboPTC = new JComboBox<PTC>();
	    controller.fillPTC(cboPTC);
	    new ComboBoxAutoCompletion(cboPTC);
	    
	    separator = new JSeparator();
	    GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	    gl_principalPanel.setHorizontalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
	    				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblDate)
	    							.addGap(7))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblRequisition)
	    							.addPreferredGap(ComponentPlacement.RELATED)))
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(txtInicialDate, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(lblTo)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtFinalDate, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
	    						.addComponent(txtRequisition, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
	    					.addGap(18)
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblPTC)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(cboPTC, 0, 327, Short.MAX_VALUE))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(lblStatus)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(cboStatus, 0, 318, Short.MAX_VALUE))))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(btnSearch)
	    					.addPreferredGap(ComponentPlacement.RELATED, 576, Short.MAX_VALUE)
	    					.addComponent(btnAll)))
	    			.addContainerGap())
	    );
	    gl_principalPanel.setVerticalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(txtRequisition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblPTC)
	    				.addComponent(lblRequisition)
	    				.addComponent(cboPTC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblDate)
	    				.addComponent(txtInicialDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(txtFinalDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(cboStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblTo)
	    				.addComponent(lblStatus))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(btnSearch)
	    				.addComponent(btnAll))
	    			.addGap(18)
	    			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(separator, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
	    			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    );
	    
	    table = new JTable();
	    String[] header = new String[] {"Requisição", "P.T.C", "Data", "Status"};
	    table.setModel(new DefaultTableModel(null, header) {
	    	  
	    	
	    	/**
			 * 
			 */
            private static final long serialVersionUID = -6775046275667222448L;
			
            boolean[] columnEditables = new boolean[] {
						false, false, false, false
				};
	    	
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		return columnEditables[column];
	    	}
	    });
	    scrollPane.setViewportView(table);
	    principalPanel.setLayout(gl_principalPanel);
	    controller.fillTable(table);
	    
	    initializeSub();
	    
    }
	
	private void initializeSub() {
	    subPanel = new JPanel();
	    getContentPane().add(subPanel, BorderLayout.SOUTH);
	    subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

	    btnExit = new JButton("Sair");
	    btnExit.setIcon(new ImageIcon(PurchaseRequisitionSearchFrame.class.getResource("/resources/ok.png")));
	    btnClear = new JButton("Limpar");
	    btnClear.setIcon(new ImageIcon(PurchaseRequisitionSearchFrame.class.getResource("/resources/ClearFrame.png")));
	    
	    subPanel.add(btnClear);
	    subPanel.add(btnExit);
    }

	private void setListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.close();
			}
		});
		
		ActionListener buttonListerner = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnSearch))search();
				else if(e.getSource().equals(btnClear))clearFrame();
				else if(e.getSource().equals(btnAll))controller.search(table, "", null, null, null, -1);
				else if(e.getSource().equals(btnExit))controller.close();
			}
		};
		btnSearch.addActionListener(buttonListerner);
		btnClear.addActionListener(buttonListerner);
		btnAll.addActionListener(buttonListerner);
		btnExit.addActionListener(buttonListerner);
		
		MouseListener tableMouseListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 && e.getSource().equals(table))showRequisition(table);
			}
		};
		table.addMouseListener(tableMouseListener);
	}
	
	private void clearFrame() {
		ClearFrame.clear(this);
		ClearFrame.clearTable(table);
	}
	private void search() {
		Date startDate = (Date) txtInicialDate.getValue();
		Date endDate = (Date) txtFinalDate.getValue();
		String requisitionNumber = txtRequisition.getText();
		Integer status = cboStatus.getSelectedIndex();
		PTC ptc = (PTC) cboPTC.getSelectedItem();
		
		controller.search(table, requisitionNumber, startDate, endDate, ptc, status);
	}
	
	private void showRequisition(JTable table) {
		if(table.getSelectedRow() == -1)return;
		PurchaseRequisition p = (PurchaseRequisition) table.getValueAt(table.getSelectedRow(), 0);
		new PurchaseRequisitionFrame(p).setVisible(true);
	}
}
