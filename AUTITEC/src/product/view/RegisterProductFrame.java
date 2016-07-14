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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import model.Material;
import product.controller.RegisterProductFrameController;
import userInterface.components.FileChooser;
import userInterface.components.UpperTextField;
import userInterface.components.filters.ImageFilter;
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
	private JTextField txtPath;

	private JButton btnOpenPhoto;

	private FileChooser fileChooser;

	public RegisterProductFrame() {
		
		controller = new RegisterProductFrameController(this);
		
		initialize();
		setListeners();
		fileChooser = new FileChooser(this);
		controller.fillMaterials(cbMaterial);
		
	}
	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 759, 562);
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
		txProduct = 
				new UpperTextField	();
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
		
		JLabel lblSendPhoto = new JLabel("Enviar Imagem");
		
		txtPath = new JTextField();
		txtPath.setColumns(10);
		
		btnOpenPhoto = new JButton("Abrir foto");
		
		btnOpenPhoto.setIcon(new ImageIcon(RegisterProductFrame.class.getResource("/resources/open.png")));
		GroupLayout layout = new GroupLayout(panel);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblProduct)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txProduct, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblSendPhoto)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPath, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnOpenPhoto))
						.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup()
									.addComponent(lblMaterial)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cbMaterial, 0, 434, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblAmount)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinnerAmount, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnInsert))
								.addComponent(tableScroll, GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE))
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnInsert)
						.addComponent(spinnerAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAmount)
						.addComponent(cbMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMaterial))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(tableScroll, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSendPhoto)
						.addComponent(txtPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOpenPhoto))
					.addGap(16))
		);
		layout.linkSize(SwingConstants.VERTICAL, new Component[] {scrollPane, tableScroll});
		
		table = new JTable();
		table.setModel(new DefaultTableModel(null, new String[] {"Produto", "Quantidade", "Remover"})
		{
			
			private static final long serialVersionUID = -6376960675127636933L;
			
			boolean[] columnEditables = new boolean[] {
				false, false, true
			};
			
			@Override
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
				else if(e.getSource().equals(btnRegister)) controller.register(txProduct.getText(), txDescription.getText(), table, txtPath.getText());
				else if(e.getSource().equals(btnInsert)) insertMaterial();
				else if(e.getSource().equals(btnOpenPhoto))selectOutput(fileChooser, txtPath);
			}
		};
		
		btnInsert.addActionListener(buttonsListeners);
		btnClear.addActionListener(buttonsListeners);
		btnCancel.addActionListener(buttonsListeners);
		btnRegister.addActionListener(buttonsListeners);
		btnOpenPhoto.addActionListener(buttonsListeners);
		
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
	
	public void selectOutput(FileChooser fileChooser, JTextField txReportFile) {
		
		fileChooser.showOpenDialog(new ImageFilter());
		
		if(fileChooser.hasSelectedFile()) {
			
			String path = fileChooser.getSelectedPathFile();
				
			txReportFile.setText(path);
		}
					
	}
}
