package product.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.AbstractCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import model.Material;
import model.Product;
import product.controller.UpdateOfProductController;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class UpdateOfProductFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1817667153998530684L;

	private ArrayList<Material> materialList;

	private UpdateOfProductController controller;

	private JFrame frame;

	private JButton btnCancel;

	private JButton btnClear;

	private JButton btnUpdate;

	private JComboBox<Material> cbMaterial;

	private JSpinner spinnerAmount;

	private AbstractButton btnInsert;

	private JSeparator separator;

	private JTable table;

	private JTextArea txtDescription;

	private JComboBox<Product> cboProduct;

	private JButton btnDelete;
	private JLabel lblImagem;
	private JTextField txtPath;
	private JButton btnOpen;
	private JLabel lblPicture;

	public UpdateOfProductFrame() {
		materialList = new ArrayList<Material>();
		frame = this;
		controller = new UpdateOfProductController();
		initialize();
		setListeners();
	}
	public void setSelectedProduct(Product product)
	{
		cboProduct.setSelectedItem(product);
		fill();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 744, 708);
		setTitle("Atualizar/Deletar Produto");
		Icon.setIcon(this);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel btnPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(btnPanel, BorderLayout.SOUTH);

		btnDelete = new JButton("Apagar");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDelete.setIcon(new ImageIcon(UpdateOfProductFrame.class.getResource("/resources/clear.png")));
		btnPanel.add(btnDelete);

		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(RegisterProductFrame.class.getResource("/resources/cancel.png")));
		btnPanel.add(btnCancel);

		btnClear = new JButton("Limpar Dados");
		btnClear.setIcon(new ImageIcon(UpdateOfProductFrame.class.getResource("/resources/ClearFrame.png")));
		btnPanel.add(btnClear);

		btnUpdate = new JButton("Atualizar");
		btnUpdate.setIcon(new ImageIcon(UpdateOfProductFrame.class.getResource("/resources/update.png")));
		btnPanel.add(btnUpdate);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		JLabel lblProduct = new JLabel("Produto");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder("Descrição"));

		JLabel lblMaterial = new JLabel("Material");

		cbMaterial = new JComboBox<Material>();
		controller.fillMaterial(cbMaterial);

		JLabel lblAmount = new JLabel("Quantidade");

		spinnerAmount = new JSpinner();
		spinnerAmount.setModel(new SpinnerNumberModel(1, 1, 100, 1));

		btnInsert = new JButton("Inserir");
		btnInsert.setIcon(new ImageIcon(RegisterProductFrame.class.getResource("/resources/plus.png")));

		JScrollPane tableScroll = new JScrollPane();

		separator = new JSeparator();

		cboProduct = new JComboBox<Product>();
		controller.fillProduct(cboProduct);
		
		lblImagem = new JLabel("Imagem");
		
		txtPath = new JTextField();
		txtPath.setColumns(10);
		
		btnOpen = new JButton("Abrir");
		btnOpen.setIcon(new ImageIcon(UpdateOfProductFrame.class.getResource("/resources/open.png")));
		
		lblPicture = new JLabel("");
		
		
		
		//lblPicture.setIcon(thumbnail);
		lblPicture.setBorder(new EtchedBorder());
		
		GroupLayout layout = new GroupLayout(panel);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(17)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblProduct)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboProduct, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblMaterial)
							.addGap(18)
							.addComponent(cbMaterial, 0, 252, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblAmount)
							.addGap(18)
							.addComponent(spinnerAmount, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnInsert)
							.addGap(100))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblImagem)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPath, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnOpen))
						.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
							.addComponent(lblPicture, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
						.addComponent(tableScroll, GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(25)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduct)
						.addComponent(cboProduct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaterial)
						.addComponent(cbMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAmount)
						.addComponent(spinnerAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInsert))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(tableScroll, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblImagem)
						.addComponent(txtPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOpen))
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGap(69)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblPicture, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)))
					.addGap(23))
		);

		table = new JTable();
		table.setModel(new DefaultTableModel(null, new String[] { "Material", "Quantidade", "Remover" }) {

			private static final long serialVersionUID = -6376960675127636933L;

			boolean[] columnEditables = new boolean[] { false, false, true };

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

		txtDescription = new JTextArea();
		txtDescription.setLineWrap(true);
		scrollPane.setViewportView(txtDescription);
		panel.setLayout(layout);

	}

	private void setListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeFrame(frame);
			}
		});

		ActionListener cboListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(cboProduct)) {
					if (cboProduct.getSelectedItem() != null) {
						fill();
					}
				}

			}
		};

		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnCancel)) {
					controller.closeFrame(frame);
				} else if (e.getSource().equals(btnDelete)) {
					int i = ShowMessage.questionMessage(frame, "APAGAR", "Deseja realmente apagar o produto \""
					        + ((Product) cboProduct.getSelectedItem()).getName() + " \"");
					if (i == JOptionPane.YES_OPTION) {
						Product product = (Product) cboProduct.getSelectedItem();
						controller.removeProduct(product);
						cboProduct.removeItem(product);
						ShowMessage.successMessage(frame, "Remoção", "Produto deletado com sucesso!");
					}
				} else if (e.getSource().equals(btnInsert)) {
					materialList.add((Material) cbMaterial.getSelectedItem());
					controller.addMaterial((Material) cbMaterial.getSelectedItem(), table, spinnerAmount,
					        (Product) cboProduct.getSelectedItem());
				} else if (e.getSource().equals(btnUpdate)) {
					Product product = (Product) cboProduct.getSelectedItem();
					product.setDescription(txtDescription.getText());
					controller.updateProduct(product, table);
					ShowMessage.successMessage(frame, "Sucesso", "Produto atualizado com sucesso!");
					ClearFrame.clear(frame);
				} else if (e.getSource().equals(btnClear)) {
					ClearFrame.clear(frame);
				}

			}
		};
		btnClear.addActionListener(buttonListener);
		btnInsert.addActionListener(buttonListener);
		btnUpdate.addActionListener(buttonListener);
		btnDelete.addActionListener(buttonListener);
		cboProduct.addActionListener(cboListener);
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

		public ButtonColumn(JTable table, int column, ImageIcon icon) {

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
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
		        boolean hasFocus, int row, int column) {

			if (hasFocus) {
				renderButton.setForeground(table.getForeground());
				renderButton.setBackground(UIManager.getColor("Button.background"));
			} else if (isSelected) {
				renderButton.setForeground(table.getSelectionForeground());
				renderButton.setBackground(table.getSelectionBackground());
			} else {
				renderButton.setForeground(table.getForeground());
				renderButton.setBackground(UIManager.getColor("Button.background"));
			}

			renderButton.setText((value == null) ? "" : value.toString());
			if (icon != null)
				renderButton.setIcon(icon);
			return renderButton;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			text = (value == null) ? "" : value.toString();
			editButton.setText(text);
			if (icon != null)
				editButton.setIcon(icon);
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

			controller.deleteMaterial((Material) table.getModel().getValueAt(row, 0),
			        (Product) cboProduct.getSelectedItem());

			((DefaultTableModel) table.getModel()).removeRow(row);
		}

	}
	
	private void fill() {
		Product product = (Product) cboProduct.getSelectedItem();
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		for (int i = tbl.getRowCount() - 1; i >= 0; i--) {
			tbl.removeRow(i);
		}
		int labelWidth = 95;
		 ImageIcon imagem = new ImageIcon(controller.getImage(product.getId()));
			ImageIcon thumbnail = null;
			if(imagem.getIconWidth() > labelWidth){       
				thumbnail = new ImageIcon(imagem.getImage().getScaledInstance(labelWidth, -1, Image.SCALE_DEFAULT));  
			}
			else thumbnail = imagem;
			lblImagem.setIcon(imagem);
		txtDescription.setText(product.getDescription());
		controller.fillMaterialTable(product, table,lblImagem);
	}
}
