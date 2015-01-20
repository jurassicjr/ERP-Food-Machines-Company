package sales.view;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.CNPJ;
import model.Inventory;
import model.Material;
import model.Supplier;
import sales.controller.InventoryController;
import util.Icon;
import util.ShowMessage;

public class InventoryFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3703798593062437817L;
	private JTextField txtFiscalNote;
	private JPanel bottonPanel;
	private JSpinner spinner;
	private JComboBox<Material> cboMaterial;
	private JLabel lblAmmount;
	private JLabel lblCnpjDeEntrada;
	private JLabel lblNotaFiscal;
	private InventoryController controller;
	private JLabel lblMaterial;
	private JSeparator separator;
	private JComboBox<Supplier> cboSupplier;
	private JLabel lblFornecedor;
	private JFrame frame;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JComboBox<CNPJ> cboCNPJ;

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
		setBounds(0, 0, 361, 248);
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
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
		        .addGroup(
		                gl_principalPanel
		                        .createSequentialGroup()
		                        .addContainerGap()
		                        .addGroup(
		                                gl_principalPanel
		                                        .createParallelGroup(Alignment.TRAILING, false)
		                                        .addComponent(separator, Alignment.LEADING)
		                                        .addGroup(
		                                                Alignment.LEADING,
		                                                gl_principalPanel
		                                                        .createSequentialGroup()
		                                                        .addComponent(lblMaterial)
		                                                        .addPreferredGap(ComponentPlacement.RELATED)
		                                                        .addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE,
		                                                                179, GroupLayout.PREFERRED_SIZE)
		                                                        .addGap(18)
		                                                        .addComponent(lblAmmount)
		                                                        .addPreferredGap(ComponentPlacement.RELATED)
		                                                        .addComponent(spinner, GroupLayout.PREFERRED_SIZE,
		                                                                GroupLayout.DEFAULT_SIZE,
		                                                                GroupLayout.PREFERRED_SIZE))
		                                        .addGroup(
		                                                Alignment.LEADING,
		                                                gl_principalPanel
		                                                        .createSequentialGroup()
		                                                        .addComponent(lblCnpjDeEntrada)
		                                                        .addPreferredGap(ComponentPlacement.RELATED)
		                                                        .addComponent(cboCNPJ, 0, GroupLayout.DEFAULT_SIZE,
		                                                                Short.MAX_VALUE))
		                                        .addGroup(
		                                                Alignment.LEADING,
		                                                gl_principalPanel.createSequentialGroup()
		                                                        .addComponent(lblNotaFiscal)
		                                                        .addPreferredGap(ComponentPlacement.RELATED)
		                                                        .addComponent(txtFiscalNote))
		                                        .addGroup(
		                                                Alignment.LEADING,
		                                                gl_principalPanel
		                                                        .createSequentialGroup()
		                                                        .addComponent(lblFornecedor)
		                                                        .addPreferredGap(ComponentPlacement.RELATED)
		                                                        .addComponent(cboSupplier, 0, GroupLayout.DEFAULT_SIZE,
		                                                                Short.MAX_VALUE)))
		                        .addContainerGap(18, Short.MAX_VALUE)));
		gl_principalPanel.setVerticalGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_principalPanel
		                .createSequentialGroup()
		                .addContainerGap()
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblMaterial)
		                                .addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(lblAmmount)
		                                .addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE))
		                .addGap(18)
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblCnpjDeEntrada)
		                                .addComponent(cboCNPJ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE))
		                .addGap(18)
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblNotaFiscal)
		                                .addComponent(txtFiscalNote, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addGap(18)
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblFornecedor)
		                                .addComponent(cboSupplier, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(ComponentPlacement.UNRELATED)
		                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
		                .addContainerGap(103, Short.MAX_VALUE)));
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
					}
				}
			}
		};
		btnConfirmar.addActionListener(buttonListeners);
		btnCancelar.addActionListener(buttonListeners);
	}
	private void addToInventory() {
		Inventory i = new Inventory();
		i.setAmmount((int) spinner.getValue());
		i.setCnpj((CNPJ) cboCNPJ.getSelectedItem());
		i.setMaterial((Material) cboMaterial.getSelectedItem());
		i.setSupplier((Supplier) cboSupplier.getSelectedItem());
		i.setFiscalNote(txtFiscalNote.getText());
		controller.addToInventory(i);
	}
}
