package sales.view.register;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.Nfe;
import model.NfeMaterialRelation;
import net.miginfocom.swing.MigLayout;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.RegisterCheckListController;
import util.Icon;

public class RegisterOfCheckList extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7052983178412547878L;
	private DateField txtMaxDate;
	private DateField txtEntryDate;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	private RegisterCheckListController controller;
	private JLabel lblFiscalNote;
	private JComboBox<Nfe> cboFiscalNote;
	private JLabel lblMaxDate;
	private JLabel lblEntryDate;
	private JPanel optionsPanel;
	private List<ButtonGroup> btnGroupList = new ArrayList<ButtonGroup>();
	private List<String> functionList = new ArrayList<String>();
	List<JLabel> labelList = new ArrayList<JLabel>();
	List<JSpinner> spinnerList = new ArrayList<JSpinner>();
	private ActionListener btnGroupListener;

	public RegisterOfCheckList() {
		controller = new RegisterCheckListController(this);
		initialize();
		setListeners();
	}

	private void initialize() {
		setTitle("CHECKLIST DE MERCADORIAS");
		Icon.setIcon(this);
		setBounds(100, 100, 659, 510);
		getContentPane().setLayout(new BorderLayout(0, 0));
		initializePrincipal();
	}

	private void initializePrincipal() {
		JPanel principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);

		lblFiscalNote = new JLabel("Nota Fiscal");

		cboFiscalNote = new JComboBox<Nfe>();
		controller.fillCboFiscalNote(cboFiscalNote);
		cboFiscalNote.setSelectedIndex(-1);

		lblMaxDate = new JLabel("Data Máxima da Entrega");

		txtMaxDate = CalendarFactory.createDateField();
		txtMaxDate.setValue(null);
		
		lblEntryDate = new JLabel("Data da Entrega");

		txtEntryDate = CalendarFactory.createDateField();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Itens",
		        TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblIntegrity = new JLabel("Alguns Produtos tiveram sua integridade física danificada ?");
		
		JRadioButton rdbtnSim = new JRadioButton("Sim");
		
		JRadioButton rdbtnNo_1 = new JRadioButton("Não");
		
		ButtonGroup btnIntegrity = new ButtonGroup();
		btnIntegrity.add(rdbtnSim);
		btnIntegrity.add(rdbtnNo_1);
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblFiscalNote)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboFiscalNote, 0, 567, Short.MAX_VALUE))
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblMaxDate)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtMaxDate, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblEntryDate)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtEntryDate, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addGap(12)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblIntegrity)
							.addGap(18)
							.addComponent(rdbtnSim)
							.addGap(18)
							.addComponent(rdbtnNo_1)))
					.addContainerGap())
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFiscalNote)
						.addComponent(cboFiscalNote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaxDate)
						.addComponent(txtMaxDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEntryDate)
						.addComponent(txtEntryDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIntegrity)
						.addComponent(rdbtnSim)
						.addComponent(rdbtnNo_1))
					.addContainerGap(61, Short.MAX_VALUE))
		);

		optionsPanel = new JPanel();
		scrollPane.setViewportView(optionsPanel);
		optionsPanel.setLayout(new MigLayout("wrap 5", "[] 100 [] 20 [] 20 [][]"));
		principalPanel.setLayout(gl_principalPanel);
		initializeSub();
	}

	private void initializeSub() {
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(subPanel, BorderLayout.SOUTH);
		btnClear = new JButton("Limpar");
		btnClear.setIcon(new ImageIcon(RegisterOfCheckList.class.getResource("/resources/ClearFrame.png")));
		subPanel.add(btnClear);
				btnCancel = new JButton("Cancelar");
				btnCancel.setIcon(new ImageIcon(RegisterOfCheckList.class.getResource("/resources/cancel.png")));
				subPanel.add(btnCancel);
		
				btnConfirm = new JButton("Confirmar");
				btnConfirm.setIcon(new ImageIcon(RegisterOfCheckList.class.getResource("/resources/ok.png")));
				
						subPanel.add(btnConfirm);
	}

	private void setListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.close();
			}
		});

		ActionListener cboListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(cboFiscalNote))fillPanel();
			}
		};
		cboFiscalNote.addActionListener(cboListener);

		btnGroupListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				changeVisibility(e);
			}
		};
		for (ButtonGroup btn : btnGroupList) {
			Enumeration<AbstractButton> elements = btn.getElements();
			while (elements.hasMoreElements()) {
				JRadioButton abstractButton = (JRadioButton) elements.nextElement();
				abstractButton.addActionListener(btnGroupListener);
			}
		}

	}

	private void changeVisibility(ActionEvent e) {
		JRadioButton btn = (JRadioButton) e.getSource();
		if (e.getActionCommand().equalsIgnoreCase("No")) {
			for (JLabel lbl : labelList) {
				if (lbl.getName().equalsIgnoreCase(btn.getName())) {
					lbl.setVisible(true);
				}
			}
			for (JSpinner spinner : spinnerList) {
				if (spinner.getName().equalsIgnoreCase(btn.getName())) {
					spinner.setVisible(true);
				}
			}
		} else if (e.getActionCommand().equalsIgnoreCase("Yes")) {
			for (JLabel lbl : labelList) {
				if (lbl.getName().equalsIgnoreCase(btn.getName())) {
					lbl.setVisible(false);
				}
			}
			for (JSpinner spinner : spinnerList) {
				if (spinner.getName().equalsIgnoreCase(btn.getName())) {
					spinner.setVisible(false);
				}
			}
		}
	}

	private void fillPanel() {
		if (cboFiscalNote.getSelectedIndex() == -1)	return;
		optionsPanel.removeAll();
		optionsPanel.repaint();
		Nfe nfe = (Nfe) cboFiscalNote.getSelectedItem();
		txtMaxDate.setValue(nfe.getPurchaseOrder().getDeliveryDate());
		List<NfeMaterialRelation> list = controller.fillPanel(nfe);
		for (NfeMaterialRelation nfeMaterialRelation : list) {
			optionsPanel.add(new JLabel(nfeMaterialRelation.getAmmount() + " " + nfeMaterialRelation.toString()));
			JRadioButton rdbtnYes = new JRadioButton("Conferido");
			rdbtnYes.setActionCommand("Yes");
			rdbtnYes.setName(nfeMaterialRelation.getMaterial().toString());
			rdbtnYes.addActionListener(btnGroupListener);
			JRadioButton rdbtnNo = new JRadioButton("Veio Faltando");
			rdbtnNo.setActionCommand("No");
			rdbtnNo.setName(nfeMaterialRelation.getMaterial().toString());
			rdbtnNo.addActionListener(btnGroupListener);
			optionsPanel.add(rdbtnNo);
			optionsPanel.add(rdbtnYes);
			ButtonGroup btnGroup = new ButtonGroup();
			btnGroup.add(rdbtnNo);
			btnGroup.add(rdbtnYes);
			btnGroupList.add(btnGroup);
			functionList.add(nfeMaterialRelation.toString());
			JLabel label = new JLabel("Qto:");
			label.setName(nfeMaterialRelation.getMaterial().toString());
			JSpinner spinner = new JSpinner();
			spinner.setName(nfeMaterialRelation.getMaterial().toString());
			optionsPanel.add(label).setVisible(false);
			optionsPanel.add(spinner).setVisible(false);
			labelList.add(label);
			spinnerList.add(spinner);
			
		}
		this.revalidate();
		optionsPanel.repaint();
	}
}
