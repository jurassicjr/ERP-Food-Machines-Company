package rh.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import model.Employee;
import model.Training;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import rh.controller.RegisterOfTrainingController;
import userInterface.components.ComboBoxAutoCompletion;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class RegisterOfTrainingFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7087487712693577771L;
	
	private JPanel principalPanel;
	private JPanel bottonPanel;
	
	private JTextField txtTitle;
	private JTextField txtDuration;
	private JTextField txtPlace;
	private JTextField txtOther;
	private JTextField txtTrainingMotive;

	private JTable table;
	
	private JLabel lblObjective;
	private JLabel lblEventType;
	private JLabel lblPeriod;
	private JLabel lblDuration;
	private JLabel lblPlace;
	private JLabel lblTrainingMotive;
	private JLabel lblEmployee;
	private JLabel lblDate;
	private JLabel lblTitle;

	private DateField txtDate;
	
	private JRadioButton rdbtnAplication;
	private JRadioButton rdbtnInfomative;

	private JComboBox<String> cboEventType;
	private JComboBox<String> cboPeriod;
	private JComboBox<Employee> cboEmployee;

	private ButtonGroup bg;
	private JScrollPane scrollPane;

	private JButton btnAdd;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	
	private RegisterOfTrainingController controller;
	
	private JRadioButton rdbtnOther;
	
	public RegisterOfTrainingFrame() {
		controller = new RegisterOfTrainingController(this);
		initialize();
		setListeners();
	}


	private void initialize() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Registro de Treinamento");
		setBounds(100, 100, 458, 513);
		setMinimumSize(new Dimension(458, 530));
		setPreferredSize(new Dimension(458, 530));
		Icon.setIcon(this);
		getContentPane().setLayout(new BorderLayout(0, 0));
		initializePrincipal();
	}


	private void initializePrincipal() {
		principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);
		
		lblTitle = new JLabel("Titulo");
		
		txtTitle = new JTextField();
		txtTitle.setColumns(10);
		
		lblObjective = new JLabel("Objetivo:");
		
		rdbtnInfomative = new JRadioButton("Informativo");
		
		rdbtnAplication = new JRadioButton("Aplicação");

		rdbtnOther = new JRadioButton("Outro");
		
		bg = new ButtonGroup();
		bg.add(rdbtnAplication);
		bg.add(rdbtnInfomative);
		bg.add(rdbtnOther);
		
		lblEventType = new JLabel("Tipo do Evento");
		
		cboEventType = new JComboBox<String>();
		cboEventType.addItem("Interno");
		cboEventType.addItem("Externo");
		cboEventType.addItem("InCompany");
		new ComboBoxAutoCompletion(cboEventType);
		cboEventType.setSelectedIndex(-1);
		
		lblPeriod = new JLabel("Periodo");
		
		cboPeriod = new JComboBox<String>();
		cboPeriod.addItem("Matutino");
		cboPeriod.addItem("Vespertino");
		cboPeriod.addItem("Integral");
		new ComboBoxAutoCompletion(cboPeriod);
		cboPeriod.setSelectedIndex(-1);
		
		lblDuration = new JLabel("Duração");
		
		txtDuration = new JTextField();
		txtDuration.setColumns(10);
		
		lblPlace = new JLabel("Local");
		
		txtPlace = new JTextField();
		txtPlace.setColumns(10);
		
		lblTrainingMotive = new JLabel("Motivo");
		
		txtTrainingMotive = new JTextField();
		txtTrainingMotive.setColumns(10);
		
		lblEmployee = new JLabel("Funcionário");
		
		cboEmployee = new JComboBox<Employee>();
		new ComboBoxAutoCompletion(cboEmployee);
		controller.fillEmployees(cboEmployee);
		cboEmployee.setSelectedIndex(-1);
		
		btnAdd = new JButton("Adicionar");
		btnAdd.setIcon(new ImageIcon(RegisterOfTrainingFrame.class.getResource("/resources/plus.png")));
		
		scrollPane = new JScrollPane();
		
		lblDate = new JLabel("Data");
		
		txtDate = CalendarFactory.createDateField();
		
		
		txtOther = new JTextField();
		txtOther.setEnabled(false);
		txtOther.setColumns(10);
		
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblTitle)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTitle, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblObjective)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnInfomative)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnAplication)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnOther)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtOther, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblEventType)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboEventType, 0, 346, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblPeriod)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboPeriod, 0, 382, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblDuration)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtDuration, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblDate)
							.addGap(4)
							.addComponent(txtDate, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblEmployee)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboEmployee, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAdd))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPlace)
								.addComponent(lblTrainingMotive))
							.addGap(18)
							.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtPlace, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
								.addComponent(txtTrainingMotive, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitle)
						.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblObjective)
						.addComponent(rdbtnInfomative)
						.addComponent(rdbtnAplication)
						.addComponent(rdbtnOther)
						.addComponent(txtOther, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEventType)
						.addComponent(cboEventType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPeriod)
						.addComponent(cboPeriod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDuration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDuration)
						.addComponent(lblDate)
						.addComponent(txtDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlace)
						.addComponent(txtPlace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTrainingMotive)
						.addComponent(txtTrainingMotive, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmployee)
						.addComponent(cboEmployee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		String[] Header = new String[]{"Nome", "função"};
		table.setModel(new DefaultTableModel(null, Header){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1355463789545401843L;
			
			boolean[] columnEditables = new boolean[] {
					false, false
				};
				
				@Override
                public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		
		});
		
		scrollPane.setViewportView(table);
		principalPanel.setLayout(gl_principalPanel);
		
		initializeBotton();
		
	}


	private void initializeBotton() {
		bottonPanel = new JPanel();
		bottonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(bottonPanel, BorderLayout.SOUTH);
		
		btnConfirm = new JButton("Confirmar");
		btnConfirm.setIcon(new ImageIcon(RegisterOfTrainingFrame.class.getResource("/resources/ok.png")));
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(RegisterOfTrainingFrame.class.getResource("/resources/1419366170_17-16.png")));
		
		btnClear = new JButton("Limpar");
		btnClear.setIcon(new ImageIcon(RegisterOfTrainingFrame.class.getResource("/resources/ClearFrame.png")));
		
		bottonPanel.add(btnClear);
		bottonPanel.add(btnCancel);
		bottonPanel.add(btnConfirm);
		
	}
	
	private void setListeners(){
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.close();
			}
		});
		
		
		
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnAdd))addEmployee();
				else if(e.getSource().equals(btnConfirm))register();
				else if(e.getSource().equals(btnClear))clear();
				else if(e.getSource().equals(btnCancel))controller.close();
				else if(e.getSource().equals(rdbtnOther))checkJTextField();
				else if(e.getSource().equals(rdbtnAplication))checkJTextField();
				else if(e.getSource().equals(rdbtnInfomative))checkJTextField();
			}
		};
		
		btnCancel.addActionListener(buttonListener);
		btnAdd.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
		rdbtnOther.addActionListener(buttonListener);
		
		KeyListener tableKeyListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
														
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getSource().equals(table))removeTableItem(e);
			}
		};
		
		table.addKeyListener(tableKeyListener);
	}
	
	private void checkJTextField() {
		if (rdbtnOther.isSelected()) {
	        txtOther.setEnabled(true);
        }else {
        	txtOther.setEnabled(false);
        }
	}
	
	private void addEmployee(){
		if(cboEmployee.getSelectedIndex() == -1){
			ShowMessage.errorMessage(this, "Erro", "Selecione um empregados para adicionar");
			return;
		}
		Employee e = (Employee) cboEmployee.getSelectedItem();
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		String employeeFunction = e.getJob().getCbo().getTitle();
		tbl.addRow(new Object[]{e, employeeFunction});
	}
	
	private void register(){
		int i = ShowMessage.questionMessage(this, "Registro", "Deseja relamente registrar esse treinamento ?");
		if(i == JOptionPane.NO_OPTION)return;
		if(txtDate.getValue().equals(null)){
			ShowMessage.errorMessage(this, "Erro", "Selecione a data do treinamento");
			return;
		}
		if(txtDuration.getText().isEmpty()){
			ShowMessage.errorMessage(this, "Erro", "Insira a duração do evento!");
			return;
		}
		if(txtPlace.getText().isEmpty()){
			ShowMessage.errorMessage(this, "Erro", "Insira o local do evento");
			return;
		}
		if(txtTitle.getText().isEmpty()){
			ShowMessage.errorMessage(this, "Erro", "Insira o nome do treinamento!");
			return;
		}
		if(txtTrainingMotive.getText().isEmpty()){
			ShowMessage.errorMessage(this, "Erro", "Insira o motivo do treinamento!");
			return;
		}
		if(cboEventType.getSelectedIndex() == -1){
			ShowMessage.errorMessage(this, "Erro", "Selecione o tipo do treinamento!");
			return;
		}
		if(cboPeriod.getSelectedIndex() == -1){
			ShowMessage.errorMessage(this, "Erro", "Selecione o periodo do treinamento");
			return;
		}
		if(table.getRowCount() == 0){
			ShowMessage.errorMessage(this, "Erro", "Adicione ao menos um funcionário para participar");
			return;
		}
		
		Training t = new Training();
		Date date = (Date) txtDate.getValue();
		String duration = txtDuration.getText();
		String eventType = (String) cboEventType.getSelectedItem();
		String motive = txtTrainingMotive.getText();
		String objective = null;
		if(rdbtnAplication.isSelected()){
			objective = "Aplicação";
		}else if(rdbtnInfomative.isSelected()){
			objective = "Informativo";
		}else if(rdbtnOther.isSelected()) {
			objective = "Outro";
			if(txtOther.getText().isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Insira qual o outro tipo de treinamento");
				return;
			}
		}
		String period = (String) cboPeriod.getSelectedItem();
		String otherMotive = txtOther.getText();
		String title = txtTitle.getText();
		List<Employee> list = fillList();
		
		t.setDate(date);
		t.setDuration(duration);
		t.setEventType(eventType);
		t.setMotive(motive);
		t.setObjective(objective);
		t.setPeriod(period);
		t.setTitle(title);
		t.setEmployeeList(list);
		t.setPlace(txtPlace.getText());
		t.setOtherMotive(otherMotive);
		controller.register(t);
		ShowMessage.successMessage(this, "Sucesso", "Treinamento registrado com êxito!");
		ClearFrame.clear(this);
	}
	
	private void clear(){
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente limpar todos os campos ?");
		if(i == JOptionPane.NO_OPTION)return;
		ClearFrame.clear(this);
	}
	
	private void removeTableItem(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_DELETE){
			int i = table.getSelectedRow();
			if(i == -1)return;
			DefaultTableModel tbl = (DefaultTableModel) table.getModel();
			tbl.removeRow(i);
		}
	}
	
	private List<Employee> fillList(){
		int rowCount = table.getRowCount();
		List<Employee> list = new ArrayList<Employee>();
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		for( int i = 0; i < rowCount; i++){
			Employee e = (Employee) tbl.getValueAt(i, 0);
			list.add(e);
		}
		return list;
	}
}
