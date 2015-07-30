package sales.view.register;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import model.Client;
import model.ClientProperties;
import model.ClientPropertiesMaterial;
import model.Material;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.ClientPropertiesRegisterController;
import userInterface.components.ComboBoxAutoCompletion;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class ClientPropertiesRegisterFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2532417792876582285L;
	private JPanel principalPanel;
	private JPanel subPanel;
	
	private DateField txtEntryDate;
	
	private JTable table;
	
	private JTextField txtFiscalNote;
	
	private JLabel lblClient;
	private JLabel lblEntryDate;
	private JLabel lblProperties;
	private JLabel lblAmmount;
	private JLabel lblFiscalNote;
	
	private JComboBox<Client> cboClient;
	private JComboBox<Material> cboMaterial;

	private JSpinner ammountSpinner;

	private JButton btnAddPropertie;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	
	private ClientPropertiesRegisterController controller;

	public ClientPropertiesRegisterFrame() {
		controller = new ClientPropertiesRegisterController(this);
		initialize();
		setListeners();
	}

	private void initialize() {
		setTitle("Propriedades do Cliente");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		Icon.setIcon(this);
		setBounds(100, 100, 614, 342);
		setMinimumSize(new Dimension(614, 342));
		setPreferredSize(new Dimension(614, 342));
		getContentPane().setLayout(new BorderLayout(0, 0));
		initializePrincipal();
	}

	private void initializePrincipal() {
		principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);

		lblClient = new JLabel("Cliente");

		cboClient = new JComboBox<Client>();
		controller.fillCLientsCBO(cboClient);
		new ComboBoxAutoCompletion(cboClient);

		lblEntryDate = new JLabel("Data da Entrada");

		txtEntryDate = CalendarFactory.createDateField();

		lblProperties = new JLabel("Propriedade");

		cboMaterial = new JComboBox<Material>();
		controller.fillProductCbo(cboMaterial);
		new ComboBoxAutoCompletion(cboMaterial);

		lblAmmount = new JLabel("Quantidade");

		ammountSpinner = new JSpinner();
		ammountSpinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));

		btnAddPropertie = new JButton("Adicionar");
		btnAddPropertie.setIcon(new ImageIcon(ClientPropertiesRegisterFrame.class.getResource("/resources/plus.png")));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		lblFiscalNote = new JLabel("Nota Fiscal");

		txtFiscalNote = new JTextField();
		txtFiscalNote.setColumns(10);
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel
		        .setHorizontalGroup(gl_principalPanel
		                .createParallelGroup(Alignment.LEADING)
		                .addGroup(
		                        gl_principalPanel
		                                .createSequentialGroup()
		                                .addContainerGap()
		                                .addGroup(
		                                        gl_principalPanel
		                                                .createParallelGroup(Alignment.LEADING)
		                                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 578,
		                                                        Short.MAX_VALUE)
		                                                .addGroup(
		                                                        gl_principalPanel
		                                                                .createSequentialGroup()
		                                                                .addGroup(
		                                                                        gl_principalPanel
		                                                                                .createParallelGroup(
		                                                                                        Alignment.LEADING,
		                                                                                        false)
		                                                                                .addGroup(
		                                                                                        gl_principalPanel
		                                                                                                .createSequentialGroup()
		                                                                                                .addComponent(
		                                                                                                        lblClient)
		                                                                                                .addPreferredGap(
		                                                                                                        ComponentPlacement.RELATED)
		                                                                                                .addComponent(
		                                                                                                        cboClient,
		                                                                                                        GroupLayout.PREFERRED_SIZE,
		                                                                                                        263,
		                                                                                                        GroupLayout.PREFERRED_SIZE))
		                                                                                .addGroup(
		                                                                                        gl_principalPanel
		                                                                                                .createSequentialGroup()
		                                                                                                .addComponent(
		                                                                                                        lblProperties)
		                                                                                                .addPreferredGap(
		                                                                                                        ComponentPlacement.RELATED)
		                                                                                                .addComponent(
		                                                                                                        cboMaterial,
		                                                                                                        0,
		                                                                                                        GroupLayout.DEFAULT_SIZE,
		                                                                                                        Short.MAX_VALUE)))
		                                                                .addGap(18)
		                                                                .addGroup(
		                                                                        gl_principalPanel
		                                                                                .createParallelGroup(
		                                                                                        Alignment.LEADING)
		                                                                                .addGroup(
		                                                                                        gl_principalPanel
		                                                                                                .createSequentialGroup()
		                                                                                                .addComponent(
		                                                                                                        lblEntryDate)
		                                                                                                .addPreferredGap(
		                                                                                                        ComponentPlacement.RELATED)
		                                                                                                .addComponent(
		                                                                                                        txtEntryDate,
		                                                                                                        GroupLayout.DEFAULT_SIZE,
		                                                                                                        177,
		                                                                                                        Short.MAX_VALUE))
		                                                                                .addGroup(
		                                                                                        gl_principalPanel
		                                                                                                .createSequentialGroup()
		                                                                                                .addComponent(
		                                                                                                        lblAmmount)
		                                                                                                .addPreferredGap(
		                                                                                                        ComponentPlacement.RELATED)
		                                                                                                .addComponent(
		                                                                                                        ammountSpinner,
		                                                                                                        GroupLayout.PREFERRED_SIZE,
		                                                                                                        GroupLayout.DEFAULT_SIZE,
		                                                                                                        GroupLayout.PREFERRED_SIZE)
		                                                                                                .addGap(18)
		                                                                                                .addComponent(
		                                                                                                        btnAddPropertie))))
		                                                .addGroup(
		                                                        gl_principalPanel
		                                                                .createSequentialGroup()
		                                                                .addComponent(lblFiscalNote)
		                                                                .addPreferredGap(ComponentPlacement.RELATED)
		                                                                .addComponent(txtFiscalNote,
		                                                                        GroupLayout.DEFAULT_SIZE, 522,
		                                                                        Short.MAX_VALUE))).addContainerGap()));
		gl_principalPanel.setVerticalGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_principalPanel
		                .createSequentialGroup()
		                .addContainerGap()
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblClient)
		                                .addComponent(cboClient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE)
		                                .addComponent(lblEntryDate)
		                                .addComponent(txtEntryDate, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addGap(18)
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblProperties)
		                                .addComponent(cboMaterial, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(lblAmmount)
		                                .addComponent(ammountSpinner, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(btnAddPropertie))
		                .addGap(8)
		                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
		                .addGap(18)
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblFiscalNote)
		                                .addComponent(txtFiscalNote, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addContainerGap(163, Short.MAX_VALUE)));

		table = new JTable();
		String[] header = new String[] { "Propriedade", "Quantidade" };
		table.setModel(new DefaultTableModel(null, header) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 8993022617068673336L;

			boolean[] columnEditables = new boolean[] { false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		principalPanel.setLayout(gl_principalPanel);

		initializeSub();
	}

	private void initializeSub() {
		subPanel = new JPanel();
		subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(subPanel, BorderLayout.SOUTH);

		btnConfirm = new JButton("Confirmar");
		btnConfirm.setIcon(new ImageIcon(ClientPropertiesRegisterFrame.class.getResource("/resources/ok.png")));
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(ClientPropertiesRegisterFrame.class.getResource("/resources/cancel.png")));
		btnClear = new JButton("Limpar");
		btnClear.setIcon(new ImageIcon(ClientPropertiesRegisterFrame.class.getResource("/resources/ClearFrame.png")));

		subPanel.add(btnClear);
		subPanel.add(btnCancel);
		subPanel.add(btnConfirm);
	}

	private void setListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.close();
			}
		});

		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnConfirm))
					confirm();
				else if (e.getSource().equals(btnCancel))
					controller.close();
				else if (e.getSource().equals(btnClear))
					clearFrame();
				else if (e.getSource().equals(btnAddPropertie))
					addPropertiesToTable();
			}
		};
		btnConfirm.addActionListener(buttonListener);
		btnCancel.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		btnAddPropertie.addActionListener(buttonListener);

		KeyListener tableKeyListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getSource().equals(table))
					deleteTableElement(e);
			}
		};
		table.addKeyListener(tableKeyListener);

		KeyListener txtKeyListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getSource().equals(txtFiscalNote) && e.getKeyCode() == KeyEvent.VK_ENTER)
					confirm();
			}
		};
		txtFiscalNote.addKeyListener(txtKeyListener);
	}

	private void deleteTableElement(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DELETE) {
			int i = table.getSelectedRow();
			if (i == -1)
				return;
			DefaultTableModel tbl = (DefaultTableModel) table.getModel();
			tbl.removeRow(i);
		}
	}

	private void confirm() {
		int i = ShowMessage.questionMessage(this, "Registrar",
		        "Deseja realmente registrar essas propriedades de clientes ?");
		if (i == JOptionPane.NO_OPTION) {
			return;
		}
		Date entryDate = (Date) txtEntryDate.getValue();
		if (entryDate == null) {
			ShowMessage.errorMessage(this, "Erro",
			        "Erro ao realizar registro de propriedades do cliente, insira a date de entrada!");
			return;
		}
		if (txtFiscalNote.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro",
			        "Erro ao realizar registro de propriedades do cliente, insita a Nota Fiscal!");
			return;
		}
		if (cboClient.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Erro",
			        "Erro ao realizar registro de propriedades do cliente, selecione um cliente!");
			return;
		}
		if (table.getRowCount() == 0) {
			ShowMessage.errorMessage(this, "Erro",
			        "Erro ao realizar registro de propriedades do cliente, insira ao menos uma propriedade!");
			return;
		}
		String fiscalNote = txtFiscalNote.getText();

		ClientProperties cp = new ClientProperties();
		cp.setEntryDate(entryDate);
		cp.setFiscalNote(fiscalNote);
		cp.setPropertiesList(generateClientPropertiesMaterial(cp));
		controller.register(cp);

	}

	private List<ClientPropertiesMaterial> generateClientPropertiesMaterial(ClientProperties cp) {
		List<ClientPropertiesMaterial> cpmList = new ArrayList<ClientPropertiesMaterial>();
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		for (int i = 0; i < table.getRowCount(); i++) {
			Material m = (Material) tbl.getValueAt(i, 0);
			int ammount = (int) tbl.getValueAt(i, 1);
			ClientPropertiesMaterial cpm = new ClientPropertiesMaterial();
			cpm.setAmmount(ammount);
			cpm.setMaterial(m);
			cpm.setClientProrpeties(cp);
			cpmList.add(cpm);
		}
		return cpmList;
	}

	private void addPropertiesToTable() {
		if (cboMaterial.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Erro", "Selecione uma propriedade!");
			return;
		}
		Material m = (Material) cboMaterial.getSelectedItem();
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		if (verifyMatchOnTable(m, tbl)) {
			ShowMessage.errorMessage(this, "Erro", "Essa propiedade do cliente já foi adicionado!");
			return;
		}
		int ammount = (int) ammountSpinner.getValue();
		tbl.addRow(new Object[] { m, ammount });
	}

	private boolean verifyMatchOnTable(Material m, DefaultTableModel tbl) {
		for (int i = 0; i < table.getRowCount(); i++) {
			Material tableMaterial = (Material) tbl.getValueAt(i, 0);
			if (tableMaterial.equals(m))
				return true;
		}
		return false;
	}

	private void clearFrame() {
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente limpar os campos ?");
		if (i == JOptionPane.NO_OPTION)
			return;
		ClearFrame.clear(this);
	}

}
