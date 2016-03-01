package financial.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import database.dao.CnpjDAO;
import financial.controller.RegisterBillFrameController;
import model.BillGroup;
import model.BillName;
import model.BillSubGroup;
import model.CNPJ;
import net.sf.nachocalendar.components.DateField;
import userInterface.components.RealNumberField;
import userInterface.components.UpperTextField;
import util.Icon;

public class RegisterBillFrame extends JFrame {

	private static final long serialVersionUID = -4336339858592180045L;
	
	private JTabbedPane tabPanel;
	
	private JPanel contentPane;
		
	private JButton btnCancel;
	private JButton btnClear;
	private JButton btnRegister;
		
	private RealNumberField txValue;
	private DateField txPayDate;
	private UpperTextField txCreditor;
	private RealNumberField txtEntryValue;
	
	private JTextArea txObservation;
	
	private JComboBox<BillGroup> cbGroup;
	private JComboBox<BillSubGroup> cbSubGroup;
	private JComboBox<BillName> cbBill;
	private JComboBox<Integer> cbInstallments;
	private JComboBox<Integer> cbDeadlines;
	private JComboBox<CNPJ> cbCnpj;
	
	private JCheckBox chckbxEntry;
	
	private JTable tableInstallments;
	
	private RegisterBillFrameController controller;
			
	public RegisterBillFrame() {
		
		controller = new RegisterBillFrameController(this);
		
		initialize();
		setListeners();
		fillCnpjs();
				
	}

	/**
	 * Inicializa os componentes gráficos do frame
	 */
	private void initialize() {
		
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 506, 504);
		setMinimumSize(new Dimension(506, 400));
		setTitle("Registrar Conta a Pagar");
		Icon.setIcon(this);
		
