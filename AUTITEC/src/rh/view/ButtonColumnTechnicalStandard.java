package rh.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import rh.controller.TechnicalStandardFrameController;

public class ButtonColumnTechnicalStandard extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {

	private static final long serialVersionUID = -1903358975859108679L;
	
	private JTable table;
	
	private String text;
	private ImageIcon icon;
	
	private JButton renderButton;
	private JButton editButton;
	
	private TechnicalStandardFrameController controller;

	public ButtonColumnTechnicalStandard(JTable table, int column, TechnicalStandardFrameController controller) {
		
		super();
		this.table = table;
		this.controller = controller;
		
		renderButton = new JButton();

		editButton = new JButton();
		editButton.setFocusPainted(false);
		editButton.addActionListener(this);

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer(this);
		columnModel.getColumn(column).setCellEditor(this);
		
	}
	
	public ButtonColumnTechnicalStandard(JTable table, int column, ImageIcon icon, TechnicalStandardFrameController controller) {
		
		super();
		this.table = table;
		this.icon = icon;
		this.controller = controller;
		
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
		int column = table.getSelectedColumn();
		controller.tableButtonClick(row, column, table);
		
	}
}