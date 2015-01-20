package production.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import production.controller.StagesProductionFrameController;
import util.Icon;

public class StagesProductionFrame extends JFrame {

	private static final long serialVersionUID = 6379096981330366934L;
	
	private StagesProductionFrameController controller;
	
	private JTextField txProduction;
	
	private JTable closeStagesTable;
	private JTable openStagesTable;
	
	public StagesProductionFrame() {
		
		controller = new StagesProductionFrameController(this);
		
		initialize();
		
		controller.setProduction(txProduction, openStagesTable, closeStagesTable);
	}
	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 414);
		setTitle("Fases de Produção");
		Icon.setIcon(this);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblProduction = new JLabel("Produção");
		txProduction = new JTextField();
		txProduction.setEditable(false);
		
		JScrollPane openStagesPanel = new JScrollPane();
		JScrollPane closeStagesPanel = new JScrollPane();
		
		GroupLayout layout = new GroupLayout(panel);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(closeStagesPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
						.addComponent(openStagesPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblProduction)
							.addGap(18)
							.addComponent(txProduction, GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduction)
						.addComponent(txProduction, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(openStagesPanel, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(closeStagesPanel, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		openStagesTable = new JTable();
		openStagesTable.setModel(new DefaultTableModel(null, new String[] {"Estágio", "Finalizar Estágio"})
		{
			
			private static final long serialVersionUID = 851922497412617510L;
			
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
		});
		
		openStagesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		openStagesTable.setCellSelectionEnabled(true);
		openStagesTable.getTableHeader().setReorderingAllowed(false);
		openStagesTable.setRowHeight(25);
		
		new ButtonColumn(openStagesTable, 1, new ImageIcon(getClass().getResource("/resources/ok.png")));
		
		closeStagesTable = new JTable();
		closeStagesTable.setModel(new DefaultTableModel(null, new String[] {"Estágio", "Data de Finalização"})
		{		
			private static final long serialVersionUID = 4071603921812411049L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		});
		closeStagesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		closeStagesTable.setCellSelectionEnabled(true);
		closeStagesTable.getTableHeader().setReorderingAllowed(false);
		closeStagesTable.setRowHeight(25);
		
		openStagesPanel.setViewportView(openStagesTable);
		closeStagesPanel.setViewportView(closeStagesTable);
		panel.setLayout(layout);
	}


	class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {

		private static final long serialVersionUID = -1903358975859108679L;
		
		private JTable table;
		
		private String text;
		private ImageIcon icon;
	
		private JButton renderButton;
		private JButton editButton;
		
		public ButtonColumn(JTable table, int column) {
			
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
		
		public ButtonColumn (JTable table, int column, ImageIcon icon) {
			
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
			
			controller.finalizeStage(openStagesTable, closeStagesTable, row);
					
		}
				
	}



}