		tabPanel = new JTabbedPane();
		setContentPane(tabPanel);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		tabPanel.add("Registro de Conta", contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblGroup = new JLabel("Grupo");
		cbGroup = new JComboBox<BillGroup>();
		cbGroup.setSelectedIndex(-1);
		
		JLabel lblSubGroup = new JLabel("Subgrupo");
		cbSubGroup = new JComboBox<BillSubGroup>();
		cbSubGroup.setEnabled(false);
		cbSubGroup.setSelectedIndex(-1);
		
		JLabel lblBill = new JLabel("Conta");
		cbBill = new JComboBox<BillName>();
		cbBill.setEnabled(false);
		
		JLabel lblValue = new JLabel("Valor");
		txValue = new RealNumberField();
		
		JLabel lblDataDeVencimento = new JLabel("Data de Vencimento");
		txPayDate = new DateField();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblCredor = new JLabel("Credor");
		txCreditor = new UpperTextField();
		txCreditor.setColumns(10);
		
		JLabel lblInstallments = new JLabel("Parcelas");
		cbInstallments = new JComboBox<Integer>();
		for(int i = 0; i < 10; i++) cbInstallments.addItem(i + 1);
		
		JLabel lblCnpj = new JLabel("CNPJ");		
		cbCnpj = new JComboBox<CNPJ>();
		
		chckbxEntry = new JCheckBox("Entrada");
		chckbxEntry.setEnabled(false);
		
		JLabel lblEntryValue = new JLabel("Valor de Entrada");
		txtEntryValue = new RealNumberField();
		txtEntryValue.setEnabled(false);
		
		GroupLayout layout = new GroupLayout(panel);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, layout.createSequentialGroup()
							.addComponent(lblGroup)
							.addGap(18)
							.addComponent(cbGroup, 0, 408, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, layout.createSequentialGroup()
							.addComponent(lblSubGroup)
							.addGap(18)
							.addComponent(cbSubGroup, 0, 391, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, layout.createSequentialGroup()
							.addComponent(lblBill)
							.addGap(18)
							.addComponent(cbBill, 0, 408, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, layout.createSequentialGroup()
							.addComponent(lblValue)
							.addGap(18)
							.addComponent(txValue, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblDataDeVencimento)
							.addGap(18)
							.addComponent(txPayDate, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, layout.createSequentialGroup()
							.addComponent(lblCredor)
							.addGap(18)
							.addComponent(txCreditor, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, layout.createSequentialGroup()
							.addComponent(lblCnpj)
							.addGap(18)
							.addComponent(cbCnpj, 0, 412, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, layout.createSequentialGroup()
							.addComponent(chckbxEntry, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblEntryValue)
							.addGap(18)
							.addComponent(txtEntryValue, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblInstallments, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbInstallments, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGroup)
						.addComponent(cbGroup, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSubGroup)
						.addComponent(cbSubGroup, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBill)
						.addComponent(cbBill, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblValue)
						.addComponent(txValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDataDeVencimento)
						.addComponent(txPayDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCredor)
						.addComponent(txCreditor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCnpj)
						.addComponent(cbCnpj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxEntry)
						.addComponent(lblEntryValue)
						.addComponent(txtEntryValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInstallments)
						.addComponent(cbInstallments, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		txObservation = new JTextArea();
		txObservation.setLineWrap(true);
		txObservation.setWrapStyleWord(true);
		
		scrollPane.setViewportView(txObservation);
		scrollPane.setBorder(new TitledBorder("Obserações"));
		panel.setLayout(layout);
		
		JPanel buttonsPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonsPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(RegisterBillFrame.class.getResource("/resources/cancel.png")));
		buttonsPanel.add(btnCancel);
		
		btnClear = new JButton("Limpar Dados");
		btnClear.setIcon(new ImageIcon(RegisterBillFrame.class.getResource("/resources/clear.png")));
		buttonsPanel.add(btnClear);
		
		btnRegister = new JButton("Registrar");
		btnRegister.setIcon(new ImageIcon(RegisterBillFrame.class.getResource("/resources/ok.png")));
		buttonsPanel.add(btnRegister);
		
		controller.setBills(cbGroup);
		
		JScrollPane panelInstallments = new JScrollPane();
		getContentPane().add(panelInstallments, BorderLayout.CENTER);
		
		tableInstallments = new JTable();
		tableInstallments.setRowHeight(25);
		tableInstallments.setModel(new DefaultTableModel(
			new Object[][] { },
			new String[] {"Parcela", "Vencimento", "Prazo" }
		) {
			
			private static final long serialVersionUID = 7739898997748290935L;
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return (column == tableInstallments.getColumnCount() -1);
			}
			
		});
		panelInstallments.setViewportView(tableInstallments);
		
		tabPanel.addTab("Parcelas", panelInstallments);
		tabPanel.setEnabledAt(1, false);
		
		cbDeadlines = new JComboBox<>();
		
		Integer installmentsOptions[] = {10, 15, 20 , 30, 45, 60, 90};
		
		for(int option : installmentsOptions) 
			cbDeadlines.addItem(option);
		
		tableInstallments.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(cbDeadlines));
	}

	private void setListeners() {
		
		ActionListener btnListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource().equals(btnCancel)) controller.closeFrame();
				else if(e.getSource().equals(btnClear)) {
					controller.clear();
					cbInstallments.setSelectedIndex(0);
				}
				else if(e.getSource().equals(btnRegister)) register();
				
			}
		};
		
		btnCancel.addActionListener(btnListener);
		btnClear.addActionListener(btnListener);
		btnRegister.addActionListener(btnListener);
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeFrame();
			}
			
		});
		
		ActionListener cbListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource().equals(cbGroup)) {
					controller.setSubGroups((BillGroup) cbGroup.getSelectedItem(), cbSubGroup);
				}
				else if(e.getSource().equals(cbSubGroup)) {
					if(cbSubGroup.getSelectedIndex() != -1) {
						controller.setBillnames((BillSubGroup) cbSubGroup.getSelectedItem(), cbBill);
					}
				}
				else if(e.getSource().equals(cbInstallments)) {
					enableInstallments();
				}
