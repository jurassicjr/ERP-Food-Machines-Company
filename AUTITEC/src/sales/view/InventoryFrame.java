package sales.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.LayoutStyle.ComponentPlacement;

import model.CNPJ;
import sales.controller.InventoryController;
import sales.view.register.RegisterNFeEntryOnSystemView;
import sales.view.register.RegisterOfCheckList;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class InventoryFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3703798593062437817L;
	
	private JFrame frame;
	private JPanel bottonPanel;
	private JComboBox<CNPJ> cboCNPJ;
	private JLabel lblCnpjDeEntrada;
	
	private InventoryController controller;

	private JSeparator separator;

	private JButton btnConfirmar;
	private JButton btnCancelar;
	private boolean isEPI = false;
	private JLabel lblChecklist;
	private JButton btnCheckList;

	private JButton btnFiscalNoteRegister;

	private JLabel lblFiscalNoteRegister;

	public InventoryFrame() {
		frame = this;
		controller = new InventoryController();
		initialize();
		setListeners();
	}
	
	public InventoryFrame(boolean isEPI) {
		frame = this;
		controller = new InventoryController();
		this.isEPI = true;
		initialize();
		setListeners();
	}

	public void initialize() {
		setTitle("ESTOQUE DE MATERIAL");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		Icon.setIcon(this);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(0, 0, 435, 187);
		setMinimumSize(new Dimension(435, 187));
		setPreferredSize(new Dimension(435, 187));
		initializePrincipal();
	}

	public void initializePrincipal() {
		JPanel principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);
		
		lblCnpjDeEntrada = new JLabel("CNPJ de Entrada");

		cboCNPJ = new JComboBox<CNPJ>();
		controller.fillCnpj(cboCNPJ);

		separator = new JSeparator();
		
		lblFiscalNoteRegister = new JLabel("Registrar Nota Fiscal");
		
		btnFiscalNoteRegister = new JButton("Registrar");
		btnFiscalNoteRegister.setIcon(new ImageIcon(InventoryFrame.class.getResource("/resources/plus.png")));
		
		lblChecklist = new JLabel("CheckList");
		
		btnCheckList = new JButton("Emitir e Registrar");
		btnCheckList.setIcon(new ImageIcon(InventoryFrame.class.getResource("/resources/plus.png")));
		
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
						.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_principalPanel.createSequentialGroup()
								.addComponent(lblCnpjDeEntrada)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(cboCNPJ, 0, 314, Short.MAX_VALUE))
							.addGroup(gl_principalPanel.createSequentialGroup()
								.addComponent(lblFiscalNoteRegister)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnFiscalNoteRegister)
								.addGap(18)
								.addComponent(lblChecklist)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnCheckList))))
					.addContainerGap())
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCnpjDeEntrada)
						.addComponent(cboCNPJ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFiscalNoteRegister)
						.addComponent(btnFiscalNoteRegister)
						.addComponent(lblChecklist)
						.addComponent(btnCheckList))
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
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
				else if(e.getSource().equals(btnFiscalNoteRegister))new RegisterNFeEntryOnSystemView().setVisible(true);
				else if(e.getSource().equals(btnCheckList))new RegisterOfCheckList().setVisible(true);
			}
		};
		btnConfirmar.addActionListener(buttonListeners);
		btnCancelar.addActionListener(buttonListeners);
		btnFiscalNoteRegister.addActionListener(buttonListeners);
		btnCheckList.addActionListener(buttonListeners);
	}

	private void addToInventory() {
//		Inventory i = new Inventory();
//		i.setAmmount((int) spinner.getValue());
//		i.setCnpj((CNPJ) cboCNPJ.getSelectedItem());
//		i.setMaterial((Material) cboMaterial.getSelectedItem());
//		i.setSupplier((Supplier) cboSupplier.getSelectedItem());
//		i.setFiscalNote(txtFiscalNote.getText());
//		i.setNoteValue(Double.parseDouble(txtNoteValue.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim()));
//		i.setIcms(Double.parseDouble(txtIcms.getText().replaceAll("%", "").replaceAll(",", "\\.").trim()));
//		i.setPis(Double.parseDouble(txtPIS.getText().replaceAll("%", "").replaceAll(",", "\\.").trim()));
//		i.setEntryValue(Double.parseDouble(txtEntryValue.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim()));
//		controller.addToInventory(i);
		
	}
}
