package sales.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.MaskFormatter;

import model.CNPJ;
import model.Inventory;
import model.Material;
import model.Supplier;
import sales.controller.InventoryController;
import userInterface.components.JNumberFormatField;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class InventoryFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3703798593062437817L;
	private JTextField txtFiscalNote;
	
	private JFrame frame;
	private JPanel bottonPanel;
	private JSpinner spinner;
	
	private JComboBox<Material> cboMaterial;
	private JComboBox<Supplier> cboSupplier;
	private JComboBox<CNPJ> cboCNPJ;
	
	private JLabel lblAmmount;
	private JLabel lblCnpjDeEntrada;
	private JLabel lblNotaFiscal;
	private JLabel lblMaterial;
	private JLabel lblFornecedor;
	
	private InventoryController controller;

	private JSeparator separator;

	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JLabel lblEntryValue;
	private JTextField txtEntryValue;
	private JLabel lblNoteValue;
	private JTextField txtNoteValue;
	private JLabel lblIcms;
	private JTextField txtIcms;
	private JLabel lblPIS;
	private JTextField txtPIS;

	public InventoryFrame() {
		frame = this;
		controller = new InventoryController();
		initialize();
		setListeners();
	}

	public void initialize() {
		setTitle("ESTOQUE DE MATERIAL");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		Icon.setIcon(this);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(0, 0, 669, 344);
		setMinimumSize(new Dimension(699, 344));
		setPreferredSize(new Dimension(699, 344));
		initializePrincipal();
	}

	public void initializePrincipal() {
		JPanel principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);

		lblMaterial = new JLabel("Material");

		cboMaterial = new JComboBox<Material>();
		controller.fillProducts(cboMaterial);
		lblAmmount = new JLabel("Quantidade");

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		
		lblCnpjDeEntrada = new JLabel("CNPJ de Entrada");

		cboCNPJ = new JComboBox<CNPJ>();
		controller.fillCnpj(cboCNPJ);

		lblNotaFiscal = new JLabel("Nota Fiscal");

		txtFiscalNote = new JTextField();
		txtFiscalNote.setColumns(10);

		lblFornecedor = new JLabel("Fornecedor");

		cboSupplier = new JComboBox<Supplier>();
		controller.fillSuppliers(cboSupplier);

		separator = new JSeparator();
		
		lblEntryValue = new JLabel("Valor de entrada");
		
	//	try {
	        txtEntryValue = new JTextField();//new JFormattedTextField(new MaskFormatter("R$##.##"));
     //   } catch (ParseException e1) {
	 //       e1.printStackTrace();
       // }
		txtEntryValue.setColumns(10);
		
		lblNoteValue = new JLabel("Valor de nota");
		
		txtNoteValue = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
		txtNoteValue.setColumns(10);
		
		lblIcms = new JLabel("ICMS");
		
		try {
	        txtIcms = new JFormattedTextField(new MaskFormatter("##.##%"));
        } catch (ParseException e) {
	        e.printStackTrace();
        }
		txtIcms.setColumns(10);
		
		lblPIS = new JLabel("PIS");
		
		try {
	        txtPIS = new JFormattedTextField(new MaskFormatter("##.##%"));
        } catch (ParseException e) {
	        e.printStackTrace();
        }
		txtPIS.setColumns(10);
		
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_principalPanel.createSequentialGroup()
							.addComponent(lblMaterial)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblAmmount)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblCnpjDeEntrada)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboCNPJ, 0, 548, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblNotaFiscal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtFiscalNote, GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblFornecedor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboSupplier, 0, 574, Short.MAX_VALUE))
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblEntryValue)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtEntryValue, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblNoteValue)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtNoteValue, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_principalPanel.createSequentialGroup()
							.addComponent(lblIcms)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtIcms, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblPIS)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPIS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaterial)
						.addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAmmount)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCnpjDeEntrada)
						.addComponent(cboCNPJ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNotaFiscal)
						.addComponent(txtFiscalNote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFornecedor)
						.addComponent(cboSupplier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNoteValue)
						.addComponent(txtNoteValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIcms)
						.addComponent(txtIcms, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPIS)
						.addComponent(txtPIS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEntryValue)
						.addComponent(txtEntryValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		principalPanel.setLayout(gl_principalPanel);

		initializeBotton();
	}

	private void initializeBotton() {
		bottonPanel = new JPanel();
		getContentPane().add(bottonPanel, BorderLayout.SOUTH);
		bottonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		btnCancelar = new JButton("Cancelar");

		btnCancelar.setIcon(new ImageIcon(InventoryFrame.class.getResource("/resources/cancel.png")));
		bottonPanel.add(btnCancelar);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setIcon(new ImageIcon(InventoryFrame.class.getResource("/resources/ok.png")));
		bottonPanel.add(btnConfirmar);
	}

	private void setListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeFrame(frame);
			}
		});
		
		ActionListener buttonListeners = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnCancelar))controller.closeFrame(frame);
				else if(e.getSource().equals(btnConfirmar)) {
					int i = ShowMessage.questionMessage(frame, "INSERÇÂO", "Deseja inserir esse material ao estoque?");
					if( i == JOptionPane.YES_OPTION) {
						addToInventory();
						ClearFrame.clear(frame);
					}
				}
			}
		};
		btnConfirmar.addActionListener(buttonListeners);
		btnCancelar.addActionListener(buttonListeners);
		
		FocusListener txtFocusListener = new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(e.getSource().equals(txtPIS)) {
					double cash = Double.parseDouble(txtNoteValue.getText().replaceAll("R|\\$", "").replaceAll(",", "\\."));
					double rate = Double.parseDouble(txtPIS.getText().replaceAll("%", "").replaceAll(",", "\\.").trim());
					double finalCash = cash + (cash*(rate/100));
					String cashString = String.valueOf(finalCash);
					if(cashString.length()<4) {
						int i = 4 - cashString.length();
						for(int a=0; a<i; a++) {
							cashString = cashString + "0";
						}
					}
					txtEntryValue.setText(cashString);
				}
			}
		};
		txtIcms.addFocusListener(txtFocusListener);
		txtPIS.addFocusListener(txtFocusListener);
	}

	private void addToInventory() {
		Inventory i = new Inventory();
		i.setAmmount((int) spinner.getValue());
		i.setCnpj((CNPJ) cboCNPJ.getSelectedItem());
		i.setMaterial((Material) cboMaterial.getSelectedItem());
		i.setSupplier((Supplier) cboSupplier.getSelectedItem());
		i.setFiscalNote(txtFiscalNote.getText());
		i.setNoteValue(Double.parseDouble(txtNoteValue.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim()));
		i.setIcms(Double.parseDouble(txtIcms.getText().replaceAll("%", "").replaceAll(",", "\\.").trim()));
		i.setPis(Double.parseDouble(txtPIS.getText().replaceAll("%", "").replaceAll(",", "\\.").trim()));
		i.setEntryValue(Double.parseDouble(txtEntryValue.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim()));
		controller.addToInventory(i);
		
	}
}