//				else if(e.getSource().equals(cbDeadlines)) {
//					int period = (int) cbDeadlines.getSelectedItem();
//					//updateDeadline(period);
//					//System.out.println(cbDeadlines.getSelectedItem());
//					//e.getSource();
//				}
				
			}
		};
		
		cbGroup.addActionListener(cbListener);
		cbSubGroup.addActionListener(cbListener);
		cbInstallments.addActionListener(cbListener);
		
		cbDeadlines.addActionListener(cbListener);
		
//		cbDeadlines.addItemListener(new ItemListener() {
//			
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				
//				if(e.getStateChange() == ItemEvent.SELECTED) {
//					//System.out.println(e.getItem());
//					//updateDeadline((int) e.getItem());
//				}
//				
//			}
//			
//		});
		
		chckbxEntry.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtEntryValue.setEnabled(chckbxEntry.isSelected());
			}
			
		});
		
		tableInstallments.getColumnModel().getColumn(2).getCellEditor().addCellEditorListener(new CellEditorListener() {
			
			private int count = 0;
			
			@Override
			public void editingStopped(ChangeEvent e) {
				
				if(count++ % 2 == 1) {	
					int period = (int) ((TableCellEditor) e.getSource()).getCellEditorValue();
					updateDeadline(period);
				}
				
			}
			
			@Override
			public void editingCanceled(ChangeEvent e) {}
			
		});
		
	}
	
	private void register() {
		
		double value = txValue.getValue();
		Date date = (Date) txPayDate.getValue();
		String observation = txObservation.getText();
		String creditor = txCreditor.getText();
		BillName billName = null;
		boolean hasName = cbBill.isEnabled();
		BillSubGroup subGroup = (BillSubGroup) cbSubGroup.getSelectedItem();
		BillGroup  group = (BillGroup) cbGroup.getSelectedItem();
		
		if(hasName) billName = (BillName) cbBill.getSelectedItem();
			
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		controller.register(value, date, observation, creditor, billName, subGroup, group, hasName);
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	private void enableInstallments() {
		
		if(cbInstallments.getSelectedItem() == null) return;
		
		int installments = (int) cbInstallments.getSelectedItem();
		
		boolean enableTab = (installments != 1);
		
		tabPanel.setEnabledAt(1, enableTab);
		txPayDate.setEnabled(!enableTab);
		chckbxEntry.setEnabled(enableTab);
		
		if(enableTab) {
			
			DefaultTableModel model = (DefaultTableModel) tableInstallments.getModel();
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			
			while (tableInstallments.getRowCount() > 0)
				model.removeRow(0);
			
			for(int i = 0; i < installments; ++i) {
				
				date = calculateDeadline(date, 30);
				
				model.addRow(new Object[]{"Parcela " + (i + 1), formatter.format(date), 30});
			}
				
		}
	
	}
	
	private Date calculateDeadline(Date date, int period) {
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		
		if(period == 30 || period == 60 || period == 90)
			calendar.add(Calendar.MONTH, period / 30);
		else calendar.add(Calendar.DAY_OF_YEAR, period);
		
		return calendar.getTime();
	}
	
	private void updateDeadline(int period) {
		
		int row = tableInstallments.getSelectedRow();
		
		while(row < tableInstallments.getRowCount()) {
		
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			
			if(row != 0) {
				
				String prevDate = (String) tableInstallments.getValueAt(row - 1, 1);
				
				try { date = formatter.parse(prevDate); }
				catch (ParseException e) { e.printStackTrace(); }
					
			}
			
			Date deadline = calculateDeadline(date, period);
			tableInstallments.setValueAt(formatter.format(deadline), row, 1);
			
			++row;
			if(row < tableInstallments.getRowCount())
				period = (int) tableInstallments.getValueAt(row, 2);
		}
		
	}
	
	private void fillCnpjs() {
		
		List<CNPJ> cnpjs = new CnpjDAO().getAll();
		
		for(CNPJ cnpj : cnpjs) 
			cbCnpj.addItem(cnpj);
		
		cbCnpj.setSelectedIndex(-1);
	}
}
