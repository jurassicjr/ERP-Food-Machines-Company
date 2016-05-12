package sales.view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.CNPJ;
import model.Nfe;
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

	private JLabel lblFiscalNote;

	private JComboBox<Nfe> cboFiscalNote;

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
		setBounds(0, 0, 697, 339);
		setMinimumSize(new Dimension(697, 339));
		setPreferredSize(new Dimension(697, 339));
		initializePrincipal();
	}

	public void initializePrincipal() {
		JPanel principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);
		
		lblCnpjDeEntrada = new JLabel("CNPJ de Entrada");

		cboCNPJ = new JComboBox<CNPJ>();
		controller.fillCnpj(cboCNPJ);

		separator = new JSeparator();
		
		lblFiscalNote = new JLabel("Nota Fiscal");
		
		btnFiscalNoteRegister = new JButton("Registrar");
		btnFiscalNoteRegister.setIcon(new ImageIcon(InventoryFrame.class.getResource("/resources/plus.png")));
		
		lblChecklist = new JLabel("CheckList");
		
		btnCheckList = new JButton("Emitir e Registrar");
		btnCheckList.setIcon(new ImageIcon(InventoryFrame.class.getResource("/resources/plus.png")));
		
		cboFiscalNote = new JComboBox<Nfe>();
		controller.fillFiscalNote(cboFiscalNote);
		
		JComboBox cboCheckList = new JComboBox();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Observa\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblCnpjDeEntrada)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboCNPJ, 0, 576, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblFiscalNote)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboFiscalNote, 0, 502, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFiscalNoteRegister))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblChecklist)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboCheckList, 0, 471, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCheckList))
						.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE))
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
						.addComponent(lblFiscalNote)
						.addComponent(btnFiscalNoteRegister)
						.addComponent(cboFiscalNote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChecklist)
						.addComponent(btnCheckList)
						.addComponent(cboCheckList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
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
			@Override
			public void windowGainedFocus(WindowEvent e) {
				updateCbo();
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

	private void updateCbo() {
		controller.fillFiscalNote(cboFiscalNote);
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
