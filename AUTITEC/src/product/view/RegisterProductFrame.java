package product.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractCellEditor;
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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import model.Material;
import product.controller.RegisterProductFrameController;
import userInterface.components.UpperTextField;
import util.Icon;
import util.ShowMessage;

public class RegisterProductFrame extends JFrame {

	private static final long serialVersionUID = 7571387875478878514L;
		
	private JTextArea txDescription;
	private JSpinner spinnerAmount;
	private JComboBox<Material> cbMaterial;
	private UpperTextField txProduct;
	private JTable table;
	
	private JButton btnCancel;
	private JButton btnClear;
	private JButton btnRegister;
	private JButton btnInsert;
	
	private RegisterProductFrameController controller;
	private JSeparator separator;

	public RegisterProductFrame() {
		
		controller = new RegisterProductFrameController(this);
		
		initialize();
		setListeners();
		
		controller.fillMaterials(cbMaterial);
		
	}
	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 680, 485);
		setTitle("Registrar Produto");
		Icon.setIcon(this);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel btnPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(btnPanel, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(RegisterProductFrame.class.getResource("/resources/cancel.png")));
		btnPanel.add(btnCancel);
		
		btnClear = new JButton("Limpar Dados");
		btnClear.setIcon(new ImageIcon(RegisterProductFrame.class.getResource("/resources/clear.png")));
		btnPanel.add(btnClear);
		
		btnRegister = new JButton("Registrar");
		btnRegister.setIcon(new ImageIcon(RegisterProductFrame.class.getResource("/resources/ok.png")));
		btnPanel.add(btnRegister);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblProduct = new JLabel("Produto");
		txProduct = new UpperTextField	();
		txProduct.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder("Descrição"));
		
		JLabel lblMaterial = new JLabel("Material");
		
		cbMaterial = new JComboBox<Material>();
		
		JLabel lblAmount = new JLabel("Quantidade");
		
		spinnerAmount = new JSpinner();
		spinnerAmount.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		
		btnInsert = new JButton("Inserir");
		btnInsert.setIcon(new ImageIcon(RegisterProductFrame.class.getResource("/resources/plus.png")));
		
		JScrollPane tableScroll = new JScrollPane();
		
		separator = new JSeparator();
		GroupLayout layout = new GroupLayout(panel);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblProduct)
							.addGap(18)
							.addComponent(txProduct, GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblMaterial)
							.addGap(18)
							.addComponent(cbMaterial, 0, 221, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblAmount)
							.addGap(18)
							.addComponent(spinnerAmount, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnInsert)
							.addGap(107))
						.addGroup(layout.createSequentialGroup()
							.addComponent(tableScroll, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduct)
						.addComponent(txProduct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaterial)
						.addComponent(cbMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAmount)
						.addComponent(spinnerAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInsert))
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
							.addGap(20)
							.addComponent(tableScroll, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(null, new String[] {"Produto", "Quantidade", "Remover"})
		{
			
			private static final long serialVersionUID = -6376960675127636933L;
			
			boolean[] columnEditables = new boolean[] {
				false, false, true
			};
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
		});
		
		tableScroll.setViewportView(table);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(25);
		
		new ButtonColumn(table, 2, new ImageIcon(getClass().getResource("/resources/clear.png"))); 
		
		
		txDescription = new JTextArea();
		txDescription.setLineWrap(true);
		scrollPane.setViewportView(txDescription);
		panel.setLayout(layout);

	}

	private void setListeners() {
		
		ActionListener buttonsListeners = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(e.getSource().equals(btnCancel)) controller.closeFrame();
				else if(e.getSource().equals(btnClear)) controller.clear();
				else if(e.getSource().equals(btnRegister)) controller.register(txProduct.getText(), txDescription.getText(), table);
				else if(e.getSource().equals(btnInsert)) insertMaterial();
				
			}
		};
		
		btnInsert.addActionListener(buttonsListeners);
		btnClear.addActionListener(buttonsListeners);
		btnCancel.addActionListener(buttonsListeners);
		btnRegister.addActionListener(buttonsListeners);
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeFrame();
			}
			
		});
		
	}
	
	private void insertMaterial() {
		
		if(cbMaterial.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Selecione o Material", "Selecione o material para Registrar ao produto");				
			return;
		}
		
		controller.insertMaterial((Material) cbMaterial.getSelectedItem(), (int) spinnerAmount.getValue(), table);
		
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
			
			((DefaultTableModel) table.getModel()).removeRow(row);
					
		}
				
	}

}
