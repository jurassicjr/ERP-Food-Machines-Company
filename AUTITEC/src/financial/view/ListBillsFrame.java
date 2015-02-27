//package financial.view;
//
//import java.awt.BorderLayout;
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//
//import javax.swing.AbstractCellEditor;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.ListSelectionModel;
//import javax.swing.UIManager;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableCellEditor;
//import javax.swing.table.TableCellRenderer;
//import javax.swing.table.TableColumnModel;
//
//import model.Bill;
//import util.Icon;
//import financial.controller.ListBillsFrameController;
//
//public class ListBillsFrame extends JFrame {
//
//	private static final long serialVersionUID = -2945947774382521011L;
//	
//	private JTable billsTable;
//	
//	private boolean hasBills;	
//	private boolean allowsPay;
//	
//	private ListBillsFrameController controller;
//	
//	public ListBillsFrame(boolean allowsPay) {
//		
//		controller = new ListBillsFrameController(this);
//		this.allowsPay = allowsPay;
//		
//		initialize();
//		setListeners();
//		
//		hasBills = controller.setBills(billsTable);	
//				
//	}
//	
//	private void initialize() {
//		
//		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		setBounds(100, 100, 850, 350);
//		setMinimumSize(new Dimension(850, 350));
//		setTitle("Pagamento de Conta");
//		Icon.setIcon(this);
//		
//		JPanel contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
//		
//		JScrollPane scrollPane = new JScrollPane();
//		contentPane.add(scrollPane, BorderLayout.CENTER);
//		
//		String tableHeader[];
//		
//		if(allowsPay) tableHeader = new String[] {"Conta a Pagar", "Credor", "Valor", "Parcelas", "Pagar"};
//		else tableHeader = new String[] {"Conta a Pagar", "Credor", "Valor", "Parcelas"};
//		
//		billsTable = new JTable();
//		billsTable.setModel(new DefaultTableModel(new Object[][] {}, tableHeader)
//		{
//			
//			private static final long serialVersionUID = -2428534803601038088L;
//			
//			boolean[] columnEditables = new boolean[] {
//					false, false, false, false, true
//			};
//			
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				return columnEditables[column];
//			}
//			
//		});
//		
//		billsTable.getColumnModel().getColumn(0).setPreferredWidth(150);
//		billsTable.getColumnModel().getColumn(1).setPreferredWidth(150);
//		billsTable.getColumnModel().getColumn(2).setPreferredWidth(30);
//		billsTable.getColumnModel().getColumn(3).setPreferredWidth(30);
//		if(allowsPay) billsTable.getColumnModel().getColumn(4).setPreferredWidth(30);
//		
//		billsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		billsTable.getTableHeader().setReorderingAllowed(false);
//		billsTable.getTableHeader().setResizingAllowed(false);
//		billsTable.setRowHeight(25);
//		
//		scrollPane.setViewportView(billsTable);
//				
//		if(allowsPay) new ButtonColumnTechnicalStandard(billsTable, 4, new ImageIcon(getClass().getResource("/resources/payment.png")));
//				
//	}
//	
//	private void setListeners() {
//		
//		addWindowListener(new WindowAdapter() {
//			
//			@Override
//			public void windowActivated(WindowEvent e) {
//				if(!hasBills) dispose();
//			}
//			
//		});		
//				
//	}	
//	
//	class ButtonColumnTechnicalStandard extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
//
//		private static final long serialVersionUID = -1903358975859108679L;
//		
//		private JTable table;
//		
//		private String text;
//		private ImageIcon icon;
//	
//		private JButton renderButton;
//		private JButton editButton;
//		
//		public ButtonColumnTechnicalStandard(JTable table, int column) {
//			
//			super();
//			this.table = table;
//			
//			renderButton = new JButton();
//
//			editButton = new JButton();
//			editButton.setFocusPainted(false);
//			editButton.addActionListener(this);
//
//			TableColumnModel columnModel = table.getColumnModel();
//			columnModel.getColumn(column).setCellRenderer(this);
//			columnModel.getColumn(column).setCellEditor(this);
//			
//		}
//		
//		public ButtonColumnTechnicalStandard (JTable table, int column, ImageIcon icon) {
//			
//			super();
//			this.table = table;
//			this.icon = icon;
//			
//			renderButton = new JButton();
//
//			editButton = new JButton(icon);
//			editButton.setFocusPainted(false);
//			editButton.addActionListener(this);
//
//			TableColumnModel columnModel = table.getColumnModel();
//			columnModel.getColumn(column).setCellRenderer(this);
//			columnModel.getColumn(column).setCellEditor(this);
//			
//		}
//
//		@Override
//		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//			
//			if (hasFocus) {
//				renderButton.setForeground(table.getForeground());
//				renderButton.setBackground(UIManager.getColor("Button.background"));
//			}
//			else if (isSelected) {
//				renderButton.setForeground(table.getSelectionForeground());
//				renderButton.setBackground(table.getSelectionBackground());
//			}
//			else {
//				renderButton.setForeground(table.getForeground());
//				renderButton.setBackground(UIManager.getColor("Button.background"));
//			}
//
//			renderButton.setText((value == null) ? "" : value.toString());
//			if(icon != null) renderButton.setIcon(icon);
//			return renderButton;
//		}
//
//		@Override
//		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//			text = (value == null) ? "" : value.toString();
//			editButton.setText(text);
//			if(icon != null) editButton.setIcon(icon);
//			return editButton;
//		}
//
//		@Override
//		public Object getCellEditorValue() {
//			return text;
//		}
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			
//			fireEditingStopped();
//			
//			int row = table.getSelectedRow();
//			Bill bill = (Bill) table.getValueAt(row, 0);
//			
//			String s = (String) table.getValueAt(row, 2);
//			double value = Double.parseDouble(s.substring(3).replaceAll(",", "."));
//								
//			controller.payBill(bill, value);
//			
//		}
//				
//	}
//	
//}
