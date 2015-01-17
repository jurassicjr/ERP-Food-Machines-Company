package rh.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import model.Employee;
import rh.controller.ListEmployeesFrameController;
import util.Icon;

public class ListEmployesFrame extends JFrame {

	private static final long serialVersionUID = -6619296918547275962L;
	
	private JTable table;
	
	private ListEmployeesFrameController controller;

	public ListEmployesFrame() {
		
		controller = new ListEmployeesFrameController(this);
		
		initialize();		
		
		controller.setEmployees(table);
		
		new ButtonColumnTechnicalStandard(table, 2, new ImageIcon(getClass().getResource("/resources/update.png")));
	}
	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 350);
		setMinimumSize(new Dimension(550, 350));
		Icon.setIcon(this);
		setTitle("Atualizar Funcionário");
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(null, new String[] {"Funcionário", "CPF", "Atualizar"}) 
		{

			private static final long serialVersionUID = -473309056407632334L;
			
			boolean[] columnEditables = new boolean[] {
				false, false, true
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
		});
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setRowHeight(25);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(280);
		table.getColumnModel().getColumn(1).setPreferredWidth(75);
		table.getColumnModel().getColumn(2).setPreferredWidth(75);
		
		scrollPane.setViewportView(table);
	}
	
	class ButtonColumnTechnicalStandard extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {

		private static final long serialVersionUID = -1903358975859108679L;
		
		private JTable table;
		
		private String text;
		private ImageIcon icon;
	
		private JButton renderButton;
		private JButton editButton;
		
		public ButtonColumnTechnicalStandard(JTable table, int column) {
			
			super();
			this.table = table;
			
			renderButton = new JButton();

			editButton = new JButton();
			editButton.setFocusPainted(false);
			editButton.addActionListener(this);

			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(column).setCellRenderer(this);
			columnModel.getColumn(column).setCellEditor(this);
			
		}
		
		public ButtonColumnTechnicalStandard (JTable table, int column, ImageIcon icon) {
			
			super();
			this.table = table;
			this.icon = icon;
			
			renderButton = new JButton();

			editButton = new JButton(icon);
			editButton.setFocusPainted(false);
			editButton.addActionListener(this);

			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(column).setCellRenderer(this);
			columnModel.getColumn(column).setCellEditor(this);
			
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			
			if (hasFocus) {
				renderButton.setForeground(table.getForeground());
				renderButton.setBackground(UIManager.getColor("Button.background"));
			}
			else if (isSelected) {
				renderButton.setForeground(table.getSelectionForeground());
				renderButton.setBackground(table.getSelectionBackground());
			}
			else {
				renderButton.setForeground(table.getForeground());
				renderButton.setBackground(UIManager.getColor("Button.background"));
			}

			renderButton.setText((value == null) ? "" : value.toString());
			if(icon != null) renderButton.setIcon(icon);
			return renderButton;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			text = (value == null) ? "" : value.toString();
			editButton.setText(text);
			if(icon != null) editButton.setIcon(icon);
			return editButton;
		}

		@Override
		public Object getCellEditorValue() {
			return text;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			fireEditingStopped();
			
			int row = table.getSelectedRow();
			Employee employee = (Employee) table.getValueAt(row, 0);
			
			controller.updateEmployee(employee);
			
		}
				
	}

}
