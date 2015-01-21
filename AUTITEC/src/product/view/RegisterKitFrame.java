package product.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.Product;
import product.controller.RegisterKitFrameController;
import userInterface.components.UpperTextField;
import util.Icon;

public class RegisterKitFrame extends JFrame {

	private static final long serialVersionUID = -99076146960822052L;
	
	private UpperTextField txKit;
	private JTable productsTable;
	
	private JSpinner spinnerAmount;
	
	private JComboBox<Product> cbProduct;
	
	private JTextArea txDescription;
	
	private RegisterKitFrameController controller;
	
	private JButton btnCancel;
	private JButton btnClear;
	private JButton btnRegister;
	private JButton btnAdd;

	public RegisterKitFrame() {
		
		controller = new RegisterKitFrameController(this);
		
		initialize();
		setListeners();
		
		controller.setProducts(cbProduct);
		
	}
	
	public void initialize() {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 540, 490);
		setTitle("Registrar Kit");
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
		btnCancel.setIcon(new ImageIcon(RegisterKitFrame.class.getResource("/resources/cancel.png")));
		btnPanel.add(btnCancel);
		
		btnClear = new JButton("Limpar");
		btnClear.setIcon(new ImageIcon(RegisterKitFrame.class.getResource("/resources/clear.png")));
		btnPanel.add(btnClear);
		
		btnRegister = new JButton("Registrar");
		btnRegister.setIcon(new ImageIcon(RegisterKitFrame.class.getResource("/resources/ok.png")));
		btnPanel.add(btnRegister);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblKit = new JLabel("Kit");
		txKit = new UpperTextField();
		
		JScrollPane descriptionPanel = new JScrollPane();
		descriptionPanel.setBorder(new TitledBorder("Descrição"));
		
		JLabel lblProduct = new JLabel("Produto");
		
		cbProduct = new JComboBox<Product>();
		
		JLabel lblAmount = new JLabel("Quantidade");
		spinnerAmount = new JSpinner();
		spinnerAmount.setModel(new SpinnerNumberModel(1, 1, 110, 1));
		
		btnAdd = new JButton("Adicionar");
		btnAdd.setIcon(new ImageIcon(RegisterKitFrame.class.getResource("/resources/plus.png")));
		
		JScrollPane tableScrollPanel = new JScrollPane();
		GroupLayout layout = new GroupLayout(panel);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(tableScrollPanel, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
						.addComponent(descriptionPanel, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblKit)
							.addGap(18)
							.addComponent(txKit, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblProduct)
							.addGap(18)
							.addComponent(cbProduct, 0, 173, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblAmount)
							.addGap(18)
							.addComponent(spinnerAmount, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnAdd)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKit)
						.addComponent(txKit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(descriptionPanel, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduct)
						.addComponent(cbProduct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAmount)
						.addComponent(spinnerAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd))
					.addGap(18)
					.addComponent(tableScrollPanel, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		txDescription = new JTextArea();
		txDescription.setLineWrap(true);
		descriptionPanel.setViewportView(txDescription);
		
		productsTable = new JTable();
		String[] header = new String[] {"Produto", "Quantidade"};
		productsTable.setModel(new DefaultTableModel(null, header) {

			/**
			 * 
			 */
            private static final long serialVersionUID = -395239143227986712L;
            
            boolean[] columnEditables = new boolean[] { false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
		});
		tableScrollPanel.setViewportView(productsTable);
		panel.setLayout(layout);
	}
	
	public void setListeners() {
		
		ActionListener btnListerns = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource().equals(btnAdd)) controller.addProduct((Product) cbProduct.getSelectedItem(), (int) spinnerAmount.getValue(), productsTable);
				else if(e.getSource().equals(btnCancel)) controller.closeFrame();
				else if(e.getSource().equals(btnClear)) controller.clear();
				else if(e.getSource().equals(btnRegister)) controller.Register();
				
			}
		};
		
		btnCancel.addActionListener(btnListerns);
		btnClear.addActionListener(btnListerns);
		btnRegister.addActionListener(btnListerns);
		btnAdd.addActionListener(btnListerns);
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent w) { 
				controller.closeFrame();
			}
			
		});
		
	}
	
}
