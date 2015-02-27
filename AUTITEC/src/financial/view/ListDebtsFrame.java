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
//import model.DebtToReceive;
//import financial.controller.ListDebtsFrameController;
//
//public class ListDebtsFrame extends JFrame {
//	
//	private static final long serialVersionUID = -1182431075673638709L;
//
//	private JTable table;
//	
//	private boolean hasDebts;
//	private boolean allowsReceipt;
//	
//	private ListDebtsFrameController controller;
//	
//	public ListDebtsFrame(boolean allowsReceipt) {
//		
//		controller = new ListDebtsFrameController(this);
//		this.allowsReceipt = allowsReceipt;
//		
//		initialize();
//		setListeners();
//		
//		hasDebts = controller.setDebts(table);
//	}
//	
//	private void initialize() {
//		
//		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		setBounds(100, 100, 800, 300);
//		setMinimumSize(new Dimension(800, 300));
//		setTitle("Recebimento de Contas");
//		
//		JPanel contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
//		
//		JScrollPane scrollPane = new JScrollPane();
//		contentPane.add(scrollPane, BorderLayout.CENTER);
//		
//		table = new JTable();
//		
//		String header[];
//		if(allowsReceipt) header = new String[] {"Conta a Receber", "Devedor", "Valor", "Receber"};
//		else header = new String[] {"Conta a Receber", "Devedor", "Valor"};
//		
//		table.setModel(new DefaultTableModel(null, header)
//		{
//			
//			private static final long serialVersionUID = -5279891303993431911L;
//			
//			boolean[] columnEditables = new boolean[] {
//				false, false, false, true
//			};
//			
//			public boolean isCellEditable(int row, int column) {
//				return columnEditables[column];
//			}
//			
//		});
//		
//		scrollPane.setViewportView(table);
//		
//		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		table.getTableHeader().setReorderingAllowed(false);
//		table.getTableHeader().setResizingAllowed(false);
//		table.setRowHeight(25);
//		
//		table.getColumnModel().getColumn(0).setPreferredWidth(200);
//		table.getColumnModel().getColumn(1).setPreferredWidth(200);
//		table.getColumnModel().getColumn(2).setPreferredWidth(50);
//		if(allowsReceipt) table.getColumnModel().getColumn(3).setPreferredWidth(50);
//		
//		if(allowsReceipt) new ButtonColumnTechnicalStandard(table, 3, new ImageIcon(getClass().getResource("/resources/payment.png")));
//	}
//	
//	private void setListeners() {
//		
//		addWindowListener(new WindowAdapter() {
//			
//			@Override
//			public void windowActivated(WindowEvent e) {
//				if(!hasDebts) dispose();
//			}
//			
//		});		
//		
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
//			DebtToReceive debtToReceive = (DebtToReceive) table.getValueAt(row, 0);
//			
//			String s = (String) table.getValueAt(row, 2);
//			s = s.substring(3).replaceAll("\\.", "").replaceAll(",", ".");
//			double value = Double.parseDouble(s);
//			
//			controller.receiveDebt(debtToReceive, value);
//			
//		}
//				
//	}
//
//}
